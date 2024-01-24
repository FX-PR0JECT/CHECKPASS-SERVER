package FXPROJECT.CHECKPASS.domain.repository.lectures;

import FXPROJECT.CHECKPASS.domain.entity.lectures.Lecture;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaLectureRepository extends JpaRepository<Lecture,Long> {

    Lecture findByLectureCode(Long lectureCode);

    Boolean existsByLectureCode(Long lectureCode);

    void deleteLectureByLectureCode(Long lectureCode);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select lec from Lecture lec where lec.lectureCode = :lecture_code")
    Lecture findLectureByLectureCode(@Param("lecture_code") Long lectureCode);

}
