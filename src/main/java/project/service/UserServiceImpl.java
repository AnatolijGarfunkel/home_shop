package project.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import project.entity.Account;
import project.entity.User;
import project.repository.UserRepository;

import java.math.BigDecimal;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private AccountService accountService;


    @Transactional
    @Override
    public User register(User user) {
        User save = repository.save(user);
        Account account = new Account(BigDecimal.valueOf(1000), save);
        accountService.create(account);
        return save;
    }

    @Override
    public User getByLogin(String login) {
        return repository.findByLogin(login);
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            String name = authentication.getName();
            return getByLogin(name);
        }

        return null;
    }

    @Override
    public Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            String name = authentication.getName();
            User userEntity = getByLogin(name);
            return userEntity.getId();
        }
        return null;
    }
}
