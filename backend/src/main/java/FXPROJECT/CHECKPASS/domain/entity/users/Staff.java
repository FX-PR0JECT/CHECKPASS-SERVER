package FXPROJECT.CHECKPASS.domain.entity.users;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Setter
@Getter
@SuperBuilder
@DiscriminatorValue("STAFF")
public class Staff extends Users {

    private String HIREDATE;

    public Staff() {

    }
}
