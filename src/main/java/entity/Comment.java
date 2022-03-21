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
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @ManyToOne
    @JoinColumn(name = "tweet_id")
    private Tweet tweet;
    @Column (nullable = false , columnDefinition = "varchar(280)")
    private String description;

    public Comment(Account account, Tweet tweet, String description) {
        this.account = account;
        this.tweet = tweet;
        this.description = description;
    }
}
