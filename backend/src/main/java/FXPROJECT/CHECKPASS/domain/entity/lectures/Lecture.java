package FXPROJECT.CHECKPASS.domain.entity.lectures;

import FXPROJECT.CHECKPASS.domain.entity.college.Departments;
import FXPROJECT.CHECKPASS.domain.entity.users.Professor;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

/**
 * CHECKPASS-19
 * Enrollment System DB 구축
 */

@Entity
@Getter @Setter
@DynamicInsert
@Builder
@AllArgsConstructor @NoArgsConstructor
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

    @Column(nullable = false)
    private String lectureGrade;

    @Column(nullable = false)
    private String lectureKind;

    @Column(nullable = false, length = 3)
    private String lectureGrades;

    @Column(nullable = false)
    private int lectureFull;

    @ColumnDefault("0")
    private Integer lectureCount;

    @Column(nullable = false, length = 5)
    private String dayOrNight;

    @ManyToOne
    @Enumerated(EnumType.STRING)
    private Departments departments;

}