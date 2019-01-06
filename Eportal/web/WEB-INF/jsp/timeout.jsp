<%-- 
    Document   : timeout
    Created on : 19 Nov, 2018, 3:23:59 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/bootstrap.min.css" rel="stylesheet" />
        <style>
            img {
                display: block;
                margin-left: auto;
                margin-right: auto;
            }
            a {
                display: block;
                margin-left: auto;
                margin-right: auto;
                alignment-adjust: central;
            }
        </style>
    </head>
    <body>
        <!--<h1>session expired!</h1>-->
        <img src="images/Session-expired.jpg" class="center"/>
        <div style="text-align: center;">
            <a href="customerlogin.do" class="btn btn-success">Please Login</a>
        </div>
        
    </body>
</html>
