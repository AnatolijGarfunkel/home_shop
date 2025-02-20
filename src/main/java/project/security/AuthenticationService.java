package project.security;


import project.security.model.JwtAuthenticationResponse;
import project.security.model.SignInRequest;

public interface AuthenticationService {

    JwtAuthenticationResponse authenticate(SignInRequest request);
}
