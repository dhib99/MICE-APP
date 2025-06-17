package jwtSecurity.example.jwtDemo.Service;



import jwtSecurity.example.jwtDemo.Dto.EventDto;
import jwtSecurity.example.jwtDemo.Model.Evenement;

import java.util.List;

public interface EvenementService {
    public Evenement ajouterEvenement(EventDto eventDto);
    //Evenement ajouterEvenement(Long userId, Long salleId, Evenement evenement);


   // Evenement ajouterEvenemente(Long userId, Long expositionId, Evenement evenement);

    List<Evenement> getAllEvenements();
}
