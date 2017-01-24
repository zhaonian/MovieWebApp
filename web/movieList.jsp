<%-- 
    Document   : movieList
    Created on : Jan 23, 2017, 2:32:32 PM
    Author     : Luan
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/meteor.css" rel="stylesheet" type="text/css" />
        <title>Movie List</title>
    </head>
    <body style="background-image: url(sky.png);    
          background-repeat: no-repeat;
          background-size: cover;
          background-attachment: fixed;
          background-position: center;">

        <table border="1">
            <tr>
                <th>id</th>
                <th>title</th>
                <th>year</th>
                <th>director</th>
            </tr>
            <%
                    ResultSet result = (ResultSet) request.getAttribute("result");
                    while (result.next()) {
            %>
            <tr>
                <td>
                    <%
                            out.print("<div>" + result.getInt("id") + "</div>");
                    %>
                </td>
                <td>
                    <%
                            out.print("<div>" + result.getString("title") + "</div>");
                    %>
                </td>
                <td>
                    <%
                            out.print("<div>" + result.getInt("year") + "</div>");
                    %>
                </td>
                <td>
                    <%
                            out.print("<div>" + result.getString("director") + "</div>");
                    %>
                </td>
            </tr>
            <%
                    }
            %>
        </table>


    </body>
</html>
