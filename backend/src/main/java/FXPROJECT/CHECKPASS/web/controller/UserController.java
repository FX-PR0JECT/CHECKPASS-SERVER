package FXPROJECT.CHECKPASS.web.controller;

import FXPROJECT.CHECKPASS.domain.common.constant.CommonMessage;
import FXPROJECT.CHECKPASS.domain.common.constant.ErrorCode;
import FXPROJECT.CHECKPASS.domain.common.constant.State;
import FXPROJECT.CHECKPASS.domain.common.exception.DupleUsers;
import FXPROJECT.CHECKPASS.domain.entity.users.*;
import FXPROJECT.CHECKPASS.web.form.ProfessorSignUpForm;
import FXPROJECT.CHECKPASS.web.form.StudentSignUpForm;
import FXPROJECT.CHECKPASS.web.form.resultForm.ResultForm;
import FXPROJECT.CHECKPASS.web.service.users.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

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




}
