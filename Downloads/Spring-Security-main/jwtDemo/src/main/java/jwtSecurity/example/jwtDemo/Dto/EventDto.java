package jwtSecurity.example.jwtDemo.Dto;

import java.time.LocalDateTime;

public class EventDto {
   // private Long id;
    private String nom;
    private String dateDebut;
    private String dateFin;
    private Long userId;
    private Long salleId;
    private Long equipementId;
    private Long expositionId;

    public Long getEquipementId() {
        return equipementId;
    }

    public void setEquipementId(Long equipementId) {
        this.equipementId = equipementId;
    }

    // Constructors
    public EventDto() {}

    public EventDto(Long id, String nom, String dateDebut, String dateFin,
                    Long userId, Long salleId, Long expositionId,long equipementId) {
       // this.id = id;
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.userId = userId;
        this.salleId = salleId;
        this.equipementId = equipementId;
        this.expositionId = expositionId;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }
// Getters and Setters
   // public Long getId() { return id; }
   // public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

   /* public LocalDateTime getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDateTime dateDebut) { this.dateDebut = dateDebut; }

    public LocalDateTime getDateFin() { return dateFin; }
    public void setDateFin(LocalDateTime dateFin) { this.dateFin = dateFin; }
*/
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getSalleId() { return salleId; }

    public void setSalleId(Long salleId) {
        this.salleId = salleId;
    }

    public Long getExpositionId() { return expositionId; }
    public void setExpositionId(Long expositionId) { this.expositionId = expositionId; }
    @Override
    public String toString() {
        return "EventDto{" +
                "nom='" + nom + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", userId=" + userId +
                ", salleId=" + salleId +
                ", expositionId=" + expositionId +
                '}';
    }

}
