package jwtSecurity.example.jwtDemo.Service;

import jwtSecurity.example.jwtDemo.Dto.LoginDto;
import jwtSecurity.example.jwtDemo.Dto.SignupRequest;

public interface AuthService {
    String login(LoginDto loginDto);
    void signup(SignupRequest signupRequest);

}