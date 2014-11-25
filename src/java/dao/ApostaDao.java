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
import model.Jogo;
import model.Rodada;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;

/**
 *
 * @author José Luiz
 */
public class ApostaDao {

    public void salvar(Aposta aposta)
    {
        CrudGenerico.salvar(aposta);
    }

    public void atualizar(Aposta aposta)
    {
        CrudGenerico.atualizar(aposta);
    }

    public void excluir(Aposta aposta)
    {
        CrudGenerico.excluir(aposta);
    }

    public List<Aposta> listar()
    {
        try
        {
            Session sessao = Hibernate4Util.getSessionFactory();
            Transaction transacao = sessao.beginTransaction();
            Criteria cr = sessao.createCriteria(Aposta.class);
            List<Aposta> resultado = cr.list();
            transacao.commit();
            return resultado;
        }
        catch (HibernateException e)
        {
            System.out.println("Não foi possível selecionar as apostas. Erro: " + e.getMessage());
            throw new HibernateException(e);
        }
    }
    
        public List<Aposta> listarAuditoria() throws Exception
    {
        try
        {
            Session sessao = Hibernate4Util.getSessionFactory();
            Transaction transacao = sessao.beginTransaction();
            AuditReader reader = AuditReaderFactory.get(sessao);
            List<Object[]> resultList = reader.createQuery().forRevisionsOfEntity(Aposta.class, false, true).getResultList();
            List<Aposta> listaAuditada = new ArrayList<>();
            int contador = 0;
            for (Object[] objTmp : resultList)
            {
                Aposta apostaTmp = (Aposta) objTmp[0];
                apostaTmp.setRevType(objTmp[2].toString());
                if (apostaTmp.getApostador()!= null)
                {
                    apostaTmp.getApostador().getCodigo();
                }
                if (apostaTmp.getJogo() != null)   
                {
                    apostaTmp.getJogo().getCodigo();
                }
                listaAuditada.add(contador, apostaTmp);
                contador++;
            }

            transacao.commit();
            return listaAuditada;
        }
        catch (HibernateException e)
        {
            throw new Exception("Não foi possível buscar a auditoria. Erro: " + e.getMessage());
        }
    }
    
    public Aposta buscar(int valor)
    {
        try
        {
            Session sessao = Hibernate4Util.getSessionFactory();
            Transaction transacao = sessao.beginTransaction();
            Criteria cr = sessao.createCriteria(Rodada.class);
            cr.add(Restrictions.eq("codigo", valor));
            Aposta aposta = (Aposta) cr.uniqueResult();
            transacao.commit();
            return aposta;
        }
        catch (HibernateException e)
        {
            System.out.println("Não foi possível buscar a Aposta. Erro: " + e.getMessage());
        }

        return null;
    }
    
    public List<Aposta> listarApostasPorRodada(Rodada rodada){
      try
        {
            Session sessao = Hibernate4Util.getSessionFactory();
            Transaction transacao = sessao.beginTransaction();
            Query consulta = sessao.createQuery("from Aposta a "
                    + " join a.jogo as j"
                    + " join j.rodada as r"
                    + " join ra.rodada as r"
                    + " where r = :rod").setParameter("rod", rodada);
            List<Aposta> resultado = consulta.list();
            transacao.commit();
            
            return resultado;

        }
        catch (HibernateException e)
        {
            System.out.println("Não foi possível selecionar as apostas. Erro: " + e.getMessage());
            throw new HibernateException(e);
        }
}
    
    public List<Aposta> listarApostasPorJogo(Jogo jogo){
        try
        {
            Session sessao = Hibernate4Util.getSessionFactory();
            Transaction transacao = sessao.beginTransaction();
            Query consulta = sessao.createQuery("from Aposta a "
                    + " join a.jogo as j"
                    + " where j = :jog").setParameter("jog", jogo);
            List<Aposta> resultado = consulta.list();
            transacao.commit();
            
            return resultado;

        }
        catch (HibernateException e)
        {
            System.out.println("Não foi possível selecionar as apostas. Erro: " + e.getMessage());
            throw new HibernateException(e);
        }
    }
    
    public List<Aposta> listarApostasApostadorPorJogo(Jogo jogo, Apostador apostador){
        try
        {
            Session sessao = Hibernate4Util.getSessionFactory();
            Transaction transacao = sessao.beginTransaction();
            Query consulta = sessao.createQuery("from Aposta a "
                    + " join a.jogo as j"
                    + " join a.apostador ap"
                    + " where j = :jog"
                    + " and ap = :apos").setParameter("jog", jogo).setParameter("apos", apostador);
            List<Aposta> resultado = consulta.list();
            transacao.commit();
            
            return resultado;

        }
        catch (HibernateException e)
        {
            System.out.println("Não foi possível selecionar as apostas. Erro: " + e.getMessage());
            throw new HibernateException(e);
        }
    }
    
    public Apostador buscarApostadorDeUmaAposta(Aposta aposta){
        try
        {
            Session sessao = Hibernate4Util.getSessionFactory();
            Transaction transacao = sessao.beginTransaction();
            Query consulta = sessao.createQuery("select a.apostador from Aposta a "
                    + " where a = :apos").setParameter("apos", aposta);
            Apostador resultado = (Apostador) consulta.uniqueResult();
            transacao.commit();
            return resultado;

        }
        catch (HibernateException e)
        {
            System.out.println("Não foi possível selecionar a Aposta. Erro: " + e.getMessage());
            throw new HibernateException(e);
        }
    }
    
    public Jogo buscarJogoDeUmaAposta(Aposta aposta){
        try
        {
            Session sessao = Hibernate4Util.getSessionFactory();
            Transaction transacao = sessao.beginTransaction();
            Query consulta = sessao.createQuery("select a.jogo from Aposta a "
                    + " where a = :apos").setParameter("apos", aposta);
            Jogo resultado = (Jogo) consulta.uniqueResult();
            transacao.commit();
            return resultado;

        }
        catch (HibernateException e)
        {
            System.out.println("Não foi possível selecionar a Aposta. Erro: " + e.getMessage());
            throw new HibernateException(e);
        }
    }
    
    public List<Apostador> listarApostadoresDeUmJogo(Jogo jogo){
        try
        {
            Session sessao = Hibernate4Util.getSessionFactory();
            Transaction transacao = sessao.beginTransaction();
            Query consulta = sessao.createQuery("select a.apostador from Aposta a "
                    + " join a.jogo as j"
                    + " where j = :jog").setParameter("jog", jogo);
            List<Apostador> resultado = consulta.list();
            transacao.commit();
            
            return resultado;

        }
        catch (HibernateException e)
        {
            System.out.println("Não foi possível selecionar as apostas. Erro: " + e.getMessage());
            throw new HibernateException(e);
        }
    }
}
