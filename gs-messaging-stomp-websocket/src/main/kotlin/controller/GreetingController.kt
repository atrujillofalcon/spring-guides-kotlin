package controller

import model.Greeting
import model.HelloMessage
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller

@Controller
open class GreetingController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    open fun greeting(message: HelloMessage): Greeting {
        Thread.sleep(1000)
        return Greeting("Hello, " + message.name + "!")
    }

}