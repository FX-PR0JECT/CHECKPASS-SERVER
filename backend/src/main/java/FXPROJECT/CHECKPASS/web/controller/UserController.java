package FXPROJECT.CHECKPASS.web.controller;

import FXPROJECT.CHECKPASS.domain.common.ProfessorSearchCondition;
import FXPROJECT.CHECKPASS.domain.common.StudentSearchCondition;
import FXPROJECT.CHECKPASS.domain.common.constant.CommonMessage;
import FXPROJECT.CHECKPASS.domain.common.constant.ErrorCode;
import FXPROJECT.CHECKPASS.domain.common.constant.State;
import FXPROJECT.CHECKPASS.domain.common.exception.DupleUsers;
import FXPROJECT.CHECKPASS.domain.common.exception.NoSuchUser;
import FXPROJECT.CHECKPASS.domain.entity.users.*;
import FXPROJECT.CHECKPASS.web.form.requestForm.*;
import FXPROJECT.CHECKPASS.web.form.responseForm.SimpleUserInformation;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.ResultForm;
import FXPROJECT.CHECKPASS.web.service.users.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private Users getUser(Long userId){
        if (!userService.existsUser(userId)){
            throw new NoSuchUser();
        }

        return userService.getUser(userId);
    }

    @GetMapping("/{userId}")
    public ResultForm showUserInformation(@PathVariable("userId") Long userId){

        Users user = getUser(userId);

        return new ResultForm().builder()
                .state(State.SUCCESS)
                .code("")
                .resultSet(user)
                .build();
    }

    @GetMapping("/simple/{userId}")
    public ResultForm showUserSimpleInformation(@PathVariable("userId") Long userId){

        Users user = getUser(userId);

        SimpleUserInformation sui = new SimpleUserInformation().builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .userDepartment(user.getUserDepartment())
                .build();

        return ResultForm.builder()
                .state(State.SUCCESS)
                .code("")
                .resultSet(sui)
                .build();
    }


    @GetMapping("/duplication/{userId}")
    public ResultForm duplicationCheck(@PathVariable("userId") Long userId){

        log.info("userId : {} " , userId);

        boolean existsUser = userService.existsUser(userId);

        if (!existsUser){
            return ResultForm.builder()
                    .state(State.SUCCESS)
                    .code("")
                    .resultSet(CommonMessage.AVAILABLE.getDescription())
                    .build();
        }else {
            throw new DupleUsers();
        }


    }

    @PostMapping("/studentSignup")
    public ResultForm studentSignup(@RequestBody StudentSignUpForm form){

        log.info("form : {}" , form.getSignUpGrade());

        Users users = userService.transferToStudent(form);

        userService.join(users);

        ResultForm resultForm = new ResultForm();

        if(userService.existsUser(users.getUserId())){
            return resultForm.builder()
                    .state(State.SUCCESS)
                    .code("")
                    .resultSet(CommonMessage.COMPLETE_JOIN.getDescription())
                    .build();
        }else {
            throw new DupleUsers(ErrorCode.DUPLICATION_USERS.getCode());
        }

    }

    @PostMapping("/professorSignup")
    public ResultForm professorSignup(@RequestBody ProfessorSignUpForm form){

        log.info("form : {}" , form.getSignUpHireDate());

        Users users = userService.transferToProfessorOrStaff(form);

        Users joinUser = userService.join(users);

        ResultForm resultForm = new ResultForm();

        if(joinUser != null){
            return resultForm.builder()
                    .state(State.SUCCESS)
                    .code("")
                    .resultSet(CommonMessage.COMPLETE_JOIN.getDescription())
                    .build();
        }else {
            throw new DupleUsers(ErrorCode.DUPLICATION_USERS.getCode());
        }
    }

    @DeleteMapping("/{userId}")
    public ResultForm secessionUser(@PathVariable("userId") Long userId){
        return userService.secessionUser(userId);
    }

    @PatchMapping("/professor/{userId}")
    public ResultForm editProfessorInformation(@PathVariable("userId") Long userId, @RequestBody ProfessorUpdateForm form){

        Users users = userService.editProfessorInformation(userId, form);

        return ResultForm.builder()
                .state(State.SUCCESS)
                .code("")
                .resultSet(CommonMessage.COMPLETE_UPDATE.getDescription())
                .build();
    }

    @PatchMapping("/student/{userId}")
    public ResultForm editStudentInformation(@PathVariable("userId") Long userId, @RequestBody StudentUpdateForm form){

        Users users = userService.editStudentInformation(userId, form);

        return ResultForm.builder()
                .state(State.SUCCESS)
                .code("")
                .resultSet(CommonMessage.COMPLETE_UPDATE.getDescription())
                .build();
    }

    @GetMapping("/professor")
    public ResultForm getProfessorList(@RequestBody ProfessorSearchCondition condition, Pageable pageable){

        List<Professor> professors = userService.getProfessorList(condition,pageable);

        return new ResultForm().builder()
                .state(State.SUCCESS)
                .code("")
                .resultSet(professors)
                .build();
    }

    @GetMapping("/staff")
    public ResultForm getStaffList(@RequestBody ProfessorSearchCondition condition,Pageable pageable){

        List<Staff> staff = userService.getStaffList(condition,pageable);

        return new ResultForm().builder()
                .state(State.SUCCESS)
                .code("")
                .resultSet(staff)
                .build();
    }

    @GetMapping("/student")
    public ResultForm getStudentList(@RequestBody StudentSearchCondition condition,Pageable pageable){

        List<Students> students = userService.getStudentList(condition,pageable);

        return new ResultForm().builder()
                .state(State.SUCCESS)
                .code("")
                .resultSet(students)
                .build();
    }

}
