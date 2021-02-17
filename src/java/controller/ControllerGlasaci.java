package controller;

import dbbroker.DBBroker;
import model.Glasaci;

public class ControllerGlasaci {

    DBBroker dbb = new DBBroker();
    Boolean trans;

    public boolean checkLogin(String username, String password) {
        dbb.pokreniDBTransakciju();
        trans = dbb.checkLogin(username, password);
        if (trans) {
            dbb.potvrdiDBTransakciju();
            return true;
        } else {
            dbb.ponistiDBTransakciju();
            return false;
        }
    }

    public void snimiGlasaca(String ime, String prezime, String adresa, int mesto, String biracko_mesto, String broj_telefona,
                             String datum, String jmbg, String datum_rodj, String nosilac_glasova, String ime_nosioca_glasova,
                             String opstinski_poverenik, String regionalni_poverenik, String republicki_poverenik,
                             String username, String password) {
        Glasaci glasac = new Glasaci();
        glasac.setIme(ime);
        glasac.setPrezime(prezime);
        glasac.setAdresa(adresa);
        glasac.setMesto(mesto);
        glasac.setBiracko_mesto(biracko_mesto);
        glasac.setBroj_telefona(broj_telefona);
        glasac.setDatum(datum);
        glasac.setJmbg(jmbg);
        glasac.setDatum_rodj(datum_rodj);
        glasac.setNosilac_glasova(nosilac_glasova);
        glasac.setIme_nosioca_glasova(ime_nosioca_glasova);
        glasac.setOpstinski_poverenik(opstinski_poverenik);
        glasac.setRegionalni_poverenik(regionalni_poverenik);
        glasac.setRepublicki_poverenik(republicki_poverenik);
        glasac.setUsername(username);
        glasac.setPassword(password);              
        glasac.setStatus("insert");

        dbb.pokreniDBTransakciju();

        boolean rez = dbb.snimi_izmeni_obrisi_Glasaca(glasac);
        if (rez) {
            dbb.potvrdiDBTransakciju();
        } //commit
        else {
            dbb.ponistiDBTransakciju();
        } //rollback
    }

    public void izmeniGlasaca(int id,String ime, String prezime, String adresa, int mesto, String biracko_mesto, String broj_telefona,
                             String datum, String jmbg, String datum_rodj, String nosilac_glasova, String ime_nosioca_glasova,
                             String opstinski_poverenik, String regionalni_poverenik, String republicki_poverenik,
                             String username, String password) {
        Glasaci glasac = new Glasaci();
        glasac.setId(id);
        glasac.setIme(ime);
        glasac.setPrezime(prezime);
        glasac.setAdresa(adresa);
        glasac.setMesto(mesto);
        glasac.setBiracko_mesto(biracko_mesto);
        glasac.setBroj_telefona(broj_telefona);
        glasac.setDatum(datum);
        glasac.setJmbg(jmbg);
        glasac.setDatum_rodj(datum_rodj);
        glasac.setNosilac_glasova(nosilac_glasova);
        glasac.setIme_nosioca_glasova(ime_nosioca_glasova);
        glasac.setOpstinski_poverenik(opstinski_poverenik);
        glasac.setRegionalni_poverenik(regionalni_poverenik);
        glasac.setRepublicki_poverenik(republicki_poverenik);
        glasac.setUsername(username);
        glasac.setPassword(password);       
        glasac.setStatus("update");

        dbb.pokreniDBTransakciju();

        boolean rez = dbb.snimi_izmeni_obrisi_Glasaca(glasac);
        if (rez) {
            dbb.potvrdiDBTransakciju();
        } //commit
        else {
            dbb.ponistiDBTransakciju();
        } //rollback
    }

    public void obrisiGlasaca(int id) {
        Glasaci glasac = new Glasaci();
        glasac.setId(id);
        
        glasac.setStatus("delete");
        
        dbb.pokreniDBTransakciju();

        boolean rez = dbb.snimi_izmeni_obrisi_Glasaca(glasac);

        if (rez) {
            dbb.potvrdiDBTransakciju();
        } //commit
        else {
            dbb.ponistiDBTransakciju();
        } //rollback
    }

    public boolean testMethod() {
        return true;
    }

    /**/ public static void main(String args[]) {
        ControllerGlasaci ctrlGlas = new ControllerGlasaci();
        /* Boolean bool = ctrlGlas.checkLogin("oo_mzvornik", "oo_mzvornik");
        System.out.println(bool);*/
        ctrlGlas.izmeniGlasaca(21, "Јована", "Павловић", "Николе Тесле 43", 106, "Центар 2", "064777777777", "15/02/2021", "2222222222222", 
                "19/02/1988", "Не, немам носиоца", "", "", "", "", "", "");

    }
}
