package com.gestionconsultations.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gestionconsultations.dto.RendezVousRequest;
import com.gestionconsultations.entity.Patient;
import com.gestionconsultations.entity.Medecin;
import com.gestionconsultations.entity.Salle;
import com.gestionconsultations.entity.RendezVous;
import com.gestionconsultations.repository.PatientRepository;
import com.gestionconsultations.repository.MedecinRepository;
import com.gestionconsultations.repository.SalleRepository;
import com.gestionconsultations.repository.RendezVousRepository;
import com.gestionconsultations.constants.StatutRendezVous;

/**
 * Service pour la gestion des consultations.
 */
@Service
public class ConsultationService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private MedecinRepository medecinRepository;

    @Autowired
    private SalleRepository salleRepository;

    @Autowired
    private RendezVousRepository rendezVousRepository;

    /**
     * Crée un nouveau rendez-vous à partir d'une requête.
     *
     * @param request DTO contenant les informations du rendez-vous
     * @return Le rendez-vous créé
     * @throws IllegalArgumentException si les données sont invalides
     */
    public RendezVous creerRendezVous(RendezVousRequest request) {
        validerRequete(request);
        
        Patient patient = trouverPatient(request.getPatientId());
        Medecin medecin = trouverMedecin(request.getMedecinId());
        Salle salle = trouverSalle(request.getSalleId());

        RendezVous rdv = creerRendezVous(patient, medecin, salle, request);
        return rendezVousRepository.save(rdv);
    }

    /**
     * Valide les données de la requête.
     */
    private void validerRequete(RendezVousRequest request) {
        if (request.getPatientId() == null || request.getMedecinId() == null) {
            throw new IllegalArgumentException("Les IDs patient et médecin sont requis");
        }
    }

    /**
     * Trouve un patient par son ID.
     */
    private Patient trouverPatient(Long patientId) {
        return patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Patient introuvable"));
    }

    /**
     * Trouve un médecin par son ID.
     */
    private Medecin trouverMedecin(Long medecinId) {
        return medecinRepository.findById(medecinId)
                .orElseThrow(() -> new IllegalArgumentException("Médecin introuvable"));
    }

    /**
     * Trouve une salle par son ID.
     */
    private Salle trouverSalle(Long salleId) {
        return salleRepository.findById(salleId)
                .orElseThrow(() -> new IllegalArgumentException("Salle introuvable"));
    }

    /**
     * Crée un objet RendezVous à partir des entités et de la requête.
     */
    private RendezVous creerRendezVous(Patient patient, Medecin medecin, 
                                       Salle salle, RendezVousRequest request) {
        RendezVous rdv = new RendezVous();
        rdv.setPatient(patient);
        rdv.setMedecin(medecin);
        rdv.setSalle(salle);
        rdv.setDateRdv(request.getDate());
        rdv.setHeureDebut(request.getHeureDebut());
        rdv.setHeureFin(request.getHeureFin());
        rdv.setMotifConsultation(request.getMotif());
        rdv.setStatut(StatutRendezVous.PLANIFIE);
        return rdv;
    }

    /**
     * Récupère tous les rendez-vous planifiés pour un médecin.
     *
     * @param medecinId L'identifiant du médecin
     * @return La liste des rendez-vous planifiés
     */
    public List<RendezVous> getRendezVousPlanifiesByMedecin(Long medecinId) {
        return rendezVousRepository.findAll().stream()
                .filter(rdv -> estRendezVousPlanifiePourMedecin(rdv, medecinId))
                .collect(Collectors.toList());
    }

    /**
     * Vérifie si un rendez-vous est planifié pour un médecin donné.
     */
    private boolean estRendezVousPlanifiePourMedecin(RendezVous rdv, Long medecinId) {
        return rdv.getMedecin() != null 
                && rdv.getMedecin().getId().equals(medecinId)
                && rdv.getDateRdv() != null
                && StatutRendezVous.PLANIFIE.equals(rdv.getStatut());
    }
}

