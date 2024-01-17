package FXPROJECT.CHECKPASS.web.service.lectures;

import FXPROJECT.CHECKPASS.domain.entity.college.Colleges;
import FXPROJECT.CHECKPASS.domain.entity.college.Departments;
import FXPROJECT.CHECKPASS.domain.entity.lectures.Lecture;
import FXPROJECT.CHECKPASS.domain.entity.users.Account;
import FXPROJECT.CHECKPASS.domain.entity.users.Professor;
import FXPROJECT.CHECKPASS.domain.enums.CollegesEnum;
import FXPROJECT.CHECKPASS.domain.enums.DepartmentsEnum;
import FXPROJECT.CHECKPASS.domain.enums.Job;
import FXPROJECT.CHECKPASS.domain.enums.LectureKind;
import FXPROJECT.CHECKPASS.domain.repository.college.JpaCollegesRepository;
import FXPROJECT.CHECKPASS.domain.repository.college.JpaDepartmentRepository;
import FXPROJECT.CHECKPASS.domain.repository.lectures.JpaLectureRepository;
import FXPROJECT.CHECKPASS.domain.repository.users.JpaAccountRepository;
import FXPROJECT.CHECKPASS.domain.repository.QueryRepository;
import FXPROJECT.CHECKPASS.web.common.searchCondition.lectures.LectureSearchCondition;
import FXPROJECT.CHECKPASS.web.service.users.UserService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;

import java.util.List;
import java.util.Optional;

@Slf4j
@SpringBootTest
public class LectureServiceTest {

    @Autowired
    private QueryRepository queryRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private LectureService lectureService;

    @Autowired
    private JpaAccountRepository jpaAccountRepository;

    @Autowired
    private JpaDepartmentRepository jpaDepartmentRepository;
    @Autowired
    private JpaCollegesRepository jpaCollegesRepository;

    @Test
    public void test(){

        Account account = new Account();
        account.setPassword("1234");
        jpaAccountRepository.save(account);

        Optional<Departments> byDepartment = jpaDepartmentRepository.findByDepartment(DepartmentsEnum.ComputerSoftware.getDepartment());

        if (byDepartment.isEmpty()){
            log.info("error department");
        }

        Professor professor = new Professor().builder()
                .account(account)
                .userJob(Job.PROFESSOR)
                .userName("test")
                .userId(2126000L)
                .departments(byDepartment.get())
                .HIREDATE("1993-12-12")
                .build();

        userService.join(professor);

        Lecture lecture_1 = new Lecture().builder()
                .lectureCode(121212L)
                .professor(professor)
                .lectureName("checkpass")
                .lectureTimes("(화 3A, 3B, 4A),(목 4A, 4B, 5A)")
                .lectureRoom("미래융합정보관 (225)")
                .lectureGrade("3학년")
                .lectureKind(LectureKind.MANDATORY)
                .lectureGrades("3학점")
                .lectureFull(40)
                .dayOrNight("day")
                .build();

        Lecture lecture_2 = new Lecture().builder()
                .lectureCode(121213L)
                .professor(professor)
                .lectureName("checkpass")
                .lectureTimes("(화 3A, 3B, 4A),(목 4A, 4B, 5A)")
                .lectureRoom("미래융합정보관 (225)")
                .lectureGrade("1학년")
                .lectureKind(LectureKind.MANDATORY)
                .lectureGrades("3학점")
                .lectureFull(40)
                .dayOrNight("day")
                .build();

        Lecture lecture_3 = new Lecture().builder()
                .lectureCode(121214L)
                .professor(professor)
                .lectureName("checkpass")
                .lectureTimes("(화 3A, 3B, 4A),(목 4A, 4B, 5A)")
                .lectureRoom("미래융합정보관 (225)")
                .lectureGrade("2학년")
                .lectureKind(LectureKind.MANDATORY)
                .lectureGrades("3학점")
                .lectureFull(40)
                .dayOrNight("day")
                .build();

        lectureService.registerLecture(lecture_1);
        lectureService.registerLecture(lecture_2);
        lectureService.registerLecture(lecture_3);


        LectureSearchCondition con = new LectureSearchCondition();
        con.setGrade("3학년");
        List<Lecture> lectureList = queryRepository.getLectureList(con);

        log.info("size : {}" , lectureList.size());

        for (Lecture lec : lectureList) {
            log.info("lecture code : {} " , lec.getLectureCode());
        }


    }

}
