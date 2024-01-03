package FXPROJECT.CHECKPASS.web.service.users;

import FXPROJECT.CHECKPASS.domain.entity.users.Account;
import FXPROJECT.CHECKPASS.domain.entity.users.Professor;
import FXPROJECT.CHECKPASS.domain.entity.users.Users;
import FXPROJECT.CHECKPASS.domain.enums.Job;
import FXPROJECT.CHECKPASS.domain.repository.JpaAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@Slf4j
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private JpaAccountRepository accountRepository;

    @Test
    void updateUsers() {

        Account account = new Account();
        account.setPassword("1234");

        accountRepository.save(account);

        Professor professor = new Professor().builder()
                .account(account)
                .userId(2126037L)
                .userAge(30)
                .userCollege("soft")
                .userName("shin")
                .userJob(Job.PROFESSOR)
                .userDepartment("software")
                .HIREDATE(LocalDate.now())
                .build();

        userService.join(professor);

        Professor updateParam = new Professor().builder()
                .account(account)
                .userId(2126037L)
                .userAge(20)
                .userCollege("software")
                .userName("shinywoon")
                .userJob(Job.PROFESSOR)
                .userDepartment("software")
                .HIREDATE(LocalDate.now())
                .build();

        Users byUserId = userService.getUser(professor.getUserId());

        userService.editUserInfo(byUserId.getUserId(),updateParam);

    }

    @Test
    public void count(){

        Account account = new Account();
        account.setPassword("1234");

        accountRepository.save(account);

        Professor professor = new Professor().builder()
                .account(account)
                .userId(2126037L)
                .userAge(30)
                .userCollege("soft")
                .userName("shin")
                .userJob(Job.PROFESSOR)
                .userDepartment("software")
                .HIREDATE(LocalDate.now())
                .build();

        userService.join(professor);

    }

}