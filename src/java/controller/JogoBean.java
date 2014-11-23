/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.JogoDao;
import dao.TimeCompeticaoDao;
import dao.TimeDao;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Competicao;
import model.Jogo;
import model.Rodada;
import model.Time;
import model.TimeCompeticao;

/**
 *
 * @author Drazzull
 */
@ManagedBean
@SessionScoped
public class JogoBean
{

    JogoDao jogoDao = new JogoDao();
    private Jogo jogo = new Jogo();
    private final TimeDao timeDao = new TimeDao();
    private final TimeCompeticaoDao timeCompeticaoDao = new TimeCompeticaoDao();

    public Jogo getJogo()
    {
        return this.jogo;
    }

    public List<Jogo> getJogos()
    {
        return this.jogoDao.listar();
    }

    /*public List<Aposta> getAuditoria() throws Exception
     {
     return this.apostaDao.listarAuditoria();
     }*/
    public String salvar()
    {
        if (this.jogo.isEditando())
        {
            this.jogoDao.atualizar(this.jogo);
            this.jogo = new Jogo();
            return "listarJogos";
        }

        this.jogoDao.salvar(this.jogo);
        this.jogo = new Jogo();
        return "listarJogos";
    }

    public String editarItem(Jogo jogo)
    {
        this.jogo = jogo;
        this.jogo.setEditando(true);
        return "cadastroJogo";
    }

    public String excluirItem(Jogo jogo)
    {
        this.jogoDao.excluir(jogo);
        this.jogo = new Jogo();
        return "listarJogos";
    }

    public List<Time> getTimesRodada()
    {
        if (this.jogo.getRodada() == null)
        {
            return null;
        }

        Competicao competicaoTmp = this.jogo.getRodada().getCompeticao();

        // Obtém a lista de cadastros de times
        List<Time> listaTimesTmp = this.timeDao.listar();
        List<Time> listaTimes = this.timeDao.listar();
        for (Time time : listaTimes)
        {
            // Busca a combinação de time/competição, se encontrou é porque o time está vinculado
            TimeCompeticao timeCompeticaoTemp = this.timeCompeticaoDao.buscarRegistro(time, competicaoTmp);
            if (timeCompeticaoTemp == null)
            {
                listaTimesTmp.remove(time);
            }
        }

        // retorna a lista atualizada
        return listaTimesTmp;
    }
}
