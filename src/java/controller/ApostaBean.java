/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import dao.ApostaDao;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Aposta;

/**
 *
 * @author Drazzull
 */
@ManagedBean
@SessionScoped
public class ApostaBean
{
    
    ApostaDao apostaDao = new ApostaDao();
    private Aposta aposta = new Aposta();

    public Aposta getAposta()
    {
        return this.aposta;
    }

    public List<Aposta> getApostas()
    {
        return this.apostaDao.listar();
    }
    
    /*public List<Aposta> getAuditoria() throws Exception
    {
        return this.apostaDao.listarAuditoria();
    }*/

    public String salvar()
    {
        if (this.aposta.isEditando())
        {
            this.apostaDao.atualizar(this.aposta);
            this.aposta = new Aposta();
            return "listarApostas";
        }

        this.apostaDao.salvar(this.aposta);
        this.aposta = new Aposta();
        return "listarApostas";
    }

    public String editarItem(Aposta aposta)
    {
        this.aposta = aposta;
        this.aposta.setEditando(true);
        return "lancamentoAposta";
    }

    public String excluirItem(Aposta aposta)
    {
        this.apostaDao.excluir(aposta);
        this.aposta = new Aposta();
        return "listarApostas";
    }
}
