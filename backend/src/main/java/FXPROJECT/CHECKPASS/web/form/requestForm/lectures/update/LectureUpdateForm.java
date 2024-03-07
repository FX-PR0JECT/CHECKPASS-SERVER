package FXPROJECT.CHECKPASS.web.form.requestForm.lectures.update;

import FXPROJECT.CHECKPASS.domain.enums.LectureKind;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LectureUpdateForm {

    private Long professorId;

    private String lectureName;

    private int major;

    private int minor;

    private int lectureGrade;

    private LectureKind lectureKind;

    private int lectureGrades;

    private Integer lectureFull;

    private String dayOrNight;

    private List<String> lectureDays;

    private List<String> lectureStartTime;

    private List<Float> lectureTimes;

}