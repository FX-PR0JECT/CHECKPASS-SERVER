package FXPROJECT.CHECKPASS.web.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;

@Slf4j
@Component
public class RandomNumberUtils {

    public int generateAttendanceCode() {
        Random random = new Random();

        int digit1 = random.nextInt(10);
        int digit2 = random.nextInt(10);
        int digit3 = random.nextInt(10);
        int digit4 = random.nextInt(10);

        int attendanceCode = digit1 * 1000 + digit2 * 100 + digit3 * 10 + digit4;

        return attendanceCode;
    }
}