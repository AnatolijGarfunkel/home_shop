package project.service;

import project.entity.Account;
import project.entity.User;

public interface AccountService {

    Account create(Account account);

    Account update(Account account);

    Account get();

    User getCurrentUser();
}
