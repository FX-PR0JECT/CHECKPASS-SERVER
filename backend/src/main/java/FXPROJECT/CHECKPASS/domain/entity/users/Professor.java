package FXPROJECT.CHECKPASS.domain.entity.users;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@SuperBuilder
@Setter
@Getter
@DiscriminatorValue("PROFESSOR")
public class Professor extends Users{

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate HIREDATE;

    public Professor() {

    }
}
