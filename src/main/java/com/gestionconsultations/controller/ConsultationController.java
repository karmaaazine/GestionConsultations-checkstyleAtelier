package com.gestionconsultations.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ConsultationController {

 @Autowired
 private ConsultationService consultationService;

 private final int MAX_SIZE=100; // Violation: constante mal nommée

 // Violation: méthode trop longue et trop de paramètres
 @PostMapping("/rendezvous")
 public RendezVous creerRendezVous(
 @RequestParam Long patientId,
 @RequestParam Long medecinId,
 @RequestParam Long salleId,
 @RequestParam String date,
 @RequestParam String heureDebut,
 @RequestParam String heureFin,
 @RequestParam String motif
 ) {
 // Violation: magic number
 if(patientId==null||medecinId==null){return null;}
 try {
 // Violation: logique complexe dans le controller
 Patient patient =
patientRepository.findById(patientId).orElse(null);
 Medecin medecin =
medecinRepository.findById(medecinId).orElse(null);
 Salle salle = salleRepository.findById(salleId).orElse(null);
 if(patient != null && medecin != null && salle != null) {
 RendezVous rdv = new RendezVous();
 rdv.setPatient(patient);
 rdv.setMedecin(medecin);
 rdv.setSalle(salle);
 rdv.setDateRdv(date);
 rdv.setHeureDebut(heureDebut);
 rdv.setHeureFin(heureFin);
 rdv.setMotifConsultation(motif);
 // Violation: gestion d'exception générique
 return consultationService.save(rdv);
 }
 } catch(Exception e) {
 e.printStackTrace();
 }
 return null;
 }

 // Violation: méthode sans javadoc
 public List<RendezVous> getRendezVousByMedecin(Long id) {
 List<RendezVous> result = new ArrayList<>();
 // Violation: complexité cyclomatique élevée
 for(RendezVous rdv : consultationService.findAll()) {
 if(rdv.getMedecin().getId().equals(id)) {
 if(rdv.getDateRdv() != null) {
 if(rdv.getStatut().equals("PLANIFIE")) {
 result.add(rdv);
 }
 }
 }
 }
 return result;
 }
}

