/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Hibernate4Util;
import java.util.List;
import model.TimeCompeticao;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author José Luiz
 */
public class TimeCompeticaoDao {

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
            System.out.println("Não foi possível selecionar apostadores. Erro: " + e.getMessage());
            throw new HibernateException(e);
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
            System.out.println("Não foi possível buscar o apostador. Erro: " + e.getMessage());
        }

        return null;
    }    
}
