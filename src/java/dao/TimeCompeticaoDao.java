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
import model.Time;
import model.TimeCompeticao;
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
public class TimeCompeticaoDao
{

    public void salvar(TimeCompeticao tc)
    {
        CrudGenerico.salvar(tc);
    }

    public void atualizar(TimeCompeticao tc)
    {
        CrudGenerico.atualizar(tc);
    }

    public void excluir(TimeCompeticao tc)
    {
        CrudGenerico.excluir(tc);
    }

    public List<TimeCompeticao> listar()
    {
        try
        {
            Session sessao = Hibernate4Util.getSessionFactory();
            Transaction transacao = sessao.beginTransaction();
            Criteria cr = sessao.createCriteria(TimeCompeticao.class);
            List<TimeCompeticao> resultado = cr.list();
            transacao.commit();
            return resultado;
        }
        catch (HibernateException e)
        {
            System.out.println("Não foi possível selecionar vinculos de times por competição. Erro: " + e.getMessage());
            throw new HibernateException(e);
        }
    }

    public List<TimeCompeticao> listarAuditoria() throws Exception
    {
        try
        {
            Session sessao = Hibernate4Util.getSessionFactory();
            Transaction transacao = sessao.beginTransaction();
            AuditReader reader = AuditReaderFactory.get(sessao);
            List<Object[]> resultList = reader.createQuery().forRevisionsOfEntity(TimeCompeticao.class, false, true).getResultList();
            List<TimeCompeticao> listaAuditada = new ArrayList<>();
            int contador = 0;
            for (Object[] objTmp : resultList)
            {
                TimeCompeticao tcTmp = (TimeCompeticao) objTmp[0];
                tcTmp.setRevType(objTmp[2].toString());
                if (tcTmp.getCompeticao() != null)
                {
                    tcTmp.getCompeticao().getCodigo();
                }
                if (tcTmp.getTime() != null)
                {
                    tcTmp.getTime().getCodigo();
                }
                listaAuditada.add(contador, tcTmp);
                contador++;
            }

            transacao.commit();
            return listaAuditada;
        }
        catch (HibernateException e)
        {
            throw new Exception("Não foi possível buscar os vinculos de time por competição. Erro: " + e.getMessage());
        }
    }

    public TimeCompeticao buscar(int valor)
    {
        try
        {
            Session sessao = Hibernate4Util.getSessionFactory();
            Transaction transacao = sessao.beginTransaction();
            Criteria cr = sessao.createCriteria(TimeCompeticao.class);
            cr.add(Restrictions.eq("codigo", valor));
            TimeCompeticao tc = (TimeCompeticao) cr.uniqueResult();
            transacao.commit();
            return tc;
        }
        catch (HibernateException e)
        {
            System.out.println("Não foi possível buscar o vinculo de time por competição. Erro: " + e.getMessage());
        }

        return null;
    }

    public TimeCompeticao buscarRegistro(Time time, Competicao competicao)
    {
        try
        {
            Session sessao = Hibernate4Util.getSessionFactory();
            Transaction transacao = sessao.beginTransaction();
            Query consulta = sessao.createQuery("SELECT tc "
                    + " FROM TimeCompeticao tc"
                    + " INNER JOIN tc.time AS tm"
                    + " INNER JOIN tc.competicao AS cm"
                    + " WHERE (tm = ?) AND (cm = ?)");
            consulta.setEntity(0, time);
            consulta.setEntity(1, competicao);

            TimeCompeticao resultado = (TimeCompeticao) consulta.uniqueResult();
            transacao.commit();
            return resultado;
        }
        catch (HibernateException e)
        {
            System.out.println("Não foi possível buscar o vinculo de time por competição. Erro: " + e.getMessage());
        }

        return null;
    }

    public List<TimeCompeticao> listarPorCompeticao(Competicao competicao)
    {
        try
        {
            Session sessao = Hibernate4Util.getSessionFactory();
            Transaction transacao = sessao.beginTransaction();
            Query consulta = sessao.createQuery("SELECT tc "
                    + " FROM TimeCompeticao tc"
                    + " INNER JOIN tc.competicao AS cm"
                    + " WHERE (cm = ?)");
            consulta.setEntity(0, competicao);
            List<TimeCompeticao> resultado = consulta.list();
            transacao.commit();
            return resultado;
        }
        catch (HibernateException e)
        {
            System.out.println("Não foi possível selecionar o vinculo de time por competição. Erro: " + e.getMessage());
            throw new HibernateException(e);
        }
    }
}
