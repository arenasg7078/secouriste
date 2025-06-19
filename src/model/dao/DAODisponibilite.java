package model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import model.persistence.Disponibilite;
import model.persistence.Journee;
import model.persistence.Secouriste;
/**
 * Fais interagir le code java avec la base de données pour modifier la table Disponiblilite
 * 
 * @author Antoine Rena
 */
public class DAODisponibilite extends DAO<Disponibilite> {
    
    /**
     * Insère un élément dans la table disponibilite
     * @param d la disponibilité à ajouter
     * @return -1 si ça renvoie une erreur
     */
    @Override
    public int create(Disponibilite d) {
        String query = "INSERT INTO EstDisponible(leSecouriste, laJournee) VALUES('" + d.getSecouriste().getId() + "','" + d.getJournee().getId() + "')";
        try (Connection con = getConnection (); Statement st = con.createStatement ()){
            return st.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    /**
     * Modifie un élément de la table disponibilite
     * @param d la disponibilité à modifier
     * @return -1
     */
    @Override
    public int update(Disponibilite d) {
        return -1;
    }

    /**
     * Supprime un élément de la table disponibilite
     * @param d la disponibilité à supprimer
     * @return -1 si cela renvoie une erreur
     */
    @Override
    public int delete(Disponibilite d) {
        String query = "DELETE FROM Disponibilite WHERE leSecouriste='" + d.getSecouriste().getId() + "' AND laJournee='" + d.getJournee().getId() + "'";
        try (Connection con = getConnection ();
            Statement st = con.createStatement ()) {
            return st.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return -1;
        }
    }

    /**
     * Renvoie tous les éléments de la table disponibilité
     * @return la liste finale
     */
    @Override
    public List<Disponibilite> findAll () {
        List <Disponibilite> users = new LinkedList <>();
        try (Connection con = getConnection ();
            Statement st = con.createStatement ();
            ResultSet rs = st.executeQuery("SELECT * FROM Disponibilite")) {
            while (rs.next()) {
                long idS = Long.parseLong(rs.getString("leSecouriste"));
                long idJ = Long.parseLong(rs.getString("laJournee"));
                DAOSecouriste DAOs = new DAOSecouriste();
                Secouriste leSecours = DAOs.findByID(idS);
                DAOJournee DAOj = new DAOJournee();
                Journee leJour = DAOj.findByID(idJ);
                users.add(new Disponibilite(leSecours, leJour));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return users;
    }

    /**
     * Cherche un élément dans la table disponibilite
     * @param idSecouriste la clé primaire de secouriste
     * @param idJour la clé primaire de journee
     * @return l'élément trouvé, sinon renvoie null
     */
    @Override
    public Disponibilite findByID(long idSecouriste, long idJour) {
        try (Connection con = getConnection ();
        PreparedStatement st = con.prepareStatement("SELECT * FROM Disponibilite WHERE leSecouriste= ? AND laJournee= ?")) {
            st.setString (1, Long.toString(idSecouriste));
            st.setString(2, Long.toString(idJour));
            try(ResultSet rs = st.executeQuery ()){
                if (rs.next()) {
                    long idS = Long.parseLong(rs.getString("leSecouriste"));
                    long idJ = Long.parseLong(rs.getString("laJournee"));
                    DAOSecouriste DAOs = new DAOSecouriste();
                    Secouriste leSecours = DAOs.findByID(idS);
                    DAOJournee DAOj = new DAOJournee();
                    Journee leJour = DAOj.findByID(idJ);
                    return new Disponibilite(leSecours, leJour);
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
    public Disponibilite findByID(String code) {
        return null;
    }

    /**
     * @return null
     */
    @Override
    public Disponibilite findByID(long code) {
        return null;
    }

    /**
     * @return null
     */
    @Override
    public Disponibilite findByID(String intitule1, String intitule2) {
        return null;
    }

    /**
     * @return null
     */
    @Override
    public Disponibilite findByID(long code, String intitule) {
        return null;
    }

    /**
     * @return null
     */
    @Override
    public Disponibilite findByID(long idSecouriste, String intitule, long idDPS) {
        return null;
    }
}