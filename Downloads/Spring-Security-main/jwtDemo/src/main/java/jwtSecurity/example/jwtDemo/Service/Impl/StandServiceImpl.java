package jwtSecurity.example.jwtDemo.Service.Impl;


import jwtSecurity.example.jwtDemo.Model.Exposition;
import jwtSecurity.example.jwtDemo.Model.Stand;
import jwtSecurity.example.jwtDemo.Repository.ExpositionRepository;
import jwtSecurity.example.jwtDemo.Repository.StandRepository;
import jwtSecurity.example.jwtDemo.Service.StandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class StandServiceImpl implements StandService {

    @Autowired
    private StandRepository standRepository;
    @Autowired
    private ExpositionRepository expositionRepository;

    public Stand saveStand(Stand stand) {
        if (stand.getExpositionId() != null) {
            Exposition exposition = expositionRepository.findById(stand.getExpositionId())
                    .orElseThrow(() -> new RuntimeException("Exposition introuvable"));
            stand.setExposition(exposition);
        }
        return standRepository.save(stand);
    }


    @Override
    public List<Stand> getStandsByExposition(Long expositionId) {
        return standRepository.findByExpositionId(expositionId);
    }

    @Override
    public Optional<Stand> getStandById(Long id) {
        return standRepository.findById(id);
    }

   // @Override
   // public Stand saveStand(Stand stand) {
       // return standRepository.save(stand);
   // }

    @Override
    public void deleteStand(Long id) {
        standRepository.deleteById(id);
    }
}