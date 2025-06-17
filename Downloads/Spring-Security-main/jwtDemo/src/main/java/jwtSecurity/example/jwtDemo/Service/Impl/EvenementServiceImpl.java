package jwtSecurity.example.jwtDemo.Service.Impl;


import jwtSecurity.example.jwtDemo.Dto.EventDto;
import jwtSecurity.example.jwtDemo.Model.Evenement;
import jwtSecurity.example.jwtDemo.Model.Exposition;
import jwtSecurity.example.jwtDemo.Model.Salle;
import jwtSecurity.example.jwtDemo.Model.User;
import jwtSecurity.example.jwtDemo.Repository.EvenementRepository;
import jwtSecurity.example.jwtDemo.Repository.ExpositionRepository;
import jwtSecurity.example.jwtDemo.Repository.SalleRepository;
import jwtSecurity.example.jwtDemo.Repository.UserRepository;
import jwtSecurity.example.jwtDemo.Service.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@Service
public class EvenementServiceImpl implements EvenementService {
    @Autowired
    private EvenementRepository evenementRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SalleRepository salleRepository;
    @Autowired
    private ExpositionRepository expositionRepository;

    @Override
    public Evenement ajouterEvenement(EventDto eventDto) {
        System.out.println("Salle ID reçu: " + eventDto.getSalleId());
        System.out.println("Exposition ID reçu: " + eventDto.getExpositionId());  // debug

        User user = userRepository.findById(eventDto.getUserId())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        Evenement evenement = new Evenement();
        evenement.setDateFin(LocalDate.parse(eventDto.getDateFin()));
        evenement.setDateDebut(LocalDate.parse(eventDto.getDateDebut()));
        evenement.setNom(eventDto.getNom());

        Long salleId = eventDto.getSalleId();
        if (salleId != null) {
            Salle salle = salleRepository.findById(salleId)
                    .orElseThrow(() -> new RuntimeException("Salle non trouvée"));
            evenement.setSalle(salle);
        }

        Long expositionId = eventDto.getExpositionId();
        if (expositionId != null) {
            Exposition exposition = expositionRepository.findById(expositionId)
                    .orElseThrow(() -> new RuntimeException("Exposition non trouvée"));
            evenement.setExposition(exposition);   // Assure-toi d'avoir ce champ dans Evenement
        }

        evenement.setUser(user);

        return evenementRepository.save(evenement);
    }




   /* @Override
    public Evenement ajouterEvenemente(Long userId , Long expositionId, Evenement evenement) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec id : " + userId));

        // Affecter l'utilisateur et la salle
        evenement.setUser(user);


        // Si expositionId n'est pas null, essayer de récupérer l'exposition
        if (expositionId != null) {
            Exposition exposition = expositionRepository.findById(expositionId)
                    .orElseThrow(() -> new RuntimeException("Exposition non trouvée avec id : " + expositionId));
            evenement.setExposition(exposition);
        } else {
            // Si expositionId est null, on enlève toute exposition associée (optionnel)
            evenement.setExposition(null);
        }

        // Sauvegarder l'événement
        return evenementRepository.save(evenement);
    }
*/

    @Override
    public List<Evenement> getAllEvenements() {
        return evenementRepository.findAll();
    }

}

