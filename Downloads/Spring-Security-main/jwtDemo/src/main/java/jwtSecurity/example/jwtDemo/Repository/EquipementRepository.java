package jwtSecurity.example.jwtDemo.Repository;


import jwtSecurity.example.jwtDemo.Model.Equipement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipementRepository extends JpaRepository<Equipement, Long> {
}
