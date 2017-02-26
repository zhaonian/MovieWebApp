<%-- 
    Document   : dbManager
    Created on : Feb 15, 2017, 4:37:07 PM
    Author     : Luan
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href=<%=request.getContextPath() + "/css/font-awesome.css"%> rel="stylesheet" type="text/css" />
        <link href=<%=request.getContextPath() + "/css/meteor.css"%> rel="stylesheet" type="text/css" />
        <title>Employee Dashboard</title>
    </head>
    <body>
        <h1>Insert a star</h1>
	<form action=<%=request.getContextPath() + "/EmployeeDashboard?method=StarInsertion"%> method="POST">
            <div>
                <input type="text" name="starName" placeholder="e.g. Tom Cruise">
            </div>
            <br>
            <button style="background-color: #2ECC71; 
                    border-radius: 6px; 
                    width: 100px; height: 40px;">
                <i class="fa fa-user-plus" aria-hidden="true"></i> Insert
            </button>
        </form>  
	<div>
	    <%
		    if ((Integer) request.getAttribute("starInsertSucceed") == 1) {
	    %>
	    <div>
		<h3>Insertion succeeds! <i class="fa fa-smile-o" aria-hidden="true"></i></h3>
	    </div>
	    <%
	    } else if ((Integer) request.getAttribute("starInsertSucceed") == 2) {
	    %>
	    <div>
		<h3>Insertion fails <i class="fa fa-frown-o" aria-hidden="true"></i></h3>
	    </div>
	    <%
		    }
	    %>
	</div>
	<br>

	<h1>Add a Movie</h1>
        <form action=<%=request.getContextPath() + "/MovieInsertion"%> method="POST">
	    <div>
                <h2 style="display: inline-block;">Title</h2>
                <input style="margin-left: 1%;" type="text" name="title" placeholder="e.g. Titanic">
            </div>

            <div>
                <h2 style="display: inline-block;">Year Released</h2>
                <input style="margin-left: 1%;" type="text" name="year" placeholder="e.g. 1994">
            </div>

            <div>
                <h2 style="display: inline-block;">Director</h2>
                <input style="margin-left: 1%;" type="text" name="director" placeholder="e.g. Joss Whedon">
            </div>
	    <div>
                <h2 style="display: inline-block;">Banner URL</h2>
                <input style="margin-left: 1%;" type="text" name="banner">
            </div>
	    <div>
                <h2 style="display: inline-block;">Trailer URL</h2>
                <input style="margin-left: 1%;" type="text" name="trailer">
            </div>
	    <div>
                <h2 style="display: inline-block;">Star first name</h2>
                <input style="margin-left: 1%; margin-right: 4%;" type="text" name="starFirstName" placeholder="e.g. Tom">
		&nbsp;&nbsp;
                <h2 style="display: inline-block;">Star last name</h2>
                <input style="margin-left: 1%;" type="text" name="starLastName" placeholder="e.g. Cruise">
            </div>
	    <div>
                <h2 style="display: inline-block;">Genre</h2>
                <input style="margin-left: 1%;" type="text" name="genre">
            </div>
            <br>
            <button style="background-color: #2ECC71; 
                    border-radius: 6px; 
                    width: 100px; height: 40px;">
                <i class="fa fa-plus" aria-hidden="true"></i> Add
            </button>
        </form>
	<%
		int insertionStatus = (Integer) request.getAttribute("starInsertSucceed");
		if (insertionStatus == 0) {
	%>
	<div>
	    <h3>Insertion fails <i class="fa fa-frown-o" aria-hidden="true"></i></h3>
	</div>
	<%
	} else if (insertionStatus > 0) {
	%>
	<div>
	    <h3>Insertion succeeds <i class="fa fa-smile-o" aria-hidden="true"></i></h3>
	</div>
	<%
		}
	%>
	<br>

	<h1>Show Database MetaData</h1>
	<form action=<%=request.getContextPath() + "/EmployeeDashboard?method=DatabaseInfo"%> method="POST">
	    <button style="background-color: #2ECC71; 
		    border-radius: 6px; 
		    width: 100px; height: 40px;">
		<i class="fa fa-database" aria-hidden="true"></i> Show MetaData
	    </button>
	</form>  
	<%
		ArrayList<String> infoArray = (ArrayList<String>) request.getAttribute("infoArray");
		for (int i = 0; i < infoArray.size(); i++) {
			out.print(infoArray.get(i));
		}
	%>
	<br>
    </body>
</html>
