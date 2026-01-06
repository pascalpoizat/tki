package com.tki;

import com.tki.model.ResearcherInfo;
import com.tki.strategy.DblpStrategy;
import com.tki.strategy.ResearcherStrategy;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.util.List;
import java.util.concurrent.Callable;

@Command(name = "tki", mixinStandardHelpOptions = true, version = "tki 1.0",
        description = "Recherche et affiche les informations d'un chercheur.")
public class TkiApp implements Callable<Integer> {

    @Parameters(index = "0..*", description = "Le nom du chercheur à rechercher.")
    private List<String> nameParts;

    // Injection de la dépendance : on instancie ici la stratégie DBLP.
    // Pour changer de source (ex: Scholar), il suffirait de changer cette ligne
    // ou de l'injecter dynamiquement.
    private final ResearcherStrategy strategy = new DblpStrategy();

    public static void main(String[] args) {
        int exitCode = new CommandLine(new TkiApp()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() {
        if (nameParts == null || nameParts.isEmpty()) {
            System.err.println("❌ Erreur : Vous devez spécifier un nom de chercheur.");
            return 1;
        }

        String query = String.join(" ", nameParts);

        try {
            // L'appel est désormais générique. TkiApp ne sait pas comment DBLP fonctionne.
            ResearcherInfo info = strategy.execute(query);
            displayInfo(info);
        } catch (Exception e) {
            System.err.println("❌ Erreur : " + e.getMessage());
            return 1;
        }
        return 0;
    }

    private void displayInfo(ResearcherInfo info) {
        System.out.println("--------------------------------------------------");
        System.out.println("SOURCE         : " + info.sourceName());
        System.out.println("👤 NOM         : " + info.name());
        System.out.println("📚 PUBLICATIONS: " + info.publicationCount());
        System.out.println("🔗 URL         : " + info.sourceUrl());
        System.out.println("--------------------------------------------------");
    }
}