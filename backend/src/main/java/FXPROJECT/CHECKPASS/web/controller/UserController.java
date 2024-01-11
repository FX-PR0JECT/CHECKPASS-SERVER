package FXPROJECT.CHECKPASS.web.controller;

import FXPROJECT.CHECKPASS.domain.common.ProfessorSearchCondition;
import FXPROJECT.CHECKPASS.domain.common.StudentSearchCondition;
import FXPROJECT.CHECKPASS.domain.common.constant.ErrorCode;
import FXPROJECT.CHECKPASS.domain.common.exception.ExistingUSER;
import FXPROJECT.CHECKPASS.domain.common.exception.UnauthenticatedUser;
import FXPROJECT.CHECKPASS.domain.entity.users.*;
import FXPROJECT.CHECKPASS.web.common.utils.ResultSetUtils;
import FXPROJECT.CHECKPASS.web.form.requestForm.users.signup.ProfessorSignUpForm;
import FXPROJECT.CHECKPASS.web.form.requestForm.users.signup.ProfessorUpdateForm;
import FXPROJECT.CHECKPASS.web.form.requestForm.users.signup.StudentSignUpForm;
import FXPROJECT.CHECKPASS.web.form.requestForm.users.update.StudentUpdateForm;
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
import static FXPROJECT.CHECKPASS.domain.common.constant.State.SUCCESS;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private Users getUser(Long userId){
        if (!userService.existsUser(userId)){
            throw new UnauthenticatedUser();
        }

        return userService.getUser(userId);
    }

    @GetMapping("/{userId}")
    public ResultForm showUserInformation(@PathVariable("userId") Long userId){

        Users user = getUser(userId);

        return ResultSetUtils.getResultForm(SUCCESS,ErrorCode.OK.getCode(),ErrorCode.OK.getTitle(),user,null);
    }

    @GetMapping("/simple/{userId}")
    public ResultForm showUserSimpleInformation(@PathVariable("userId") Long userId){

        Users user = getUser(userId);

        SimpleUserInformation sui = new SimpleUserInformation().builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .userDepartment(user.getUserDepartment())
                .build();

        return ResultSetUtils.getResultForm(SUCCESS,ErrorCode.OK.getCode(),ErrorCode.OK.getTitle(),sui,null);
    }


    @GetMapping("/duplication/{userId}")
    public ResultForm duplicationCheck(@PathVariable("userId") Long userId){

        log.info("userId : {} " , userId);

        boolean existsUser = userService.existsUser(userId);

        if (!existsUser){
            return ResultSetUtils.getResultForm(SUCCESS,ErrorCode.OK.getCode(),ErrorCode.OK.getTitle(),AVAILABLE_ID.getDescription(),null);
        }else {
            throw new ExistingUSER();
        }


    }

    @PostMapping("/studentSignup")
    public ResultForm studentSignup(@RequestBody @Validated StudentSignUpForm form , BindingResult bindingResult){

        log.info("form : {}" , form.getSignUpGrade());

        Users users = userService.transferToStudent(form);

        userService.join(users);

        ResultForm resultForm = new ResultForm();

        if(userService.existsUser(users.getUserId())){
            return ResultSetUtils.getResultForm(SUCCESS,ErrorCode.OK.getCode(),ErrorCode.OK.getTitle(),COMPLETE_JOIN.getDescription(),null);
        }else {
            throw new ExistingUSER();
        }

    }

    @PostMapping("/professorSignup")
    public ResultForm professorSignup(@RequestBody ProfessorSignUpForm form){

        log.info("form : {}" , form.getSignUpHireDate());

        Users users = userService.transferToProfessorOrStaff(form);

        Users joinUser = userService.join(users);

        ResultForm resultForm = new ResultForm();

        if(joinUser != null){
            return ResultSetUtils.getResultForm(SUCCESS,ErrorCode.OK.getCode(),ErrorCode.OK.getTitle(),COMPLETE_JOIN.getDescription(),null);
        }else {
            throw new ExistingUSER();
        }
    }

    @DeleteMapping("/{userId}")
    public ResultForm secessionUser(@PathVariable("userId") Long userId){
        return userService.secessionUser(userId);
    }

    @PatchMapping("/professor/{userId}")
    public ResultForm editProfessorInformation(@PathVariable("userId") Long userId, @RequestBody ProfessorUpdateForm form){

        Users users = userService.editProfessorInformation(userId, form);

        return ResultSetUtils.getResultForm(SUCCESS,ErrorCode.OK.getCode(),ErrorCode.OK.getTitle(),COMPLETE_UPDATE.getDescription(),null);
    }

    @PatchMapping("/student/{userId}")
    public ResultForm editStudentInformation(@PathVariable("userId") Long userId, @RequestBody StudentUpdateForm form){

        Users users = userService.editStudentInformation(userId, form);

        return ResultSetUtils.getResultForm(SUCCESS,ErrorCode.OK.getCode(),ErrorCode.OK.getTitle(),COMPLETE_UPDATE.getDescription(),null);
    }

    @GetMapping("/professor")
    public ResultForm getProfessorList(@RequestBody ProfessorSearchCondition condition, Pageable pageable){

        List<Professor> professors = userService.getProfessorList(condition,pageable);

        return ResultSetUtils.getResultForm(SUCCESS,ErrorCode.OK.getCode(),ErrorCode.OK.getTitle(),professors,null);
    }

    @GetMapping("/staff")
    public ResultForm getStaffList(@RequestBody ProfessorSearchCondition condition,Pageable pageable){

        List<Staff> staff = userService.getStaffList(condition,pageable);

        return ResultSetUtils.getResultForm(SUCCESS,ErrorCode.OK.getCode(),ErrorCode.OK.getTitle(),staff,null);
    }

    @GetMapping("/student")
    public ResultForm getStudentList(@RequestBody StudentSearchCondition condition,Pageable pageable){

        List<Students> students = userService.getStudentList(condition,pageable);

        return ResultSetUtils.getResultForm(SUCCESS,ErrorCode.OK.getCode(),ErrorCode.OK.getTitle(),students,null);
    }

}
