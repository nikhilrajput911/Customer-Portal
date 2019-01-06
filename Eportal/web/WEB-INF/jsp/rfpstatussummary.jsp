
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
        <style>
            td.details-control {
                background: url('images/plus_icon.png') no-repeat center center;
                cursor: pointer;
            }
            tr.shown td.details-control {
                background: url('images/minus_icon.png') no-repeat center center;
            }
        </style>
        <script type="text/javascript">
            $(document).ready(function()
            {
                $("#summary_table").dataTable();

                $('#completedRfeStatusTableId').DataTable();

                var releasedRfeTable = $('#releasedRfeTableId').DataTable({
                    "columns": [
                        {
                            "className": 'details-control',
                            "orderable": false,
                            "data": null,
                            "defaultContent": ''
                        },
                        {"data": "S.No."},
                        {"data": "RFP No."},
                        {"data": "RFP Title"},
                        {"data": "Creation Date"},
                        {"data": "Open Date"},
                        {"data": "Close Date"},
                        {"data": "Released Date"},
                        {"data": "Export"},
                    ],
                    "order": [[1, 'asc']]
                });
                $("#releasedRfeTableId tbody").on('click', 'td.details-control', function() {
                    var tr = $(this).closest('tr');
//        alert(tr.children('td:eq(2)').text());
                    var row = releasedRfeTable.row(tr);

                    if (row.child.isShown())
                    {
                        row.child.hide();
                        tr.removeClass('shown');
                    }
                    else
                    {
                        row.child(format(tr.children('td:eq(2)').text())).show();
                        tr.addClass('shown');

                    }
                });
            });
        </script>

    </head>
    <body>

        <%@include file = "template.jsp" %>
        <div class="breadcrumbs" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="icon-home home-icon"></i><a href="home.do">Home</a>
                    <span class="divider"><i class="icon-angle-right arrow-icon"></i></span>
                </li>
                <li class="active">RFP Status</li>						
            </ul>
        </div>
        <div class="page-content">
            <ul class="nav nav-tabs">
                <li class="active"><a data-toggle="pill" href="#status">Total RFP</a></li>
                <li><a data-toggle="pill" href="#releasedRfp">Released RFP</a></li>
                <li><a data-toggle="pill" href="#completedRfp">Completed RFP</a></li>
            </ul>
            <!--<form id="rfq_summary_form" name="rfq_summary_form" action="" method="">-->
            <!--            <div class="row-fluid">
                            <div class="span4">
                                <div class="control-group">
                                    <label class="control-label" for="">RFQ No.</label>
                                    <div class="controls">
                                        <select id="rfq_search_no" name="rfq_search_no" data-width="100%" data-live-search="true" class="selectpicker" data-style="btn-primary">
                                            <option value="default" disabled selected hidden>Please choose any RFP No...</option>
            <c:forEach var="rfqHeader" items="${map.rfqHeader}">
                <option value="${rfqHeader.rfqid}">RFQ_${rfqHeader.rfqid}</option>
            </c:forEach>
        </select>
    </div>																
</div>

</div>
<div class="span4">
<div class="control-group">
    <label class="control-label" for="">Released RFQ</label>
    <div class="controls">
        <select id="rfq_search_released" name="rfq_search_released" data-width="100%" data-live-search="true" class="selectpicker" data-style="btn-primary">
            <option value="default" disabled selected hidden>Please choose any RFQ...</option>
            <c:forEach var="releasedRfq" items="${map.releasedRfqList}">
                <option value="${releasedRfq.rfqid}">RFQ_${releasedRfq.rfqid}</option>
            </c:forEach>
        </select>
    </div>																
</div>

</div>
<div class="span4">
<div class="control-group">
    <label class="control-label" for="">RFQ Status</label>
    <div class="controls">
        <select id="rfq_search_status" name="rfq_search_status" data-width="100%" data-live-search="true" class="selectpicker" data-style="btn-primary">
            <option value="default" disabled selected hidden>Please choose any Status...</option>
            <c:forEach var="status" items="${map.statusList}">
                <option value="${status}">${status}</option>
            </c:forEach>
        </select>
    </div>																
</div>
</div>
</div>
<div class="row-fluid">
<div class="span4">
<div class="control-group">
    <label class="control-label" for="">RFQ Title</label>
    <div class="controls">
        <select id="rfq_search_title" name="rfq_search_title" data-width="100%" data-live-search="true" class="selectpicker" data-style="btn-primary">
            <option value="default" disabled selected hidden>Please choose any RFP Title...</option>
            <c:forEach var="rfqHeader" items="${map.rfqHeader}">
                <option value="${rfqHeader.RFQTitle}">${rfqHeader.RFQTitle}</option>
            </c:forEach>
        </select>
    </div>																
</div>
</div>
<div class="span4">
<div class="control-group">
    <label class="control-label" for="">Supplier</label>
    <div class="controls">
        <select id="rfq_search_supplier" name="rfq_search_supplier" data-width="100%" data-live-search="true" class="selectpicker" data-style="btn-primary">
            <option value="default" disabled selected hidden>Please choose any Supplier...</option>
            <c:forEach var="supplier" items="${map.supplierList}">
                <option value="${supplier.id}">${supplier.companyname}</option>
            </c:forEach>
        </select>
    </div>																
</div>
</div>
</div>
<div class="row-fluid">

