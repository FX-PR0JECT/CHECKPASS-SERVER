package FXPROJECT.CHECKPASS.web.form.requestForm.lectures.update;

import FXPROJECT.CHECKPASS.domain.entity.users.Professor;
import FXPROJECT.CHECKPASS.domain.enums.LectureKind;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LectureUpdateForm {

    private Long professorId;

    private String lectureName;

    private String lectureTimes;

    private String lectureRoom;

    private String lectureGrade;

    private LectureKind lectureKind;

    private String lectureGrades;

    private Integer lectureFull;

    private String dayOrNight;
}