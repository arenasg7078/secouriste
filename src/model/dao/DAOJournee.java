package model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import model.persistence.Journee;
/**
 * Fais interagir le code java avec la base de données pour modifier la table Journee
 * @author Antoine Rena
 */
public class DAOJournee extends DAO<Journee> {
    
    /**
     * insère un élément dans la table journee
     * @param j la journee à ajouter
     * @return -1 si une erreur est renvoyée
     */
    @Override
    public int create(Journee j) {
        String query = "INSERT INTO Journee(id, jour, mois, annee) VALUES('" + j.getId() + "','" + j.getJour() + "','" + j.getMois() + "','" + j.getAnnee() + "')";
        try (Connection con = getConnection (); Statement st = con.createStatement ()){
            return st.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }
    
    /**
     * modifie un élément de la table journee
     * * @param j la journee à modifier
     * * @return -1 si cela renvoie une erreur
     */
    @Override
    public int update(Journee j) {
        String query = "UPDATE Journee SET idJournee='" + j.getId() + "', jour='" + j.getJour() + "', mois='" + j.getMois() + "', annee='" + j.getAnnee() + "' WHERE id=" + j.getJour() + "'";
        try (Connection con = getConnection (); Statement st = con.createStatement ()){
            return st.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    /**
     * Supprime une journée de la table
     * @param j la journée à supprimer
     * @return le nombre de lignes affectées, ou -1 en cas d'erreur
     */
    @Override
    public int delete(Journee j) {
        String query = "DELETE FROM Journee WHERE id='" + j.getId() + "'";
        try (Connection con = getConnection ();
            Statement st = con.createStatement ()) {
            return st.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return -1;
        }
    }

    /**
     * Récupère toutes les journées de la table Journee
     * @return une liste de toutes les journées
     */
    @Override
    public List<Journee> findAll() {
        List <Journee> journee = new LinkedList <>();
        try (Connection con = getConnection ();
            Statement st = con.createStatement ();
            ResultSet rs = st.executeQuery("SELECT * FROM Journee")) {
            while (rs.next()) {
                long id = Integer.parseInt(rs.getString("id"));
                int jour = Integer.parseInt(rs.getString("jour"));
                int mois = Integer.parseInt(rs.getString("mois"));
                int annee = Integer.parseInt(rs.getString("annee"));
                journee.add(new Journee(id, jour, mois, annee));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return journee;
    }

    /**
     * Cherche une journée grace à son identifiant
     * @param id l'identifiant de la journée à trouver
     * @return la journée trouvée, ou null si non trouvée
     */
    @Override
    public Journee findByID(long id) {
        try (Connection con = getConnection ();
        PreparedStatement st = con.prepareStatement("SELECT * FROM Journee WHERE id= ?")) {
            st.setString (1, Long.toString(id));
            try(ResultSet rs = st.executeQuery ()){
                if (rs.next()) {
                    long idJournee = Integer.parseInt(rs.getString("idJournee"));
                    int jour = Integer.parseInt(rs.getString("jour"));
                    int mois = Integer.parseInt(rs.getString("mois"));
                    int annee = Integer.parseInt(rs.getString("annee"));
                    return new Journee(idJournee, jour, mois, annee);
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
    public Journee findByID(String code) {
        return null;
    }

    /**
     * @return null
     */
    @Override
    public Journee findByID(long idSecouriste, long idJour) {
        return null;
    }

    /**
     * @return null
     */
    @Override
    public Journee findByID(String intitule1, String intitule2) {
        return null;
    }

    /**
     * @return null
     */
    @Override
    public Journee findByID(long code, String intitule) {
        return null;
    }

    /**
     * @return null
     */
    @Override
    public Journee findByID(long idSecouriste, String intitule, long idDPS) {
        return null;
    }
}