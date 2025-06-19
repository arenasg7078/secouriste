package model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import model.persistence.Secouriste;
/**
 * Fais interagir le code java avec la base de données pour modifier la table Secouriste
 * @author Antoine Rena
 */
public class DAOSecouriste extends DAO<Secouriste> {

    /**
     * Insère un nouveau secouriste dans la table Secouriste
     * @param s le secouriste à ajouter
     * @return le nombre de lignes affectées, ou -1 en cas d'erreur
     */
    @Override
    public int create(Secouriste s) {
        String query = "INSERT INTO Secouriste(id, nom, prenom, dateNaissance, email, tel, adresse, user, mdp, status) VALUES ('" + s.getId() + "','" + s.getNom() + "','" + s.getPrenom() + "','" + s.getDateNaissance() + "','" + s.getEmail() + "','" + s.getTel() + "','" + s.getAdresse() + "','" + s.getUser() + "','" + s.getMdp() + "','" + s.getStatus() + " ')";
        try (Connection con = getConnection (); Statement st = con.createStatement ()) {
            return st.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return -1;
        }
    }

    /**
     * Met à jour un secouriste dans la table Secouriste
     * @param s le secouriste à modifier
     * @return le nombre de lignes affectées, ou -1 en cas d'erreur
     */
    @Override
    public int update(Secouriste s) {
        String query = "UPDATE Secouriste SET id='" + s.getId() + "', nom='" + s.getNom() + "', prenom='" + s.getPrenom() + "', dateNaissance='" + s.getDateNaissance() + "', email='" + s.getEmail() + "', tel='" + s.getTel() + "', adresse='" + s.getAdresse() + "', user='" + s.getUser() + "', mdp='" + s.getMdp() + "', status='" + s.getStatus() + "' WHERE id='" + s.getId() + "'";
        try (Connection con = getConnection ();
            Statement st = con.createStatement ()) {
            return st.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return -1;
        }
    }

    /**
     * Supprime un secouriste de la table Secouriste
     * @param s le secouriste à supprimer
     * @return le nombre de lignes affectées, ou -1 en cas d'erreur
     */
    @Override
    public int delete(Secouriste s) {
        String query = "DELETE FROM Secouriste WHERE id='" + s.getId() + "'";
        try (Connection con = getConnection();
            Statement st = con.createStatement ()) {
            return st.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return -1;
        }
    }

    /**
     * Récupère tous les secouristes de la table Secouriste
     * @return une liste de tous les secouristes
     */
    @Override
    public List<Secouriste> findAll () {
        List <Secouriste> users = new LinkedList <>();
        try (Connection con = getConnection ();
            Statement st = con.createStatement ();
            ResultSet rs = st.executeQuery("SELECT * FROM Secouriste")) {
            while (rs.next()) {
                long id = Integer.parseInt(rs.getString("id"));
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String dateNaissance = rs.getString("dateNaissance");
                String email = rs.getString("email");
                String tel = rs.getString("tel");
                String adresse = rs.getString("adresse");
                String user = rs.getString("user");
                String mdp = rs.getString("mdp");
                String status = rs.getString("status");
                users.add(new Secouriste(id, nom, prenom, dateNaissance, email, tel, adresse, user, mdp, status));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            return users;
    }

    /**
     * Cherche un secouriste par son identifiant
     * @param id l'identifiant du secouriste à trouver
     * @return le secouriste trouvé, ou null si non trouvé
     */
    @Override
    public Secouriste findByID(long id) {
        try (Connection con = getConnection ();
        PreparedStatement st = con.prepareStatement("SELECT * FROM Secouriste WHERE id= ?")) {
            st.setString (1, Long.toString(id));
            try(ResultSet rs = st.executeQuery ()){
                if (rs.next()) {
                    long idSecouriste = Integer.parseInt(rs.getString("id"));
                    String nom = rs.getString("nom");
                    String prenom = rs.getString("prenom");
                    String dateNaissance = rs.getString("dateNaissance");
                    String email = rs.getString("email");
                    String tel = rs.getString("tel");
                    String adresse = rs.getString("adresse");
                    String user = rs.getString("user");
                    String mdp = rs.getString("mdp");
                    String status = rs.getString("status");
                    return new Secouriste(idSecouriste, nom, prenom, dateNaissance, email, tel, adresse, user, mdp, status);
                }
            } catch (SQLException ex) { 
                System.out.println(ex.getMessage()); 
            }
        } catch (SQLException ex) { 
            System.out.println(ex.getMessage()); 
        }
        return null;
    }

    /**
     * @return null
     */
    @Override
    public Secouriste findByID(String code) {
        return null;
    }

    /**
     * @return null
     */
    @Override
    public Secouriste findByID(long idSecouriste, long idJour) {
        return null;
    }

    /**
     * @return null
     */
    @Override
    public Secouriste findByID(String intitule1, String intitule2) {
        return null;
    }
        
    /**
     * @return null
     */
    @Override
    public Secouriste findByID(long code, String intitule) {
        return null;
    }

    /**
     * @return null
     */
    @Override
    public Secouriste findByID(long idSecouriste, String intitule, long idDPS) {
        return null;
    }
}
