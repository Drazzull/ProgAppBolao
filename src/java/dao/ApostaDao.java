/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Hibernate4Util;
import java.util.List;
import model.Aposta;
import model.Rodada;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

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
            System.out.println("Não foi possível selecionar apostadores. Erro: " + e.getMessage());
            throw new HibernateException(e);
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
            System.out.println("Não foi possível buscar o apostador. Erro: " + e.getMessage());
        }

        return null;
    }    
}
