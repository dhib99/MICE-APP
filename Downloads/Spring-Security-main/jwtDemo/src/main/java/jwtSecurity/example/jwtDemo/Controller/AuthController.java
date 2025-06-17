package jwtSecurity.example.jwtDemo.Controller;


import jakarta.validation.Valid;
import jwtSecurity.example.jwtDemo.Dto.AuthResponseDto;
import jwtSecurity.example.jwtDemo.Dto.LoginDto;
import jwtSecurity.example.jwtDemo.Dto.SignupRequest;
import jwtSecurity.example.jwtDemo.Service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        authService.signup(signupRequest);
        return new ResponseEntity<>("Utilisateur inscrit avec succès", HttpStatus.CREATED);
    }
    // Build Login REST API
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto){

        //01 - Receive the token from AuthService
        String token = authService.login(loginDto);

        //02 - Set the token as a response using JwtAuthResponse Dto class
        AuthResponseDto authResponseDto = new AuthResponseDto();
        authResponseDto.setAccessToken(token);

        //03 - Return the response to the user
        return new ResponseEntity<>(authResponseDto, HttpStatus.OK);
    }
}
