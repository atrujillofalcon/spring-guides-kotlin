import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import service.StorageService
import storage.StorageProperties

@SpringBootApplication(scanBasePackages = arrayOf("service**", "storage**", "controller**"))
@EnableConfigurationProperties(StorageProperties::class)
open class KotlinApplication {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(KotlinApplication::class.java, *args)
        }
    }

    @Bean
    open fun init(storageService: StorageService) = CommandLineRunner {
        storageService.deleteAll()
        storageService.init()
    }



}