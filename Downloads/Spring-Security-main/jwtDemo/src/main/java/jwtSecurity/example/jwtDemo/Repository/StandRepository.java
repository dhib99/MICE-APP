package jwtSecurity.example.jwtDemo.Repository;


import jwtSecurity.example.jwtDemo.Model.Exposition;
import jwtSecurity.example.jwtDemo.Model.Stand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StandRepository extends JpaRepository<Stand, Long> {
    List<Stand> findByExposition(Exposition exposition);

    // ✅ OPTION 2 : si tu veux utiliser un ID, utilise une requête JPQL personnalisée
    @Query("SELECT s FROM Stand s WHERE s.exposition.id = :expositionId")
    List<Stand> findByExpositionId(@Param("expositionId") Long expositionId);
}

