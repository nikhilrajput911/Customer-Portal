
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
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>

        <link href="css/bootstrap.min.css" rel="stylesheet" />
        <link href="css/bootstrap-responsive.min.css" rel="stylesheet" />
        <!--<link rel="stylesheet" href="css/font-awesome.min.css" />-->
        <!--<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />-->		

        <link rel="stylesheet" href="css/googleapis-font.css" />
        <link rel="stylesheet" href="css/ace.min.css" />
        <link rel="stylesheet" href="css/ace-responsive.min.css" />
        <link rel="stylesheet" href="css/ace-skins.min.css" />
        <link rel="stylesheet" href="css/main.css" />

        <link rel="stylesheet" href="css/loader.css" />

        <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>-->
        <!--<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>-->

        <!--<link rel="stylesheet" href="https://cdn.datatables.net/1.10.18/css/jquery.dataTables.min.css" type="text/css"/>-->

        <!--<script src="https://cdn.datatables.net/1.10.18/js/jquery.dataTables.min.js" type="text/javascript"></script>-->

        <script src="js/jquery.min.js"></script>
        <link rel="stylesheet" href="css/dataTables1.10.18.min.css" />
        <script src="js/dataTables1.10.18.min.js"></script>

        <link rel="stylesheet" href="css/jquery-ui-1.12.1.css">
        <script src="js/jquery-ui-1.12.1.js"></script>

        <link rel="stylesheet" href="css/font-awesome2.min.css" />

        <link rel="stylesheet" href="css/customer-notification.css" />
        <script src="js/customer-notification.js"></script>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

        <script src="js/customer.js"></script>

        <style>
            .align-right {
                text-align: right;
                float: right;
            }
        </style>
        <script>
            $(document).ready(function() {

                var set_image = $("#set_image").prop("src").toString().split(",");
                if (set_image[1] === "NotFound")
                {
                    $("#default_image_a").css("display", "block");
                    $("#set_image_a").css("display", "none");
                }

                var today = new Date();
                var day = today.getDate() > 9 ? today.getDate() : "0" + today.getDate();
                console.log(day);
                var month = (today.getMonth() + 1) > 9 ? (today.getMonth() + 1) : "0" + (today.getMonth() + 1);
                console.log(month);
                var year = today.getFullYear();

//                $("#statement_from_date").attr("max", year + "-" + month + "-" + day);
//                $("#statement_to_date").attr("max", year + "-" + month + "-" + day);

                $("#statementcustomerId").change(function() {
                    var customerId = $(this).val();
                    console.log(customerId);
                    $("#sessionCustomerId").val(customerId);
                });

//                $("#statement_from_date").blur(function()
//                {
//                    $("#statement_to_date").attr("min", $('#statement_from_date').val());
//                    $("#statement_to_date").val('');
//                });

                $("#statement_to_date").change(function() {

                    var uploadFromDate = $('#statement_from_date').val();

                    console.log(uploadFromDate);

                    if (uploadFromDate === "")
                    {
                        //alert("Please Select Open Date First!");
                        bootbox.dialog("Please enter From Date First!", [{"label": "ok", "class": "btn-small btn-primary"}]);
                        $('#statement_to_date').val('');
                        return false;
                    }

                    else
                    {
                        console.log("else");
                    }
                });

                $("#doctype").change(function() {
                    var doctype = $("#doctype option:selected").text();
                    console.log("doctype: " + doctype);

                    if (doctype === "AR Listing")
                    {
                        $("#transmonth").val("Month");
                        $("#transyear").val("Year");
                        $("#statement_from_date").val("");
                        $("#statement_to_date").val("");

                        $("#transmonth").prop("disabled", true);
                        $("#transyear").prop("disabled", true);
                        $("#statement_from_date").prop("disabled", true);
                        $("#statement_to_date").prop("disabled", true);
                    }
                    else
                    {
                        $("#transmonth").val("Month");
                        $("#transyear").val("Year");
                        $("#statement_from_date").val("");
                        $("#statement_to_date").val("");

                        $("#transmonth").prop("disabled", false);
                        $("#transyear").prop("disabled", false);
                        $("#statement_from_date").prop("disabled", false);
                        $("#statement_to_date").prop("disabled", false);
                    }
                });

                $("#transmonth").change(function() {

                    $("#statement_from_date").val("");
                    $("#statement_to_date").val("");

                    $("#statement_from_date").prop("disabled", true);
                    $("#statement_to_date").prop("disabled", true);
                });

                $("#transyear").change(function() {

                    $("#statement_from_date").val("");
                    $("#statement_to_date").val("");

                    $("#statement_from_date").prop("disabled", true);
                    $("#statement_to_date").prop("disabled", true);
                });

                $("#statement_from_date").change(function() {

                    $("#transmonth").val("Month");
                    $("#transyear").val("Year");

                    $("#transmonth").prop("disabled", true);
                    $("#transyear").prop("disabled", true);
                });

                $("#statement_to_date").change(function() {

                    $("#transmonth").val("Month");
                    $("#transyear").val("Year");

                    $("#transmonth").prop("disabled", true);
                    $("#transyear").prop("disabled", true);
                });
//                $("#overlay").css("display", "none");


                $("#statement_from_date").keyup(function() {
//                    console.log($(this).val());
                    var from_date = $(this).val();
                    if (from_date !== "")
                    {
//                        console.log("len: " + from_date.length);
                        if (from_date.length === 2)
                        {
                            if (from_date > 31)
                            {
                                alert("Enter valid day!");
                                return false;
                            }
                            $(this).val(from_date + "-");
                        }
                        if (from_date.length === 5)
                        {
                            var month = from_date.substr(from_date.indexOf("-") + 1, 2);
                            console.log("month: " + month);
                            if (month > 12)
                            {
                                alert("Enter valid month!");
                                return false;
                            }
                            $(this).val(from_date + "-");
                        }
                        if (from_date.length > 10)
                        {

                        }
                    }
                });

                $("#statement_to_date").keyup(function() {
//                    console.log($(this).val());
                    var from_date = $(this).val();
                    if (from_date !== "")
                    {
//                        console.log("len: " + from_date.length);
                        if (from_date.length === 2)
                        {
                            if (from_date > 31)
                            {
                                alert("Enter valid day!");
                                return false;
                            }
                            $(this).val(from_date + "-");
                        }
                        if (from_date.length === 5)
                        {
                            var month = from_date.substr(from_date.indexOf("-") + 1, 2);
                            console.log("month: " + month);
                            if (month > 12)
                            {
                                alert("Enter valid month!");
                                return false;
                            }
                            $(this).val(from_date + "-");
                        }
                        if (from_date.length > 10)
                        {

                        }
                    }
                });

                $("#statement_from_date").blur(function() {
//                    console.log($(this).val());
                    var from_date = $(this).val();
                    if (from_date !== "")
                    {
                        if (from_date.length > 10)
                        {
                            alert("Enter valid date!");
                            return false;
                        }
                        var from_min_date = $("#statement_from_date").datepicker("option", "minDate");
                        console.log("from_min_date: " + from_min_date);

                        var today = new Date();
                        console.log("today: " + today);

                        var from = from_date.split("-");
                        var fromDateEntered = new Date(from[2], from[1] - 1, from[0]);
                        console.log("entered: " + fromDateEntered);

                        var parseMinDate = Date.parse(from_min_date);
                        var parseTodayDate = Date.parse(today);
                        var parseValueEntered = Date.parse(fromDateEntered);

                        console.log("parseMinDate: " + parseMinDate);
                        console.log("parseValueEntered " + parseValueEntered);
                        console.log("parseTodayDate " + parseTodayDate);
                        console.log("parseValueEntered - parseMinDate : " + (parseValueEntered - parseMinDate));
                        console.log(" : " + (parseValueEntered < parseMinDate));

                        if (parseValueEntered < parseMinDate)
                        {
                            alert('From date must be equal to or greater than 01-09-2018');
                            return false;
                        }
                        if (parseValueEntered > parseTodayDate)
                        {
                            alert('From date can not be future date!');
                            return false;
                        }
                        $("#statement_to_date").datepicker("option", "minDate", $("#statement_from_date").datepicker("getDate"));
                    }
                });


                $("#statement_to_date").blur(function() {
//                    console.log($(this).val());
                    var from_date = $(this).val();
                    if (from_date !== "")
                    {
                        if (from_date.length > 10)
                        {
                            alert("Enter valid date!");
                            return false;
                        }
                        var from_min_date = $("#statement_to_date").datepicker("option", "minDate");
                        console.log("from_min_date: " + from_min_date);

                        var today = new Date();
                        console.log("today: " + today);

                        var from = from_date.split("-");
                        var fromDateEntered = new Date(from[2], from[1] - 1, from[0]);
                        console.log("entered: " + fromDateEntered);

                        var parseMinDate = Date.parse(from_min_date);
                        var parseTodayDate = Date.parse(today);
                        var parseValueEntered = Date.parse(fromDateEntered);

                        console.log("parseMinDate: " + parseMinDate);
                        console.log("parseValueEntered " + parseValueEntered);
                        console.log("parseTodayDate " + parseTodayDate);
                        console.log("parseValueEntered - parseMinDate : " + (parseValueEntered - parseMinDate));
                        console.log(" : " + (parseValueEntered < parseMinDate));

                        if (parseValueEntered < parseMinDate)
                        {
                            alert('To date must be equal to or greater than from date');
                            return false;
                        }
                        if (parseValueEntered > parseTodayDate)
                        {
                            alert('From date can not be future date!');
                            return false;
                        }

                    }
                });

                $("#statement_from_date").datepicker({
                    dateFormat: 'dd-mm-yy',
                    maxDate: 0,
                    minDate: new Date('2018/09/01'),
                    onSelect: function(dateText, inst) {
                        $("#statement_to_date").datepicker("option", "minDate", $("#statement_from_date").datepicker("getDate"));
                    }
                });

                $("#statement_to_date").datepicker({dateFormat: 'dd-mm-yy', maxDate: 0});
            });
        </script>
    </head>

    <body onload="fetchDocOnLoad();
            getYears();">

        <%@include file = "templatecustomer.jsp" %>

        <!--        <div id="overlay">
                    <div id="loader"></div>
                </div>-->


        <input type="hidden" id="sessionCustomerId" value="${customerid}">
        <input type="hidden" id="userRole" value="${userRole}">
        <input type="hidden" name="dmsip" id="dmsip" value="${dmsip}">
        <div class="breadcrumbs" id="breadcrumbs">
            <ul class="breadcrumb">
                <li class="align-center">
                    <h4>My Statement</h4>

                </li>
                <!--<li class="active">Response Comment</li>-->						
            </ul>
            <div class="nav-search" id="nav-search">
                <a href="redirectcustomerhome.do"><h5><b>Go to Home</b></h5></a>
            </div>
        </div>
        <div class="page-content">
            <div class="panel panel-shadow">
                <div class="panel-body">
                    <div class="row-fluid">
                        <div class="span12">
                            <form class="form-vertical">
                                <div class="row-fluid">
                                    <%                    if (session.getAttribute("userRole").equals("Admin")) {
                                    %>
                                    <div class="span6">
                                        <div class="control-group">
                                            <label class="control-label" for="statementcustomerId">Customer Name</label>
                                            <div class="controls">
                                                <div class="">
                                                    <select id="statementcustomerId" name="statementcustomerId" class="span10 search-doc-form-field" required>
                                                        <option>--Select--</option>
                                                        <c:forEach var="customer" items="${customerList}" varStatus="status">
                                                            <option value="${customer.customercode}">${customer.customername}</option>
                                                        </c:forEach>
                                                    </select>

                                                </div>
                                            </div>																
                                        </div>
                                    </div>
                                    <%                                        }
                                    %>
                                </div>

                                <div class="row-fluid">
                                    <div class="span6">
                                        <div class="control-group">
                                            <label class="control-label" for="doctype">Document Name</label>
                                            <div class="controls">
                                                <div class="">
                                                    <select id="doctype" name="doctype" class="span10 search-doc-form-field">
                                                        <!--<option value="All">All</option>-->
                                                        <c:forEach var="all" items="${allList}" varStatus="status">
                                                            <option value="${all}">${all}</option>
                                                        </c:forEach>
                                                        <%                    if (session.getAttribute("userRole").equals("Admin") || session.getAttribute("userRole").equals("Company Admin")) {
                                                        %>
                                                        <c:forEach var="doc" items="${docList}" varStatus="status">
                                                            <option value="${doc}">${doc}</option>
                                                        </c:forEach>
                                                        <%} else {
                                                        %>
                                                        <c:forEach var="group" items="${docGroupList}" varStatus="status">
                                                            <option value="${group.groupalias}">${group.groupname}</option>
                                                        </c:forEach>    
                                                        <%
                                                            }%>
                                                        <!--                                                        <option value="AR Listing">AR Listing</option>
                                                                                                                <option value="SOA">Statement of Account</option>-->
                                                    </select>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="span3">
                                        <div class="control-group">
                                            <label class="control-label" for="transperiod">Transaction Period (Month)</label>
                                            <div class="controls">
                                                <div class="">
                                                    <select id="transmonth" name="transperiod" class="span10 search-doc-form-field">
                                                        <option>Month</option>
                                                        <option value="1">Jan</option>
                                                        <option value="2">Feb</option>
                                                        <option value="3">Mar</option>
                                                        <option value="4">Apr</option>
                                                        <option value="5">May</option>
                                                        <option value="6">June</option>
                                                        <option value="7">July</option>
                                                        <option value="8">Aug</option>
                                                        <option value="9">Sep</option>
                                                        <option value="10">Oct</option>
                                                        <option value="11">Nov</option>
                                                        <option value="12">Dec</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="span3">
                                        <div class="control-group">
                                            <label class="control-label" for="transperiod">Transaction Period (Year)</label>

                                            <div class="controls">
                                                <div class="">
                                                    <select id="transyear" name="transperiod" class="span10 search-doc-form-field">
                                                        <option>Year</option>

                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row-fluid">
                                    <div class="span6">
                                        <div class="control-group">
                                            <label class="control-label" for="">Document Date (From)</label>
                                            <div class="controls">
                                                <div class="">
                                                    <!--<input type="date" id="statement_from_date" name="statement_from_date" class="span10 search-doc-form-field" onkeydown="return false">-->
                                                    <!--                                                    <input type="text" placeholder="DD-MM-YYYY" onfocus="(this.type='date')" id="statement_from_date" name="statement_from_date" class="span10 search-doc-form-field" onkeydown="return false">-->
                                                    <input type="text" placeholder="DD-MM-YYYY" id="statement_from_date" name="statement_from_date" class="span10 search-doc-form-field">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="span6">
                                        <div class="control-group">
                                            <label class="control-label" for="">To</label>
                                            <div class="controls">
                                                <div class="">
                                                    <!--<input type="date" id="statement_to_date" name="statement_to_date" class="span11 search-doc-form-field" onkeydown="return false">-->
                                                    <!--                                                    <input type="text" placeholder="DD-MM-YYYY" onfocus="(this.type='date')" id="statement_to_date" name="statement_to_date" class="span11 search-doc-form-field" onkeydown="return false">-->
                                                    <input type="text" placeholder="DD-MM-YYYY" id="statement_to_date" name="statement_to_date" class="span11 search-doc-form-field">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row-fluid">
                                    <div class="span6">
                                        <input type="button" value="Get Statement" id="getstatementbtn" class="btn btn-purple">
                                        <!--<input type="button" value="Send over E-Mail" id="sendoveremailbtn" class="btn btn-success">-->
                                        <input type="button" value="Clear Criteria" id="statementclearcriteria" class="btn btn-pink">
                                    </div>
                                    <div class="span6">
                                        <input type="button" value="Download All Document" id="advsearchdownloadall" onclick="downloadMultipleDoc();
                                                saveMultipleDoc();" class="btn btn-danger align-right" style="display: none;">
                                    </div>
                                </div><br>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!--- Showing Documents List --->
        <div class="page-content">
            <div class="row-fluid table-responsive" id="myOrderDocumentTableDivId" style="display: none;">
                <div class="span12">
                    <table id="Search_document_table" style="align:center" class="table table-striped table-bordered" >
                        <thead>
                            <tr>
                                <th>S.No</th>
                                <th hidden>Document Index</th>
                                <th hidden>Image Index</th>
                                <th>Document Number</th>
                                <th>Document Name</th>  
                                <th>Document Type</th>  								
                                <th>Document Date</th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody></tbody>

                    </table>
                </div>
            </div>
        </div>


        <!--- Documet Listing table Ends here --->



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

    function fetchDocOnLoad()
    {
        if ($("#userRole").val() !== "Admin")
        {
            FetchAllDocs();
        }
    }

    function FetchAllDocs()
    {
        var length = $('#doctype').children('option').length;
        console.log("length: " + length);
        if (length !== 0)
        {
            $("#getstatementbtn").prop("disabled", false);
            
            console.log("Inside FetchAllDocs...");
            var customerId = $("#sessionCustomerId").val();

            var xmlInput = "<InputCriteria>";
            xmlInput += "<CustomerId>" + customerId + "</CustomerId>";
            xmlInput += "</InputCriteria>";

            var dmsip = $("#dmsip").val();

            var URLParam = 'InputXML=' + xmlInput + '&RequestType=statement';
            console.log("URLParam: " + URLParam);

            var serviceUrl = dmsip + "/WebServiceCall/CallWebService";
            console.log("serviceUrl: " + serviceUrl);
//        CustomerPortalapp/ng/search/statement

            $.ajax({
                type: "POST",
                url: serviceUrl,
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                dataType: "xml",
                data: URLParam,
                success: function(data, textStatus, jqXHR) {
                    console.log("success FetchAllDocs: " + data);
                    findDocument(data);

                }
            });
        }
        else
        {
            $("#getstatementbtn").prop("disabled", true);
        }
    }
    var multipleDownloadDataArray = "";
    function findDocument(xmlre)
    {
        var xmlString = XMLToString(xmlre); //Convert the XML Object to a String
        var xmlDoc = $.parseXML(xmlString); //Parse the XML String to get data
        var $xml = $(xmlDoc);
        var $ProjectDetails = $xml.find('DocumentDetails');


        var DocIndex = [];
        var DocName = [];
        var DocType = [];
        var DocExt = [];
        var DocImgIndex = [];
        var DocInsertDate = [];

        var $DocumentIndex = $ProjectDetails.find('DocumentIndex').each(function() {
//        alert($(this).text());
            DocIndex.push($(this).text());
        });
        var $DocumentName = $ProjectDetails.find('DocumentName').each(function() {
//        alert($(this).text());
            DocName.push($(this).text());
        });
        var $DocumentType = $ProjectDetails.find('DocumentType').each(function() {
//        alert($(this).text());
            DocType.push($(this).text());
        });
        var $Extension = $ProjectDetails.find('Extension').each(function() {
//        alert($(this).text());
            DocExt.push($(this).text());
        });
        var $ImageIndex = $ProjectDetails.find('ImageIndex').each(function() {
//        alert($(this).text());
            DocImgIndex.push($(this).text());
        });
        var $InsertDate = $ProjectDetails.find('InsertDate').each(function() {
//        alert($(this).text());
            var convertedDate = $(this).text().replace(/(\d{2})-(\d{2})-(\d{4})/, "$1/$2/$3");
            console.log("Converted Date:" + convertedDate);
            DocInsertDate.push(convertedDate);
        });
        var row = "";

        if (DocIndex.length > 0)
        {
            $("#advsearchdownloadall").css("display", "block");
        }
        else
        {
            $("#advsearchdownloadall").css("display", "none");
        }

        $("#Search_document_table").children("tbody").html("");

        var xmlInputDownload = "";
        var n = "N";
        var y = "Y";
        docIndexForDownloadAll = "";

        for (var i = 0; i < DocIndex.length; i++)
        {

            var temp = DocIndex[i] + "," + DocName[i] + "," + DocExt[i];
            multipleDownloadDataArray = multipleDownloadDataArray + temp + "<>";

            row += "<tr><td>" + (i + 1) + "</td><td hidden>" + DocIndex[i] + "</td><td>" + DocName[i] + "</td><td>" + DocType[i] + "</td><td>" + DocExt[i] + "</td><td hidden>" + DocImgIndex[i] + "</td><td>" + DocInsertDate[i] + "</td><td class='center'><a href='#' onclick=\"downloadSingleDoc(" + DocIndex[i] + ",'" + y + "');\" title='View Document'><i class='fa fa-eye fa-2x'></i></a></td><td class='center'><a href='#' onclick=\"downloadSingleDoc(" + DocIndex[i] + ", '" + n + "');customerAuditReport(" + DocIndex[i] + ",'" + DocName[i] + "','" + DocExt[i] + "','" + y + "');\" title='Download Document'><i class='fa fa-download fa-2x'></i></a></td></tr>";


            if (i === 0)
            {
                docIndexForDownloadAll = docIndexForDownloadAll + DocIndex[i];
            }
            else
            {
                docIndexForDownloadAll = docIndexForDownloadAll + "," + DocIndex[i];
            }

            xmlInputDownload += "<DocumentDetails>";
            xmlInputDownload += "<DocumentIndex>" + DocIndex[i] + "</DocumentIndex>";
            xmlInputDownload += "</DocumentDetails>";
        }
        console.log("xmlInputDownload: " + xmlInputDownload);

        console.log("docIndexForDownloadAll: " + docIndexForDownloadAll);

        xmlInputForDownloadAll = "<InputCriteria>";
        xmlInputForDownloadAll += xmlInputDownload;
        xmlInputForDownloadAll += "</InputCriteria>";
        console.log("xmlInputForDownloadAll: " + xmlInputForDownloadAll);

        $("#myOrderDocumentTableDivId").css("display", "block");
        console.log("Search_document_table row: " + row);

        $("#Search_document_table tbody").append(row);

        if ($.fn.DataTable.isDataTable('#Search_document_table')) {
            table.fnDestroy();
            $("#Search_document_table").children('tbody').html(row);
            table = $('#Search_document_table').dataTable();
            table.fnReloadAjax();
            table = $('#Search_document_table').dataTable();
        }
        else
        {
            table = $('#Search_document_table').dataTable();
        }
    }


    $("#getstatementbtn").click(function() {
        console.log("Inside getstatementbtn Click");


        var doctype = $("#doctype").val();
        var docTypeText = $("#doctype option:selected").text();
        console.log("doctype: " + doctype);
        console.log("docTypeText " + docTypeText);

        var transmonth = $("#transmonth").val();
        var transyear = $("#transyear").val();
        var statement_from_date = $("#statement_from_date").val();
        var statement_to_date = $("#statement_to_date").val();
        var role = $("#userRole").val();

        console.log(role);
        console.log(statement_from_date);
        console.log(statement_to_date);
        console.log(transmonth);
        console.log(transyear);

        var customerid = "";
        if (role === "Admin")
        {
            customerid = $("#statementcustomerId").val();
            if (customerid === "--Select--" || customerid === "")
            {
                bootbox.dialog("Please Select Customer!", [{
                        "label": "ok",
                        "class": "btn-small btn-primary"
                    }]);
                $("#statementcustomerId").css("border-color", "red");
                return false;
            }
            else
            {
                $("#statementcustomerId").css("border-color", "");
            }
        }
        if (docTypeText === "Statement of Account")
        {
//            $("#transmonth").val("Month");
//            $("#transyear").val("Year");
//            $("#statement_from_date").val("");
//            $("#statement_to_date").val("");
//            
//            $("#transmonth").prop("disabled", false);
//            $("#transyear").prop("disabled", false);
//            $("#statement_from_date").prop("disabled", false);
//            $("#statement_to_date").prop("disabled", false);

            if (((transmonth === "" || transmonth === "Month") && (transyear === "" || transyear === "Year"))
                    && (statement_from_date === "" && statement_to_date === ""))
            {
                bootbox.dialog("Please enter date range or transaction period!", [{
                        "label": "ok",
                        "class": "btn-small btn-primary"
                    }]);
                return false;
            }
            if (!(transmonth === "" || transmonth === "Month") && (transyear === "" || transyear === "Year"))
            {
                bootbox.dialog("Please select transaction year!", [{
                        "label": "ok",
                        "class": "btn-small btn-primary"
                    }]);
                $("#transyear").css("border-color", "red");
                return false;
            }
            else
            {
                $("#transyear").css("border-color", "");
            }

            if (!(transyear === "" || transyear === "Year") && (transmonth === "" || transmonth === "Month"))
            {
                bootbox.dialog("Please select transaction month!", [{
                        "label": "ok",
                        "class": "btn-small btn-primary"
                    }]);
                $("#transmonth").css("border-color", "red");
                return false;
            }
            else
            {
                $("#transmonth").css("border-color", "");
            }

            if (statement_from_date !== "" && statement_to_date === "")
            {
                bootbox.dialog("Please enter to date!", [{
                        "label": "ok",
                        "class": "btn-small btn-primary"
                    }]);
                $("#statement_to_date").css("border-color", "red");
                return false;
            }
            else
            {
                $("#statement_to_date").css("border-color", "");
            }

            if (statement_from_date === "" && statement_to_date !== "")
            {
                bootbox.dialog("Please enter from date!", [{
                        "label": "ok",
                        "class": "btn-small btn-primary"
                    }]);
                $("#statement_from_date").css("border-color", "red");
                return false;
            }
            else
            {
                $("#statement_from_date").css("border-color", "");
            }
        }
        else if (docTypeText === "AR Listing")
        {
//            $("#transmonth").val("Month");
//            $("#transyear").val("Year");
//            $("#statement_from_date").val("");
//            $("#statement_to_date").val("");
//            
//            
//            $("#transmonth").prop("disabled", true);
//            $("#transyear").prop("disabled", true);
//            $("#statement_from_date").prop("disabled", true);
//            $("#statement_to_date").prop("disabled", true);
        }
        else
        {
//            $("#transmonth").val("Month");
//            $("#transyear").val("Year");
//            $("#statement_from_date").val("");
//            $("#statement_to_date").val("");
//            
//            $("#transmonth").prop("disabled", false);
//            $("#transyear").prop("disabled", false);
//            $("#statement_from_date").prop("disabled", false);
//            $("#statement_to_date").prop("disabled", false);
        }
//        $("#overlay").css("display", "block");
        console.log("start loading...");

        var xmlInput = "<InputCriteria>";
        xmlInput += "<CustomerId>" + $("#sessionCustomerId").val() + "</CustomerId>";

        if (doctype !== 'All') {
            xmlInput += " <DocumentDetails>";
            xmlInput += " <DocumentType>" + docTypeText + "</DocumentType>";
            xmlInput += " </DocumentDetails>";
        }

        if (statement_from_date !== "" && statement_to_date !== "") {
            xmlInput += "<FromDate>" + statement_from_date + "</FromDate>";
            xmlInput += "<ToDate>" + statement_to_date + "</ToDate>";
        }
        if (transmonth !== "Month" && transyear !== "Year") {
            xmlInput += "<TillDate>" + transmonth + "." + transyear + "</TillDate>";
        }
        xmlInput += " </InputCriteria>";
        console.log("Input Xml for Download:" + xmlInput);

        var dmsip = $("#dmsip").val();

        var URLParam = 'InputXML=' + xmlInput + '&RequestType=statement';
        console.log("URLParam: " + URLParam);

        var serviceUrl = dmsip + "/WebServiceCall/CallWebService";
        console.log("serviceUrl: " + serviceUrl);

//        CustomerPortalapp/ng/search/statement
        $.ajax({
            type: "POST",
            url: serviceUrl,
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            dataType: "xml",
            data: URLParam,
            success: function(data, textStatus, jqXHR) {
                console.log("success: " + data);
                findDocument(data);
            }
        });

//        $("#overlay").css("display", "none");
        console.log("end loading");
    });
</script>
</body>
</html>
