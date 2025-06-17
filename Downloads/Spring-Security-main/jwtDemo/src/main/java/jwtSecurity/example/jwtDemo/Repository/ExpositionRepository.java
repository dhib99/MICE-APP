package jwtSecurity.example.jwtDemo.Repository;


import jwtSecurity.example.jwtDemo.Model.Exposition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpositionRepository extends JpaRepository<Exposition, Long> {
   // List<Exposition> findByEvenement_Id(Long evenementId);

}
