package project.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.dto.AccountCreateDto;
import project.dto.AccountResponseDto;
import project.dto.UserCreateDto;
import project.dto.UserResponseDto;
import project.entity.Account;
import project.entity.User;

@Component
public class AccountConverter implements Converter<Account, AccountCreateDto, AccountResponseDto> {

    @Autowired
    private Converter<User, UserCreateDto, UserResponseDto> userConverter;


    @Override
    public AccountResponseDto toDto(Account account) {
        User user = account.getUser();
        UserResponseDto userDto = userConverter.toDto(user);
        AccountResponseDto dto = new AccountResponseDto(account.getId(), userDto, account.getAmount());
        return dto;
    }

    @Override
    public Account toEntity(AccountCreateDto dto) {
        return null;
    }
}
