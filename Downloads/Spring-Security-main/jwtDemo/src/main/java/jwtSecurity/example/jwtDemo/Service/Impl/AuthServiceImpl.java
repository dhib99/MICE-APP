package jwtSecurity.example.jwtDemo.Service.Impl;

import jwtSecurity.example.jwtDemo.Config.JwtTokenProvider;
import jwtSecurity.example.jwtDemo.Dto.LoginDto;
import jwtSecurity.example.jwtDemo.Dto.SignupRequest;
import jwtSecurity.example.jwtDemo.Model.Role;
import jwtSecurity.example.jwtDemo.Model.User;
import jwtSecurity.example.jwtDemo.Repository.RoleRepository;
import jwtSecurity.example.jwtDemo.Repository.UserRepository;
import jwtSecurity.example.jwtDemo.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired

    private  UserRepository userRepository;
    @Autowired

    private  RoleRepository roleRepository;
    @Autowired

    private  PasswordEncoder encoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @Override
    public void signup(SignupRequest signupRequest) {
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new RuntimeException("Email déjà utilisé !");
        }

        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setEmail(signupRequest.getEmail());

        user.setCin(signupRequest.getCin());
        user.setLastname(signupRequest.getLastname());

        // Si création/modification pas fournis côté client, on met la date courante
        user.setCreationDate(signupRequest.getCreationDate() != null ? signupRequest.getCreationDate() : LocalDateTime.now());
        user.setModificationDate(signupRequest.getModificationDate() != null ? signupRequest.getModificationDate() : LocalDateTime.now());

        user.setStatus(signupRequest.getStatus());
        user.setPassword(encoder.encode(signupRequest.getPassword()));

        Set<Role> roles = new HashSet<>();
        Set<String> roleStrings = signupRequest.getRoles();

        if (roleStrings == null || roleStrings.isEmpty()) {
            Role userRole = roleRepository.findByName("ROLE_USER")
                    .orElseThrow(() -> new RuntimeException("Rôle ROLE_USER non trouvé"));
            roles.add(userRole);
        } else {
            for (String roleStr : roleStrings) {
                Role role = roleRepository.findByName(roleStr)
                        .orElseThrow(() -> new RuntimeException("Rôle non trouvé : " + roleStr));
                roles.add(role);
            }
        }

        user.setRoles(roles);
        userRepository.save(user);
    }



    @Override
    public String login(LoginDto loginDto) {

        // 01 - AuthenticationManager is used to authenticate the user
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),
                loginDto.getPassword()
        ));

        /* 02 - SecurityContextHolder is used to allows the rest of the application to know
        that the user is authenticated and can use user data from Authentication object */
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 03 - Generate the token based on username and secret key
        String token = jwtTokenProvider.generateToken(authentication);

        // 04 - Return the token to controller
        return token;
    }
}
