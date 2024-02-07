package FXPROJECT.CHECKPASS.domain.entity.users;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Setter
@Getter
@SuperBuilder
@DiscriminatorValue("STUDENTS")
@NoArgsConstructor
public class Students extends Users{

    private String studentGrade;

    @Column(length = 5)
    private String dayOrNight;

    @Column(length = 3)
    private String studentSemester;

}
