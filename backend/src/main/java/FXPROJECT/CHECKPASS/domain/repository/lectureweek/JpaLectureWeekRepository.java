package FXPROJECT.CHECKPASS.domain.repository.lectureweek;

import FXPROJECT.CHECKPASS.domain.entity.attendance.StartSemesterDays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface JpaLectureWeekRepository extends JpaRepository<StartSemesterDays,Integer> {

    @Query("select s from StartSemesterDays s where s.semester = :semester")
    StartSemesterDays findBySemester(@Param("semester") Integer semester);

}
