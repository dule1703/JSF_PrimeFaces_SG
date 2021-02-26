package views_manage_beans;

import controller.ControllerOpstine;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.ListIterator;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import model.SpisakOpstina;

@ManagedBean(name = "opstineMB")
@SessionScoped
public class OpstineMB implements Serializable {

    @ManagedProperty(value = "#{glasaciMB}")
    private GlasaciMB glasaciMB;

    private static final long serialVersionUID = 1L;
    private int id;
    private String naziv_opstine;

    ArrayList<String> listaNazivaOpstina;

    ControllerOpstine ctrlOpstine;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv_opstine() {
        return naziv_opstine;
    }

    public void setNaziv_opstine(String naziv_opstine) {
        this.naziv_opstine = naziv_opstine;
    }

    public ArrayList<String> getListaNazivaOpstina() {
        return listaNazivaOpstina;
    }

    public void setListaNazivaOpstina(ArrayList<String> listaNazivaOpstina) {
        this.listaNazivaOpstina = listaNazivaOpstina;
    }

    public GlasaciMB getGlasaciMB() {
        return glasaciMB;
    }

    public void setGlasaciMB(GlasaciMB glasaciMB) {
        this.glasaciMB = glasaciMB;
    }

    public void vratiOpstine() {
        ArrayList<SpisakOpstina> spisakOpstina = ctrlOpstine.vratiOpstine(glasaciMB.getUsername());
        ArrayList<String> listaNaziva = new ArrayList<>();
        ListIterator<SpisakOpstina> listItr = spisakOpstina.listIterator();
        while (listItr.hasNext()) {
            listaNaziva.add(listItr.next().getNaziv_opstine());
        }

        listaNazivaOpstina = listaNaziva;
        /*for(String li: listaNaziva){
           System.out.println(li);
        }*/
    }

    public OpstineMB() {
        ctrlOpstine = new ControllerOpstine();

    }

    /*
    public static void main(String args[]) {
        OpstineMB opst = new OpstineMB();
       
    }*/
}
