package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    private AccountDAO accountDAO;

    public AccountService() {
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public Account loginAccount(Account account) {
        if (account.getUsername().equals("") 
        || account.getPassword().length() < 4) return null;
        return accountDAO.loginAccount(account);
    }

    public Account registerAccount(Account account) {
        if (account.getUsername().equals("") 
        || account.getPassword().length() < 4 
        || findAccountByUsername(account.getUsername()) != null) return null;
        return accountDAO.insertAccount(account);
    }

    public Account findAccountByUsername(String username) {
        return accountDAO.getAccountByUsername(username);
    }
}
