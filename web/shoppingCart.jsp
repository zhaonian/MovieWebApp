<%-- 
    Document   : shoppingCart
    Created on : Feb 1, 2017, 6:58:46 PM
    Author     : Luan
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href=<%=request.getContextPath() + "/css/meteor.css"%> rel="stylesheet" type="text/css" />
        <title>Shopping Cart</title>
    </head>
    <body>
        <h1>Shopping Cart</h1>
	<h2><a href="<%=request.getContextPath() + "/mainPage.jsp"%>">home</a></h2>
	<h2><a href="<%=request.getContextPath() + "/search.jsp"%>">advanced search</a></h2>
        <table border="4">
            <tr>
                <th>Title</th>
                <th>Price</th>
                <th>Qty</th>
                <th>update</th>
                <th>remove</th>
            </tr>
	    <form action=<%=request.getContextPath() + "/QuantityManager"%> method="POST">
		<%
			ArrayList<backend.Movie> shoppingCart = (ArrayList<backend.Movie>) request.getSession().getAttribute("shoppingCart");
			for (backend.Movie movie : shoppingCart) {

		%>
		<tr>
		    <td>
			<%			    out.print(movie.getTitle());
			%>
		    </td>
		    <td>
			1
		    </td>
		    <td>
			<input type="text" name=<%=movie.getId()%> placeholder="eg. 1">    
		    </td>
		    <td>
			<input type="submit" name="submit" value="update">
		    </td>
		    <td>
			<input type="submit" name="submit" value="remove">
		    </td>

		</tr>
		<%
			}
		%>
	    </form>
	</table>
	<h3>
	    Total: $
	    <%
		    out.print(request.getAttribute("quantity"));
	    %>
	</h3>
	<a href="<%=request.getContextPath() + "/checkOut.jsp"%>">Check Out</a>
    </body>
</html>
