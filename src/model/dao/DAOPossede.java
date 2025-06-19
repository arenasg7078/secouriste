package model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import model.persistence.Competence;
import model.persistence.Possede;
import model.persistence.Secouriste;
/**
 * Fais interagir le code java avec la base de données pour modifier la table Possede
 * @author Antoine Rena
 */
public class DAOPossede extends DAO<Possede> {
    @Override
    /**
     * Ajoute un élément dans la table possede
     * @param p l'élément possede à ajouter
     * @return -1 si l'appel n'a pas fonctionné
     */
    public int create(Possede p) {
        String query = "INSERT INTO Possede(leSecouriste, laCompetence) VALUES ('" + p.getSecouriste().getId() + "','" + p.getCompetence().getIntitule() +" ')";
        try (Connection con = getConnection (); Statement st = con.createStatement ()) {
            return st.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return -1;
        }
    }

    /**
     * Modifie un élément de la table pessede
     * @param p l'élément possede à modifier
     */
    @Override
    public int update(Possede p) {
        String query = "UPDATE Possede SET leSecouriste='" + p.getSecouriste().getId() + "', laCompetence='" + p.getCompetence().getIntitule() + "' WHERE leSecouriste=" + p.getSecouriste().getId() + "' AND laCompetence=" + p.getCompetence().getIntitule() + "'";
        try (Connection con = getConnection (); Statement st = con.createStatement ()){
            return st.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    /**
     * Supprime un élément de la table possede
     * @param p l'élément possede à supprimer
     * @return -1 si l'appel n'a pas fonctionné
     */
    @Override   
    public int delete(Possede p) {
        String query = "DELETE FROM Possede WHERE leSecouriste=" + p.getSecouriste().getId() + "' AND laCompetence=" + p.getCompetence().getIntitule() + "'";
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
     * @param code la clé primaire de secouriste
     * @param intitule la clé primaire de competence
     * @return null si l'élément n'est pas trouvé, sinon, renvoie l'élément
     */
    @Override
    public Possede findByID(long code, String intitule) {
        try (Connection con = getConnection ();
        PreparedStatement st = con.prepareStatement("SELECT * FROM Possede WHERE leSecouriste= ? AND laCompetence= ?")) {
            st.setString (1, Long.toString(code));
            st.setString (2, intitule);
            try(ResultSet rs = st.executeQuery ()){
                if (rs.next()) {
                    String secouristeCherche = rs.getString("leSecouriste");
                    String compCherche = rs.getString("laCompetence");

                    DAOSecouriste secourDAO = new DAOSecouriste();
                    DAOCompetence compDAO = new DAOCompetence();

                    Secouriste secour = secourDAO.findByID(secouristeCherche);
                    Competence comp = compDAO.findByID(compCherche);

                    Possede p = new Possede(secour, comp);
                    return p;
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
    public List<Possede> findAll () {
        List <Possede> p = new LinkedList <>();
        try (Connection con = getConnection ();
            Statement st = con.createStatement ();
            ResultSet rs = st.executeQuery("SELECT * FROM Possede")) {
            while (rs.next()) {
                String secouristeCherche = rs.getString("leSecouriste");
                String compCherche = rs.getString("laCompetence");

                DAOSecouriste secourDAO = new DAOSecouriste();
                DAOCompetence compDAO = new DAOCompetence();

                Secouriste secour = secourDAO.findByID(secouristeCherche);
                Competence comp = compDAO.findByID(compCherche);

                p.add(new Possede(secour, comp));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return p;
    }

    /**
     * @return null
     */
    @Override
    public Possede findByID(String code) {
        return null;
    }

    /**
     * @return null
     */
    @Override
    public Possede findByID(long idSecouriste, long idJour) {
        return null;
    }

    /**
     * @return null
     */
    @Override
    public Possede findByID(long code) {
        return null;
    }

    /**
     * @return null
     */
    @Override
    public Possede findByID(String intitule1, String intitule2) {
        return null;
    }

    /**
     * @return null
     */
    @Override
    public Possede findByID(long idSecouriste, String intitule, long idDPS) {
        return null;
    }
}
