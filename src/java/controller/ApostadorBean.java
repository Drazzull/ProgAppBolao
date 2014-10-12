/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import dao.ApostadorDao;
import java.util.List;
import model.Apostador;

/**
 *
 * @author Drazzull
 */
public class ApostadorBean
{
   
    ApostadorDao apostadorDao = new ApostadorDao();
    private Apostador apostador = new Apostador();

    public Apostador getApostador()
    {
        return this.apostador;
    }

    public List<Apostador> getApostadores()
    {
        return this.apostadorDao.listar();
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
}
