package FXPROJECT.CHECKPASS.domain.entity.lectures;

import FXPROJECT.CHECKPASS.domain.entity.users.Students;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Enrollment {

        @Id
        private Long enrollmentId;

        @ManyToOne
        @JoinColumn(name = "student_id")
        Students student;

        @JoinColumn(name = "lecture_code")
        @ManyToOne
        Lecture lecture;

        public Enrollment(Students student, Lecture lecture) {
                this.student = student;
                this.lecture = lecture;
                this.enrollmentId = Long.valueOf(student.getUserId().toString() + lecture.getLectureCode().toString());
        }

}
