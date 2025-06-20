package model.graphe;
import java.util.ArrayList;
import java.util.HashMap;

import model.persistence.*;
/**
 * classe qui fait l'affectation des secouriste avec l'aproche Exhaustive
 * @author Antoine Rena
 */
public class Exhaustive {

    public static HashMap<DPS, Secouriste> meilleureAffectation = new HashMap<>();
    public static int maxDpsAffectes = 0;

    public static HashMap<DPS, Secouriste> affecterExhaustif(HashMap<DPS, ArrayList<Competence>> besoinsDPS, HashMap<Secouriste, ArrayList<Competence>> competencesSecouristes) {
        meilleureAffectation = new HashMap<>();
        maxDpsAffectes = 0;

        ArrayList<DPS> dpsListe = new ArrayList<>(besoinsDPS.keySet());
        HashMap<DPS, Secouriste> affectationsActuelles = new HashMap<>();
        ArrayList<Secouriste> secouristesAffectes = new ArrayList<>();

        essayerToutesLesAffectations(0, dpsListe, affectationsActuelles, secouristesAffectes, besoinsDPS, competencesSecouristes);

        return meilleureAffectation;
    }

    public static void essayerToutesLesAffectations(int indexDPS, ArrayList<DPS> dpsListe, HashMap<DPS, Secouriste> affectations, ArrayList<Secouriste> secouristesUtilises, HashMap<DPS, ArrayList<Competence>> besoinsDPS, HashMap<Secouriste, ArrayList<Competence>> competencesSecouristes) {
        boolean continuer = true;

        if (indexDPS == dpsListe.size()) {
            if (affectations.size() > maxDpsAffectes) {
                maxDpsAffectes = affectations.size();
                meilleureAffectation = new HashMap<>(affectations);
            }
            continuer = false;
        }

        if (continuer) {
            DPS dpsActuel = dpsListe.get(indexDPS);
            ArrayList<Competence> besoins = besoinsDPS.get(dpsActuel);

            for (Secouriste nomSecouriste : competencesSecouristes.keySet()) {
                boolean estDejaAffecte = false;

                for (Secouriste nom : secouristesUtilises) {
                    if (nom.equals(nomSecouriste)) {
                        estDejaAffecte = true;
                    }
                }

                if (!estDejaAffecte) {
                    ArrayList<Competence> competences = competencesSecouristes.get(nomSecouriste);

                    if (competences.containsAll(besoins)) {
                        affectations.put(dpsActuel, nomSecouriste);
                        secouristesUtilises.add(nomSecouriste);

                        essayerToutesLesAffectations(indexDPS + 1, dpsListe, affectations, secouristesUtilises, besoinsDPS, competencesSecouristes);

                        affectations.remove(dpsActuel);
                        secouristesUtilises.remove(secouristesUtilises.size() - 1);
                    }
                }
            }

            essayerToutesLesAffectations(indexDPS + 1, dpsListe, affectations, secouristesUtilises, besoinsDPS, competencesSecouristes);
        }
    }
}
