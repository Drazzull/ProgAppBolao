/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Hibernate4Util;
import java.util.ArrayList;
import java.util.List;
import model.Aposta;
import model.Apostador;
import model.Competicao;
import model.Jogo;
import model.Ranking;
import model.RankingObj;
import model.Rodada;
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

    public void inserirRankingRodada(Rodada rodada){
        try
        {
            Session sessao = Hibernate4Util.getSessionFactory();
            Transaction transacao = sessao.beginTransaction();
            ApostaDao apostaDAO = new ApostaDao();
            ApostadorDao apostadorDAO = new ApostadorDao();
            JogoDao jogoDAO = new JogoDao();
            RankingDao rankingDAO = new RankingDao();
            
            Ranking[] rankings = new Ranking[apostadorDAO.listar().size()];
            int i = 0;
            for(Apostador a : apostadorDAO.listar()){
                rankings[i] = new Ranking(rodada, a, null);
                i++;
            }
            
            List<Jogo> jogosDaRodada = jogoDAO.listarJogoPorRodada(rodada);
            for(Jogo j : jogosDaRodada){
                List<Apostador> apostadoresDeUmJogo = apostaDAO.listarApostadoresDeUmJogo(j);
                for(Apostador a : apostadoresDeUmJogo){
                    List<Aposta> apostasDeUmApostadorEmUmJogo = apostaDAO.listarApostasApostadorPorJogo(j, a);
                    for(Aposta apos : apostasDeUmApostadorEmUmJogo){
                        for (Ranking ranking : rankings) {
                            if(ranking.getApostador() == apos.getApostador()){
                                if(apos.getPlacarTime1() == j.getPlacarTime1() && apos.getPlacarTime2() == j.getPlacarTime2() && apos.getVencedor() == j.getVencedor().getNome()){
                                    ranking.setPontuacao(ranking.getPontuacao()+40);
                                } else if(apos.getVencedor() == j.getVencedor().getNome()){
                                    ranking.setPontuacao(ranking.getPontuacao()+10);
                                } else{
                                    ranking.setPontuacao(ranking.getPontuacao()+0);
                                }
                            }
                        }
                    }
                }
            }
            for(Ranking ranking : rankings){
                if(ranking.getPontuacao() != null)
                    rankingDAO.salvar(ranking);
                
                transacao.commit();
            }
            }
        catch (HibernateException e)
        {
            System.out.println("Não foi possível selecionar os rankings. Erro: " + e.getMessage());
            throw new HibernateException(e);
        }
    }
}
