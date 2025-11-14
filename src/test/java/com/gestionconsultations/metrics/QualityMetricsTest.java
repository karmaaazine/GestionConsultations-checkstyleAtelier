package com.gestionconsultations.metrics;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.gestionconsultations.controller.ConsultationController;
import com.gestionconsultations.service.ConsultationService;
import com.gestionconsultations.service.StockService;

/**
 * Tests pour les métriques de qualité du code.
 */
@SpringBootTest
public class QualityMetricsTest {

    @Autowired
    private ConsultationController consultationController;

    @Autowired
    private ConsultationService consultationService;

    @Autowired
    private StockService stockService;

    /**
     * Teste que la complexité cyclomatique du contrôleur est < 10.
     */
    @Test
    public void testComplexiteCyclomatiqueController() {
        int complexite = QualityMetricsCalculator.calculateCyclomaticComplexity(
            consultationController.getClass());
        assertTrue(complexite < 10, 
            "La complexité cyclomatique du contrôleur (" + complexite + ") doit être < 10");
    }

    /**
     * Teste que la complexité cyclomatique du service est < 10.
     */
    @Test
    public void testComplexiteCyclomatiqueService() {
        int complexite = QualityMetricsCalculator.calculateCyclomaticComplexity(
            consultationService.getClass());
        assertTrue(complexite < 10, 
            "La complexité cyclomatique du service (" + complexite + ") doit être < 10");
    }

    /**
     * Teste que la complexité cyclomatique du StockService est < 10.
     */
    @Test
    public void testComplexiteCyclomatiqueStockService() {
        int complexite = QualityMetricsCalculator.calculateCyclomaticComplexity(
            stockService.getClass());
        assertTrue(complexite < 10, 
            "La complexité cyclomatique du StockService (" + complexite + ") doit être < 10");
    }

    /**
     * Teste que le score de maintenabilité est > 60.
     */
    @Test
    public void testMaintenabilite() {
        double maintainabilityIndex = QualityMetricsCalculator.calculateMaintainabilityIndex(
            consultationController.getClass());
        assertTrue(maintainabilityIndex > 60, 
            "Le score de maintenabilité (" + maintainabilityIndex + ") doit être > 60");
    }

    /**
     * Teste la maintenabilité du contrôleur.
     */
    @Test
    public void testMaintenabiliteController() {
        double maintainabilityIndex = QualityMetricsCalculator.calculateMaintainabilityIndex(
            consultationController.getClass());
        assertTrue(maintainabilityIndex > 60, 
            "Le score de maintenabilité du contrôleur (" + maintainabilityIndex + ") doit être > 60");
    }

    /**
     * Teste la maintenabilité du service.
     */
    @Test
    public void testMaintenabiliteService() {
        double maintainabilityIndex = QualityMetricsCalculator.calculateMaintainabilityIndex(
            consultationService.getClass());
        assertTrue(maintainabilityIndex > 60, 
            "Le score de maintenabilité du service (" + maintainabilityIndex + ") doit être > 60");
    }

    /**
     * Teste la maintenabilité du StockService.
     */
    @Test
    public void testMaintenabiliteStockService() {
        double maintainabilityIndex = QualityMetricsCalculator.calculateMaintainabilityIndex(
            stockService.getClass());
        assertTrue(maintainabilityIndex > 60, 
            "Le score de maintenabilité du StockService (" + maintainabilityIndex + ") doit être > 60");
    }
}

