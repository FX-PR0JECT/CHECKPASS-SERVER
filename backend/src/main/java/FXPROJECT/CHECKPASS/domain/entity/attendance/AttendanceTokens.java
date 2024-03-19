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
    private int attendanceCode;

    @OneToOne(cascade = CascadeType.ALL)
    private Lecture lecture;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime expirationDate;

}
