package FXPROJECT.CHECKPASS.domain.repository.users;

import FXPROJECT.CHECKPASS.domain.entity.users.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAccountRepository extends JpaRepository<Account,Long> {

}
