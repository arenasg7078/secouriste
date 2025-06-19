package model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import model.persistence.Competence;
import model.persistence.DPS;
import model.persistence.Journee;
import model.persistence.Site;
import model.persistence.Sport;
/**
 * Fais interagir le code java avec la base de données pour modifier la table DPS
 * 
 * @author Antoine Rena
 */
public class DAODPS extends DAO<DPS> {
    
    /**
     * Insère un élément dans la table dsp
     * @param dps le dps à ajouter
     * @return -1 si il y a une erreur
     */
    @Override
    public int create(DPS dps) {
        String query = "INSERT INTO DPS(id, horaire_depart, horaire_fin, laJournee, leSport, leSite) VALUES('" + dps.getId() +  "','" + dps.getHoraireDep() + "','" + dps.getHoraireFin() + "','" + dps.getJournee().getId() + "','" + dps.getSport().getCode() + "','" + dps.getSite().getCode() + "')";
        try (Connection con = getConnection (); Statement st = con.createStatement ()){
            return st.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    /**
     * Met un élément à jour dans la table dsp
     * @param dps le dsp à modifier
     * @return -1 si il y a une erreur
     */
    @Override
    public int update(DPS dps) {
        String query = "UPDATE DPS SET id='" + dps.getId() + "', horaire_depart='" + dps.getHoraireDep() + "', horaire_fin='" + dps.getHoraireFin() + "', laJournee='" + dps.getJournee().getId() + "', leSport='" + dps.getSport().getCode() + "', leSite='" + dps.getSite() + "' WHERE id='" + dps.getId() + "'";
        try (Connection con = getConnection (); Statement st = con.createStatement ()){
            return st.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    /**
     * supprime un élément de la table dps
     * @param dps le dps à supprimer
     * @return -1 si il y a une erreur
     */
    @Override
    public int delete(DPS dps) {
        String query = "DELETE FROM DPS WHERE id='" + dps.getId() + "'";
        try (Connection con = getConnection();
            Statement st = con.createStatement ()) {
            return st.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return -1;
        }
    }

    /**
     * Cherche un élément en fonction de son ID 
     * @param id l'identifiant du dps
     * @return null si l'élément n'est pas trouvé, sinon, renvoie l'élément
     */
    @Override
    public DPS findByID(long id) {
        try (Connection con = getConnection ();
        PreparedStatement st = con.prepareStatement("SELECT * FROM DPS JOIN Site ON Site.code = DPS.leSite JOIN Sport ON Sport.code = DPS.leSport JOIN Journee ON journee.idJournee = DPS.laJournee JOIN Besoin ON Besoin.leDPS = DPS.id WHERE id= ?")) {
            st.setString (1, Long.toString(id));
            try(ResultSet rs = st.executeQuery ()){
                if (rs.next()) {
                    long code = Long.parseLong(rs.getString("idDPS"));
                    int horaireDepart = Integer.parseInt(rs.getString("horaire_depart"));
                    int horaireFin = Integer.parseInt(rs.getString("horaire_fin"));
                    String sportCherche = rs.getString("leSport");
                    String siteCherche = rs.getString("leSite");
                    String jourCherche = rs.getString("laJournee");

                    DAOSport SportDAO = new DAOSport();
                    DAOSite siteDAO = new DAOSite();
                    DAOJournee jourDAO = new DAOJournee();
                    
                    int nombre = Integer.parseInt("nombre");
                    DAOCompetence DAOc = new DAOCompetence();
                    Competence laComp = DAOc.findByID(rs.getString("laCompetence"));                    
                    Map<Competence, Integer> besoin = new HashMap<>();
                    besoin.put(laComp, nombre);

                    Sport leSport = SportDAO.findByID(sportCherche);
                    Site leSite = siteDAO.findByID(siteCherche);
                    Journee jour = jourDAO.findByID(jourCherche);

                    DPS c = new DPS(code, horaireDepart, horaireFin, jour, leSport, leSite);
                    return c;
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
     * Renvoie tous les éléments de la table dps
     * @return la liste finale
     */
    @Override
    public List<DPS> findAll () {
        List <DPS> lesDPS = new LinkedList <>();
        try (Connection con = getConnection ();
            Statement st = con.createStatement ();
            ResultSet rs = st.executeQuery("SELECT * FROM DPS")) {
            while (rs.next()) {
                long code = Long.parseLong(rs.getString("idDPS"));
                int horaireDepart = Integer.parseInt(rs.getString("horaire_depart"));
                int horaireFin = Integer.parseInt(rs.getString("horaire_fin"));
                String sportCherche = rs.getString("leSport");
                String siteCherche = rs.getString("leSite");
                String jourCherche = rs.getString("laJournee");

                DAOSport SportDAO = new DAOSport();
                DAOSite siteDAO = new DAOSite();
                DAOJournee jourDAO = new DAOJournee();
                
                int nombre = Integer.parseInt("nombre");
                DAOCompetence DAOc = new DAOCompetence();
                Competence laComp = DAOc.findByID(rs.getString("laCompetence"));                    
                Map<Competence, Integer> besoin = new HashMap<>();
                besoin.put(laComp, nombre);

                Sport leSport = SportDAO.findByID(sportCherche);
                Site leSite = siteDAO.findByID(siteCherche);
                Journee jour = jourDAO.findByID(jourCherche);
                lesDPS.add(new DPS(code, horaireDepart, horaireFin, jour, leSport, leSite));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lesDPS;
    }

    /**
     * @return null
     */
    @Override
    public DPS findByID(String code) {
        return null;
    }

    /**
     * @return null
     */
    @Override
    public DPS findByID(long idSecouriste, long idJour) {
        return null;
    }

    /**
     * @return null
     */
    @Override
    public DPS findByID(String intitule1, String intitule2) {
        return null;
    }

    /**
     * @return null
     */
    @Override
    public DPS findByID(long code, String intitule) {
        return null;
    }

    /**
     * @return null
     */
    @Override
    public DPS findByID(long idSecouriste, String intitule, long idDPS) {
        return null;
    }
}