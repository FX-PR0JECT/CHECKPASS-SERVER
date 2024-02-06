package FXPROJECT.CHECKPASS.web.form.requestForm.lectures.register;

import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LectureTimeSource {

    private List<String> lectureDays;

    private List<String> lectureStartTime;

    private List<Float> lectureTimes;
}
