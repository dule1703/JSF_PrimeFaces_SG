package dbbroker;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import model.Glasaci;
import model.Opstine;
import model.SpisakOpstina;

public class DBBroker {

    private Connection conn;
    private Statement st;
    private PreparedStatement ps = null;
    private String upit;

    public void pokreniDBTransakciju() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Успешно учитан драјвер!");
            conn = DriverManager.getConnection("jdbc:mysql://185.119.88.77:3306/ddweba_javaSG?characterEncoding=UTF-8", "ddweba_javaSG", "javaSG_2020");
            System.out.println("Успешно повезана база!");
            conn.setAutoCommit(false);
        } catch (ClassNotFoundException e) {
            System.err.println("Greska prilikom ucitavanja driver-a... -> " + e);
        } catch (SQLException e) {
            System.err.println("Greska prilikom otvaranja konekcije sa bazom... -> " + e);
        }
    }

    public void potvrdiDBTransakciju() {
        try {
            conn.commit();
            conn.close();
        } catch (SQLException e) {
            System.err.println("Greska prilikom commit operacije... -> " + e);
        }
    }

    public void ponistiDBTransakciju() {
        try {
            conn.rollback();
            conn.close();
        } catch (SQLException e) {
            System.err.println("Greska prilikom rollback operacije... -> " + e);
        }
    }

    /*Provera username-a i password-a*/
    public boolean checkLogin(String username, String password) {

        try {

            ps = conn.prepareStatement("SELECT username, password FROM snp_glasaci WHERE username = ? and password = ? ");
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            Boolean bool = false;
            if (rs.next()) {
                bool = true;
                System.out.println(rs.getString("username"));
                return bool;
            } else {
                System.out.println("Погрешно корисничко име или шифра!");
                return bool;
            }
        } catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
            return false;
        }
    }

    /*Metode za ControllerOpstine*/
    public boolean snimi_izmeni_obrisi_Opstinu(Opstine opstine) {
        try {

            st = conn.createStatement();
            String upit;

            if (opstine.getStatus().equals("insert")) {
                upit = "SELECT * FROM opstine WHERE opstina_id = " + opstine.getOpstina_id();
                ResultSet rs = st.executeQuery(upit);
                if (rs.next()) {
                    System.out.println("Већ постоји општина коју желите да убаците!");
                } else {
                    upit = "INSERT INTO opstine VALUES(id, " + opstine.getOpstina_id() + "," + opstine.getRegion_id() + ")";
                    st.executeUpdate(upit);
                    System.out.println("Успешно снимљена општина!");
                }

            } else if (opstine.getStatus().equals("update")) {

                upit = "UPDATE opstine SET opstina_id='" + opstine.getOpstina_id() + "', region_id='" + opstine.getRegion_id() + "'WHERE id='" + opstine.getId() + "'";
                st.executeUpdate(upit);
                System.out.println("Успешно измењена општина!");

            } else {
                upit = "DELETE FROM opstine WHERE id= 200";
                st.executeUpdate(upit);
                System.out.println("Успешно избрисана општина!");
            }

            return true;
        } catch (SQLException e) {
            System.err.println("Objekat ne moze da se zapamti u bazi... -> " + e);

        }
        return false;

    }

    public Opstine nadjiOpstinu(int id) {
        Opstine opstine = new Opstine();
        try {

            String upit = "SELECT * FROM opstine where id='" + id + "'";
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {

                int rbId = rs.getInt("id");
                int opstinaId = rs.getInt("opstina_id");
                int region_id = rs.getInt("region_id");
                //punjenje objekta Opstine
                System.out.println(rbId);
                System.out.println(opstinaId);
                System.out.println(region_id);
                opstine.setId(id);
                opstine.setOpstina_id(opstinaId);
                opstine.setRegion_id(region_id);

            }
        } catch (Exception e) {
        }
        return opstine;
    }

    public ArrayList<SpisakOpstina> ucitajOpstine(String currentUser) {        
        ArrayList<SpisakOpstina> ol = new ArrayList<>();
        SpisakOpstina opst;
        try {
            st = conn.createStatement();
            upit = "SELECT so.id AS so_id, so.naziv_opstine FROM spisak_opstina so "
                    + "INNER JOIN opstine o ON o.opstina_id = so.id "
                    + "INNER JOIN regioni r ON r.id = o.region_id "
                    + "WHERE  o.region_id = (SELECT id FROM regioni WHERE naziv_regiona = (SELECT r.naziv_regiona FROM regioni r "
                                                                                             + "INNER JOIN opstine o ON r.id = o.region_id "
                                                                                             + "INNER JOIN spisak_opstina so ON so.id = o.opstina_id "
                                                                                             + "INNER JOIN snp_glasaci g ON so.id = g.mesto "
                                                                                             + "WHERE g.username='" + currentUser + "' AND g.regionalni_poverenik='да')) "
                    + "OR so.naziv_opstine = (SELECT so.naziv_opstine FROM opstine o "
                                            + "INNER JOIN spisak_opstina so ON so.id = o.opstina_id "
                                            + "INNER JOIN regioni r ON o.region_id = r.id "
                                            + "INNER JOIN snp_glasaci g ON so.id = g.mesto "
                                        + "WHERE g.username='" + currentUser + "' AND g.opstinski_poverenik='да')";
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {
                int id = rs.getInt("so_id");
                String naziv_opstine = rs.getString("naziv_opstine");
                opst = new SpisakOpstina();
                opst.setId(id);
                opst.setNaziv_opstine(naziv_opstine);
                ol.add(opst);

            }

        } catch (Exception e) {

        }

        /*
        for (SpisakOpstina so : ol) {
            System.out.print(so.getId() + " ");
            System.out.println(so.getNaziv_opstine());
        }*/
        return ol;
    }

    /*Metode za ControllerGlasaci*/
    public boolean snimi_izmeni_obrisi_Glasaca(Glasaci glasaci) {
        int sifraMesta = 0;

        try {
            st = conn.createStatement();
            String upit;
            String upitSelect;
            upitSelect = "SELECT so.id, so.naziv_opstine FROM spisak_opstina so INNER JOIN opstine o ON o.opstina_id = so.id WHERE so.naziv_opstine LIKE '%" + glasaci.getMesto() + "'";
            ResultSet rs = st.executeQuery(upitSelect);
            while (rs.next()) {
                sifraMesta = rs.getInt("id");
                System.out.println(sifraMesta);
            }

            if (glasaci.getStatus().equals("insert")) {
                upit = "INSERT INTO snp_glasaci (ime, prezime, adresa, mesto, biracko_mesto, broj_telefona, datum, jmbg, datum_rodj, "
                        + "nosilac_glasova, ime_nosioca_glasova, opstinski_poverenik, regionalni_poverenik, republicki_poverenik, "
                        + "username, password) VALUES('" + glasaci.getIme() + "', '" + glasaci.getPrezime() + "', '"
                        + glasaci.getAdresa() + "', '" + sifraMesta + "', '" + glasaci.getBiracko_mesto() + "', '"
                        + glasaci.getBroj_telefona() + "', '" + glasaci.getDatum() + "', '" + glasaci.getJmbg() + "', '" + glasaci.getDatum_rodj()
                        + "', '" + glasaci.getNosilac_glasova() + "', '" + glasaci.getIme_nosioca_glasova() + "', '" + glasaci.getOpstinski_poverenik()
                        + "', '" + glasaci.getRegionalni_poverenik() + "', '" + glasaci.getRepublicki_poverenik() + "', '"
                        + glasaci.getUsername() + "', '" + glasaci.getPassword() + "')";
                st.executeUpdate(upit);
                System.out.println("Успешно снимљен гласач!");
            } else if (glasaci.getStatus().equals("update")) {
                upit = "UPDATE snp_glasaci SET ime = '" + glasaci.getIme() + "', prezime = '" + glasaci.getPrezime() + "', adresa = '"
                        + glasaci.getAdresa() + "', mesto = '" + sifraMesta + "', biracko_mesto = '" + glasaci.getBiracko_mesto()
                        + "', broj_telefona = '" + glasaci.getBroj_telefona() + "', datum = '" + glasaci.getDatum() + "', jmbg = '" + glasaci.getJmbg()
                        + "', datum_rodj = '" + glasaci.getDatum_rodj() + "', nosilac_glasova = '" + glasaci.getNosilac_glasova()
                        + "', ime_nosioca_glasova ='" + glasaci.getIme_nosioca_glasova() + "', opstinski_poverenik = '" + glasaci.getOpstinski_poverenik()
                        + "', regionalni_poverenik = '" + glasaci.getRegionalni_poverenik() + "', republicki_poverenik ='" + glasaci.getRepublicki_poverenik()
                        + "', username = '" + glasaci.getUsername() + "', password = '" + glasaci.getPassword() + "' WHERE id = '" + glasaci.getId() + "'";
                st.executeUpdate(upit);
                System.out.println("Успешно ажуриран гласач!");
            } else if (glasaci.getStatus().equals("delete")) {
                upit = "DELETE FROM snp_glasaci WHERE id = '" + glasaci.getId() + "'";
                st.executeUpdate(upit);
                System.out.println("Успешно избрисан гласач!");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
        return true;
    }

    public boolean testMethod() {
        try {
            st = conn.createStatement();

            String upit;

            upit = "DELETE FROM opstine WHERE id= 200";
            st.executeUpdate(upit);
            System.out.println("Успешно избрисана општина!");

        } catch (Exception e) {

        }
        return true;
    }

    /*
    public static void main(String args[]) {
        /*  Glasaci glas = new Glasaci();
        glas.setIme("Марија");
        glas.setPrezime("Павловић");
        glas.setAdresa("Николе Тесле 43");
        glas.setMesto("Мали Зворник");
        glas.setBiracko_mesto("Центар 2");
        glas.setBroj_telefona("064777777777");
        glas.setDatum("15/02/2021");
        glas.setJmbg("2222222222222");
        glas.setDatum_rodj("19/02/1988");
        glas.setNosilac_glasova("Не, немам носиоца");
        glas.setIme_nosioca_glasova("");
        glas.setOpstinski_poverenik("");
        glas.setRegionalni_poverenik("");
        glas.setRepublicki_poverenik("");
        glas.setUsername("");
        glas.setPassword("");
        glas.setStatus("insert");
        DBBroker dbb = new DBBroker();

        dbb.pokreniDBTransakciju();

        dbb.ucitajOpstine("jb_poverenik");

    }*/
}
