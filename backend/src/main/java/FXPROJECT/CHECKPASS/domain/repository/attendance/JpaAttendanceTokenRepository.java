package FXPROJECT.CHECKPASS.domain.repository.attendance;

import FXPROJECT.CHECKPASS.domain.entity.attendance.AttendanceTokens;
import FXPROJECT.CHECKPASS.domain.entity.lectures.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAttendanceTokenRepository extends JpaRepository<AttendanceTokens, Integer> {

    AttendanceTokens findByAttendanceCode(int attendanceCode);

    AttendanceTokens findByLecture(Lecture lecture);

    boolean existsByAttendanceCode(int attendanceCode);

    boolean existsByLecture(Lecture lecture);
}
