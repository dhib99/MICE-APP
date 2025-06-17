package jwtSecurity.example.jwtDemo.Model;

import jakarta.persistence.*;

@Entity
public class Stand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private double taille;

    private double x;       // coordonnée X
    private double y;       // coordonnée Y

    private double width;   // largeur
    private double height;  // hauteur
    @Transient
    private Long expositionId;

    // Getters et Setters...
    public Exposition getExposition() {
        return exposition;
    }

    public void setExposition(Exposition exposition) {
        this.exposition = exposition;
    }

    public Long getExpositionId() {
        return expositionId;
    }

    public void setExpositionId(Long expositionId) {
        this.expositionId = expositionId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getTaille() {
        return taille;
    }

    public void setTaille(double taille) {
        this.taille = taille;
    }

    public boolean isDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(boolean disponibilite) {
        this.disponibilite = disponibilite;
    }

    private boolean disponibilite;

    @ManyToOne
    @JoinColumn(name = "exposition_id", nullable = false)
    private Exposition exposition;



}