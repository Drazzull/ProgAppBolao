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
import dao.RodadaDao;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.Rodada;

@FacesConverter(value = "rodadaConverter")
public class RodadaConverter implements Converter
{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value)
    {
        if ((value == null) || value.equals(""))
        {
            return null;
        }

        RodadaDao dao = new RodadaDao();
        return dao.buscar(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value)
    {
        if (value instanceof Rodada)
        {
            Rodada rodada = (Rodada) value;
            return String.valueOf(rodada.getCodigo());
        }

        return "";
    }
}
