package FXPROJECT.CHECKPASS.domain.common.converter;

import FXPROJECT.CHECKPASS.domain.entity.beacon.BeaconPK;
import FXPROJECT.CHECKPASS.domain.entity.lectures.Lecture;
import FXPROJECT.CHECKPASS.domain.dto.LectureTimeCode;
import FXPROJECT.CHECKPASS.web.common.utils.ToLectureWordUtils;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.LectureInformation;
import FXPROJECT.CHECKPASS.web.service.beacon.BeaconService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LectureToLectureInformationConverter implements Converter<Lecture, LectureInformation> {

    private final BeaconService beaconService;
    @Override
    public LectureInformation convert(Lecture lecture) {

        List<String> time = new ArrayList<>();
        for (LectureTimeCode code : lecture.getLectureTimeCode()){
            time.add(code.getLectureTimeCode());
        }

        BeaconPK beaconPK = lecture.getBeacon().getBeaconPK();
        String lectureRoom = beaconService.getLectureRoom(beaconPK);
        Long lectureCode = lecture.getLectureCode();
        String lectureYear = lectureCode.toString().substring(0, 4) + "년도 ";

        LectureInformation lectureInformation = new LectureInformation().builder()
                .lectureCode(lectureCode)
                .lectureName(lecture.getLectureName())
                .lectureGrade(lecture.getLectureGrade())
                .lectureKind(lecture.getLectureKind())
                .lectureGrades(lecture.getLectureGrades())
                .professorName(lecture.getProfessor().getUserName())
                .lectureRoom(lectureRoom)
                .lectureFull(lecture.getLectureFull())
                .lectureCount(lecture.getLectureCount())
                .dayOrNight(lecture.getDayOrNight())
                .departments(lecture.getDepartments().getDepartment())
                .lectureTimes(time)
                .alphaTimeCodes(ToLectureWordUtils.TransferLectureWord(lecture.getLectureTimeCode()))
                .scheduleArray(ToLectureWordUtils.getScheduleArray(lecture.getLectureTimeCode()))
                .division(lecture.getDivision())
                .yearSemester(lectureYear + lecture.getSemester())
                .build();

        return lectureInformation;

    }
}
