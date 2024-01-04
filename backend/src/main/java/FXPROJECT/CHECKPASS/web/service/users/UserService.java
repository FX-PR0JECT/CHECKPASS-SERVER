package FXPROJECT.CHECKPASS.web.service.users;

import FXPROJECT.CHECKPASS.domain.common.constant.CommonMessage;
import FXPROJECT.CHECKPASS.domain.common.constant.ErrorCode;
import FXPROJECT.CHECKPASS.domain.common.constant.State;
import FXPROJECT.CHECKPASS.domain.common.exception.DupleUsers;
import FXPROJECT.CHECKPASS.domain.common.exception.NoSuchUser;
import FXPROJECT.CHECKPASS.domain.entity.users.*;
import FXPROJECT.CHECKPASS.domain.enums.Job;
import FXPROJECT.CHECKPASS.domain.repository.JpaAccountRepository;
import FXPROJECT.CHECKPASS.domain.repository.JpaQueryRepository;
import FXPROJECT.CHECKPASS.domain.repository.JpaUsersRepository;
import FXPROJECT.CHECKPASS.web.form.requestForm.ProfessorSignUpForm;
import FXPROJECT.CHECKPASS.web.form.requestForm.SignUpForm;
import FXPROJECT.CHECKPASS.web.form.requestForm.StudentSignUpForm;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.ResultForm;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    private final JpaQueryRepository jpaQueryUsersRepository;
    private final JpaUsersRepository jpaUsersRepository;
    private final JpaAccountRepository jpaAccountRepository;

    /**
     * 회원 가입
     * @param user 저장할 users 객체
     * @return 저장된 객체를 반환
     */
    @Transactional
    public Users join(Users user){

        if (!existsUser(user.getUserId())){
            return jpaUsersRepository.save(user);
        }else{
            throw new DupleUsers(ErrorCode.DUPLICATION_USERS.getDescription());
        }

    }


    /**
     * 사용자 아이디를 이용한 사용자 정보 조회 : 단일 조회
     * @param userId User id
     * @return User 객체
     */
    public Users getUser(Long userId){
        Optional<Users> target = jpaUsersRepository.findById(userId);
        return target.orElse(null);
    }

//    public List<Users> getUserList(){
//        return jpaQueryUsersRepository.findAll();
//    }

    /**
     * 사용자 아이디를 이용한 사용자 정보 삭제
     * @param userId User id
     * @return true : 삭제 완료, false : 문제 발생 -> 예외로 변경 필요
     */
    @Transactional
    public ResultForm secessionUser(Long userId){

        if (!existsUser(userId)) {
            throw new NoSuchUser();
        }

        jpaUsersRepository.deleteById(userId);
        return new ResultForm().builder()
                .state(State.SUCCESS)
                .code("")
                .resultSet(CommonMessage.COMPLETE_DELETE.getDescription())
                .build();
    }

    /**
     * 사용자 아이디를 이용해서 존재하는지 확인
     * @param userId User id
     * @return true : 존재, false : 존재하지 않음
     */
    public Boolean existsUser(Long userId){
        return jpaUsersRepository.existsById(userId);
    }

//    /**
//     * 사용자 정보 수정 Method
//     * @param userId User id
//     * @param param 사용자 정보 수정 Parameter
//     * @return 수정된 사용자 정보
//     */
//    @Transactional
//    public Users editUserInformation(Long userId, Users param){
//
//        if (!existsUser(userId)){
//            throw new NoSuchUser();
//        }
//
//        Users users = updateAllData(jpaUsersRepository.findById(userId).get(), param);
//
//        jpaUsersRepository.save(users);
//
//        return jpaUsersRepository.findById(userId).get();
//
//    }
//
//    /**
//     * 저장된 사용자 정보를 param Data 로 Update
//     * @param target 수정할 사용자 정보
//     * @param param 수정될 정보
//     * @return 수정 된 최종 정보
//     */
//    private Users updateAllData(Users target, Users param) {
//
//        target.setUserId(param.getUserId());
//        target.setUserJob(param.getUserJob());
//        target.setUserName(param.getUserName());
//        target.setUserDepartment(param.getUserDepartment());
//        target.setAccount(param.getAccount());
//        target.setUserCollege(param.getUserCollege());
//
//        if (target instanceof Professor){
//            Professor downcastProfessor = (Professor) target;
//            Professor updateParam = (Professor) param;
//            downcastProfessor.setHIREDATE(updateParam.getHIREDATE());
//            return downcastProfessor;
//        } else if (target instanceof Staff) {
//            Staff downcastStaff = (Staff) target;
//            Staff updateParam = (Staff) target;
//            downcastStaff.setHIREDATE(updateParam.getHIREDATE());
//            return downcastStaff;
//        } else if (target instanceof Students) {
//            Students downcastStudent = (Students) target;
//            Students updateParam = (Students) param;
//            downcastStudent.setDayOrNight(updateParam.getDayOrNight());
//            downcastStudent.setStudentGrade(updateParam.getStudentGrade());
//            downcastStudent.setStudentSemester(updateParam.getStudentSemester());
//            return downcastStudent;
//        }else{
//            return target;
//        }
//    }

    public Users transferToProfessorOrStaff(SignUpForm form) {

        Account account = setAccount(form);

        if (form instanceof ProfessorSignUpForm) {
            ProfessorSignUpForm professorSignUpForm = (ProfessorSignUpForm) form;
            if (form.getSignUpJob() == Job.PROFESSOR){
                Professor professor = new Professor().builder()
                        .userId(professorSignUpForm.getSignUpId())
                        .userJob(professorSignUpForm.getSignUpJob())
                        .userCollege(professorSignUpForm.getSignUpCollege())
                        .userDepartment(professorSignUpForm.getSignUpDepartment())
                        .userName(professorSignUpForm.getSignUpName())
                        .HIREDATE(professorSignUpForm.getSignUpHireDate())
                        .account(account)
                        .build();
                return professor;
            }else {
                Staff staff = new Staff().builder()
                        .userId(professorSignUpForm.getSignUpId())
                        .userJob(professorSignUpForm.getSignUpJob())
                        .userCollege(professorSignUpForm.getSignUpCollege())
                        .userDepartment(professorSignUpForm.getSignUpDepartment())
                        .userName(professorSignUpForm.getSignUpName())
                        .HIREDATE(professorSignUpForm.getSignUpHireDate())
                        .account(account)
                        .build();
                return staff;
            }
        }
        return null;
    }

    public Users transferToStudent(StudentSignUpForm form) {

        Account account = setAccount(form);

        StudentSignUpForm stuForm = (StudentSignUpForm) form;
        Students student = new Students().builder()
                .userId(stuForm.getSignUpId())
                .userName(stuForm.getSignUpName())
                .userJob(stuForm.getSignUpJob())
                .userDepartment(stuForm.getSignUpDepartment())
                .userCollege(stuForm.getSignUpCollege())
                .account(account)
                .dayOrNight(stuForm.getSignUpDayOrNight())
                .studentGrade(stuForm.getSignUpGrade())
                .studentSemester(stuForm.getSignUpSemester())
                .build();
        return student;
    }

    private Account setAccount(SignUpForm form){
        Account account = new Account();
        account.setPassword(form.getSignUpPassword());

        jpaAccountRepository.save(account);
        return account;
    }


}
