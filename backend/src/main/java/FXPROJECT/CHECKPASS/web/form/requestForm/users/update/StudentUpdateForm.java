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

    private String updateStudentSemester;


}
