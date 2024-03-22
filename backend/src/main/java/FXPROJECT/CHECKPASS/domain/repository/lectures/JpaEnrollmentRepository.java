package FXPROJECT.CHECKPASS.domain.repository.lectures;

import FXPROJECT.CHECKPASS.domain.entity.lectures.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaEnrollmentRepository extends JpaRepository<Enrollment,Long> {

    @Query(value = "SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM Enrollment e WHERE e.lecture.lectureCode = :lectureCode")
    boolean existsByLectureCode(@Param("lectureCode") Long lectureCode);

}
