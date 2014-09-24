/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Drazzull
 */
@ManagedBean
@ApplicationScoped
public class Imagens
{
/*
    @EJB
    private ImageService service;

    public StreamedContent getImage() throws IOException
    {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE)
        {
            // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }
        else
        {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            String id = context.getExternalContext().getRequestParameterMap().get("id");
            Image image = service.find(Long.valueOf(id));
            return new DefaultStreamedContent(new ByteArrayInputStream(image.getBytes()));
        }
    }
*/
}
