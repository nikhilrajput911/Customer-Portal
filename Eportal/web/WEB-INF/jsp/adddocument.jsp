
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
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />		

        <link rel="stylesheet" href="css/ace.min.css" />
        <link rel="stylesheet" href="css/ace-responsive.min.css" />
        <link rel="stylesheet" href="css/ace-skins.min.css" />
        <link rel="stylesheet" href="css/main.css" />

        <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>-->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>


        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

        <script src="js/customer.js"></script>

        <style>
        </style>

    </head>

    <body>

        <%@include file = "templatecustomer.jsp" %>
        <div class="breadcrumbs" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="icon-plus-sign-alt home-icon"></i><a href="#">Add Document</a>
                    <span class="divider"><i class="icon-angle-right arrow-icon"></i></span>
                </li>
                <!--<li class="active">Response Comment</li>-->						
            </ul>
        </div>
        <div class="page-content">
            <div class="row-fluid">
                <div class="span9">
                    <form class="form-vertical" action="uploaddocument.do" method="post" id="document_form" enctype="multipart/form-data">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        <h4><span style="color: green;">${message}</span></h4>
                        <div class="row-fluid">
                            <div class="span6">
                                <div class="control-group">
                                    <label class="control-label" for="">Invoice</label>
                                    <div class="controls">
                                        <div class="">
                                            <input type="file" id="id-input-file-1" class="span10" name="invoice"/>	

                                        </div>
                                    </div>
                                </div>

                            </div>
                            <div class="span6">
                                <div class="control-group">
                                    <label class="control-label" for="">Mill Certificate</label>
                                    <div class="controls">
                                        <div class="">
                                            <input type="file" id="id-input-file-2" class="span10" name="mill_certificate"/>	
                                        </div>
                                    </div>																
                                </div>
                            </div>
                        </div>
                        <div class="row-fluid">
                            <div class="span6">
                                <div class="control-group">
                                    <label class="control-label" for="">Purchase Order</label>
                                    <div class="controls">
                                        <div class="">
                                            <input type="file" id="id-input-file-3" class="span10" name="purchase_order"/>	

                                        </div>
                                    </div>
                                </div>

                            </div>
                            <div class="span6">
                                <div class="control-group">
                                    <label class="control-label" for="">Statement of Account</label>
                                    <div class="controls">
                                        <div class="">
                                            <input type="file" id="id-input-file-4" class="span10" name="statement_account"/>	
                                        </div>
                                    </div>																
                                </div>
                            </div>
                        </div>
                        <div class="row-fluid">
                            <div class="span6">
                                <div class="control-group">
                                    <label class="control-label" for="">AR Listing</label>
                                    <div class="controls">
                                        <div class="">
                                            <input type="file" id="id-input-file-5" class="span10" name="ar_listing"/>	

                                        </div>
                                    </div>
                                </div>

                            </div>
                            <div class="span6">
                                <div class="control-group">
                                    <label class="control-label" for="">Debit Note</label>
                                    <div class="controls">
                                        <div class="">
                                            <input type="file" id="id-input-file-6" class="span10" name="debit_note"/>	
                                        </div>
                                    </div>																
                                </div>
                            </div>
                        </div>
                        <div class="row-fluid">
                            <div class="span6">
                                <div class="control-group">
                                    <label class="control-label" for="">Credit Note</label>
                                    <div class="controls">
                                        <div class="">
                                            <input type="file" id="id-input-file-9" class="span10" name="credit_note"/>	
                                        </div>
                                    </div>																
                                </div>
                            </div>
                            <div class="span6">
                                <div class="control-group">
                                    <label class="control-label" for="">Delivery Order and PO Summary</label>
                                    <div class="controls">
                                        <div class="">
                                            <input type="file" id="id-input-file-7" class="span10" name="delivery_order"/>	

                                        </div>
                                    </div>
                                </div>

                            </div>

                        </div>
                        <div class="row-fluid">
                            <div class="span6">
                                <div class="control-group">
                                    <label class="control-label" for="">Sales Terms and Conditions</label>
                                    <div class="controls">
                                        <div class="">
                                            <input type="file" id="id-input-file-8" class="span10" name="sales_terms_conditions"/>	
                                        </div>
                                    </div>																
                                </div>
                            </div>
                        </div>
                    </form>
                    <div class="row-fluid">
                        <div class="span6">
                            <input type="button" value="Add Document" id="addDocument" class="btn btn-purple">
                            <!--<input type="button" value="Upload Document" id="uploadDocument" class="btn btn-pink">-->
                        </div>
                    </div><br>
                    <div class="row-fluid table-responsive" style="overflow-y: scroll;height: 400px;">
                        <div class="span12">
                            <table id="document-table" class="table table-striped table-bordered table-hover">
                                <thead>
                                    <th>S.No.</th>
                                    <th>Document Type</th>
                                    <th>Document Name</th>
                                    <th>Document</th>
                                    <th>Uploaded on</th>
                                    <th>Uploaded by</th>
                                </thead>
                                <tbody>
                                    <c:forEach var="doc" items="${docList}" varStatus="status">
                                        <tr>
                                            <td>${status.count}</td>
                                            <td>${doc.documenttype}</td>
                                            <td>${doc.documentname}</td>
                                            <td><a href="downloadCustomerDocument.do?docId=${doc.docid}">${doc.documentname}</a></td>
                                            <td>${doc.updatedate}</td>
                                            <td>${doc.updatedby}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                                <tfoot>
                                    <th>S.No.</th>
                                    <th>Document Type</th>
                                    <th>Document Name</th>
                                    <th>Document</th>
                                    <th>Uploaded on</th>
                                    <th>Uploaded by</th>
                                </tfoot>
                            </table>
                        </div>
                    </div>

                </div>

                <div class="span3">
                    <div class="card">
                        <img class="card-img-top" src="images/profile2.jpg" alt="Card image" height="10" width="10">
                        <div class="card-body">
                            <h4 class="card-title">John Doe</h4>
                            <p class="card-text">Some example text some example text. John Doe is an architect and engineer</p>
                            <a href="#" class="btn btn-primary">See Profile</a>
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
        $('#id-input-file-1 , #id-input-file-2, #id-input-file-3, #id-input-file-4, #id-input-file-5 , #id-input-file-6, #id-input-file-7, #id-input-file-8, #id-input-file-9').ace_file_input({
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
