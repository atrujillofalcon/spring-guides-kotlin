import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient


@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = arrayOf("controller**"))
open class EurekaClientApplication {
    val log: Logger = LoggerFactory.getLogger(EurekaClientApplication::class.java)

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(EurekaClientApplication::class.java, *args)
        }

    }
}