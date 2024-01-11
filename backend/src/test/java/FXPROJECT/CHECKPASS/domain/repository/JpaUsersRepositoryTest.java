package FXPROJECT.CHECKPASS.domain.repository;

import FXPROJECT.CHECKPASS.domain.entity.users.Account;
import FXPROJECT.CHECKPASS.domain.entity.users.Professor;;
import FXPROJECT.CHECKPASS.domain.entity.users.Users;
import FXPROJECT.CHECKPASS.domain.enums.Job;
import FXPROJECT.CHECKPASS.domain.repository.users.JpaAccountRepository;
import FXPROJECT.CHECKPASS.domain.repository.users.JpaUsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class JpaUsersRepositoryTest {

    @Autowired
    private JpaUsersRepository usersRepository;

    @Autowired
    private JpaAccountRepository accountRepository;

    @Test
    @DisplayName("jpaTest")
    @Rollback(false)
    public void crud(){

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

        log.info("job : {}",professor.getUserJob());
        assertThat(savedProfessor.getUserId()).isEqualTo(2126037L);

        //getUser
        Optional<Users> byId = usersRepository.findById(professor.getUserId());

        Users target = null;

        if (!byId.isEmpty()){
            target = byId.get();
        }

        Professor pTarget = null;

        if (target != null){
            if (target.getUserJob().equals(Job.PROFESSOR)){
                pTarget = (Professor) target;
            }
        }

        log.info("target Id : {} , target HireDate : {} " , pTarget.getUserId() , pTarget.getHIREDATE());
        assertThat(pTarget.getUserId()).isEqualTo(professor.getUserId());

        //update
        pTarget.setHIREDATE("1994-12-23");

        //findAll
        Account accountA = new Account();
        accountA.setPassword("test");

        accountRepository.save(accountA);

        Professor professorA = new Professor().builder()
                .account(accountA)
                .userId(2126000L)
                .userCollege("structure")
                .userName("Lee")
                .userJob(Job.PROFESSOR)
                .userDepartment("software")
                .HIREDATE(String.valueOf(LocalDate.now()))
                .build();

        Professor savedProfessorA = usersRepository.save(professorA);

        List<Users> result = usersRepository.findAll();

        assertThat(result.size()).isEqualTo(2);


        //delete
        usersRepository.deleteById(professorA.getUserId());
        usersRepository.deleteById(professor.getUserId());

        result = usersRepository.findAll();
        assertThat(result.size()).isEqualTo(0);

    }

    @Test
    @DisplayName("SuperClassTest")
    public void superClass(){
        Account accountA = new Account();
        accountA.setPassword("test");

        accountRepository.save(accountA);

        Professor professor = new Professor().builder()
                .account(accountA)
                .userId(2126000L)
                .userCollege("structure")
                .userName("Lee")
                .userJob(Job.PROFESSOR)
                .userDepartment("software")
                .HIREDATE(String.valueOf(LocalDate.now()))
                .build();

        Users user = professor;

        Users savedUser = usersRepository.save(user);

        Professor pr = (Professor) savedUser;
        log.info(" super User : {} , HireDate : {} " , pr.getUserId(),pr.getHIREDATE());

    }

    @Test
    @DisplayName("사용자 아이디로 존재하는 지 확인하기")
    public void deleteByUserId(){

        Account accountA = new Account();
        accountA.setPassword("test");

        accountRepository.save(accountA);

        Professor professor = new Professor().builder()
                .account(accountA)
                .userId(2126000L)
                .userCollege("structure")
                .userName("Lee")
                .userJob(Job.PROFESSOR)
                .userDepartment("software")
                .HIREDATE(String.valueOf(LocalDate.now()))
                .build();

        usersRepository.save(professor);

        boolean exists = usersRepository.existsById(professor.getUserId());

        log.info("exists : {}", exists);

        assertThat(exists).isTrue();

    }

}
