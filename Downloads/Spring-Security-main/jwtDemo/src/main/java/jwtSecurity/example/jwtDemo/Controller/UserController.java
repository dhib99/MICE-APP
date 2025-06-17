package jwtSecurity.example.jwtDemo.Controller;


import jwtSecurity.example.jwtDemo.Dto.DeleteResponse;
import jwtSecurity.example.jwtDemo.Dto.SignupRequest;
import jwtSecurity.example.jwtDemo.Model.User;
import jwtSecurity.example.jwtDemo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {
    @Autowired
    UserService userService ;


    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody Map<String, Object> request) {
        String email = (String) request.get("email");
        String password = (String) request.get("password");
        String roleName = (String) request.get("roleName");

        // Création de l'utilisateur
        SignupRequest user = new SignupRequest();
        user.setEmail(email);
        user.setPassword(password);

        try {
            // Appel du service pour créer l'utilisateur et affecter le rôle
            User createdUser = userService.createUser(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            // Gestion du cas où le mot de passe est manquant
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            // Gestion du cas où l'email est déjà utilisé ou le rôle est introuvable
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping(path ="/user/update/{userId}")
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }
    @GetMapping( path ="/user/list")
    public java.util.List < User> listeUser(){
        return userService.listeUser();
    }
    @GetMapping( path ="/user/list/{userId}")
    public User getUserbyId(@PathVariable(name="userId")Long userId){
        return userService.getUserbyId(userId);

    }
    @DeleteMapping(path = "/user/delete/{userId}")
    public ResponseEntity<DeleteResponse> deleteUser(@PathVariable("userId") Long userId) {
        userService.deletUser(userId);  // Note le nom correct
        DeleteResponse response = new DeleteResponse();
        response.setResponse("user deleted");
        return ResponseEntity.ok(response);
    }
}