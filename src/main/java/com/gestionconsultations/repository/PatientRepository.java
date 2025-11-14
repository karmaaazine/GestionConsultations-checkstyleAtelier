package com.gestionconsultations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gestionconsultations.entity.Patient;

/**
 * Repository pour l'entité Patient.
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}

