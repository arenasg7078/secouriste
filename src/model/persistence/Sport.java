package model.persistence;

/**
 * la classe sport
 */
public class Sport {

    private String code;
    private String nom;

    /**
     * le constructeur de sport
     * @param c le code du sport
     * @param n le nom du sport
     */
    public Sport(String c, String n) {
        this.code = c;
        this.nom = n;
    }

    /**
     * permet de récupérer le code du sport
     * @return le code
     */
    public String getCode() {
        return this.code;
    }

    /**
     * permet de modifier le code
     * @param c le code
     */
    public void setCode(String c) {
        this.code = c;
    }

    /**
     * permet de récupérer le nom
     * @return le nom
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * permet de modifier le nom
     * @param n le nom
     */
    public void setNom(String n) {
        this.nom = n;
    }

    /**
     * permet de récupérer les informations du sport
     * @return les informations du sport en format String
     */
    public String toString() {
        return "Le sport " + this.code + " a pour nom ; " + this.nom;
    }
}
