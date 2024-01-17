package FXPROJECT.CHECKPASS.domain.repository.college;

import FXPROJECT.CHECKPASS.domain.entity.college.Departments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JpaDepartmentRepository extends JpaRepository<Departments,Long> {

    Optional<Departments> findByDepartment(String department);

    @Query(value = "select d from Departments d where d.colleges.collegeId = :collegeId")
    Optional<Departments> findDepartmentsByColleges(@Param("collegeId") Long collegeId);

}
