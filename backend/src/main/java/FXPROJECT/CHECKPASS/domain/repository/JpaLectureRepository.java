package FXPROJECT.CHECKPASS.domain.repository;

import FXPROJECT.CHECKPASS.domain.entity.lectures.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaLectureRepository extends JpaRepository<Lecture,Long> {

}
