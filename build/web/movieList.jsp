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
	<h2><a href="<%=request.getContextPath() + "/checkOut.jsp"%>">Check Out <i class="fa fa-credit-card" aria-hidden="true"></i></a></h2>
	<h2><a href="<%=request.getContextPath() + "/shoppingCart.jsp"%>">my cart <i class="fa fa-shopping-cart" aria-hidden="true"></i></a></h2>
	<h2><a href="<%=request.getContextPath() + "/mainPage.jsp"%>">home <i class="fa fa-home" aria-hidden="true"></i></a></h2>
	<h2><a href="<%=request.getContextPath() + "/search.jsp"%>">advanced search <i class="fa fa-search" aria-hidden="true"></i></a></h2>


	<div>
	    <form action="<%=request.getContextPath() + "/NumPerPage"%>">
		<select name="numPerPage">
		    <option value="5">5</option>
		    <option value="10">10</option>
		    <option value="20">20</option>
		    <option value="50">50</option>
		</select>
		<input type="submit" value="change layout now!">
	    </form>
	</div>
	<br>
	<a href="<%=request.getContextPath()
		+ "/NextPrevPage?method=prev&numPerPage="
		+ request.getAttribute("numPerPage") + "&s="
		+ request.getAttribute("s")%>">
	    <i class="fa fa-arrow-left" aria-hidden="true"></i>&nbsp;
	    previous page</a> 
	&nbsp;&nbsp;&nbsp;
	<a href="<%=request.getContextPath()
		+ "/NextPrevPage?method=next&numPerPage="
		+ request.getAttribute("numPerPage") + "&s="
		+ request.getAttribute("s")%>">next page&nbsp;
	    <i class="fa fa-arrow-right" aria-hidden="true"></i>
	</a>

        <table border="1">
            <tr>
		<th>banner</th>
                <th>id</th>
                <th>
		    title
                    <a href="<%=request.getContextPath() + "/sortMovie?method=titleAsc"%>"><i class="fa fa-sort-alpha-asc" aria-hidden="true"></i></a> &nbsp;
		    <a href="<%=request.getContextPath() + "/sortMovie?method=titleDesc"%>"><i class="fa fa-sort-alpha-desc" aria-hidden="true"></i></a>
                </th>
                <th>
		    year
                    <a href="<%=request.getContextPath() + "/sortMovie?method=yearAsc"%>"><i class="fa fa-sort-numeric-asc" aria-hidden="true"></i></a> &nbsp;
		    <a href="<%=request.getContextPath() + "/sortMovie?method=yearDesc"%>"><i class="fa fa-sort-numeric-desc" aria-hidden="true"></i></a>
                </th>
                <th>director</th>
		<th>genres</th>
		<th>stars</th>
                <th>add into shopping cart</th>
            </tr>
            <%
		    ArrayList<backend.Movie> arrayMovie = (ArrayList<backend.Movie>) request.getSession().getAttribute("arrayMovie");
		    backend.DBConnection dbConnection = new backend.DBConnection();
		    int s = (Integer) request.getAttribute("s");
		    int numPerPage = (Integer) request.getAttribute("numPerPage");

		    for (int i = s; i < s + numPerPage && i < arrayMovie.size(); i++) {
			    backend.Movie movie = arrayMovie.get(i);
            %>
            <tr>
		<td>
		    <%			    out.print("<img src='" + movie.getBanner_url() + "' style='width:200px;height:230px;'/>");
		    %>
		</td>
                <td>
                    <%
			    int movieiD = movie.getId();
			    out.print("<div>" + movieiD + "</div>");
                    %>
                </td>
                <td>
                    <a class="tooltip" href="<%=request.getContextPath() + "/SingleMovie?id=" + movieiD%>" name="movieName" 
		       value=<%=movieiD%>>
			<%=movie.getTitle()%>
			<span class="tooltiptext" name="MovieInfoToolTip">
			</span>
                    </a>
                </td>
                <td>
                    <%			    out.print("<div>" + movie.getYear() + "</div>");
                    %>
                </td>
                <td>
                    <%
			    out.print("<div>" + movie.getDirector() + "</div>");
                    %>
                </td>
		<td>
                    <%
			    backend.SingleMovie singleMovie = new backend.SingleMovie(dbConnection.get_connection());
			    backend.Movie m = singleMovie.getSingleMovie(movie.getId());
			    for (String genre : m.getListGenres()) {
				    out.print("<div>" + genre + "</div>");
			    }
                    %>
                </td>                
		<td>
                    <%
			    for (String star : m.getListStars()) {
		    %>
		    <a  href = "<%=request.getContextPath() + "/SingleStar?star="%> + <%out.print(star);%>"><%out.print(star);%></a>
		    <%
			    }
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
                    %>
                </td>
            </tr>
            <%
		    }
            %>
        </table>
	<h2><a href="<%=request.getContextPath() + "/Logout"%>">Log Out</a></h2>
	<script lang="javascript" src="<%=request.getContextPath() + "/js/jquery-3.1.1.js"%>"></script>
	<script lang="javascript" src="<%=request.getContextPath() + "/js/meteor.js"%>"></script>
    </body>
</html>
