package FXPROJECT.CHECKPASS.web.service.users;

import FXPROJECT.CHECKPASS.domain.entity.college.Colleges;
import FXPROJECT.CHECKPASS.domain.entity.college.Departments;
import FXPROJECT.CHECKPASS.domain.enums.DepartmentsEnum;
import FXPROJECT.CHECKPASS.domain.repository.college.JpaCollegesRepository;
import FXPROJECT.CHECKPASS.domain.repository.college.JpaDepartmentRepository;
import FXPROJECT.CHECKPASS.web.common.searchCondition.users.ProfessorSearchCondition;
import FXPROJECT.CHECKPASS.web.common.searchCondition.users.StudentSearchCondition;
import FXPROJECT.CHECKPASS.domain.common.exception.ExistingUSER;
import FXPROJECT.CHECKPASS.domain.common.exception.InvalidRoleRequest;
import FXPROJECT.CHECKPASS.domain.common.exception.UnauthenticatedUser;
import FXPROJECT.CHECKPASS.domain.entity.users.*;
import FXPROJECT.CHECKPASS.domain.enums.Job;
import FXPROJECT.CHECKPASS.domain.repository.users.JpaAccountRepository;
import FXPROJECT.CHECKPASS.domain.repository.QueryRepository;
import FXPROJECT.CHECKPASS.domain.repository.users.JpaUsersRepository;
import FXPROJECT.CHECKPASS.web.form.requestForm.users.signup.ProfessorSignUpForm;
import FXPROJECT.CHECKPASS.web.form.requestForm.users.signup.ProfessorUpdateForm;
import FXPROJECT.CHECKPASS.web.form.requestForm.users.signup.SignUpForm;
import FXPROJECT.CHECKPASS.web.form.requestForm.users.signup.StudentSignUpForm;
import FXPROJECT.CHECKPASS.web.form.requestForm.users.update.StudentUpdateForm;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    private final QueryRepository jpaQueryUsersRepository;
    private final JpaUsersRepository jpaUsersRepository;
    private final JpaAccountRepository jpaAccountRepository;
    private final JpaCollegesRepository jpaCollegesRepository;
    private final JpaDepartmentRepository jpaDepartmentRepository;

    /**
     * 회원 가입
     * @param user 저장할 users 객체
     * @return 저장된 객체를 반환
     */
    @Transactional
    public Users join(Users user){

        if (existsUser(user.getUserId())){
            throw new ExistingUSER();
        }
        return jpaUsersRepository.save(user);
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

    /**
     * 사용자 아이디를 이용한 사용자 정보 삭제
     * @param userId User id
     * @return true : 삭제 완료, false : 문제 발생 -> 예외로 변경 필요
     */
    @Transactional
    public void secessionUser(Long userId){

        if (!existsUser(userId)) {
            throw new UnauthenticatedUser();
        }
        jpaUsersRepository.deleteById(userId);
    }

    /**
     * 사용자 아이디를 이용해서 존재하는지 확인
     * @param userId User id
     * @return true : 존재, false : 존재하지 않음
     */
    public Boolean existsUser(Long userId){
        return jpaUsersRepository.existsById(userId);
    }

    /**
     * 사용자 정보 수정 Method
     * @param userId User id
     * @param param 사용자 정보 수정 Parameter
     * @return 수정된 사용자 정보
     */
    @Transactional
    public Users editProfessorInformation(Long userId, ProfessorUpdateForm param){

        if (!existsUser(userId)){
            throw new UnauthenticatedUser();
        }

        Users target = jpaUsersRepository.findById(userId).get();

        if (target.getUserJob() != Job.PROFESSOR && target.getUserJob() != Job.STAFF){
            throw new InvalidRoleRequest();
        }

        Users users = updateAllProfessorAndStaff(target, param);

        jpaUsersRepository.save(users);

        return users;

    }

    /**
     * 사용자 정보 수정 Method
     * @param userId User id
     * @param param 사용자 정보 수정 Parameter
     * @return 수정된 사용자 정보
     */
    @Transactional
    public Users editStudentInformation(Long userId, StudentUpdateForm param){

        if (!existsUser(userId)){
            throw new UnauthenticatedUser();
        }

        Users target = jpaUsersRepository.findById(userId).get();

        if (target.getUserJob() != Job.STUDENTS){
            throw new InvalidRoleRequest();
        }

        Users users = updateAllStudent(target, param);

        jpaUsersRepository.save(users);

        return jpaUsersRepository.findById(userId).get();

    }

    /**
     * 저장된 사용자 정보를 param Data 로 Update
     * @param target 수정할 사용자 정보
     * @param param 수정될 정보
     * @return 수정 된 최종 정보
     */
    private Users updateAllProfessorAndStaff(Users target, ProfessorUpdateForm param) {

        log.info("param data : {} " , param.getUpdateDepartment());

        Optional<Departments> departments = jpaDepartmentRepository.findByDepartment(param.getUpdateDepartment());

        log.info("update Professor : {}" , departments.get().getDepartment());

        if (departments.isEmpty()){
            log.info("departments Error");
        }

        target.setDepartments(departments.get());
        target.setUserName(param.getUpdateName());
        target.getAccount().setPassword(param.getUpdatePassword());

        if (target instanceof Professor){
            Professor downcastProfessor = (Professor) target;
            ProfessorUpdateForm updateParam = (ProfessorUpdateForm) param;
            downcastProfessor.setHIREDATE(updateParam.getUpdateHireDate());
            return downcastProfessor;
        } else if (target instanceof Staff) {
            Staff downcastStaff = (Staff) target;
            ProfessorUpdateForm updateParam = (ProfessorUpdateForm) param;
            downcastStaff.setHIREDATE(updateParam.getUpdateHireDate());
            return downcastStaff;
        }else{
            return target;
        }
    }

    private Optional<Departments> getDepartments(String departmentName) {
        Optional<Departments> byDepartment = jpaDepartmentRepository.findByDepartment(departmentName);
        return byDepartment;
    }

    private Users updateAllStudent(Users target, StudentUpdateForm param) {

        Optional<Departments> departments = getDepartments(param.getUpdateDepartment());

        if (departments.isEmpty()){
            log.info("departments Error");
        }

        target.setDepartments(departments.get());
        target.setUserName(param.getUpdateName());
        target.getAccount().setPassword(param.getUpdatePassword());


        Students downcastStudent = (Students) target;
        StudentUpdateForm updateParam = (StudentUpdateForm) param;
        downcastStudent.setStudentGrade(updateParam.getUpdateStudentGrade());
        downcastStudent.setDayOrNight(updateParam.getUpdateDayOrNight());
        downcastStudent.setStudentSemester(updateParam.getUpdateStudentSemester());
        return downcastStudent;

    }

    public Users transferToProfessorOrStaff(SignUpForm form) {

        Account account = setAccount(form);

        if (form instanceof ProfessorSignUpForm) {
            ProfessorSignUpForm professorSignUpForm = (ProfessorSignUpForm) form;
            if (form.getSignUpJob() == Job.PROFESSOR){

                Optional<Departments> departments = getDepartments(form.getSignUpDepartment().getDepartment());

                if (departments.isEmpty()){
                    log.info("departments Error");
                }

                Professor professor = new Professor().builder()
                        .userId(professorSignUpForm.getSignUpId())
                        .userJob(professorSignUpForm.getSignUpJob())
                        .departments(departments.get())
                        .userName(professorSignUpForm.getSignUpName())
                        .HIREDATE(professorSignUpForm.getSignUpHireDate())
                        .account(account)
                        .build();
                return professor;
            }else {

                Optional<Departments> departments = getDepartments(form.getSignUpDepartment().getDepartment());

                if (departments.isEmpty()){
                    log.info("departments Error");
                }

                Staff staff = new Staff().builder()
                        .userId(professorSignUpForm.getSignUpId())
                        .userJob(professorSignUpForm.getSignUpJob())
                        .departments(departments.get())
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

        Optional<Departments> departments = getDepartments(form.getSignUpDepartment().getDepartment());

        if (departments.isEmpty()){
            log.info("departments Error");
        }

        StudentSignUpForm stuForm = (StudentSignUpForm) form;
        Students student = new Students().builder()
                .userId(stuForm.getSignUpId())
                .userName(stuForm.getSignUpName())
                .userJob(stuForm.getSignUpJob())
                .departments(departments.get())
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

    public List<Professor> getProfessorList(ProfessorSearchCondition condition, Pageable pageable) {
        return jpaQueryUsersRepository.getProfessorList(condition,pageable);
    }

    public List<Staff> getStaffList(ProfessorSearchCondition condition,Pageable pageable) {
        return jpaQueryUsersRepository.getStaffList(condition,pageable);
    }

    public List<Students> getStudentList(StudentSearchCondition condition,Pageable pageable) {
        return jpaQueryUsersRepository.getStudentList(condition,pageable);
    }

}
