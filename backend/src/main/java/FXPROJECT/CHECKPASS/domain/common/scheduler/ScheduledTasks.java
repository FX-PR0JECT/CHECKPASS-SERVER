package FXPROJECT.CHECKPASS.domain.common.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ScheduledTasks {

    private final JdbcTemplate jdbcTemplate;

    public ScheduledTasks(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Scheduled(cron = "0 0,30 9-17 * * 1-5")
    public void markAbsent() throws Exception {
        jdbcTemplate.execute("CALL mark_absent()");
    }
}
