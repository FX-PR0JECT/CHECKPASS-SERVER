package FXPROJECT.CHECKPASS.web.form.responseForm.resultForm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LectureInformation {

    private Long lectureCode;

    private String lectureName;

    private String lectureGrade;

    private String lectureKind;

    private String lectureGrades;

    private String professorName;

    private String lectureRoom;

    private String lectureTimes;

    private Integer lectureFull;

    private Integer lectureCount;

    private String dayOrNight;

    private String departments;

}
