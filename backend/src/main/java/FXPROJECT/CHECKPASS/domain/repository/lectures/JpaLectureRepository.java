package FXPROJECT.CHECKPASS.domain.repository.lectures;

import FXPROJECT.CHECKPASS.domain.entity.lectures.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaLectureRepository extends JpaRepository<Lecture,Long> {

    Lecture findByLectureCode(Long lectureCode);

    Boolean existsByLectureCode(Long lectureCode);

    void deleteLectureByLectureCode(Long lectureCode);

}