</div>
<div class="row-fluid">
<div class="span6">
<button id="search_btn_id" class="btn btn-pink">Search</button>
<button id="search_clear_btn_id" class="btn btn-danger">Clear</button>
</div>
</div><br>-->
            <!--</form>-->
            <div class="tab-content" style="height: 500px;">
                <div id="status" class="tab-pane fade in active">
                    <div class="row-fluid table-responsive" id="rfq_summary_table_div">
                        <div class="span12">
                            <table id="summary_table" class="table table-hover table-condensed">
                                <thead>
                                    <tr>
                                        <th>S.No.</th>  
                                        <th>RFQ No.</th>
                                        <th>Status</th>
                                        <th>Title</th>
                                        <th>Time Left</th>
                                        <th>Supplier</th>
                                        <th>Supplier RFQ Status</th>
                                    </tr>
                                </thead>

                                <tbody>
                                    <c:forEach var="rfqHeaderSupplier" items="${map.rfqHeaderSupplierList}" varStatus="status">
                                        <tr>
                                            <td class="center-align">${status.count}</td>
                                            <td><a target="_blank" href="rfpsummarydetails.do?rfqid=${rfqHeaderSupplier[1].rfqid}&rfpstatus=${rfqHeaderSupplier[1].rfqstatus}&supplierrfpstatus=${rfqHeaderSupplier[0] == null ? 'na' : rfqHeaderSupplier[0].supplierWFstatus}&supplierid=${rfqHeaderSupplier[0] == null ? 'na' : rfqHeaderSupplier[0].getBPaasSupplierUserTableid().id}">RFQ_${rfqHeaderSupplier[1].rfqid}</a></td>
                                            <td>${rfqHeaderSupplier[1].rfqstatus}</td>
                                            <td>${rfqHeaderSupplier[1].RFQTitle}</td>
                                            <td>${rfqHeaderSupplier[1].timeleft}</td>
                                            <td>${rfqHeaderSupplier[0] == null ? '' : rfqHeaderSupplier[0].getBPaasSupplierUserTableid().companyname}</td>
                                            <td>${rfqHeaderSupplier[0] == null ? '' : rfqHeaderSupplier[0].supplierWFstatus}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div id="releasedRfp" class="tab-pane fade">
                    <div class="row-fluid table-responsive">
                        <div class="span12" style="">
                            <table id="releasedRfeTableId" class="table table-hover table-condensed">
                                <thead class="table-header">
                                    <tr>
                                        <th></th>
                                        <th class="center">S. No</th>
                                        <th class="center">RFQ No</th>
                                        <th>RFQ Title</th>
                                        <th>Creation Date</th>
                                        <th>Open Date</th>
                                        <th>Close Date</th>
                                        <th>Released Date</th>
                                        <th>Export</th>
                                    </tr>
                                </thead>

                                <tbody>
                                    <c:forEach var="rfqheader" items="${map.releasedHeaders}" varStatus="status">
                                        <tr>
                                            <td></td>
                                            <td class="center">${status.count}</td>
                                            <td><a href="showRfp.do?rfpId=${rfqheader.rfqid}">RFP_${rfqheader.rfqid}</a></td>
                                            <td>${rfqheader.RFQTitle}</td>
                                            <td>${rfqheader.creationdate}</td>
                                            <td>${rfqheader.opendate}</td>
                                            <td>${rfqheader.closedate}</td>
                                            <td>${rfqheader.updatedate}</td>
                                            <td><a class="btn btn-small btn-danger">PDF</a></td>
                                        </tr>
                                    </c:forEach> 
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div id="completedRfp" class="tab-pane fade">
                    <div class="row-fluid table-responsive">
                        <div class="span12" style="">
                            <table id="completedRfeStatusTableId" class="table table-hover table-condensed">
                                <thead class="table-header">
                                    <tr>
                                        <th class="center">S. No</th>
                                        <th class="center">RFQ No</th>
                                        <th>RFQ Title</th>
                                        <th>Supplier</th>
                                        <th>Status</th>
                                        <th>Creation Date</th>
                                        <th>Completion Date</th>
                                        <!--<th>Open Date</th>-->
                                        <!--<th>Close Date</th>-->
                                        <!--<th>Released Date</th>-->
                                        <th>Export</th>
                                    </tr>
                                </thead>

                                <tbody>
                                    <c:forEach var="supplierSelection" items="${map.supplierSelection}" varStatus="status">
                                        <tr>
                                            <td class="center">${status.count}</td>
                                            <td><a href="showAwardRfp.do?rfpid=${supplierSelection.BPaasWorkOrderRFQHeaderRFQID.rfqid}&supplierid=${supplierSelection.BPaasSupplierUserTableid.id}">RFP_${supplierSelection.BPaasWorkOrderRFQHeaderRFQID.rfqid}</a></td>
                                            <td>${supplierSelection.BPaasWorkOrderRFQHeaderRFQID.RFQTitle}</td>
                                            <td>${supplierSelection.BPaasSupplierUserTableid.companyname}</td>
                                            <td>${supplierSelection.supplierWFstatus}</td>
                                            <td>${supplierSelection.BPaasWorkOrderRFQHeaderRFQID.creationdate}</td>
                                            <td>${supplierSelection.updatedate}</td>
                                            <!--<td>${supplierSelection.BPaasWorkOrderRFQHeaderRFQID.opendate}</td>-->
                                            <!--<td>${supplierSelection.BPaasWorkOrderRFQHeaderRFQID.closedate}</td>-->
                                            <!--<td>${supplierSelection.BPaasWorkOrderRFQHeaderRFQID.updatedate}</td>-->
                                            <td><a class="btn btn-small btn-danger">PDF</a></td>
                                        </tr>
                                    </c:forEach> 
                                </tbody>
                            </table>
                        </div>
                    </div> 
                </div>
            </div>
            <br><br>

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
