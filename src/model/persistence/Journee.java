package model.persistence;

/**
 * classe journee 
 * @author Antoine Rena
 */
public class Journee {

    private long id;
    private int jour;
    private int mois;
    private int annee;

    /**
     * le constructeur de journee
     * @param id l'id de la journée
     * @param jour le jour du mois
     * @param mois le mois de l'année
     * @param annee l'année
     */
    public Journee(long id, int jour, int mois, int annee) {
        this.id = id;
        this.jour = jour;
        this.mois = mois;
        this.annee = annee;
    }

    /**
     * permet de récupérer l'id
     * @return l'id
     */
    public long getId() {
        return id;
    }

    /**
     * permet de récupérer le jour
     * @return le jour
     */
    public int getJour() {
        return jour;
    }

    /**
     * permet de récupérer le mois
     * @return le mois
     */
    public int getMois() {
        return mois;
    }

    /**
     * permet de récupérer l'année
     * @return l'année
     */
    public int getAnnee() {
        return annee;
    }

    /**
     * permet de modifier le jour
     * @param jour le jour
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * permet de modifier le jour
     * @param jour le jour
     */
    public void setJour(int jour) {
        this.jour = jour;
    }

    /**
     * permet le modifier le mois
     * @param mois le mois
     */
    public void setMois(int mois) {
        this.mois = mois;
    }

    /**
     * permet de modifier l'année
     * @param annee l'année
     */
    public void setAnnee(int annee) {
        this.annee = annee;
    }

    /**
     * permet de récupérer la journée
     * @return la journée en format String
     */
    public String toString() {
        return this.jour + "/" + this.mois + "/" + this.annee;
    }
}