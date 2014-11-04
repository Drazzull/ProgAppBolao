/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Hibernate4Util;
import java.util.List;
import model.Jogo;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

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
            Criteria cr = sessao.createCriteria(Jogo.class);
            List<Jogo> resultado = cr.list();
            return resultado;
        }
        catch (HibernateException e)
        {
            System.out.println("Não foi possível selecionar apostadores. Erro: " + e.getMessage());
            throw new HibernateException(e);
        }
    }
    
    public Jogo buscar(int valor)
    {
        try
        {
            Session sessao = Hibernate4Util.getSessionFactory();
            Criteria cr = sessao.createCriteria(Jogo.class);
            cr.add(Restrictions.eq("codigo", valor));
            Jogo jogo = (Jogo) cr.uniqueResult();
            return jogo;
        }
        catch (HibernateException e)
        {
            System.out.println("Não foi possível buscar o apostador. Erro: " + e.getMessage());
        }

        return null;
    }    
}
