package model.graphe;
import java.util.ArrayList;
import java.util.HashMap;

import model.persistence.Competence;

public class DAGVerifier {

    /**
     * Vérifie si le graphe est un DAG (pas de cycle).
     */
    public static boolean estDAG(HashMap<Competence, ArrayList<Competence>> graphe) {
        ArrayList<Competence> visites = new ArrayList<>();
        ArrayList<Competence> pileRec = new ArrayList<>();

        boolean estCorrect = true; // variable résultat

        for (Competence noeud : graphe.keySet()) {
            if (!estDansListe(visites, noeud)) {
                boolean cycle = aUnCycle(noeud, graphe, visites, pileRec);
                if (cycle) {
                    estCorrect = false;
                }
            }
        }

        return estCorrect;
    }

    /**
     * Fonction récursive qui détecte les cycles.
     */
    public static boolean aUnCycle(
        Competence courant,
        HashMap<Competence, ArrayList<Competence>> graphe,
        ArrayList<Competence> visites,
        ArrayList<Competence> pileRec
    ) {
        visites.add(courant);
        pileRec.add(courant);

        boolean trouveCycle = false;

        ArrayList<Competence> voisins = graphe.get(courant);
        if (voisins != null) {
            for (Competence voisin : voisins) {
                if (!estDansListe(visites, voisin)) {
                    boolean sousCycle = aUnCycle(voisin, graphe, visites, pileRec);
                    if (sousCycle) {
                        trouveCycle = true;
                    }
                } else {
                    if (estDansListe(pileRec, voisin)) {
                        trouveCycle = true;
                    }
                }
            }
        }

        supprimerDeListe(pileRec, courant);

        return trouveCycle;
    }

    public static boolean estDansListe(ArrayList<Competence> liste, Competence valeur) {
        return liste.contains(valeur);
    }

    public static void supprimerDeListe(ArrayList<Competence> liste, Competence valeur) {
        int position = -1;
        int i = 0;
        while (i < liste.size()) {
            if (liste.get(i).equals(valeur)) {
                position = i;
            }
            i++;
        }
        if (position != -1) {
            liste.remove(position);
        }
    }
}
