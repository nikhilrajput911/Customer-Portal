
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
        
        <!--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">-->
        <link rel="stylesheet" href="css/font-awesome2.min.css" />

        <link rel="stylesheet" href="css/customer-notification.css" />
        <script src="js/customer-notification.js"></script>
        
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

        <script src="js/customer.js"></script>

        <style>
        </style>

        <script>
            $(document).ready(function() {
                var set_image = $("#set_image").prop("src").toString().split(",");
                if(set_image[1] === "NotFound")
                {
                    $("#default_image_a").css("display", "block");
                    $("#set_image_a").css("display", "none");
                }
                
                $("#clear-customer-btn").click(function() {
                    location.reload(true);
                });
            });
        </script>
        
    </head>

    <body>

        <%@include file = "templatecustomer.jsp" %>
        <div class="breadcrumbs" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <a href="managegroup.do"><span class="menu-text"> Manage Group</span></a>
                    <span class="divider"><i class="icon-angle-right arrow-icon"></i></span>
                </li>
                <li class="active">Mapped Group</li>							
            </ul>
            <div class="nav-search" id="nav-search">
                <a href="redirectcustomerhome.do"><h5><b>Go to Home</b></h5></a>
            </div>
        </div>


        <div class="page-content">
            <div class="panel panel-shadow">
                <div class="panel-body">
                    <form method="post" action="mappcustomertogroup.do" name="group-mapping-form" id="group-mapping-form">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        <input type="hidden" id="selectedSupplierId" name="selectedSupplierId" value="">
                        <input type="hidden" id="gid" name="gid" value="">
                        <input type="hidden" id="custId" name="custId" value="">
                        <div class="row-fluid">
                            <div class="span6">
                                <h3><b>Group: </b>${group.groupname}</h3>
                            </div>
                        </div>
                        <hr>
                        <div class="row-fluid">
                            <div class="span6">
                                <div class="control-group">
                                    <label class="control-label" for="avail_customers"><b>Available Customer</b></label>
                                    <div class="controls">
                                        <div class="">
                                            <input type="text" id="search_customer" placeholder="Search..." style="width: 75%;">
                                            <select name="avail_customers" id="avail_customers" size="10" style="width: 80%;">            
                                                <c:forEach var="cust" items="${customer}">
                                                    <option value="${cust.id}">${cust.username}</option>
                                                </c:forEach>
                                            </select>

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="span6">
                                <div class="control-group">
                                    <button type="button" class="btn btn-purple btn-small" id="select-all-customer-btn">Select All</button>
                                    
                                    <label class="control-label" for="mapped_customers"><b>Mapped Customer</b></label>
                                    <div class="controls">
                                        <div class="">

                                            <select name="mapped_customers" id="mapped_customers" size="10" style="width: 80%;">            
                                                <c:forEach var="groupCust" items="${supplierGroup}">
                                                    <option style="background-color: blanchedalmond;" value="${groupCust.bpaasCustomersubuserId.id}">${groupCust.bpaasCustomersubuserId.username}</option>
                                                </c:forEach>
                                            </select>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row-fluid">
                            <div class="span12 align-center">
                                <button type="button" class="btn btn-danger hidden" id="mappcustomertogroupbtn">Update</button>
                                <!--<button type="button" class="btn btn-purple hidden" id="clear-customer-btn">Clear</button>-->
                                <button type="button" class="btn btn-pink hidden" id="deselect-customer-btn">Remove</button>
                            </div>
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

    });
</script>
</body>
</html>
