<%-- 
    Document   : confirmation
    Created on : Feb 1, 2017, 7:27:29 PM
    Author     : Luan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href=<%=request.getContextPath() + "/css/font-awesome.css"%> rel="stylesheet" type="text/css" />
	<link href=<%=request.getContextPath() + "/css/meteor.css"%> rel="stylesheet" type="text/css" />
        <title>Confirmation</title>
    </head>
    <body>
        <h1>Confirmation</h1>
	<h2><a href="<%=request.getContextPath() + "/shoppingCart.jsp"%>">my cart <i class="fa fa-shopping-cart" aria-hidden="true"></i></a></h2>
	<h2><a href="<%=request.getContextPath() + "/mainPage.jsp"%>">home <i class="fa fa-home" aria-hidden="true"></i></a></h2>

        <%
		if ((Integer) request.getAttribute("confirmed") == 1) {
        %>
        <h2>
            Transaction succeeds! Thank You!
        </h2>
        <%
	} else {
        %>
        <h2>
            Wrong credit card info. Please try again.
        </h2>
        <%
		}
        %>
    </body>
    <h2><a href="<%=request.getContextPath() + "/Logout"%>">Log Out</a></h2>
</html>
