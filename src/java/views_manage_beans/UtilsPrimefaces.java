package views_manage_beans;

import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputnumber.InputNumber;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.selectbooleancheckbox.SelectBooleanCheckbox;
import org.primefaces.component.selectonemenu.SelectOneMenu;

public class UtilsPrimefaces {

    /**
     * Disable all the children components
     *
     * @param uiComponentName
     */
    public static void disableUIComponent(String uiComponentName) {
        UIComponent component = FacesContext.getCurrentInstance()
                .getViewRoot().findComponent(uiComponentName);
        if (component != null) {
            disableAll(component.getChildren());
        }
    }

    private static void disableAll(List<UIComponent> components) {

        for (UIComponent component : components) {
           // logger.info(component.getClass().getTypeName());

            if (component instanceof InputText) {
                ((InputText) component).setDisabled(true);

            } else if (component instanceof InputNumber) {
                ((InputNumber) component).setDisabled(true);

            } else if (component instanceof InputTextarea) {
                ((InputTextarea) component).setDisabled(true);

            } else if (component instanceof HtmlInputText) {
                ((HtmlInputText) component).setDisabled(true);

            } else if (component instanceof SelectOneMenu) {
                ((SelectOneMenu) component).setDisabled(true);

            } else if (component instanceof SelectBooleanCheckbox) {
                ((SelectBooleanCheckbox) component).setDisabled(true);

            } else if (component instanceof CommandButton) {
                ((CommandButton) component).setDisabled(true);
            }
            disableAll(component.getChildren());
        }
    }

}
