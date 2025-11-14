package com.gestionconsultations.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gestionconsultations.entity.Medicament;
import com.gestionconsultations.repository.MedicamentRepository;
import com.gestionconsultations.constants.Constants;

/**
 * Service pour la gestion du stock de médicaments.
 */
@Service
public class StockService {

    @Autowired
    private MedicamentRepository medicamentRepository;

    /**
     * Vérifie si le stock est suffisant pour une quantité donnée.
     *
     * @param medicamentId L'identifiant du médicament
     * @param quantite La quantité demandée
     * @return true si le stock est suffisant, false sinon
     */
    public boolean verifierStock(Long medicamentId, int quantite) {
        if (medicamentId == null || quantite <= Constants.MINIMUM_QUANTITY) {
            return false;
        }

        Medicament medicament = medicamentRepository.findById(medicamentId).orElse(null);
        if (medicament == null) {
            return false;
        }

        return medicament.getStockActuel() >= quantite;
    }
}
