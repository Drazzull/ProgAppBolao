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
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import model.Time;

@ManagedBean
public class TimeBean
{

    TimeDao timeDao = new TimeDao();
    private Time time = new Time();
    private String nomeBusca;
    private Date dataInicialBusca;
    private Date dataFinalBusca;

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

    public String gravar() throws IOException
    {
        this.timeDao.salvar(this.getTime());
        return "listarTimes.xhtml";
    }

    public List<Time> getTimes()
    {
        return this.timeDao.listar();
    }

    public String editar(Time time)
    {
        this.time = time;
        this.time.setEditando(true);
        return "cadastroTime.xhtml";
    }

    public String editar()
    {
        this.timeDao.atualizar(this.time);
        return "listarTimes.xhtml";
    }

    public String excluir(Time time)
    {
        this.timeDao.excluir(time);
        return "listarTimes.xhtml";
    }

    public List<Time> getTimesPorNome()
    {
        return this.timeDao.listarPorNome(this.nomeBusca);
    }

    public List<Time> getTimesPorDataFundacao()
    {
        return this.timeDao.listarPorDataFundacao(this.dataInicialBusca, this.dataFinalBusca);
    }
}
