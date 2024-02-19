package FXPROJECT.CHECKPASS.domain.common.converter;

import FXPROJECT.CHECKPASS.domain.entity.lectures.Lecture;
import FXPROJECT.CHECKPASS.domain.dto.LectureTimeCode;
import FXPROJECT.CHECKPASS.web.common.utils.ToLectureWordUtils;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.LectureInformation;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LectureToLectureInformationConverter implements Converter<Lecture, LectureInformation> {

    @Override
    public LectureInformation convert(Lecture lecture) {

        List<String> time = new ArrayList<>();
        for (LectureTimeCode code : lecture.getLectureTimeCode()){
            time.add(code.getLectureTimeCode());
        }

        LectureInformation lectureInformation = new LectureInformation().builder()
                .lectureCode(lecture.getLectureCode())
                .lectureName(lecture.getLectureName())
                .lectureGrade(lecture.getLectureGrade())
                .lectureKind(lecture.getLectureKind())
                .lectureGrades(lecture.getLectureGrades())
                .professorName(lecture.getProfessor().getUserName())
                .lectureRoom(lecture.getLectureRoom())
                .lectureFull(lecture.getLectureFull())
                .lectureCount(lecture.getLectureCount())
                .dayOrNight(lecture.getDayOrNight())
                .departments(lecture.getDepartments().getDepartment())
                .lectureTimes(time)
                .alphaTimeCodes(ToLectureWordUtils.TransferLectureWord(lecture.getLectureTimeCode()))
                .scheduleArray(ToLectureWordUtils.getScheduleArray(lecture.getLectureTimeCode()))
                .division(lecture.getDivision())
                .yearSemester(lecture.getYearSemester())
                .build();

        return lectureInformation;

    }
}
