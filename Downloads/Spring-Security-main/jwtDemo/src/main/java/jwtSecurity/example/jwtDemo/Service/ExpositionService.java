package jwtSecurity.example.jwtDemo.Service;



import jwtSecurity.example.jwtDemo.Model.Exposition;
import jwtSecurity.example.jwtDemo.Model.Stand;

import java.util.List;
import java.util.Optional;

public interface ExpositionService {
    List<Exposition> getAllExpositions();
    Optional<Exposition> getExpositionById(Long id);
    Exposition saveExposition(Exposition exposition);
    void deleteExposition(Long id);
   /* public void ajouterStandAExposition(Long expositionId, Stand stand);*/
}
