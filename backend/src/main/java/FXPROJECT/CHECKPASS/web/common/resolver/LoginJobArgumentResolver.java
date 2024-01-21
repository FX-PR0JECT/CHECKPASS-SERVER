package FXPROJECT.CHECKPASS.web.common.resolver;

import FXPROJECT.CHECKPASS.domain.common.constant.SessionConst;
import FXPROJECT.CHECKPASS.domain.common.exception.NoPermission;
import FXPROJECT.CHECKPASS.domain.entity.college.Departments;
import FXPROJECT.CHECKPASS.domain.entity.users.Users;
import FXPROJECT.CHECKPASS.domain.enums.Job;
import FXPROJECT.CHECKPASS.web.common.annotation.LoginUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Slf4j
public class LoginJobArgumentResolver implements HandlerMethodArgumentResolver {


    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        log.info("LoginDepartment Resolver support Parameter 실행");

        boolean hasLoginAnnotation =
                parameter.hasParameterAnnotation(LoginUser.class);
        boolean hasMemberType =
                Departments.class.isAssignableFrom(parameter.getParameterType());
        return hasLoginAnnotation && hasMemberType;

    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        log.info("resolveArgument 실행");

        HttpServletRequest request = (HttpServletRequest)
                webRequest.getNativeRequest();
        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        }

        Users loggedInUser = (Users) session.getAttribute(SessionConst.LOGIN_MEMBER);

        Job userJob = loggedInUser.getUserJob();

        if(userJob == Job.STUDENTS){
            throw new NoPermission();
        }

        return loggedInUser;
    }
}
