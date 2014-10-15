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
import model.Apostador;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;

public class ApostadorDao
{

    /**
     * Salva o apostador no banco de dados
     *
     * @param apostador Objeto contendo as informações do apostador que serão salvas
     */
    public void salvar(Apostador apostador)
    {
        CrudGenerico.salvar(apostador);
    }

    /**
     * Realiza a alteração dos dados de um apostador já salvo anteriormente no banco
     *
     * @param apostador Objeto do tipo apostador com os dados que serão salvos
     */
    public void atualizar(Apostador apostador)
    {
        CrudGenerico.atualizar(apostador);
    }

    /**
     * Realiza a exclusão do apostador
     *
     * @param apostador Objeto do tipo apostador que será excluído
     */
    public void excluir(Apostador apostador)
    {
        CrudGenerico.excluir(apostador);
    }

    /**
     * Obtém lista com todos os apostadores do banco
     *
     * @return Lista com todos os grupos do banco
     */
    public List<Apostador> listar()
    {
        try
        {
            Session sessao = Hibernate4Util.getSessionFactory();
            Transaction transacao = sessao.beginTransaction();
            Query consulta = sessao.createQuery("from Apostador");
            List<Apostador> resultado = consulta.list();
            transacao.commit();
            return resultado;
        }
        catch (HibernateException e)
        {
            System.out.println("Não foi possível selecionar apostadores. Erro: " + e.getMessage());
            throw new HibernateException(e);
        }
    }

    public List<Apostador> listarAuditoria() throws Exception
    {
        try
        {
            Session sessao = Hibernate4Util.getSessionFactory();
            Transaction transacao = sessao.beginTransaction();
            AuditReader reader = AuditReaderFactory.get(sessao);
            List<Object[]> resultList = reader.createQuery().forRevisionsOfEntity(Apostador.class, false, true).getResultList();
            List<Apostador> listaAuditada = new ArrayList<>();
            int contador = 0;
            for (Object[] objTmp : resultList)
            {
                Apostador apostadorTmp = (Apostador) objTmp[0];
                apostadorTmp.setRevType(objTmp[2].toString());
                if (apostadorTmp.getGrupo() != null)
                {
                    apostadorTmp.getGrupo().getCodigo();
                }
                if (apostadorTmp.getTimeDePreferencia() != null)   
                {
                    apostadorTmp.getTimeDePreferencia().getCodigo();
                }
                listaAuditada.add(contador, apostadorTmp);
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
     * Busca o código de um apostador
     *
     * @param valor Código a ser pesquisado
     * @return Apostador referente ao código
     */
    public Apostador buscar(int valor)
    {
        try
        {
            Session sessao = Hibernate4Util.getSessionFactory();
            Transaction transacao = sessao.beginTransaction();
            Query consulta = sessao.createQuery("from Apostador where codigo = :parametro");
            consulta.setInteger("parametro", valor);
            Apostador apostador = (Apostador) consulta.uniqueResult();
            transacao.commit();
            return apostador;
        }
        catch (HibernateException e)
        {
            System.out.println("Não foi possível buscar o apostador. Erro: " + e.getMessage());
        }

        return null;
    }
}
