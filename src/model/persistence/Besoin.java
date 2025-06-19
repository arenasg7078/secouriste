package model.persistence;

/**
 * classe besoin qui lie les classes dps et compétence
 * @author Antoine Rena
 */
public class Besoin {
    private int nombre;
    private DPS dps;
    private Competence competence;

    /**
     * le constructeur de besoin
     * @param nb le nombre
     * @param dps le dps
     * @param comp la compétence
     */
    public Besoin(int nb, DPS dps, Competence comp) {
        this.nombre = nb;
        this.dps = dps;
        this.competence = comp;
    }

    /**
     * permet de récupérer le nombre
     * @return le nombre
     */
    public int getNombre(){
        return this.nombre;
    }

    /**
     * permet de choisir le nombre
     * @param nb le nombre
     */
    public void setNombre(int nb) {
        this.nombre = nb;
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
     * permet de récupérer la relation besoin
     * @return la relation besoin en format String
     */
    public String toString() {
        return "Le DPS " + this.dps.getId() + " à besoin de " + this.competence.getIntitule();
    }
}
