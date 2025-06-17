package jwtSecurity.example.jwtDemo.Controller;


import jwtSecurity.example.jwtDemo.Model.Role;
import jwtSecurity.example.jwtDemo.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class RoleController {
    @Autowired
    RoleService roleService ;

    @PostMapping(path ="/create")
    public Role creatRole(@RequestBody Role role){
        return roleService.creatRole(role);
    }
    @PutMapping(path ="/update")
    public Role updateRole(@RequestBody Role role){
        return roleService.updateRole(role);
    }
    @GetMapping( path ="/list")
    public java.util.List < Role> listeRole(){
        return roleService.listeRole();
    }
    @DeleteMapping( path ="/delet/{roleId}")
    public String deleteRole(@PathVariable(name="roleId")long roleId){
        roleService.deletRole(roleId);
        return "roledeleted" ;


    }}