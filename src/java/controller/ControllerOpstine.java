package controller;

import dbbroker.DBBroker;
import java.util.ArrayList;
import java.util.Date;
import model.Opstine;
import model.SpisakOpstina;

public class ControllerOpstine {

    DBBroker dbb = new DBBroker();
    Boolean trans = true;
    Opstine opstine;

    public void snimiOpstinu(int opstina_id, int region_id) {
        opstine = new Opstine();
        opstine.setOpstina_id(opstina_id);
        opstine.setRegion_id(region_id);
        opstine.setStatus("insert");

        dbb.pokreniDBTransakciju();

        boolean rez = dbb.snimi_izmeni_obrisi_Opstinu(opstine);

        if (rez) {
            dbb.potvrdiDBTransakciju();
        } //commit
        else {
            dbb.ponistiDBTransakciju();
        } //rollback
    }

    public void izmeniOpstinu(int id, int opstina_id, int region_id) {
        opstine = new Opstine();
        opstine.setId(id);
        opstine.setOpstina_id(opstina_id);
        opstine.setRegion_id(region_id);
        opstine.setStatus("update");

        dbb.pokreniDBTransakciju();

        boolean rez = dbb.snimi_izmeni_obrisi_Opstinu(opstine);

        if (rez) {
            dbb.potvrdiDBTransakciju();
        } //commit
        else {
            dbb.ponistiDBTransakciju();
        } //rollback
    }

    public void obrisiOpstine(int id) {
        opstine = new Opstine();
        opstine.setId(id);
        opstine.setStatus("delete");
        dbb.pokreniDBTransakciju();

        boolean rez = dbb.snimi_izmeni_obrisi_Opstinu(opstine);

        if (rez) {
            dbb.potvrdiDBTransakciju();
        } //commit
        else {
            dbb.ponistiDBTransakciju();
        } //rollback
    }

    public Opstine nadjiOpstinu(int id) {
        opstine = new Opstine();
        dbb.pokreniDBTransakciju();
        opstine = dbb.nadjiOpstinu(id);

        if (trans) {
            dbb.potvrdiDBTransakciju();
            return opstine;
        } else {
            dbb.ponistiDBTransakciju();
            return null;
        }
    }
    
    public ArrayList<SpisakOpstina> vratiOpstine(String currentUser){
        ArrayList<SpisakOpstina> ol;
        dbb.pokreniDBTransakciju();
        ol = dbb.ucitajOpstine(currentUser);
        /* for(SpisakOpstina li: ol){
               System.out.print(li.getId() + " ");
               System.out.println(li.getNaziv_opstine());
            }*/
        
        if(trans){
            dbb.potvrdiDBTransakciju();
            return ol;
            
        }else {
            dbb.ponistiDBTransakciju();
            return null;
        }
    
    }

    public void testMethod(){
   
    
    }

   /* public static void main(String args[]) {
        ControllerOpstine ctrlOpst = new ControllerOpstine();
       
        ctrlOpst.vratiOpstine("oo_mzvornik");
      
    }*/
}
