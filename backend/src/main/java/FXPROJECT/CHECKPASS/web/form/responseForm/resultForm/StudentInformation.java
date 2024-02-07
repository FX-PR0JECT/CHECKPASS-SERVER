package FXPROJECT.CHECKPASS.web.form.responseForm.resultForm;

import FXPROJECT.CHECKPASS.domain.enums.Job;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentInformation {

    private Long userId;

    private String userDepartment;

    private String userCollege;

    private String userName;

    private Job userJob;

    private String studentGrade;

    private String dayOrNight;

    private String studentSemester;

}
