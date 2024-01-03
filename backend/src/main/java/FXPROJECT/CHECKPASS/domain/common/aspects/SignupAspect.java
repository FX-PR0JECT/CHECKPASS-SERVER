package FXPROJECT.CHECKPASS.domain.common.aspects;

import FXPROJECT.CHECKPASS.domain.enums.Job;
import FXPROJECT.CHECKPASS.web.form.ProfessorSignUpForm;
import FXPROJECT.CHECKPASS.web.form.SignUpForm;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
//@Aspect
//@Component
public class SignupAspect {

    ///@Before("execution(* FXPROJECT.CHECKPASS.web.controller.UserController.signup(..))")
    public void signupBefore(JoinPoint joinPoint){
        Object arg = joinPoint.getArgs()[0];
        SignUpForm form = (SignUpForm) arg;
        if (form.getSignUpJob() == Job.PROFESSOR){
            ProfessorSignUpForm test = (ProfessorSignUpForm) form;
            log.info("down cast ProfessorsignUpform :  {} " , test.getSignUpHireDate());
        }
        log.info("arg : {} , signature : {}" , form.getSignUpName(),joinPoint.getSignature());
    }

}
