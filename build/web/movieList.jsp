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
        <link href=<%=request.getContextPath() + "/css/font-awesome.css"%> rel="stylesheet" type="text/css" />
        <link href=<%=request.getContextPath() + "/css/meteor.css"%> rel="stylesheet" type="text/css" />
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
                <th>
                    <a href="<%=request.getContextPath() + "/sortMovie?method=title"%>">title</a>
                </th>
                <th>
                    <a href="<%=request.getContextPath() + "/sortMovie?method=year"%>">year</a>
                </th>
                <th>director</th>
                <th>add into shopping cart</th>
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
                    <a href="<%=request.getContextPath() + "/SingleMovie?id=" + result.getInt("id")%>">
                        <%
                                out.print("<div>" + result.getString("title") + "</div>");
                        %>
                    </a>
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
                <td>
                    <%
                            out.print("<form action=" + request.getContextPath()
                                    + "/shoppingCart.jsp"
                                    + " method='POST'>"
                                    + "<div style='padding-left: 35%;'>"
                                    + "<button value=" + result.getInt("id")
                                    + " name='movieAddedToCart'>"
                                    + "<i class='fa fa-shopping-cart' aria-hidden='true'></i>"
                                    + "&nbsp;add"
                                    + "</button>"
                                    + "</div>"
                                    + "</form>");
//                            out.print("<a href='" + request.getContextPath() + "/shoppingCart.jsp?id=" + result.getInt("id") + ">"
//                                    + result.getInt("id") + "</a>");
                    %>
                </td>
            </tr>
            <%
                    }
            %>
        </table>


    </body>
</html>
