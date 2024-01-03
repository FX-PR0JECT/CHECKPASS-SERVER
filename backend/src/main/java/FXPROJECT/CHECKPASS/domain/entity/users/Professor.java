package FXPROJECT.CHECKPASS.domain.entity.users;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@Setter
@Getter
@DiscriminatorValue("PROFESSOR")
public class Professor extends Users{

    private String HIREDATE;

    public Professor() {

    }
}
