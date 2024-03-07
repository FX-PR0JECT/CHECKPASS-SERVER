package FXPROJECT.CHECKPASS.domain.repository.beacon;

import FXPROJECT.CHECKPASS.domain.entity.beacon.Beacon;
import FXPROJECT.CHECKPASS.domain.entity.beacon.BeaconPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBeaconRepository extends JpaRepository<Beacon, BeaconPK> {

    Beacon findByBeaconPK(BeaconPK beaconPK);
}
