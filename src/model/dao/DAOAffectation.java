package model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import model.persistence.Affectation;
import model.persistence.DPS;
import model.persistence.Competence;
import model.persistence.Secouriste;
/**
 * Fais interagir le code java avec la base de données pour modifier la table Affectation
 * @author Antoine Rena
 */
public class DAOAffectation extends DAO<Affectation> {
    @Override
    /**
     * Ajoute un élément dans la table affectation
     * @param a l'affectation à ajouter a la table
     * @return -1 si l'appel n'a pas fonctionné
     */
    public int create(Affectation a) {
        String query = "INSERT INTO Affectation(leSecouriste, laCompetence, leDPS) VALUES ('" + a.getSecouriste().getId() + "','" + a.getCompetence().getIntitule() + "','" + a.getDPS().getId() + " ')";
        try (Connection con = getConnection (); Statement st = con.createStatement ()) {
            return st.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return -1;
        }
    }

    /**
     * Modifie un élément de la table affectation
     * @param a l'affectation à modifier
     */
    @Override
    public int update(Affectation a) {
        String query = "UPDATE Affectation SET leSecouriste='" + a.getSecouriste().getId() + "', laCompetence='" + a.getCompetence().getIntitule() + "', leDPS='" + a.getDPS().getId() + "' WHERE leSecouriste=" + a.getSecouriste().getId() + "' AND laCompetence=" + a.getCompetence().getIntitule() + "' AND leDPS='" + a.getDPS().getId() + "'";
        try (Connection con = getConnection (); Statement st = con.createStatement ()){
            return st.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    /**
     * Supprime un élément de la table affectation
     * @param a l'affectation à supprimer
     * @return -1 si l'appel n'a pas fonctionné
     */
    @Override   
    public int delete(Affectation a) {
        String query = "DELETE FROM Affectation WHERE leSecouriste=" + a.getSecouriste().getId() + "' AND laCompetence=" + a.getCompetence().getIntitule() + "' AND leDPS='" + a.getDPS().getId() + "'";
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
     * @param idSecouriste la clé primaire de secouriste
     * @param intitule la clé primaire de competence
     * @param idDPS la clé primaire de dps
     * @return null si l'élément n'est pas trouvé, sinon, renvoie l'élément
     */
    @Override
    public Affectation findByID(long idSecouriste, String intitule, long idDPS) {
        try (Connection con = getConnection ();
        PreparedStatement st = con.prepareStatement("SELECT * FROM Affectation WHERE leSecouriste= ? AND laCompetence= ? AND leDPS= ?")) {
            st.setString (1, Long.toString(idSecouriste));
            st.setString (2, intitule);
            st.setString (3, Long.toString(idDPS));
            try(ResultSet rs = st.executeQuery ()){
                if (rs.next()) {
                    String secouristeCherche = rs.getString("leSecouriste");
                    String compCherche = rs.getString("laCompetence");
                    String dpsCherche = rs.getString("leDPS");

                    DAOSecouriste secourDAO = new DAOSecouriste();
                    DAOCompetence compDAO = new DAOCompetence();
                    DAODPS dpsDAO = new DAODPS();

                    Secouriste secour = secourDAO.findByID(secouristeCherche);
                    Competence comp = compDAO.findByID(compCherche);
                    DPS dps = dpsDAO.findByID(dpsCherche);

                    Affectation a = new Affectation(secour, comp, dps);
                    return a;
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
     * Renvoie tous les éléments de la table affectation
     * @return la liste finale
     */
    @Override
    public List<Affectation> findAll () {
        List <Affectation> a = new LinkedList <>();
        try (Connection con = getConnection ();
            Statement st = con.createStatement ();
            ResultSet rs = st.executeQuery("SELECT * FROM Affectation")) {
            while (rs.next()) {
                String secouristeCherche = rs.getString("leSecouriste");
                String compCherche = rs.getString("laCompetence");
                String dpsCherche = rs.getString("leDPS");

                DAOSecouriste secourDAO = new DAOSecouriste();
                DAOCompetence compDAO = new DAOCompetence();
                DAODPS dpsDAO = new DAODPS();

                Secouriste secour = secourDAO.findByID(secouristeCherche);
                Competence comp = compDAO.findByID(compCherche);
                DPS dps = dpsDAO.findByID(dpsCherche);

                a.add(new Affectation(secour, comp, dps));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return a;
    }

    /**
     * @return null
     */
    @Override
    public Affectation findByID(String code) {
        return null;
    }

    /**
     * @return null
     */
    @Override
    public Affectation findByID(long idSecouriste, long idJour) {
        return null;
    }

    /**
     * @return null
     */
    @Override
    public Affectation findByID(long code) {
        return null;
    }

    /**
     * @return null
     */
    @Override
    public Affectation findByID(String intitule1, String intitule2) {
        return null;
    }

    /**
     * @return null
     */
    @Override
    public Affectation findByID(long idSecouriste, String intitule) {
        return null;
    }
}
