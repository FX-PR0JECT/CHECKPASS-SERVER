package FXPROJECT.CHECKPASS.domain.repository.attendance;

import FXPROJECT.CHECKPASS.domain.entity.attendance.AttendanceTokens;
import FXPROJECT.CHECKPASS.domain.entity.lectures.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAttendanceTokenRepository extends JpaRepository<AttendanceTokens, Integer> {

    AttendanceTokens findByLecture(Lecture lecture);

    boolean existsByLecture(Lecture lecture);

    void deleteByLecture(Lecture lecture);
}
