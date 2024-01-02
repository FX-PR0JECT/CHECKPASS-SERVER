package FXPROJECT.CHECKPASS.domain.entity.users;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.hibernate.validator.constraints.Range;

@Entity
@DiscriminatorValue("Student")
public class Students extends Users{

    @Range(min = 1, max = 5)
    private int studentGrade;

    @Column(length = 1)
    private char dayOrNigth;

    @Column(length = 3)
    private String studentSemester;

}
