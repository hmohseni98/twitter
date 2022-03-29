package service;

import repository.lmpl.AccountRepository;
import entity.Account;

import java.util.List;

public class AccountService extends BaseService<AccountRepository, Account, Integer> {

    private final AccountRepository accountRepository = new AccountRepository();

    public AccountService() {
        super(new AccountRepository(), Account.class);
    }

    public List<Account> findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    public Account findByUsernameId(String username) {
        return accountRepository.findByUsernameId(username);
    }

        public Account login(String username, String password) {
        return accountRepository.findAll(Account.class)
                .stream()
                .filter(account -> account.getUsername().equals(username) && account.getPassword().equals(password))
                .findFirst()
                .get();
    }
}
