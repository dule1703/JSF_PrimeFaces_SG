package dbbroker;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import model.Glasaci;
import model.Opstine;

public class DBBroker {

    private Connection conn;
    private Statement st;
    private PreparedStatement ps = null;

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

    /*Metode za ControllerGlasaci*/
    public boolean snimi_izmeni_obrisi_Glasaca(Glasaci glasaci) {

        try {
            st = conn.createStatement();
            String upit;
            if(glasaci.getStatus().equals("insert")){
                upit = "INSERT INTO snp_glasaci (ime, prezime, adresa, mesto, biracko_mesto, broj_telefona, datum, jmbg, datum_rodj, "
                        + "nosilac_glasova, ime_nosioca_glasova, opstinski_poverenik, regionalni_poverenik, republicki_poverenik, "
                        + "username, password) VALUES('" + glasaci.getIme() +"', '" + glasaci.getPrezime() +"', '" 
                        + glasaci.getAdresa() + "', '" + glasaci.getMesto()+ "', '" + glasaci.getBiracko_mesto() +"', '" 
                        + glasaci.getBroj_telefona()+ "', '" + glasaci.getDatum()+ "', '" + glasaci.getJmbg()+ "', '" + glasaci.getDatum_rodj()
                        + "', '" + glasaci.getNosilac_glasova() + "', '" + glasaci.getIme_nosioca_glasova() + "', '" + glasaci.getOpstinski_poverenik()
                        + "', '" + glasaci.getRegionalni_poverenik() + "', '" + glasaci.getRepublicki_poverenik() + "', '" 
                        + glasaci.getUsername() + "', '" + glasaci.getPassword() + "')";
                st.executeUpdate(upit);
                System.out.println("Успешно снимљен гласач!");
            }else if(glasaci.getStatus().equals("update")){
                upit = "UPDATE snp_glasaci SET ime = '" + glasaci.getIme() +"', prezime = '" + glasaci.getPrezime() +"', adresa = '" 
                        + glasaci.getAdresa() + "', mesto = '" + glasaci.getMesto()+ "', biracko_mesto = '" + glasaci.getBiracko_mesto() 
                        +"', broj_telefona = '" + glasaci.getBroj_telefona()+ "', datum = '" + glasaci.getDatum()+ "', jmbg = '" + glasaci.getJmbg()
                        + "', datum_rodj = '" + glasaci.getDatum_rodj() + "', nosilac_glasova = '" + glasaci.getNosilac_glasova() 
                        + "', ime_nosioca_glasova ='" + glasaci.getIme_nosioca_glasova() + "', opstinski_poverenik = '" + glasaci.getOpstinski_poverenik() 
                        + "', regionalni_poverenik = '" + glasaci.getRegionalni_poverenik() + "', republicki_poverenik ='" + glasaci.getRepublicki_poverenik() 
                        + "', username = '" + glasaci.getUsername() + "', password = '" + glasaci.getPassword() + "' WHERE id = '" + glasaci.getId() + "'";
                st.executeUpdate(upit);
                System.out.println("Успешно ажуриран гласач!");
            }else if(glasaci.getStatus().equals("delete")) {
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
            //  String upit = "UPDATE snp_glasaci SET ime = 'Душко' WHERE id = 19";
            String upit ;

           upit = "DELETE FROM opstine WHERE id= 200";
                st.executeUpdate(upit);
                System.out.println("Успешно избрисана општина!");

            /*ResultSet rs = st.executeQuery(upit);
          while(rs.next()){
             int id = rs.getInt("id");
             String ime = rs.getString("ime");
             String prezime = rs.getString("prezime");
             String adresa = rs.getString("adresa");
             
             System.out.println(id);
             System.out.println(ime);
             System.out.println(prezime);
             System.out.println(adresa);
          }*/
        } catch (Exception e) {

        }
        return true;
    }

    /*
 public static void main(String args[]) {
        DBBroker dbb = new DBBroker();
        Opstine opst = new Opstine();

        opst.setOpstina_id(32);
        opst.setRegion_id(26);
        opst.setStatus("insert");
        dbb.pokreniDBTransakciju();
        //   dbb.snimi_izmeni_obrisi_Glasaca();
        dbb.testMethod();
    }*/
}
