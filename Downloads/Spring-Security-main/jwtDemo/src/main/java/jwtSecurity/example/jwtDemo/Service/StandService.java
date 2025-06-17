package jwtSecurity.example.jwtDemo.Service;



import jwtSecurity.example.jwtDemo.Model.Stand;

import java.util.List;
import java.util.Optional;

public interface StandService {
    List<Stand> getStandsByExposition(Long expositionId);
    Optional<Stand> getStandById(Long id);
    Stand saveStand(Stand stand);
    void deleteStand(Long id);
}
