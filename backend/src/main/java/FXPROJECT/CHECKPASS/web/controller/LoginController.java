package FXPROJECT.CHECKPASS.web.controller;

import FXPROJECT.CHECKPASS.domain.common.constant.CommonMessage;
import FXPROJECT.CHECKPASS.domain.common.constant.SessionConst;
import FXPROJECT.CHECKPASS.domain.common.constant.State;
import FXPROJECT.CHECKPASS.domain.common.exception.NoSuchUser;
import FXPROJECT.CHECKPASS.domain.entity.users.Users;
import FXPROJECT.CHECKPASS.web.form.requestForm.LoginForm;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.ResultForm;
import FXPROJECT.CHECKPASS.web.service.users.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResultForm login(@RequestBody LoginForm form, HttpServletRequest request){

        Users loginUser = loginService.login(form);

        if (loginUser == null){
            throw new NoSuchUser();
        }


        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginUser);

        return new ResultForm().builder()
                .state(State.SUCCESS)
                .code("")
                .resultSet(CommonMessage.SUCCESS_LOGIN)
                .build();
    }

    @PostMapping("/logout")
    public ResultForm logoutV3(HttpServletRequest request){

        HttpSession session = request.getSession(false);

        if (session != null){
            session.invalidate();
        }

        return new ResultForm().builder()
                .state(State.SUCCESS)
                .code("")
                .resultSet(CommonMessage.SUCCESS_LOGOUT)
                .build();
    }

}
