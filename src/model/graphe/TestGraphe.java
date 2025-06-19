package model.graphe;
import java.util.ArrayList;
import java.util.HashMap;
import model.persistence.*;

public class TestGraphe {

    public static void main(String[] args) {
        // === 1. Graphe des compétences ===
        HashMap<Competence, ArrayList<Competence>> graphe = new HashMap<>();
        Competence c1 = new Competence("PSE1");
        Competence c2 = new Competence("PSE2");
        Competence c3 = new Competence("CE");
        Competence c4 = new Competence("CP");
        Competence c5 = new Competence("CO");
        Competence c6 = new Competence("SSA");
        Competence c7 = new Competence("VPSP");
        Competence c8 = new Competence("PBC");
        Competence c9 = new Competence("PBF");

        ArrayList<Competence> p1 = new ArrayList<>(); // PSE1 n’a pas de prérequis
        graphe.put(c1, p1);

        ArrayList<Competence> p2 = new ArrayList<>();
        p2.add(c1);
        graphe.put(c2, p2); // PSE2 → PSE1

        ArrayList<Competence> p3 = new ArrayList<>();
        p3.add(c2);
        graphe.put(c3, p3); // CE → PSE2

        ArrayList<Competence> p4 = new ArrayList<>();
        p4.add(c3);
        graphe.put(c4, p4); // CP → CE

        ArrayList<Competence> p5 = new ArrayList<>();
        p5.add(c4);
        graphe.put(c5, p5); // CO → CP

        ArrayList<Competence> p6 = new ArrayList<>();
        p6.add(c1);
        graphe.put(c6, p6); // SSA → PSE1

        ArrayList<Competence> p7 = new ArrayList<>();
        p7.add(c2);
        graphe.put(c7, p7); // VPSP → PSE2

        ArrayList<Competence> p8 = new ArrayList<>();
        graphe.put(c8, p8);

        ArrayList<Competence> p9 = new ArrayList<>();
        p9.add(c8);
        graphe.put(c9, p9); // PBF → PBC

        boolean estUnDAG = DAGVerifier.estDAG(graphe);
        System.out.println("Graphe des compétences : " + (estUnDAG ? "VALIDE (DAG)" : "INVALIDE (cycle détecté)"));
        System.out.println();

        // === gr pas un DAG
        HashMap<Competence, ArrayList<Competence>> gr = new HashMap<>();

        ArrayList<Competence> p10 = new ArrayList<>();
        p10.add(c3);
        gr.put(c1, p10); 

        ArrayList<Competence> p11 = new ArrayList<>();
        p11.add(c1);
        gr.put(c2, p11); 

        ArrayList<Competence> p12 = new ArrayList<>();
        p12.add(c2);
        gr.put(c3, p12); 

        boolean grEstUnDAG = DAGVerifier.estDAG(gr);
        System.out.println("Graphe des compétences : " + (grEstUnDAG ? "VALIDE (DAG)" : "INVALIDE (cycle détecté)"));
        System.out.println();

        // === 2. Définir les besoins des DPS ===
        HashMap<DPS, ArrayList<Competence>> besoins = new HashMap<>();
        
        Journee j = new Journee(0, 0, 0, 0);
        Sport sp = new Sport("s1", "criquet");
        Site st = new Site("s1", "France", 0, 0);

        DPS d1 = new DPS(1, 0, 0, j, sp, st);
        ArrayList<Competence> b1 = new ArrayList<>();
        b1.add(c1);
        besoins.put(d1, b1);

        DPS d2 = new DPS(2, 0, 0, j, sp, st);
        ArrayList<Competence> b2 = new ArrayList<>();
        b2.add(c2);
        besoins.put(d2, b2);

        DPS d3 = new DPS(3, 0, 0, j, sp, st);
        ArrayList<Competence> b3 = new ArrayList<>();
        b3.add(c3);
        besoins.put(d3, b3);

        DPS d4 = new DPS(4, 0, 0, j, sp, st);
        ArrayList<Competence> b4 = new ArrayList<>();
        b4.add(c4);
        besoins.put(d4, b4);

        DPS d5 = new DPS(5, 0, 0, j, sp, st);
        ArrayList<Competence> b5 = new ArrayList<>();
        b5.add(c5);
        besoins.put(d5, b5);

        DPS d6 = new DPS(6, 0, 0, j, sp, st);
        ArrayList<Competence> b6 = new ArrayList<>();
        b6.add(c6);
        besoins.put(d6, b6);

        DPS d7 = new DPS(7, 0, 0, j, sp, st);
        ArrayList<Competence> b7 = new ArrayList<>();
        b7.add(c7);
        besoins.put(d7, b7);

        DPS d8 = new DPS(8, 0, 0, j, sp, st);
        ArrayList<Competence> b8 = new ArrayList<>();
        b8.add(c8);
        besoins.put(d8, b8);

        DPS d9 = new DPS(9, 0, 0, j, sp, st);
        ArrayList<Competence> b9 = new ArrayList<>();
        b9.add(c9);
        besoins.put(d9, b9);

        // === 3. Définir les compétences des secouristes ===
        HashMap<Secouriste, ArrayList<Competence>> secouristes = new HashMap<>();

        Secouriste s1 = new Secouriste(1, "rena", "antoine", "jj/mm/aa", "a@mail.com", "XX XX XX XX XX", "6 rue de rue", "antoine", "mdp", "admin");
        ArrayList<Competence> po1 = new ArrayList<>();
        po1.add(c1);
        po1.add(c2);
        secouristes.put(s1, po1);

        Secouriste s2 = new Secouriste(2, "cajean", "lucie", "jj/mm/aa", "l@mail.com", "XX XX XX XX XX", "2 allée de l'allée", "lucie", "mdp", "admin");
        ArrayList<Competence> po2 = new ArrayList<>();
        po2.add(c1);
        secouristes.put(s2, po2);

        Secouriste s3 = new Secouriste(3, "guguin", "alexandre", "jj/mm/aa", "ag@mail.com", "XX XX XX XX XX", "4 avenu de l'avenu", "alexandre", "mdp", "stagiaire");
        ArrayList<Competence> po3 = new ArrayList<>();
        po3.add(c3);
        secouristes.put(s3, po3);

        Secouriste s4 = new Secouriste(4, "bouëffard", "kevin", "jj/mm/aa", "k@mail.com", "XX XX XX XX XX", "8 impasse de l'impasse", "kevin", "mdp", "retraité");
        ArrayList<Competence> po4 = new ArrayList<>();
        po4.add(c5);
        secouristes.put(s4, po4);

        Secouriste s5 = new Secouriste(5, "secouriste", "quelconque", "jj/mm/aa", "s@mail.com", "XX XX XX XX XX", "9 chemin du chemin", "secouriste", "mdp", "secouriste");
        ArrayList<Competence> po5 = new ArrayList<>();
        po5.add(c4);
        po5.add(c5);
        secouristes.put(s5, po5);

        Secouriste s6 = new Secouriste(6, "autre", "secouriste", "jj/mm/aa", "au@mail.com", "XX XX XX XX XX", "1 avenue de la rue", "autre", "mdp", "secouriste");
        ArrayList<Competence> po6 = new ArrayList<>();
        po6.add(c5);
        po6.add(c6);
        po6.add(c7);
        secouristes.put(s6, po6);

        Secouriste s7 = new Secouriste(7, "p", "david", "jj/mm/aa", "p@mail.com", "XX XX XX XX XX", "8 impasse de l'impasse", "david", "mdp", "secouriste");
        ArrayList<Competence> po7 = new ArrayList<>();
        po7.add(c8);
        secouristes.put(s7, po7);

        Secouriste s8 = new Secouriste(8, "et", "encore", "jj/mm/aa", "e@mail.com", "XX XX XX XX XX", "9 chemin du chemin", "encore", "mdp", "secouriste");
        ArrayList<Competence> po8 = new ArrayList<>();
        po8.add(c7);
        po8.add(c9);
        secouristes.put(s8, po8);

        Secouriste s9 = new Secouriste(9, "voila", "etDe9", "jj/mm/aa", "et@mail.com", "XX XX XX XX XX", "1 avenue de la rue", "etDe9", "mdp", "secouriste");
        ArrayList<Competence> po9 = new ArrayList<>();
        po6.add(c7);
        po6.add(c8);
        po6.add(c9);
        secouristes.put(s9, po9);

        // === 4. Approche gloutonne ===
        long t1 = System.nanoTime();
        HashMap<DPS, Secouriste> resultatGlouton = Glouton.affecterGlouton(besoins, secouristes);
        long t2 = System.nanoTime();

        System.out.println("Méthode gloutonne :");
        afficherResultat(resultatGlouton);
        System.out.println("Nombre de DPS couverts : " + resultatGlouton.size());
        System.out.println("Temps (ms) : " + ((t2 - t1) / 1_000_000.0));
        System.out.println();

        // === 5. Approche exhaustive ===
        long t3 = System.nanoTime();
        HashMap<DPS, Secouriste> resultatExhaustif = Exhaustive.affecterExhaustif(besoins, secouristes);
        long t4 = System.nanoTime();

        System.out.println("Méthode exhaustive :");
        afficherResultat(resultatExhaustif);
        System.out.println("Nombre de DPS couverts : " + resultatExhaustif.size());
        System.out.println("Temps (ms) : " + ((t4 - t3) / 1_000_000.0));
    }

    public static void afficherResultat(HashMap<DPS, Secouriste> affectations) {
        for (DPS dps : affectations.keySet()) {
            Secouriste secouriste = affectations.get(dps);
            System.out.println("DPS : " + dps.getId() + " → Secouriste : " + secouriste.getId());
        }
    }
}
