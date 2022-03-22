package Repository;

import entity.Account;

import java.util.List;

public interface AccountInterface extends BaseDao<Account, Integer> {

    public List<Account> findByUsername(String username);

    public Account findByUsernameId(String username);
}
