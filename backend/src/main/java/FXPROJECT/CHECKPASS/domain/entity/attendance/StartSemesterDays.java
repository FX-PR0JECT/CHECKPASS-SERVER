package FXPROJECT.CHECKPASS.domain.entity.attendance;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class StartSemesterDays {

    @Id
    private Integer semester;

    private String startSemesterDay;

}
