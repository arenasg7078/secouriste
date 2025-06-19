package model.persistence;

/**
 * classe Disponibilite qui lie les classe secouriste et journee
 * @author Antoine Rena
 */
public class Disponibilite {

    private Secouriste secouriste;
    private Journee journee;

    /**
     * constructeur de disponibilite
     * @param secouriste le secouriste
     * @param journee la journée ou le secouriste est disponible
     */
    public Disponibilite(Secouriste secouriste, Journee journee) {
        this.secouriste = secouriste;
        this.journee = journee;
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
     * permet de récupérer la journée ou le secouriste est disponible
     * @return la journee
     */
    public Journee getJournee() {
        return journee;
    }

    /**
     * permet de modifier la journée disponible du secouriste
     * @param journee la journée
     */
    public void setJournee(Journee journee) {
        this.journee = journee;
    }

    /**
     * permet d'afficher la disponibilité du secouriste
     * @return la disponibilité en String
     */
    public String toString() {
        return "Le secouriste " + this.secouriste.getId() + " est disponible le " + this.journee.toString();
    }
}