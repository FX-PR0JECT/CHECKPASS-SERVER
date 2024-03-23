package FXPROJECT.CHECKPASS.domain.entity.attendance;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class AttendanceId implements Serializable {

    @Column(nullable = false)
    private Long studentId;

    @Column(nullable = false)
    private Long lectureCode;

    @Column(nullable = false)
    private String studentGrade;

    @Column(nullable = false)
    private String studentSemester;

    @Column(nullable = false)
    private String lectureDay;

    @Column(nullable = false)
    private int week;
}
