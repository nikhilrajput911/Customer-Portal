
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

        <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/css/bootstrap-select.min.css" />
        <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/js/bootstrap-select.min.js"></script>

        <script src="js/rfe.js"></script>

        <script type="text/javascript">
            $('.selectpicker').selectpicker();
        </script>

        <style>
            
        </style>
        <script>
            $(document).ready(function() {
//                $('#releasedRfeStatusTableId').DataTable();
                
            });


        </script>
    </head>
    <body>

        <%@include file = "template.jsp" %>
        <div class="breadcrumbs" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="icon-desktop"></i><a href="#"><span class="menu-text"> RFP Management </span></a>
                    <span class="divider"><i class="icon-angle-right arrow-icon"></i></span>
                </li>
                <li class="active">Award RFP</li>						
            </ul>
        </div>
        <div class="page-content">					
            
            <!--<div class="tab-content" style="height: 500px;">-->
                <!--<div id="status" class="tab-pane fade in active">-->
                    <div class="row-fluid">
                        <div class="col-md-12">
                            <!--<div class="widget-box">-->
                            <!--                                <div class="widget-header widget-header-blue widget-header-flat">
                                                                <h4 class="lighter">Release RFP</h4>
                                                            </div>-->
                            <!--<div class="widget-body">-->
                            <form id="awardRFPFrom" name="releaseRFPFrom" action="doAwardAllSupplierRfp.do" method="post">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                <div class="widget-main">
                                    <div class="row-fluid">
                                        <div class="span12 align-center">
                                            <div class="control-group">
                                                <!--<label class="control-label" for="">Approved RFQ</label>-->
                                                <h4><span style="color: green;">${message}</span></h4>
                                                <br>
                                                <div class="controls">
                                                    <div class="">
                                                        <select id="released_rfp_select" name="released_rfp_select" data-width="100%" data-live-search="true" class="selectpicker" data-style="btn-primary">
                                                            <option value="" disabled selected hidden>Please choose any RFP...</option>
                                                            <c:forEach var="rfqHeader" items="${map.releasedHeaders}">
                                                                <option value="${rfqHeader.rfqid}" class="align-left">${rfqHeader.RFQTitle}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>																
                                            </div>
                                        </div>
                                    </div><br>
                                    <div class="row-fluid">
                                        <div class="span2 align-center">
                                            <!--<button id="awardAllSupplier_btn" class="btn btn-purple btn-small" style="display: none;">Award All</button>-->
                                            <input type="submit" value="Award" class="btn btn-purple btn-small" style="display: none;" id="awardAllSupplier_btn">
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row-fluid table-responsive" id="releasedRfeStatusTableDivId" style="display: none;">
                                        <div class="span12">
                                            <table id="releasedRfeStatusTableId" class="table table-hover table-condensed">
                                                <thead class="table-header">
                                                    <tr>
                                                        <th></th>
                                                        <th>S. No</th>
                                                        <th>Supplier Name</th>
                                                        <th>RFP Status</th>
                                                        <th>Last Update Date</th>
                                                        <th>Create Date</th>
                                                        <th>Time Left</th>

                                                    </tr>
                                                </thead>

                                                <tbody>
                                                </tbody>
                                            </table>
                                        </div>

                                    </div>

                                </div>
                                <input type="hidden" id="suppliersId" name="suppliersId" value="">
                                <input type="hidden" id="rfqid" name="rfqid" value="">
                            </form>
                            <!--                                </div>
                                                        </div>-->

                        </div>
                    </div>
                <!--</div>-->
                
                                                
            <!--</div>-->                                        
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
<script src="js/bootbox.min.js"></script>   
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
