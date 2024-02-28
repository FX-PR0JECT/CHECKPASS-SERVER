package FXPROJECT.CHECKPASS.domain.entity.lectures;


import jakarta.persistence.Entity;
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
public class LectureRoom {

    @Id
    private String lectureRoom;

    private String major;

    private String minor;


}
