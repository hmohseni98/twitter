package repository.infc;

import entity.Account;
import repository.base.BaseRepository;

import java.util.List;

public interface AccountInterface extends BaseRepository<Account, Integer> {

    public List<Account> findByUsername(String username);

    public Account findByUsernameId(String username);
}
