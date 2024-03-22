package FXPROJECT.CHECKPASS.domain.common.converter;

import FXPROJECT.CHECKPASS.domain.entity.beacon.BeaconPK;
import FXPROJECT.CHECKPASS.domain.entity.lectures.Lecture;
import FXPROJECT.CHECKPASS.domain.dto.LectureTimeCode;
import FXPROJECT.CHECKPASS.web.common.utils.ToLectureWordUtils;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.SimpleLectureInformation;
import FXPROJECT.CHECKPASS.web.service.beacon.BeaconService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LectureToLectureSimpleInfoConverter implements Converter<Lecture, SimpleLectureInformation> {

    @Override
    public SimpleLectureInformation convert(Lecture lecture) {

        SimpleLectureInformation simpleInfo = new SimpleLectureInformation().builder()
                .lectureCode(lecture.getLectureCode())
                .lectureName(lecture.getLectureName())
                .division(lecture.getDivision())
                .build();

        return simpleInfo;
    }
}
