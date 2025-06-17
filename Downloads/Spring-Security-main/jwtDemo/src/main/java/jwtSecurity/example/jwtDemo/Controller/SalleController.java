package jwtSecurity.example.jwtDemo.Controller;


import jwtSecurity.example.jwtDemo.Model.Salle;
import jwtSecurity.example.jwtDemo.Service.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


    @RestController
    @RequestMapping("/api/salles")
    public class SalleController {

        @Autowired
        private SalleService salleService;

        @PostMapping
        public Salle ajouterSalle(@RequestBody Salle salle) {
            return salleService.ajouterSalle(salle);
        }

        @GetMapping("/{id}")
        public Salle obtenirSalleParId(@PathVariable Long id) {
            return salleService.obtenirSalleParId(id);
        }

        @GetMapping
        public List<Salle> listerSalles() {
            return salleService.listerSalles();
        }

        @PutMapping("/{id}")
        public Salle mettreAJourSalle(@PathVariable Long id, @RequestBody Salle salle) {
            return salleService.mettreAJourSalle(id, salle);
        }

        @DeleteMapping("/{id}")
        public void supprimerSalle(@PathVariable Long id) {
            salleService.supprimerSalle(id);
        }
    }

