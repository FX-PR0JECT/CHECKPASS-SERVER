package FXPROJECT.CHECKPASS.domain.entity.attendance;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StartSemesterDays {

    @Id
    private Integer semester;

    private String startSemesterDay;

}
