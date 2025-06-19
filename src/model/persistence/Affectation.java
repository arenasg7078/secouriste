package model.persistence;

/**
 * Classe affectation qui lie les classes secouriste, competence, et dps
 * @author Antoine.rena
 */
public class Affectation {
    private Secouriste secouriste;
    private Competence competence;
    private DPS dps;

    /**
     * constructeur de la classe affectation
     * @param secouriste le secouriste a affecté
     * @param competence la compétence qui lui serra affecté
     * @param dps le dsp
     */
    public Affectation(Secouriste secouriste, Competence competence, DPS dps) {
        this.secouriste = secouriste;
        this.competence = competence;
        this.dps = dps;
    }

    /**
     * permet de récupérer le secouriste
     * @return le secouriste
     */
    public Secouriste getSecouriste() {
        return secouriste;
    }

    /**
     * permet de choisir le secouriste
     * @param secouriste le secouriste
     */
    public void setSecouriste(Secouriste secouriste) {
        this.secouriste = secouriste;
    }

    /**
     * permet de récupérer la compétence
     * @return la compétence
     */
    public Competence getCompetence() {
        return this.competence;
    }

    /**
     * permet de choisir la compétence
     * @param comp la compétence
     */
    public void setCompetence(Competence comp) {
        this.competence = comp;
    }

    /**
     * permet de récupérer le dps
     * @return le dps
     */
    public DPS getDPS() {
        return this.dps;
    }

    /**
     * permet de choisir le dps
     * @param dps le dps
     */
    public void setDPS(DPS dps) {
        this.dps = dps;
    }

    /**
     * permet de récupérer l'affectation
     * @return l'affectation en format String
     */
    public String toString() {
        return "Le secouriste " + this.secouriste.getId() + " est affecté a la compétence " + this.competence.getIntitule() + " et au DPS " + this.dps;
    }
}

