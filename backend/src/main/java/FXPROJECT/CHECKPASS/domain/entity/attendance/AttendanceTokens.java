package FXPROJECT.CHECKPASS.domain.entity.attendance;

import FXPROJECT.CHECKPASS.domain.entity.lectures.Lecture;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceTokens {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attendanceTokenId;

    @OneToOne
    private Lecture lecture;

    @Column
    private Integer attendanceCode;

    @Column
    private LocalDateTime startDate;

    public AttendanceTokens(Lecture lecture) {
        this.lecture = lecture;
    }

}
