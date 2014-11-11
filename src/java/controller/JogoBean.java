/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.JogoDao;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Jogo;

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
}
