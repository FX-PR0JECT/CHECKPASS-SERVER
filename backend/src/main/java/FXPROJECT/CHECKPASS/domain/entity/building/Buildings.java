package FXPROJECT.CHECKPASS.domain.entity.building;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Buildings {

    @Id
    private int buildingCode;

    @Column(nullable = false)
    private String buildingName;
}
