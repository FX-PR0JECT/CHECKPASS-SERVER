package FXPROJECT.CHECKPASS.domain.repository.college;

import FXPROJECT.CHECKPASS.domain.entity.college.Departments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaDepartmentRepository extends JpaRepository<Departments,Long> {
    Optional<Departments> findByDepartment(String department);
}
