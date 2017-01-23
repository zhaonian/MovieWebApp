<%-- 
    Document   : home.jsp
    Created on : Jan 22, 2017, 4:25:30 PM
    Author     : Luan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/font-awesome.css">
        <style type="text/css">
            .container{
                width: 260px;
                height: 150px;
                text-align: center;
                background-color: rgba(52, 73, 94, 0.7);
                margin: 0 auto;
                margin-top: 1%;
                color: #9B59B6;
            }
            input{
                height: 20px;
                width: 140px;
                font-size: 12px;
                margin-top: 7%;
                background-color: #fff;
                padding-left: 4%;
            }
            .form-input::before{
                content: "\f2c0";
                position: absolute;
                font-family: "FontAwesome";
                font-size: 15px;
                padding-top: 1.9%;
                padding-left: 0.3%;
            }
            .form-input:nth-child(2)::before{
                content: "\f023";
                padding-left: 0.4%;
            }
            .btn-login{
                width: 100px; 
                padding-left: 10%;
                padding-right: 10%;
                border: none;
                border-radius: 4px;
                color: #fff;
                background-color: #2ECC71;
                cursor: pointer;
            }
        </style>
    </head>
    <body bgcolor='#fddbff'>
        <h1 align="center">Welcome To Luan's MovieWebApp!</h1>
        <div class="container">
            <form action=<%=request.getContextPath() + "/Login"%> method="POST">
                <div class="form-input">
                    <input type="text" name="email" placeholder="Enter Email">
                </div>

                <div class="form-input">
                    <input type="password" name="password" placeholder="Enter Password">
                </div>
                <input type="submit" value="Login" class="btn-login">
            </form>
        </div>
    </body>
</html>
