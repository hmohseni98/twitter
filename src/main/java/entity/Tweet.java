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
@Table(name = "tweet")
public class Tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @Column(nullable = false , columnDefinition = "varchar(100)")
    private String subject;
    @Column(nullable = false , columnDefinition = "varchar(280)")
    private String description;


    public Tweet(Account account, String subject, String description) {
        this.account = account;
        this.subject = subject;
        this.description = description;
    }
}
