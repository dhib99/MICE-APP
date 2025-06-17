package jwtSecurity.example.jwtDemo.Service;



import jwtSecurity.example.jwtDemo.Model.Salle;

import java.util.List;

public interface SalleService {
    Salle ajouterSalle(Salle salle);
    Salle obtenirSalleParId(Long id);
    List<Salle> listerSalles();
    Salle mettreAJourSalle(Long id, Salle salle);
    void supprimerSalle(Long id);
}
