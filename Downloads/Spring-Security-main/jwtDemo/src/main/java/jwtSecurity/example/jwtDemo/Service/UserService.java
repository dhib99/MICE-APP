package jwtSecurity.example.jwtDemo.Service;


import jwtSecurity.example.jwtDemo.Dto.SignupRequest;
import jwtSecurity.example.jwtDemo.Model.User;

public interface UserService {
    User createUser(SignupRequest user);
    public User updateUser(User user );
    public void deletUser(Long usertodelete );
    public java.util.List  <User> listeUser();
    public User getUserbyId(long userId);
}
