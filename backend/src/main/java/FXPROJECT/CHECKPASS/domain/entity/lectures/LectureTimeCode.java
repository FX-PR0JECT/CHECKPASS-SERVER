package FXPROJECT.CHECKPASS.domain.entity.lectures;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Entity
@Getter
public class LectureTimeCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureTimeCodeId;

    private String lectureTimeCode;

}
