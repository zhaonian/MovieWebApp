<%-- 
    Document   : singleMovie
    Created on : Jan 25, 2017, 9:57:42 AM
    Author     : Luan
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href=<%=request.getContextPath() + "/css/font-awesome.css"%> rel="stylesheet" type="text/css" />
        <link href=<%=request.getContextPath() + "/css/meteor.css"%> rel="stylesheet" type="text/css" />
        <title>Movie Info</title>
    </head>
    <body>
        <div class='container'>
            <%
                    ResultSet result = (ResultSet) request.getAttribute("result");
                    while (result.next()) {
                            out.print("<h1>" + result.getString("title") + "</h1>");
                            out.print("<div> Year: " + result.getInt("year") + "</div>");
                            out.print("<div> ID: " + result.getInt("id") + "</div>");
                            out.print("<div> Director: " + result.getString("director") + "</div>");
                            //                        out.print("<div> Stars " + result.getString("stars") + "</div>");
                            out.print("<a href='" + result.getString("trailer_url") + "'> "
                                    + "<i class='fa fa-play-circle-o'></i>Watch Trailor</a>");
                            //                        out.print("<img src=url(" + result.getString("banner_url") + ")/>");
                    }
            %>
        </div>
    </body>
</html>
