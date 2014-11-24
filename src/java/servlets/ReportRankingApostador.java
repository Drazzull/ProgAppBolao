/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.CompeticaoDao;
import dao.RankingDao;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Competicao;
import model.RankingObj;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author José Luiz
 */
@WebServlet(name = "ReportRanking", urlPatterns =
{
    "/ReportRanking"
})
public class ReportRankingApostador extends HttpServlet
{

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        try
        {
            //Trazendo o arquivo em um objeto File...
            File reportFile = new File(request.getServletContext().getRealPath("/WEB-INF/reportRankingApostador.jasper"));

            //Definindo mapa de parâmetros que serão enviados ao jasper...
            Map parameters = new HashMap();
            String titulo = "Ranking por apostador - ";
            //Exemplo de passagem por parâmetro do título do relatório...
            String id = request.getParameter("competicao");
            Integer id_convertido = Integer.parseInt(id);
            CompeticaoDao competicaoDAO = new CompeticaoDao();
            Competicao competicao = competicaoDAO.buscar(id_convertido);
            titulo = titulo + competicao.getNome();
            parameters.put("Titulo", titulo);

            RankingDao rankingDAO = new RankingDao();
            List<RankingObj> rankings = rankingDAO.listarPorApostador(competicao);
            //Criando nosso próprio dataSouce que levará os dados ao relatório...
            JRDataSource dataSource = new JRBeanCollectionDataSource(rankings);

            //Criação do array de bytes que será o arquivo PDF...
            // Poderiamos ter outros formatos suportados...
            byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameters, dataSource);

            //Preparando a resposta para o navegador...
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);

            //Atribuindo a saida para fazer download do arquivo PDF gerado...
            ServletOutputStream ouputStream = response.getOutputStream();
            ouputStream.write(bytes, 0, bytes.length);
            ouputStream.flush();
            ouputStream.close();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
