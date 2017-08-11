import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@EnableEurekaServer
@SpringBootApplication(scanBasePackages = arrayOf("controller**"))
class EurekaServiceApplication {
    val log: Logger = LoggerFactory.getLogger(EurekaServiceApplication::class.java)

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(EurekaServiceApplication::class.java, *args)
        }

    }
}