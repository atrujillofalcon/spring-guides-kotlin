package service

import org.springframework.core.io.Resource
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Path
import java.util.stream.Stream

interface StorageService {
    fun init()
    fun deleteAll(): Boolean
    fun load(filename: String): Path
    fun store(file: MultipartFile)
    fun loadAll(): Stream<Path>
    fun loadAsResource(filename: String): Resource
}