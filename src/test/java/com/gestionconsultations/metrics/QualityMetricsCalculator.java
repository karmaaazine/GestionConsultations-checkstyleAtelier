package com.gestionconsultations.metrics;

import java.lang.reflect.Method;

/**
 * Utilitaire pour calculer les métriques de qualité du code.
 */
public class QualityMetricsCalculator {

    /**
     * Calcule la complexité cyclomatique d'une classe.
     * 
     * @param clazz La classe à analyser
     * @return La complexité cyclomatique estimée
     */
    public static int calculateCyclomaticComplexity(Class<?> clazz) {
        int complexity = 1; // Base complexity
        
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            // Chaque méthode publique ajoute à la complexité
            if (java.lang.reflect.Modifier.isPublic(method.getModifiers())) {
                complexity += estimateMethodComplexity(method);
            }
        }
        
        return complexity;
    }

    /**
     * Estime la complexité d'une méthode individuelle.
     * 
     * @param method La méthode à analyser
     * @return La complexité estimée
     */
    private static int estimateMethodComplexity(Method method) {
        int complexity = 1; // Base complexity pour la méthode
        
        // Estimation basée sur le nombre de paramètres
        complexity += method.getParameterCount();
        
        // Pour une analyse plus précise, il faudrait analyser le bytecode
        // Ici, on fait une estimation simple
        return complexity;
    }

    /**
     * Calcule l'index de maintenabilité (Maintainability Index).
     * 
     * Formule simplifiée basée sur:
     * - Complexité cyclomatique
     * - Nombre de lignes de code
     * - Ratio de commentaires
     * 
     * @param clazz La classe à analyser
     * @return L'index de maintenabilité (0-100)
     */
    public static double calculateMaintainabilityIndex(Class<?> clazz) {
        int methodCount = clazz.getDeclaredMethods().length;
        int fieldCount = clazz.getDeclaredFields().length;
        
        // Estimation des métriques
        double cyclomaticComplexity = calculateCyclomaticComplexity(clazz);
        double linesOfCode = estimateLinesOfCode(clazz, methodCount);
        double commentRatio = estimateCommentRatio(clazz);
        double halsteadVolume = estimateHalsteadVolume(clazz, methodCount, fieldCount);
        
        // Formule de l'index de maintenabilité (version simplifiée)
        double mi = 171 
            - 5.2 * Math.log(Math.max(1, halsteadVolume)) 
            - 0.23 * cyclomaticComplexity 
            - 16.2 * Math.log(Math.max(1, linesOfCode)) 
            + 50 * Math.sin(Math.sqrt(2.4 * commentRatio));
        
        // Normaliser entre 0 et 100
        return Math.max(0, Math.min(100, mi));
    }

    /**
     * Estime le nombre de lignes de code d'une classe.
     */
    private static double estimateLinesOfCode(Class<?> clazz, int methodCount) {
        // Estimation: ~10 lignes par méthode + ~5 lignes par champ
        int fieldCount = clazz.getDeclaredFields().length;
        return methodCount * 10 + fieldCount * 5 + 10; // +10 pour la déclaration de classe
    }

    /**
     * Estime le ratio de commentaires.
     */
    private static double estimateCommentRatio(Class<?> clazz) {
        // Si la classe a des annotations Javadoc, on estime un bon ratio
        if (clazz.getAnnotations().length > 0) {
            return 0.25; // 25% de commentaires estimés
        }
        return 0.15; // 15% par défaut
    }

    /**
     * Estime le volume de Halstead.
     */
    private static double estimateHalsteadVolume(Class<?> clazz, int methodCount, int fieldCount) {
        // Estimation simplifiée: volume basé sur le nombre d'éléments
        return (methodCount + fieldCount) * 20;
    }

    /**
     * Calcule le nombre de méthodes par classe.
     */
    public static int getMethodCount(Class<?> clazz) {
        return clazz.getDeclaredMethods().length;
    }

    /**
     * Calcule le nombre de champs par classe.
     */
    public static int getFieldCount(Class<?> clazz) {
        return clazz.getDeclaredFields().length;
    }
}

