package FXPROJECT.CHECKPASS.domain.entity.attendance;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attendance;

    @ColumnDefault("false")
    private Boolean attend;

    @ColumnDefault("false")
    private Boolean canceled;

    private String makeupClass;

    private LocalDateTime attendTime;

}
