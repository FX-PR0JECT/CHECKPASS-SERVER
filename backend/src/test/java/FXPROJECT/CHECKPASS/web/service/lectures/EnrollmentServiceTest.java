/*
package FXPROJECT.CHECKPASS.web.service.lectures;

import FXPROJECT.CHECKPASS.domain.entity.college.Colleges;
import FXPROJECT.CHECKPASS.domain.entity.college.Departments;
import FXPROJECT.CHECKPASS.domain.entity.lectures.Enrollment;
import FXPROJECT.CHECKPASS.domain.entity.lectures.Lecture;
import FXPROJECT.CHECKPASS.domain.entity.users.Account;
import FXPROJECT.CHECKPASS.domain.entity.users.Professor;
import FXPROJECT.CHECKPASS.domain.entity.users.Students;
import FXPROJECT.CHECKPASS.domain.entity.users.Users;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
class EnrollmentServiceTest {

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private JpaEnrollmentRepository jpaEnrollmentRepository;

    @Autowired
    private JpaUsersRepository jpaUsersRepository;

    static private final List<Long> studentIdList = new ArrayList<>();

    @Test
    @DisplayName("EnrollmentTest")
    public void enrollmentTest() throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(200);
        CountDownLatch countDownLatch = new CountDownLatch(200);

        List<Users> Students = jpaUsersRepository.findAllById(studentIdList);


        for (Users student : Students){
            executorService.submit(() -> {
                try {
                    enrollmentService.enrollment(121212L, student);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();

        List<Enrollment> enrollmentList = jpaEnrollmentRepository.findAll();
        assertThat(enrollmentList.size()).isEqualTo(40);
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

            Account accountA = new Account();
            accountA.setPassword("1234");

            accountRepository.save(accountA);

            Professor professor = new Professor().builder()
                    .account(accountA)
                    .userId(100000L)
                    .userName("shin")
                    .userJob(Job.PROFESSOR)
                    .departments(byDepartment.get())
                    .HIREDATE(String.valueOf(LocalDate.now()))
                    .build();

            jpaUsersRepository.save(professor);

            for(int i=0; i < 200; i++){
                long id = 200000L;
                Account accountB = new Account();
                accountB.setPassword("1234");

                accountRepository.save(accountB);

                studentIdList.add(id + i);

                Students student = new Students().builder()
                        .account(accountB)
                        .userId(id+i)
                        .userName("신영운")
                        .userJob(Job.STUDENTS)
                        .departments(byDepartment.get())
                        .studentGrade("4학년")
                        .dayOrNight("day")
                        .studentSemester("1학기")
                        .build();
                jpaUsersRepository.save(student);
            }

            Professor professorA = (Professor) jpaUsersRepository.findByUserId(100000L);

            Lecture lecture = new Lecture().builder()
                    .lectureCode(121212L)
                    .professor(professorA)
                    .lectureName("캡스톤 디자인")
                    //.lecturetimeCode("(화 3A, 3B, 4A)")
                    .lectureRoom("미래융합정보관 (225)")
                    .lectureGrade("3학년")
                    .lectureKind("전필")
                    .lectureGrades("3학점")
                    .lectureFull(40)
                    .dayOrNight("day")
                    .departments(byDepartment.get())
                    .build();

            jpaLectureRepository.save(lecture);
        }
    }
}*/
