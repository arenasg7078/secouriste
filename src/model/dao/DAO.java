package model.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
/**
 * Interface définissant les DAO
 * @author Antoine Rena
 */
public abstract class DAO <T> {
    private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql:///sae_secours";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";
    protected Connection getConnection () throws SQLException {
        // Charger la classe du pilote
        try {
            Class.forName(DRIVER_CLASS_NAME);
        } catch (ClassNotFoundException ex) {
            System.out.println("class not found");
            System.out.println(ex.getMessage());
            return null;
        }
        // Obtenir la connection
        return DriverManager.getConnection(URL , USERNAME , PASSWORD);
    }
    public abstract List <T> findAll ();
    public abstract T findByID(long id);
    public abstract T findByID(String code);
    public abstract T findByID(long idSecouriste, long idJour);
    public abstract T findByID(String intitule1, String intitule2);
    public abstract T findByID(long code, String intitule);
    public abstract T findByID(long idSecouriste, String intitule, long idDPS);
    public abstract int update(T element);
    public abstract int delete(T element);
    public abstract int create(T element);
}