
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
        <!--        <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet" />
                <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>-->

        <link rel="stylesheet" href="css/font-awesome2.min.css" />
        
        <link rel="stylesheet" href="css/customer-notification.css" />
        <script src="js/customer-notification.js"></script>
        
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

        <script src="js/customer.js"></script>

        <style>
            .hideColumn {
                display: none;
            }
            .align-right {
                text-align: right;
                float: right;
            }
        </style>

        <script>
            $(document).ready(function() {

                var set_image = $("#set_image").prop("src").toString().split(",");
                if(set_image[1] === "NotFound")
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

//                $("#contract_from_date").attr("max", year + "-" + month + "-" + day);
//                $("#contract_to_date").attr("max", year + "-" + month + "-" + day);

//                $('.select-multiple-box').select2({
//                    placeholder: "Select Project(s)"
//                });
//
//                $("#contract_from_date").blur(function()
//                {
//                    $("#contract_to_date").attr("min", $('#contract_from_date').val());
//                    $("#contract_to_date").val('');
//                });

                $("#contract_to_date").change(function() {

                    var uploadFromDate = $('#contract_from_date').val();

                    console.log(uploadFromDate);

                    if (uploadFromDate === "")
                    {
                        //alert("Please Select Open Date First!");
                        bootbox.dialog("Please enter From Date First!", [{"label": "ok", "class": "btn-small btn-primary"}]);
                        $('#contract_to_date').val('');
                        return false;
                    }

                    else
                    {
                        console.log("else");
                    }
                });

                $("#clearContractDocCriteria").click(function() {
                    location.reload(true);
//                    if ($("#userRole").val() === "Admin")
//                    {
//                        $("#customerId").val('--Select--');
//                    }
//                    console.log("project: " + $("#projectId").val());
//                    $("#projectId").val("");
//                    $('#contract_from_date').val("");
//                    $('#contract_to_date').val("");
//                    $("#docType").val("");
                });
//                $("#overlay").css("display", "none");
                
                $("#contract_from_date").keyup(function() {
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

                $("#contract_to_date").keyup(function() {
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

                $("#contract_from_date").blur(function() {
//                    console.log($(this).val());
                    var from_date = $(this).val();
                    if (from_date !== "")
                    {
                        if(from_date.length > 10)
                        {
                            alert("Enter valid date!");
                            return false;
                        }
                        var from_min_date = $("#contract_from_date").datepicker("option", "minDate");
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
                        $("#contract_to_date").datepicker("option", "minDate", $("#contract_from_date").datepicker("getDate"));
                    }
                });

                
                $("#contract_to_date").blur(function() {
//                    console.log($(this).val());
                    var from_date = $(this).val();
                    if (from_date !== "")
                    {
                        if(from_date.length > 10)
                        {
                            alert("Enter valid date!");
                            return false;
                        }
                        var from_min_date = $("#contract_to_date").datepicker("option", "minDate");
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

                $("#contract_from_date").datepicker({
                    dateFormat: 'dd-mm-yy',
                    maxDate: 0,
                    minDate: new Date('2018/09/01'),
                    onSelect: function(dateText, inst) {
                        $("#contract_to_date").datepicker("option", "minDate", $("#contract_from_date").datepicker("getDate"));
                    }
                });

                $("#contract_to_date").datepicker({dateFormat: 'dd-mm-yy', maxDate: 0});
            });
        </script>

    </head>

    <body onload="">

        <%@include file = "templatecustomer.jsp" %>  

        <!--        <div id="overlay">
                    <div id="loader"></div>
                </div>-->


        <div class="breadcrumbs" id="breadcrumbs">
            <ul class="breadcrumb">
                <li class="align-center">
                    <h4>Customer Documents</h4>

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
                    <input type="hidden" id="userRole" value="${userRole}">
                    <input type="hidden" id="sessionCustomerId" value="${customerid}">
                    <input type="hidden" name="dmsip" id="dmsip" value="${dmsip}">

                    <div class="row-fluid">
                        <%                    if (session.getAttribute("userRole").equals("Admin")) {
                        %>
                        <div class="span6">
                            <div class="control-group">
                                <label class="control-label" for="customerId">Customer Name</label>
                                <div class="controls">
                                    <div class="">
                                        <select id="customerId" name="customerId" class="span10 myorder-form-field" required>
                                            <option>--Select--</option>
                                            <c:forEach var="customer" items="${customerList}" varStatus="status">
                                                <option value="${customer.customercode}">${customer.customername}</option>
                                            </c:forEach>
                                        </select>

                                    </div>
                                </div>																
                            </div>
                        </div>
                        <%                            }
                        %>
<!--                        <div class="span6">
                            <div class="control-group">
                                <label class="control-label" for="projectId">Project Name</label>
                                <div class="controls">
                                    <div class="">

                                        <select name="projectId" id="projectId" class="span10 myorder-form-field">
                                            <option value="">--Select--</option>
                                            <%                    if (session.getAttribute("userRole").equals("Company Admin")) {
                                            %>
                                            <c:forEach var="project" items="${projectListByCutomer}" varStatus="status">
                                                <option value="${project.projectcode}">${project.projectname}</option>
                                            </c:forEach>
                                            <%
                                            } else if (!session.getAttribute("userRole").equals("Admin")) {
                                            %>
                                            <c:forEach var="mapping" items="${projectList}" varStatus="status">
                                                <option value="${mapping.bpaasProjectseededPid.projectcode}">${mapping.bpaasProjectseededPid.projectname}</option>
                                            </c:forEach>
                                            <%
                                                }
                                            %>
                                        </select>

                                    </div>
                                </div>
                            </div>
                        </div>-->
                    </div>
                    <!--                    <div class="row-fluid">
                                            <div class="span6">
                                                <div class="control-group">
                                                    <label class="control-label" for="avail_project"><b>Project Name</b></label>
                                                    <div class="controls">
                                                        <div class="">
                                                            <input type="text" id="search_project" placeholder="Search..." style="width: 80%;">
                                                            <select name="avail_project" id="avail_project" size="4" style="width: 85%;">            
                    
                </select>

            </div>
        </div>
    </div>
</div>
<br><br>
<div class="span6">
    <div class="control-group">
        <label class="control-label" for="mapped_project"><b>Selected Project</b></label>
        <div class="controls">
            <div class="">

                <select name="mapped_project" id="mapped_project" size="4" style="width: 85%;">            

                </select>

            </div>
        </div>
    </div>
</div>
</div>-->
                    <div class="row-fluid">
                        <div class="span6">
                            <div class="control-group">
                                <label class="control-label" for="">Document Name</label>
                                <div class="controls">
                                    <div class="">
                                        <select id="docType" name="docType" class="span10 search-doc-form-field">
                                            <option value="">--Select--</option>
                                            <option>Contract Document</option>
                                        </select>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row-fluid">
                        <div class="span6">
                            <div class="control-group">
                                <label class="control-label" for="uploadFromDate">Document Date (From)</label>
                                <div class="controls">
                                    <div class="">
                                        <!--                                        <input type="date" id="contract_from_date" name="uploadFromDate" class="span10 search-doc-form-field" required="true" onkeydown="return false">-->
                                        <!--<input type="text" placeholder="DD-MM-YYYY" onfocus="(this.type='date')" id="contract_from_date" name="uploadFromDate" class="span10 search-doc-form-field" required="true" onkeydown="return false">-->
                                        <input type="text" placeholder="DD-MM-YYYY" id="contract_from_date" name="uploadFromDate" class="span10 search-doc-form-field" required="true">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="span6">
                            <div class="control-group">
                                <label class="control-label" for="uploadToDate">To</label>
                                <div class="controls">
                                    <div class="">
                                        <!--                                        <input type="date" id="contract_to_date" name="uploadToDate" class="span10 search-doc-form-field" required="true" onkeydown="return false">-->
                                        <!--<input type="text" placeholder="DD-MM-YYYY" onfocus="(this.type='date')" id="contract_to_date" name="uploadToDate" class="span10 search-doc-form-field" required="true" onkeydown="return false">-->
                                        <input type="text" placeholder="DD-MM-YYYY" id="contract_to_date" name="uploadToDate" class="span10 search-doc-form-field" required="true">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row-fluid">
                        <div class="span6">
                            <input type="button" value="Get Document" id="ContractDoc" class="btn btn-purple">
                            <input type="button" value="Clear Criteria" id="clearContractDocCriteria" class="btn btn-success">
                        </div>
                        <div class="span6">
                            <input type="button" value="Download All Document" id="advsearchdownloadall" onclick="downloadMultipleDoc();saveMultipleDoc();" class="btn btn-danger align-right" style="display: none;">
                        </div>
                    </div>
                    <div class="space"></div>
                    <div class="row-fluid table-responsive" id="myOrderDocumentTableDivId" style="display: none;">
                        <div class="span12">
                            <table id="Search_document_table" class="table table-striped table-bordered table-hover">
                                <thead>
                                <th>S.No.</th>
                                <th class="hideColumn">Document Index</th>
                                <th>Document Number</th>
                                <th>Document Name</th>
                                <th>Document Type</th>
                                <th class="hideColumn">Image Index</th>
                                <th>Document Date</th>
                                <th></th>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                    </div>
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
    $("#ContractDoc").click(function() {
        console.log("Inside GetContractDocument");


        var role = $("#userRole").val();
        console.log(role);

        var sessionCustomerId = $("#sessionCustomerId").val();
        console.log(sessionCustomerId);
        var customerid = "";

        if (role === "Admin")
        {
            customerid = $("#customerId").val();
            if (customerid === "--Select--")
            {
                bootbox.dialog("Please Select Customer!", [{
                        "label": "ok",
                        "class": "btn-small btn-primary"
                    }]);
                $("#customerId").css("border-color", "red");
                return false;
            }
            else
            {
                $("#customerId").css("border-color", "");
            }
        }
        else
        {
            customerid = sessionCustomerId;
        }

//        var projectId = $("#projectId").val();
//        console.log("projectId " + projectId);
//        if (projectId === "--Select--" || projectId === "")
//        {
//            bootbox.dialog("Please Select Project!", [{
//                    "label": "ok",
//                    "class": "btn-small btn-primary"
//                }]);
//            $("#projectId").css("border-color", "red");
//            return false;
//        }
//        else
//        {
//            $("#projectId").css("border-color", "");
//        }

        var docType = $("#docType").val();
        console.log("docType: " + docType);
        if (docType === "--Select--" || docType === "")
        {
            bootbox.dialog("Please Select Document Category!", [{
                    "label": "ok",
                    "class": "btn-small btn-primary"
                }]);
            $("#docType").css("border-color", "red");
            return false;
        }
        else
        {
            $("#docType").css("border-color", "");
        }

//        $("#overlay").css("display", "block");

        var contract_from_date = $("#contract_from_date").val();
        var contract_to_date = $("#contract_to_date").val();

        if (contract_from_date !== "")
        {
            if (contract_to_date === "")
            {
                bootbox.dialog("Please enter to date!", [{
                        "label": "ok",
                        "class": "btn-small btn-primary"
                    }]);
                $("#contract_to_date").css("border-color", "red");
                return false;
            }
            else
            {
                $("#contract_to_date").css("border-color", "");
            }
        }

        var xmlInput = "<InputCriteria>";
        xmlInput += "<CustomerId>" + customerid + "</CustomerId>";
//        if (projectId != '') {
//            xmlInput += " <ProjectId>" + projectId + "</ProjectId>";
//
//        }
        if (contract_from_date != '') {
            xmlInput += " <FromDate>" + contract_from_date + "</FromDate>";
        }
        if (contract_to_date != '') {
            xmlInput += "<ToDate>" + contract_to_date + "</ToDate>";
        }
        xmlInput += "</InputCriteria>";
        console.log("Input XML for Contract Document:" + xmlInput);
//        10.246.8.70
//        192.168.13.119
        var dmsip = $("#dmsip").val();
        console.log(dmsip);

        var URLParam = 'InputXML=' + xmlInput + '&RequestType=contract';
        console.log("URLParam: " + URLParam);

//        var serviceUrl = "http://" + dmsip + "/WebServiceCall/CallWebService";
        var serviceUrl = dmsip + "/WebServiceCall/CallWebService";
        console.log("serviceUrl: " + serviceUrl);
//        CustomerPortalapp/ng/search/contract
        $.ajax({
            type: "POST",
            url: serviceUrl,
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            dataType: "xml",
            data: URLParam,
            success: function(data, textStatus, jqXHR) {
                console.log("success: " + data);
                findDocumentByProjectIdAndCustomerId(data);

            }
        });
//        $("#overlay").css("display", "none");
        console.log("end loading...");
    });

</script>
</body>
</html>
