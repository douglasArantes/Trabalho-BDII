package bdii.locadora.controller.converters;

import bdii.locadora.model.Cliente;
import bdii.locadora.model.Funcionario;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by Dougla$ on 12/11/2014.
 */
@FacesConverter("locadora.DateConverter")
public class DateConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return LocalDate.parse(value, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((LocalDate)value).toString();
    }
}
