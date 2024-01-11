package FXPROJECT.CHECKPASS.web.service.users;

import FXPROJECT.CHECKPASS.domain.entity.users.Account;
import FXPROJECT.CHECKPASS.domain.entity.users.Professor;
import FXPROJECT.CHECKPASS.domain.entity.users.Users;
import FXPROJECT.CHECKPASS.domain.enums.Job;
import FXPROJECT.CHECKPASS.domain.repository.users.JpaAccountRepository;
import FXPROJECT.CHECKPASS.web.form.requestForm.users.signup.ProfessorUpdateForm;
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
                .userCollege("soft")
                .userName("shin")
                .userJob(Job.PROFESSOR)
                .userDepartment("software")
                .HIREDATE(String.valueOf(LocalDate.now()))
                .build();

        userService.join(professor);

        ProfessorUpdateForm updateParam = new ProfessorUpdateForm().builder()
                .updatePassword("test")
                .updateCollege("software")
                .updateName("shinywoon")
                .updateDepartment("software")
                .updateHireDate(String.valueOf(LocalDate.now()))
                .build();

        Users byUserId = userService.getUser(professor.getUserId());

        userService.editProfessorInformation(byUserId.getUserId(),updateParam);

    }

    @Test
    public void count(){

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

        userService.join(professor);

    }

}