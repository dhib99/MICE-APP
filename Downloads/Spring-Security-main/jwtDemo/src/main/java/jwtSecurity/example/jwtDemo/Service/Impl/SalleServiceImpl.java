package jwtSecurity.example.jwtDemo.Service.Impl;



import jwtSecurity.example.jwtDemo.Model.Salle;
import jwtSecurity.example.jwtDemo.Repository.SalleRepository;
import jwtSecurity.example.jwtDemo.Service.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalleServiceImpl implements SalleService {

    @Autowired
    private SalleRepository salleRepository;

    @Override
    public Salle ajouterSalle(Salle salle) {
        return salleRepository.save(salle);
    }

    @Override
    public Salle obtenirSalleParId(Long id) {
        return salleRepository.findById(id).orElseThrow(() -> new RuntimeException("Salle non trouvée"));
    }

    @Override
    public List<Salle> listerSalles() {
        return salleRepository.findAll();
    }

    @Override
    public Salle mettreAJourSalle(Long id, Salle salle) {
        if (!salleRepository.existsById(id)) {
            throw new RuntimeException("Salle non trouvée");
        }
        salle.setId(id);
        return salleRepository.save(salle);
    }

    @Override
    public void supprimerSalle(Long id) {
        if (!salleRepository.existsById(id)) {
            throw new RuntimeException("Salle non trouvée");
        }
        salleRepository.deleteById(id);
    }
}
