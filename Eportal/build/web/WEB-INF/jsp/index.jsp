
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="JSON.JSONObject, JSON.JSONArray,JSON.JSONException" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@page session="true" %>

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
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />		

        <link rel="stylesheet" href="css/ace.min.css" />
        <link rel="stylesheet" href="css/ace-responsive.min.css" />
        <link rel="stylesheet" href="css/ace-skins.min.css" />
        <link rel="stylesheet" href="css/main.css" />

        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js" type="text/javascript"></script>

        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.18/css/jquery.dataTables.min.css" type="text/css"/>

        <script src="https://cdn.datatables.net/1.10.18/js/jquery.dataTables.min.js" type="text/javascript"></script>

        <script src="js/index.js"></script>

        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

        <script type="text/javascript">
            $(document).ready(function()
            {
//                $('#rfpTableId').DataTable();

            });
            // Load google charts
            google.charts.load('current', {packages: ['corechart', 'bar']});
            google.charts.setOnLoadCallback(drawChart);


            // Draw the chart and set the chart values
            function drawChart() {
                var total = document.getElementById('totalRFP').value;
                var approved = document.getElementById('approvedRFP').value;
                var pending = document.getElementById('pendingRFP').value;
                var released = document.getElementById('releasedRFP').value;
                var awarded = document.getElementById('awardedRFP').value;
//                console.log(total);
//                console.log(approved);
//                console.log(pending);

                var data = google.visualization.arrayToDataTable([
                    ['Status', 'Count'],
                    ['Awarded', 0],
                    ['Released', released],
                    ['Pending', pending],
                    ['Approved', approved],
                    ['Total', total]
                ]);

                var materialOptions = {
                    chart: {
                    },
                    height: 250,
                    width: 500,
                    bars: 'vertical',
                    bar: {groupWidth: '50%'}
                };
                var materialChart = new google.charts.Bar(document.getElementById('piechart'));
                materialChart.draw(data, materialOptions);
            }
        </script>

        <style>
            .hoverCSS:hover {
                border-color: whitesmoke;
                border-width: 2px;
            }
        </style>
    </head>

    <body>

        <%@include file = "template.jsp" %>

        <div class="page-content">	
            <div class="row-fluid">
                <div class="span2">
                    <!--                    <div class="panel panel-primary">
                                            <div class="panel-heading">Total RFP</div>
                                            <div class="panel-body align-center">${jsonObj.get('TotalRfq')}</div>
                                            <div class="panel-footer">Panel Footer</div>
                                        </div>-->
                    <div class="card hoverCSS">
                        <div class="card-body bg-primary text-white">
                            <h4 class="card-title">Total RFP</h4>
                            <%                                if (session.getAttribute("userType").equals("SCM User")) {
                            %>
                            <p class="card-text align-center"><h2>${detailsMap.headers.size()}</h2></p>
                                <%
                                } else if (session.getAttribute("userType").equals("Approver")) {
                                %>
                            <p class="card-text align-center"><h2>${detailsMap.selectedApproval.size()}</h2></p>
                                <%
                                    }
                                %>
                            <a href="#" class="card-link text-white">Details</a>
                            <!--<security:authentication property="principal"></security:authentication>-->
                            <!--<a href="#" class="btn btn-primary btn-small">Details</a>-->
                            <!--<a href="#" class="card-link text-white">Another link</a>-->
                            ${pageContext.request.userPrincipal.name}
                        </div>
                    </div>
                </div>
                <%                    if (session.getAttribute("userType").equals("SCM User")) {
                %>            
                <div class="span2">
                    <div class="card hoverCSS">
                        <div class="card-body bg-success text-white">
                            <h4 class="card-title">Approved RFP</h4>
                            <p class="card-text"><h2>${detailsMap.ApprovedRfq}</h2></p>
                            <a href="#" class="card-link text-white">Details</a>
                            <!--<a href="#" class="card-link text-white">Another link</a>-->
                        </div>
                    </div>    
                </div>
                <%
                    }
                %>            
                <div class="span2">
                    <div class="card hoverCSS">
                        <div class="card-body bg-warning text-white">
                            <h4 class="card-title">Pending RFP</h4>
                            <%
                                if (session.getAttribute("userType").equals("SCM User")) {
                            %>
                            <p class="card-text align-center"><h2>${detailsMap.PendingRfq}</h2></p>
                                <%
                                } else if (session.getAttribute("userType").equals("Approver")) {
                                %>
                            <p class="card-text align-center"><h2>${detailsMap.approvalPending.size()}</h2></p>
                                <%
                                    }
                                %>
                            <a href="#" class="card-link text-white">Details</a>
                            <!--<a href="#" class="card-link text-white">Another link</a>-->
                        </div>
                    </div>

                </div>
                <%                    if (session.getAttribute("userType").equals("SCM User")) {
                %>            
                <div class="span2">
                    <div class="card hoverCSS">
                        <div class="card-body bg-danger text-white">
                            <h4 class="card-title">Released RFP</h4>
                            <p class="card-text"><h2>${detailsMap.ReleasedRfq}</h2></p>
                            <a href="#" class="card-link text-white">Details</a>
                            <!--<a href="#" class="card-link text-white">Another link</a>-->
                        </div>
                    </div>
                </div>
                <div class="span2">
                    <div class="card hoverCSS">
                        <div class="card-body bg-dark text-white">
                            <h4 class="card-title">Awarded RFP</h4>
                            <p class="card-text"><h2>${detailsMap.AwardedRfq}</h2></p>
                            <a href="#" class="card-link text-white">Details</a>
                            <!--<a href="#" class="card-link text-white">Another link</a>-->
                        </div>
                    </div>
                </div>
                <div class="span2">
                    <div class="card hoverCSS">
                        <div class="card-body bg-secondary text-white">
                            <h4 class="card-title">Total Supplier</h4>
                            <p class="card-text"><h2>${detailsMap.SupplierUser.size()}</h2></p>
                            <a href="#" class="card-link text-white">Details</a>
                            <!--<a href="#" class="card-link text-white">Another link</a>-->
                        </div>
                    </div>
                </div>
                <%
                    }
                %>            
            </div>
            <div class="space-10"></div>
            <div class="row-fluid">
                <!--                <div class="span6">
                                    <div class="panel">
                                        <div class="panel-heading">RFP Details</div>
                                        <div class="panel-body">
                                            <div id="piechart" style="display: none"></div>
                                            <div id="piechart" style="display: none;"></div>
                                            <div>
                
                                            </div>
                                        </div>
                                    </div>
                                </div>-->
                <div class="span6">
                    <div class="widget-box">
                        <div class="widget-header">
                            <h4 class="smaller">RFP Details</h4>
                        </div>
                        <div class="widget-body">
                            <div class="widget-main no-padding">
                                <div class="dialogs">													
                                    <table id="rfpTableId" class="table table-bordered table-responsive table-hover">
                                        <thead class="table-header">
                                            <tr>
                                                <th>S.No.</th>
                                                <th>RFQ No.</th>
                                                <th>RFQ Title</th>
                                                <th>RFQ Status</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                if (session.getAttribute("userType").equals("SCM User")) {
                                            %>
                                            <c:forEach var="rfqHeader" items="${detailsMap.headers}" varStatus="status">
                                                <tr>
                                                    <td>${status.count}</td>
                                                    <td><a href="showRfp.do?rfpId=${rfqHeader.rfqid}">RFP_${rfqHeader.rfqid}</a></td>
                                                    <td>${rfqHeader.RFQTitle}</td>
                                                    <td>${rfqHeader.rfqstatus}</td>
                                                </tr>
                                            </c:forEach>
                                            <%
                                            } else if (session.getAttribute("userType").equals("Approver")) {
                                            %>   
                                            <c:forEach var="selectedApproval" items="${detailsMap.selectedApproval}" varStatus="status">
                                                <tr>
                                                    <td>${status.count}</td>
                                                    <td><a href="showRfp.do?rfpId=${selectedApproval.BPaasWorkOrderRFQHeaderRFQID.rfqid}">RFP_${selectedApproval.BPaasWorkOrderRFQHeaderRFQID.rfqid}</a></td>
                                                    <td>${selectedApproval.BPaasWorkOrderRFQHeaderRFQID.RFQTitle}</td>
                                                    <td>${selectedApproval.BPaasWorkOrderRFQHeaderRFQID.rfqstatus}</td>
                                                </tr>
                                            </c:forEach>
                                            <%
                                                }
                                            %>

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="span6">
                    <div class="widget-box">
                        <div class="widget-header">
                            <h4 class="smaller">My Task</h4>
                        </div>
                        <div class="widget-body">
                            <div class="widget-main no-padding">
                                <div class="dialogs">													
                                    <div class="itemdiv dialogdiv">
                                        <div class="user">
                                            <div class="setDate center"><h4>24</h4></div>
                                        </div>
                                        <div class="body">								
                                            <div class="time"><span class="green">Dec</span></div>					
                                            <div class="name"><a href="#">John</a></div>
                                            <div class="text">Raw denim you probably haven&#39;t heard of them jean shorts Austin.</div>
                                        </div>
                                    </div>
                                    <div class="itemdiv dialogdiv">
                                        <div class="user">
                                            <div class="setDate center"><h4>25</h4></div>
                                        </div>
                                        <div class="body">
                                            <div class="time"><span class="green">Dec</span></div>
                                            <div class="name">
                                                <a href="#">Bob</a>
                                                <span class="label label-info arrowed arrowed-in-right">Important</span>
                                            </div>
                                            <div class="text">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque commodo massa sed ipsum porttitor facilisis.</div>
                                        </div>
                                    </div>
                                    <div class="itemdiv dialogdiv">
                                        <div class="user">
                                            <div class="setDate center"><h4>26</h4></div>
                                        </div>
                                        <div class="body">	
                                            <div class="time"><span class="green">Dec</span></div>
                                            <div class="name">
                                                <a href="#">Jim</a>
                                            </div>
                                            <div class="text">Raw denim you probably haven&#39;t heard of them jean shorts Austin.</div>													
                                        </div>
                                    </div>
                                    <div class="itemdiv dialogdiv">
                                        <div class="user">
                                            <div class="setDate center"><h4>27</h4></div>
                                        </div>
                                        <div class="body">						
                                            <div class="time"><span class="green">Jan</span></div>
                                            <div class="name"><a href="#">Jim</a></div>
                                            <div class="text">Raw denim you probably haven&#39;t heard of them jean shorts Austin.</div>													
                                        </div>
                                    </div>																							
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--                <div class="span6">
                                    <div class="infobox-container align-center">
                                        <div class="infobox infobox-blue">
                                            <div class="infobox-icon"><i class="icon-table"></i></div>
                                            <div class="infobox-data">
                                                <span class="infobox-data-number" id="totalDiv">${jsonObj.get('TotalRfq')}</span>
                                                <a><div class="infobox-content">Total RFP</div></a>
                                            </div>
                                                                                <div class="stat stat-success">8%</div>
                                        </div>
                                        <div class="infobox infobox-green">
                                            <div class="infobox-icon"><i class="icon-check"></i></div>
                                            <div class="infobox-data">
                                                <span class="infobox-data-number" id="approvedDiv">${jsonObj.get('ApprovedRfq')}</span>
                                                <a><div class="infobox-content">Approved RFP</div></a>
                                            </div>									
                                        </div>
                                        <div class="infobox infobox-pink">
                                            <div class="infobox-icon">
                                                <i class="icon-remove"></i>
                                            </div>
                                            <div class="infobox-data">
                                                <span class="infobox-data-number" id="pendingDiv">${jsonObj.get('PendingRfq')}</span>
                                                <a><div class="infobox-content">Pending RFP</div></a>
                                            </div>									
                                        </div>
                                        <div class="infobox infobox-red ">
                                            <div class="infobox-icon"><i class="icon-beaker"></i></div>
                                            <div class="infobox-data">
                                                <span class="infobox-data-number">7</span>
                                                <a><div class="infobox-content">Released</div></a>
                                            </div>
                                        </div>        
                                        <div class="infobox infobox-orange2 ">
                                            <div class="infobox-chart">
                                                <span class="sparkline" data-values="196,128,202,177,154,94,100,170,224"></span>
                                            </div>
                                            <div class="infobox-data">
                                                <span class="infobox-data-number">6,251</span>
                                                <a><div class="infobox-content">Rewarded</div></a>
                                            </div>
                                            <div class="badge badge-success">7.2%<i class="icon-arrow-up"></i>
                                        </div>
                                    </div>        
                                </div>-->
            </div>

        </div>
        <!--            <div class="row-fluid">
                        <div class="span12">
                            <div class="infobox-container align-center">
        
                                <div class="infobox infobox-red ">
                                    <div class="infobox-icon"><i class="icon-beaker"></i></div>
                                    <div class="infobox-data">
                                        <span class="infobox-data-number">7</span>
                                        <a><div class="infobox-content">Released</div></a>
                                    </div>
                                </div>
                                <div class="infobox infobox-orange2 ">
                                    <div class="infobox-chart">
                                        <span class="sparkline" data-values="196,128,202,177,154,94,100,170,224"></span>
                                    </div>
                                    <div class="infobox-data">
                                        <span class="infobox-data-number">6,251</span>
                                        <a><div class="infobox-content">Rewarded</div></a>
                                    </div>
                                    <div class="badge badge-success">7.2%<i class="icon-arrow-up"></i>
                                    </div>
                                </div>
                            </div>
        
                        </div>
                    </div>-->

        <div class="space-10"></div>
        <!--                    <div class="row-fluid">
                                <div class="span12">
                                    <table id="sample-table-1" class="table table-striped table-bordered table-hover">
                                        <thead>
                                            <tr>
                                                <th class="center">Contact No</th>
                                                <th>Material Name</th>
                                                <th>Quantity</th>
                                                <th>Units</th>
                                                <th>Delivery Date Time</th>
                                                <th>Loading Point</th>
                                                <th>UnLoading Point</th>
                                                <th>Last Modified</th>
                                            </tr>
                                        </thead>
        
                                        <tbody>
                                            <tr>
                                                <td class="center">9650705593</td>
                                                <td><a href="#">Cement</a></td>
                                                <td>55</td>
                                                <td>Kg</td>
                                                <td>Jan 21</td>
                                                <td>Gorakhpur</td>												
                                                <td>New Delhi</td>
                                                <td>25 Dec</td>
                                            </tr>
                                            <tr>
                                                <td class="center">9650705593</td>
                                                <td><a href="#">Steel</a></td>
                                                <td>55</td>
                                                <td>Kg</td>
                                                <td>Jan 21</td>
                                                <td>Gorakhpur</td>												
                                                <td>New Delhi</td>
                                                <td>25 Dec</td>
                                            </tr>
                                            <tr>
                                                <td class="center">980080943</td>
                                                <td><a href="#">Tiles</a></td>
                                                <td>55</td>
                                                <td>Kg</td>
                                                <td>Jan 21</td>
                                                <td>Gorakhpur</td>												
                                                <td>New Delhi</td>
                                                <td>25 Dec</td>
                                            </tr>
                                            <tr>
                                                <td class="center">9650705593</td>
                                                <td><a href="#">Steel</a></td>
                                                <td>55</td>
                                                <td>Kg</td>
                                                <td>Jan 21</td>
                                                <td>Gorakhpur</td>												
                                                <td>New Delhi</td>
                                                <td>25 Dec</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>-->
        <!-- <div class="row-fluid">
                <div class="span7">
                        <div class="widget-box transparent">
                                <div class="widget-header widget-header-flat">
                                        <h4 class="lighter">
                                                <i class="icon-signal"></i>My Request Summary										
                                        </h4>

                                        <div class="widget-toolbar">
                                                <a href="#" data-action="collapse">
                                                        <i class="icon-chevron-up"></i>
                                                </a>
                                        </div>
                                </div>
                                <div class="widget-body">
                                        <div class="widget-main padding-4">
                                                                                                                
                                        </div>
                                </div>
                        </div>
                </div>
        </div> -->
        <input type="hidden" id="totalRFP" value="${detailsMap.headers.size()}">
        <input type="hidden" id="approvedRFP" value="${detailsMap.ApprovedRfq}">
        <input type="hidden" id="pendingRFP" value="${detailsMap.PendingRfq}">
        <input type="hidden" id="releasedRFP" value="${detailsMap.ReleasedRfq}">
        <input type="hidden" id="awardedRFP" value="${detailsMap.AwardedRfq}">
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
