
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
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>

        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.18/css/jquery.dataTables.min.css" type="text/css"/>

        <script src="https://cdn.datatables.net/1.10.18/js/jquery.dataTables.min.js" type="text/javascript"></script>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

        <script src="js/customer.js"></script>

        <style>
        </style>

    </head>

    <body>

        <%@include file = "templatecustomer.jsp" %>
        <div class="breadcrumbs" id="breadcrumbs">
            <ul class="breadcrumb">
                <li class="align-center">
                    <h4>About Us</h4>

                </li>
                <!--<li class="active">Response Comment</li>-->						
            </ul>
        </div>
        <div class="page-content">
            <div class="panel panel-shadow">
                <div class="panel-body">
                    <div class="row-fluid">
                        <div class="span6">
                            <h4><b>NatSteel Holdings Pte Ltd</b></h4>
                            <p>Co Reg No 200810196Z<br>
                                22 Tanjong Kling Road<br>
                                Singapore 628048</p>
                            <br>
                            <p>Tel: (65) 6265 1233<br>
                                Fax: (65) 6265 8317</p>
                            <br><br>
                            <h4><b>How to get here</b></h4>
                            <b>By car</b><br>
                            <p>Take the Ayer Rajah Expressway (towards Tuas)<br>
                                Exit at Pioneer Circle (Exit 18)<br>
                                Filter left onto Pioneer Road<br>
                                Filter left onto Tanjong Kling Road</p>
                        </div>
                        <div class="span6">
                            <h5><b>General and overseas product enquiries</b></h5>
                            <p>enquiries@natsteel.com.sg</p>
                            <br>
                            <h5><b>Sales enquiries (local)</b></h5>
                            <p>sales@natsteel.com.sg</p>
                            <br>
                            <h5><b>Personal Data Protection Act (PDPA) Feedback</b></h5>
                            <p>For feedback relating to PDPA matters, please email to: dpo@natsteel.com.sg</p>
                            <br>
                            <h5><b>By bus</b></h5>
                            <p>Take bus service 249 from Boon Lay Interchange
                                Alight at bus stop along Tanjong Kling Road (after NatSteel Holdings' main office)</p>
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
