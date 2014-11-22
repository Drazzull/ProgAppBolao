/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.TimeCompeticaoDao;
import dao.TimeDao;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Competicao;
import model.Time;
import model.TimeCompeticao;

/**
 *
 * @author Drazzull
 */
@ManagedBean
@SessionScoped
public class TimeCompeticaoBean
{

    TimeCompeticaoDao timeCompeticaoDao = new TimeCompeticaoDao();
    private final TimeDao timeDao = new TimeDao();
    Competicao competicao;

    public Competicao getCompeticao()
    {
        return competicao;
    }

    public void setCompeticao(Competicao competicao)
    {
        this.competicao = competicao;
    }

    public List<Time> getTimes()
    {
        if (this.competicao == null)
        {
            return null;
        }

        // Obtém a lista de cadastros de times
        List<Time> listaTimes = this.timeDao.listar();
        for (Time time : listaTimes)
        {
            // Busca a combinação de time/competição, se encontrou é porque o time está vinculado
            TimeCompeticao timeCompeticaoTemp = this.timeCompeticaoDao.buscarRegistro(time, this.competicao);
            if (timeCompeticaoTemp != null)
            {
                time.setVinculado(true);
            }
        }
        
        // retorna a lista atualizada
        return listaTimes;
    }

    public void vincularTime(Time time)
    {
        // Obtém o objeto que corresponde ao vinculo atual
        TimeCompeticao timeCompeticaoTemp = this.timeCompeticaoDao.buscarRegistro(time, this.competicao);

        // Se for para desvincular, deleta o registro da tabela
        if (time.isVinculado())
        {
            this.timeCompeticaoDao.excluir(timeCompeticaoTemp);
            return;
        }

        // Se foi encontrado o registro, não faz nada
        if (timeCompeticaoTemp != null)
        {
            return;
        }

        // Cria um novo registro para essa competição/time
        timeCompeticaoTemp = new TimeCompeticao();
        timeCompeticaoTemp.setCompeticao(this.competicao);
        timeCompeticaoTemp.setTime(time);
        this.timeCompeticaoDao.salvar(timeCompeticaoTemp);
    }
}
