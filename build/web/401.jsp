<%-- 
    Document   : 401
    Created on : Jan 22, 2017, 6:16:55 PM
    Author     : Luan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href=<%=request.getContextPath() + "/css/font-awesome.css"%> rel="stylesheet" type="text/css" />
        <title>401 Error Page</title>
    </head>
    <body>
        <h1>
	    <h2><a href="<%=request.getContextPath() + "/home.jsp"%>">user log In</a></h2>
	    <h2><a href="<%=request.getContextPath() + "/dashBoard.jsp"%>">employee log In</a></h2>
            <i style="color:red;" class="fa fa-times" aria-hidden="true"></i>
            <div style="margin-left: 10px;
                 display: inline-block;">401 Error: Authentication Failed</div>
        </h1>
    </body>
</html>
