package FXPROJECT.CHECKPASS.web.common.utils;

import FXPROJECT.CHECKPASS.domain.entity.attendance.StartSemesterDays;
import FXPROJECT.CHECKPASS.domain.repository.lectureweek.JpaLectureWeekRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;

import static java.time.format.DateTimeFormatter.ISO_DATE;

@Slf4j
@Component
@RequiredArgsConstructor
public class LectureWeekUtils {

    private final JpaLectureWeekRepository weekRepository;

    public Integer getWeek(){

        int year = LocalDate.now().getYear();

        boolean before = LocalDate.now().isBefore(LocalDate.of(year, Month.AUGUST, 1));

        StartSemesterDays byId = null;

        if (before){
            byId = weekRepository.findBySemester(1);
        }else {
            byId = weekRepository.findBySemester(2);
        }

        LocalDate parse = LocalDate.parse(byId.getStartSemesterDay(), ISO_DATE);

        int base = parse.getDayOfYear();
        int nowDayOfYear = LocalDate.now().getDayOfYear();

        log.info("base : {} , nowDayOfYear : {}", base, nowDayOfYear);

        int i = (nowDayOfYear/7) - (base/7);

        log.info(" now week : {} " , i + 1);

        return i + 1;
    }

}
