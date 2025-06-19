package model.persistence;

/**
 * la classe possede qui lie les classe secouriste et competence
 * @author Antoine Rena
 */
public class Possede{

    private Secouriste secouriste;
    private Competence competence;

    /**
     * le constructeur de la classe possede 
     * @param secouriste le secouriste
     * @param competence la compétence que possede le secouriste
     */
    public Possede(Secouriste secouriste, Competence competence) {
        this.secouriste = secouriste;
        this.competence = competence;
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
     * permet de récupérer le lien possede
     * @return le lien possede en format String
     */
    public String toString() {
        return "Le secoriste " + this.secouriste.getId() + " possède la compétence " + this.competence.getIntitule();
    }
}