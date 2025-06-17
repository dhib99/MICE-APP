package jwtSecurity.example.jwtDemo.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Evenement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom; // Nouveau champ

    private LocalDate dateDebut;
    private LocalDate dateFin;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "salle_id")
    private Salle salle;

    @ManyToOne
    @JoinColumn(name = "exposition_id")
    private Exposition exposition;


    // Constructeurs, Getters & Setters
    public Evenement() {}

    public Evenement(String nom, LocalDate dateDebut, LocalDate dateFin, User user, Salle salle) {
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.user = user;
        this.salle = salle;
    }

    public Long getId() { return id; }
    public String getNom() { return nom; } // Getter pour `nom`
    public LocalDate getDateDebut() { return dateDebut; }
    public LocalDate getDateFin() { return dateFin; }
    public User getUser() { return user; }
    public Salle getSalle() { return salle; }
    public Exposition getExposition() {
        return exposition;
    }

    public void setExposition(Exposition exposition) {
        this.exposition = exposition;
    }

    public void setId(Long id) { this.id = id; }
    public void setNom(String nom) { this.nom = nom; } // Setter pour `nom`
    public void setDateDebut(LocalDate dateDebut) { this.dateDebut = dateDebut; }
    public void setDateFin(LocalDate dateFin) { this.dateFin = dateFin; }
    public void setUser(User user) { this.user = user; }
    public void setSalle(Salle salle) { this.salle = salle; }
}