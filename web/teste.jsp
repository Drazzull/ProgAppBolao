<%-- 
    Document   : teste
    Created on : 23/11/2014, 23:25:28
    Author     : José Luiz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="ReportRankingApostador">
            <input type="submit" value="Report Apostador" />
            <input type="hidden" name="competicao" value="1" />
        </form><br>
        <form action="ReportRankingGrupo">
            <input type="submit" value="Report Grupo" />
            <input type="hidden" name="competicao" value="1" />
            
        </form>
    </body>
</html>
