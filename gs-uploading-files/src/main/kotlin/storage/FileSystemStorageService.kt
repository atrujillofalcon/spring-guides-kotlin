package storage

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Service
import org.springframework.util.FileSystemUtils
import org.springframework.web.multipart.MultipartFile
import service.StorageService
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.stream.Stream

@Service
open class FileSystemStorageService @Autowired
constructor(properties: StorageProperties) : StorageService {

    private val rootLocation: Path = Paths.get(properties.location)

    override fun init() {
        Files.createDirectory(rootLocation)
    }

    override fun store(file: MultipartFile) {
        if (file.isEmpty)
            throw StorageException("Failed to store empty file " + file.originalFilename)
        Files.copy(file.inputStream, this.rootLocation.resolve(file.originalFilename))
    }

    override fun loadAll(): Stream<Path> {
        return Files.walk(this.rootLocation, 1)
                .filter { path -> path != this.rootLocation }
                .map { path -> this.rootLocation.relativize(path) }

    }

    override fun loadAsResource(filename: String): Resource {
        val file = load(filename)
        val resource = UrlResource(file.toUri())
        return if (resource.exists() || resource.isReadable)
            resource
        else
            throw StorageFileNotFoundException("Could not read file: " + filename)
    }

    override fun deleteAll() = FileSystemUtils.deleteRecursively(rootLocation.toFile())
    override fun load(filename: String): Path = rootLocation.resolve(filename)
}
