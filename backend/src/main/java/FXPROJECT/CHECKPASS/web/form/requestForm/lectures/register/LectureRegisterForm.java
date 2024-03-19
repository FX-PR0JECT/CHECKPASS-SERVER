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

    private int major;

    private int minor;

    private int lectureGrade;

    private LectureKind lectureKind;

    private int lectureGrades;

    private Integer lectureFull;

    private String dayOrNight;

    private DepartmentsEnum departments;

    private List<String> lectureDays;

    private List<String> lectureStartTime;

    private List<Float> lectureTimes;

    private String division;

}