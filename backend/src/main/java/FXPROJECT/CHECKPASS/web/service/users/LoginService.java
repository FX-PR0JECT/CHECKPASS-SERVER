package FXPROJECT.CHECKPASS.web.service.users;

import FXPROJECT.CHECKPASS.domain.common.exception.UnauthenticatedUser;
import FXPROJECT.CHECKPASS.domain.entity.users.Users;
import FXPROJECT.CHECKPASS.domain.repository.JpaUsersRepository;
import FXPROJECT.CHECKPASS.web.form.requestForm.LoginForm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final JpaUsersRepository jpaUsersRepository;

    @Value("${jwt.secret}")
    private String secretKey;
    private Long expiredMs = 1000 * 60 * 60L;

    @PostMapping("/login")
    public Users login(LoginForm form) {

        if (jpaUsersRepository.existsById(form.getLoginId())){

            Users user = jpaUsersRepository.findByUserId(form.getLoginId());

            if (user != null){

                String password = user.getAccount().getPassword();

                if (form.getLoginPassword().equals(password)){
                    return user;
                }else{
                    throw new UnauthenticatedUser();
                }

            }else{
                throw new UnauthenticatedUser();
            }

        }else{
            throw new UnauthenticatedUser();
        }
    }

}
