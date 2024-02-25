package FXPROJECT.CHECKPASS.web.common.utils;

import FXPROJECT.CHECKPASS.domain.dto.LectureTimeCode;
import FXPROJECT.CHECKPASS.domain.dto.ScheduleArray;
import FXPROJECT.CHECKPASS.domain.enums.DaysEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class ToLectureWordUtils {

    public static LectureCodesToken getLectureCodesToken(List<LectureTimeCode> lectureTimeCodes){
        return transferLectureTimeCodeTolectureCodesToken(lectureTimeCodes);
    }

    public static ScheduleArray getScheduleArray(List<LectureTimeCode> lectureTimeCodes){

        LectureCodesToken lectureCodesToken = getLectureCodesToken(lectureTimeCodes);

        List<String> dayWords = translationKoreanDays(lectureCodesToken);

        List<String> startTimes = lectureCodesToken.getStartTimes();

        List<Integer> startIndexes = new ArrayList<>();

        for(String startTime : startTimes){

            int subHour = Integer.parseInt(startTime.substring(0, 2));

            int startIndex = (subHour - 9) * 2;

            String subMin = startTime.substring(2);

            if (!subMin.equals("00")){
                startIndex += 1;
            }
            startIndexes.add(startIndex);
        }

        ScheduleArray scheduleArray = new ScheduleArray();

        for(int i = 0; i < lectureCodesToken.times.size(); i++){

            boolean[] schedule = new boolean[18];
            Arrays.fill(schedule, false);

            int iterCount = Integer.parseInt(lectureCodesToken.times.get(i)) / 30;

            log.info("iterCount : {}" ,iterCount);

            for(int j = 0; j < iterCount; j++){
                schedule[(startIndexes.get(i) + j)] = true;
            }

            scheduleArray.getScheduleArray().put(dayWords.get(i),schedule);
        }

        for (boolean[] arry : scheduleArray.getScheduleArray().values()){
            log.info("test : {} ",arry);
        }

        return scheduleArray;
    }

    public static List<String> TransferLectureWord(List<LectureTimeCode> lectureTimeCodes) {

        /*
        TO 코드를 분해해서 각각의 정보를 얻는다.
        TO 각 정보를 활용해 alpha 수업 표시법으로 변환한다
         */

        LectureCodesToken lectureCodesToken = getLectureCodesToken(lectureTimeCodes);

        List<String> startTimeWords = changeStartTimeToStartTimeWord(lectureCodesToken);

        List<LectureTimeWords> lectureTimeWords = generateTimeWords(lectureCodesToken,startTimeWords);

        List<String> dayWords = translationKoreanDays(lectureCodesToken);

        List<String> alphaTimeCodes = makeFinalCodes(lectureTimeWords, dayWords);

        return alphaTimeCodes;

    }

    private static List<String> makeFinalCodes(List<LectureTimeWords> lectureTimeWords, List<String> dayWords) {
        List<String> finalTimeCode = new ArrayList<>();
        for (int i = 0; i < lectureTimeWords.size(); i++){
            finalTimeCode.add(dayWords.get(i) + " " + lectureTimeWords.get(i).getFinalWord());
        }
        return finalTimeCode;
    }

    private static LectureCodesToken transferLectureTimeCodeTolectureCodesToken(List<LectureTimeCode> lectureTimeCodes) {

        List<String> lectureCodes = new ArrayList<>();

        for (LectureTimeCode lectureTimeCode: lectureTimeCodes){
                lectureCodes.add(lectureTimeCode.getLectureTimeCode());
        }

        LectureCodesToken lectureCodesToken = subStringLectureCodes(lectureCodes);

        return lectureCodesToken;
    }

    private static List<String> translationKoreanDays(LectureCodesToken lectureCodesToken) {

        List<String> days = lectureCodesToken.getDays();

        List<String> dayWord = new ArrayList<>();

        for(String day : days){
            int dayOrdinal = Integer.parseInt(day);
            DaysEnum[] values = DaysEnum.values();
            dayWord.add(values[dayOrdinal].getDay());
        }
        return dayWord;
    }

    private static List<LectureTimeWords> generateTimeWords(LectureCodesToken lectureCodesToken, List<String> startTimeWords) {

        List<String> times = lectureCodesToken.getTimes();

        List<LectureTimeWords> lectureTimeWordsList = new ArrayList<>();

        for (int i = 0; i < times.size(); i++){

            String time = times.get(i);
            int iterCount = Integer.parseInt(time) / 30;

            String startTimeWord = startTimeWords.get(i);

            List<String> wordList = new ArrayList<>();
            wordList.add(startTimeWord);

            LectureTimeWords lectureTimeWords = new LectureTimeWords();

            int hourCode = Integer.parseInt(startTimeWord.substring(0, 1));
            String minuteCode = startTimeWord.substring(1);

            for (int j = 0; j < iterCount - 1; j++){

                if (minuteCode.equals("A")){
                    minuteCode = "B";
                } else if (minuteCode.equals("B")) {
                    minuteCode = "A";
                    hourCode += 1;
                }

                wordList.add(hourCode + minuteCode);
            }
            lectureTimeWords.setFinalWord(wordList);
            lectureTimeWordsList.add(lectureTimeWords);
        }
        return lectureTimeWordsList;
    }

    private static List<String> changeStartTimeToStartTimeWord(LectureCodesToken lectureCodesToken) {

        List<String> startTimes = lectureCodesToken.getStartTimes();

        List<String> timeWords = new ArrayList<>();

        for(String startTime : startTimes){

            String hour = startTime.substring(0,2);
            String minute = startTime.substring(2);

            int hourCode = Integer.parseInt(hour) - 8;

            String alpha = "";

            if (minute.equals("00")){
                alpha = "A";
            }else if (minute.equals("30")){
                alpha = "B";
            }

            String timeWord = hourCode + alpha;
            timeWords.add(timeWord);
        }

        return timeWords;
    }

    private static LectureCodesToken subStringLectureCodes(List<String> lectureCodes) {

        LectureCodesToken lectureCodesToken = new LectureCodesToken();

        List<String> days = new ArrayList<>();

        List<String> startTimes = new ArrayList<>();

        List<String> times = new ArrayList<>();

        for (String code : lectureCodes){

            int startTimeIndex = code.indexOf("T") + 1;

            int timesIndex = code.indexOf("H") + 1;


            days.add(code.substring(1,startTimeIndex-1));
            startTimes.add(code.substring(startTimeIndex,7));
            times.add(code.substring(timesIndex));

        }
        lectureCodesToken.setDays(days);
        lectureCodesToken.setStartTimes(startTimes);
        lectureCodesToken.setTimes(times);

        return lectureCodesToken;
    }

    public static void main(String[] args) {

        List<LectureTimeCode> lectureTimeCodes = new ArrayList<>();

        LectureTimeCode lectureTimeCodeA = new LectureTimeCode();
        lectureTimeCodeA.setLectureTimeCode("D0T0900H120");

        lectureTimeCodes.add(lectureTimeCodeA);

        LectureTimeCode lectureTimeCodeB = new LectureTimeCode();
        lectureTimeCodeB.setLectureTimeCode("D1T1030H150");

        lectureTimeCodes.add(lectureTimeCodeB);

        List<String> strings = TransferLectureWord(lectureTimeCodes);

        for (String code:strings){
            log.info("code : {} " , code);
        }

        getScheduleArray(lectureTimeCodes);

    }

    @Getter
    @Setter
    static class LectureCodesToken {

        List<String> days;

        List<String> startTimes;

        List<String> times;

    }


    @Getter
    @Setter
    static class LectureTimeWords {

        List<String> finalWord;

    }

}
