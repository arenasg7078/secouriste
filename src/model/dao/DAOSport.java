package model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import model.persistence.Sport;
/**
 * Fais interagir le code java avec la base de données pour modifier la table Sport
 * @author Antoine Rena
 */
public class DAOSport extends DAO<Sport> {

    /**
     * Insère un nouveau sport dans la table Sport
     * @param s le sport à ajouter
     * @return le nombre de lignes affectées, ou -1 en cas d'erreur
     */
    @Override
    public int create(Sport s) {
        String query = "INSERT INTO Sport(code,nom) VALUES('" + s.getCode() + "','" + s.getNom() + "')";
        try (Connection con = getConnection (); Statement st = con.createStatement ()){
            return st.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    /**
     * Met à jour un sport existant dans la table Sport
     * @param s le sport à modifier
     * @return le nombre de lignes affectées, ou -1 en cas d'erreur
     */
    @Override
    public int update(Sport s) {
        String query = "UPDATE Sport SET code='" + s.getCode() + "', nom='" + s.getNom() + "' WHERE code=" + s.getCode() + "'";
        try (Connection con = getConnection (); Statement st = con.createStatement ()){
            return st.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    /**
     * Supprime un sport de la table Sport
     * @param s le sport à supprimer
     * @return le nombre de lignes affectées, ou -1 en cas d'erreur
     */
    @Override
    public int delete(Sport s) {
        String query = "DELETE FROM Sport WHERE code='" + s.getCode() + "'";
        try (Connection con = getConnection ();
            Statement st = con.createStatement ()) {
            return st.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return -1;
        }
    }

    /**
     * Récupère tous les sports de la table Sport
     * @return une liste de tous les sports
     */
    @Override
    public List<Sport> findAll() {
        List <Sport> sport = new LinkedList <>();
        try (Connection con = getConnection ();
            Statement st = con.createStatement ();
            ResultSet rs = st.executeQuery("SELECT * FROM Sport")) {
            while (rs.next()) {
                String code = rs.getString("code");
                String nom = rs.getString("nom");
                sport.add(new Sport(code, nom));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return sport;
    }

    /**
     * Cherche un sport par son code
     * @param code le code du sport à trouver
     * @return le sport trouvé, ou null si non trouvé
     */
    @Override
    public Sport findByID(String code) {
        try (Connection con = getConnection ();
        PreparedStatement st = con.prepareStatement("SELECT * FROM Sport WHERE code= ?")) {
            st.setString (1, code);
            try(ResultSet rs = st.executeQuery ()){
                if (rs.next()) {
                    String codeSport = rs.getString("code");
                    String nom = rs.getString("nom");
                    return new Sport(codeSport ,nom);
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
    public Sport findByID(long id) {
        return null;
    }

    /**
     * @return null
     */
    @Override
    public Sport findByID(long idSecouriste, long idJour) {
        return null;
    }
    
    /**
     * @return null
     */
    @Override
    public Sport findByID(String intitule1, String intitule2) {
        return null;
    }

    /**
     * @return null
     */
    @Override
    public Sport findByID(long code, String intitule) {
        return null;
    }

    /**
     * @return null
     */
    @Override
    public Sport findByID(long idSecouriste, String intitule, long idDPS) {
        return null;
    }
}
