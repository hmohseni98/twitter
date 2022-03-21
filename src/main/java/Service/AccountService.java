package Service;

import CustomException.AccountDoesNotExist;
import CustomException.RecordDoesNotExist;
import Repository.AccountRepository;
import entity.Account;

public class AccountService extends BaseService<AccountRepository, Account, Integer> {

    private final AccountRepository accountRepository = new AccountRepository();

    public AccountService() {
        super(new AccountRepository() , Account.class);
    }

    public Account findByUsername(String username) {
        return accountRepository.findAll(Account.class)
                .stream()
                .filter(account -> account.getUsername().equals(username))
                .findFirst()
                .get();
    }

    public Account login(String username, String password) {
        return accountRepository.findAll(Account.class)
                .stream()
                .filter(account -> account.getUsername().equals(username) && account.getPassword().equals(password))
                .findFirst()
                .get();
    }
}
