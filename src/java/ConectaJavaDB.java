
import conexao.Hibernate4Util;
import org.hibernate.Session;

/**
 *
 * @author Eduardo Comin
 * @since 10/08/2011
 */
public class ConectaJavaDB
{

    public static void main(String[] args)
    {
        Session sessao = null;
        try
        {
            sessao = Hibernate4Util.getSessionFactory();
            System.out.println("Conectou!");
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
