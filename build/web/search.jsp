<%-- 
    Document   : search
    Created on : Jan 22, 2017, 7:47:24 PM
    Author     : Luan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href=<%=request.getContextPath()+"/css/font-awesome.css"%> rel="stylesheet" type="text/css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body style="background-image: url(sky.png); 
          padding-left: 3%;
          background-repeat: no-repeat;
          background-size: cover;
          background-attachment: fixed;
          background-position: center;">
        <form action=<%=request.getContextPath() + "/Search"%> method="POST">
            <div>
                <h1>Title</h1>
                <input type="text" name="title" placeholder="e.g. Titanic">
            </div>

            <div>
                <h1>Year Released</h1>
                <input type="text" name="yearFrom" placeholder="e.g. 1994">
                &nbsp; to &nbsp;
                <input type="text" name="yearTo" placeholder="e.g. 2017">
            </div>

            <div>
                <h1>Director</h1>
                <input type="text" name="director" placeholder="e.g. Joss Whedon">
            </div>

            <div>
                <h1>Star</h1>
                <input type="text" name="star" placeholder="e.g. Tom Cruise or Cruise">
            </div>
            <br>
            <br>
            <button style="background-color: #2ECC71; 
                    border-radius: 6px; 
                    width: 100px; height: 40px;">
                <i class="fa fa-search" aria-hidden="true"></i> Search
            </button>
        </form>    
    </body>
</html>
