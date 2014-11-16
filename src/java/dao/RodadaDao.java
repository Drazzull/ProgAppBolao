/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Hibernate4Util;
import java.util.ArrayList;
import java.util.List;
import model.Rodada;
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
            Transaction transacao = sessao.beginTransaction();
            Criteria cr = sessao.createCriteria(Rodada.class);
            List<Rodada> resultado = cr.list();
            transacao.commit();
            return resultado;
        }
        catch (HibernateException e)
        {
            System.out.println("Não foi possível selecionar apostadores. Erro: " + e.getMessage());
            throw new HibernateException(e);
        }
    }

    public List<Rodada> listarAuditoria() throws Exception
    {
        try
        {
            Session sessao = Hibernate4Util.getSessionFactory();
            Transaction transacao = sessao.beginTransaction();
            AuditReader reader = AuditReaderFactory.get(sessao);
            List<Object[]> resultList = reader.createQuery().forRevisionsOfEntity(Rodada.class, false, true).getResultList();
            List<Rodada> listaAuditada = new ArrayList<>();
            int contador = 0;
            for (Object[] objTmp : resultList)
            {
                Rodada rodadaTmp = (Rodada) objTmp[0];
                rodadaTmp.setRevType(objTmp[2].toString());
                if (rodadaTmp.getCompeticao()!= null)
                {
                    rodadaTmp.getCompeticao().getCodigo();
                }
                listaAuditada.add(contador, rodadaTmp);
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
    
    public Rodada buscar(int valor)
    {
        try
        {
            Session sessao = Hibernate4Util.getSessionFactory();
            Transaction transacao = sessao.beginTransaction();
            Criteria cr = sessao.createCriteria(Rodada.class);
            cr.add(Restrictions.eq("codigo", valor));
            Rodada rodada = (Rodada) cr.uniqueResult();
            transacao.commit();
            return rodada;
        }
        catch (HibernateException e)
        {
            System.out.println("Não foi possível buscar o apostador. Erro: " + e.getMessage());
        }

        return null;
    }    
}
