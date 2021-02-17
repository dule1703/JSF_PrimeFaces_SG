package views_manage_beans;

import controller.ControllerGlasaci;
import controller.ControllerOpstine;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.Opstine;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "glasaciMB")
@SessionScoped
public class GlasaciMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private ControllerGlasaci ctrlGlasaci;

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

    }

    /*public static void main(String args[]){
       GlasaciMB logCheck = new GlasaciMB();
       logCheck.testMethodMB();
    }*/
}
