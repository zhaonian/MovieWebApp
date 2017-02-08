<%-- 
    Document   : singleStar
    Created on : Feb 5, 2017, 5:07:54 PM
    Author     : Luan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href=<%=request.getContextPath() + "/css/meteor.css"%> rel="stylesheet" type="text/css" />
	<link href=<%=request.getContextPath() + "/css/font-awesome.css"%> rel="stylesheet" type="text/css" />
        <title>Single Star</title>
    </head>
    <body>
	<h2><a href="<%=request.getContextPath() + "/checkOut.jsp"%>">Check Out <i class="fa fa-credit-card" aria-hidden="true"></i></a></h2>
	<h2><a href="<%=request.getContextPath() + "/shoppingCart.jsp"%>">my cart <i class="fa fa-shopping-cart" aria-hidden="true"></i></a></h2>
	<h2><a href="<%=request.getContextPath() + "/mainPage.jsp"%>">home <i class="fa fa-home" aria-hidden="true"></i></a></h2>
	<h2><a href="<%=request.getContextPath() + "/search.jsp"%>">advanced search <i class="fa fa-search" aria-hidden="true"></i></a></h2>
        <h1>Star Info</h1>
	<%
		backend.Star star = (backend.Star) request.getAttribute("singleStar");
		out.print("<div> Name: " + star.getName() + "</div>");
		out.print("<div> ID: " + star.getId() + "</div>");
		out.print("<div> Date of birth: " + star.getDob() + "</div>");
		out.print("<div> Movies: ");
		for (backend.Movie movie : star.getMovieList()) {
	%>
	<a href="<%=request.getContextPath() + "/SingleMovie?id="%> + <%out.print(movie.getId());%>"><%out.print(movie.getTitle());%></a>
	<%
		}
		out.print("</div>");
		out.print("<img src='" + star.getPicture_url() + "'/>");

	%>
	<h2><a href="<%=request.getContextPath() + "/Logout"%>">Log Out</a></h2>
    </body>
</html>
