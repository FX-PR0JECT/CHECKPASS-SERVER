package FXPROJECT.CHECKPASS.domain.entity.attendance;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Attendance {

    @Id
    private String AttendanceId;

    private short AttendanceCode;

}
