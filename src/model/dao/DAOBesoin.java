package model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import model.persistence.Besoin;
import model.persistence.DPS;
import model.persistence.Competence;
/**
 * Fais interagir le code java avec la base de données pour modifier la table Besoin
 * 
 * @author Antoine Rena
 */
public class DAOBesoin extends DAO<Besoin>{
    @Override
    /**
     * Ajoute un élément dans la table besoin
     * @param b le besoin à ajouter a la table
     * @return -1 si l'appel n'a pas fonctionné
     */
    public int create(Besoin b) {
        String query = "INSERT INTO Besoin(nombre, leDPS, laCompetence) VALUES ('" + b.getNombre() + "','" + b.getDPS().getId() + "','" + b.getCompetence().getIntitule() +" ')";
        try (Connection con = getConnection (); Statement st = con.createStatement ()) {
            return st.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return -1;
        }
    }

    /**
     * Modifie un élément de la table besoin
     * @param b le besoin à modifier
     */
    @Override
    public int update(Besoin b) {
        String query = "UPDATE Besoin SET nombre='" + b.getNombre() + "', leDPS='" + b.getDPS().getId() + "', laCompetence='" + b.getCompetence().getIntitule() + "' WHERE leDPS=" + b.getDPS().getId() + "' AND laCompetence=" + b.getCompetence().getIntitule() + "'";
        try (Connection con = getConnection (); Statement st = con.createStatement ()){
            return st.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    /**
     * Supprime un élément de la table besoin
     * @param b le besoin à supprimer
     * @return -1 si l'appel n'a pas fonctionné
     */
    @Override   
    public int delete(Besoin b) {
        String query = "DELETE FROM Besoin WHERE leDSP=" + b.getDPS().getId() + "' AND laCompetence=" + b.getCompetence().getIntitule() + "'";
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
     * @param code la clé primaire de dps
     * @param intitule la clé primaire de competence
     * @return null si l'élément n'est pas trouvé, sinon, renvoie l'élément
     */
    @Override
    public Besoin findByID(long code, String intitule) {
        try (Connection con = getConnection ();
        PreparedStatement st = con.prepareStatement("SELECT * FROM Besoin WHERE leDPS= ? AND laCompetence= ?")) {
            st.setString (1, Long.toString(code));
            st.setString (2, intitule);
            try(ResultSet rs = st.executeQuery ()){
                if (rs.next()) {
                    int nombre = Integer.parseInt(rs.getString("nombre"));
                    String dpsCherche = rs.getString("leDPS");
                    String compCherche = rs.getString("laCompetence");
                    
                    DAODPS dpsDAO = new DAODPS();
                    DAOCompetence compDAO = new DAOCompetence();

                    DPS dps = dpsDAO.findByID(dpsCherche);
                    Competence comp = compDAO.findByID(compCherche);

                    Besoin p = new Besoin(nombre, dps, comp);
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
    public List<Besoin> findAll () {
        List <Besoin> b = new LinkedList <>();
        try (Connection con = getConnection ();
            Statement st = con.createStatement ();
            ResultSet rs = st.executeQuery("SELECT * FROM Besoin")) {
            while (rs.next()) {
                int nombre = Integer.parseInt(rs.getString("nombre"));
                String dpsCherche = rs.getString("leDPS");
                String compCherche = rs.getString("laCompetence");
                    
                DAODPS dpsDAO = new DAODPS();
                DAOCompetence compDAO = new DAOCompetence();

                DPS dps = dpsDAO.findByID(dpsCherche);
                Competence comp = compDAO.findByID(compCherche);

                b.add(new Besoin(nombre, dps, comp));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return b;
    }

    /**
     * @return null
     */
    @Override
    public Besoin findByID(String code) {
        return null;
    }

    /**
     * @return null
     */
    @Override
    public Besoin findByID(long idSecouriste, long idJour) {
        return null;
    }
    
    /**
     * @return null
     */
    @Override
    public Besoin findByID(long code) {
        return null;
    }

    /**
     * @return null
     */
    @Override
    public Besoin findByID(String intitule1, String intitule2) {
        return null;
    }

    /**
     * @return null
     */
    @Override
    public Besoin findByID(long idSecouriste, String intitule, long idDPS) {
        return null;
    }
}
