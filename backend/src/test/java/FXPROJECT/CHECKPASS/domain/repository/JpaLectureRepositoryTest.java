package FXPROJECT.CHECKPASS.domain.repository;

import FXPROJECT.CHECKPASS.domain.entity.lectures.Lecture;
import FXPROJECT.CHECKPASS.domain.entity.users.Account;
import FXPROJECT.CHECKPASS.domain.entity.users.Professor;
import FXPROJECT.CHECKPASS.domain.enums.Job;
import FXPROJECT.CHECKPASS.domain.enums.LectureKind;
import FXPROJECT.CHECKPASS.domain.repository.lectures.JpaLectureRepository;
import FXPROJECT.CHECKPASS.domain.repository.users.JpaAccountRepository;
import FXPROJECT.CHECKPASS.domain.repository.users.JpaUsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;


@Slf4j
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class JpaLectureRepositoryTest {

    @Autowired
    private JpaLectureRepository jpaLectureRepository;
    @Autowired
    private JpaUsersRepository usersRepository;
    @Autowired
    private JpaAccountRepository accountRepository;


    @Test
    @Rollback(false)
    public void CRUD(){

        Account account = new Account();
        account.setPassword("1234");

        accountRepository.save(account);

        Professor professor = new Professor().builder()
                .account(account)
                .userId(2126037L)
                .userCollege("soft")
                .userName("shin")
                .userJob(Job.PROFESSOR)
                .userDepartment("software")
                .HIREDATE(String.valueOf(LocalDate.now()))
                .build();

        Professor savedProfessor = usersRepository.save(professor);

        Lecture lecture = new Lecture().builder()
                .lectureCode(121212L)
                .professor(savedProfessor)
                .lectureName("checkpass")
                .lectureTimes("(화 3A, 3B, 4A),(목 4A, 4B, 5A)")
                .lectureRoom("미래융합정보관 (225)")
                .lectureGrade("3학년")
                .lectureKind(LectureKind.MANDATORY)
                .lectureGrades("3학점")
                .lectureFull(40)
                .dayOrNight("day")
                .build();

        Lecture registeredLecture = jpaLectureRepository.save(lecture);

        log.info("professorId : {}", registeredLecture.getProfessor().getUserId());
        assertThat(registeredLecture.getLectureCode()).isEqualTo(lecture.getLectureCode());


        // findByLectureCode
        Lecture byLectureCode = jpaLectureRepository.findByLectureCode(registeredLecture.getLectureCode());
        log.info("byLectureCode : {} , name : {} , dayOrNight : {} , Professor : {} " ,
                byLectureCode.getLectureCode(),byLectureCode.getLectureName(),
                byLectureCode.getDayOrNight(), byLectureCode.getProfessor().getUserName());
        assertThat(byLectureCode).isNotNull();
        assertThat(byLectureCode.getLectureCode()).isEqualTo(registeredLecture.getLectureCode());

        // ExistsByLectureCode
        Boolean bool = jpaLectureRepository.existsByLectureCode(byLectureCode.getLectureCode());

        assertThat(bool).isTrue();

        // delete
        jpaLectureRepository.deleteLectureByLectureCode(byLectureCode.getLectureCode());

        bool = jpaLectureRepository.existsByLectureCode(byLectureCode.getLectureCode());
        assertThat(bool).isFalse();

        Lecture lectureA = new Lecture().builder()
                .lectureCode(121212L)
                .professor(savedProfessor)
                .lectureName("checkpass")
                .lectureTimes("(화 3A, 3B, 4A),(목 4A, 4B, 5A)")
                .lectureRoom("미래융합정보관 (225)")
                .lectureGrade("1학년")
                .lectureKind(LectureKind.MANDATORY)
                .lectureGrades("3학점")
                .lectureFull(40)
                .dayOrNight("day")
                .build();

        Lecture lectureB = new Lecture().builder()
                .lectureCode(121212L)
                .professor(savedProfessor)
                .lectureName("checkpass")
                .lectureTimes("(화 3A, 3B, 4A),(목 4A, 4B, 5A)")
                .lectureRoom("미래융합정보관 (225)")
                .lectureGrade("2학년")
                .lectureKind(LectureKind.MANDATORY)
                .lectureGrades("3학점")
                .lectureFull(40)
                .dayOrNight("day")
                .build();

        jpaLectureRepository.save(lectureA);
        jpaLectureRepository.save(lectureB);

    }
}