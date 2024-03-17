package FXPROJECT.CHECKPASS.domain.repository.attendance;

import FXPROJECT.CHECKPASS.domain.entity.attendance.AttendanceTokens;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAttendanceTokenRepository extends JpaRepository<AttendanceTokens, Integer> {

    AttendanceTokens findByAttendanceCode(int attendanceCode);

    boolean existsByAttendanceCode(int attendanceCode);
}
