package FXPROJECT.CHECKPASS.domain.entity.lectures;
import FXPROJECT.CHECKPASS.domain.entity.users.Students;
import jakarta.persistence.*;

@Entity
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
