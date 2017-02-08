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
	<link href=<%=request.getContextPath() + "/css/font-awesome.css"%> rel="stylesheet" type="text/css" />
        <title>Shopping Cart</title>
    </head>
    <body>
        <h1>Shopping Cart</h1>
	<h2><a href="<%=request.getContextPath() + "/mainPage.jsp"%>">home <i class="fa fa-home" aria-hidden="true"></i></a></h2>
	<h2><a href="<%=request.getContextPath() + "/search.jsp"%>">advanced search <i class="fa fa-search" aria-hidden="true"></i></a></h2>
        <table border="4">
            <tr>
                <th>Title</th>
                <th>Price</th>
                <th>Manage Qty</th>
                <th>update</th>
                <th>remove</th>
		<th>Current Qty</th>
            </tr>

	    <%
		    ArrayList<backend.Movie> shoppingCart = (ArrayList<backend.Movie>) request.getSession().getAttribute("shoppingCart");
		    for (backend.Movie movie : shoppingCart) {

	    %>
	    <tr>
		<td>
		    <%				out.print(movie.getTitle());
		    %>
		</td>
		<td>
		    1
		</td>
	    <form action=<%=request.getContextPath() + "/QuantityManager"%> method="POST">
		<td>
		    <input type="text" name=<%=movie.getId()%> placeholder="eg. 1">    
		</td>
		<td>
		    <input type="submit" name="submit" value="update">
		</td>
		<td>
		    <input type="submit" name="submit" value="remove">
		</td>
	    </form>
	    <td>
		<%
			out.print(request.getSession().getAttribute("" + movie.getId()));
		%>
	    </td>


	</tr>
	<%
		}
	%>

    </table>
    <h3>
	Total: $
	<%
		out.print(request.getSession().getAttribute("total"));
	%>


    </h3>
    <a href="<%=request.getContextPath() + "/checkOut.jsp"%>">Check Out</a>
    <h2><a href="<%=request.getContextPath() + "/Logout"%>">Log Out</a></h2>
</body>
</html>
