
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
        <link rel="stylesheet" href="css/dataTables1.10.18.min.css" />

        <!--<script src="https://cdn.datatables.net/1.10.18/js/jquery.dataTables.min.js" type="text/javascript"></script>-->
        <script src="js/dataTables1.10.18.min.js"></script>

        <!--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">-->

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
                if (set_image[1] === "NotFound")
                {
                    $("#default_image_a").css("display", "block");
                    $("#set_image_a").css("display", "none");
                }

                $('#customerTableId').DataTable({
                    "columnDef": [{"orderable": false, "targets": 1}]
                });
                $("#messageToAll").change(function() {
                    $("#messageToAllCustomerUser").val($(this).val());
                });
                $("#send_message_to_all_supplier").click(function() {
                    $("#comment-form").submit();
                });
                $("#send_message_to_all").click(function() {
                    $("#chatDiv").removeClass("hidden");
                    $("#sendMessageToAllModal").modal("show");
                });
            });
        </script>
    </head>

    <body>

        <%@include file = "templatecustomer.jsp" %>

        <div class="breadcrumbs" id="breadcrumbs">
            <ul class="breadcrumb">
                <li class="align-center">
                    <h4>Customers</h4>

                </li>
                <!--<li class="active">Response Comment</li>-->						
            </ul>
            <div class="nav-search" id="nav-search">
                <a href="redirectcustomerhome.do"><h5><b>Go to Home</b></h5></a>
            </div>
        </div>
        <div class="page-content">
            <h5 class="center"><span style="color: green;">${message}</span></h5>
            <div class="panel panel-shadow">
                <div class="panel-body">
                    <%                        if (session.getAttribute("userRole").equals("Admin")) {
                    %>
                    <div class="row-fluid">
                        <div class="span6">
                            <input type="button" id="send_message_to_all" value="Send message to all" class="btn btn-success">
                            <!--<input type="button" id="send_message_to_all" value="Send message to all" data-toggle="modal" data-target="#sendMessageToAllModal" class="btn btn-success">-->
                        </div>
                    </div>
                    <%
                        }
                    %>
                    <div class="space"></div>
                    <div class="row-fluid">
                        <div class="span12">
                            <table id="customerTableId" class="table table-hover table-condensed">
                                <thead>
                                <th>S.No.</th>
                                <th>Personal Name</th>
                                <th>Username</th>
                                <th>Company Name</th>
                                </thead>
                                <tbody>
                                    <c:forEach var="customer" items="${map.customerUserList}" varStatus="status">
                                        <tr>
                                            <td>${status.count}</td>
                                            <td><a href="customerresponsemgnt.do?supplierid=${customer.id}">${customer.fisrtname} ${customer.lastname}</a></td>
                                            <td>${customer.username}</td>
                                            <td>${customer.bpaasCustomerseededCid.customername}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody> 
                            </table>
                        </div>
                    </div>
                    <div id="chatDiv" class="hidden">
                        <div class="modal fade" id="sendMessageToAllModal" role="dialog">
                            <div class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title">Send Message</h4>
                                    </div>
                                    <div class="modal-body">
                                        <form id="comment-form" method="post" action="sendmessagetoallcustomeruser.do" enctype="multipart/form-data">
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                            <textarea class="span5" style="" id="messageToAll" name="messageToAll" rows="6" cols=""></textarea>
                                            <input type="file" id="id-input-file-1" class="span10" name="file"/>	
                                            <input type="hidden" id="messageToAllCustomerUser" name="messageToAllCustomerUser">
                                        </form>    
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-success" id="send_message_to_all_supplier">Send</button>
                                        <button type="button" class="btn btn-inverse" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>


    </div><!--/.main-content  <span class="badge">15</span>-->
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
        $('#id-input-file-1').ace_file_input({
            no_file: 'No File ...',
            btn_choose: 'Choose',
            btn_change: 'Change',
            droppable: false,
            onchange: null,
            thumbnail: false
        });
    });
</script>
</body>
</html>
