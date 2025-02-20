package project.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import project.converter.Converter;
import project.dto.UserCreateDto;
import project.dto.UserResponseDto;
import project.entity.User;
import project.security.AuthenticationService;
import project.security.model.JwtAuthenticationResponse;
import project.security.model.SignInRequest;
import project.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private Converter<User, UserCreateDto, UserResponseDto> converter;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private PasswordEncoder encoder;




    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody @Valid UserCreateDto dto) {
        User entity = converter.toEntity(dto);
        String password = dto.getPassword();
        String encode = encoder.encode(password);
        entity.setPassword(encode);
        User register = service.register(entity);
        UserResponseDto responseDto = converter.toDto(register);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @Operation(summary = "User login", description = "вход пользователя в систему через имя и пароль")
    public JwtAuthenticationResponse login(@RequestBody SignInRequest request) {
        return authenticationService.authenticate(request);
    }

    @GetMapping
    public ResponseEntity<UserResponseDto> getCurrentUser() {
        User currentUser = service.getCurrentUser();
        UserResponseDto responseDto = converter.toDto(currentUser);
        //new ResponseEntity<>(responseDto, HttpStatus.OK);
        return ResponseEntity.ok(responseDto);
    }
}







































