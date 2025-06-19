package model.persistence;

/**
 * la classe site
 * @author Antoine Rena
 */
public class Site {
    
    private String code;
    private String nom;
    private float longitude;
    private float latitude;

    /**
     * le constructeur de site
     * @param c le code du site
     * @param n le nom du site
     * @param lo la longitude du site
     * @param la la latitude du site
     */
    public Site(String c, String n, float lo, float la) {
        this.code = c;
        this.nom = n;
        this.longitude = lo;
        this.latitude = la;
    }

    /**
     * permet de récupérer le code du site
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
     * permet de récupérer le nom du site
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
     * permet de récupérer la longitude du site
     * @return la longitude
     */
    public float getLongitude() {
        return this.longitude;
    }

    /**
     * permet de modifier la longitude 
     * @param lo la longitude
     */
    public void setLongitude(float lo) {
        this.longitude = lo;
    }

    /**
     * permet de récupérer la latitude du site
     * @return la latitude
     */
    public float getLatitude() {
        return this.latitude;
    }

    /**
     * permet de modifier la latitude 
     * @param la la latitude
     */
    public void setLatitude(float la) {
        this.latitude = la;
    }

    /**
     * permet de récupérer les informations du site
     * @return les informations du site en format String
     */
    public String toString() {
        return "Le site " + this.code + " a pour nom ; " + this.nom + ", a pour longitude ; " + this.longitude + " et a pour latitude ; " + this.latitude;
    }
}
