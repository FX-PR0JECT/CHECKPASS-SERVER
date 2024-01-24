package FXPROJECT.CHECKPASS.domain.repository;

import FXPROJECT.CHECKPASS.domain.entity.college.Colleges;
import FXPROJECT.CHECKPASS.domain.entity.college.Departments;
import FXPROJECT.CHECKPASS.domain.entity.lectures.Enrollment;
import FXPROJECT.CHECKPASS.domain.entity.lectures.Lecture;
import FXPROJECT.CHECKPASS.domain.entity.users.Account;
import FXPROJECT.CHECKPASS.domain.entity.users.Professor;
import FXPROJECT.CHECKPASS.domain.entity.users.Students;
import FXPROJECT.CHECKPASS.domain.enums.CollegesEnum;
import FXPROJECT.CHECKPASS.domain.enums.DepartmentsEnum;
import FXPROJECT.CHECKPASS.domain.enums.Job;
import FXPROJECT.CHECKPASS.domain.repository.college.JpaCollegesRepository;
import FXPROJECT.CHECKPASS.domain.repository.college.JpaDepartmentRepository;
import FXPROJECT.CHECKPASS.domain.repository.lectures.JpaEnrollmentRepository;
import FXPROJECT.CHECKPASS.domain.repository.lectures.JpaLectureRepository;
import FXPROJECT.CHECKPASS.domain.repository.users.JpaAccountRepository;
import FXPROJECT.CHECKPASS.domain.repository.users.JpaUsersRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class JpaEnrollmentRepositoryTest {

    @Autowired
    private JpaEnrollmentRepository jpaEnrollmentRepository;

    @Autowired
    private JpaUsersRepository jpaUsersRepository;

    @Autowired
    private JpaLectureRepository jpaLectureRepository;

    @Test
    @DisplayName("EnrollmentTest")
    public void test() {

        Lecture lecture = jpaLectureRepository.findByLectureCode(121212L);
        log.info("lectureCode : {}", lecture.getLectureCode());

        Students student = (Students) jpaUsersRepository.findByUserId(200000L);
        log.info("StudentId : {}", student.getUserId());

        Enrollment enrollment = new Enrollment(student, lecture);

        Enrollment enroll = jpaEnrollmentRepository.save(enrollment);
        lecture.setLectureCount(lecture.getLectureCount()+1);

        log.info("enrollmentId : {}, studentId : {}, lectureCode : {}", enroll.getEnrollmentId(), enroll.getStudent().getUserId(), enroll.getLecture().getLectureCode());
        log.info("lectureCount : {}", lecture.getLectureCount());

        Enrollment findByEnrollmentId = jpaEnrollmentRepository.findByEnrollmentId(200000121212L);
        log.info("LectureName : {}, studentId : {}", findByEnrollmentId.getLecture().getLectureName(), findByEnrollmentId.getStudent().getUserId());

        jpaEnrollmentRepository.deleteById(findByEnrollmentId.getEnrollmentId());

        Boolean isExists = jpaEnrollmentRepository.existsById(findByEnrollmentId.getEnrollmentId());
        assertThat(isExists).isFalse();

    }

    @TestConfiguration
    static class testData {

        @Autowired
        private JpaAccountRepository accountRepository;

        @Autowired
        private JpaCollegesRepository jpaCollegesRepository;

        @Autowired
        private JpaDepartmentRepository jpaDepartmentRepository;

        @Autowired
        private JpaUsersRepository jpaUsersRepository;

        @Autowired
        private JpaLectureRepository jpaLectureRepository;

        @PostConstruct
        private void testInitData() {

            CollegesEnum[] collegesEnums = CollegesEnum.values();

            for (CollegesEnum college : collegesEnums) {
                log.info("college : {}", college.getCollege());
                Colleges colleges = new Colleges();
                colleges.setCollege(college.getCollege());
                jpaCollegesRepository.save(colleges);
            }

            DepartmentsEnum[] departmentsEnums = DepartmentsEnum.values();

            for (DepartmentsEnum department : departmentsEnums) {
                log.info("department : {}", department.getDepartment());
                Colleges colleges = new Colleges();
                Optional<Colleges> findCollege = jpaCollegesRepository.findById(department.getCollegeCode());
                log.info("findCollege : {}", findCollege.get().getCollege());
                Departments departments = new Departments();
                departments.setDepartment(department.getDepartment());
                departments.setColleges(findCollege.get());
                jpaDepartmentRepository.save(departments);
            }

            Optional<Departments> byDepartment = jpaDepartmentRepository.findByDepartment(DepartmentsEnum.ComputerSoftware.getDepartment());

            if (byDepartment.isEmpty()){
                log.info("error department");
            }

            for (int i=0; i < 3; i++){
                long id = 100000L;
                Account accountA = new Account();
                accountA.setPassword("1234");

                accountRepository.save(accountA);

                Professor professor = new Professor().builder()
                        .account(accountA)
                        .userId(id+i)
                        .userName("shin")
                        .userJob(Job.PROFESSOR)
                        .departments(byDepartment.get())
                        .HIREDATE(String.valueOf(LocalDate.now()))
                        .build();

                jpaUsersRepository.save(professor);
            }

            for(int i=0; i < 3; i++){
                long id = 200000L;
                Account accountB = new Account();
                accountB.setPassword("1234");

                accountRepository.save(accountB);

                Students students = new Students().builder()
                        .account(accountB)
                        .userId(id+i)
                        .userName("신영운")
                        .userJob(Job.STUDENTS)
                        .departments(byDepartment.get())
                        .studentGrade("4학년")
                        .dayOrNight("day")
                        .studentSemester("1학기")
                        .build();
                jpaUsersRepository.save(students);
            }

            Professor professorA = (Professor) jpaUsersRepository.findByUserId(100000L);
            Professor professorB = (Professor) jpaUsersRepository.findByUserId(100001L);
            Professor professorC = (Professor) jpaUsersRepository.findByUserId(100002L);

            Lecture lecture = new Lecture().builder()
                    .lectureCode(121212L)
                    .professor(professorA)
                    .lectureName("캡스톤 디자인")
                    .lectureTimes("(화 3A, 3B, 4A)")
                    .lectureRoom("미래융합정보관 (225)")
                    .lectureGrade("3학년")
                    .lectureKind("전필")
                    .lectureGrades("3학점")
                    .lectureFull(40)
                    .dayOrNight("day")
                    .departments(byDepartment.get())
                    .build();

            jpaLectureRepository.save(lecture);

            Lecture lectureB = new Lecture().builder()
                    .lectureCode(121213L)
                    .professor(professorB)
                    .lectureName("딥러닝")
                    .lectureTimes("(화 3A, 3B, 4A),(목 4A, 4B, 5A)")
                    .lectureRoom("미래융합정보관 (219)")
                    .lectureGrade("4학년")
                    .lectureKind("전선")
                    .lectureGrades("3학점")
                    .lectureFull(40)
                    .dayOrNight("day")
                    .departments(byDepartment.get())
                    .build();

            jpaLectureRepository.save(lectureB);

            Lecture lectureC = new Lecture().builder()
                    .lectureCode(121214L)
                    .professor(professorC)
                    .lectureName("컴퓨터보안")
                    .lectureTimes("(목 4A, 4B, 5A)")
                    .lectureRoom("미래융합정보관 (225)")
                    .lectureGrade("4학년")
                    .lectureKind("전선")
                    .lectureGrades("3학점")
                    .lectureFull(40)
                    .dayOrNight("day")
                    .departments(byDepartment.get())
                    .build();

            jpaLectureRepository.save(lectureC);
        }
    }
}