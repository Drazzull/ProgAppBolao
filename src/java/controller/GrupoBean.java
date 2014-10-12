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
import dao.GrupoDao;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Grupo;

@ManagedBean
@SessionScoped
public class GrupoBean
{

    GrupoDao grupoDao = new GrupoDao();
    private Grupo grupo = new Grupo();

    public Grupo getGrupo()
    {
        return this.grupo;
    }

    public List<Grupo> getGrupos()
    {
        return this.grupoDao.listar();
    }

    public String salvar()
    {
        if (this.grupo.isEditando())
        {
            this.grupoDao.atualizar(this.grupo);
            this.grupo = new Grupo();
            return "listarGrupos";
        }

        this.grupoDao.salvar(this.grupo);
        this.grupo = new Grupo();
        return "listarGrupos";
    }

    public String editarItem(Grupo grupo)
    {
        this.grupo = grupo;
        this.grupo.setEditando(true);
        return "cadastroGrupo";
    }

    public String excluirItem(Grupo grupo)
    {
        this.grupoDao.excluir(grupo);
        this.grupo = new Grupo();
        return "listarGrupos";
    }
}
