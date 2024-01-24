package FXPROJECT.CHECKPASS.domain.entity.lectures;
import FXPROJECT.CHECKPASS.domain.entity.users.Students;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@AllArgsConstructor @NoArgsConstructor
public class Enrollment {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long enrollmentId;

        @ManyToOne
        @JoinColumn(name = "student_id")
        Students student;

        @JoinColumn(name = "lecture_code")
        @ManyToOne
        Lecture lecture;

}
