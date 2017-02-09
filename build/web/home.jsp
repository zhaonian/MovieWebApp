<%-- 
    Document   : home.jsp
    Created on : Jan 22, 2017, 4:25:30 PM
    Author     : Luan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html charset=UTF-8"/>
        <link href=<%=request.getContextPath() + "/css/font-awesome.css"%> rel="stylesheet" type="text/css" />
        <link href=<%=request.getContextPath() + "/css/meteor.css"%> rel="stylesheet" type="text/css" />
	<script src='https://www.google.com/recaptcha/api.js'></script>
    </head>

    <body>
        <h1 align="center"><i class="fa fa-film" aria-hidden="true"></i>  Welcome to Zhaonian Luan's MovieWebApp!</h1>

        <div class="container">
            <form action=<%=request.getContextPath() + "/Login"%> method="POST">
                <div class="form-input">
                    <input class="input" type="text" name="email" placeholder="Enter Email">
                </div>

                <div class="form-input">
                    <input class="input" type="password" name="password" placeholder="Enter Password">
                </div>
		<br>
                <input type="submit" value="Login" class="btn-login">
		<div class="g-recaptcha" data-sitekey="6Ld55xQUAAAAABslVbsAVbgHjmDRq-4p9PSdQNL4"></div>
            </form>
        </div>
    </body>
</html>