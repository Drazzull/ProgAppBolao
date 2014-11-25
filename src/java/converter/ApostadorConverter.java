/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

/**
 *
 * @author Drazzull
 */
import dao.ApostadorDao;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.Apostador;

@FacesConverter(value = "apostadorConverter")
public class ApostadorConverter implements Converter
{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value)
    {
        if ((value == null) || value.equals(""))
        {
            return null;
        }

        ApostadorDao dao = new ApostadorDao();
        return dao.buscar(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value)
    {
        if (value instanceof Apostador)
        {
            Apostador apostador = (Apostador) value;
            return String.valueOf(apostador.getCodigo());
        }

        return "";
    }
}
