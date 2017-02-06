<%-- 
    Document   : singleMovie
    Created on : Jan 25, 2017, 9:57:42 AM
    Author     : Luan
--%>

<%@page import="java.util.ArrayList"%>
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
	<h2><a href="<%=request.getContextPath() + "/shoppingCart.jsp"%>">my cart</a></h2>
	<h2><a href="<%=request.getContextPath() + "/mainPage.jsp"%>">home</a></h2>
	<h2><a href="<%=request.getContextPath() + "/search.jsp"%>">advanced search</a></h2>

        <div>
            <%
		    backend.Movie movie = (backend.Movie) request.getAttribute("movie");
		    out.print("<h2> Title: " + movie.getTitle() + "</h2>");
		    out.print("<div> ID: " + movie.getId() + "</div>");
		    out.print("<div> Year: " + movie.getYear() + "</div>");
		    out.print("<div> Director: " + movie.getDirector() + "</div>");

		    out.print("<div> Genres: ");
		    for (String genre : movie.getListGenres()) {
	    %>
	    <a href="<%=request.getContextPath() + "/SingleGenre?genre="%> + <%out.print(genre);%>"><%out.print(genre);%></a>
	    <%
		    }
		    out.print("</div>");

		    out.print("<div> Stars: ");
		    for (String star : movie.getListStars()) {
	    %>
	    <a href="<%=request.getContextPath() + "/SingleStar?star="%> + <%out.print(star);%>"><%out.print(star);%></a>
	    <%
		    }
		    out.print("</div>");

		    out.print("<div> Trailer: " + movie.getTrailer() + "</div>");
		    out.print("<img src='" + movie.getBanner_url() + "'/>");
            %>
        </div>
    </body>
</html>
