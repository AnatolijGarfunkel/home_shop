package project.service;

import project.entity.Account;
import project.entity.User;

import java.math.BigDecimal;

public interface AccountService {

    Account create(Account account);

    Account update(Account account);

    Account get();

    User getCurrentUser();

    void toPay(Long userId, BigDecimal totalPrice);
}
