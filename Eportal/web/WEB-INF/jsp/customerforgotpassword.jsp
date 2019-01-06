
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
        <div class="panel panel-shadow">
            <div class="panel-body">
                <section id="login">
                    <div class="form-wrap">
                        <h2>Reset Password</h2>
                        <form method="post" action="" role="form" id="update-password-form">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                            <input type="hidden" name="check_constraint" id="check_constraint" value="false">
                            <input type="hidden" name="passconfig" id="passconfig" value="${passconfig}">
                            <input type="hidden" name="isAdmin" id="isAdmin" value="">
                            <div class="form-group">
                                <input name="customer-username-forgot" id="customer-username-forgot" type="text" class="form-control input-small" placeholder="Username" required/>
                            </div>
                            <div class="form-group">
                                <input name="sec_que1" id="sec_que1" type="text" class="form-control input-small" placeholder="Security Question 1" readonly="true"/>
                                <input name="sec_que_ans1" id="sec_que_ans1" type="text" class="form-control input-small" placeholder="Security Answer 1" readonly="true"/>
                                <input type="hidden" id="verify_sec_que_ans1" value="">
                            </div>
                            <div class="form-group">
                                <input name="sec_que2" id="sec_que2" type="text" class="form-control input-small" placeholder="Security Question 2"  readonly="true"/>
                                <input name="sec_que_ans2" id="sec_que_ans2" type="text" class="form-control input-small" placeholder="Security Answer 2" readonly="true"/>
                                <input type="hidden" id="verify_sec_que_ans2" value="">
                            </div>
                            <div class="form-group">
                                <input name="sec_que3" id="sec_que3" type="text" class="form-control input-small" placeholder="Security Question 3"  readonly="true"/>
                                <input name="sec_que_ans3" id="sec_que_ans3" type="text" class="form-control input-small" placeholder="Security Answer 3" readonly="true"/>
                                <input type="hidden" id="verify_sec_que_ans3" value="">
                            </div>
                            <div style="text-align: center">
                                <input type="button" id="change-password" class="btn btn-primary form-control" style="color: white;" value="Submit" data-toggle="modal" data-target="#forgotPasswordModal">
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
    <div id="modalDiv">
        <div id="forgotPasswordModal" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <div class="modal-body">
                        <form method="post" action="updateforgotpassword.do" role="form" id="reset-password-form">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                            <input type="hidden" name="userid" id="userid" value="">
                            <div class="form-group">
                                <input name="newpassword" id="newpassword" type="password" class="form-control input reset-form-field" placeholder="New Password" required/>
                            </div>
                            <div class="form-group">
                                <input name="confirmnewpassword" id="confirmnewpassword" type="password" class="form-control input reset-form-field" placeholder="Confirm New Password" required/>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" id="reset_btn_id" class="btn btn-primary">Reset</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </div>                        

</body>
</html>
