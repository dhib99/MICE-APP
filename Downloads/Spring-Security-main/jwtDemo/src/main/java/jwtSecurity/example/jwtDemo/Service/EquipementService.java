package jwtSecurity.example.jwtDemo.Service;



import jwtSecurity.example.jwtDemo.Model.Equipement;

import java.util.List;
import java.util.Optional;

public interface EquipementService {
    // Créer un équipement
    Equipement createEquipement(Equipement equipement);

    // Récupérer tous les équipements
    List<Equipement> getAllEquipements();

    // Récupérer un équipement par son ID
    Optional<Equipement> getEquipementById(Long id);

    // Mettre à jour un équipement
    Equipement updateEquipement(Long id, Equipement equipement);

    // Supprimer un équipement par son ID
    void deleteEquipement(Long id);

}
