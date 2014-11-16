/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Hibernate4Util;
import java.util.ArrayList;
import java.util.List;
import model.Jogo;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;

/**
 *
 * @author José Luiz
 */
public class JogoDao {

    public void salvar(Jogo jogo)
    {
        CrudGenerico.salvar(jogo);
    }

    public void atualizar(Jogo jogo)
    {
        CrudGenerico.atualizar(jogo);
    }

    public void excluir(Jogo jogo)
    {
        CrudGenerico.excluir(jogo);
    }

    public List<Jogo> listar()
    {
        try
        {
            Session sessao = Hibernate4Util.getSessionFactory();
            Transaction transacao = sessao.beginTransaction();
            Criteria cr = sessao.createCriteria(Jogo.class);
            List<Jogo> resultado = cr.list();
            transacao.commit();
            return resultado;
        }
        catch (HibernateException e)
        {
            System.out.println("Não foi possível selecionar apostadores. Erro: " + e.getMessage());
            throw new HibernateException(e);
        }
    }

    public List<Jogo> listarAuditoria() throws Exception
    {
        try
        {
            Session sessao = Hibernate4Util.getSessionFactory();
            Transaction transacao = sessao.beginTransaction();
            AuditReader reader = AuditReaderFactory.get(sessao);
            List<Object[]> resultList = reader.createQuery().forRevisionsOfEntity(Jogo.class, false, true).getResultList();
            List<Jogo> listaAuditada = new ArrayList<>();
            int contador = 0;
            for (Object[] objTmp : resultList)
            {
                Jogo jogoTmp = (Jogo) objTmp[0];
                jogoTmp.setRevType(objTmp[2].toString());
                if (jogoTmp.getTime1() != null)
                {
                    jogoTmp.getTime1().getCodigo();
                }
                if (jogoTmp.getTime2() != null)   
                {
                    jogoTmp.getTime2().getCodigo();
                }
                if (jogoTmp.getVencedor() != null)   
                {
                    jogoTmp.getVencedor().getCodigo();
                }
                listaAuditada.add(contador, jogoTmp);
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
    
    public Jogo buscar(int valor)
    {
        try
        {
            Session sessao = Hibernate4Util.getSessionFactory();
            Transaction transacao = sessao.beginTransaction();
            Criteria cr = sessao.createCriteria(Jogo.class);
            cr.add(Restrictions.eq("codigo", valor));
            Jogo jogo = (Jogo) cr.uniqueResult();
            transacao.commit();
            return jogo;
        }
        catch (HibernateException e)
        {
            System.out.println("Não foi possível buscar o apostador. Erro: " + e.getMessage());
        }

        return null;
    }    
}
