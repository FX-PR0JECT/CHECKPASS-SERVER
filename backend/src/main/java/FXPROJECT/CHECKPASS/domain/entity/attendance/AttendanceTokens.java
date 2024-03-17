package FXPROJECT.CHECKPASS.domain.entity.attendance;

import FXPROJECT.CHECKPASS.domain.entity.lectures.Lecture;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AttendanceTokens {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tokenId;

    @OneToOne(cascade = CascadeType.ALL)
    private Lecture lecture;

    @Column(nullable = false)
    private int attendanceCode;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime expirationDate;

    public AttendanceTokens(Lecture lecture, int attendanceCode, LocalDateTime startDate, LocalDateTime expirationDate) {
        this.lecture = lecture;
        this.attendanceCode = attendanceCode;
        this.startDate = startDate;
        this.expirationDate = expirationDate;
    }

}
