package controller

import model.Greeting
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

@RestController
class GreetingController {
    companion object {
        val template = "Hello, %s!"
        val counter = AtomicLong()
    }

    @RequestMapping("/greeting")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String): Greeting =
            Greeting(counter.incrementAndGet(), String.format(template, name))
}