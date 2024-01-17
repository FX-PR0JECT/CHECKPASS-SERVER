package FXPROJECT.CHECKPASS.domain.repository.college;


import FXPROJECT.CHECKPASS.domain.entity.college.Colleges;
import FXPROJECT.CHECKPASS.domain.enums.CollegesEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaCollegesRepository extends JpaRepository<Colleges,Long> {

    @Query(value = "select c.collegeId from Colleges c where c.college = :college")
    Long findCollegeByCollegeName(@Param("college") String college);
}
