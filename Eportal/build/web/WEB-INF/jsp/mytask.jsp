
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
        <link rel="stylesheet" href="css/bootstrap4-table.css" />


        <script>
            $(document).ready(function()
            {
                $('#myTaskTableId').DataTable({
                    "columnDef": [{"orderable": false, "targets": 1}]
                });

            });
        </script>

    </head>
    <body>

        <%@include file = "template.jsp" %>

        <div class="page-content">					

            <div class="space-10"></div>

            <div class="row-fluid table-responsive">
                <div class="span12" style="">
                    <table id="myTaskTableId" class="table table-hover table-condensed">
                        <thead class="">
                            <tr>
                                <th class="center">S. No</th>
                                <th class="center">RFQ No</th>
                                <th>RFQ Title</th>
                                <th>Status</th>
                                    <%                                            if (!session.getAttribute("userType").equals("Supplier")) {
                                    %>
                                <th>Assigned To</th>
                                    <%
                                        }
                                    %>
                                <th>Creation Date</th>
                                <!--<th>Open Date</th>-->
                                <!--<th>Close Date</th>-->
                                <!--<th>Payment Terms</th>-->
                                <th>Last Updated Date</th>
                                <th>Export</th>

                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach var="rfqheader" items="${rfqDetails.rqfheaders}" varStatus="status">
                                <tr>
<!--                                            <td class="center">${rfqheader.rfqid}</td>-->
                                    <td class="center">${status.count}</td>
                                    <%
                                        if (session.getAttribute("userType").equals("Supplier")) {
                                    %>
                                    <td><a href="redirectByRfpStatus.do?rfpid=${rfqheader.rfqid}&status=${rfqDetails.supplierSelection[status.index].supplierWFstatus}">RFP_${rfqheader.rfqid}</a></td>
                                    <%
                                        } else {
                                    %>
                                    <td><a href="showRfp.do?rfpId=${rfqheader.rfqid}">RFP_${rfqheader.rfqid}</a></td>
                                    <%
                                        }
                                    %>
                                    <td>${rfqheader.RFQTitle}</td>

                                    <%
                                        if (session.getAttribute("userType").equals("Supplier")) {
                                    %>
                                    <td>${rfqDetails.supplierSelection[status.index].supplierWFstatus}</td>
                                    <%
                                    } else {
                                    %>
                                    <td>${rfqheader.rfqstatus}</td>
                                    <%
                                        }
                                    %>
                                    <%
                                        if (!session.getAttribute("userType").equals("Supplier")) {
                                    %>
                                    <td>${rfqheader.assignedTo}</td>
                                    <%
                                        }
                                    %>
                                    <td>${rfqheader.creationdate}</td>
                                    <!--<td>${rfqheader.opendate}</td>-->
                                    <!--<td>${rfqheader.closedate}</td>-->
                                    <!--<td>${rfqheader.paymentterms}</td>-->
                                    <td>${rfqheader.updatedate}</td>
                                    <td><a class="btn btn-small btn-danger">PDF</a></td>
                                </tr>
                            </c:forEach> 
                        </tbody>
                    </table>
                </div>
            </div>

        </div>				
    </div><!--/.main-content-->
</div><!--/.main-container-->		


<!--<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>-->				
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
