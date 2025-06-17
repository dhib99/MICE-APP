package jwtSecurity.example.jwtDemo.Model;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Salle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;            // Nom de la salle
    private int capacite;          // Capacité de la salle (nombre de personnes)

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)  // Relation de composition avec Equipement
    @JoinColumn(name = "salle_id")  // Lier les équipements à la salle via une clé étrangère
    private List<Equipement> equipements;  // Liste des équipements dans la salle

    private boolean disponible;    // Indique si la salle est disponible

    public Salle() {}

    public Salle(String nom, int capacite, List<Equipement> equipements, boolean disponible) {
        this.nom = nom;
        this.capacite = capacite;
        this.equipements = equipements;
        this.disponible = disponible;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public List<Equipement> getEquipements() {
        return equipements;
    }

    public void setEquipements(List<Equipement> equipements) {
        this.equipements = equipements;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}