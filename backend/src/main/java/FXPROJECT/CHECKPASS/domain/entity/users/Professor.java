package FXPROJECT.CHECKPASS.domain.entity.users;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@Setter
@Getter
@DiscriminatorValue("PROFESSOR")
@NoArgsConstructor
public class Professor extends Users{

    private String hireDate;

}
