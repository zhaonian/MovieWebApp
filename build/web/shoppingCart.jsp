<%-- 
    Document   : shoppingCart
    Created on : Feb 1, 2017, 6:58:46 PM
    Author     : Luan
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href=<%=request.getContextPath() + "/css/meteor.css"%> rel="stylesheet" type="text/css" />
        <title>Shopping Cart</title>
    </head>
    <body class=''>
        <h1>Shopping Cart</h1>
        <table border="4">
            <tr>
                <th>Title</th>
                <th>Price</th>
                <th>Qty</th>
                <th>update</th>
                <th>remove</th>
            </tr>

            <%
                    backend.DBConnection dbConnection = new backend.DBConnection();
                    backend.SingleMovie singleMovie = new backend.SingleMovie(dbConnection.get_connection());
                    ResultSet result = singleMovie.getSingleMovie(Integer.parseInt(request.getParameter("movieAddedToCart")));
                    while (result.next()) {
            %>
            <tr>
                <td>
                    <%
                            out.print(result.getString("title"));
                    %>
                </td>
                <td>
                    $1.00
                </td>
                <td>
                    <input type="text" name="quantity" placeholder="eg. 1">    
                </td>
                <td>
                    <input type="submit" value="Update">
                </td>
                <td>
                    <input type="submit" value="Remove">
                </td>
            </tr>
            <%
                    }
            %>
        </table>
        <h3>
            Total: $
            <%
                    out.print(request.getParameter("quantity"));
            %>
        </h3>
        <a href="<%=request.getContextPath() + "/checkOut.jsp"%>">Check Out</a>
    </body>
</html>
