package FXPROJECT.CHECKPASS.domain.common;

import FXPROJECT.CHECKPASS.domain.repository.JpaQueryRepository;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class QuerydslConfig {

    private final EntityManager em;

    @Bean
    public JpaQueryRepository jpaQueryRepository(){
        return new JpaQueryRepository(em);
    }

}
