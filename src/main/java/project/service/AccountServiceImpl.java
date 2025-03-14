package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.Account;
import project.entity.User;
import project.repository.AccountRepository;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private UserService userService;


    @Override
    public void toPay(Long userId, BigDecimal totalPrice) {
        Account account = repository.findByUserId(userId);
        BigDecimal subtract = account.getAmount().subtract(totalPrice);
        account.setAmount(subtract);
        repository.save(account);
    }

    @Override
    public Account create(Account account) {
        return repository.save(account);
    }

    @Override
    public Account update(Account account) {
        return null;
    }

    @Override
    public Account get() {
        Long currentUserId = userService.getCurrentUserId();
        return repository.findByUserId(currentUserId);
    }

    @Override
    public User getCurrentUser() {
        return userService.getCurrentUser();
    }

}
