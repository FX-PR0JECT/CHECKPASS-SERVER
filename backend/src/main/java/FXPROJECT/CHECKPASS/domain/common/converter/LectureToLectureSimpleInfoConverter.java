package FXPROJECT.CHECKPASS.domain.common.converter;

import FXPROJECT.CHECKPASS.domain.entity.lectures.Lecture;
import FXPROJECT.CHECKPASS.domain.dto.LectureTimeCode;
import FXPROJECT.CHECKPASS.web.common.utils.ToLectureWordUtils;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.SimpleLectureInformation;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LectureToLectureSimpleInfoConverter implements Converter<Lecture, SimpleLectureInformation> {

    @Override
    public SimpleLectureInformation convert(Lecture lecture) {

        List<String> time = new ArrayList<>();
        for (LectureTimeCode code : lecture.getLectureTimeCode()){
            time.add(code.getLectureTimeCode());
        }

        SimpleLectureInformation simpleInfo = new SimpleLectureInformation().builder()
                .lectureName(lecture.getLectureName())
                .professorName(lecture.getProfessor().getUserName())
                .lectureRoom(lecture.getLectureRoom())
                .lectureTimes(time)
                .alphaTimeCodes(ToLectureWordUtils.TransferLectureWord(lecture.getLectureTimeCode()))
                .build();

        return simpleInfo;
    }
}
