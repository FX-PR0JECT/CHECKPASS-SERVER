package FXPROJECT.CHECKPASS.web.form.requestForm.lectures.register;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LectureTimeSource {

    private List<String> lectureDays;

    private List<String> lectureStartTime;

    private List<Float> lectureTimes;
}
