package FXPROJECT.CHECKPASS.web.form.responseForm.resultForm;

import FXPROJECT.CHECKPASS.domain.enums.Job;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorInformation {

    private Long userId;

    private String userDepartment;

    private String userCollege;

    private String userName;

    private Job userJob;

    private String hireDate;

}
