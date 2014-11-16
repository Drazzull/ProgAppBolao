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
import java.util.ArrayList;
import java.util.List;
import model.Grupo;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;

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
            Transaction transacao = sessao.beginTransaction();
            Criteria cr = sessao.createCriteria(Grupo.class);
            List<Grupo> resultado = cr.list();
            transacao.commit();
            return resultado;
        }
        catch (HibernateException e)
        {
            System.out.println("Não foi possível selecionar grupos. Erro: " + e.getMessage());
            throw new HibernateException(e);
        }
    }
    
        public List<Grupo> listarAuditoria() throws Exception
    {
        try
        {
            Session sessao = Hibernate4Util.getSessionFactory();
            Transaction transacao = sessao.beginTransaction();
            AuditReader reader = AuditReaderFactory.get(sessao);
            List<Object[]> resultList = reader.createQuery().forRevisionsOfEntity(Grupo.class, false, true).getResultList();
            List<Grupo> listaAuditada = new ArrayList<>();
            int contador = 0;
            for (Object[] objTmp : resultList)
            {
                Grupo grupoTmp = (Grupo) objTmp[0];
                grupoTmp.setRevType(objTmp[2].toString());
                listaAuditada.add(contador, grupoTmp);
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
            Transaction transacao = sessao.beginTransaction();
            Criteria cr = sessao.createCriteria(Grupo.class);
            cr.add(Restrictions.eq("codigo", valor));
            Grupo grupo = (Grupo) cr.uniqueResult();
            transacao.commit();
            return grupo;
        }
        catch (HibernateException e)
        {
            System.out.println("Não foi possível buscar o grupo. Erro: " + e.getMessage());
        }

        return null;
    }
}
