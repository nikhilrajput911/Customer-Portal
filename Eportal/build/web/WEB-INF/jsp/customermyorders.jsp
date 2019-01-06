
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="JSON.JSONObject, JSON.JSONArray,JSON.JSONException" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>Dashboard</title>

        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="description" content="overview &amp; stats" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />		
        <!--<meta http-equiv="X-UA-Compatible" content="IE=edge"/>-->

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

        <!--<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.0/jquery.validate.min.js"></script>-->

        <link rel="stylesheet" href="css/dataTables1.10.18.min.css" />
        <script src="js/dataTables1.10.18.min.js"></script>

        <!--        <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet" />
                <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>-->
        <link rel="stylesheet" href="css/jquery-ui-1.12.1.css">
        <script src="js/jquery-ui-1.12.1.js"></script>

        <link rel="stylesheet" href="css/font-awesome2.min.css" />
        <!--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />-->

        <link rel="stylesheet" href="css/customer-notification.css" />
        <script src="js/customer-notification.js"></script>

        <!--        <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/css/bootstrap-select.min.css" />
                <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/js/bootstrap-select.min.js"></script>-->

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

        <script src="js/customer.js"></script>

        <link rel="stylesheet" href="css/tree-structure.css" />
        <!--<script type="text/javascript" src="https://gc.kis.v2.scr.kaspersky-labs.com/471A31DC-05D0-7447-B4B5-B98A1CCD2B4E/main.js" charset="UTF-8"></script>-->

        <link rel="stylesheet" href="css/jstree-style.min.css"/>
        <script src="js/jstree.min.js"></script>

        <link rel="stylesheet" href="css/richtext.min.css"/>
        <script src="js/jquery.richtext.min.js"></script>
        <!--<link rel="stylesheet" href="css/site.css"/>-->

        <style>
            .hideColumn {
                display: none;
            }
            .align-right {
                text-align: right;
                float: right;
            }
            .jstree-leaf {
                /*                font-size: 20px;
                                height: 20px;*/
            }
            li.jstree-leaf > a .jstree-themeicon { 
                display: none;
            }

            /*            li.jstree-closed > a .jstree-icon 
                        {
                            background:url("../images/folder-icon.png") 0px 0px no-repeat !important;
                        }*/
            /*            .bootstrap-select:not([class*=col-]):not([class*=form-control]):not(.input-group-btn)
                        {
                            width: 503px;
                            background-color: white !important;
                            height: 30px;
                        }
                        .btn-group>.btn:first-child {
                            height: 35px;
                        }
                        .btn-primary {
                            background-color: white!important;
                            border-color: black;
                            border-width: 9px !important;
                            color: black !important;
                        }
                        btn:focus {
                            background-color: white!important;
                        }
                        caret {
                            background-color: black!important;
                        }*/
        </style>

        <script>
            $(document).ready(function() {
                $('.text-aria').richText({
                    // text formatting
                    bold: true,
                    italic: true,
                    underline: true,
                    imageUpload: false,
                    fileUpload: false,
                    urls: false,
                    table: false,
                    videoEmbed: false,
                    removeStyles: true,
                    code: false
                });
                //                    $('.selectpicker').selectpicker();

                var set_image = $("#set_image").prop("src").toString().split(",");
                if (set_image[1] === "NotFound")
                {
                    $("#default_image_a").css("display", "block");
                    $("#set_image_a").css("display", "none");
                }
                //                $('.select-multiple-box').select2({
                //                    placeholder: "Select Project(s)"
                //                });
                //                $("#overlay").css("display", "none");

                $("#my_project_to_date").change(function() {

                    console.log("from: " + $('#my_project_from_date').val());

                    var uploadFromDate = $('#my_project_from_date').val();

                    console.log(uploadFromDate);

                    if (uploadFromDate === "")
                    {
                        //alert("Please Select Open Date First!");
                        bootbox.dialog("Please enter From Date First!", [{"label": "ok", "class": "btn-small btn-primary"}]);
                        $('#my_project_to_date').val('');
                        return false;
                    }
                    else
                    {
                        console.log("else");
                    }
                });


                $("#my_project_from_date").keyup(function() {
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

                $("#my_project_to_date").keyup(function() {
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

                $("#my_project_from_date").blur(function() {
                    //                    console.log($(this).val());
                    var from_date = $(this).val();
                    if (from_date !== "")
                    {
                        if (from_date.length > 10)
                        {
                            alert("Enter valid date!");
                            return false;
                        }
                        var from_min_date = $("#my_project_from_date").datepicker("option", "minDate");
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
                        $("#my_project_to_date").datepicker("option", "minDate", $("#my_project_from_date").datepicker("getDate"));
                    }
                });


                $("#my_project_to_date").blur(function() {
                    //                    console.log($(this).val());
                    var from_date = $(this).val();
                    if (from_date !== "")
                    {
                        if (from_date.length > 10)
                        {
                            alert("Enter valid date!");
                            return false;
                        }
                        var from_min_date = $("#my_project_to_date").datepicker("option", "minDate");
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

                $("#my_project_from_date").datepicker({
                    dateFormat: 'dd-mm-yy',
                    maxDate: 0,
                    minDate: new Date('2018/09/01'),
                    onSelect: function(dateText, inst) {
                        $("#my_project_to_date").datepicker("option", "minDate", $("#my_project_from_date").datepicker("getDate"));
                    }
                });

                $("#my_project_to_date").datepicker({dateFormat: 'dd-mm-yy', maxDate: 0});

                var selectedIndexes = [];

//
//                $("#docUL").on('click', '.download-selected-doc', function() {
////                    alert($(this).prop("checked"));
////                    alert($(this).val());
//                    var isTrue = $(this).prop("checked");
//                    var index = $(this).val();
////                    console.log("selected index: " + index);
////                    console.log("isTrue: " + isTrue);
//
//                    if (isTrue === true)
//                    {
//                        selectedIndexes.push(index);
//                        selectedDataEntry[index] = dataByIndexes[index];
//                    }
//                    else
//                    {
//                        selectedIndexes.pop(index);
//                        delete selectedDataEntry[index];
//                    }
//                    console.log("selectedIndexes: " + selectedIndexes);
//
//
//                    if (selectedIndexes.length !== 0)
//                    {
//                        docIndexForDownloadAll = selectedIndexes;
//                        $("#downloadselected").removeClass("hidden");
//                    }
//                    else
//                    {
//                        docIndexForDownloadAll = allDocIndexes;
//                        $("#downloadselected").addClass("hidden");
//                    }
//                    console.log("docIndexForDownloadAll: " + docIndexForDownloadAll);
//                });
//                $('#container').on("changed.jstree", function(e, data) {
////                    console.log(data.selected);
////                    console.log(data.selected.length);
//                    allSelectedDocIndex = data.selected;
//
//                    console.log("allSelectedDocIndex: " + allSelectedDocIndex);
//
//                    if(allSelectedDocIndex.length === 0)
//                    {
//                        $("#downloadselected").addClass("hidden");
//                    }
//                    else
//                    {
//                        $("#downloadselected").removeClass("hidden");
//                    }
//                    
//                    if(allSelectedDocIndex.length !== 1)
//                    {
//                        $("#viewSelectedDocument").addClass("hidden");
//                    }
//                    else
//                    {
//                        $("#viewSelectedDocument").removeClass("hidden");
//                    }
//                });

                $("#viewSelectedDocument").click(function() {
                    viewSingleDoc(allSelectedDocIndex[0]);
                });

                $("#output1").click(function() {

                    var xmlre = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
                            + "<OutputCriteria>"
                            + "<CustomerId>1101215</CustomerId>"
                            + "<DOSummary>"
                            + "<DocumentIndex>1001</DocumentIndex>"
                            + "<DocumentName>NatSteel DO10.11.2018-10.11.2018-()</DocumentName>"
                            + "<DocumentType>DO Summary</DocumentType>"
                            + "<Extension>XLS</Extension>"
                            + "<ImageIndex>7848</ImageIndex>"
                            + "<InsertDate>10-11-2018</InsertDate>"
                            + "</DOSummary>"
                            + "<CreditNote>"
                            + "<DocumentIndex>1012</DocumentIndex>"
                            + "<DocumentName>CR-1070011438</DocumentName>"
                            + "<DocumentType>Credit Note</DocumentType>"
                            + "<Extension>PDF</Extension>"
                            + "<ImageIndex>7961</ImageIndex>"
                            + "<InsertDate>16-11-2018</InsertDate>"
                            + "</CreditNote>"
                            + "<DebitNote>"
                            + "<DocumentIndex>1002</DocumentIndex>"
                            + "<DocumentName>1070011438</DocumentName>"
                            + "<DocumentType>Debit Note</DocumentType>"
                            + "<Extension>PDF</Extension>"
                            + "<ImageIndex>7961</ImageIndex>"
                            + "<InsertDate>16-11-2018</InsertDate>"
                            + "</DebitNote>"
                            + "<DebitNote>"
                            + "<DocumentIndex>1003</DocumentIndex>"
                            + "<DocumentName>1070011438</DocumentName>"
                            + "<DocumentType>Debit Note</DocumentType>"
                            + "<Extension>PDF</Extension>"
                            + "<ImageIndex>7778</ImageIndex>"
                            + "<InsertDate>16-11-2018</InsertDate>"
                            + "</DebitNote>"
                            + "<Invoice>"
                            + "<DocumentIndex>1004</DocumentIndex>"
                            + "<DocumentName>Invoice 1050483764</DocumentName>"
                            + "<DocumentType>Invoice</DocumentType>"
                            + "<Extension>PDF</Extension>"
                            + "<ImageIndex>8577</ImageIndex>"
                            + "<InsertDate>10-11-2018</InsertDate>"
                            + "<SignedDO>"
                            + "<DocumentIndex>1005</DocumentIndex>"
                            + "<DocumentName>Signed DO Summary 1041203154</DocumentName>"
                            + "<DocumentType>Signed DO</DocumentType>"
                            + "<Extension>PDF</Extension>"
                            + "<ImageIndex>8578</ImageIndex>"
                            + "<InsertDate>10-11-2018</InsertDate>"
                            + "</SignedDO>"
//                            + "<SignedDO>"
//                            + "<DocumentIndex>1006</DocumentIndex>"
//                            + "<DocumentName>Signed DO Summary 1041202887</DocumentName>"
//                            + "<DocumentType>Signed DO</DocumentType>"
//                            + "<Extension>PDF</Extension>"
//                            + "<ImageIndex>8584</ImageIndex>"
//                            + "<InsertDate>10-11-2018</InsertDate>"
//                            + "</SignedDO>"
                            + "<MillCertificate>"
                            + "<DocumentIndex>1022</DocumentIndex>"
                            + "<DocumentName>NatSteel MillCert DO_1041202499</DocumentName>"
                            + "<DocumentType>Mill Certificate</DocumentType>"
                            + "<Extension>PDF</Extension>"
                            + "<ImageIndex>7716</ImageIndex>"
                            + "<InsertDate>27-11-2018</InsertDate>"
                            + "</MillCertificate>"
//                            + "<MillCertificate>"
//                            + "<DocumentIndex>1023</DocumentIndex>"
//                            + "<DocumentName>NatSteel MillCert DO_1041202499</DocumentName>"
//                            + "<DocumentType>Mill Certificate</DocumentType>"
//                            + "<Extension>PDF</Extension>"
//                            + "<ImageIndex>7689</ImageIndex>"
//                            + "<InsertDate>27-11-2018</InsertDate>"
//                            + "</MillCertificate>"
                            + "<UnSignedDO>"
                            + "<DocumentIndex>1026</DocumentIndex>"
                            + "<DocumentName>DO Detail 1041203019</DocumentName>"
                            + "<DocumentType>UnSigned DO</DocumentType>"
                            + "<Extension>PDF</Extension>"
                            + "<ImageIndex>8033</ImageIndex>"
                            + "<InsertDate>10-11-2018</InsertDate>"
                            + "</UnSignedDO>"
                            + "</Invoice>"
                            + "<Invoice>"
                            + "<DocumentIndex>1007</DocumentIndex>"
                            + "<DocumentName>Invoice 1050483763</DocumentName>"
                            + "<DocumentType>Invoice</DocumentType>"
                            + "<Extension>PDF</Extension>"
                            + "<ImageIndex>8583</ImageIndex>"
                            + "<InsertDate>10-11-2018</InsertDate>"
                            + "<SignedDO>"
                            + "<DocumentIndex>1008</DocumentIndex>"
                            + "<DocumentName>Signed DO Summary 1041203154-11</DocumentName>"
                            + "<DocumentType>Signed DO</DocumentType>"
                            + "<Extension>PDF</Extension>"
                            + "<ImageIndex>8578</ImageIndex>"
                            + "<InsertDate>10-11-2018</InsertDate>"
                            + "</SignedDO>"
                            + "<SignedDO>"
                            + "<DocumentIndex>1009</DocumentIndex>"
                            + "<DocumentName>Signed DO Summary 1041202887-22</DocumentName>"
                            + "<DocumentType>Signed DO</DocumentType>"
                            + "<Extension>PDF</Extension>"
                            + "<ImageIndex>8584</ImageIndex>"
                            + "<InsertDate>10-11-2018</InsertDate>"
                            + "</SignedDO>"
                            + "<MillCertificate>"
                            + "<DocumentIndex>1024</DocumentIndex>"
                            + "<DocumentName>NatSteel MillCert DO_1041202499-33</DocumentName>"
                            + "<DocumentType>Mill Certificate</DocumentType>"
                            + "<Extension>PDF</Extension>"
                            + "<ImageIndex>7689</ImageIndex>"
                            + "<InsertDate>27-11-2018</InsertDate>"
                            + "</MillCertificate>"
                            + "<UnSignedDO>"
                            + "<DocumentIndex>1025</DocumentIndex>"
                            + "<DocumentName>DO Detail 1041203019-2</DocumentName>"
                            + "<DocumentType>UnSigned DO</DocumentType>"
                            + "<Extension>PDF</Extension>"
                            + "<ImageIndex>8033</ImageIndex>"
                            + "<InsertDate>10-11-2018</InsertDate>"
                            + "</UnSignedDO>"
                            + "</Invoice>"
                            + "<maincode>0</maincode>"
                            + "<ProjectId>107636</ProjectId>"
                            + "<Type>S</Type>"
                            + "<UnSignedDO>"
                            + "<DocumentIndex>1010</DocumentIndex>"
                            + "<DocumentName>DO Detail 1041194365</DocumentName>"
                            + "<DocumentType>UnSigned DO</DocumentType>"
                            + "<Extension>PDF</Extension>"
                            + "<ImageIndex>8593</ImageIndex>"
                            + "<InsertDate>10-11-2019</InsertDate>"
                            + "<MillCertificate>"
                            + "<DocumentIndex>1021</DocumentIndex>"
                            + "<DocumentName>Mill Detail 1041194365</DocumentName>"
                            + "<DocumentType>Mill Certificate</DocumentType>"
                            + "<Extension>PDF</Extension>"
                            + "<ImageIndex>8593</ImageIndex>"
                            + "<InsertDate>10-11-2020</InsertDate>"
                            + "</MillCertificate>"
                            + "</UnSignedDO>"

                            + "</OutputCriteria>";

                    myProjectTreeStructure(xmlre);

                });
                $("#output2").click(function() {

                    var xmlre = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
                            + "<OutputCriteria>"
                            + "<CustomerId>1101215</CustomerId>"
                            + "<DOSummary>"
                            + "<DocumentIndex>1001</DocumentIndex>"
                            + "<DocumentName>NatSteel DO10.11.2018-10.11.2018-()</DocumentName>"
                            + "<DocumentType>DO Summary</DocumentType>"
                            + "<Extension>XLS</Extension>"
                            + "<ImageIndex>7848</ImageIndex>"
                            + "<InsertDate>10-11-2018</InsertDate>"
                            + "</DOSummary>"
                            + "<CreditNote>"
                            + "<DocumentIndex>1012</DocumentIndex>"
                            + "<DocumentName>CR-1070011438</DocumentName>"
                            + "<DocumentType>Credit Note</DocumentType>"
                            + "<Extension>PDF</Extension>"
                            + "<ImageIndex>7961</ImageIndex>"
                            + "<InsertDate>16-11-2018</InsertDate>"
                            + "</CreditNote>"
                            + "<DebitNote>"
                            + "<DocumentIndex>1002</DocumentIndex>"
                            + "<DocumentName>1070011438</DocumentName>"
                            + "<DocumentType>Debit Note</DocumentType>"
                            + "<Extension>PDF</Extension>"
                            + "<ImageIndex>7961</ImageIndex>"
                            + "<InsertDate>16-11-2018</InsertDate>"
                            + "</DebitNote>"
                            + "<DebitNote>"
                            + "<DocumentIndex>1003</DocumentIndex>"
                            + "<DocumentName>1070011438</DocumentName>"
                            + "<DocumentType>Debit Note</DocumentType>"
                            + "<Extension>PDF</Extension>"
                            + "<ImageIndex>7778</ImageIndex>"
                            + "<InsertDate>16-11-2018</InsertDate>"
                            + "</DebitNote>"
                            + "<Invoice>"
                            + "<DocumentIndex>1004</DocumentIndex>"
                            + "<DocumentName>Invoice 1050483764</DocumentName>"
                            + "<DocumentType>Invoice</DocumentType>"
                            + "<Extension>PDF</Extension>"
                            + "<ImageIndex>8577</ImageIndex>"
                            + "<InsertDate>10-11-2018</InsertDate>"
                            + "<SignedDO>"
                            + "<DocumentIndex>1005</DocumentIndex>"
                            + "<DocumentName>Signed DO Summary 1041203154</DocumentName>"
                            + "<DocumentType>Signed DO</DocumentType>"
                            + "<Extension>PDF</Extension>"
                            + "<ImageIndex>8578</ImageIndex>"
                            + "<InsertDate>10-11-2018</InsertDate>"
                            + "</SignedDO>"
                            + "<SignedDO>"
                            + "<DocumentIndex>1006</DocumentIndex>"
                            + "<DocumentName>Signed DO Summary 1041202887</DocumentName>"
                            + "<DocumentType>Signed DO</DocumentType>"
                            + "<Extension>PDF</Extension>"
                            + "<ImageIndex>8584</ImageIndex>"
                            + "<InsertDate>10-11-2018</InsertDate>"
                            + "</SignedDO>"
                            + "</Invoice>"
                            + "<Invoice>"
                            + "<DocumentIndex>1007</DocumentIndex>"
                            + "<DocumentName>Invoice 1050483763</DocumentName>"
                            + "<DocumentType>Invoice</DocumentType>"
                            + "<Extension>PDF</Extension>"
                            + "<ImageIndex>8583</ImageIndex>"
                            + "<InsertDate>10-11-2018</InsertDate>"
                            + "<SignedDO>"
                            + "<DocumentIndex>1008</DocumentIndex>"
                            + "<DocumentName>Signed DO Summary 1041203154</DocumentName>"
                            + "<DocumentType>Signed DO</DocumentType>"
                            + "<Extension>PDF</Extension>"
                            + "<ImageIndex>8578</ImageIndex>"
                            + "<InsertDate>10-11-2018</InsertDate>"
                            + "</SignedDO>"
                            + "<SignedDO>"
                            + "<DocumentIndex>1009</DocumentIndex>"
                            + "<DocumentName>Signed DO Summary 1041202887</DocumentName>"
                            + "<DocumentType>Signed DO</DocumentType>"
                            + "<Extension>PDF</Extension>"
                            + "<ImageIndex>8584</ImageIndex>"
                            + "<InsertDate>10-11-2018</InsertDate>"
                            + "</SignedDO>"
                            + "</Invoice>"
                            + "<DWG>"
                            + "<DocumentIndex>1011</DocumentIndex>"
                            + "<DocumentName>DWG 1041194365</DocumentName>"
                            + "<DocumentType>DWG</DocumentType>"
                            + "<Extension>PDF</Extension>"
                            + "<ImageIndex>9000</ImageIndex>"
                            + "</DWG>"
                            + "<maincode>0</maincode>"
                            + "<ProjectId>107636</ProjectId>"
                            + "<Type>S</Type>"
                            + "</OutputCriteria>";
                    myProjectTreeStructure(xmlre);
                });

                var to = false;
                $("#search_jstree_input").keyup(function() {
                    if (to)
                    {
                        clearTimeout(to);
                    }
                    to = setTimeout(function() {
                        var v = $('#search_jstree_input').val();
                        console.log("v: " + v);
                        $('#container').jstree(true).search(v);
                    }, 250);
                });

                $("#mailSelectedDocument").click(function() {
                    $("#mailModalDiv").removeClass("hidden");

                    $("#mailTo").val("");
                    $("#mailCC").val("");
                    $("#mailSubject").val("");
                    $(".richText-editor").html("");

                    $("#mailTo").css("border-color", "");
                    $("#mailSubject").css("border-color", "");
                    $("#mailBody").css("border-color", "");

                    $("#errorMailTo").html('');
                    $("#errorMailCC").html('');

                    $("#sendMailModal").modal("show");
                });

                $("#mailTo").after("<div id='errorMailTo' style='color:red;'></div>");
                $("#mailTo").blur(function() {
                    var val = $(this).val();
                    console.log(val);
//                   console.log("index: " + val.indexOf(";"));

                    var regex = new RegExp('^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$');

                    var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
                    if (val !== "")
                    {
                        if (val.indexOf(";") === -1)
                        {


//                       if (regex.test(val))
                            if (filter.test(val))
                            {
//                           console.log("if");
                                $("#errorMailTo").html('');
                                $("#sendMail").prop("disabled", false);
                            }
                            else
                            {
//                           console.log("else");
                                $("#errorMailTo").html('Please enter valid email address!');
                                $("#sendMail").prop("disabled", true);
                            }
                        }
                        else
                        {
                            var isValidEmail = true;
                            var emails = val.split(";");
                            for (var email in emails)
                            {
//                           console.log("email: " + emails[email]);

                                if (filter.test(emails[email]))
                                {
//                               console.log("if.1");
                                }
                                else
                                {
//                               console.log("else.1");
                                    isValidEmail = false;
                                    break;
                                }
                            }
                            console.log("isValidEmail: " + isValidEmail);
                            if (isValidEmail === false)
                            {
                                $("#errorMailTo").html('Please enter valid email address!');
                                $("#sendMail").prop("disabled", true);
                            }
                            else
                            {
                                $("#errorMailTo").html('');
                                $("#sendMail").prop("disabled", false);
                            }
                        }
                    }
                    else
                    {
                        $("#errorMailTo").html('');
                    }
                });

                $("#mailCC").after("<div id='errorMailCC' style='color:red;'></div>");
                $("#mailCC").blur(function() {
                    var val = $(this).val();
                    console.log(val);
//                   console.log("index: " + val.indexOf(";"));

                    var regex = new RegExp('^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$');

                    var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
                    if (val !== "")
                    {
                        if (val.indexOf(";") === -1)
                        {


//                       if (regex.test(val))
                            if (filter.test(val))
                            {
//                           console.log("if");
                                $("#errorMailCC").html('');
                                $("#sendMail").prop("disabled", false);
                            }
                            else
                            {
//                           console.log("else");
                                $("#errorMailCC").html('Please enter valid email address!');
                                $("#sendMail").prop("disabled", true);
                            }
                        }
                        else
                        {
                            var isValidEmail = true;
                            var emails = val.split(";");
                            for (var email in emails)
                            {
//                           console.log("email: " + emails[email]);

                                if (filter.test(emails[email]))
                                {
//                               console.log("if.1");
                                }
                                else
                                {
//                               console.log("else.1");
                                    isValidEmail = false;
                                    break;
                                }
                            }
                            console.log("isValidEmail: " + isValidEmail);
                            if (isValidEmail === false)
                            {
                                $("#errorMailCC").html('Please enter valid email address!');
                                $("#sendMail").prop("disabled", true);
                            }
                            else
                            {
                                $("#errorMailCC").html('');
                                $("#sendMail").prop("disabled", false);
                            }
                        }
                    }
                    else
                    {
                        $("#errorMailCC").html('');
                    }
                });
            });


        </script>
        <style>
            .span6-style {
                width: 100.502906% !important;
            }

            .divborder1 {
                border-left: 2px grey;
                border-style: solid;
                border-top: none;
                border-right: none;
                border-bottom: none;
                overflow-y: auto;
            }

            .divborder2 {
                border-left: 2px grey;
                border-style: solid;
                border-top: none;
                border-right: none;
                border-bottom: none;
                height: 55vh;
                overflow-y: auto;
            }
            .mail-modal {
                width: 590px;
            }
        </style>
    </style>
</head>

<body onload="">

    <%@include file = "templatecustomer.jsp" %>

    <!--        <div id="overlay">
                <div id="loader"></div>
            </div>-->

    <div class="breadcrumbs" id="breadcrumbs">
        <ul class="breadcrumb">
            <li class="align-center">
                <h4>My Projects</h4>

            </li>
            <!--<li class="active">Response Comment</li>-->						
        </ul>
        <div class="nav-search" id="nav-search">
            <a href="redirectcustomerhome.do"><h5><b>Go to Home</b></h5></a>
        </div>
    </div>
    <div class="page-content">
        <div class="panel panel-shadow">
            <div class="panel-body" style="height: 70vh;">
                <input type="hidden" id="sessionCustomerId" value="${customerid}">
                <input type="hidden" id="userRole" value="${userRole}">
                <input type="hidden" name="dmsip" id="dmsip" value="${dmsip}">
                <div class="row-fluid">
                    <div class="span6">
                        <div class="row-fluid">
                            <%                    if (session.getAttribute("userRole").equals("Admin")) {
                            %>
                            <div class="span6 span6-style">
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

                        </div>
                        <div class="row-fluid">
                            <div class="span6 span6-style">
                                <div class="control-group">
                                    <label class="control-label" for="projectId">Project Name</label>
                                    <div class="controls">
                                        <div class="">

                                            <select name="projectId" id="projectId" class="span10" required>
                                                <option>--Select--</option>
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
                            </div>
                        </div>                    
                        <div class="row-fluid hidden">
                            <div class="span6 span6-style">
                                <div class="control-group">
                                    <label class="control-label" for="">Document Name</label>
                                    <div class="controls">
                                        <div class="">
                                            <select id="docType" name="docType" class="span10 search-doc-form-field">
                                                <c:forEach var="group" items="${docGroupList}" varStatus="status">
                                                    <option value="${group.groupalias}">${group.groupname}</option>
                                                </c:forEach>    
                                            </select>

                                        </div>
                                    </div>
                                </div>
                            </div>                    
                        </div>
                        <div class="row-fluid">
                            <div class="span6 span6-style">
                                <div class="control-group">
                                    <label class="control-label" for="">Document Date (From)</label>
                                    <div class="controls">
                                        <div class="">
                                            <input type="text" placeholder="DD-MM-YYYY" id="my_project_from_date" name="my_project_from_date" class="span10 search-doc-form-field">
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="row-fluid">
                            <div class="span6 span6-style">
                                <div class="control-group">
                                    <label class="control-label" for="">To</label>
                                    <div class="controls">
                                        <div class="">
                                            <input type="text" placeholder="DD-MM-YYYY" id="my_project_to_date" name="my_project_to_date" class="span10 search-doc-form-field">

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>                    


                        <div class="row-fluid">
                            <div class="span6">
                                <input type="button" value="Get Document" id="searchDocument" class="btn btn-purple">
                                <input type="button" value="Clear Criteria" id="clearMyOrderCriteria" class="btn btn-success">
                                <br>
<!--                                <input type="button" value="Output 1" id="output1">
                                <input type="button" value="Output 2" id="output2">-->
                            </div>

                        </div>
                    </div>

                    <!--<div class="vl"></div>-->                        
                    <!--<hr width="1" size="500">-->

                    <div class="span6">
                        <!--<div class="divborder1">-->
                        <div class="row-fluid">
                            <div class="span12">

                                <input type="button" value="Download All" id="advsearchdownloadall" onclick="downloadMultipleDoc();
                                        saveMultipleDoc();" class="btn btn-small btn-danger align-right hidden">

                                <input type="button" value="Download Selected" id="downloadselected" onclick="downloadSelectedMultipleDoc();
                                        saveMyOrderMultipleDoc();" class="btn btn-small btn-purple align-right hidden">

                                <input type="button" value="Send Mail" id="mailSelectedDocument" class="btn btn-small btn-info align-right hidden">

                                <input type="button" value="View Document" id="viewSelectedDocument" class="btn btn-small btn-pink align-right hidden">



                                <!--<input type="button" value="Download Selected" id="downloadselected" onclick="downloadSelectedMultipleDoc();" class="btn btn-purple align-right hidden">-->

                            </div>
                        </div>
                        <br>
                        <div class="row-fluid">
                            <div class="span12">
                                <input type="text" placeholder="Search document" id="search_jstree_input" class="hidden align-right">
                            </div>
                        </div>
                        <!--</div>-->
                        <div class="space"></div>
                        <div class="divborder2">
                            <div class="row-fluid">
                                <!--                                <div class="span6">
                                                                    <ul id="docUL">
                                
                                                                    </ul>
                                                                </div>-->
                                <div class="span12">

                                    <div id="message"></div>

                                    <div id="container" class="tree-container">

                                        <ul id="sub_jstree_ul">

                                        </ul>
                                    </div>

                                </div>

                            </div>
                        </div>
                    </div>
                </div>


            </div>
        </div>
    </div>
    <div id="mailModalDiv" class="hidden">
        <div class="modal fade" id="sendMailModal" role="dialog" style="width: 650px;">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Send Mail</h4>
                    </div>
                    <div class="modal-body">
                        <form id="mail_form" method="post" action="" enctype="multipart/form-data">

                            <label class="" for="">To</label>
                            <input type="text" placeholder="eg:abc@incipio.com;xyz@ncpo.com;...." id="mailTo" name="mailTo" class="mail-modal">

                            <label class="" for="">CC</label>
                            <input type="text" placeholder="eg:abc@incipio.com;xyz@ncpo.com;...." id="mailCC" name="mailCC" class="mail-modal">

                            <label class="" for="">Subject</label>
                            <input type="text" placeholder="" id="mailSubject" name="mailSubject" class="mail-modal">

                            <label class="" for="">Mail Body</label>
                            <textarea class="mail-modal text-aria" id="mailBody" name="mailBody" rows="6" cols=""></textarea>

                            <!--                            <div class="widget-box">
                                                            <div class="widget-header widget-header-small  header-color-blue"></div>
                                                            <div class="widget-body">
                                                                <div class="widget-main no-padding">
                                                                    <div class="wysiwyg-editor" id="editor2"></div>
                                                                </div>						
                                                            </div>
                                                        </div>-->

                            <input type="hidden" id="messageToAllCustomerUser" name="messageToAllCustomerUser">
                        </form>    
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-success" id="sendMail">Send</button>
                        <button type="button" class="btn btn-inverse" data-dismiss="modal">Close</button>
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


<script src="js/markdown/markdown.min.js"></script>
<script src="js/markdown/bootstrap-markdown.min.js"></script>
<script src="js/jquery.hotkeys.min.js"></script>
<script src="js/bootstrap-wysiwyg.min.js"></script>	

<script type="text/javascript">
    $(function() {
        $('.dialogs,.comments').slimScroll({
            height: '200px'
        });
//        $('#editor2').css({'height': '200px'}).ace_wysiwyg({
//            toolbar_place: function(toolbar) {
//                return $(this).closest('.widget-box').find('.widget-header').prepend(toolbar);
//            },
//            toolbar:
//                    [
//                        'bold',
//                        {name: 'italic', title: 'Change Title!', icon: 'icon-leaf'},
//                        'strikethrough',
//                        'underline',
//                        null,
//                        'insertunorderedlist',
//                        'insertorderedlist',
//                        null,
//                        'justifyleft',
//                        'justifycenter',
//                        'justifyright'
//                    ],
//            speech_button: false
//        });
    });


</script>
</body>
</html>
