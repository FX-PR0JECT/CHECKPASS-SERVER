package FXPROJECT.CHECKPASS.domain.entity.attendance;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Attendance {

    @EmbeddedId
    private AttendanceId attendanceId;

    private int attendanceStatus;

}
