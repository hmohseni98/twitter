package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Account followers;
    @ManyToOne
    private Account followings;


    public Follow(Account followers, Account followings) {
        this.followers = followers;
        this.followings = followings;
    }

    @Override
    public String toString() {
        return "Follow{" +
                "id=" + id +
                ", followers=" + followers.getUsername() +
                ", followings=" + followings.getUsername() +
                '}';
    }
}
