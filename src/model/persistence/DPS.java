package model.persistence;

/**
 * classe dps qui utilise les classes journee, sport et site
 * @author Antoine Rena
 */
public class DPS {
    
    private long id;
    private int horaire_depart;
    private int horaire_fin;
    private Journee journee;
    private Sport sport;
    private Site site;

    /**
     * le constructeur de dps
     * @param theId l'id du dps
     * @param dep l'horaire de départ
     * @param fin l'horaire de fin
     * @param jour le jour
     * @param sport le sport attitré
     * @param site le site attitré
     */
    public DPS(long theId, int dep, int fin, Journee jour, Sport sport, Site site) {
        this.id = theId;
        this.horaire_depart = dep;
        this.horaire_fin = fin;
        this.journee = jour;
        this.site = site;
        this.sport = sport;
    }

    /**
     * permet de récupérer l'id du dps
     * @return l'id
     */
    public long getId() {
        return this.id;
    }

    /**
     * permet de modifier l'id du dps
     * @param theId l'id
     */
    public void setId(long theId) {
        this.id = theId;
    }

    /**
     * permet de récupérer l'horaire de début
     * @return l'horaire 
     */
    public int getHoraireDep() {
        return this.horaire_depart;
    }

    /**
     * premet de modifier l'horaire de début
     * @param dep l'horaire
     */
    public void setHoraireDep(int dep) {
        this.horaire_depart = dep;
    }

    /**
     * permet de récupérer l'horaire de fin
     * @return l'horaire 
     */
    public int getHoraireFin() {
        return this.horaire_fin;
    }

    /**
     * premet de modifier l'horaire de jin
     * @param dep l'horaire
     */
    public void setHoraireFin(int fin) {
        this.horaire_fin = fin;
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
     * permet de récupérer le sport
     * @return le sport
     */
    public Sport getSport() {
        return this.sport;
    }

    /**
     * permet de modifier le sport
     * @param s le sport
     */
    public void setSport(Sport s) {
        this.sport = s;
    }

    /**
     * permet de récupérer le site
     * @return le site
     */
    public Site getSite() {
        return this.site;
    }

    /**
     * permet de modifier le site
     * @param s le site
     */
    public void setSite(Site s) {
        this.site = s;
    }

    /**
     * permet de récupérer le dps
     * @return le dps en String
     */
    public String toString() {
        return "Le DPS " + this.id + " intervient le " + this.journee.toString() + " de " + this.horaire_depart + " à " + this.horaire_fin + " pour le sport " + this.sport.getNom() + " à " + this.site.getNom();
    }
}
