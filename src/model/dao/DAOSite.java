package model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import model.persistence.Site;
/**
 * Fais interagir le code java avec la base de données pour modifier la table Site
 * @author Antoine Rena
 */
public class DAOSite extends DAO<Site> {

    /**
     * Insère un nouveau site dans la table Site
     * @param s le site à ajouter
     * @return le nombre de lignes affectées, ou -1 en cas d'erreur
     */
    @Override
    public int create(Site s) {
        String query = "INSERT INTO Site(code,nom,longitude,latitude) VALUES('" + s.getCode() + "','" + s.getNom() + "','" + s.getLongitude() + "','" + s.getLatitude() + "')";
        try (Connection con = getConnection (); Statement st = con.createStatement ()){
            return st.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    /**
     * Met à jour un site existant dans la table Site
     * @param s le site à modifier
     * @return le nombre de lignes affectées, ou -1 en cas d'erreur
     */
    @Override
    public int update(Site s) {
        String query = "UPDATE Site SET code='" + s.getCode() + "', nom='" + s.getNom() + "', longitude='" + s.getLongitude() + "', latitude='" + s.getLatitude() + "' WHERE code=" + s.getCode() + "'";
        try (Connection con = getConnection (); Statement st = con.createStatement ()){
            return st.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    /**
     * Supprime un site de la table Site
     * @param s le site à supprimer
     * @return le nombre de lignes affectées, ou -1 en cas d'erreur
     */
    @Override
    public int delete(Site s) {
        String query = "DELETE FROM Site WHERE code='" + s.getCode() + "'";
        try (Connection con = getConnection ();
            Statement st = con.createStatement ()) {
            return st.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return -1;
        }
    }

    /**
     * Récupère tous les sites de la table Site
     * @return une liste de tous les sites
     */
    @Override
    public List<Site> findAll() {
        List <Site> site = new LinkedList <>();
        try (Connection con = getConnection ();
            Statement st = con.createStatement ();
            ResultSet rs = st.executeQuery("SELECT * FROM Site")) {
            while (rs.next()) {
                String code = rs.getString("code");
                String nom = rs.getString("nom");
                float longitude = Float.parseFloat(rs.getString("longitude"));
                float latitude = Float.parseFloat(rs.getString("latitude"));
                site.add(new Site(code, nom,longitude,latitude));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return site;
    }

    /**
     * Cherche un site par son code
     * @param code le code du site à trouver
     * @return le site trouvé, ou null si non trouvé
     */
    @Override
    public Site findByID(String code) {
        try (Connection con = getConnection ();
        PreparedStatement st = con.prepareStatement("SELECT * FROM Site WHERE code= ?")) {
            st.setString (1, code);
            try(ResultSet rs = st.executeQuery ()){
                if (rs.next()) {
                    String id = rs.getString("code");
                    String nom = rs.getString("nom");
                    float longitude = Float.parseFloat(rs.getString("longitude"));
                    float latitude = Float.parseFloat(rs.getString("latitude"));
                    return new Site(id, nom, longitude, latitude);
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
    public Site findByID(long id) {
        return null;
    }

    /**
     * @return null
     */
    @Override
    public Site findByID(long idSecouriste, long idJour) {
        return null;
    }

    /**
     * @return null
     */
    @Override
    public Site findByID(String intitule1, String intitule2) {
        return null;
    }

    /**
     * @return null
     */
    @Override
    public Site findByID(long code, String intitule) {
        return null;
    }

    /**
     * @return null
     */
    @Override
    public Site findByID(long idSecouriste, String intitule, long idDPS) {
        return null;
    }
}
