package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.converter.Converter;
import project.dto.AccountCreateDto;
import project.dto.AccountResponseDto;
import project.entity.Account;
import project.entity.User;
import project.service.AccountService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService service;

    @Autowired
    private Converter<Account, AccountCreateDto, AccountResponseDto> converter;


    @PostMapping
    public ResponseEntity<AccountResponseDto> create() {
        User currentUser = service.getCurrentUser();
        Account account = service.create(new Account(BigDecimal.valueOf(1000), currentUser));
        AccountResponseDto dto = converter.toDto(account);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<AccountResponseDto> getFromCurrentUser() {
        Account account = service.get();
        AccountResponseDto dto = converter.toDto(account);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
