package receiver

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.concurrent.CountDownLatch

@Component
open class KotlinReceiver(@Autowired val latch: CountDownLatch) {

    val LOGGER: Logger = LoggerFactory.getLogger(KotlinReceiver::class.java)

    fun receiveMessage(message: String) {
        LOGGER.info("Received <$message>")
        latch.countDown()
    }

}