<%-- 
    Document   : teste
    Created on : 23/11/2014, 23:25:28
    Author     : José Luiz
--%>

<%@page import="model.Competicao"%>
<%@page import="dao.CompeticaoDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="ReportRankingApostador">
            <select name="competicao">
                <option value="">-- Selecione uma competição --</option>
            <%
                CompeticaoDao competicaoDAO = new CompeticaoDao();
                for(Competicao c : competicaoDAO.listar())
                    out.println("<option value=\""+c.getCodigo()+"\">"+c.getNome()+"</option>");
            %>
            </select><br>
            <input type="submit" value="Report Apostador" />
        </form><br>
        <form action="ReportRankingGrupo">
            <select name="competicao">
                <option value="">-- Selecione uma competição --</option>
            <%
                for(Competicao c : competicaoDAO.listar())
                    out.println("<option value=\""+c.getCodigo()+"\">"+c.getNome()+"</option>");
            %>
            </select><br>
            <input type="submit" value="Report Grupo" />
        </form>
    </body>
</html>
