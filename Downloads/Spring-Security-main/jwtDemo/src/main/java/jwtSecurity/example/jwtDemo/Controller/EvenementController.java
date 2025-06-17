package jwtSecurity.example.jwtDemo.Controller;

import jwtSecurity.example.jwtDemo.Dto.EventDto;
import jwtSecurity.example.jwtDemo.Model.Evenement;
import jwtSecurity.example.jwtDemo.Model.User;
import jwtSecurity.example.jwtDemo.Repository.UserRepository;
import jwtSecurity.example.jwtDemo.Service.EvenementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/evenements")
public class EvenementController {
    private static final Logger logger = LoggerFactory.getLogger(EvenementController.class);

    @Autowired
    private EvenementService evenementService;

    @PostMapping("/add")
    public EventDto ajouterEvenement(@RequestBody EventDto eventDto) {

            Evenement saved = evenementService.ajouterEvenement(eventDto);
            return eventDto;

    }

    @GetMapping
    public List<Evenement> getAllEvenements() {
        return evenementService.getAllEvenements();
    }

}


    // @PostMapping("/{userId}//{expositionId}")
    //public Evenement ajouterEvenemente(@PathVariable Long userId,   @PathVariable(required = false) Long expositionId, @RequestBody Evenement evenement) {
       // return evenementService.ajouterEvenement(userId,expositionId, evenement);

    //@PostMapping("/{userId}/{salleId}/{equipmentId}")
   // public Evenement ajouterEvenement(
           // @PathVariable Long userId,
          //  @PathVariable Long salleId,
           // @PathVariable Long equipmentId,
           // @RequestBody Evenement evenement) {

      //  return evenementService.ajouterEvenement(userId, salleId, equipmentId, evenement);
   // }
   // @GetMapping("/salle/{salleId}")
   // public List<Evenement> getEvenementsParSalle(@PathVariable Long salleId) {
  //      return evenementService.getEvenementsParSalle(salleId);
    //}