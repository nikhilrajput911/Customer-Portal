
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="JSON.JSONObject, JSON.JSONArray,JSON.JSONException" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>Dashboard</title>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
    <meta name="description" content="User login page" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />		

    <script src="js/jquery.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap-new.min.css" />
    <script src="js/bootstrap-new.min.js"></script>
    
    
<!--    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.3/js/bootstrapValidator.min.js" type="text/javascript"></script>-->
    <!--<script src="js/login.js"></script>-->
    <!--<script src="js/validate.js"></script>-->
    <link rel="stylesheet" href="css/login.css">
    <script src="js/customer.js" type="text/javascript"></script>
    <style>
    </style>
    <script>
        $(document).ready(function() {


            $("#backtologin").click(function() {
//                alert("s");
                location.href = "customerlogin.do";
            });
        });
    </script>

</head>

<body>

    <div class="page-content">
        <br>
        <div class="panel panel-shadow">
            <div class="panel-body">
                <section id="login">
                    <div class="form-wrap">
                        <h2>Update Password</h2>
                        <form method="post" action="updatecustomerpassword.do" role="form" id="update-password-form">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                            <input type="hidden" name="check_constraint" id="check_constraint" value="false">
                            <input type="hidden" name="passconfig" id="passconfig" value="${passconfig}">
                            <div class="form-group">
                                <input name="customer-username" id="customer-username" type="text" class="form-control input-small" placeholder="Username" required/>
                            </div>
                            <div class="form-group">

                                <input name="oldpassword" id="oldpassword" type="password" class="form-control input" placeholder="Old Password" required/>
                            </div>
                            <div class="form-group">

                                <input name="newpassword" id="newpassword" type="password" class="form-control input" placeholder="New Password" required/>
                            </div>
                            <div style="text-align: center">
                                <input type="submit" id="change-password" class="btn btn-primary form-control" style="color: white;" value="Submit">
                            </div>
                            <br>
                            <div style="text-align: center">
                                <input type="button" id="backtologin" class="btn btn-default form-control" value="Back">
                            </div>
                        </form>
                    </div>
                </section>
            </div>
        </div>
    </div>


</body>
</html>
