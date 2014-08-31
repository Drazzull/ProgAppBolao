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
import java.util.List;
import javax.faces.bean.ManagedBean;
import model.Time;

@ManagedBean
public class TimeBean
{
    
    TimeDao timeDao = new TimeDao();
    
    private Time time = new Time();
    
    public Time getTime()
    {
        return this.time;
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
}
