package jwtSecurity.example.jwtDemo.Repository;


import jwtSecurity.example.jwtDemo.Model.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvenementRepository extends JpaRepository<Evenement, Long> {
}
