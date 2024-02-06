package FXPROJECT.CHECKPASS.web.common.utils;

import FXPROJECT.CHECKPASS.domain.entity.lectures.LectureTimeCode;
import FXPROJECT.CHECKPASS.domain.enums.DaysEnum;
import FXPROJECT.CHECKPASS.web.form.requestForm.lectures.register.LectureTimeSource;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class LectureCodeUtils {

    public List<LectureTimeCode> getLectureCode(LectureTimeSource lectureTimeSource){

        List<String> lectureDayCodeList = getLectureDaysCode(lectureTimeSource.getLectureDays());

        List<String> lectureStartTimeCodeList = getLectureTimeCodeList(lectureTimeSource.getLectureStartTime());

        List<String> lectureTimesCodeList = getLectureTimesCodeList(lectureTimeSource.getLectureTimes());

        List<LectureTimeCode> timeCodeList = new ArrayList<>();

        LectureTimeCode lectureTimeCode = new LectureTimeCode();

        for (int i = 0 ; i < lectureDayCodeList.size(); i++){

            String timeCode = lectureDayCodeList.get(i) + lectureStartTimeCodeList.get(i) + lectureTimesCodeList.get(i);

            lectureTimeCode.setLectureTimeCode(timeCode);

            timeCodeList.add(lectureTimeCode);
        }

        return timeCodeList;

    }

    private List<String> getLectureTimesCodeList(List<Float> lectureTimes) {

        List<String> lectureTimeCodeList = new ArrayList<>();

        for (Float time : lectureTimes){

            lectureTimeCodeList.add(transferTimeCode(time));

        }

        return lectureTimeCodeList;

    }

    private String transferTimeCode(Float time) {

        return "H" + (int)(time * 60);

    }

    private List<String> getLectureTimeCodeList(List<String> lectureStartTime) {

        List<String> lectureStartTimeCodeList = new ArrayList<>();

        for (String startTime : lectureStartTime){

            lectureStartTimeCodeList.add(transferStartTimeCode(startTime));

        }

        return lectureStartTimeCodeList;

    }

    private String transferStartTimeCode(String startTime) {

        String[] splitArray = startTime.split(":");

        String resultword = "";

        for (String splitword: splitArray){
            resultword += splitword;
        }

        return "T" + resultword;

    }

    private List<String> getLectureDaysCode(List<String> lectureDays) {

        List<String> lectureDayCodeList = new ArrayList<>();

        for (String lectureDay : lectureDays){

            lectureDayCodeList.add(transferDayCode(lectureDay));

        }

        return lectureDayCodeList;

    }

    private String transferDayCode(String lectureDay) {
        return "D" + DaysEnum.valueOf(lectureDay).ordinal();
    }

}
