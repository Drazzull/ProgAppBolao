/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import conexao.Hibernate4Util;
import java.util.Date;
import org.hibernate.Session;

/**
 *
 * @author José Luiz
 */
public class ConectaMySQL {
        public static void main(String[] args) {
            Session sessao = null;
            try {
                    sessao = Hibernate4Util.getSessionFactory();
                    Date data = new Date();
                    System.out.println("Conectou!" +data);
                    
	} catch (Exception e){
                System.out.println(e.getMessage());
        }
}
}
