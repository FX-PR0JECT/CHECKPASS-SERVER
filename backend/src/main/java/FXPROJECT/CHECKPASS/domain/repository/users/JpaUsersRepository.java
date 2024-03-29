package FXPROJECT.CHECKPASS.domain.repository.users;

import FXPROJECT.CHECKPASS.domain.entity.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JpaUsersRepository extends JpaRepository<Users,Long> {

    Users findByUserId(Long loginId);
}
