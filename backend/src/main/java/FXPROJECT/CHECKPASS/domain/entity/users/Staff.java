package FXPROJECT.CHECKPASS.domain.entity.users;


import jakarta.persistence.Entity;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
public class Staff extends Users {

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate HIREDATE;

}
