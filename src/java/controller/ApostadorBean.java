/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ApostadorDao;
import dao.GrupoDao;
import dao.TimeDao;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Apostador;
import model.Grupo;
import model.Time;

/**
 *
 * @author Drazzull
 */
@ManagedBean
@SessionScoped
public class ApostadorBean
{

    ApostadorDao apostadorDao = new ApostadorDao();
    private Apostador apostador = new Apostador();
    private final GrupoDao grupoDao = new GrupoDao();
    private final TimeDao timeDao = new TimeDao();

    public Apostador getApostador()
    {
        return this.apostador;
    }

    public List<Apostador> getApostadores()
    {
        return this.apostadorDao.listar();
    }
    
    public List<Apostador> getAuditoria() throws Exception
    {
        return this.apostadorDao.listarAuditoria();
    }

    public String salvar()
    {
        if (this.apostador.isEditando())
        {
            this.apostadorDao.atualizar(this.apostador);
            this.apostador = new Apostador();
            return "listarApostadores";
        }

        this.apostadorDao.salvar(this.apostador);
        this.apostador = new Apostador();
        return "listarApostadores";
    }

    public String editarItem(Apostador apostador)
    {
        this.apostador = apostador;
        this.apostador.setEditando(true);
        return "cadastroApostador";
    }

    public String excluirItem(Apostador apostador)
    {
        this.apostadorDao.excluir(apostador);
        this.apostador = new Apostador();
        return "listarApostadores";
    }

    public List<Grupo> getGrupos()
    {
        return this.grupoDao.listar();
    }

    public List<Time> getTimes()
    {
        return this.timeDao.listar();
    }
}
