package FXPROJECT.CHECKPASS.domain.entity.users;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Entity
@Getter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, length = 15)
    private String password;

    public Account() {

    }
}
