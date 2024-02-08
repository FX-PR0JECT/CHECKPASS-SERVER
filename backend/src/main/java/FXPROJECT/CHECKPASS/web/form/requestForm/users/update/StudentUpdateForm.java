package FXPROJECT.CHECKPASS.web.form.requestForm.users.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
    public class StudentUpdateForm extends UpdateForm {

    private String updateStudentGrade;

    private String updateDayOrNight;

    // 학기 자동 ++ , state [휴학] -> 고려 해볼것
    private String updateStudentSemester;

}
