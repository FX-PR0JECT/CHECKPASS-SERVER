package FXPROJECT.CHECKPASS.web.controller;

import FXPROJECT.CHECKPASS.domain.common.exception.InternalException;
import FXPROJECT.CHECKPASS.domain.common.exception.NoSearchResultsFound;
import FXPROJECT.CHECKPASS.domain.enums.DepartmentsEnum;
import FXPROJECT.CHECKPASS.web.common.annotation.LoginUser;
import FXPROJECT.CHECKPASS.web.common.searchCondition.users.ProfessorSearchCondition;
import FXPROJECT.CHECKPASS.web.common.searchCondition.users.StudentSearchCondition;
import FXPROJECT.CHECKPASS.domain.common.exception.ExistingUSER;
import FXPROJECT.CHECKPASS.domain.common.exception.UnauthenticatedUser;
import FXPROJECT.CHECKPASS.domain.entity.users.*;
import FXPROJECT.CHECKPASS.web.common.utils.ResultFormUtils;
import FXPROJECT.CHECKPASS.web.form.requestForm.users.signup.ProfessorSignUpForm;
import FXPROJECT.CHECKPASS.web.form.requestForm.users.signup.ProfessorUpdateForm;
import FXPROJECT.CHECKPASS.web.form.requestForm.users.signup.SignUpForm;
import FXPROJECT.CHECKPASS.web.form.requestForm.users.signup.StudentSignUpForm;
import FXPROJECT.CHECKPASS.web.form.requestForm.users.update.StudentUpdateForm;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.LoginUserForm;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.SimpleUserInformation;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.ResultForm;
import FXPROJECT.CHECKPASS.web.service.users.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static FXPROJECT.CHECKPASS.domain.common.constant.CommonMessage.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    /**
     * Users 객체 가져오기
     * @param userId Users 고유 Id
     * @return Users 객체 : 없을 경우 Null
     */
    private Users getUser(Long userId){
        if (!userService.existsUser(userId)){
            throw new UnauthenticatedUser();
        }
        return userService.getUser(userId);
    }

    @GetMapping
    public ResultForm showLoginUser(@LoginUser Users user){
        LoginUserForm loginUserForm = new LoginUserForm().builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .build();
        return ResultFormUtils.getSuccessResultForm(loginUserForm);
    }

    /**
     * URL : /users/{userId}
     * @param userId path variable 사용
     * @return ResultSet
     */
    @GetMapping("/{userId}")
    public ResultForm showUserInformation(@PathVariable("userId") Long userId){

        Users user = getUser(userId);

        if (user == null){
            throw new NoSearchResultsFound();
        }

        return ResultFormUtils.getSuccessResultForm(user);
    }

    /**
     * Users 정보 간소화
     * @param userId 사용자 고유 아이디
     * @return Users 간소화 정보 : 없을 경우 Exception 처리
     */
    @GetMapping("/simple/{userId}")
    public ResultForm showUserSimpleInformation(@PathVariable("userId") Long userId){
        
        Users user = getUser(userId);

        if (user == null){
            throw new NoSearchResultsFound();
        }

        SimpleUserInformation sui = new SimpleUserInformation().builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .userDepartment(user.getDepartments().getDepartment())
                .userJob(user.getUserJob())
                .build();

        return ResultFormUtils.getSuccessResultForm(sui);
    }

    /**
     * DB 내 존재하는 Users 인지 확인
     * @param userId Users 고유 ID
     * @return 성공 : 사용 가능 ID 입니다. 실패 : 이미 존재하는 User 입니다.
     */
    @GetMapping("/duplication/{userId}")
    public ResultForm duplicationCheck(@PathVariable("userId") Long userId){

        boolean existsUser = userService.existsUser(userId);

        if (existsUser){
            throw new ExistingUSER();
        }

        return ResultFormUtils.getSuccessResultForm(AVAILABLE_ID.getDescription());
    }

    /**
     * 학생 등록하기
     * @param form 학생 전용 등록 Form
     * @param bindingResult 검증
     * @return 성공 : 등록이 완료 되었습니다. 실패 : 이미 존재하는 회원 입니다.
     */
    @PostMapping("/studentSignup")
    public ResultForm studentSignup(@RequestBody @Validated StudentSignUpForm form , BindingResult bindingResult){

        filterDepartments(form);

        Users users = userService.transferToStudent(form);

        userService.join(users);

        if(!userService.existsUser(users.getUserId())){
            throw new ExistingUSER();
        }

        return ResultFormUtils.getSuccessResultForm(COMPLETE_JOIN.getDescription());

    }

    private static void filterDepartments(SignUpForm form) {

        DepartmentsEnum signUpDepartment = form.getSignUpDepartment();

        if(signUpDepartment == null){
            form.setSignUpDepartment(DepartmentsEnum.valueOf(String.valueOf(form.getSignUpCollege())));
        }
    }

    /**
     * 교수 등록하기
     * @param form 교수 전용 등록 Form
     * @return 성공 : 등록이 완료 되었습니다. 실패 : 이미 존재하는 회원 입니다.
     */
    @PostMapping("/professorSignup")
    public ResultForm professorSignup(@RequestBody ProfessorSignUpForm form){

        filterDepartments(form);

        Users users = userService.transferToProfessorOrStaff(form);

        Users joinUser = userService.join(users);

        if(joinUser == null){
            throw new ExistingUSER();
        }

        return ResultFormUtils.getSuccessResultForm(COMPLETE_JOIN.getDescription());

    }

    /**
     * 회원 탈퇴하기
     * @param userId 회원 고유 ID
     * @return 성공 : 삭제가 완료 되었습니다. 실패 존재하지 않는 User 입니다.
     */
    @DeleteMapping("/{userId}")
    public ResultForm secessionUser(@PathVariable("userId") Long userId){

        if (userService.existsUser(userId)){
            throw new UnauthenticatedUser();
        }

        userService.secessionUser(userId);

        Users user = userService.getUser(userId);

        if (user != null){
            throw new InternalException();
        }

        return ResultFormUtils.getSuccessResultForm(COMPLETE_DELETE);
    }

    /**
     * 교수 정보 수정하기
     * @param userId 회원 고유 ID
     * @param form 교수 / 직원 전용 수정 정보 form
     * @return 성공 : 수정이 완료 되었습니다.
     */
    @PatchMapping("/professor/{userId}")
    public ResultForm editProfessorInformation(@PathVariable("userId") Long userId, @RequestBody ProfessorUpdateForm form){

        Users users = userService.editProfessorInformation(userId, form);

        if (users == null){
            throw new InternalException();
        }

        return ResultFormUtils.getSuccessResultForm(COMPLETE_UPDATE.getDescription());
    }

    /**
     * 학생 정보 수정하기
     * @param userId 회원 고유 ID
     * @param form 학생 전용 수정 정보 form
     * @return 성공 : 수정이 완료 되었습니다.
     */
    @PatchMapping("/student/{userId}")
    public ResultForm editStudentInformation(@PathVariable("userId") Long userId, @RequestBody StudentUpdateForm form){

        Users users = userService.editStudentInformation(userId, form);

        if (users == null){
            throw new InternalException();
        }

        return ResultFormUtils.getSuccessResultForm(COMPLETE_UPDATE.getDescription());
    }

    /**
     * 교수 리스트 받아오기
     * @param condition
     * @param pageable
     * @return
     */
    @GetMapping("/professor")
    public ResultForm getProfessorList(@RequestBody ProfessorSearchCondition condition, Pageable pageable){

        List<Professor> professors = userService.getProfessorList(condition,pageable);

        return ResultFormUtils.getSuccessResultForm(professors);
    }

    @GetMapping("/staff")
    public ResultForm getStaffList(@RequestBody ProfessorSearchCondition condition,Pageable pageable){

        List<Staff> staff = userService.getStaffList(condition,pageable);

        return ResultFormUtils.getSuccessResultForm(staff);
    }

    @GetMapping("/student")
    public ResultForm getStudentList(@RequestBody StudentSearchCondition condition,Pageable pageable){

        List<Students> students = userService.getStudentList(condition,pageable);

        return ResultFormUtils.getSuccessResultForm(students);
    }

}
