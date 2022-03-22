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
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @ManyToOne
    @JoinColumn(name = "tweet_id")
    private Tweet tweet;
    @Enumerated(EnumType.STRING)
    private Type type;

    public Likes(Account account, Tweet tweet, Type type) {
        this.account = account;
        this.tweet = tweet;
        this.type = type;
    }

    @Override
    public String toString() {
        return "LikeOrDislike{" +
                "id=" + id +
                ", account=" + account.getUsername() +
                ", tweet=" + tweet.getDescription() +
                ", type=" + type +
                '}';
    }
}
