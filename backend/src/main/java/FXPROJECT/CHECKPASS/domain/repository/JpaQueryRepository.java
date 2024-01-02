package FXPROJECT.CHECKPASS.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JpaQueryRepository {

    private JPAQueryFactory factory;

    @Autowired
    public JpaQueryRepository(EntityManager em) {
        factory = new JPAQueryFactory(em);
    }

}
