
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
        <script src="js/jquery.min.js"></script>
        <!--<link rel="stylesheet" href="https://cdn.datatables.net/1.10.18/css/jquery.dataTables.min.css" type="text/css"/>-->

        <!--<script src="https://cdn.datatables.net/1.10.18/js/jquery.dataTables.min.js" type="text/javascript"></script>-->

        <!--<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet" />-->
        <!--<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>-->

        <link rel="stylesheet" href="css/customer-notification.css" />
        <script src="js/customer-notification.js"></script>
        
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

        <script src="js/customer.js"></script>

        <style>
        </style>

        <script>
            $(document).ready(function() {
//                $('.select-multiple-box').select2({
//                    placeholder: "Select Project(s)"
//                });
                var set_image = $("#set_image").prop("src").toString().split(",");
                if(set_image[1] === "NotFound")
                {
                    $("#default_image_a").css("display", "block");
                    $("#set_image_a").css("display", "none");
                }

                $("#save_password_config").click(function() {
                    var minPassLen = $("#minPassLen").val();
                    var minSpecialChar = $("#minSpecialChar").val();
                    var minUpperCaseCharCount = $("#minUpperCaseCharCount").val();
                    var minLowerCaseCharCount = $("#minLowerCaseCharCount").val();
                    var minNumCount = $("#minNumCount").val();

                    console.log(minPassLen);
                    console.log(minSpecialChar);
                    console.log(minUpperCaseCharCount);
                    console.log(minLowerCaseCharCount);
                    console.log(minNumCount);

                    if (minPassLen === "")
                    {
//                        $("#minPassLen").css("border-color", "red");
                        $("#minPassLen").focus();
                        return false;
                    }
                    else
                    {
                        $("#minPassLen").css("border-color", "");
                    }

                    if (minSpecialChar === "")
                    {
//                        $("#minSpecialChar").css("border-color", "red");
                        $("#minSpecialChar").focus();
                        return false;
                    }
                    else
                    {
                        $("#minSpecialChar").css("border-color", "");
                    }

                    if (minUpperCaseCharCount === "")
                    {
//                        $("#minUpperCaseCharCount").css("border-color", "red");
                        $("#minUpperCaseCharCount").focus();
                        return false;
                    }
                    else
                    {
                        $("#minUpperCaseCharCount").css("border-color", "");
                    }

                    if (minLowerCaseCharCount === "")
                    {
//                        $("#minLowerCaseCharCount").css("border-color", "red");
                        $("#minLowerCaseCharCount").focus();
                        return false;
                    }
                    else
                    {
                        $("#minLowerCaseCharCount").css("border-color", "");
                    }

                    if (minNumCount === "")
                    {
//                        $("#minNumCount").css("border-color", "red");
                        $("#minNumCount").focus();
                        return false;
                    }
                    else
                    {
                        $("#minNumCount").css("border-color", "");
                    }

                    var sum = Number(minSpecialChar) + Number(minUpperCaseCharCount) + Number(minLowerCaseCharCount) + Number(minNumCount);
                    console.log("sum: " + sum);



                    if (Number(minPassLen) !== sum)
                    {
                        bootbox.dialog("Sum of number of special character, upper case character, lower case character and numeric must be equal to maximum password length!", [{"label": "ok", "class": "btn-small btn-primary"}]);
                        return false;
                    }
                });
                $("#edit_pass_config").click(function() {
                    $("#minPassLen").css("display", "block");
                    if ($("#ro_minSpecialChar").val() !== "")
                    {
                        $("#minPassLen").val($("#ro_minSpecialChar").val());

                    }
                    $("#ro_minSpecialChar").css("display", "none");

                    $("#minSpecialChar").prop("disabled", false);
                    $("#minUpperCaseCharCount").prop("disabled", false);
                    $("#minLowerCaseCharCount").prop("disabled", false);
                    $("#minNumCount").prop("disabled", false);

                    $("#save_password_config").removeClass("hidden");
                });

            });
        </script>

    </head>

    <body>

        <%@include file = "templatecustomer.jsp" %>
        <div class="breadcrumbs" id="breadcrumbs">
            <ul class="breadcrumb">
                <li class="align-center">
                    <h4>Password Configuration</h4>

                </li>
                <!--<li class="active">Response Comment</li>-->						
            </ul>
            <div class="nav-search" id="nav-search">
                <a href="redirectcustomerhome.do"><h5><b>Go to Home</b></h5></a>
            </div>
        </div>
        <div class="page-content">
            <div class="panel panel-shadow">
                <div class="panel-body">
                    <div class="row-fluid">
                        <div class="span12">
                            <form class="form-vertical" action="updatecustomerpasswordconfig.do" method="post" enctype="">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                <input type="hidden" name="id" value="${passConfigList.size() == 0 ? '' : passConfigList.get(0).id}">
                                <div class="row-fluid">
                                    <div class="span6">
                                        <div class="control-group">
                                            <label class="control-label" for="minPassLen">Password Length</label>
                                            <div class="controls">
                                                <div class="">
                                                    <select id="minPassLen" name="passLength" class="span10" required style="display: none;">
                                                        <option value="">--Select--</option>
                                                        <option value="8">8</option>
                                                        <option value="9">9</option>
                                                        <option value="10">10</option>
                                                        <option value="11">11</option>
                                                        <option value="12">12</option>
                                                        <option value="13">13</option>
                                                        <option value="14">14</option>
                                                        <option value="15">15</option>
                                                    </select>
                                                    <input type="text" value="${passConfigList.size() == 0 ? '' : passConfigList.get(0).passLength}" id="ro_minSpecialChar" name="ro_specialChar" class="span10" disabled="true">
                                                </div>
                                            </div>																
                                        </div>
                                    </div>
                                    <div class="span6">
                                        <div class="control-group">
                                            <label class="control-label" for="minSpecialChar">Number Of Special Characters</label>
                                            <div class="controls">
                                                <div class="">
                                                    <!--                                                    <select id="minSpecialChar" name="minSpecialChar" class="span10" required>
                                                                                                            <option value="">--Select--</option>
                                                                                                            <option>1</option>
                                                                                                            <option>2</option>
                                                                                                            <option>3</option>
                                                                                                            <option>4</option>
                                                                                                            <option>5</option>
                                                                                                            <option>6</option>
                                                                                                            <option>7</option>
                                                                                                            <option>8</option>
                                                                                                        </select>-->
                                                    <input type="text" value="${passConfigList.size() == 0 ? '' : passConfigList.get(0).specialChar}" id="minSpecialChar" name="specialChar" class="span10" required disabled="true">
                                                </div>
                                            </div>																
                                        </div>
                                    </div>
                                </div>
                                <div class="row-fluid">
                                    <div class="span6">
                                        <div class="control-group">
                                            <label class="control-label" for="minUpperCaseCharCount">Upper Case Character Count</label>
                                            <div class="controls">
                                                <div class="">
                                                    <!--                                                    <select id="minUpperCaseCharCount" name="minUpperCaseCharCount" class="span10" required>
                                                                                                            <option value="">--Select--</option>
                                                                                                            <option>1</option>
                                                                                                            <option>2</option>
                                                                                                            <option>3</option>
                                                                                                            <option>4</option>
                                                                                                            <option>5</option>
                                                                                                            <option>6</option>
                                                                                                            <option>7</option>
                                                                                                            <option>8</option>
                                                                                                        </select>-->
                                                    <input type="text" value="${passConfigList.size() == 0 ? '' : passConfigList.get(0).upparChar}" id="minUpperCaseCharCount" name="upparChar" class="span10" required disabled="true">
                                                </div>
                                            </div>																
                                        </div>
                                    </div>
                                    <div class="span6">
                                        <div class="control-group">
                                            <label class="control-label" for="minLowerCaseCharCount">Lower Case Character Count</label>
                                            <div class="controls">
                                                <div class="">
                                                    <!--                                                    <select id="minLowerCaseCharCount" name="minLowerCaseCharCount" class="span10" required>
                                                                                                            <option value="">--Select--</option>
                                                                                                            <option>1</option>
                                                                                                            <option>2</option>
                                                                                                            <option>3</option>
                                                                                                            <option>4</option>
                                                                                                            <option>5</option>
                                                                                                            <option>6</option>
                                                                                                            <option>7</option>
                                                                                                            <option>8</option>
                                                                                                        </select>-->
                                                    <input type="text" value="${passConfigList.size() == 0 ? '' : passConfigList.get(0).lowerChar}" id="minLowerCaseCharCount" name="lowerChar" class="span10" required disabled="true">
                                                </div>
                                            </div>																
                                        </div>
                                    </div>
                                </div>
                                <div class="row-fluid">
                                    <div class="span6">
                                        <div class="control-group">
                                            <label class="control-label" for="minNumCount">Numeric Count</label>
                                            <div class="controls">
                                                <div class="">
                                                    <!--                                                    <select id="minNumCount" name="minNumCount" class="span10" required>
                                                                                                            <option value="">--Select--</option>
                                                                                                            <option>1</option>
                                                                                                            <option>2</option>
                                                                                                            <option>3</option>
                                                                                                            <option>4</option>
                                                                                                            <option>5</option>
                                                                                                            <option>6</option>
                                                                                                            <option>7</option>
                                                                                                            <option>8</option>
                                                                                                        </select>-->
                                                    <input type="text" value="${passConfigList.size() == 0 ? '' : passConfigList.get(0).numericCount}" id="minNumCount" name="numericCount" class="span10" required disabled="true">
                                                </div>
                                            </div>																
                                        </div>
                                    </div>
                                </div>
                                <div class="space"></div>
                                <div class="row-fluid align-center">
                                    <div class="span12">
                                        <input type="button" value="Edit" class="btn btn-pink" id="edit_pass_config">
                                        <input type="submit" value="Save" class="btn btn-danger hidden" id="save_password_config">
                                    </div>
                                </div>
                            </form>
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
