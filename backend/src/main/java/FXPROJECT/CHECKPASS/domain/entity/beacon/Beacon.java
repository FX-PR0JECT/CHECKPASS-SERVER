package FXPROJECT.CHECKPASS.domain.entity.beacon;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Beacon {

    @EmbeddedId
    private BeaconPK beaconPK;
}
