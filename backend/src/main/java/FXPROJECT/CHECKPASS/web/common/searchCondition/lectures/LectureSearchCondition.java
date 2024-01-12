package FXPROJECT.CHECKPASS.web.common.searchCondition.lectures;

import FXPROJECT.CHECKPASS.domain.enums.LectureKind;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LectureSearchCondition {

    private String grade;
    private LectureKind kind;
    private String grades;
    private String dayOrNight;
    private Long lectureCode;
    private String lectureName;

}
