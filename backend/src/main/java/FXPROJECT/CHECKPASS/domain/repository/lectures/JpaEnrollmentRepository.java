package FXPROJECT.CHECKPASS.domain.repository.lectures;

import FXPROJECT.CHECKPASS.domain.entity.lectures.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaEnrollmentRepository extends JpaRepository<Enrollment,Long> {

    Enrollment findByEnrollmentId(Long enrollmentId);

}
