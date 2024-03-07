package FXPROJECT.CHECKPASS.web.common.searchCondition.lectures;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LectureSearchCondition {

    private List<Integer> grade;
    private List<String> kind;
    private List<Integer> grades;
    private Long lectureCode;
    private String lectureName;
    private String professorName;

}
