/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Hibernate4Util;
import java.util.ArrayList;
import java.util.List;
import model.Competicao;
import model.Ranking;
import model.RankingObj;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
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

    public List<RankingObj> listarPorApostador(Competicao competicao)
    {
        try
        {
            Session sessao = Hibernate4Util.getSessionFactory();
            Transaction transacao = sessao.beginTransaction();
            Query consulta = sessao.createQuery("select new model.RankingObj(a.nome,a.apelido,sum(ra.pontuacao)) from Ranking as ra "
                    + " join ra.apostador a"
                    + " join ra.rodada ro"
                    + " join ro.competicao c"
                    + " where c = :comp"
                    + " group by a.nome"
                    + " order by sum(ra.pontuacao) desc").setParameter("comp", competicao);
            List<RankingObj> listaIncompleta = consulta.list();
            transacao.commit();
            List<RankingObj> resultado = new ArrayList<>();
            int i = 1;
            for (RankingObj r : listaIncompleta)
            {
                r.setPosicao(i);
                resultado.add(r);
                i++;
            }
            return resultado;

        }
        catch (HibernateException e)
        {
            System.out.println("Não foi possível selecionar os rankings. Erro: " + e.getMessage());
            throw new HibernateException(e);
        }
    }

    public List<RankingObj> listarPorGrupo(Competicao competicao)
    {
        try
        {
            Session sessao = Hibernate4Util.getSessionFactory();
            Transaction transacao = sessao.beginTransaction();
            Query consulta = sessao.createQuery("select new model.RankingObj(g.nome,sum(ra.pontuacao)) from Ranking ra "
                    + " join ra.apostador as a"
                    + " join a.grupo as g"
                    + " join ra.rodada as ro"
                    + " join ro.competicao as c"
                    + " where c = :comp"
                    + " group by g.nome"
                    + " order by sum(ra.pontuacao) desc").setParameter("comp", competicao);
            List<RankingObj> listaIncompleta = consulta.list();
            transacao.commit();
            List<RankingObj> resultado = new ArrayList<>();
            int i = 1;
            for (RankingObj r : listaIncompleta)
            {
                r.setPosicao(i);
                resultado.add(r);
                i++;
            }
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
