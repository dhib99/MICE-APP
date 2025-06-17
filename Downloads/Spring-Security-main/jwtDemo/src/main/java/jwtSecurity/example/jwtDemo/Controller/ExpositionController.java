package jwtSecurity.example.jwtDemo.Controller;

import jwtSecurity.example.jwtDemo.Model.Exposition;
import jwtSecurity.example.jwtDemo.Repository.ExpositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/expositions")
@CrossOrigin(origins = "http://localhost:4200")
public class ExpositionController {

    @Autowired
    private ExpositionRepository expositionRepository;

    // Récupérer toutes les expositions
    @GetMapping
    public List<Exposition> getAllExpositions() {
        return expositionRepository.findAll();
    }

    // Récupérer une exposition par id
    @GetMapping("/{id}")
    public ResponseEntity<Exposition> getExpositionById(@PathVariable Long id) {
        Optional<Exposition> exposition = expositionRepository.findById(id);
        if (exposition.isPresent()) {
            return ResponseEntity.ok(exposition.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Créer une nouvelle exposition
    @PostMapping
    public Exposition createExposition(@RequestBody Exposition exposition) {
        return expositionRepository.save(exposition);
    }



    // Supprimer une exposition
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExposition(@PathVariable Long id) {
        if (expositionRepository.existsById(id)) {
            expositionRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
