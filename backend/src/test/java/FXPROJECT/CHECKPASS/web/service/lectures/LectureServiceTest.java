package FXPROJECT.CHECKPASS.web.service.lectures;

import FXPROJECT.CHECKPASS.domain.entity.lectures.Lecture;
import FXPROJECT.CHECKPASS.domain.entity.users.Account;
import FXPROJECT.CHECKPASS.domain.entity.users.Professor;
import FXPROJECT.CHECKPASS.domain.enums.Job;
import FXPROJECT.CHECKPASS.domain.repository.lectures.JpaLectureRepository;
import FXPROJECT.CHECKPASS.domain.repository.users.JpaAccountRepository;
import FXPROJECT.CHECKPASS.domain.repository.QueryRepository;
import FXPROJECT.CHECKPASS.web.common.searchCondition.lectures.LectureSearchCondition;
import FXPROJECT.CHECKPASS.web.service.users.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class LectureServiceTest {

    @Autowired
    private QueryRepository ur;

    @Autowired
    private UserService userService;

    @Autowired
    private JpaAccountRepository ar;

    @Autowired
    private JpaLectureRepository lr;

    @Test
    public void test(){

        Account account = new Account();
        account.setPassword("1234");
        ar.save(account);

        Professor professor = new Professor().builder()
                .account(account)
                .userJob(Job.PROFESSOR)
                .userName("test")
                .userId(2126000L)
                .userDepartment("software")
                .userCollege("tech")
                .HIREDATE("1993-12-12")
                .build();

        userService.join(professor);

        Lecture lecture = new Lecture().builder()
                .lectureCode(121212L)
                .professor(professor)
                .lectureName("checkpass")
                .lectureTimes("(화 3A, 3B, 4A),(목 4A, 4B, 5A)")
                .lectureRoom("미래융합정보관 (225)")
                .lectureGrade(3)
                .lectureKind("전필")
                .lectureGrades(3)
                .lectureFull(40)
                .dayOrNight("day")
                .build();

        Lecture lectureA = new Lecture().builder()
                .lectureCode(121213L)
                .professor(professor)
                .lectureName("checkpass")
                .lectureTimes("(화 3A, 3B, 4A),(목 4A, 4B, 5A)")
                .lectureRoom("미래융합정보관 (225)")
                .lectureGrade(1)
                .lectureKind("전필")
                .lectureGrades(3)
                .lectureFull(40)
                .dayOrNight("day")
                .build();

        Lecture lectureB = new Lecture().builder()
                .lectureCode(121214L)
                .professor(professor)
                .lectureName("checkpass")
                .lectureTimes("(화 3A, 3B, 4A),(목 4A, 4B, 5A)")
                .lectureRoom("미래융합정보관 (225)")
                .lectureGrade(2)
                .lectureKind("전필")
                .lectureGrades(3)
                .lectureFull(40)
                .dayOrNight("day")
                .build();

        lr.save(lectureA);
        lr.save(lectureB);
        lr.save(lecture);

        LectureSearchCondition con = new LectureSearchCondition();
        con.setGrade(3);
        List<Lecture> lectureList = ur.getLectureList(con);

        log.info("test : {}" , lectureList.size());

        for (Lecture l : lectureList) {
            log.info("lecture code : {} " , l.getLectureCode());
        }

    }

}
