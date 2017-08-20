package schedule

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Component
class ScheduledKotlinTask {

    val log = LoggerFactory.getLogger(ScheduledKotlinTask::class.java)
    val dateFormat = DateTimeFormatter.ISO_DATE_TIME

    @Scheduled(fixedRate = 5000)
    fun reportCurrentTime() = log.info("The time is now ${dateFormat.format(LocalDateTime.now())}")

}