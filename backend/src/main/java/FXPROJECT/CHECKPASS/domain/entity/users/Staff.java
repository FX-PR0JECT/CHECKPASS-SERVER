package FXPROJECT.CHECKPASS.domain.entity.users;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@DiscriminatorValue("STAFF")
public class Staff extends Users {

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate HIREDATE;

}
