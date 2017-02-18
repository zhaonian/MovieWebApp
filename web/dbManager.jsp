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
		Insertion succeeds! <i class="fa fa-smile-o" aria-hidden="true"></i>
	    </div>
	    <%
	    } else if ((Integer) request.getAttribute("starInsertSucceed") == 2) {
	    %>
	    <div>
		Insertion fails <i class="fa fa-frown-o" aria-hidden="true"></i>
	    </div>
	    <%
		    }
	    %>
	</div>
	<br>
	<br>
	<h1>
	    Show Database MetaData
	</h1>
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

    </body>
</html>
