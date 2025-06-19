package model.dao;
import model.persistence.*;
public class TestDAO {
    public static void main(String[] args) {
        Sport s = new Sport("s1", "criquet");
        DAOSport d = new DAOSport();
        int c = d.create(s);

        Journee j = new Journee(1,0,0,0);
        DAOJournee d2 = new DAOJournee();
        int c2 = d2.create(j);

        Site si = new Site("s1", "site", 0,0);
        DAOSite d3 = new DAOSite();
        int c3 = d3.create(si);

        DPS dps = new DPS(1, 0,0, j, s, si);
        DAODPS d4 = new DAODPS();
        int c4 = d4.create(dps);
    }
}