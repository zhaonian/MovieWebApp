<%-- 
    Document   : mainPage
    Created on : Jan 22, 2017, 6:15:26 PM
    Author     : Luan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href=<%=request.getContextPath() + "/css/meteor.css"%> rel="stylesheet" type="text/css" />
	<link href=<%=request.getContextPath() + "/css/font-awesome.css"%> rel="stylesheet" type="text/css" />
        <title>Main Page</title>
    </head>
    <body style="background-image: url(sky.png);
          background-repeat: no-repeat;
          background-size: cover;
          background-attachment: fixed;
          background-position: center;">
        <h1>Main Page</h1>
	<h2><a href="<%=request.getContextPath() + "/checkOut.jsp"%>">Check Out <i class="fa fa-credit-card" aria-hidden="true"></i></a></h2>
	<h2><a href="<%=request.getContextPath() + "/shoppingCart.jsp"%>">my cart <i class="fa fa-shopping-cart" aria-hidden="true"></i></a></h2>

        <a href=<%=request.getContextPath() + "/search.jsp"%>>Search</a>
        <a href=<%=request.getContextPath() + "/Browse"%>>Browse</a>
	<h2><a href="<%=request.getContextPath() + "/Logout"%>">Log Out</a></h2>
    </body>
</html>