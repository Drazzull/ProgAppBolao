/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author Drazzull
 */
import dao.TimeDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;
import model.Time;

@ManagedBean
@SessionScoped
public class TimeBean
{

    TimeDao timeDao = new TimeDao();
    private Time time = new Time();
    private String nomeBusca;
    private Date dataInicialBusca;
    private Date dataFinalBusca;
    private Part logoUpload;

    public Time getTime()
    {
        return this.time;
    }

    public String getNomeBusca()
    {
        return nomeBusca;
    }

    public void setNomeBusca(String nomeBusca)
    {
        this.nomeBusca = nomeBusca;
    }

    public Date getDataInicialBusca()
    {
        return dataInicialBusca;
    }

    public void setDataInicialBusca(Date dataInicialBusca)
    {
        this.dataInicialBusca = dataInicialBusca;
    }

    public Date getDataFinalBusca()
    {
        return dataFinalBusca;
    }

    public void setDataFinalBusca(Date dataFinalBusca)
    {
        this.dataFinalBusca = dataFinalBusca;
    }

    public Part getLogoUpload()
    {
        return logoUpload;
    }

    public void setLogoUpload(Part logoUpload)
    {
        this.logoUpload = logoUpload;
    }

    public List<Time> getTimes()
    {
        if (this.nomeBusca != null)
        {
            return this.timeDao.listarPorNome(this.nomeBusca);
        }

        if ((this.dataInicialBusca != null) && (this.dataFinalBusca != null))
        {
            return this.timeDao.listarPorDataFundacao(this.dataInicialBusca, this.dataFinalBusca);
        }

        return this.timeDao.listar();
    }

    public String salvar() throws IOException, SQLException
    {
        /*try
        {
            String arquivo = new Scanner(this.logoUpload.getInputStream()).useDelimiter("\\A").next();
            this.time.setLogoUpload(new SerialBlob(arquivo.getBytes()));
        }
        catch (IOException e)
        {
        }*/

        if (this.time.isEditando())
        {
            this.timeDao.atualizar(this.time);
            this.time = new Time();
            return "listarTimes";
        }

        this.timeDao.salvar(this.time);
        this.time = new Time();
        return "listarTimes";
    }

    public String editarItem(Time time)
    {
        this.time = time;
        this.time.setEditando(true);
        return "cadastroTime";
    }

    public String excluirItem(Time time) throws IOException
    {
        this.timeDao.excluir(time);
        this.time = new Time();
        return "listarTimes";
    }
}
