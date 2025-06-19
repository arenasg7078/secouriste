package model.persistence;

/**
 * la classe secouriste
 * @author Antoine Rena
 */
public class Secouriste {

    private long id;
    private String nom;
    private String prenom;
    private String dateNaissance;
    private String email;
    private String tel;
    private String adresse;

    private String user;
    private String mdp;
    private String status;

    /**
     * le constructeur de secouriste
     * @param id l'id du secouriste
     * @param nom son nom
     * @param prenom son prénom
     * @param dateNaissance sa date de naissance
     * @param email son email
     * @param tel son numéro de téléphone
     * @param adresse son adresse
     * @param user son identifiant sur l'app
     * @param mdp son mot de passe sur l'app
     * @param status son status (utilisateur ou admin)
     */
    public Secouriste(long id, String nom, String prenom, String dateNaissance, String email, String tel, String adresse, String user, String mdp, String status) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.email = email;
        this.tel = tel;
        this.adresse = adresse;
        this.user = user;
        this.mdp = mdp;
        this.status = status;
    }

    /**
     * permet de récupérer l'id du secouriste
     * @return l'id
     */
    public long getId() {
        return id;
    }

    /**
     * permet de récupérer le nom du secouriste
     * @return le nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * permet de récupérer le prenom du secouriste
     * @return le prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * permet de récupérer la date de naissance du secouriste
     * @return la date de naissance
     */
    public String getDateNaissance() {
        return dateNaissance;
    }

    /**
     * permet de récupérer l'email du secouriste
     * @return l'email
     */
    public String getEmail() {
        return email;
    }

    /**
     * permet de récupérer le numéro de téléphone du secouriste
     * @return le numéro de téléphone
     */
    public String getTel() {
        return tel;
    }

    /**
     * permet de récupérer l'adresse du secouriste
     * @return l'adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * permet de récupérer le psodo du secouriste
     * @return le psodo
     */
    public String getUser() {
        return user;
    }

    /**
     * permet de récupérer le mot de passe du secouriste
     * @return le mot de passe
     */
    public String getMdp() {
        return mdp;
    }

    /**
     * permet de récupérer le status du secouriste
     * @return le status
     */
    public String getStatus() {
        return status;
    }

    /**
     * permet de modifier l'id
     * @param id l'id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * permet de modifier le nom
     * @param nom le nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * permet de modifier le prenom
     * @param prenom le prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * permet de modifier la date de naissance
     * @param dateNaissance la date de naissance
     */
    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /**
     * permet de modifier l'email
     * @param email l'email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * permet de modifier le numéro de téléphone 
     * @param tel le numéro de téléphone
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * permet de modifier l'adresse
     * @param adresse l'adresse
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
    /**
     * permet de modifier le psedo
     * @param user le psodo
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * permet de modifier le mot de passe
     * @param mdp le mot de passe
     */
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    /**
     * permet de modifier le status
     * @param status le status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * permet de récupérer les informations du secouriste
     * @return les informations du secouriste en format de String
     */
    public String toString() {
        return "Secouriste " + this.id + ";\n" + "nom : " + this.nom + "\nprénom : " + this.prenom +  "\ndate de naissance : " + this.dateNaissance + "\nemail : " + this.email + "\ntel : " + this.tel + "\nadresse : " + this.adresse;
    }
}