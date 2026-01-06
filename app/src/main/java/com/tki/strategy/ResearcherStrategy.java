package com.tki.strategy;

import com.tki.model.ResearcherInfo;
import java.io.IOException;

public interface ResearcherStrategy {
    /**
     * Récupère les informations d'un chercheur à partir d'une requête (nom, id...).
     * * @param query Le nom ou l'identifiant du chercheur.
     * @return Les informations agrégées.
     * @throws IOException En cas de problème réseau ou de parsing.
     */
    ResearcherInfo execute(String query) throws IOException;
}