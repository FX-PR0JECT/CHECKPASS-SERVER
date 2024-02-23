package FXPROJECT.CHECKPASS.web.common.searchCondition.lectures;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LectureSearchCondition {

    private List<String> grade;
    private List<String> kind;
    private List<String> grades;
    private Long lectureCode;
    private String lectureName;
    private String professorName;

}
