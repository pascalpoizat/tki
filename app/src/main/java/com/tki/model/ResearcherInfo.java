package com.tki.model;

/**
 * Structure immuable contenant les informations agrégées d'un chercheur.
 * Indépendant de la source (DBLP, Scholar, etc.).
 */
public record ResearcherInfo(
    String name,            // Nom complet trouvé
    int publicationCount,   // Nombre de publications
    String sourceUrl,       // URL de la source
    String sourceName       // Nom de la source (ex: "DBLP")
) {}