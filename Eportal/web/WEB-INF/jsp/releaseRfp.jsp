
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

    </head>
    <body>

        <%@include file = "template.jsp" %>
        <div class="breadcrumbs" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="icon-desktop"></i><a href="#"><span class="menu-text"> RFP Management </span></a>
                    <span class="divider"><i class="icon-angle-right arrow-icon"></i></span>
                </li>
                <li class="active">Release RFP</li>						
            </ul>
        </div>
        <div class="page-content">					
            <!--            <ul class="nav nav-pills">
                            <li class="active"><a data-toggle="pill" href="#releaseRfp">Release RFP</a></li>
                            <li><a data-toggle="pill" href="#releasedRfp">Released RFP</a></li>
                        </ul>-->
            <div class="tab-content">
                <div id="releaseRfp" class="tab-pane fade in active">
                    <div class="row-fluid">
                        <div class="col-md-12">
                            <div class="widget-box">
                                <div class="widget-header widget-header-blue widget-header-flat">
                                    <h4 class="lighter">Release RFP</h4>
                                </div>
                                <div class="widget-body">
                                    <form id="releaseRFPFrom" name="releaseRFPFrom" action="doReleaseRfp.do" method="post">
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
                                                                <select id="approved_rfp_select" name="approved_rfp_select" data-width="100%" data-live-search="true" class="selectpicker" data-style="btn-primary">
                                                                    <option value="" disabled selected hidden>Please choose any RFP...</option>
                                                                    <c:forEach var="rfqHeader" items="${map.headers}">
                                                                        <option value="${rfqHeader.rfqid}" class="align-left">${rfqHeader.RFQTitle} - ${rfqHeader.rfqstatus}</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                        </div>																
                                                    </div>
                                                </div>
                                            </div><br>
                                            <div class="row-fluid">
                                                <div class="span6">
                                                    <div class="control-group">
                                                        <label class="control-label" for="">RFQ Title</label>
                                                        <div class="controls">
                                                            <div class="">
                                                                <input type="text" id="rfqTitle" name="rfqTitle" style="width: 80%;" readonly="true">
                                                            </div>
                                                        </div>																
                                                    </div>
                                                </div>
                                                <div class="span6">
                                                    <div class="control-group">
                                                        <label class="control-label" for="">Time Left</label>
                                                        <div class="controls">
                                                            <div class="">
                                                                <input type="text" id="timeLeft" name="timeLeft" style="width: 80%;" readonly="true">
                                                            </div>
                                                        </div>																
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row-fluid">
                                                <div class="span6">
                                                    <div class="control-group">
                                                        <label class="control-label" for="">Description</label>
                                                        <div class="controls">
                                                            <div class="">
                                                                <textarea id="description" name="description" rows="3" style="width: 80%;background-color: #f2f2f2;" readonly="true"></textarea>
                                                            </div>
                                                        </div>																
                                                    </div>
                                                </div>
                                            </div>
                                            <h4 style="color: #005580;"><b>Supplier Details</b></h4>
                                            <hr>
                                            <div class="row-fluid">
                                                <div class="span6">
                                                    <div class="control-group">
                                                        <label class="control-label" for="availableSupplier"><b>Available Supplier</b></label>
                                                        <div class="controls">
                                                            <div class="">
                                                                <input type="text" id="search_supplier" placeholder="Search..." style="width: 80%;">
                                                                <select id="availableSupplier" name="availableSupplier" size="10" style="width: 85%;">
                                                                    <c:forEach var="supplier" items="${map.suppliers}">
                                                                        <option value="${supplier.id}" >${supplier.companyname}</option>
                                                                    </c:forEach>
                                                                </select>
                                                                <button type="button" class="btn btn-purple btn-small" id="select-all-suuplier-btn">Select All</button>
                                                                <button type="button" class="btn btn-pink btn-small" id="deselect-all-suuplier-btn">Deselect All</button>
                                                            </div>
                                                        </div>																
                                                    </div>
                                                </div>
                                                <div class="span6">
                                                    <div class="control-group">
                                                        <label class="control-label" for="selectedSupplier"><b>Selected Supplier</b></label>
                                                        <div class="controls">
                                                            <div class="">
                                                                <select id="selectedSupplier" name="selectedSupplier" multiple size="10" style="width: 85%;">
                                                                    <!--<option></option>-->
                                                                </select>
                                                            </div>
                                                        </div>																
                                                    </div>
                                                </div>
                                            </div>
                                            <input type="hidden" value="" id="selectedSupplierId" name="selectedSupplierId">
                                            <hr>
                                            <div class="row-fluid">
                                                <div class="span12 align-center">
                                                    <button type="" class="btn btn-danger btn-large" id="releaseRFPBtnId">Release</button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
                <div id="releasedRfp" class="tab-pane fade">
                    <div class="row-fluid">
                        <div class="span12" style="">
                   
                        </div>
                    </div>    
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
