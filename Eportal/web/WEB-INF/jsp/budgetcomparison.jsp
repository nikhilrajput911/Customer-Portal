
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

        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

        <script src="js/response-management.js"></script>

        <script type="text/javascript">

            $('.selectpicker').selectpicker();
        </script>

    </head>
    <body>

        <%@include file = "template.jsp" %>
        <div class="breadcrumbs" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="icon-picture"></i><a href="#">Report Management</a>
                    <span class="divider"><i class="icon-angle-right arrow-icon"></i></span>
                </li>
                <li class="active">Budget Comparison</li>						
            </ul>
        </div>
        <div class="page-content">					
            <!--            <ul class="nav nav-pills">
                            <li class="active"><a data-toggle="pill" href="#releaseRfp">Release RFP</a></li>
                            <li><a data-toggle="pill" href="#releasedRfp">Released RFP</a></li>
                        </ul>-->
            <!--<div class="tab-content">-->
            <!--<div id="releaseRfp" class="tab-pane fade in active">-->
            <!--<div class="row-fluid">-->
            <!--<div class="col-md-12">-->
            <!--<div class="widget-box">-->
            <!--                            <div class="widget-header widget-header-blue widget-header-flat">
                                            <h4 class="lighter">Response Management</h4>
                                        </div>-->
            <!--<div class="widget-body">-->
            <!--<form id="releaseRFPFrom" name="releaseRFPFrom" action="doReleaseRfp.do" method="post">-->
            <!--<div class="widget-main">-->
            <div class="row-fluid">
                <div class="span12 align-center">
                    <div class="control-group">
                        <!--<label class="control-label" for="">Approved RFQ</label>-->
                        <!--<h4><span style="color: red;">${message}</span></h4>-->
                        <br>
                        <div class="controls">
                            <div class="">
                                <select id="approved_rfp_select" name="approved_rfp_select" data-width="100%" data-live-search="true" class="selectpicker" data-style="btn-primary">
                                    <option value="" disabled selected hidden>Please choose any RFP...</option>
                                    <c:forEach var="rfqHeader" items="${map.rfqHeaderList}">
                                        <option value="${rfqHeader.rfqid}" class="align-left">${rfqHeader.RFQTitle}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>																
                    </div>

                </div>
            </div>

            <br>            
            <div class="row-fluid">
                <div class="span12">
                    <div id="materialBarChartDiv">

                    </div>
                </div>
            </div>
            <hr>
            <br>
            <div class="row-fluid" style="display: none;" id="tableDivId">
                <div class="span12">
                    <table id="budgetComparisonTable" class="table table-striped table-bordered table-hover">
                        <thead>
                            <tr>
                                <th>S. No.</th>
                                <th>Supplier</th>
                                <th>RFQ Id</th>
                                <th>Time Left</th>
                                <th>Quantity</th>
                                <th>Price Per Quantity</th>
                                <th>Expected Price</th>
                                <th>Total Price</th>
                                <th>Target Price</th>
                            </tr>
                        </thead>

                        <tbody>

                        </tbody>
                    </table>
                </div>
            </div>

            <!--</div>-->
            <!--</form>-->
            <!--</div>-->
            <!--</div>-->

            <!--</div>-->
            <!--</div>-->
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
