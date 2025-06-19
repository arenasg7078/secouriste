package model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import model.persistence.Competence;
/**
 * Fais interagir le code java avec la base de données pour modifier la table Competence
 * @author Antoine Rena
 */
public class DAOCompetence extends DAO<Competence> {
    
    @Override
    /**
     * Ajoute un élément dans la table 
     * @param c la compétence à ajouter dans la table
     * @return -1 si l'appel n'a pas fonctionné
     */
    public int create(Competence c) {
        String query = "INSERT INTO Competence(intitule) VALUES ('" + c.getIntitule() + " ')";
        try (Connection con = getConnection (); Statement st = con.createStatement ()) {
            return st.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return -1;
        }
    }

    /**
     * Modifie un élément de la table competence
     * @param c La compétence à modifier
     */
    @Override
    public int update(Competence c) {
        String query = "UPDATE Competence SET intitule='" + c.getIntitule() + "' WHERE intitule=" + c.getIntitule() + "'";
        try (Connection con = getConnection (); Statement st = con.createStatement ()){
            return st.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    /**
     * Supprime un élément de la table competence
     * @param c la compétence à supprimer
     * @return -1 si l'appel n'a pas fonctionné
     */
    @Override   
    public int delete(Competence c) {
        String query = "DELETE FROM Competence WHERE intitule='" + c.getIntitule() + "'";
        try (Connection con = getConnection ();
            Statement st = con.createStatement ()) {
            return st.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return -1;
        }
    }

    /**
     * Renvoie tous les éléments de la table cometence
     * @return renvoie la liste de tous éléments de la table
     */
    @Override
    public List<Competence> findAll() {
        List <Competence> c = new LinkedList <>();
        try (Connection con = getConnection ();
            Statement st = con.createStatement ();
            ResultSet rs = st.executeQuery("SELECT * FROM Competence")) {
            while (rs.next()) {
                String intitule = rs.getString("intitule");

                c.add(new Competence(intitule));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return c;
    }

    /**
     * @return null
     */
    @Override
    public Competence findByID(long id) {
        return null;
    }

    /**
     * Cherche un élément à partir de son code
     * @param code l'identifiant de la compétence
     * @return la compétence si elle est trouvée, sinon renvoie null
     */
    @Override
    public Competence findByID(String code) {
        try (Connection con = getConnection ();
        PreparedStatement st = con.prepareStatement("SELECT * FROM Competence WHERE intitule= ?")) {
            st.setString (1, code);
            try(ResultSet rs = st.executeQuery ()){
                if (rs.next()) {
                    String intitule = rs.getString("intitule");

                    return new Competence(intitule);
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
    public Competence findByID(long idSecouriste, long idJour) {
        return null;
    }

    /**
     * @return null
     */
    @Override
    public Competence findByID(String intitule1, String intitule2) {
        return null;
    }

    /**
     * @return null
     */
    @Override
    public Competence findByID(long code, String intitule) {
        return null;
    }

    /**
     * @return null
     */
    @Override
    public Competence findByID(long idSecouriste, String intitule, long idDPS) {
        return null;
    }
}