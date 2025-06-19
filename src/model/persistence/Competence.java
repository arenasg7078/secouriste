package model.persistence;

/**
 * classe Competence
 * @author Antoine Rena
 */
public class Competence {
    
    private String intitule;

    /**
     * le contructeur de competence
     * @param intit l'intitulé de la compétence
     */
    public Competence(String intit) {
        this.intitule = intit;
    }

    /**
     * permet de récupérer l'intitulé de la compétence
     * @return l'intitulé 
     */
    public String getIntitule() {
        return this.intitule;
    }

    /**
     * permet de modifier l'intitulé de la compétence
     * @param intit l'intitulé
     */
    public void setIntitule(String intit) {
        this.intitule = intit;
    }

    /**
     * permet de récupérer la compétence
     * @return la compétence en format String
     */
    public String toString() {
        return "Cette compétence à pour intitulé " + this.intitule;
    }
}
