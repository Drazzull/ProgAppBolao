/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Hibernate4Util;
import java.util.List;
import model.Rodada;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author José Luiz
 */
public class RodadaDao {

    public void salvar(Rodada rodada)
    {
        CrudGenerico.salvar(rodada);
    }

    public void atualizar(Rodada rodada)
    {
        CrudGenerico.atualizar(rodada);
    }

    public void excluir(Rodada rodada)
    {
        CrudGenerico.excluir(rodada);
    }

    public List<Rodada> listar()
    {
        try
        {
            Session sessao = Hibernate4Util.getSessionFactory();
            Criteria cr = sessao.createCriteria(Rodada.class);
            List<Rodada> resultado = cr.list();
            return resultado;
        }
        catch (HibernateException e)
        {
            System.out.println("Não foi possível selecionar apostadores. Erro: " + e.getMessage());
            throw new HibernateException(e);
        }
    }
    
    public Rodada buscar(int valor)
    {
        try
        {
            Session sessao = Hibernate4Util.getSessionFactory();
            Criteria cr = sessao.createCriteria(Rodada.class);
            cr.add(Restrictions.eq("codigo", valor));
            Rodada rodada = (Rodada) cr.uniqueResult();
            return rodada;
        }
        catch (HibernateException e)
        {
            System.out.println("Não foi possível buscar o apostador. Erro: " + e.getMessage());
        }

        return null;
    }    
}
