package jwtSecurity.example.jwtDemo.Model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Exposition {

    public enum ExpositionType {
        TYPE1,
        TYPE2,
        TYPE3
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @Enumerated(EnumType.STRING)
    private ExpositionType type;   // type d'exposition

    private Integer maxStands;     // limite max stands (nullable)

   /* @OneToMany(mappedBy = "exposition", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Stand> stands = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "evenement_id", nullable = true)
    private Evenement evenement;*/

    // Getters & Setters

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

    public ExpositionType getType() {
        return type;
    }

    public void setType(ExpositionType type) {
        this.type = type;
    }

    public Integer getMaxStands() {
        return maxStands;
    }

    public void setMaxStands(Integer maxStands) {
        this.maxStands = maxStands;
    }
/*
    public List<Stand> getStands() {
        return stands;
    }

    public void setStands(List<Stand> stands) {
        this.stands = stands;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }*/
}
