
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
                if (set_image[1] === "NotFound")
                {
                    $("#default_image_a").css("display", "block");
                    $("#set_image_a").css("display", "none");
                }

                var table = $('#groupTableId').DataTable({
                    "columnDefs": [
                        {
                            "targets": [5],
                            "visible": false
                        }
                    ]
                });
                $("#customer_name").click(function() {
                    table.draw();
                });

                $("#groupTableId").on("click", ".edit-column", function() {

                    if ($(this).prop("title") === "Update")
                    {
                        console.log("update");
                        var pid = $(this).parent().parent().find("td").eq(0).html();
                        var status = $(this).parent().parent().find("td").eq(4).children().val();
                        console.log("pid: " + pid);
                        console.log("status " + status);
                        if (status !== "Select")
                        {
                            updateProjectStatus(pid, status);


//                            $(this).parent().parent().find("td").eq(3).children().css("border-color", "");
                        }
                        else
                        {
                            $(this).parent().parent().find("td").eq(4).children().css("border-color", "red");
                        }
                    }
                    else
                    {
                        console.log("edit");
                        console.log("pid: " + $(this).parent().parent().find("td").eq(0).html());
                        console.log("status: " + $(this).parent().parent().find("td").eq(4).children().val());
                        $(this).children().prop("class", "fa fa-save fa-2x align-center");
                        $(this).prop("title", "Update");
                        $(this).parent().parent().find("td").eq(4).children().prop("disabled", false);
                    }
                });

            });
            function updateProjectStatus(pid, status)
            {
                $.ajax(
                        {
                            type: "GET",
                            url: "rfeCont.do",
                            data:
                                    {
                                        "reqFrom": "UpdateProjectStatus",
                                        "Pid": pid,
                                        "Status": status
                                    },
                            dataType: "json",
                            complete: function(responseJson)
                            {
//                                            var obj = $.parseJSON(responseJson.responseText);
//                                            console.log("status: " + obj.status);
//                                            if (obj.status === "success")
//                                            {
                                location.href = "customermanageprojects.do";
//                                            }
                            }
                        });
            }

            $.fn.dataTable.ext.search.push(
                    function(settings, data, dataIndex) {
//                        alert("2");
                        var customer_name = $('#customer_name').val();
                        var customer = data[5];

                        if (customer.toString().indexOf(customer_name) !== -1)
                        {
                            console.log("sec");
                            return true;
                        }
                        if (customer_name === "All")
                        {
                            return true;
                        }
                        return false;
                    }

            );
        </script>

    </head>

    <body>

        <%@include file = "templatecustomer.jsp" %>
        <div class="breadcrumbs" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <a href="manageuser.do"><span class="menu-text"> Manage User</span></a>
                    <span class="divider"><i class="icon-angle-right arrow-icon"></i></span>
                </li>
                <li class="active">Manage Projects</li>					
            </ul>
            <div class="nav-search" id="nav-search">
                <a href="redirectcustomerhome.do"><h5><b>Go to Home</b></h5></a>
            </div>
        </div>
        <div class="page-content">
            <h5 class="center"><span style="color: green;" id="message"></span></h5>
            <div class="row-fluid">
                <div class="span6">
                    <div class="control-group">
                        <label class="control-label" for="">Customer Name</label>
                        <div class="controls">
                            <div class="">
                                <select id="customer_name" name="customer_name" class="span10 subuser-form-field" required>
                                    <option>--Select--</option>
                                    <option>All</option>
                                    <c:forEach var="customer" items="${customerList}" varStatus="status">
                                        <option value="${customer.customername}">${customer.customername}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
            <div class="row-fluid table-responsive panel-shadow">
                <div class="span12">
                    <div class="space"></div>
                    <table id="groupTableId" style="width: 100%;" class="table table-striped table-bordered table-hover">
                        <thead>
                        <th class="hidden"></th>
                        <th class="hidden">S.No.</th>

                        <th>Project Name</th>
                        <th>Project Status</th>
                        <th class=""></th>
                        <th>Customer</th>
                        <th class=""></th>
                        </thead>
                        <tbody>
                            <c:forEach var="project" items="${projectList}" varStatus="status">
                                <tr>
                                    <td class="hidden">${project.pid}</td>
                                    <td class="hidden">${status.count}</td>

                                    <td>${project.projectname}</td>
                                    <td>${project.projectstatus}</td>
                                    <td class=""><select disabled="true"><option>Select</option><option>Active</option><option>Inactive</option></select></td>
                                    <td>${project.bpaasCustomerseededCid.customername}</td>
                                    <td class="center"><a class="edit-column" title="Edit Project"><i class="fa fa-pencil-square-o fa-2x align-center"></i></a></td>
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
