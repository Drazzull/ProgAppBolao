/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import conexao.Hibernate4Util;
import dao.ApostadorDao;
import dao.GrupoDao;
import dao.TimeDao;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Apostador;
import model.Grupo;
import model.Time;
import org.hibernate.Session;

/**
 *
 * @author José Luiz
 */
public class ConectaMySQL
{

    public static void main(String[] args) throws ParseException
    {
        Session sessao = null;
        try
        {
            sessao = Hibernate4Util.getSessionFactory();
            Date data = new Date();
            System.out.println("Conectou!" + data);

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date data_fun;
        TimeDao timeDAO = new TimeDao();
        List<Time> times = new ArrayList<>();

        data_fun = sdf.parse("1935-12-16");
        Time sao_paulo = new Time("São Paulo FC", data_fun, "São Paulo - SP", "www.saopaulofc.com.br", "spfc@hotmail.com", "(011)3895-2141", "Rua Morumbi Nº369", "A história do São Paulo Futebol Clube começou a se desenhar em 1900, quando o Club \nAthlético Paulistano foi fundado. O Paulistano logo se destacou, tornando-se a grande potência do futebol paulista e brasileiro no início do século XX. O Paulistano se recusava a aderir ao iminente profissionalismo\n do futebol, e alguns dissidentes juntaram-se à Associação Atlética das Palmeiras, que possuía o melhor estádio da época, mas estava com muitas dívidas, para fundar o São Paulo.");
        times.add(sao_paulo);
        data_fun = null;

        data_fun = sdf.parse("1903-09-15");
        Time gremio = new Time("Grêmio Foot-Ball Porto Alegrense", data_fun, "Porto Alegre - RS", "www.gremio.com.br", "gremiopa@gmail.com", "(054)3214-9632", "Rua Azul Nº314", "O Grêmio Foot-Ball Porto Alegrense (conhecido apenas por Grêmio) é um \nclube de futebol brasileiro da cidade de Porto Alegre, no Rio Grande do Sul, fundado em 15 de setembro de 19033 e suas cores são o azul, preto e branco.");
        times.add(gremio);
        data_fun = null;

        data_fun = sdf.parse("1910-09-01");
        Time corinthians = new Time("Sport Club Corinthians Paulista", data_fun, "São Paulo - SP", "www.corinthians.com", "sccorinthiansp@hotmail.com", "(011)3171-2411", "Rua Timão Nº171", "O Sport Club Corinthians Paulista (conhecido apenas por \nCorinthians e cujo acrônimo é SCCP) é um clube multiesportivo brasileiro sediado na cidade de São Paulo. Foi fundado como uma equipe de futebol no dia 1º de setembro de 1910 por um grupo de operários \ndo bairro Bom Retiro.");
        times.add(corinthians);
        data_fun = null;

        data_fun = sdf.parse("1895-11-17");
        Time mengao = new Time("Clube de Regatas do Flamengo", data_fun, "Rio de Janeito - RJ", "www.mengao.com", "mengo@gmail.com", "(017)3274-1274", "Rua da Gávea Nº152", "O Clube de Regatas do Flamengo (mais conhecido simplesmente como Flamengo, \ne popularmente pelos apelidos de Mengo, Mengão e Fla) é uma agremiação poliesportiva brasileira com sede na cidade do Rio de Janeiro. O clube foi fundado para disputas de remo em 17 de novembro de 1895.6 Hoje é um dos\n clubes mais bem-sucedidos no futebol brasileiro e é o clube mais conhecido e popular em todo o Brasil, tendo a maior torcida de futebol do mundo com cerca de 39,1 milhões de torcedores.");
        times.add(mengao);
        data_fun = null;

        data_fun = sdf.parse("1898-08-21");
        Time vasco = new Time("Clube de Regatas Vasco da Gama", data_fun, "Rio de Janeito - RJ", "www.vasco.com", "vasco@gmail.com", "(017)3274-2111", "Rua Maritima Nº214", "Club de Regatas Vasco da Gama ComC • MHM3 é uma agremiação sociopoliesportiva\n brasileira com sede na cidade do Rio de Janeiro. Foi fundada em 21 de agosto de 1898 por um grupo de remadores que, inspirados nas celebrações do quarto centenário da descoberta do caminho marítimo para as Índias, \nocorrida em 1498, batizaram a nova agremiação com o nome do heroico português que alcançara tal feito, o navegador Vasco da Gama.");
        times.add(vasco);
        data_fun = null;

        data_fun = sdf.parse("1902-07-21");
        Time fluminense = new Time("Fluminense Sport Club", data_fun, "Rio de Janeito - RJ", "www.fluminense.com", "fluminense@hotmail.com", "(017)3274-1478", "Rua da Baixada Nº784", "Fluminense Football Club é uma agremiação poliesportiva e cultural \nsediada na cidade do Rio de Janeiro, no Brasil, fundada em 21 de julho de 1902. É uma sociedade civil de caráter desportivo que tem como principal atividade o futebol, tendo sido o primeiro colocado do Ranking da CBF em \n2013 e quinto em 2014.");
        times.add(fluminense);
        data_fun = null;

        data_fun = sdf.parse("1909-04-04");
        Time inter = new Time("Sport Club Internacional", data_fun, "Porto Alegre - RS", "www.internacional.com", "internacional@gmail.com", "(054)3144-2517", "Rua da Redenção Nº141", "O Sport Club Internacional, conhecido apenas por Internacional, Inter,\n SC Internacional ou ainda Inter de Porto Alegre e cujo acrônimo é SCI, é um clube esportivo brasileiro de futebol da cidade de Porto Alegre, no Rio Grande do Sul.");
        times.add(inter);
        data_fun = null;

        data_fun = sdf.parse("1921-01-02");
        Time cruzeiro = new Time("Cruzeiro Esporte Clube", data_fun, "Belo Horizonte - MG", "www.cruzeiro.com", "cruzeiro@hotmail.com", "(060)3752-1478", "Rua da Gloria Nº952", "Cruzeiro Esporte Clube é uma associação polidesportiva brasileira, com sede em Belo Horizonte, no estado de Minas Gerais.\nFundado em 1921 com o nome de Sociedade Esportiva Palestra Itália, foi rebatizado para seu nome atual em 1942 - em referência ao Cruzeiro do Sul - por imposição do \ngoverno federal à época proibiu o uso no país de quaisquer símbolos de Alemanha, Itália e Japão, nações inimigas do Brasil no contexto da Segunda Guerra Mundial");
        times.add(cruzeiro);
        data_fun = null;

        data_fun = sdf.parse("1912-04-14");
        Time santos = new Time("Santos Futebol Clube", data_fun, "Santos - SP", "www.santos.com", "santos@gmail.com", "(022)3852-2147", "Rua do Peixe Nº741", "Santos Futebol Clube, mais conhecido como Santos, é um clube brasileiro de futebol, fundado em 1912,\n com sede em Santos, no estado de São Paulo. Eleito pela FIFA como a melhor equipe de futebol das Américas do século XX, o Santos é um dos 5 clubes que nunca foram rebaixados para a 2º divisão do\n Campeonato Brasileiro e é o único clube brasileiro a conquistar, num mesmo ano, em 1962, um título estadual, um nacional, um continental e um intercontinental.");
        times.add(santos);
        data_fun = null;

        data_fun = sdf.parse("1924-03-26");
        Time atletico = new Time("Clube Atlético Paranaense", data_fun, "Curitiba - PR", "www.atleticopr.com", "atleticopr@hotmail.com", "(047)3652-2356", "Rua América Nº214", "Clube Atlético Paranaense (conhecido também como Atlético-PR e cujo acrônimo é CAP)\n é um clube de futebol brasileiro, da cidade de Curitiba. Foi fundado em 26 de março de 1924, a partir da fusão do Internacional Futebol Clube e do América Futebol Clube.");
        times.add(atletico);
        data_fun = null;

        for (Time t : times)
        {
            timeDAO.salvar(t);
        }

        GrupoDao grupoDAO = new GrupoDao();
        List<Grupo> grupos = new ArrayList<>();

        Grupo grupoa = new Grupo("Eng. Computação");
        grupos.add(grupoa);

        Grupo grupob = new Grupo("Unidos do Mengão");
        grupos.add(grupob);

        for (Grupo g : grupos)
        {
            grupoDAO.salvar(g);
        }

        Date data_nasc;
        ApostadorDao apostadorDAO = new ApostadorDao();
        List<Apostador> apostadores = new ArrayList<>();

        data_nasc = sdf.parse("1994-05-14");
        Apostador alana = new Apostador("Alana Matielo", "89553644660", data_nasc, "aladeli@hotmail.com", "lana_delicia", inter, grupob);
        apostadores.add(alana);
        data_nasc = null;

        data_nasc = sdf.parse("1930-11-10");
        Apostador van = new Apostador("Van Hohenheim", "26835321806", data_nasc, "heimchan@gmail.com", "van", mengao, grupob);
        apostadores.add(van);
        data_nasc = null;

        data_nasc = sdf.parse("1942-11-18");
        Apostador ishin = new Apostador("Ishin Shiba", "62828459128", data_nasc, "ishin69@gmail.com", "ishi_taichou", cruzeiro, grupoa);
        apostadores.add(ishin);
        data_nasc = null;

        data_nasc = sdf.parse("1985-08-15");
        Apostador boruto = new Apostador("Bolt Uzumaki", "18826946272", data_nasc, "n1boruto@gmail.com", "parafuso", sao_paulo, grupoa);
        apostadores.add(boruto);
        data_nasc = null;

        data_nasc = sdf.parse("1912-10-06");
        Apostador madara = new Apostador("Madara Uchiha", "54517155994", data_nasc, "madara666@gmail.com", "diva_lendaria", cruzeiro, grupoa);
        apostadores.add(madara);
        data_nasc = null;

        for (Apostador a : apostadores)
        {
            apostadorDAO.salvar(a);
        }
    }
}
