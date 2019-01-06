
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
        
        <!--<link rel="stylesheet" href="https://cdn.datatables.net/1.10.18/css/jquery.dataTables.min.css" type="text/css"/>-->

        <!--<script src="https://cdn.datatables.net/1.10.18/js/jquery.dataTables.min.js" type="text/javascript"></script>-->
        
        <!--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">-->

        <script src="js/jquery.min.js"></script>
        <link rel="stylesheet" href="css/dataTables1.10.18.min.css" />
        <script src="js/dataTables1.10.18.min.js"></script>
        <link rel="stylesheet" href="css/font-awesome2.min.css" />
        
        <link rel="stylesheet" href="css/customer-notification.css" />
        <script src="js/customer-notification.js"></script>
        
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

        <script src="js/customer.js"></script>

        <style>
        </style>
        
        <script>
            $(document).ready(function()
            {
                var set_image = $("#set_image").prop("src").toString().split(",");
                if(set_image[1] === "NotFound")
                {
                    $("#default_image_a").css("display", "block");
                    $("#set_image_a").css("display", "none");
                }
                
                $('#groupTableId').DataTable({
                    "columnDef": [{"orderable": false, "targets": 1}]
                });

            });
        </script>

    </head>

    <body>

        <%@include file = "templatecustomer.jsp" %>
        <div class="breadcrumbs" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <h5><b>Manage Group</b></h5>
                </li>
                <!--<li class="active">Manage Group</li>-->						
            </ul>
            <div class="nav-search" id="nav-search">
                <a href="redirectcustomerhome.do"><h5><b>Go to Home</b></h5></a>
            </div>
        </div>
        <div class="page-content">
            <div class="row-fluid table-responsive panel-shadow">
                <div class="span12">
                    <div class="space"></div>
                    <table id="groupTableId" class="table table-striped table-bordered table-hover">
                        <thead>
                            <th>S.No.</th>
                            <th>Group Name</th>
                            <th>Description</th>
                            <th class="center"></th>
                        </thead>
                        <tbody>
                            <c:forEach var="group" items="${groups}" varStatus="status">
                                <tr>
                                    <td>${status.count}</td>
                                    <td>${group.groupname}</td>
                                    <td>${group.description}</td>
                                    <td class="center"><a href="mappedgroup.do?gid=${group.gid}" title="Edit Group"><i class="fa fa-pencil-square-o fa-2x align-center"></i></a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div class="space"></div>
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
