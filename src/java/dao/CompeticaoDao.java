/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Hibernate4Util;
import java.util.List;
import model.Competicao;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author José Luiz
 */
public class CompeticaoDao {

    public void salvar(Competicao competicao)
    {
        CrudGenerico.salvar(competicao);
    }

    public void atualizar(Competicao competicao)
    {
        CrudGenerico.atualizar(competicao);
    }

    public void excluir(Competicao competicao)
    {
        CrudGenerico.excluir(competicao);
    }

    public List<Competicao> listar()
    {
        try
        {
            Session sessao = Hibernate4Util.getSessionFactory();
            Criteria cr = sessao.createCriteria(Competicao.class);
            List<Competicao> resultado = cr.list();
            return resultado;
        }
        catch (HibernateException e)
        {
            System.out.println("Não foi possível selecionar apostadores. Erro: " + e.getMessage());
            throw new HibernateException(e);
        }
    }
    
    public Competicao buscar(int valor)
    {
        try
        {
            Session sessao = Hibernate4Util.getSessionFactory();
            Criteria cr = sessao.createCriteria(Competicao.class);
            cr.add(Restrictions.eq("codigo", valor));
            Competicao competicao = (Competicao) cr.uniqueResult();
            return competicao;
        }
        catch (HibernateException e)
        {
            System.out.println("Não foi possível buscar o apostador. Erro: " + e.getMessage());
        }

        return null;
    }
}
