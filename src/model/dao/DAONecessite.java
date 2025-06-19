package model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import model.persistence.Competence;
import model.persistence.Necessite;
/**
 * Fais interagir le code java avec la base de données pour modifier la table Necessite
 * @author Antoine Rena
 */
public class DAONecessite extends DAO<Necessite> {
    
    @Override
    /**
     * Ajoute un élément dans la table necessite
     * @param n la necessité à ajouter
     * @return -1 si l'appel n'a pas fonctionné
     */
    public int create(Necessite n) {
        String query = "INSERT INTO Necessite(competence1, competence2) VALUES ('" + n.getCompetence1().getIntitule() + "','" + n.getCompetence2().getIntitule() +" ')";
        try (Connection con = getConnection (); Statement st = con.createStatement ()) {
            return st.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return -1;
        }
    }

    /**
     * Modifie un élément de la table necessite
     * @param n la necessité à modifier
     */
    @Override
    public int update(Necessite n) {
        String query = "UPDATE Necessite SET competence1='" + n.getCompetence1().getIntitule() + "', competence2='" + n.getCompetence2().getIntitule() + "' WHERE competence1=" + n.getCompetence1().getIntitule() + "' AND competence2=" + n.getCompetence2().getIntitule() + "'";
        try (Connection con = getConnection (); Statement st = con.createStatement ()){
            return st.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    /**
     * Supprime un élément de la table necessite
     * @param n la necessité à supprimer
     * @return -1 si l'appel n'a pas fonctionné
     */
    @Override   
    public int delete(Necessite n) {
        String query = "DELETE FROM Necessite WHERE competence1=" + n.getCompetence1().getIntitule() + "' AND competence2=" + n.getCompetence2().getIntitule() + "'";
        try (Connection con = getConnection ();
            Statement st = con.createStatement ()) {
            return st.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return -1;
        }
    }

    /**
     * Cherche un élément en fonction de son ID 
     * @param id l'identifiant de la necessité
     * @return null si l'élément n'est pas trouvé, sinon, renvoie l'élément
     */
    @Override
    public Necessite findByID(String intitule1, String intitule2) {
        try (Connection con = getConnection ();
        PreparedStatement st = con.prepareStatement("SELECT * FROM Necessite WHERE competence1= ? AND competence2= ?")) {
            st.setString (1, intitule1);
            st.setString (2, intitule2);
            try(ResultSet rs = st.executeQuery ()){
                if (rs.next()) {
                    String comp1Cherche = rs.getString("competence1");
                    String comp2Cherche = rs.getString("competence2");

                    DAOCompetence comp1DAO = new DAOCompetence();
                    DAOCompetence comp2DAO = new DAOCompetence();

                    Competence comp1 = comp1DAO.findByID(comp1Cherche);
                    Competence comp2 = comp2DAO.findByID(comp2Cherche);

                    Necessite n = new Necessite(comp1, comp2);
                    return n;
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
     * Renvoie tous les éléments de la table
     * @return la liste finale
     */
    @Override
    public List<Necessite> findAll () {
        List <Necessite> n = new LinkedList <>();
        try (Connection con = getConnection ();
            Statement st = con.createStatement ();
            ResultSet rs = st.executeQuery("SELECT * FROM Necessite")) {
            while (rs.next()) {
                String comp1Cherche = rs.getString("competence1");
                String comp2Cherche = rs.getString("competence2");

                DAOCompetence comp1DAO = new DAOCompetence();
                DAOCompetence comp2DAO = new DAOCompetence();

                Competence comp1 = comp1DAO.findByID(comp1Cherche);
                Competence comp2 = comp2DAO.findByID(comp2Cherche);

                n.add(new Necessite(comp1, comp2));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return n;
    }

    /**
     * @return null
     */
    @Override
    public Necessite findByID(String code) {
        return null;
    }

    /**
     * @return null
     */
    @Override
    public Necessite findByID(long idSecouriste, long idJour) {
        return null;
    }

    /**
     * @return null
     */
    @Override
    public Necessite findByID(long code) {
        return null;
    }

    /**
     * @return null
     */
    @Override
    public Necessite findByID(long code, String intitule) {
        return null;
    }

    /**
     * @return null
     */
    @Override
    public Necessite findByID(long idSecouriste, String intitule, long idDPS) {
        return null;
    }
}
