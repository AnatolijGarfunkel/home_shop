package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.Account;
import project.entity.User;
import project.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private UserService userService;


    @Override
    public Account create(Account account) {
        Account save = repository.save(account);
        return save;
    }

    @Override
    public Account update(Account account) {
        return null;
    }

    @Override
    public Account get() {
        Long currentUserId = userService.getCurrentUserId();
        Account account = repository.findByUserId(currentUserId);
        return account;
    }

    @Override
    public User getCurrentUser() {
        User currentUser = userService.getCurrentUser();
        return currentUser;
    }
}
