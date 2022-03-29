package repository.lmpl;


import repository.infc.AccountInterface;
import entity.Account;
import lombok.var;

import java.util.List;

public class AccountRepository extends BaseRepositoryImpl<Account, Integer> implements AccountInterface {

    @Override
    public List<Account> findByUsername(String username) {
        try (var session = sessionFactory.openSession()) {
            String sql = "select * from account " +
                    "where username like :username ";
            var query = session.createNativeQuery(sql, Account.class);
            query.setParameter("username", username + "%");
            return query.getResultList();
        }
    }

    @Override
    public Account findByUsernameId(String username) {
        try (var session = sessionFactory.openSession()) {
            String sql = "select * from account " +
                    "where username = :username ";
            var query = session.createNativeQuery(sql, Account.class);
            query.setParameter("username", username );
            return query.getSingleResult();
        }
    }
}
