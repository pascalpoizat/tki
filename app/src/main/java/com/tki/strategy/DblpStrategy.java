package com.tki.strategy;

import com.tki.model.ResearcherInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Implémentation de la stratégie pour DBLP.
 * Utilise l'API de recherche d'auteurs puis la fiche XML.
 */
public class DblpStrategy implements ResearcherStrategy {

    private static final String DBLP_SEARCH_URL = "https://dblp.org/search/author/api?format=xml&q=";

    @Override
    public ResearcherInfo execute(String name) throws IOException {
        System.out.println("🔍 [DBLP] Recherche de '" + name + "'...");

        // 1. Recherche du PID (Person ID) / URL
        String authorXmlUrl = findAuthorXmlUrl(name);
        
        if (authorXmlUrl == null) {
            throw new IOException("Auteur non trouvé sur DBLP pour la requête : " + name);
        }

        // 2. Récupération des détails
        return fetchDetails(authorXmlUrl);
    }

    private String findAuthorXmlUrl(String name) throws IOException {
        String encodedName = URLEncoder.encode(name, StandardCharsets.UTF_8);
        Document doc = Jsoup.connect(DBLP_SEARCH_URL + encodedName)
                .parser(Parser.xmlParser())
                .timeout(10000)
                .get();

        Element firstHit = doc.select("hits hit").first();
        if (firstHit != null) {
            Element info = firstHit.selectFirst("info");
            if (info != null) {
                return info.select("url").text() + ".xml";
            }
        }
        return null;
    }

    private ResearcherInfo fetchDetails(String xmlUrl) throws IOException {
        Document doc = Jsoup.connect(xmlUrl)
                .parser(Parser.xmlParser())
                .timeout(10000)
                .get();

        // Compte des publications (balises <r>)
        Elements publications = doc.select("dblpperson > r");
        int count = publications.size();
        
        // Nom exact
        String exactName = doc.select("dblpperson > person > author").first().text();

        return new ResearcherInfo(exactName, count, xmlUrl, "DBLP");
    }
}