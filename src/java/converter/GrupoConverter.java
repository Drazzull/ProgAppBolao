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
import dao.GrupoDao;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.Grupo;

@FacesConverter(value = "grupoConverter")
public class GrupoConverter implements Converter
{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value)
    {
        if ((value == null) || value.equals(""))
        {
            return null;
        }
        
        GrupoDao dao = new GrupoDao();
        return dao.buscar(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value)
    {
        if (value instanceof Grupo)
        {
            Grupo grupo = (Grupo) value;
            return String.valueOf(grupo.getCodigo());
        }

        return "";
    }
}
