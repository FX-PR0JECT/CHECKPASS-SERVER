package FXPROJECT.CHECKPASS.domain.repository.attendance;

import FXPROJECT.CHECKPASS.domain.entity.attendance.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAttendanceRepository extends JpaRepository<Attendance, String> {

}
