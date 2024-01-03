package FXPROJECT.CHECKPASS.domain.entity.users;

import FXPROJECT.CHECKPASS.domain.enums.Job;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * CHECKPASS-13
 * User Management System DB 구축
 */

@Getter
@Setter
@SuperBuilder
@Entity
@Inheritance(strategy = InheritanceType.JOINED) // 상속 매핑은 부모 클래스에 @Inheritance 를 사용해야 한다. 그리고 그리고 매핑 전략을 지정해준다 => 조인 전략 사용
@DiscriminatorColumn(name = "userJob") // 부모 클래스에 구분 컬럼을 지정. 이 컬럼으로 저장된 자식 테이블을 구분할 수 있다.
public abstract class Users {

    @Id
    @Column(nullable = false)
    private Long userId;

    @OneToOne
    private Account account;

    @Column(nullable = false , length = 3)
    private int userAge;

    @Column(nullable = false)
    private String userCollege;

    @Column(nullable = false)
    private String userDepartment;

    @Column(nullable = false , length = 30)
    private String userName;

    @Enumerated(EnumType.STRING)
    @Column(insertable = false, updatable = false)
    private Job userJob;

    public Users() {

    }
}
