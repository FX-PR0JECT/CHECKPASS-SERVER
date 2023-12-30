package FXPROJECT.CHECKPASS.domain.entity.lectures;

import FXPROJECT.CHECKPASS.domain.entity.users.Professor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import org.hibernate.validator.constraints.Range;

/**
 * CHECKPASS-19
 * Enrollment System DB 구축
 */

@Entity
public class Lecture {

    @Id
    @Column(nullable = false)
    private Long lectureCode;

    @ManyToOne
    private Professor professor;

    @Column(nullable = false, length = 20)
    private String lectureName;

    @Column(nullable = false)
    private String lectureTimes;

    @Column(nullable = false, length = 20)
    private String lectureRoom;

    @Range(min = 1, max = 5)
    @Column(nullable = false)
    private int lectureGrade;

    @Column(nullable = false, length = 2)
    private String lectureKind;

    @Range(min = 1, max = 12)
    @Column(nullable = false, length = 1)
    private int lectureGrades;

    @Column(nullable = false, length = 3)
    private int lectureFull;

    @Column(nullable = false, length = 3)
    private int lectureCount;

    @Column(nullable = false, length = 1)
    private char dayOrNight;
}
