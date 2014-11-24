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
public class CompeticaoDao
{

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
            Transaction transacao = sessao.beginTransaction();
            Criteria cr = sessao.createCriteria(Competicao.class);
            List<Competicao> resultado = cr.list();
            transacao.commit();
            return resultado;
        }
        catch (HibernateException e)
        {
            System.out.println("Não foi possível selecionar apostadores. Erro: " + e.getMessage());
            throw new HibernateException(e);
        }
    }

    public List<Competicao> listarAuditoria() throws Exception
    {
        try
        {
            Session sessao = Hibernate4Util.getSessionFactory();
            Transaction transacao = sessao.beginTransaction();
            AuditReader reader = AuditReaderFactory.get(sessao);
            List<Object[]> resultList = reader.createQuery().forRevisionsOfEntity(Competicao.class, false, true).getResultList();
            List<Competicao> listaAuditada = new ArrayList<>();
            int contador = 0;
            for (Object[] objTmp : resultList)
            {
                Competicao competicaoTmp = (Competicao) objTmp[0];
                competicaoTmp.setRevType(objTmp[2].toString());
                listaAuditada.add(contador, competicaoTmp);
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

    public String buscaNomeCompeticao(Competicao competicao)
    {
        try
        {
            Session sessao = Hibernate4Util.getSessionFactory();
            Transaction transacao = sessao.beginTransaction();
            Query consulta4 = sessao.createQuery("select c.nome from Competicao c where c = :competicao").setParameter("competicao", competicao);
            String nome_competicao = (String) consulta4.uniqueResult();
            transacao.commit();
            return nome_competicao;
        }
        catch (HibernateException e)
        {
            System.out.println("Não foi possível buscar o apostador. Erro: " + e.getMessage());
        }

        return null;
    }

    public Competicao buscar(int valor)
    {
        try
        {
            Session sessao = Hibernate4Util.getSessionFactory();
            Transaction transacao = sessao.beginTransaction();
            Criteria cr = sessao.createCriteria(Competicao.class);
            cr.add(Restrictions.eq("codigo", valor));
            Competicao competicao = (Competicao) cr.uniqueResult();
            transacao.commit();
            return competicao;
        }
        catch (HibernateException e)
        {
            System.out.println("Não foi possível buscar o apostador. Erro: " + e.getMessage());
        }

        return null;
    }
}
