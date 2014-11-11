/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import dao.CompeticaoDao;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Competicao;

/**
 *
 * @author Drazzull
 */
@ManagedBean
@SessionScoped
public class CompeticaoBean
{
    
    CompeticaoDao competicaoDao = new CompeticaoDao();
    private Competicao competicao = new Competicao();

    public Competicao getCompeticao()
    {
        return this.competicao;
    }

    public List<Competicao> getCompeticoes()
    {
        return this.competicaoDao.listar();
    }
    
    /*public List<Aposta> getAuditoria() throws Exception
    {
        return this.apostaDao.listarAuditoria();
    }*/

    public String salvar()
    {
        if (this.competicao.isEditando())
        {
            this.competicaoDao.atualizar(this.competicao);
            this.competicao = new Competicao();
            return "listarCompeticoes";
        }

        this.competicaoDao.salvar(this.competicao);
        this.competicao = new Competicao();
        return "listarCompeticoes";
    }

    public String editarItem(Competicao competicao)
    {
        this.competicao = competicao;
        this.competicao.setEditando(true);
        return "cadastroCompeticao";
    }

    public String excluirItem(Competicao competicao)
    {
        this.competicaoDao.excluir(competicao);
        this.competicao = new Competicao();
        return "listarCompeticoes";
    }
}
