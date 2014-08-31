/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Hibernate4Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Drazzull
 */
public class CrudGenerico
{

    /**
     * Salva o time no banco de dados
     *
     * @param object Objeto contendo as informações que serão salvas
     */
    public static void salvar(Object object)
    {
        try
        {
            Session sessao = Hibernate4Util.getSessionFactory();
            Transaction transacao = sessao.beginTransaction();
            sessao.save(object);
            transacao.commit();
        }
        catch (HibernateException e)
        {
            System.out.println("Não foi possível inserir o contato. Erro: " + e.getMessage());
        }
    }

    /**
     * Realiza a alteração dos dados de um time já salvo anteriormente no banco
     *
     * @param object Objeto com os dados que serão atualizados
     */
    public static void atualizar(Object object)
    {
        try
        {
            Session sessao = Hibernate4Util.getSessionFactory();
            Transaction transacao = sessao.beginTransaction();
            sessao.update(object);
            transacao.commit();
        }
        catch (HibernateException e)
        {
            System.out.println("Não foi possível alterar o contato. Erro: " + e.getMessage());
        }
    }

    /**
     * Realiza a exclusão do time
     *
     * @param object Objeto que será excluído
     */
    public static void excluir(Object object)
    {
        try
        {
            Session sessao = Hibernate4Util.getSessionFactory();
            Transaction transacao = sessao.beginTransaction();
            sessao.delete(object);
            transacao.commit();
        }
        catch (HibernateException e)
        {
            System.out.println("Não foi possível excluir o contato. Erro: " + e.getMessage());
        }
    }
}
