package jwtSecurity.example.jwtDemo.Service;


import jwtSecurity.example.jwtDemo.Model.Role;

public interface RoleService {
    public Role creatRole(Role role );
    public Role updateRole(Role role );
    public Role deletRole(long roleId );
    public java.util.List  <Role> listeRole();
}
