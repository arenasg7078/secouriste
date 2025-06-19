package model.graphe;
import java.util.ArrayList;
import java.util.HashMap;

import model.persistence.*;

public class Glouton {

    /**
     * Méthode d'affectation gloutonne sans break/continue.
     */
    public static HashMap<DPS, Secouriste> affecterGlouton( HashMap<DPS, ArrayList<Competence>> besoinsDPS, HashMap<Secouriste, ArrayList<Competence>> competencesSecouristes) {
        HashMap<DPS, Secouriste> affectations = new HashMap<>();
        ArrayList<Secouriste> secouristesDejaAffectes = new ArrayList<>();

        // Pour chaque DPS
        for (DPS nomDPS : besoinsDPS.keySet()) {
            ArrayList<Competence> besoins = besoinsDPS.get(nomDPS);

            // On initialise un booléen pour savoir si le DPS a été affecté
            boolean affecte = false;

            // On parcourt tous les secouristes
            for (Secouriste nomSecouriste : competencesSecouristes.keySet()) {
                if (!affecte) {
                    boolean estLibre = true;
                    // Vérifier si le secouriste est déjà affecté
                    for (Secouriste nomOccupe : secouristesDejaAffectes) {
                        if (nomOccupe.equals(nomSecouriste)) {
                            estLibre = false;
                        }
                    }

                    if (estLibre) {
                        ArrayList<Competence> competences = competencesSecouristes.get(nomSecouriste);
                        if (competences.containsAll(besoins)) {
                            affectations.put(nomDPS, nomSecouriste);
                            secouristesDejaAffectes.add(nomSecouriste);
                            affecte = true;
                        }
                    }
                }
            }
        }

        return affectations;
    }
}
