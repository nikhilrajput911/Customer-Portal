<%-- 
    Document   : Registeration
    Created on : Apr 16, 2018, 10:04:37 AM
    Author     : RaphelTudu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="description" content="User login page" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />		

        <link href="css/bootstrap.min.css" rel="stylesheet" />
        <link href="css/bootstrap-responsive.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="css/font-awesome.min.css" />
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />		

        <link rel="stylesheet" href="css/ace.min.css" />
        <link rel="stylesheet" href="css/ace-responsive.min.css" />
        <link rel="stylesheet" href="css/ace-skins.min.css" />
        <link rel="stylesheet" href="css/main.css" />


        <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>-->

        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js" type="text/javascript"></script>
        <!--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>-->
        <!--<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.3/js/bootstrapValidator.min.js" type="text/javascript"></script>-->
        <script src="js/registration-validations.js"></script>
        <title>Registration</title>
    </head>
    <body>
        <div class="container">
            <br>
            <!--            <div class="text-center">
                        <img src="images/logo.gif" alt=""/>
                        <div class="clearfix"></div>
                        </div>-->
            <h2 class="modal-title text-center" style="color:#1fa67b;">Sign Up</h2>
            <hr>
            <form class="form-vertical" action="register.do" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <div class="row-fluid">
                    <div class="span6">
                        <div class="control-group">
                            <label class="control-label" for="firstname">First Name<span style="color: red;"> *</span></label>
                            <div class="controls">
                                <div class="">
                                    <input type="text" name="firstname" id="firstname" class="span10 registration-field" required/>
                                </div>
                            </div>																
                        </div>
                    </div>
                    <div class="span6">
                        <div class="control-group">
                            <label class="control-label" for="lastname">Last Name</label>
                            <div class="controls">
                                <div class="">
                                    <input type="text" value="" id="lastname" name="lastname" class="span10 registration-field"/>

                                </div>
                            </div>
                        </div>

                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span6">
                        <div class="control-group">
                            <label class="control-label" for="username">Username<span style="color: red;"> *</span></label>
                            <div class="controls">
                                <div class="">
                                    <input type="text" name="username" id="username" class="span10 registration-field" required/>
                                </div>
                            </div>																
                        </div>
                    </div>
                    <div class="span6">
                        <div class="control-group">
                            <label class="control-label" for="password">Password<span style="color: red;"> *</span></label>
                            <div class="controls">
                                <div class="">
                                    <input type="password" value="" id="password" name="password" class="span10 registration-field"/>

                                </div>
                            </div>
                        </div>

                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span6">
                        <div class="control-group">
                            <label class="control-label" for="confirmpassword">Confirm Password<span style="color: red;"> *</span></label>
                            <div class="controls">
                                <div class="">
                                    <input type="password" name="confirmpassword" id="confirmpassword" class="span10 registration-field" required/>
                                </div>
                            </div>																
                        </div>
                    </div>
                    <div class="span6">
                        <div class="control-group">
                            <label class="control-label" for="country">Country<span style="color: red;"> *</span></label>
                            <div class="controls">
                                <div class="">
                                    <select class="span10 registration-field" name="country" id="country" required/>
                                    <option>Select a Country</option>
                                    <c:forEach var="userCntry" items="${regLOV.userCntry}">
                                        <option>${userCntry.countryName}</option>
                                    </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span6">
                        <div class="control-group">
                            <label class="control-label" for="usertype">User Type<span style="color: red;"> *</span></label>
                            <div class="controls">
                                <div class="">
                                    <select class="span10 registration-field" name="usertype" id="usertype" required/>
                                    <option>Select User Type</option>
                                    <option>Admin</option>
                                    <option>SCM User</option>
                                    <option>Approver</option>
                                    <option>Manager</option>
                                    </select>
                                </div>
                            </div>																
                        </div>
                    </div>
                    <div class="span6">
                        <div class="control-group">
                            <label class="control-label" for="userstatus">Status<span style="color: red;"> *</span></label>
                            <div class="controls">
                                <div class="">
                                    <select class="span10 registration-field" name="userstatus" id="userstatus" required/>
                                    <option>Select Status</option>
                                    <option>Active</option>
                                    <option>Passive</option>
                                    <option>Disabled</option>
                                    <option>Deactivated</option>
                                    </select>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span6">
                        <div class="control-group">
                            <label class="control-label" for="supervisoremailid">Supervisor Email-Id<span style="color: red;"> *</span></label>
                            <div class="controls">
                                <div class="">
                                    <input type="email" class="span10 registration-field" name="supervisoremailid" id="supervisoremailid" placeholder="Supervisor Email Address" required/>
                                </div>
                            </div>																
                        </div>
                    </div>
                    <div class="span6">
                        <div class="control-group">
                            <label class="control-label" for="workemailid">Work Email-Id<span style="color: red;"> *</span></label>
                            <div class="controls">
                                <div class="">
                                    <input type="email" class="span10 registration-field" name="workemailid" id="workemailid" placeholder="Work Email Address" required/>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span6">
                        <div class="control-group">
                            <label class="control-label" for="contactnumber">Contact No.<span style="color: red;"> *</span></label>
                            <div class="controls">
                                <div class="">
                                    <input type="text" class="span10 registration-field" name="contactnumber" id="contactnumber" placeholder="Contact No" required/>
                                </div>
                            </div>																
                        </div>
                    </div>
                    <div class="span6">
                        <div class="control-group">
                            <label class="control-label" for="userroleSystemaccess">System Access<span style="color: red;"> *</span></label>
                            <div class="controls">
                                <div class="">
                                    <select class="span10 registration-field" name="userroleSystemaccess" id="userroleSystemaccess" required/>
                                    <option>Select System Access</option>
                                    <option>Admin</option>
                                    <option>Super Admin</option>
                                    <option>Local Admin</option>
                                    <option>Manager</option>
                                    </select>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span6">
                        <div class="control-group">
                            <label class="control-label" for="supervisorname">Supervisor Name<span style="color: red;"> *</span></label>
                            <div class="controls">
                                <div class="">
                                    <input type="text" class="span10 registration-field" name="supervisorname" id="supervisorname" placeholder="Supervisor Name" required/>
                                </div>
                            </div>																
                        </div>
                    </div>

                </div>
                <div class="row-fluid">
                    <div class="span12">
                        <div class="control-group">
                            <label class="control-label" for="securityquestion1">Security Question 1<span style="color: red;"> *</span></label>
                            <div class="controls">
                                <div class="">
                                    <select class="span10 registration-field" name="securityquestion1" id="securityquestion1" required/>
                                    <option value="default" disabled selected hidden>Please choose any question...</option>
                                    <c:forEach var="secQue" items="${regLOV.secQues}">
                                        <option>${secQue.securityquestions}</option>
                                    </c:forEach>
                                    </select>
                                </div>
                            </div>																
                        </div>
                    </div>
                </div>
                <div class="row-fluid">

                    <div class="span12">
                        <div class="control-group">
                            <label class="control-label" for="securityanswer1">Security Answer 1<span style="color: red;"> *</span></label>
                            <div class="controls">
                                <div class="">
                                    <input type="text" class="span10 registration-field" name="securityanswer1" id="securityanswer1" placeholder="Answer goes here...." required/>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span12">
                        <div class="control-group">
                            <label class="control-label" for="securityquestion2">Security Question 2<span style="color: red;"> *</span></label>
                            <div class="controls">
                                <div class="">
                                    <select class="span10 registration-field" name="securityquestion2" id="securityquestion2" required/>
                                    <option value="default" disabled selected hidden>Please choose any question...</option>
                                    <c:forEach var="secQue" items="${regLOV.secQues}">
                                        <option>${secQue.securityquestions}</option>
                                    </c:forEach>
                                    </select>
                                </div>
                            </div>																
                        </div>
                    </div>
                </div>
                <div class="row-fluid">

                    <div class="span12">
                        <div class="control-group">
                            <label class="control-label" for="answer2">Security Answer 2<span style="color: red;"> *</span></label>
                            <div class="controls">
                                <div class="">
                                    <input type="text" class="span10 registration-field" name="answer2" id="answer2" placeholder="Answer goes here...." required/>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span12">
                        <div class="control-group">
                            <label class="control-label" for="securityquestion3">Security Question 3<span style="color: red;"> *</span></label>
                            <div class="controls">
                                <div class="">
                                    <select class="span10 registration-field" name="securityquestion3" id="securityquestion3" required/>
                                    <option value="default" disabled selected hidden>Please choose any question...</option>
                                    <c:forEach var="secQue" items="${regLOV.secQues}">
                                        <option>${secQue.securityquestions}</option>
                                    </c:forEach>
                                    </select>
                                </div>
                            </div>																
                        </div>
                    </div>
                </div>
                <div class="row-fluid">

                    <div class="span12">
                        <div class="control-group">
                            <label class="control-label" for="answer3">Security Answer 3<span style="color: red;"> *</span></label>
                            <div class="controls">
                                <div class="">
                                    <input type="text" class="span10 registration-field" name="answer3" id="answer3" placeholder="Answer goes here...." required/>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>

                <hr>

                <!--                <input type="hidden" id="creationdate" name="creationdate"/>
                                <input type="hidden" id="updatedate" name="updatedate"/>-->
                <input type="hidden" id="updatedby" name="updatedby"/>
                <input type="hidden" id="createdby" name="createdby"/>
                <div class="row-fluid">
                    <div class="span6">
                        <div class="control-group">
                            <!--<label class="control-label" for="answer3">Security Answer 3<span style="color: red;"> *</span></label>-->
                            <div class="controls">
                                <div class="">
                                    <div class="text-center">
                                        <button type="Submit" class="btn btn-purple btn-lg btn-block" id="register">Register</button>
                                        
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="span6">
                        <div class="control-group">
                            <!--<label class="control-label" for="answer3">Security Answer 3<span style="color: red;"> *</span></label>-->
                            <div class="controls">
                                <div class="">
                                    <div class="text-center">
                                        <button type="button" class="btn btn-danger btn-lg btn-block" id="back_btn">Back</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>


            </form><br>
        </div>
    </body>
</html>
<script type="text/javascript">
    window.jQuery || document.write("<script src='js/jquery-2.0.3.min.js'>" + "<" + "/script>");
</script>			
<script src="js/bootstrap.min.js"></script> 
<script src="js/jquery-ui-1.10.3.custom.min.js"></script>       
<script src="js/bootbox.min.js"></script>   
<script src="js/jquery.slimscroll.min.js"></script>     
<script src="js/ace-elements.min.js"></script>
<script src="js/ace.min.js"></script>
<script src="js/fuelux/fuelux.wizard.min.js"></script>
