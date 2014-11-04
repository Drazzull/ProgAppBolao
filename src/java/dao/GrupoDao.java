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
import java.util.List;
import model.Grupo;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class GrupoDao
{

    /**
     * Salva o grupo no banco de dados
     *
     * @param grupo Objeto contendo as informações do grupo que serão salvas
     */
    public void salvar(Grupo grupo)
    {
        CrudGenerico.salvar(grupo);
    }

    /**
     * Realiza a alteração dos dados de um grupo já salvo anteriormente no banco
     *
     * @param grupo Objeto do tipo grupo com os dados que serão salvos
     */
    public void atualizar(Grupo grupo)
    {
        CrudGenerico.atualizar(grupo);
    }

    /**
     * Realiza a exclusão do grupo
     *
     * @param grupo Objeto do tipo grupo que será excluído
     */
    public void excluir(Grupo grupo)
    {
        CrudGenerico.excluir(grupo);
    }

    /**
     * Obtém lista com todos os grupos do banco
     *
     * @return Lista com todos os grupos do banco
     */
    public List<Grupo> listar()
    {
        try
        {
            Session sessao = Hibernate4Util.getSessionFactory();
            Criteria cr = sessao.createCriteria(Grupo.class);
            List<Grupo> resultado = cr.list();
            return resultado;
        }
        catch (HibernateException e)
        {
            System.out.println("Não foi possível selecionar grupos. Erro: " + e.getMessage());
            throw new HibernateException(e);
        }
    }

    /**
     * Busca o código de um grupo
     *
     * @param valor Código a ser pesquisado
     * @return Grupo referente ao código
     */
    public Grupo buscar(int valor)
    {
        try
        {
            Session sessao = Hibernate4Util.getSessionFactory();
            Criteria cr = sessao.createCriteria(Grupo.class);
            cr.add(Restrictions.eq("codigo", valor));
            Grupo grupo = (Grupo) cr.uniqueResult();
            return grupo;
        }
        catch (HibernateException e)
        {
            System.out.println("Não foi possível buscar o grupo. Erro: " + e.getMessage());
        }

        return null;
    }
}
