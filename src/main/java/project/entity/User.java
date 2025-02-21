package project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.enums.Role;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "shop_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    private String password;

    private String name;

    private String email;

    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_CLIENT;

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private Cart cart;


    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
