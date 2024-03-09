package FXPROJECT.CHECKPASS.domain.entity.beacon;

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

    @Column(nullable = false)
    private int major;

    @Column(nullable = false)
    private int minor;
}
