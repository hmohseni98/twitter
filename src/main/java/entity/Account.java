package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true, columnDefinition = "varchar(30)")
    private String username;
    @Column(nullable = false, columnDefinition = "varchar(16)")
    private String password;

    @OneToMany(mappedBy = "followers")
    private Set<Follow> follower;

    @OneToMany(mappedBy = "followings")
    private Set<Follow> following;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Account(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public String toString2() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
