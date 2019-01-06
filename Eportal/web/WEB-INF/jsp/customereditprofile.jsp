
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

        <link rel="stylesheet" href="css/loader.css" />

        <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>-->
        <!--        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
        
                <link rel="stylesheet" href="https://cdn.datatables.net/1.10.18/css/jquery.dataTables.min.css" type="text/css"/>
        
                <script src="https://cdn.datatables.net/1.10.18/js/jquery.dataTables.min.js" type="text/javascript"></script>-->
        <script src="js/jquery.min.js"></script>
        <link rel="stylesheet" href="css/dataTables1.10.18.min.css" />
        <script src="js/dataTables1.10.18.min.js"></script>


        <link rel="stylesheet" href="css/customer-notification.css" />
        <script src="js/customer-notification.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

        <script src="js/customer.js"></script>

        <style>
        </style>
        <script>
            $(document).ready(function() {
                
                var role = $("#userRole").val();
                
                if(role !== "Admin" && role !== "Company Admin")
                {
                    $("#firstname").prop("readonly", true);
                    $("#lastname").prop("readonly", true);
                    $("#addressline1").prop("readonly", true);
                    $("#addressline2").prop("readonly", true);
                    $("#addressline3").prop("readonly", true);
                    $("#emailid").prop("readonly", true);
                }
                
                $("#gotohomelink").click(function() {
//                    alert("home");
                    var personalFname = $("#personalfirstname").val();
                    var personalLname = $("#personallastname").val();
                    var personalEmail = $("#personalemailid").val();
                    var personalNumber = $("#personalcontactnumber").val();
                    
                    if (personalFname === "")
                    {
                        bootbox.dialog("Please update personal first name!", [{"label": "ok", "class": "btn-small btn-primary"}]);
                        return false;
                    }
                    if (personalLname === "")
                    {
                        bootbox.dialog("Please update personal last name!", [{"label": "ok", "class": "btn-small btn-primary"}]);
                        return false;
                    }
                    if (personalEmail === "")
                    {
                        bootbox.dialog("Please update personal email id!", [{"label": "ok", "class": "btn-small btn-primary"}]);
                        return false;
                    }
                    if (personalNumber === "")
                    {
                        bootbox.dialog("Please update personal contact number!", [{"label": "ok", "class": "btn-small btn-primary"}]);
                        return false;
                    }
                    
                    var secQue1 = $("#secQue1").val();
                    var secQue2 = $("#secQue2").val();
                    var secQue3 = $("#secQue3").val();
                    var secAns1 = $("#secAns1").val();
                    var secAns2 = $("#secAns2").val();
                    var secAns3 = $("#secAns3").val();
                    
                    if (secQue1 === "--Select--")
                    {
                        bootbox.dialog("Please update security questions!", [{"label": "ok", "class": "btn-small btn-primary"}]);
                        return false;
                    }
                    
                    if (secQue2 === "--Select--")
                    {
                        bootbox.dialog("Please update security questions!", [{"label": "ok", "class": "btn-small btn-primary"}]);
                        return false;
                    }
                    
                    if (secQue3 === "--Select--")
                    {
                        bootbox.dialog("Please update security questions!", [{"label": "ok", "class": "btn-small btn-primary"}]);
                        return false;
                    }
                    
                    if (secAns1 === "")
                    {
                        bootbox.dialog("Please update security answers!", [{"label": "ok", "class": "btn-small btn-primary"}]);
                        return false;
                    }
                    
                    if (secAns2 === "")
                    {
                        bootbox.dialog("Please update security answers!", [{"label": "ok", "class": "btn-small btn-primary"}]);
                        return false;
                    }
                    
                    if (secAns3 === "")
                    {
                        bootbox.dialog("Please update security answers!", [{"label": "ok", "class": "btn-small btn-primary"}]);
                        return false;
                    }
                    
                    var isPersonalInfoUpdated = $("#isPersonalInfoUpdated").val();
                    
                    if(isPersonalInfoUpdated !== "Yes")
                    {
                        bootbox.dialog("Please update details!", [{"label": "ok", "class": "btn-small btn-primary"}]);
                        return false;
                    }
                });
//                $("#overlay").css("display", "none");
                var set_image = $("#set_image").prop("src").toString().split(",");
                if (set_image[1] === "NotFound")
                {
                    $("#default_image_a").css("display", "block");
                    $("#set_image_a").css("display", "none");
                }

                $("#emailid").after("<div id='error' style='color:red;'></div>");
                $("#emailid").keyup(function() {
                    var val = $(this).val();
                    var regex = new RegExp('^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$');

                    if (regex.test(val))
                    {
                        $(this).css("border-color", "");
                        $(this).siblings().html('');
//                        $("#check_constraint").val('true');
                        $("#submitCompanyProfile").prop("disabled", false);
                    }
                    else
                    {
                        $(this).css("border-color", "red");
                        $(this).siblings().html('Please enter valid email address!');
//                        $("#check_constraint").val('false');
                        $("#submitCompanyProfile").prop("disabled", true);
                    }
                });

                $("#profilepicture").change(function() {
//                    var img = new Image();
//                    alert(this.files[0].size);

                    var reader = new FileReader();
                    reader.readAsDataURL(this.files[0]);

                    reader.onload = function(e) {
                        //Initiate the JavaScript Image object.
                        var image = new Image();

                        //Set the Base64 string return from FileReader as source.
                        image.src = e.target.result;

                        //Validate the File Height and Width.
                        image.onload = function() {
                            var height = this.height;
                            var width = this.width;
                            console.log("height: " + height);
                            console.log("width " + width);

                            if (height > 50 || width > 50) {
                                alert("Height must not be greater then 50px and width must not be greater then 50px.");
                                $("#submitCompanyProfile").prop("disabled", true);
                                return false;
                            }
//                            alert("Uploaded image has valid Height and Width.");
                            $("#submitCompanyProfile").prop("disabled", false);
                            return true;
                        };

                    }
                });
                $(".question-duplicate-entry").change(function() {
//                    var que = $(this).val();
//                    console.log("que: " + que);
                    var quesArray = [];
                    if ($(this).val() !== "--Select--")
                    {
                        $(".question-duplicate-entry").each(function() {
                            var que = $(this).val();
                            console.log("que: " + que);

                            if (que !== "--Select--")
                            {
                                quesArray.push(que);

                                var result = hasDuplicate(quesArray);
                                console.log("result: " + result);

                                if (result === "Found")
                                {
                                    $("#submitCompanyProfile").prop("disabled", true);
                                    bootbox.dialog("Please select unique questions!", [{"label": "ok", "class": "btn-small btn-primary"}]);
                                    return false;
                                }
                                else
                                {
                                    $("#submitCompanyProfile").prop("disabled", false);
                                }
                            }
                        });
                    }
                });
            });
            function hasDuplicate(array)
            {
//        console.log("in function: " + array);
                var valuesSoFar = [];
                var status = "NotFound";
                for (var i = 0; i < array.length; i++)
                {
//            console.log("index: " + valuesSoFar.indexOf(array[i]));
//            console.log("valuesSoFar: " + valuesSoFar);
                    if (valuesSoFar.indexOf(array[i]) !== -1)
                    {
                        status = "Found";
                    }
                    valuesSoFar.push(array[i]);
                }
//        console.log("valuesSoFar: " + valuesSoFar);
                return status;
            }
        </script>
    </head>

    <body onload="setSecurityQue();">

        <%@include file = "templatecustomer.jsp" %>

        <!--        <div id="overlay">
                    <div id="loader"></div>
                </div>-->

        <div class="breadcrumbs" id="breadcrumbs">
            <ul class="breadcrumb">
                <li class="align-center">
                    <h4>Edit Company Profile</h4>

                </li>
                <!--<li class="active">Response Comment</li>-->						
            </ul>
            <div class="nav-search" id="nav-search">
                <a href="redirectcustomerhome.do" id="gotohomelink"><h5><b>Go to Home</b></h5></a>
            </div>
        </div>
        <div class="page-content">
            <h5 class="center"><span style="color: green;">${message}</span></h5>
            <div class="panel panel-shadow">
                <form action="updatecustomerprofile.do" method="post" class="form-vertical" enctype="multipart/form-data">
                    <div class="panel-body">

                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        <input type="hidden" name="cid" value="${map.customer.id}">
                        <input type="hidden" id="userRole" value="${userRole}">    
                        <input type="hidden" id="isPersonalInfoUpdated" value="${map.customer.isPersonalInfoUpdated}">    
                        <div class="row-fluid">
                            <div class="span6">
                                <div class="control-group">
                                    <label class="control-label" for="firstname">First Name</label>
                                    <div class="controls">
                                        <div class="">
                                            <input type="text" name="firstname" id="firstname" class="span10 customer-form-field" value="${map.customer.fisrtname}"/>

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="span6">
                                <div class="control-group">
                                    <label class="control-label" for="lastname">Last Name</label>
                                    <div class="controls">
                                        <div class="">
                                            <input type="text" name="lastname" id="lastname" class="span10 customer-form-field" value="${map.customer.lastname}"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row-fluid">
                            <div class="span6">
                                <div class="control-group">
                                    <label class="control-label" for="addressline1">Address Line1</label>
                                    <div class="controls">
                                        <div class="">
                                            <!--<input type="text" name="pr" id="pr" class="span10" required/>-->
                                            <textarea id="addressline1" name="addressline1" rows="4" class="span10 customer-form-field">${map.customer.addressline1}</textarea>
                                        </div>
                                    </div>																
                                </div>
                            </div>
                            <div class="span6">
                                <div class="control-group">
                                    <label class="control-label" for="addressline2">Address Line2</label>
                                    <div class="controls">
                                        <div class="">
                                            <!--<input type="text" value="" name="prd" id="prd" class="span10" required/>-->
                                            <textarea id="addressline2" name="addressline2" rows="4" class="span10 customer-form-field">${map.customer.addressline2}</textarea>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                        <div class="row-fluid">
                            <div class="span6">
                                <div class="control-group">
                                    <label class="control-label" for="addressline3">Address Line3</label>
                                    <div class="controls">
                                        <div class="">
                                            <!--<input type="text" value="" name="prd" id="prd" class="span10" required/>-->
                                            <textarea id="addressline3" name="addressline3" rows="4" class="span10 customer-form-field">${map.customer.addressline3}</textarea>
                                        </div>
                                    </div>
                                </div>

                            </div>
                            <div class="span6">
                                <div class="control-group">
                                    <label class="control-label" for="emailid">Email-Id</label>
                                    <div class="controls">
                                        <div class="">
                                            <input type="text" name="emailid" id="emailid" class="span10 form-field customer-form-field" value="${map.customer.emailid}"/>
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
                                            <input type="text" value="${map.customer.personalfirstname}" name="personalfirstname" id="personalfirstname" class="span10 customer-form-field" readonly="true"/>
                                        </div>
                                    </div>																
                                </div>
                            </div>
                            <div class="span6">
                                <div class="control-group">
                                    <label class="control-label" for="personallastname">Personal Last Name</label>
                                    <div class="controls">
                                        <div class="">
                                            <input type="text" value="${map.customer.personallastname}" name="personallastname" id="personallastname" class="span10 form-field subuser-form-field" readonly="true"/>
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
                                            <input type="text" value="${map.customer.personalemailid}" name="personalemailid" id="personalemailid" class="span10 subuser-form-field"/>
                                        </div>
                                    </div>																
                                </div>
                            </div>
                            <div class="span6">
                                <div class="control-group">
                                    <label class="control-label" for="personalcontactnumber">Personal Contact Number</label>
                                    <div class="controls">
                                        <div class="">
                                            <input type="text" value="${map.customer.personalcontactnumber}" name="personalcontactnumber" id="personalcontactnumber" class="span10 form-field subuser-form-field"/>
                                        </div>
                                    </div>																
                                </div>
                            </div>
                        </div>                
                        <div class="row-fluid">
                            <div class="span6">
                                <div class="control-group">
                                    <label class="control-label" for="secQue1">Security Question 1</label>
                                    <div class="controls">
                                        <div class="">
                                            <select id="secQue1" name="secQuestion" class="span10 form-field question-duplicate-entry">
                                                <option>--Select--</option>
                                                <c:forEach var="secQue" items="${map.secQueList}">
                                                    <option>${secQue.secquestion}</option>
                                                </c:forEach>
                                            </select>
                                            <input type="hidden" name="ro_secQue1" id="ro_secQue1" value="${map.custQueAnsList.size() == 0 ? '' : map.custQueAnsList.get(0).question}"/>
                                        </div>
                                    </div>																
                                </div>
                            </div>
                            <div class="span6">
                                <div class="control-group">
                                    <label class="control-label" for="secAns1">Answer 1</label>
                                    <div class="controls">
                                        <div class="">
                                            <input type="text" name="secAnswer" id="secAns1" value="${map.custQueAnsList.size() == 0 ? '' : map.custQueAnsList.get(0).answer}" class="span10 form-field"/>
                                        </div>
                                    </div>																
                                </div>
                            </div>
                        </div>
                        <div class="row-fluid">
                            <div class="span6">
                                <div class="control-group">
                                    <label class="control-label" for="secQue2">Security Question 2</label>
                                    <div class="controls">
                                        <div class="">
                                            <select id="secQue2" name="secQuestion" class="span10 form-field question-duplicate-entry">
                                                <option>--Select--</option>
                                                <c:forEach var="secQue" items="${map.secQueList}">
                                                    <option>${secQue.secquestion}</option>
                                                </c:forEach>
                                            </select>
                                            <input type="hidden" name="ro_secQue2" id="ro_secQue2" value="${map.custQueAnsList.size() == 0 ? '' : map.custQueAnsList.get(1).question}"/>
                                        </div>
                                    </div>																
                                </div>
                            </div>
                            <div class="span6">
                                <div class="control-group">
                                    <label class="control-label" for="secAns2">Answer 2</label>
                                    <div class="controls">
                                        <div class="">
                                            <input type="text" name="secAnswer" id="secAns2" value="${map.custQueAnsList.size() == 0 ? '' : map.custQueAnsList.get(1).answer}" class="span10 form-field"/>
                                        </div>
                                    </div>																
                                </div>
                            </div>
                        </div>
                        <div class="row-fluid">
                            <div class="span6">
                                <div class="control-group">
                                    <label class="control-label" for="secQue3">Security Question 3</label>
                                    <div class="controls">
                                        <div class="">
                                            <select id="secQue3" name="secQuestion" class="span10 form-field question-duplicate-entry">
                                                <option>--Select--</option>
                                                <c:forEach var="secQue" items="${map.secQueList}">
                                                    <option>${secQue.secquestion}</option>
                                                </c:forEach>
                                            </select>
                                            <input type="hidden" name="ro_secQue3" id="ro_secQue3" value="${map.custQueAnsList.size() == 0 ? '' : map.custQueAnsList.get(2).question}"/>
                                        </div>
                                    </div>																
                                </div>
                            </div>
                            <div class="span6">
                                <div class="control-group">
                                    <label class="control-label" for="secAns3">Answer 3</label>
                                    <div class="controls">
                                        <div class="">
                                            <input type="text" name="secAnswer" id="secAns3" value="${map.custQueAnsList.size() == 0 ? '' : map.custQueAnsList.get(2).answer}" class="span10 form-field"/>
                                        </div>
                                    </div>																
                                </div>
                            </div>
                        </div>                
                        <div class="row-fluid">
                            <div class="span6">

                                <div class="control-group">
                                    <label class="control-label" for="profilepicture">Profile Picture</label>
                                    <div class="controls">
                                        <div class="">
                                            <input type="file" class="customer-form-field" name="profilepicture" id="profilepicture">
                                        </div>
                                    </div>																
                                </div>
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="row-fluid align-center">
                        <!--<input type="button" value="Edit" class="btn btn-purple" id="editCompanyProfile">-->
                        <input type="submit" value="Update" class="btn btn-primary" id="submitCompanyProfile">

                    </div>
                </form>
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

        $('#profilepicture').ace_file_input({
            no_file: 'No File ...',
            btn_choose: 'Choose',
            btn_change: 'Change',
            droppable: false,
            onchange: null,
            thumbnail: false
        });
    });
    function setSecurityQue()
    {
        var que1 = $("#ro_secQue1").val();
        if (que1 !== "")
        {
            $("#secQue1").val(que1);
        }
        var que1 = $("#ro_secQue2").val();
        if (que1 !== "")
        {
            $("#secQue2").val(que1);
        }
        var que1 = $("#ro_secQue3").val();
        if (que1 !== "")
        {
            $("#secQue3").val(que1);
        }

        var userRole = $("#userRole").val();

        if (userRole === "Admin")
        {
            $(".customer-form-field").prop("readonly", true);
        }
    }
</script>
</body>
</html>
