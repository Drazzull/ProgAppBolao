/*
 * To change this license header, choose License Headers in Project Properties. 
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author Drazzull
 */
import conexao.Hibernate4Util;
import java.util.Date;
import java.util.List;
import model.Time;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TimeDao
{

    /**
     * Salva o time no banco de dados
     *
     * @param time Objeto contendo as informações do time que serão salvas
     */
    public void salvar(Time time)
    {
        CrudGenerico.salvar(time);
    }

    /**
     * Realiza a alteração dos dados de um time já salvo anteriormente no banco
     *
     * @param time Objeto do tipo time com os dados que serão salvos
     */
    public void atualizar(Time time)
    {
        CrudGenerico.atualizar(time);
    }

    /**
     * Realiza a exclusão do time
     *
     * @param time Objeto do tipo time com o objeto que será excluído
     */
    public void excluir(Time time)
    {
        CrudGenerico.excluir(time);
    }

    /**
     * Obtém lista com todos os times do banco
     *
     * @return Lista com todos os times do banco
     */
    public List<Time> listar()
    {
        try
        {
            Session sessao = Hibernate4Util.getSessionFactory();
            Transaction transacao = sessao.beginTransaction();
            Query consulta = sessao.createQuery("from Time");
            List<Time> resultado = consulta.list();
            transacao.commit();
            return resultado;
        }
        catch (HibernateException e)
        {
            System.out.println("Não foi possível selecionar times. Erro: " + e.getMessage());
            throw new HibernateException(e);
        }
    }

    /**
     * Obtém lista com todos os times do banco
     *
     * @param nome Nome a ser filtrado
     * @return Lista com todos os times do banco
     */
    public List<Time> listarPorNome(String nome)
    {
        try
        {
            Session sessao = Hibernate4Util.getSessionFactory();
            Transaction transacao = sessao.beginTransaction();
            Query consulta = sessao.createQuery("from Time where nome like :nome order by nome, dataFundacao");
            consulta.setString("nome", "%" + nome + "%");

            List<Time> resultado = consulta.list();
            transacao.commit();
            return resultado;
        }
        catch (HibernateException e)
        {
            System.out.println("Não foi possível selecionar times. Erro: " + e.getMessage());
            throw new HibernateException(e);
        }
    }

    /**
     * Obtém lista com todos os times do banco
     *
     * @param dataIni Data inicial a ser filtrada
     * @param dataFin Data final a ser filtrada
     * @return Lista com todos os times do banco
     */
    public List<Time> listarPorDataFundacao(Date dataIni, Date dataFin)
    {
        try
        {
            Session sessao = Hibernate4Util.getSessionFactory();
            Transaction transacao = sessao.beginTransaction();
            Query consulta = sessao.createQuery("from Time where dataFundacao between :dataini AND :datafin order by dataFundacao desc, codigo");
            consulta.setDate("dataini", dataIni);
            consulta.setDate("datafin", dataFin);

            List<Time> resultado = consulta.list();
            transacao.commit();
            return resultado;
        }
        catch (HibernateException e)
        {
            System.out.println("Não foi possível selecionar times. Erro: " + e.getMessage());
            throw new HibernateException(e);
        }
    }

    /**
     * Busca o código de um time
     *
     * @param valor Código a ser pesquisado
     * @return Time referente ao código
     */
    public Time buscar(int valor)
    {
        try
        {
            Session sessao = Hibernate4Util.getSessionFactory();
            Transaction transacao = sessao.beginTransaction();
            Query consulta = sessao.createQuery("from Time where codigo = :parametro");
            consulta.setInteger("parametro", valor);
            Time time = (Time) consulta.uniqueResult();
            transacao.commit();
            return time;
        }
        catch (HibernateException e)
        {
            System.out.println("Não foi possível buscar o time. Erro: " + e.getMessage());
        }

        return null;
    }
}
