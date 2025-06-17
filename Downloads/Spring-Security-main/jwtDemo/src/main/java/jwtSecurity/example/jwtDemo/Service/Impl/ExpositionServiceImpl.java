package jwtSecurity.example.jwtDemo.Service.Impl;


import jwtSecurity.example.jwtDemo.Model.Exposition;
import jwtSecurity.example.jwtDemo.Model.Stand;
import jwtSecurity.example.jwtDemo.Repository.ExpositionRepository;
import jwtSecurity.example.jwtDemo.Service.ExpositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpositionServiceImpl implements ExpositionService {

    @Autowired
    private ExpositionRepository expositionRepository;

    @Override
    public List<Exposition> getAllExpositions() {
        return expositionRepository.findAll();
    }

    @Override
    public Optional<Exposition> getExpositionById(Long id) {
        return expositionRepository.findById(id);
    }

    @Override
    public Exposition saveExposition(Exposition exposition) {
        return expositionRepository.save(exposition);
    }

    @Override
    public void deleteExposition(Long id) {
        expositionRepository.deleteById(id);
    }
    /*public void ajouterStandAExposition(Long expositionId, Stand stand) {
        Exposition exposition = expositionRepository.findById(expositionId)
                .orElseThrow(() -> new RuntimeException("Exposition non trouvée"));

        if (exposition.getType() == Exposition.ExpositionType.TYPE3) {
            //int nombreStandsActuels = exposition.getStands().size();
           // if (exposition.getMaxStands() != null && nombreStandsActuels >= exposition.getMaxStands()) {
                throw new RuntimeException("Limite de stands atteinte pour cette exposition");
            }
        }

        stand.setExposition(exposition);
       // exposition.getStands().add(stand);
        expositionRepository.save(exposition);
    }
*/
}

