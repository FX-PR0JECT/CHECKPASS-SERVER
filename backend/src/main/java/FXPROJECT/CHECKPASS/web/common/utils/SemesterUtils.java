package FXPROJECT.CHECKPASS.web.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@Component
public class SemesterUtils {

    public String getSemester() {
        int month = LocalDate.now().getMonthValue();
        String semester;

        if (month <= 6) {
            semester = "1학기";
        } else {
            semester = "2학기";
        }

        return semester;
    }
}
