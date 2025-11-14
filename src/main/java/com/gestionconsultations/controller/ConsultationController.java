package com.gestionconsultations.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gestionconsultations.dto.RendezVousRequest;
import com.gestionconsultations.entity.RendezVous;
import com.gestionconsultations.service.ConsultationService;

/**
 * Contrôleur REST pour la gestion des consultations.
 */
@RestController
@RequestMapping("/api")
public class ConsultationController {

    @Autowired
    private ConsultationService consultationService;

    /**
     * Crée un nouveau rendez-vous.
     *
     * @param request DTO contenant les informations du rendez-vous
     * @return Le rendez-vous créé ou null en cas d'erreur
     */
    @PostMapping("/rendezvous")
    public ResponseEntity<RendezVous> creerRendezVous(@RequestBody RendezVousRequest request) {
        try {
            RendezVous rdv = consultationService.creerRendezVous(request);
            if (rdv != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(rdv);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Récupère tous les rendez-vous planifiés pour un médecin donné.
     *
     * @param medecinId L'identifiant du médecin
     * @return La liste des rendez-vous planifiés
     */
    @GetMapping("/medecins/{medecinId}/rendezvous")
    public List<RendezVous> getRendezVousByMedecin(@PathVariable Long medecinId) {
        return consultationService.getRendezVousPlanifiesByMedecin(medecinId);
    }
}
