import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.listener.PatternTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter
import receiver.KotlinReceiver
import java.util.concurrent.CountDownLatch

@SpringBootApplication(scanBasePackages = arrayOf("receiver**"))
class Application {

    companion object {
        val LOGGER: Logger = LoggerFactory.getLogger(Application::class.java)

        @JvmStatic
        fun main(args: Array<String>) {
            val ctx = SpringApplication.run(Application::class.java, *args)
            val template = ctx.getBean(StringRedisTemplate::class.java)
            val latch = ctx.getBean(CountDownLatch::class.java)

            LOGGER.info("Sending message...")
            template.convertAndSend("chat", "Hello from Redis!")
            latch.await()
            System.exit(0)
        }
    }

    @Bean
    fun latch(): CountDownLatch = CountDownLatch(1)

    @Bean
    fun receiver(latch: CountDownLatch): KotlinReceiver = KotlinReceiver(latch)

    @Bean
    fun template(connectionFactory: RedisConnectionFactory): StringRedisTemplate = StringRedisTemplate(connectionFactory)

    @Bean
    fun listenerAdapter(receiver: KotlinReceiver): MessageListenerAdapter = MessageListenerAdapter(receiver, "receiveMessage")

    @Bean
    fun container(connectionFactory: RedisConnectionFactory, listenerAdapter: MessageListenerAdapter): RedisMessageListenerContainer {
        val container = RedisMessageListenerContainer()
        container.connectionFactory = connectionFactory
        container.addMessageListener(listenerAdapter, PatternTopic("chat"))
        return container
    }
}