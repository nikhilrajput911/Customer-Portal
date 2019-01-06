
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="JSON.JSONObject, JSON.JSONArray,JSON.JSONException" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>Dashboard</title>


        <meta name="description" content="overview &amp; stats" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />		

        <link href="css/bootstrap.min.css" rel="stylesheet" />
        <link href="css/bootstrap-responsive.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="css/font-awesome.min.css" />
        <!--<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />-->		

        <link rel="stylesheet" href="css/googleapis-font.css" />
        <link rel="stylesheet" href="css/ace.min.css" />
        <link rel="stylesheet" href="css/ace-responsive.min.css" />
        <link rel="stylesheet" href="css/ace-skins.min.css" />
        <link rel="stylesheet" href="css/main.css" />

        <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>-->
        <!--<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>-->
        <!--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">-->

        <!--<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet" />-->
        <!--<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>-->
        <script src="js/jquery.min.js"></script>
        <link rel="stylesheet" href="css/select2.min.css" />
        <script src="js/select2.min.js"></script>

        <link rel="stylesheet" href="css/customer-notification.css" />
        <script src="js/customer-notification.js"></script>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

        <script src="js/customer.js"></script>
        <script src="js/customer-validation.js"></script>

        <style>
            .select2-container .select2-search--inline .select2-search__field
            {
                margin-right: 20px !important;
                /*position: fixed !important;*/
            }
            .select2-search:after {
                position: fixed;
            }
        </style>
        <script>
            $(document).ready(function() {
                var set_image = $("#set_image").prop("src").toString().split(",");
                if (set_image[1] === "NotFound")
                {
                    $("#default_image_a").css("display", "block");
                    $("#set_image_a").css("display", "none");
                }

                $('.select-multiple-box').select2({
                    placeholder: "Select Project(s)"
                });
                $('#avail_document_multiple').select2({
                    placeholder: "Select Document(s)"
                });
//                $('#projectId').multiselect();
            });
            function onLoadFunc()
            {
                var param = window.location.search.substring(1).toString();
                var id = param.split("=")[1];
                console.log("id: " + id);

                var userRole = $("#loggedInUserRole").val();
                var userId = $("#loggedInUserId").val();

                console.log("userRole: " + userRole);
                console.log("userId " + userId);
                if (id === userId)
                {
                    $("#delete_edituserbtn").prop("disabled", true);
                }
                else
                {
                    $("#delete_edituserbtn").prop("disabled", false);
                }
            }
        </script>
    </head>

    <body onload="onLoadFunc();">

        <%@include file = "templatecustomer.jsp" %>
        <div class="breadcrumbs" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <a href="manageuser.do"><span class="menu-text"> Manage User</span></a>
                    <span class="divider"><i class="icon-angle-right arrow-icon"></i></span>
                </li>
                <li class="active">Edit User</li>						
            </ul>
            <div class="nav-search" id="nav-search">
                <a href="redirectcustomerhome.do"><h5><b>Go to Home</b></h5></a>
            </div>
        </div>
        <div class="page-content">
            <h5 class="center"><span style="color: green;">${message}</span></h5>

            <div class="space"></div>
            <div id="addUserFormDiv" class="collapse in">
                <div class="panel panel-primary panel-shadow">
                    <div class="panel-heading">
                        User Details
                    </div>
                    <div class="panel-body">
                        <div class="row-fluid">
                            <div class="span12">
                                <form class="form-vertical" action="updatecustomeruser.do" method="post" enctype="" id="update_user_form">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                    <input type="hidden" name="uid" id="uid">
                                    <input type="hidden" id="projectIds" name="projectIds" value="">
                                    <input type="hidden" id="documentIds" name="documentIds" value="">
                                    <input type="hidden" id="passconfig" name="passconfig" value="${passconfig}">
                                    <input type="hidden" id="loggedInUserRole" value="${userRole}">
                                    <input type="hidden" id="loggedInUserId" value="${userId}">

                                    <div class="row-fluid">
                                        <div class="span6">
                                            <div class="control-group">
                                                <label class="control-label" for="customerId">Customer Name</label>
                                                <div class="controls">
                                                    <div class="">
                                                        <input type="text" value="${custUser.bpaasCustomerseededCid.customername}" name="custname" id="custname" class="span10" readonly/>


                                                    </div>
                                                </div>																
                                            </div>
                                        </div>


                                    </div>
                                    <div class="row-fluid">
                                        <div class="span6">
                                            <div class="control-group">
                                                <label class="control-label" for="firstname">First Name</label>
                                                <div class="controls">
                                                    <div class="">
                                                        <input type="text" value="${custUser.fisrtname}" name="firstname" id="firstname" class="span10" readonly/>
                                                    </div>
                                                </div>																
                                            </div>
                                        </div>
                                        <div class="span6">
                                            <div class="control-group">
                                                <label class="control-label" for="lastname">Last Name</label>
                                                <div class="controls">
                                                    <div class="">
                                                        <input type="text" value="${custUser.lastname}" name="lastname" id="lastname" class="span10" readonly/>
                                                    </div>
                                                </div>																
                                            </div>
                                        </div>

                                    </div>
                                    <div class="row-fluid">
                                        <div class="span6">
                                            <div class="control-group">
                                                <label class="control-label" for="custaddline1">Address Line1</label>
                                                <div class="controls">
                                                    <div class="">
                                                        <!--<input type="text" name="pr" id="pr" class="span10" required/>-->
                                                        <textarea id="custaddline1" name="custaddline1" rows="4" class="span10" readonly style="background-color: #f2f2f2;">${custUser.addressline1}</textarea>
                                                    </div>
                                                </div>																
                                            </div>
                                        </div>
                                        <div class="span6">
                                            <div class="control-group">
                                                <label class="control-label" for="">Address Line2</label>
                                                <div class="controls">
                                                    <div class="">
                                                        <!--<input type="text" value="" name="prd" id="prd" class="span10" required/>-->
                                                        <textarea id="custaddline2" name="custaddline2" rows="4" class="span10" readonly style="background-color: #f2f2f2;">${custUser.addressline2}</textarea>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                    <div class="row-fluid">
                                        <div class="span6">
                                            <div class="control-group">
                                                <label class="control-label" for="">Address Line3</label>
                                                <div class="controls">
                                                    <div class="">
                                                        <!--<input type="text" value="" name="prd" id="prd" class="span10" required/>-->
                                                        <textarea id="custaddline3" name="custaddline3" rows="4" class="span10" readonly style="background-color: #f2f2f2;">${custUser.addressline3}</textarea>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>
                                        <div class="span6">
                                            <div class="control-group">
                                                <label class="control-label" for="emailid">Email-Id</label>
                                                <div class="controls">
                                                    <div class="">
                                                        <input type="text" value="${custUser.emailid}" name="emailid" id="emailid" class="span10" readonly/>
                                                    </div>
                                                </div>																
                                            </div>
                                        </div>

                                    </div>


                                    <div class="row-fluid">
                                        <div class="span6">
                                            <div class="control-group">
                                                <label class="control-label" for="personalfirstname">Personal First Name</label>
                                                <div class="controls">
                                                    <div class="">
                                                        <input type="text" value="${custUser.personalfirstname}" name="personalfirstname" id="personalfirstname" class="span10" readonly="true"/>
                                                    </div>
                                                </div>																
                                            </div>
                                        </div>
                                        <div class="span6">
                                            <div class="control-group">
                                                <label class="control-label" for="personallastname">Personal Last Name</label>
                                                <div class="controls">
                                                    <div class="">
                                                        <input type="text" value="${custUser.personallastname}" name="personallastname" id="personallastname" class="span10 form-field" readonly="true"/>
                                                    </div>
                                                </div>																
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row-fluid">
                                        <div class="span6">
                                            <div class="control-group">
                                                <label class="control-label" for="personalemailid">Personal Email Id</label>
                                                <div class="controls">
                                                    <div class="">
                                                        <input type="text" value="${custUser.personalemailid}" name="personalemailid" id="personalemailid" class="span10" readonly="true"/>
                                                    </div>
                                                </div>																
                                            </div>
                                        </div>
                                        <div class="span6">
                                            <div class="control-group">
                                                <label class="control-label" for="personalcontactnumber">Personal Contact Number</label>
                                                <div class="controls">
                                                    <div class="">
                                                        <input type="text" value="${custUser.personalcontactnumber}" name="personalcontactnumber" id="personalcontactnumber" class="span10" readonly="true"/>
                                                    </div>
                                                </div>																
                                            </div>
                                        </div>
                                    </div>                

                                    <div class="row-fluid">
                                        <div class="span6">
                                            <div class="control-group">
                                                <label class="control-label" for="username">Username</label>
                                                <div class="controls">
                                                    <div class="">
                                                        <input type="text" value="${custUser.username}" name="username" id="username" class="span10" required readonly="true"/>
                                                    </div>
                                                </div>																
                                            </div>
                                        </div>

                                        <div class="span6">
                                            <div class="control-group">
                                                <label class="control-label" for="password">Password</label>
                                                <div class="controls">
                                                    <div class="">
                                                        <input type="password" value="" name="password" id="pass_word" class="span10" required readonly="true"/>
                                                        <a id="reset_password_link" style="display: none;">Reset Password</a>
                                                    </div>
                                                </div>																
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row-fluid">
                                        <div class="span6">
                                            <div class="control-group">
                                                <label class="control-label" for="confirmpassword">Confirm Password</label>
                                                <div class="controls">
                                                    <div class="">
                                                        <input type="password" value="" name="confirmpassword" id="confirm_password" class="span10" required readonly="true"/>
                                                    </div>
                                                </div>																
                                            </div>
                                        </div>
                                        <div class="span6">
                                            <div class="control-group">
                                                <label class="control-label" for="confirmpassword">Status</label>
                                                <div class="controls">
                                                    <div class="">
                                                        <input type="text" value="${custUser.status}" name="ro_status" id="ro_status" class="span10" required readonly/>
                                                        <select id="status" name="status" class="span10" style="display: none;">
                                                            <option>Active</option>
                                                            <option>Block</option>
                                                        </select>
                                                    </div>
                                                </div>																
                                            </div>
                                        </div>            
                                    </div>
                                    <div class="row-fluid">
                                        <div class="span6" id="document_right_div">
                                            <div class="control-group">
                                                <label class="control-label" for="document_right">Documents</label>
                                                <div class="controls">
                                                    <div class="">
                                                        <select id="document_right" size="5" name="document_right" class="span10">
                                                            <c:forEach var="groups" items="${groupsList}" varStatus="status">
                                                                <option value="${groups.gid}">${groups.groupname}</option>
                                                            </c:forEach>
                                                        </select>
                                                        <div id="documentIddiv" style="display: hide;">
                                                            <select id="avail_document_multiple" name="documentId[]" multiple="multiple" class="span11 subuser-form-field select-multiple-box" disabled="true">
                                                                <c:forEach var="group" items="${groupTotalList}" varStatus="status">
                                                                    <option value="${group.gid}">${group.groupname}</option>

                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>																
                                            </div>
                                        </div>
                                        <div class="span6">
                                            <div class="control-group">
                                                <label class="control-label" for="firstname">Project Name</label>
                                                <div class="controls">
                                                    <div class="">
                                                        <!--<input type="text" value="${custUser.getCustomerProjectMappingCollection()}" name="projectname" id="projectname" class="span10" readonly/>-->

                                                        <select id="ro_projectname" name="" size="5" class="span10 subuser-form-field" required>
                                                            <c:forEach var="mapping" items="${projectMappingList}" varStatus="status">
                                                                <option value="${mapping.bpaasProjectseededPid.pid}">${mapping.bpaasProjectseededPid.projectname}</option>
                                                            </c:forEach>
                                                        </select>
                                                        <div id="projectIddiv" style="display: hide;">
                                                            <select id="projectIdMultiple" name="projectId[]" multiple="multiple" class="span10 subuser-form-field select-multiple-box" required disabled="true">
                                                                <c:forEach var="project" items="${projectList}" varStatus="status">
                                                                    <option value="${project.pid}">${project.projectname}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>																
                                            </div>
                                        </div>
                                    </div>

                                    <div class="space"></div>
                                    <div class="row-fluid align-center">
                                        <div class="span12">
                                            <input type="button" value="Remove Document" id="remove_document_btn" class="btn btn-yellow">
                                            <input type="button" value="Edit User" id="edit_edituserbtn" class="btn btn-info">
                                            <input type="button" value="Update User" id="update_edituserbtn" class="btn btn-purple" style="display: none;">
                                            <!--<input type="button" value="Block User" id="block_edituserbtn" class="btn btn-pink">-->
                                            <%                    if (session.getAttribute("userRole").equals("Admin")) {
                                            %>    
                                            <input type="button" value="Delete User" id="delete_edituserbtn" class="btn btn-success">
                                            <%
                                                }
                                            %>
                                            <input type="button" value="Remove Project" id="remove_project_btn" class="btn btn-pink">

                                            <input type="button" value="Close" id="close_edituserbtn" class="btn btn-danger">
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


    </div><!--/.main-content-->
</div><!--/.main-container-->        

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

<script type="text/javascript">
    $(function() {
        $('.dialogs,.comments').slimScroll({
            height: '200px'
        });

    });
</script>
</body>
</html>
