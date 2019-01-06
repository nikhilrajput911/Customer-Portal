
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

        <!--        <link rel="stylesheet" href="css/bootstrap-multiselect/bootstrap-multiselect.css" type="text/css">
                <script type="text/javascript" src="js/bootstrap-multiselect/bootstrap-multiselect.js"></script>-->
        <!--<link rel="stylesheet" href="https://cdn.datatables.net/1.10.18/css/jquery.dataTables.min.css" type="text/css"/>-->

        <!--<script src="https://cdn.datatables.net/1.10.18/js/jquery.dataTables.min.js" type="text/javascript"></script>-->
        <script src="js/jquery.min.js"></script>
        <link rel="stylesheet" href="css/dataTables1.10.18.min.css" />
        <link rel="stylesheet" href="css/buttons.dataTables.min.css" type="text/css"/>

        <script src="js/dataTables1.10.18.min.js"></script>

        <script src="js/dataTables.buttons.min.js" type="text/javascript"></script>
        <script src="js/buttons.flash.min.js" type="text/javascript"></script>
        <script src="js/jszip.min.js" type="text/javascript"></script>
        <script src="js/pdfmake.min.js" type="text/javascript"></script>
        <script src="js/vfs_fonts.js" type="text/javascript"></script>
        <script src="js/buttons.html5.min.js" type="text/javascript"></script>
        <script src="js/buttons.print.min.js" type="text/javascript"></script>

        <link rel="stylesheet" href="css/font-awesome2.min.css" />

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
//                $('.select-multiple-box').select2({
//                    placeholder: "Select Project(s)"
//                });
                $("#manageprojectsbtn").click(function() {
                    location.href = "customermanageprojects.do";
                });

                var set_image = $("#set_image").prop("src").toString().split(",");
                if (set_image[1] === "NotFound")
                {
                    $("#default_image_a").css("display", "block");
                    $("#set_image_a").css("display", "none");
                }

                $('.select-multiple-box').select2({
                    placeholder: "Select"
                });

