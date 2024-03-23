/*
package FXPROJECT.CHECKPASS.web.service.users;

import FXPROJECT.CHECKPASS.domain.entity.college.Departments;
import FXPROJECT.CHECKPASS.domain.entity.users.Account;
import FXPROJECT.CHECKPASS.domain.entity.users.Professor;
import FXPROJECT.CHECKPASS.domain.entity.users.Users;
import FXPROJECT.CHECKPASS.domain.enums.DepartmentsEnum;
import FXPROJECT.CHECKPASS.domain.enums.Job;
import FXPROJECT.CHECKPASS.domain.repository.college.JpaCollegesRepository;
import FXPROJECT.CHECKPASS.domain.repository.college.JpaDepartmentRepository;
import FXPROJECT.CHECKPASS.domain.repository.users.JpaAccountRepository;
import FXPROJECT.CHECKPASS.web.form.requestForm.users.update.ProfessorUpdateForm;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private JpaAccountRepository accountRepository;
    @Autowired
    private JpaDepartmentRepository jpaDepartmentRepository;
    @Autowired
    private JpaCollegesRepository jpaCollegesRepository;

    @Test
    void updateUsers() {

        Account account = new Account();
        account.setPassword("1234");

        accountRepository.save(account);

        Optional<Departments> byDepartment = jpaDepartmentRepository.findByDepartment(DepartmentsEnum.ComputerSoftware.getDepartment());

        if (byDepartment.isEmpty()){
            log.info("error department");
        }

        Professor professor = new Professor().builder()
                .account(account)
                .userId(2126037L)
                .userName("shin")
                .userJob(Job.PROFESSOR)
                .departments(byDepartment.get())
                .HIREDATE(String.valueOf(LocalDate.now()))
                .build();

        userService.join(professor);

        Optional<Departments> updateDeparment = jpaDepartmentRepository.findByDepartment("항공기계설계학과");

        if (updateDeparment.isEmpty()){
            log.info("error department");
        }

        ProfessorUpdateForm updateParam = new ProfessorUpdateForm().builder()
                .updatePassword("test")
                .updateName("shinywoon")
                .updateDepartment(updateDeparment.get().getDepartment())
                .updateHireDate(String.valueOf(LocalDate.now()))
                .build();

        Users byUserId = userService.getUser(professor.getUserId());

        Users users = userService.editProfessorInformation(byUserId.getUserId(), updateParam);

        log.info("users : {} , {}", users.getDepartments().getDepartment() , users.getDepartments().getColleges());

        userService.secessionUser(professor.getUserId());

    }

    @Test
    public void count(){

        Account account = new Account();
        account.setPassword("1234");

        accountRepository.save(account);

        Optional<Departments> byDepartment = jpaDepartmentRepository.findByDepartment(DepartmentsEnum.ComputerSoftware.getDepartment());

        if (byDepartment.isEmpty()){
            log.info("error department");
        }

        Professor professor = new Professor().builder()
                .account(account)
                .userId(2126037L)
                .userName("shin")
                .userJob(Job.PROFESSOR)
                .departments(byDepartment.get())
                .HIREDATE(String.valueOf(LocalDate.now()))
                .build();

        userService.join(professor);

        userService.secessionUser(professor.getUserId());

    }

}
*/
