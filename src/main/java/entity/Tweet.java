package entity;

import lombok.*;
import javax.persistence.*;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tweet")
public class Tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @Column(nullable = false , columnDefinition = "varchar(280)")
    private String description;
    @Column(nullable = false, columnDefinition = "date")
    private Date date;


    public Tweet(Account account, String description, Date date) {
        this.account = account;
        this.description = description;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "tweet id=" + id +
                ", account=" + account.getUsername() +
                ", tweet='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}
