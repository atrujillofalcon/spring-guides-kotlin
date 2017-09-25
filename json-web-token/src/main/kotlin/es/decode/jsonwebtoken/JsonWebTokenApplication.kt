package es.decode.jsonwebtoken

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class JsonWebTokenApplication

fun main(args: Array<String>) {
    SpringApplication.run(JsonWebTokenApplication::class.java, *args)
}
