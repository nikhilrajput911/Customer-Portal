
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="JSON.JSONObject, JSON.JSONArray,JSON.JSONException" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>Audit Log Report</title>


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


        <link rel="stylesheet" href="css/buttons.dataTables.min.css" type="text/css"/>

        <link rel="stylesheet" href="css/dataTables1.10.18.min.css" />

        <script src="js/dataTables1.10.18.min.js"></script>

        <script src="js/dataTables.buttons.min.js" type="text/javascript"></script>
        <script src="js/buttons.flash.min.js" type="text/javascript"></script>
        <script src="js/jszip.min.js" type="text/javascript"></script>
        <script src="js/pdfmake.min.js" type="text/javascript"></script>
        <script src="js/vfs_fonts.js" type="text/javascript"></script>
        <script src="js/buttons.html5.min.js" type="text/javascript"></script>
        <script src="js/buttons.print.min.js" type="text/javascript"></script>

        <!--        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.18/css/jquery.dataTables.min.css" type="text/css"/>
        
                <script src="https://cdn.datatables.net/1.10.18/js/jquery.dataTables.min.js" type="text/javascript"></script>-->

        <!--        <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet" />
                <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>-->

        <link rel="stylesheet" href="css/jquery-ui-1.12.1.css">
        <script src="js/jquery-ui-1.12.1.js"></script>

        <link rel="stylesheet" href="css/customer-notification.css" />
        <script src="js/customer-notification.js"></script>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

        <script src="js/customer.js"></script>

        <style>
        </style>

        <script>
            var table = "";
            $(document).ready(function() {
//                $('.select-multiple-box').select2({
//                    placeholder: "Select Project(s)"
//                });
                var set_image = $("#set_image").prop("src").toString().split(",");
                if (set_image[1] === "NotFound")
                {
                    $("#default_image_a").css("display", "block");
                    $("#set_image_a").css("display", "none");
                }
//
//                table = $('#download_document_report_table').DataTable({
//                    dom: 'Bfrtip',
//                    buttons: [
//                        'excel', 'print'
//                    ]
//                });

                $("#generate_mail_report").click(function() {

                    var fromDate = $("#from_report1").val();
                    var toDate = $("#to_report1").val();
                    var mailtype = $("#mailtype").val();

                    if (fromDate === "")
                    {
                        $("#from_report1").css("border-color", "red");
                        return false;
                    }
                    else
                    {
                        $("#from_report1").css("border-color", "");
                    }
                    if (toDate === "")
                    {
                        $("#to_report1").css("border-color", "red");
                        return false;
                    }
                    else
                    {
                        $("#to_report1").css("border-color", "");
                    }
                    if (mailtype === "--Select--")
                    {
                        $("#mailtype").css("border-color", "red");
                        return false;
                    }
                    else
                    {
                        $("#mailtype").css("border-color", "");
                    }

                    $.ajax(
                            {
                                type: "GET",
                                url: "rfeCont.do",
                                async: true,
                                data:
                                        {
                                            "reqFrom": "CustomerMailReport",
                                            "fromDate": fromDate,
                                            "toDate": toDate,
                                            "mailType": mailtype
                                        },
                                dataType: "json",
                                complete: function(responseJson)
                                {
                                    var obj = $.parseJSON(responseJson.responseText);
//                        alert(obj.status);
                                    console.log(obj.Records);
                                    var row = '';
                                    for (var i = 0; i < obj.Records; i++)
                                    {
                                        row += "<tr><td>" + (i + 1) + "</td><td>" + obj.Result[i].CUSTOMER_NAME + "</td><td>" + obj.Result[i].PROJECT_NAME + "</td><td>" + obj.Result[i].TO_ADD + "</td><td>" + obj.Result[i].CC_ADD + "</td><td>" + obj.Result[i].DOC_NAME + "</td><td>" + obj.Result[i].SENT_BY + "</td><td>" + obj.Result[i].SENT_ON + "</td></tr>";
                                    }



//                                    $("#Search_document_table_div").css("display", "block");
                                    $("#download_document_report_table").children("tbody").html(row);


                                    if ($.fn.DataTable.isDataTable('#download_document_report_table')) {
//                                        alert("if");


                                        table2.fnDestroy();

                                        $("#download_document_report_table").children('tbody').html(row);

                                        var row_count = obj.Records;
                                        if (row_count > 0)
                                        {
                                            table2 = $('#download_document_report_table').dataTable({
                                                dom: 'Bfrtip',
                                                buttons: [
                                                    'excel', 'print'
                                                ]
                                            });
                                        }
                                        else
                                        {
                                            table2 = $('#download_document_report_table').dataTable();
                                        }

                                    }
                                    else
                                    {
//                                        alert("else");
                                        var row_count = obj.Records;
                                        if (row_count > 0)
                                        {
                                            table2 = $('#download_document_report_table').dataTable({
                                                dom: 'Bfrtip',
                                                buttons: [
                                                    
                                                        'excel', 'print'

                                                ]
                                            });
                                        }
                                        else
                                        {
                                            table2 = $('#download_document_report_table').dataTable();
                                        }


                                    }

                                }
                            });

                });


                $("#from_report1").datepicker({
                    dateFormat: 'dd-mm-yy',
                    onSelect: function(dateText, inst) {
                        $("#to_report1").datepicker("option", "minDate", $("#from_report1").datepicker("getDate"));
                    }
                });

                $("#to_report1").datepicker({dateFormat: 'dd-mm-yy'});

            });
            function convertDate(d) {
                var parts = d.split(" ");
                var months = {Jan: "01", Feb: "02", Mar: "03", Apr: "04", May: "05", Jun: "06", Jul: "07", Aug: "08", Sep: "09", Oct: "10", Nov: "11", Dec: "12"};
                return parts[2] + "-" + months[parts[1]] + "-" + parts[5];
            }

        </script>

    </head>

    <body>

        <%@include file = "templatecustomer.jsp" %>
        <div class="breadcrumbs" id="breadcrumbs">
            <ul class="breadcrumb">
                <li class="align-center">
                    <h4>Reports</h4>

                </li>
                <!--<li class="active">Response Comment</li>-->						
            </ul>
            <div class="nav-search" id="nav-search">
                <a href="redirectcustomerhome.do"><h5><b>Go to Home</b></h5></a>
            </div>
        </div>
        <div class="page-content">
            <ul class="nav nav-tabs">
                <li><a href="customerreport.do">Audit Log Report</a></li>
                <li><a href="customerprofilereport.do">Customer Profile Update Report</a></li>
                <li><a href="customeradmintrackingreport.do">Admin Tracking Report</a></li>
                <li class="active"><a data-toggle="pill" href="#mailreport">Mail Report</a></li>
            </ul>
            <!--<a href="customerprofilereport.do">Customer Profile Update Report</a>-->
            <!--<div class="panel panel-shadow">-->
            <!--<div class="panel-body">-->
            <div class="tab-content">
                <div id="mailreport" class="tab-pane fade in active">
                    <div class="row-fluid">
                        <div class="span6">
                            <div class="control-group">
                                <label class="control-label" for="">From</label>
                                <div class="controls">
                                    <div class="">
                                        <input type="text" placeholder="DD-MM-YYYY" id="from_report1" name="from_report1" class="span10 search-doc-form-field" style="background-color: white !important;">
                                        <div id='error' style='color:red;'></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="span6">
                            <div class="control-group">
                                <label class="control-label" for="">To</label>
                                <div class="controls">
                                    <div class="">
                                        <input type="text" placeholder="DD-MM-YYYY" id="to_report1" name="to_report1" class="span10 search-doc-form-field" style="background-color: white !important;">
                                        <div id='error' style='color:red;'></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row-fluid">
                        <div class="span6">
                            <div class="control-group">
                                <label class="control-label" for="">Mail Type</label>
                                <div class="controls">
                                    <div class="">
                                        <select id="mailtype" name="mailtype" class="span10 subuser-form-field" required>
                                            <option>--Select--</option>
                                            <option>All</option>
                                            <option>Manual</option>
                                            <option>Auto</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="row-fluid">
                        <div class="span6">
                            <input type="button" value="Generate" class="btn btn-pink btn-small" id="generate_mail_report">
                        </div>
                    </div>
                    <div class="space"></div>
                    <div class="row-fluid">
                        <div class="span12 table-responsive">
                            <table style="width: 100%;" id="download_document_report_table" class="table table-striped table-bordered table-hover">
                                <thead>
                                <th>S.No.</th>
                                <th>Customer Name</th>
                                <th>Project Name</th>
                                <th>TO Address</th>
                                <th>CC Address</th>
                                <th>Document Name</th>
                                <th>Mail Sent By</th>
                                <th>Mail Sent On</th>
                                
                                </thead>
                                <tbody>

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div id="custprofileupdatereport" class="tab-pane">

                    <div class="space"></div>

                </div>
            </div>
            <!--</div>-->
            <!--</div>-->
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
