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
	<h2><a href="<%=request.getContextPath() + "/shoppingCart.jsp"%>">my cart</a></h2>
	<h2><a href="<%=request.getContextPath() + "/mainPage.jsp"%>">home</a></h2>
	<h2><a href="<%=request.getContextPath() + "/search.jsp"%>">advanced search</a></h2>
	
        <table border="1">
            <tr>
		<th>banner</th>
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
		    ArrayList<backend.Movie> arrayMovie = (ArrayList<backend.Movie>) request.getAttribute("arrayMovie");
		    for (backend.Movie movie : arrayMovie) {
            %>
            <tr>
		<td>
		    <%
			    out.print("<img src='" + movie.getBanner_url() + "' style='width:200px;height:230px;'/>");
		    %>
		</td>
                <td>
                    <%
			    out.print("<div>" + movie.getId() + "</div>");
                    %>
                </td>
                <td>
                    <a href="<%=request.getContextPath() + "/SingleMovie?id=" + movie.getId()%>">
                        <%
				out.print("<div>" + movie.getTitle() + "</div>");
                        %>
                    </a>
                </td>
                <td>
                    <%
			    out.print("<div>" + movie.getYear() + "</div>");
                    %>
                </td>
                <td>
                    <%
			    out.print("<div>" + movie.getDirector() + "</div>");
                    %>
                </td>
                <td>
                    <%
			    out.print("<form action=" + request.getContextPath()
				    + "/ShoppingCart"
				    + " method='POST'>"
				    + "<div style='padding-left: 35%;'>"
				    + "<button value=" + movie.getId()
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
