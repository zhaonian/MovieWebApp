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
        <title>Confirmation</title>
    </head>
    <body>
        <h1>Confirmation</h1>
        <%
                if ((boolean) request.getAttribute("confirmed")) {
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
</html>
