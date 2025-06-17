package jwtSecurity.example.jwtDemo.Service.Impl;

import jwtSecurity.example.jwtDemo.Dto.SignupRequest;
import jwtSecurity.example.jwtDemo.Model.Role;
import jwtSecurity.example.jwtDemo.Model.User;
import jwtSecurity.example.jwtDemo.Repository.RoleRepository;
import jwtSecurity.example.jwtDemo.Repository.UserRepository;
import jwtSecurity.example.jwtDemo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service

public class UserServiceimpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;

    @Override
    public User createUser(SignupRequest user) {
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Le mot de passe est requis");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email déjà utilisé !");
        }

        // Récupérer le rôle en fonction du nom de rôle passé en chaîne de caractères
        Set<Role> roles = new HashSet<>();  // Utilisation de HashSet pour garantir un Set


        for (String roleName : user.getRoles()) {
            Role role = roleRepository.findByName(roleName)
                    .orElseThrow(() -> new RuntimeException("Rôle non trouvé : " + roleName));
            roles.add(role);
        }





        User userToSave= new User();
        userToSave.setCin(user.getCin());
        userToSave.setRoles(roles);
        userToSave.setEmail(user.getEmail());
        userToSave.setUsername(user.getUsername());

        userToSave.setLastname(user.getLastname());
        user.setCreationDate(user.getCreationDate());
        user.setModificationDate(user.getModificationDate());
        user.setStatus(user.getStatus());
        userToSave.setPassword(encoder.encode(user.getPassword()));

        return userRepository.save(userToSave);
    }



    @Override
    public User updateUser(User user) {

        return  userRepository.save(user);
    }


    @Override
    public void deletUser(Long userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
        } else {
            // Optionally, throw an exception or handle the "user not found" case
            throw new RuntimeException("User with ID " + userId + " not found.");
        }
    }

    @Override
    public List<User> listeUser() {
        return userRepository.findAll();
    }


    @Override
    public User getUserbyId(long userId) {
        return userRepository.findById(userId).get();
    }
}
