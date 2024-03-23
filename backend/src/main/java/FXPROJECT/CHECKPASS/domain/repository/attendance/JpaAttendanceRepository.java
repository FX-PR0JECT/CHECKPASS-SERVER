package FXPROJECT.CHECKPASS.domain.repository.attendance;

import FXPROJECT.CHECKPASS.domain.entity.attendance.Attendance;
import FXPROJECT.CHECKPASS.domain.entity.attendance.AttendanceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaAttendanceRepository extends JpaRepository<Attendance, String> {
    @Query(value = "SELECT CASE WHEN EXISTS (SELECT a FROM Attendance a WHERE a.attendanceStatus IN (1, 2) AND a.attendanceId = :attendanceId) THEN true ELSE false END FROM Attendance a")
    boolean existsByAttendanceStatus(@Param("attendanceId") AttendanceId attendanceId);

    void deleteByAttendanceId(AttendanceId attendanceId);
}