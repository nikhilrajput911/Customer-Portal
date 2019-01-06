<%-- 
    Document   : registeredsupplier
    Created on : 1 Aug, 2018, 6:01:17 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="description" content="User login page" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />		

        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js" type="text/javascript"></script>

        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.18/css/jquery.dataTables.min.css" type="text/css"/>

        <script src="https://cdn.datatables.net/1.10.18/js/jquery.dataTables.min.js" type="text/javascript"></script>

        <link href="css/bootstrap.min.css" rel="stylesheet" />
        <link href="css/bootstrap-responsive.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="css/font-awesome.min.css" />
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />

        <link rel="stylesheet" href="css/ace.min.css" />
        <link rel="stylesheet" href="css/ace-responsive.min.css" />
        <link rel="stylesheet" href="css/ace-skins.min.css" />
        <link rel="stylesheet" href="css/main.css" />


        <script>
            $(document).ready(function()
            {
                $('#registeredsupplierTable').DataTable({
                    "columnDef": [{"orderable": false, "targets": 1}]
                });

            });
        </script>

    </head>


    <body>
        <%@include file = "template.jsp" %>
        <div class="breadcrumbs" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="icon-book"></i><a href=""><span class="menu-text"> Supplier Management </span></a>
                    <span class="divider"><i class="icon-angle-right arrow-icon"></i></span>
                </li>
                <li class="active">Registered Supplier</li>						
            </ul>
        </div>
        <div class="page-content">					

            <div class="space-10"></div>

            <div class="row-fluid">
                <div class="span12" style="">
                    <table id="registeredsupplierTable" class="table table-striped table-bordered table-hover display">
                        <thead>
                            <tr>
                                <th class="center">S. No</th>
                                <th>Company Name</th>
                                <th>Type of Business</th>
                                <th>Supplier Status</th>
                                <th>GST State</th>
                                <th>Creation Date</th>
                                <th>Created by</th>
                                <th>Updated on</th>
                                <th>Updated by</th>
                            </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="supplier" items="${supplierList}" varStatus="status">
                            <tr>
                                <td class="center">${status.count}</td>
                                <td><a href="showSupplier.do?supplierId=${supplier.id}">${supplier.companyname}</a></td>
                                <td>${supplier.typeofbusiness}</td>
                                <td>${supplier.supplierstatus}</td>
                                <td>${supplier.gstState}</td>
                                <td>${supplier.creationdate}</td>
                                <td>${supplier.createdby}</td>
                                <td>${supplier.updatedate}</td>
                                <td>${supplier.updatedby}</td>
                            </tr>
                        </c:forEach> 
                        </tbody>
                    </table>
                </div>
            </div>

        </div>				
    </div><!--/.main-content-->
</div><!--/.main-container-->		

<script type="text/javascript">
    window.jQuery || document.write("<script src='js/jquery-2.0.3.min.js'>" + "<" + "/script>");
</script>				
<script type="text/javascript">
    if ("ontouchend" in document)
        document.write("<script src='js/jquery.mobile.custom.min.js'>" + "<" + "/script>");
</script>
<script src="js/bootstrap.min.js"></script>		
<script src="js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="js/jquery.ui.touch-punch.min.js"></script>
<script src="js/jquery.slimscroll.min.js"></script>
<script src="js/ace-elements.min.js"></script>
<script src="js/ace.min.js"></script>	

<script type="text/javascript">
    $('.dialogs,.comments').slimScroll({
        height: '200px'
    });
</script>
</body>
</html>
