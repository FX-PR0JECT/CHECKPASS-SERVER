package FXPROJECT.CHECKPASS.domain.entity.attendance;

import FXPROJECT.CHECKPASS.domain.entity.lectures.Lecture;
import FXPROJECT.CHECKPASS.domain.entity.users.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class AttendanceId {

    @Id
    @OneToOne
    @JoinColumn(name = "attendanceId")
    private Attendance attendance;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "lectureCode")
    private Lecture lecture;

    @Range(min = 1 , max = 20)
    private Long week;

}
