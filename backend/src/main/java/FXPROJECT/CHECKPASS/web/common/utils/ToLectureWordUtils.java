package FXPROJECT.CHECKPASS.web.common.utils;

import FXPROJECT.CHECKPASS.domain.dto.LectureTimeCode;
import FXPROJECT.CHECKPASS.domain.enums.DaysEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ToLectureWordUtils {

    public static List<String> TransferLectureWord(List<LectureTimeCode> lectureTimeCodes) {

        /*
        TO 코드를 분해해서 각각의 정보를 얻는다.
        TO 각 정보를 활용해 alpha 수업 표시법으로 변환한다
         */

        LectureCodesToken lectureCodesToken = transferLectureTimeCodeTolectureCodesToken(lectureTimeCodes);

        List<String> timeWords = changeStartTimeToStartTimeWord(lectureCodesToken);

        List<LectureTimeWords> lectureTimeWords = generateTimeWords(lectureCodesToken,timeWords);

        List<String> dayWords = translationKoreanDays(lectureCodesToken);

        List<String> alphaTimeCodes = makeFinalCodes(lectureTimeWords, dayWords);

        return alphaTimeCodes;

    }

    private static List<String> makeFinalCodes(List<LectureTimeWords> lectureTimeWords, List<String> dayWords) {
        List<String> finalTimeCode = new ArrayList<>();
        for (int i = 0; i < lectureTimeWords.size() ; i = i+2){ // 0 , 1
            finalTimeCode.add(dayWords.get(i / 2) + " " + lectureTimeWords.get(i).getFinalWorld());
            finalTimeCode.add(dayWords.get(i / 2) + " " + lectureTimeWords.get(i+1).getFinalWorld());
            if (i > 3){
                break;
            }
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

    private static List<LectureTimeWords> generateTimeWords(LectureCodesToken lectureCodesToken,List<String> timeWords) {

        List<String> times = lectureCodesToken.getTimes();

        List<LectureTimeWords> lectureTimeWordsList = new ArrayList<>();

        for (String time : times){

            int iterCount = Integer.parseInt(time) / 30;

            for(String timeword: timeWords){

                LectureTimeWords lectureTimeWords = new LectureTimeWords();

                List<String> wordList = new ArrayList<>();

                int substring = Integer.parseInt(timeword.substring(0, 1));
                String substring1 = timeword.substring(1);

                for (int j = 0; j < iterCount; j++){

                    if (substring1.equals("A")){
                        substring1 = "B";
                    } else if (substring1.equals("B")) {
                        substring1 = "A";
                        substring += 1;
                    }

                    wordList.add(substring + substring1);
                }
                lectureTimeWords.setFinalWorld(wordList);
                lectureTimeWordsList.add(lectureTimeWords);
            }
        }
        return lectureTimeWordsList;
    }

    private static List<String> changeStartTimeToStartTimeWord(LectureCodesToken lectureCodesToken) {

        List<String> startTimes = lectureCodesToken.getStartTimes();

        List<String> timeWords = new ArrayList<>();

        for(String startTime : startTimes){

            String subString = startTime.substring(0,2);
            String subString2 = startTime.substring(2);

            int hour = Integer.parseInt(subString) - 8;

            String alpha = "";

            if (subString2.equals("00")){
                alpha = "A";
            }else if (subString2.equals("30")){
                alpha = "B";
            }

            String timeWord = hour + alpha;

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
        lectureTimeCodeB.setLectureTimeCode("D1T1000H150");

        lectureTimeCodes.add(lectureTimeCodeB);

        List<String> strings = TransferLectureWord(lectureTimeCodes);

        for (String code:strings){
            log.info("code : {} " , code);
        }

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

        List<String> finalWorld;

    }

}
