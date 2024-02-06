package FXPROJECT.CHECKPASS.web.form.requestForm.lectures.register;

import FXPROJECT.CHECKPASS.domain.enums.DepartmentsEnum;
import FXPROJECT.CHECKPASS.domain.enums.LectureKind;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LectureRegisterForm {

    private Long lectureCode;

    private Long professorId;

    private String lectureName;

    private String lectureRoom;

    private String lectureGrade;

    private LectureKind lectureKind;

    private String lectureGrades;

    private Integer lectureFull;

    private Integer lectureCount;

    private String dayOrNight;

    private DepartmentsEnum departments;

    private List<Integer> lectureTimes;

    private List<String> lectureDays;

    private List<String> lectureStartTime;

}