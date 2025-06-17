package jwtSecurity.example.jwtDemo.Repository;


import jwtSecurity.example.jwtDemo.Model.Salle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalleRepository extends JpaRepository<Salle, Long> {
}