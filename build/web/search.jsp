<%-- 
    Document   : search
    Created on : Jan 22, 2017, 7:47:24 PM
    Author     : Luan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
        <style type='text/css'>
            .form-input::before{
                content: "\f2c0";
                position: absolute;
                font-family: "FontAwesome";
                font-size: 15px;
                padding-top: 1.9%;
                padding-left: 0.3%;
            }

        </style>
    </head>
    <body bgcolor='#fddbff'>
        <form action=<%=request.getContextPath() + "/Search"%> method="POST">
            <div class="form-input">
                <h1>Title</h1>
                <input type="text" name="title" placeholder="e.g. Titanic">
            </div>

            <div class="form-input">
                <h1>Year Released</h1>
                <input type="text" name="yearFrom" placeholder="e.g. 1994">
                &nbsp; to &nbsp;
                <input type="text" name="yearTo" placeholder="e.g. 2017">
            </div>
            
            <div class="form-input">
                <h1>Director</h1>
                <input type="text" name="director" placeholder="e.g. Joss Whedon">
            </div>
            
            <div class="form-input">
                <h1>Star</h1>
                <input type="text" name="star" placeholder="e.g. Tom Cruise or Cruise">
            </div>
            <br>
            <br>
            <input type="submit" value="Login" class="btn-login">
        </form>
    </body>
</html>
