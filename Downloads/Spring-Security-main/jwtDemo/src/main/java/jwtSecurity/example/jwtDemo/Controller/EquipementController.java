package jwtSecurity.example.jwtDemo.Controller;

import jwtSecurity.example.jwtDemo.Model.Equipement;
import jwtSecurity.example.jwtDemo.Service.EquipementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/equipements")
public class EquipementController {

    private final EquipementService equipementService;

    // Injection du service via le constructeur
    @Autowired
    public EquipementController(EquipementService equipementService) {
        this.equipementService = equipementService;
    }

    // Méthode pour obtenir tous les équipements
    @GetMapping
    public List<Equipement> getAllEquipements() {
        return equipementService.getAllEquipements();
    }

    // Méthode pour obtenir un équipement par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Equipement> getEquipementById(@PathVariable Long id) {
        Optional<Equipement> equipement = equipementService.getEquipementById(id);
        if (equipement.isPresent()) {
            return ResponseEntity.ok(equipement.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Méthode pour ajouter un nouvel équipement
    @PostMapping
    public ResponseEntity<Equipement> createEquipement(@RequestBody Equipement equipement) {
        Equipement createdEquipement = equipementService.createEquipement(equipement);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEquipement);
    }

    // Méthode pour mettre à jour un équipement existant
    @PutMapping("/{id}")
    public ResponseEntity<Equipement> updateEquipement(@PathVariable Long id, @RequestBody Equipement equipement) {
        Equipement updatedEquipement = equipementService.updateEquipement(id, equipement);
        if (updatedEquipement != null) {
            return ResponseEntity.ok(updatedEquipement);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Méthode pour supprimer un équipement
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipement(@PathVariable Long id) {
        equipementService.deleteEquipement(id);
        return ResponseEntity.noContent().build(); // Retourne un status 204 (No Content)
    }
}