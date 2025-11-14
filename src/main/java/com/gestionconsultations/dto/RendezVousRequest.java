package com.gestionconsultations.dto;

import jakarta.validation.constraints.NotNull;

/**
 * DTO pour la création d'un rendez-vous.
 */
public class RendezVousRequest {

    @NotNull(message = "L'ID du patient est requis")
    private Long patientId;

    @NotNull(message = "L'ID du médecin est requis")
    private Long medecinId;

    @NotNull(message = "L'ID de la salle est requis")
    private Long salleId;

    @NotNull(message = "La date est requise")
    private String date;

    @NotNull(message = "L'heure de début est requise")
    private String heureDebut;

    @NotNull(message = "L'heure de fin est requise")
    private String heureFin;

    @NotNull(message = "Le motif est requis")
    private String motif;

    // Getters et Setters
    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getMedecinId() {
        return medecinId;
    }

    public void setMedecinId(Long medecinId) {
        this.medecinId = medecinId;
    }

    public Long getSalleId() {
        return salleId;
    }

    public void setSalleId(Long salleId) {
        this.salleId = salleId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    public String getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }
}

