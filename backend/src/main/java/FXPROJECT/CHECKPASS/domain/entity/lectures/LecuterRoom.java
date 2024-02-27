package FXPROJECT.CHECKPASS.domain.entity.lectures;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@Setter
@DynamicInsert
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LecuterRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long beaconId;

    private String major;

    private String minor;

    private String LectureRoom;


}
