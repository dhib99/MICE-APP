package jwtSecurity.example.jwtDemo.Service.Impl;


import jwtSecurity.example.jwtDemo.Model.Role;
import jwtSecurity.example.jwtDemo.Repository.RoleRepository;
import jwtSecurity.example.jwtDemo.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role creatRole(Role role) {

        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(Role role) {

        return  roleRepository.save(role);
    }

    @Override
    public Role deletRole(long roleId) {
        Role role =roleRepository.findById(roleId).get();
        roleRepository.delete(role);
        return role;
    }


    @Override
    public List<Role> listeRole() {
        return roleRepository.findAll();
    }



}