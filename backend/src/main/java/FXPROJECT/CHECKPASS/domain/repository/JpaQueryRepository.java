package FXPROJECT.CHECKPASS.domain.repository;

import FXPROJECT.CHECKPASS.domain.entity.users.QUsers;
import FXPROJECT.CHECKPASS.domain.entity.users.Users;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static FXPROJECT.CHECKPASS.domain.entity.users.QUsers.*;


@Repository
public class JpaQueryRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;

    @Autowired
    public JpaQueryRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    public List<Users> findAll() {

        List<Users> result = query
                .select(users)
                .from(users)
                .where()
                .fetch();

        return result;
    }

    private BooleanExpression equalUserCollege(String userCollege){
        if (StringUtils.hasText(userCollege)){
            return users.userCollege.eq("%" + userCollege + "%");
        }
        return null;
    }

}
