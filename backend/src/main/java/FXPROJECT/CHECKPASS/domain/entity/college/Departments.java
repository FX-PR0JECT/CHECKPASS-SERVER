package FXPROJECT.CHECKPASS.domain.entity.college;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Departments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;

    private String department;

    @ManyToOne
    @JoinColumn(name = "collegeId")
    private Colleges colleges;

}
