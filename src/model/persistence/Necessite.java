package model.persistence;

/**
 * classe Necessite qui lie la classe competence avec elle même
 * @author Antoine Rena 
 */
public class Necessite {
    
    private Competence competence1;
    private Competence competence2;

    /**
     * le constructeur de necessite
     * @param comp1 la compétence 1
     * @param comp2 la compétence 2
     */
    public Necessite(Competence comp1, Competence comp2) {
        this.competence1 = comp1;
        this.competence2 = comp2;
    }

    /**
     * permet de récupére la compétence 1
     * @return la compétence 1
     */
    public Competence getCompetence1() {
        return this.competence1;
    }

    /**
     * permet de modifier la comptétence 1
     * @param comp la comptétence 1
     */
    public void setCompetence1(Competence comp) {
        this.competence1 = comp;
    }

    /**
     * permet de récupére la compétence 2
     * @return la compétence 2
     */
    public Competence getCompetence2() {
        return this.competence2;
    }

    /**
     * permet de modifier la comptétence 2
     * @param comp la comptétence 2
     */
    public void setCompetence2(Competence comp) {
        this.competence2 = comp;
    }

    /**
     * permet de récupérer la nécessité
     * @return la necessité en format String
     */
    public String toString() {
        return "La compétence " + this.competence1.getIntitule() + " a besoin de la compétence " + this.competence2.getIntitule();
    }
}
