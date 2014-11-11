/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import conexao.Hibernate4Util;
import java.util.List;
import model.Ranking;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Drazzull
 */
public class RankingDao
{
    
    public void salvar(Ranking ranking)
    {
        CrudGenerico.salvar(ranking);
    }

    public void atualizar(Ranking ranking)
    {
        CrudGenerico.atualizar(ranking);
    }

    public void excluir(Ranking ranking)
    {
        CrudGenerico.excluir(ranking);
    }

    public List<Ranking> listar()
    {
        try
        {
            Session sessao = Hibernate4Util.getSessionFactory();
            Transaction transacao = sessao.beginTransaction();
            Criteria cr = sessao.createCriteria(Ranking.class);
            List<Ranking> resultado = cr.list();
            transacao.commit();
            return resultado;
        }
        catch (HibernateException e)
        {
            System.out.println("Não foi possível selecionar os rankings. Erro: " + e.getMessage());
            throw new HibernateException(e);
        }
    }

    public Ranking buscar(int valor)
    {
        try
        {
            Session sessao = Hibernate4Util.getSessionFactory();
            Transaction transacao = sessao.beginTransaction();
            Criteria cr = sessao.createCriteria(Ranking.class);
            cr.add(Restrictions.eq("codigo", valor));
            Ranking ranking = (Ranking) cr.uniqueResult();
            transacao.commit();
            return ranking;
        }
        catch (HibernateException e)
        {
            System.out.println("Não foi possível buscar o apostador. Erro: " + e.getMessage());
        }

        return null;
    }
    
}
