package FXPROJECT.CHECKPASS.domain.repository.college;


import FXPROJECT.CHECKPASS.domain.entity.college.Colleges;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCollegesRepository extends JpaRepository<Colleges,Long> {
    
}
