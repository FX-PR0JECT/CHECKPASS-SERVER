package FXPROJECT.CHECKPASS.web.form.responseForm.resultForm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
@AllArgsConstructor
public class AttendanceStatisticsInformation {

    private String lectureName;

    private Long lectureCode;

    private String professorName;

    private String division;

    private Map<Integer, Long> attendanceCounts;
}
