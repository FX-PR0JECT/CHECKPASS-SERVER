package FXPROJECT.CHECKPASS.web.form.requestForm.letures.update;

import FXPROJECT.CHECKPASS.domain.entity.users.Professor;
import FXPROJECT.CHECKPASS.domain.enums.LectureKind;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LectureUpdateForm {

    private Long lectureCode;

    private Professor professor;

    private String lectureName;

    private String lectureTimes;

    private String lectureRoom;

    private String lectureGrade;

    private LectureKind lectureKind;

    private String lectureGrades;

    private Integer lectureFull;

    private String dayOrNight;
}
