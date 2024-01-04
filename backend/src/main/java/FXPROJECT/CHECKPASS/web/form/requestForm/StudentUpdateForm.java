package FXPROJECT.CHECKPASS.web.form.requestForm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class StudentUpdateForm extends UpdateForm{

    private String studentGrade;

    private String dayOrNight;

    private String studentSemester;


}
