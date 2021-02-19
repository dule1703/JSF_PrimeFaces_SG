package views_manage_beans;

import controller.ControllerOpstine;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name="opstineMB")
@SessionScoped
public class OpstineMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String naziv_opstine;
    
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
    
    
    public OpstineMB() {
        ctrlOpstine = new ControllerOpstine();
    }
    
}
