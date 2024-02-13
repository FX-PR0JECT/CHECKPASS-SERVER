package FXPROJECT.CHECKPASS.domain.entity.lectures;

import FXPROJECT.CHECKPASS.domain.entity.users.Students;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class Enrollment {

        @Id
        private Long enrollmentId;

        @ManyToOne
        @JoinColumn(name = "student_id")
        private Students student;

        @JoinColumn(name = "lecture_code")
        @ManyToOne
        private Lecture lecture;

        private String yearSemester;

        public Enrollment(Students student, Lecture lecture) {
                this.student = student;
                this.lecture = lecture;
                this.enrollmentId = Long.valueOf(student.getUserId().toString() + lecture.getLectureCode().toString());
                this.yearSemester = LocalDate.now().getYear() + "년도 " + student.getStudentSemester();
        }
}