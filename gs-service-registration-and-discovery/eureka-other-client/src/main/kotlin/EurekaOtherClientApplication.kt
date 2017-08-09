import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient


@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = arrayOf("controller**"))
open class EurekaOtherClientApplication {
    val log: Logger = LoggerFactory.getLogger(EurekaOtherClientApplication::class.java)

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(EurekaOtherClientApplication::class.java, *args)
        }

    }
}