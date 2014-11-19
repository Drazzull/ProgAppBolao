/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.RodadaDao;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Rodada;

/**
 *
 * @author Drazzull
 */
@ManagedBean
@SessionScoped
public class RodadaBean
{

    RodadaDao rodadaDao = new RodadaDao();
    private Rodada rodada = new Rodada();

    public Rodada getRodada()
    {
        return this.rodada;
    }

    public List<Rodada> getRodadas()
    {
        return this.rodadaDao.listar();
    }

    /*public List<Aposta> getAuditoria() throws Exception
     {
     return this.apostaDao.listarAuditoria();
     }*/
    public String salvar()
    {
        if (this.rodada.isEditando())
        {
            this.rodadaDao.atualizar(this.rodada);
            this.rodada = new Rodada();
            return "listarRodadas";
        }

        this.rodadaDao.salvar(this.rodada);
        this.rodada = new Rodada();
        return "listarRodadas";
    }

    public String editarItem(Rodada rodada)
    {
        this.rodada = rodada;
        this.rodada.setEditando(true);
        return "cadastroRodada";
    }

    public String excluirItem(Rodada rodada)
    {
        this.rodadaDao.excluir(rodada);
        this.rodada = new Rodada();
        return "listarRodadas";
    }
}
