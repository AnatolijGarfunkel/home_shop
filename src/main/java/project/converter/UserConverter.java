package project.converter;

import org.springframework.stereotype.Service;
import project.dto.UserCreateDto;
import project.dto.UserResponseDto;
import project.entity.User;

@Service
public class UserConverter implements Converter<User, UserCreateDto, UserResponseDto> {


    @Override
    public UserResponseDto toDto(User user) {
        UserResponseDto dto = new UserResponseDto(user.getId(), user.getLogin());
        return dto;
    }

    @Override
    public User toEntity(UserCreateDto dto) {
        User user = new User(dto.getLogin(), dto.getPassword());
        return user;
    }
}