//                $('#projectId').multiselect();
                $("#avail_project_multiple").change(function() {
                    console.log($(this).val());
                    $("#projectIds").val($(this).val());
                });
                $("#avail_document_multiple").change(function() {
                    console.log($(this).val());
                    $("#documentIds").val($(this).val());
                });


                var table = $('#usersListTable').DataTable({
                    dom: 'Bfrtip',
                    buttons: [
                        'csv', 'excel', 'print'
                    ]
                });
                table.rows().every(function(rowIdx, tableLoop, rowLoop) {
                    var data = this.data();
                    var creation_date = data[6];
//                    console.log("creation_date: " + creation_date);
                    if (creation_date !== "")
                    {
                        creation_date = convertDate(creation_date);
                        
                        data[6] = creation_date //append a string to every col #2
                        this.data(data);
                    }
                });
            });
            function convertDate(d) {
                var parts = d.split(" ");
                var months = {Jan: "01", Feb: "02", Mar: "03", Apr: "04", May: "05", Jun: "06", Jul: "07", Aug: "08", Sep: "09", Oct: "10", Nov: "11", Dec: "12"};
                return parts[2] + "-" + months[parts[1]] + "-" + parts[5];
            }
            
        </script>

    </head>

    <body>

        <%@include file = "templatecustomer.jsp" %>
        <div class="breadcrumbs" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <h5><b>Manage User</b></h5>
                </li>
                <!--<li class="active">Manage User</li>-->						
            </ul>
            <div class="nav-search" id="nav-search">
                <a href="redirectcustomerhome.do"><h5><b>Go to Home</b></h5></a>
            </div>
        </div>
        <div class="page-content">
            <h5 class="center"><span style="color: green;">${message}</span></h5>
                <%                if (session.getAttribute("userRole").equals("Admin")) {
                %>
            <div class="row-fluid">
                <div class="span3">
                    <input type="button" value="Add User" class="btn btn-purple" data-toggle="collapse" data-target="#addUserFormDiv">
                    <input type="button" value="Manage Projects" class="btn btn-pink" id="manageprojectsbtn">
                </div>
            </div>
            <%
                }
            %>
            <div class="space"></div>
            <div id="addUserFormDiv" class="collapse">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        Enter User Details
                    </div>
                    <div class="panel-body">
                        <div class="row-fluid">
                            <div class="span12">
                                <form class="form-vertical" id="add_user_form" action="addcustomersubuser.do" method="post" enctype="">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                    <input type="hidden" id="projectIds" name="projectIds" value="">
                                    <input type="hidden" id="documentIds" name="documentIds" value="">
                                    <input type="hidden" id="passconfig" name="passconfig" value="${passconfig}">
                                    <div class="row-fluid">
                                        <div class="span6">
                                            <div class="control-group">
                                                <label class="control-label" for="customerId">Customer Name</label>
                                                <div class="controls">
                                                    <div class="">
                                                        <select id="customerIds" name="customerId" class="span10 subuser-form-field" required>
                                                            <option>--Select--</option>
                                                            <c:forEach var="customer" items="${customerList}" varStatus="status">
                                                                <option value="${customer.cid}">${customer.customername}</option>
                                                            </c:forEach>
                                                        </select>

                                                    </div>
                                                </div>																
                                            </div>
                                        </div>
                                        <div class="span6">
                                            <div class="control-group">
                                                <label class="control-label" for="projectId">Project Name</label>
                                                <div class="controls">
                                                    <div class="">
                                                        <select id="avail_project_multiple" name="projectId[]" multiple="multiple" class="span11 subuser-form-field select-multiple-box">

                                                        </select>
                                                    </div>
                                                </div>																
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row-fluid">
                                        <div class="span6">
                                            <div class="control-group">
                                                <label class="control-label" for="projectId">Document</label>
                                                <div class="controls">
                                                    <div class="">
                                                        <select id="avail_document_multiple" name="documentId[]" multiple="multiple" class="span11 subuser-form-field select-multiple-box">
                                                            <c:forEach var="group" items="${groupList}" varStatus="status">
                                                                <option value="${group.gid}">${group.groupname}</option>

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
                                                <label class="control-label" for="firstname">First Name</label>
                                                <div class="controls">
                                                    <div class="">
                                                        <input type="text" name="fisrtname" id="fisrtname" class="span10 subuser-form-field" readonly="true"/>
                                                    </div>
                                                </div>																
                                            </div>
                                        </div>
                                        <div class="span6">
                                            <div class="control-group">
                                                <label class="control-label" for="lastname">Last Name</label>
                                                <div class="controls">
                                                    <div class="">
                                                        <input type="text" name="lastname" id="lastname" class="span10 form-field subuser-form-field" readonly="true"/>
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
                                                        <textarea id="custaddline1" name="custaddline1" rows="4" class="span10" readonly="true" style="background-color: #f2f2f2;"></textarea>
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
                                                        <textarea id="custaddline2" name="custaddline2" rows="4" class="span10" readonly="true" style="background-color: #f2f2f2;"></textarea>
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
                                                        <textarea id="custaddline3" name="custaddline3" rows="4" class="span10" readonly="true" style="background-color: #f2f2f2;"></textarea>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>
                                        <div class="span6">
                                            <div class="control-group">
                                                <label class="control-label" for="emailid">Email-Id</label>
                                                <div class="controls">
                                                    <div class="">
                                                        <input type="text" name="emailid" id="emailid" class="span10 form-field subuser-form-field" readonly="true"/>
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
                                                        <input type="text" name="personalfirstname" id="personalfirstname" class="span10 subuser-form-field"/>
                                                    </div>
                                                </div>																
                                            </div>
                                        </div>
                                        <div class="span6">
                                            <div class="control-group">
                                                <label class="control-label" for="personallastname">Personal Last Name</label>
                                                <div class="controls">
                                                    <div class="">
                                                        <input type="text" name="personallastname" id="personallastname" class="span10 form-field subuser-form-field"/>
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
                                                        <input type="text" name="personalemailid" id="personalemailid" class="span10 subuser-form-field"/>
                                                    </div>
                                                </div>																
                                            </div>
                                        </div>
                                        <div class="span6">
                                            <div class="control-group">
                                                <label class="control-label" for="personalcontactnumber">Personal Contact Number</label>
                                                <div class="controls">
                                                    <div class="">
                                                        <input type="text" name="personalcontactnumber" id="personalcontactnumber" class="span10 form-field subuser-form-field"/>
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
                                                        <input type="text" name="username" id="username" class="span10 form-field subuser-form-field" required/>
                                                    </div>
                                                </div>																
                                            </div>
                                        </div>
                                        <div class="span6">
                                            <div class="control-group">
                                                <label class="control-label" for="password">Password</label>
                                                <div class="controls">
                                                    <div class="">
                                                        <input type="password" value="natsteel351" title="Default Password is natsteel351" name="password" id="pass_word" class="span10 form-field subuser-form-field" required readonly/>
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
                                                        <input type="password" value="natsteel351" title="Default Password is natsteel351" name="confirmpassword" id="confirm_password" class="span10 form-field subuser-form-field" required readonly/>
                                                    </div>
                                                </div>																
                                            </div>
                                        </div>
                                    </div>
                                    <!--                                    <div class="row-fluid">
                                                                            <div class="span6">
                                                                                <div class="control-group">
                                                                                    <label class="control-label" for="mobileno">Mobile No.</label>
                                                                                    <div class="controls">
                                                                                        <div class="">
                                                                                            <input type="text" name="phonenumber" id="mobileno" class="span10 form-field subuser-form-field" required/>
                                                                                        </div>
                                                                                    </div>																
                                                                                </div>
                                                                            </div>
                                                                            <div class="span6">
                                                                                <div class="control-group">
                                                                                    <label class="control-label" for="country">Country</label>
                                                                                    <div class="controls">
                                                                                        <div class="">
                                                                                            <input type="text" name="country" id="country" class="span10 form-field subuser-form-field" required/>
                                                                                        </div>
                                                                                    </div>																
                                                                                </div>
                                                                            </div>
                                                                        </div>-->
                                    <div class="space"></div>
                                    <div class="row-fluid align-center">
                                        <div class="span12">
                                            <input type="button" value="Add" id="add_user_btn" class="btn btn-pink">
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="passwordPolicyDiv" class="collapse">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        Password Policy Details
                    </div>
                    <div class="panel-body">

                    </div>
                </div>
            </div>
            <div class="space"></div>
            <div class="row-fluid table-responsive">
                <div class="span12">
                    <table id="usersListTable" class="table table-striped table-bordered table-hover">
                        <thead>
                        <th>S.No.</th>
                        <th>Username</th>
                        <th>Company</th>
                        <th>Personal Name</th>
                        <th>User Type</th>
                        <th>Status</th>
                        <th>Creation Date</th>
                        <th>Created by</th>
                        <th></th>
                        </thead>
                        <tbody>
                            <c:forEach var="user" items="${subuser}" varStatus="status">
                                <tr>
                                    <td>${status.count}</td>
                                    <td>${user.username}</td>
                                    <td>${user.bpaasCustomerseededCid.customername}</td>
                                    <td>${user.personalfirstname} ${user.personallastname}</td>
                                    <!--<td>${userType[status.index]}</td>-->
                                    <td>${user.usertype}</td>
                                    <td>${user.status}</td>
                                    <td>${user.creationdate}</td>
                                    <td>${user.createdby}</td>
                                    <td class="center"><a title="Edit User" href="customeredituser.do?uid=${user.id}"><i class="fa fa-pencil-square-o fa-2x"></i></a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                        <!--                        <tfoot>
                                                    <th>S.No.</th>
                                                    <th>Username</th>
                                                    <th>Name</th>
                                                    <th>Creation Date</th>
                                                    <th>Created by</th>
                                                    <th>Edit</th>
                                                </tfoot>-->
                    </table>
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
