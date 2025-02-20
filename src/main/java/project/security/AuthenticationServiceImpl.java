package project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import project.security.model.JwtAuthenticationResponse;
import project.security.model.SignInRequest;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UserDetailsService service;

    @Autowired
    private JwtService jwtService;

    @Override
    public JwtAuthenticationResponse authenticate(SignInRequest request) {
        manager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.login(),
                                request.password()
                        )
                );

        UserDetails userDetails = service.loadUserByUsername(request.login());
        String token = jwtService.generateToken(userDetails);
        return new JwtAuthenticationResponse(token);
    }
}
