package FXPROJECT.CHECKPASS.domain.entity.users;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("Professor")
public class Professor extends Users{

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate HIREDATE;

}
