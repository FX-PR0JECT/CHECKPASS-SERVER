package FXPROJECT.CHECKPASS.domain.entity.beacon;

import FXPROJECT.CHECKPASS.domain.entity.building.Buildings;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class BeaconPK implements Serializable {

    @ManyToOne(cascade = CascadeType.ALL)
    private Buildings buildings;

    @Column(nullable = false)
    private int minor;
}
