
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

        <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>-->
        <!--<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>-->
        <script src="js/jquery.min.js"></script>

        <!--<link rel="stylesheet" href="https://cdn.datatables.net/1.10.18/css/jquery.dataTables.min.css" type="text/css"/>-->
        <link rel="stylesheet" href="css/dataTables1.10.18.min.css" />

        <!--<script src="https://cdn.datatables.net/1.10.18/js/jquery.dataTables.min.js" type="text/javascript"></script>-->
        <script src="js/dataTables1.10.18.min.js"></script>

        <!--<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet" />-->
        <!--<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>-->

        <link rel="stylesheet" href="css/customer-notification.css" />
        <script src="js/customer-notification.js"></script>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

        <script src="js/customer.js"></script>

        <style>
        </style>

        <script>
            $(document).ready(function() {
                var set_image = $("#set_image").prop("src").toString().split(",");
                if (set_image[1] === "NotFound")
                {
                    $("#default_image_a").css("display", "block");
                    $("#set_image_a").css("display", "none");
                }

                $("#id-input-file-1").change(function() {

                    console.log(this.files[0].size);
                    var fileName = this.files[0].name.toString();
                    console.log(fileName);
//                    alert(fileName);
                    var specialCount = (fileName.match(/[!@#$%^&*()_+\-=\[\]{};':"\\|,<>\/?]/g) || []).length;
                    console.log("specialCount: " + specialCount);
                    
                    
                    if(specialCount > 0)
                    {
                        alert("File name must not contain special characters.");
                        $("#id-input-file-1").val('');
                        $("#updateTermsAndCondition").prop("disabled", true);
                    }
                    else
                    {
                        $("#updateTermsAndCondition").prop("disabled", false);
                    }
                    
                    if (this.files[0].size > 800000)
                    {
                        alert("File size must be less than 800KB");
                        $("#id-input-file-1").val('');
                        $("#updateTermsAndCondition").prop("disabled", true);
                    }
                    else
                    {
                        $("#updateTermsAndCondition").prop("disabled", false);
                    }
                });
//
//                $("#updateTermsAndCondition").click(function() {
//
//                    if ($("#id-input-file-1")[0].files[0] === undefined)
//                    {
//                        alert("Please select file.");
//                    }
//                    else
//                    {
//                        var fileName = $("#id-input-file-1")[0].files[0].name;
//                        var file = $("#id-input-file-1")[0].files[0];
//
//                        console.log("file name: " + fileName);
//                        console.log("file : " + file);
//
//                        var reader = new FileReader();
//                        reader.readAsDataURL(file);
//
//                        reader.onload = function() {
////                            console.log(reader.result);
//
//                            var dmsip = $("#dmsip").val();
//                            console.log(dmsip);
//
//                            var URLParam = "EncodedDoc=" + reader.result + "&filename=" + fileName;
//                            console.log("URLParam: " + URLParam);
//
//                            var serviceUrl = dmsip + "/WebServiceCall/AddDocument";
//                            console.log("serviceUrl: " + serviceUrl);
//
//                            $.ajax({
//                                type: "GET",
//                                url: serviceUrl,
//                                contentType: "application/x-www-form-urlencoded; charset=utf-8",
//                                data: URLParam,
//                                async: false,
//                                success: function(data, textStatus, jqXHR) {
//                                    //                    alert("success: " + data);
//                                    console.log("success: " + data);
//                                    getDocumentUploadResponse(data);
//
//
//                                }
//                            });
//                        };
//                        reader.onerror = function(error) {
//                            console.log('Error: ', error);
//                        };
//                    }
//                });

            });
            function getDocumentUploadResponse(xmlResponse)
            {
//                var xmlString = XMLtoString(xmlResponse); //Convert the XML Object to a String
//                console.log("xmlString: " + xmlString);

                var xmlDoc = $.parseXML(xmlResponse); //Parse the XML String to get data
                console.log("xmlDoc: " + xmlDoc);

                var $xml = $(xmlDoc);
                console.log("$xml: " + $xml);
//
//                var $output = $xml.find('OutputCriteria');
//                console.log("output: " + $output);

                var $MainCode = $xml.find("maincode");
                console.log("main obj: " + $MainCode);
                console.log("main code: " + $MainCode.text());

                if ($MainCode.text() === "0")
                {
                    alert("Document uploaded successfully.");
                    location.reload(true);
                }
                else
                {
                    alert("Document upload has failed. Please try again.");
                }
            }
            function XMLtoString(oXML)
            {
                //code for IE
                //    alert("in XMLToString: " + oXML);
                if (window.ActiveXObject) {
                    var oString = oXML.xml;
                    return oString;
                }
                // code for Chrome, Safari, Firefox, Opera, etc.
                else {
                    return (new XMLSerializer()).serializeToString(oXML);
                }
            }
        </script>

    </head>

    <body>

        <%@include file = "templatecustomer.jsp" %>
        <div class="breadcrumbs" id="breadcrumbs">
            <ul class="breadcrumb">
                <li class="align-center">
                    <h4>Configure Dashboard Images</h4>

                </li>
                <!--<li class="active">Response Comment</li>-->						
            </ul>
            <div class="nav-search" id="nav-search">
                <a href="redirectcustomerhome.do"><h5><b>Go to Home</b></h5></a>
            </div>
        </div>
        <div class="page-content">
            <h5 class="center"><span style="color: green;">${message}</span></h5>
            <div class="panel panel-shadow">
                <div class="panel-body">
                    <form id="configure_image_form" action="uploadtermsandconditiondocument.do" method="post" class="form-vertical" enctype="multipart/form-data">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        <input type="hidden" name="dmsip" id="dmsip" value="${dmsip}">
                        <div class="row-fluid">
                            <div class="span6">
                                <div class="control-group">
                                    <label class="control-label" for="sectionName">Document Name</label>
                                    <div class="controls">
                                        <div class="">
                                            <select id="sectionName" name="sectionName" class="span10 subuser-form-field" required>
                                                <option>Terms and Condition</option>

                                            </select>
                                        </div>
                                    </div>																
                                </div>
                            </div>
                            <div class="span6">
                                <div class="control-group">
                                    <label class="control-label" for="image">Upload Document</label>
                                    <div class="controls">
                                        <div class="">
                                            <input type="file" id="id-input-file-1" name="files" accept="">
                                        </div>
                                    </div>																
                                </div>
                            </div>
                        </div>
                        <br>
                        <div class="row-fluid align-center">
                            <input type="submit" value="Upload" class="btn btn-primary" id="updateTermsAndCondition">

                        </div>
                    </form>
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
        $('#id-input-file-1 , #id-input-file-2').ace_file_input({
            no_file: 'No File ...',
            btn_choose: 'Choose',
            btn_change: 'Change',
            droppable: false,
            onchange: null,
            thumbnail: false
        });
    });
</script>
</body>
</html>
