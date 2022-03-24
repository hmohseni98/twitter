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
    @Column(nullable = false, columnDefinition = "varchar(280)")
    private String description;
    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment reply;

    public Comment(Account account, Tweet tweet, String description) {
        this.account = account;
        this.tweet = tweet;
        this.description = description;
    }

    public Comment(Account account, Tweet tweet, String description, Comment reply) {
        this.account = account;
        this.tweet = tweet;
        this.description = description;
        this.reply = reply;
    }

    @Override
    public String toString() {
        try {
            return "Comment{" +
                    "id=" + id +
                    ", account=" + account.getUsername() +
                    ", tweet id=" + tweet.getId() +
                    ", tweet=" + tweet.getDescription() +
                    ", comment ='" + description + '\'' +
                    ", reply id=" + reply.getId() +
                    ", reply account=" + reply.getAccount().getUsername() +
                    ", reply comment=" + reply.description +
                    '}';
        } catch (NullPointerException e){
            return "Comment{" +
                    "id=" + id +
                    ", account=" + account.getUsername() +
                    ", tweet id=" + tweet.getId() +
                    ", tweet=" + tweet.getDescription() +
                    ", comment='" + description + '\'' +
                    ", reply=" + reply +
                    '}';
        }
    }
}
