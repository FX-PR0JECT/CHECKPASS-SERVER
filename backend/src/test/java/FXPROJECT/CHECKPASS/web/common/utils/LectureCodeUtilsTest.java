package FXPROJECT.CHECKPASS.web.common.utils;

import FXPROJECT.CHECKPASS.web.form.requestForm.lectures.register.LectureTimeSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@Slf4j
class LectureCodeUtilsTest {

    @Test
    public void transferCode(){

        LectureCodeUtils lectureCodeUtils = new LectureCodeUtils();

        LectureTimeSource lectureTimeSource = new LectureTimeSource();

        List<String> lectureDay = new ArrayList<>();
        lectureDay.add("Monday");
        lectureDay.add("Tuesday");

        lectureTimeSource.setLectureDays(lectureDay);

        List<String> lectureStartTimes = new ArrayList<>();
        lectureStartTimes.add("09:00");
        lectureStartTimes.add("10:30");

        lectureTimeSource.setLectureStartTime(lectureStartTimes);

        List<Float> lectureTimes = new ArrayList<>();
        lectureTimes.add(2.5f);
        lectureTimes.add(2.0f);

        lectureTimeSource.setLectureTimes(lectureTimes);

        List<String> lectureCode = lectureCodeUtils.getLectureCode(lectureTimeSource);

        for (String code : lectureCode){
            log.info("code : {} " , code);
        }

    }

}