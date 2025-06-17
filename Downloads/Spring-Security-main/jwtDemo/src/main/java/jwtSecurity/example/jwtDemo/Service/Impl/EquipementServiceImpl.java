package jwtSecurity.example.jwtDemo.Service.Impl;


import jwtSecurity.example.jwtDemo.Model.Equipement;
import jwtSecurity.example.jwtDemo.Repository.EquipementRepository;
import jwtSecurity.example.jwtDemo.Service.EquipementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipementServiceImpl implements EquipementService {

    private final EquipementRepository equipementRepository;

    @Autowired
    public EquipementServiceImpl(EquipementRepository equipementRepository) {
        this.equipementRepository = equipementRepository;
    }

    @Override
    public Equipement createEquipement(Equipement equipement) {
        return equipementRepository.save(equipement);
    }

    @Override
    public List<Equipement> getAllEquipements() {
        return equipementRepository.findAll();
    }

    @Override
    public Optional<Equipement> getEquipementById(Long id) {
        return equipementRepository.findById(id);
    }

    @Override
    public Equipement updateEquipement(Long id, Equipement equipement) {
        // On peut d'abord vérifier si l'équipement existe avant de le mettre à jour
        if (equipementRepository.existsById(id)) {
            equipement.setId(id); // On s'assure que l'ID est correct
            return equipementRepository.save(equipement);
        }
        return null; // ou une exception, selon vos besoins
    }

    @Override
    public void deleteEquipement(Long id) {
        if (equipementRepository.existsById(id)) {
            equipementRepository.deleteById(id);
        }
    }
}