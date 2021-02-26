package views_manage_beans;

import controller.ControllerGlasaci;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

@ManagedBean(name = "glasaciMB")
@SessionScoped
public class GlasaciMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String ime;
    private String prezime;
    private String adresa;
    private String mesto;
    private String biracko_mesto;
    private String broj_telefona;
    private String datum;
    private String jmbg;
    private Date datum_rodj;
    private String nosilac_glasova;
    private String ime_nosioca_glasova;
    private String opstinski_poverenik;
    private String regionalni_poverenik;
    private String republicki_poverenik;
    private String username;
    private String password;
    private ArrayList<String> listaNosilacaGlasova;  
    private ArrayList<String> listaImenaNosilaca;   
    private boolean enabled;

    public void toggle() {
        enabled = !enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    

    private ControllerGlasaci ctrlGlasaci;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public String getBiracko_mesto() {
        return biracko_mesto;
    }

    public void setBiracko_mesto(String biracko_mesto) {
        this.biracko_mesto = biracko_mesto;
    }

    public String getBroj_telefona() {
        return broj_telefona;
    }

    public void setBroj_telefona(String broj_telefona) {
        this.broj_telefona = broj_telefona;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public Date getDatum_rodj() {
        return datum_rodj;
    }

    public void setDatum_rodj(Date datum_rodj) {
        this.datum_rodj = datum_rodj;
    }

    public String getNosilac_glasova() {
        return nosilac_glasova;
    }

    public void setNosilac_glasova(String nosilac_glasova) {
        this.nosilac_glasova = nosilac_glasova;
    }

    public String getIme_nosioca_glasova() {
        return ime_nosioca_glasova;
    }

    public void setIme_nosioca_glasova(String ime_nosioca_glasova) {
        this.ime_nosioca_glasova = ime_nosioca_glasova;
    }

    public String getOpstinski_poverenik() {
        return opstinski_poverenik;
    }

    public void setOpstinski_poverenik(String opstinski_poverenik) {
        this.opstinski_poverenik = opstinski_poverenik;
    }

    public String getRegionalni_poverenik() {
        return regionalni_poverenik;
    }

    public void setRegionalni_poverenik(String regionalni_poverenik) {
        this.regionalni_poverenik = regionalni_poverenik;
    }

    public String getRepublicki_poverenik() {
        return republicki_poverenik;
    }

    public void setRepublicki_poverenik(String republicki_poverenik) {
        this.republicki_poverenik = republicki_poverenik;
    }

    public ControllerGlasaci getCtrlGlasaci() {
        return ctrlGlasaci;
    }

    public void setCtrlGlasaci(ControllerGlasaci ctrlGlasaci) {
        this.ctrlGlasaci = ctrlGlasaci;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public ArrayList<String> getListaNosilacaGlasova() {
        return listaNosilacaGlasova;
    }

    public void setListaNosilacaGlasova(ArrayList<String> listaNosilacaGlasova) {
        this.listaNosilacaGlasova = listaNosilacaGlasova;
    }

    public ArrayList<String> getListaImenaNosilaca() {
        return listaImenaNosilaca;
    }

    public void setListaImenaNosilaca(ArrayList<String> listaImenaNosilaca) {
        this.listaImenaNosilaca = listaImenaNosilaca;
    }
    
    
    
    

    public String loginProject() {
        System.out.println("Ушао!");
        ctrlGlasaci = new ControllerGlasaci();
        boolean result = ctrlGlasaci.checkLogin(getUsername(), getPassword());
        if (result) {
            System.out.println(result);
            return "form";
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failure!", "Your username or password is not correct!"));
            return "";
        }
    }

    public void onDateSelect(SelectEvent<Date> event) {
        FacesContext context = FacesContext.getCurrentInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Изабрани датум:", sdf.format(event.getObject())));
    }

    public void snimiGlasaca() {
        try {
            
            DateFormat df_datum = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date today = Calendar.getInstance().getTime();
            String datumToday = df_datum.format(today);
            DateFormat df_datumRodj = new SimpleDateFormat("dd/MM/yyyy");
            String datumRodjenja = df_datumRodj.format(getDatum_rodj());
            
            ctrlGlasaci.snimiGlasaca(getIme(), getPrezime(), getAdresa(), getMesto(), getBiracko_mesto(), getBroj_telefona(), datumToday , getJmbg(),
                    datumRodjenja, getNosilac_glasova(), getIme_nosioca_glasova(), "", "", "", "", "");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Успешно снимљен гласач!", ""));
        } catch (Exception e) {

        }

    }
    
    public void clearForm(){
        setIme("");
    }

    public String testMethodMB() {

        if (username.equals("Dule") && password.equals("Dule")) {
            return "form";
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failure!", "Your username or password is not correct!"));
            return "";
        }

    }

    public GlasaciMB() {
        ctrlGlasaci = new ControllerGlasaci();
        listaNosilacaGlasova = new ArrayList<>();
        listaNosilacaGlasova.add("Изаберите опцију...");
        listaNosilacaGlasova.add("Да, имам носиоца гласова");
        listaNosilacaGlasova.add("Не, немам носиоца гласова");
        listaNosilacaGlasova.add("Ја сам носилац");
        
        listaImenaNosilaca = new ArrayList<>();
        listaImenaNosilaca.add("Marija Petrovic");
        listaImenaNosilaca.add("Petar Maric");
    }

    /*public static void main(String args[]){
       GlasaciMB logCheck = new GlasaciMB();
       logCheck.testMethodMB();
    }*/
}
