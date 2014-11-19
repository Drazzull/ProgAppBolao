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
import dao.CompeticaoDao;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.Competicao;

@FacesConverter(value = "competicaoConverter")
public class CompeticaoConverter implements Converter
{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value)
    {
        if ((value == null) || value.equals(""))
        {
            return null;
        }

        CompeticaoDao dao = new CompeticaoDao();
        return dao.buscar(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value)
    {
        if (value instanceof Competicao)
        {
            Competicao competicao = (Competicao) value;
            return String.valueOf(competicao.getCodigo());
        }

        return "";
    }
}
