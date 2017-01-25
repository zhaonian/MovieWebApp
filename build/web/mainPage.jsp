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
        <link href="css/meteor.css" rel="stylesheet" type="text/css" />
        <title>Main Page</title>
    </head>
    <body style="background-image: url(sky.png);
          background-repeat: no-repeat;
          background-size: cover;
          background-attachment: fixed;
          background-position: center;">
        <h1>Main Page</h1>
        <a href=<%=request.getContextPath() + "/search.jsp"%>>Search</a>
        <a href=<%=request.getContextPath() + "/browse.jsp"%>>Browse</a>
    </body>
</html>