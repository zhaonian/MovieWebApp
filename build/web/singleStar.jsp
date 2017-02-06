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
        <title>Single Star</title>
    </head>
    <body>
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

    </body>
</html>
