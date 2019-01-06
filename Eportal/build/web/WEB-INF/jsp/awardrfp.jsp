<%-- 
    Document   : showRfp
    Created on : Jul 10, 2018, 12:46:56 PM
    Author     : RaphelTudu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>RFP Details</title>

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
        <script src="js/rfe.js"></script>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
</head>
<style>
    textarea
    {
        resize: none;
        //background-color: #f2f2f2;
    }
    .modal-body .form-control
    {
        min-width: 90%;
        max-width: 100%;
    }
</style>
<body onload="onLoadFunction();">
    <%@include file = "template.jsp" %>
    <div class="page-content">					
        <div class="row-fluid">
            <div class="col-md-12">
                <div class="widget-box">
                    <div class="widget-header widget-header-blue widget-header-flat">
                        <h4 class="lighter">RFP Details</h4>
                    </div>
                    <div class="widget-body">
                        <div class="widget-main">
                            <div class="row-fluid">
                                <div id="fuelux-wizard" class="row-fluid" data-target="#step-container">
                                    <ul class="wizard-steps">
                                        <li data-target="#step1" class="active">
                                            <span class="step">1</span>
                                            <span class="title">Purchase Requisition Details</span>
                                        </li>
                                        <li data-target="#step2">
                                            <span class="step">2</span>
                                            <span class="title">Line Item Details</span>
                                        </li>
                                        <li data-target="#step3">
                                            <span class="step">3</span>
                                            <span class="title">Attachment and Clauses</span>
                                        </li>
                                        <!--                                        <li data-target="#step4">
                                                                                    <span class="step">4</span>
                                                                                    <span class="title">Approval Selection</span>
                                                                                </li>-->


                                    </ul>
                                </div>
                                <hr/>
                                <div class="step-content row-fluid position-relative" id="step-container">
                                    <form class="form-vertical" id="validation-emp" method="post" action="doAwardRfp.do" enctype="multipart/form-data">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                        <input type="hidden" id="check_constraint" value="true"/>    
                                        <div class="step-pane active" id="step1">
<!--                                            <div class="row-fluid">
                                                <div class="span6">
                                                    <div class="control-group">
                                                        <label class="control-label" for="">Assigned To<span style="color: red;"> *</span></label>
                                                        <div class="controls">
                                                            <div class="">
                                                                <input value="${rfqDetails.rfqHeaders.get(0).assignedTo}" type="text" name="assignedTo" id="assignedTo" class="span10 form-field" required readonly/>
                                                            </div>
                                                        </div>																
                                                    </div>
                                                </div>
                                            </div>-->
                                            <div class="row-fluid">
                                                <div class="span6">
                                                    <div class="control-group">
                                                        <label class="control-label" for="">RFQ Title<span style="color: red;"> *</span></label>
                                                        <div class="controls">
                                                            <div class="">
                                                                <input value="${rfqDetails.rfqHeaders.get(0).RFQTitle}" type="text" name="RFQTitle" id="RFQTitle" class="span10 form-field make-enable" required readonly/>
                                                            </div>
                                                        </div>																
                                                    </div>
                                                </div>
                                                <div class="span6">
                                                    <div class="control-group">
                                                        <label class="control-label" for="">Status<span style="color: red;"> *</span></label>
                                                        <div class="controls">
                                                            <div class="">
                                                                <input type="text" value="${rfqDetails.rfqHeaders.get(0).rfqstatus}" id="rfqstatus" name="rfqstatus" class="span10 form-field" required readonly/>

                                                            </div>
                                                        </div>
                                                    </div>

                                                </div>
                                            </div>
                                            <div class="row-fluid">
                                                <div class="span6">
                                                    <div class="control-group">
                                                        <label class="control-label" for="">Initiator Name</label>
                                                        <div class="controls">
                                                            <div class="">
                                                                <input type="text" name="updatedby" id="updatedby" value="${rfqDetails.rfqHeaders.get(0).createdby}" class="span10 make-enable" readonly/>
                                                            </div>
                                                        </div>																
                                                    </div>
                                                </div>
                                                <div class="span6">
                                                    <div class="control-group">
                                                        <label class="control-label" for="">Project Name/Number<span style="color: red;"> *</span></label>
                                                        <div class="controls">
                                                            <div class="">
                                                                <input type="text" value="${rfqDetails.rfqHeaders.get(0).projectnamecode}" name="Projectnamecode" id="Projectnamecode" class="span10 form-field make-enable" required readonly/>
                                                            </div>
                                                        </div>																
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row-fluid">

                                                <div class="span6">
                                                    <div class="control-group">
                                                        <label class="control-label" for="">Open Date<span style="color: red;"> *</span></label>
                                                        <div class="controls">
                                                            <div class="">
                                                                <input type="text" value="${rfqDetails.rfqHeaders.get(0).opendate}" name="opendateReadonly" id="opendateReadonly" class="span10 form-field" required readonly/>
                                                                <input placeholder="Edit Open Date" type="date" name="opendate" id="opendate" class="span10 form-field" style="display: none;" required/>
                                                            </div>
                                                        </div>
                                                    </div>

                                                </div>
                                                <div class="span6">
                                                    <div class="control-group">
                                                        <label class="control-label" for="">Close Date<span style="color: red;"> *</span></label>
                                                        <div class="controls">
                                                            <div class="">
                                                                <input type="text" value="${rfqDetails.rfqHeaders.get(0).closedate}" name="closedateReadonly" id="closedateReadonly" class="span10 form-field" required readonly/>
                                                                <input type="date" name="closedate" id="closedate" class="span10 form-field show-field" style="display: none;" required/>
                                                            </div>
                                                        </div>																
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row-fluid">

                                                <div class="span6">
                                                    <div class="control-group">
                                                        <label class="control-label" for="">Time Left</label>
                                                        <div class="controls">
                                                            <div class="">
                                                                <input type="text" value="${rfqDetails.rfqHeaders.get(0).timeleft}" name="timeleft" id="timeleft" class="span10" readonly/>
                                                            </div>
                                                        </div>
                                                    </div>

                                                </div>
                                                <div class="span6">
                                                    <div class="control-group">
                                                        <label class="control-label" for="">Description</label>
                                                        <div class="controls">
                                                            <div class="">
                                                                <!--                                                                        <input type="text" name="description" id="description" class="span10 form-field" required/>-->
                                                                <textarea style="background-color: #f2f2f2;" id="description" name="description" rows="4" class="span10 make-enable" readonly>${rfqDetails.rfqHeaders.get(0).description}</textarea>
                                                            </div>
                                                        </div>																
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row-fluid">
                                                <div class="span6">
                                                    <div class="control-group">
                                                        <label class="control-label" for="">Bill to Address<span style="color: red;"> *</span></label>
                                                        <div class="controls">
                                                            <div class="">
                                                                <!--<input type="text" name="pr" id="pr" class="span10" required/>-->
                                                                <textarea style="background-color: #f2f2f2;" id="billtoaddress" name="billtoaddress" rows="4" class="span10 form-field make-enable" required readonly>${rfqDetails.rfqHeaders.get(0).billtoaddress}</textarea>

                                                            </div>
                                                        </div>																
                                                    </div>
                                                </div>
                                                <div class="span6">
                                                    <div class="control-group">
                                                        <label class="control-label" for="">Ship to Address<span style="color: red;"> *</span></label>
                                                        <div class="controls">
                                                            <div class="">
                                                                <!--<input type="text" value="" name="prd" id="prd" class="span10" required/>-->
                                                                <textarea style="background-color: #f2f2f2;" id="shiptoaddress" name="shiptoaddress" rows="4" class="span10 form-field make-enable" required readonly>${rfqDetails.rfqHeaders.get(0).shiptoaddress}</textarea>
                                                            </div>
                                                        </div>
                                                    </div>

                                                </div>
                                            </div>

                                            <div class="row-fluid">
                                                <div class="span6" id="costcodeReadonlyDiv">
                                                    <div class="control-group">
                                                        <label class="control-label" for="">Cost Code<span style="color: red;"> *</span></label>
                                                        <div class="controls">
                                                            <div class="">
                                                                <input type="text" value="${rfqDetails.rfqHeaders.get(0).costcode}" id="costcodeReadonly" name="costcodeReadonly" class="span10 form-field hidden-field" required readonly/>

                                                            </div>
                                                        </div>																
                                                    </div>
                                                </div>

                                                <div class="span6" id="negotationstyleReadonlyDiv">
                                                    <div class="control-group">
                                                        <label class="control-label" for="">Negotiation Style<span style="color: red;"> *</span></label>
                                                        <div class="controls">
                                                            <div class="">
                                                                <input type="text" value="${rfqDetails.rfqHeaders.get(0).negotationstyle}" id="negotationstyleReadonly" name="negotationstyleReadonly" class="span10 form-field hidden-field" required readonly/>

                                                            </div>
                                                        </div>
                                                    </div>

                                                </div>

                                            </div>
                                            <div class="row-fluid" id="costCodeAndNegoStyleDiv">
                                                <div class="span6">
                                                    <div class="control-group">
                                                        <label class="control-label" for="">Cost Code<span style="color: red;"> *</span></label>
                                                        <div class="controls">
                                                            <div class="">
                                                                <select id="costcode" name="costcode" class="span10 form-field" required>
                                                                    <option>Edit Cost Code</option>
                                                                    <c:forEach var="costCode" items="${rfqDetails.rfeCostCode}">
                                                                        <option value=${costCode.costcode}>${costCode.costcode}</option>
                                                                    </c:forEach>    
                                                                </select>
                                                            </div>
                                                        </div>																
                                                    </div>
                                                </div>  
                                                <div class="span6">
                                                    <div class="control-group">
                                                        <label class="control-label" for="">Negotiation Style<span style="color: red;"> *</span></label>
                                                        <div class="controls">
                                                            <div class="">
                                                                <select id="Negotationstyle" name="Negotationstyle" class="span10 form-field" required>
                                                                    <option>Edit Negotiation Style</option>
                                                                    <c:forEach var="style" items="${rfqDetails.rfeNegoStyle}">
                                                                        <option value=${style.negotiationstyle}>${style.negotiationstyle}</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>                    
                                            <div class="row-fluid">

                                                <div class="span6">
                                                    <div class="control-group">
                                                        <label class="control-label" for="">Payment Terms<span style="color: red;"> *</span></label>
                                                        <div class="controls">
                                                            <label class="radio-inline">
                                                                <div id="paymenttermsReadonlyDiv">
                                                                    <input type="radio" name="paymenttermsReadonly" id="paymenttermsReadonly" value="${rfqDetails.rfqHeaders.get(0).paymentterms}" checked> ${rfqDetails.rfqHeaders.get(0).paymentterms}
                                                                </div>
                                                                <div class="clearfix"></div>
                                                                <div id="paymenttermsDiv" class="make-enable" style="display: none;">
                                                                    <input type="radio" name="paymentterms" id="paymentterms" value="30" checked> 30
                                                                    <div class="clearfix"></div>
                                                                    <input type="radio" name="paymentterms" id="paymentterms" value="45"> 45
                                                                    <div class="clearfix"></div>
                                                                    <input type="radio" name="paymentterms" id="paymentterms" value="60"> 60
                                                                </div>
                                                            </label>
                                                        </div>
                                                    </div>

                                                </div>
                                                <div class="span6" id="notetobuyerdiv">
                                                    <div class="control-group">
                                                        <label class="control-label" for="">Note to Buyer</label>
                                                        <div class="controls">
                                                            <div class="">
                                                                <!--<input type="text" value="" name="prd" id="prd" class="span10" required/>-->
                                                                <textarea style="background-color: #f2f2f2;" id="notetobuyer" name="notetobuyer" rows="4" class="span10" readonly>${rfqDetails.rfqSupplierHeader.get(0).notetobuyer}</textarea>
                                                            </div>
                                                        </div>
                                                    </div>

                                                </div>
                                            </div>
                                            <!--                                                        <input type="submit" value="Submit" id="PurReqDetBtn">-->
                                            <!--                                                    </form>-->
                                        </div>
                                        <div class="step-pane" id="step2">

                                            <div class="row-fluid show-field" style="display: none;">
                                                <div class="span4">
                                                    <div class="control-group">
                                                        <label class="control-label" for="">Category<span style="color: red;"> *</span></label>
                                                        <div class="controls">
                                                            <div class="">
                                                                <select id="categoryId" class="span10 mat_det_class" required>
                                                                    <option>Select</option>
                                                                    <c:forEach var="category" items="${rfqDetails.rfeCategory}">
                                                                        <option value="${category}">${category}</option>
                                                                    </c:forEach>    
                                                                </select>
                                                            </div>
                                                        </div>																
                                                    </div>
                                                </div>
                                                <div class="span4">
                                                    <div class="control-group">
                                                        <label class="control-label" for="">Sub-category<span style="color: red;"> *</span></label>
                                                        <div class="controls">
                                                            <div class="">
                                                                <select id="subcategoryId" class="span10 mat_det_class" required>
                                                                    <option>Select</option>
                                                                </select>
                                                            </div>
                                                        </div>																
                                                    </div>
                                                </div>
                                                <div class="span4">
                                                    <div class="control-group">
                                                        <label class="control-label" for="">Item Code<span style="color: red;"> *</span></label>
                                                        <div class="controls">
                                                            <div class="">
                                                                <select id="itemCode" class="span10" required>
                                                                    <option>Select</option>
                                                                </select>
                                                            </div>
                                                        </div>																
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row-fluid show-field" style="display: none;">
                                                <div class="span4">
                                                    <div class="control-group">
                                                        <label class="control-label" for="">Item Name<span style="color: red;"> *</span></label>
                                                        <div class="controls">
                                                            <div class="">
                                                                <input type="text" name="itemname" id="itemname" class="span8 mat_det_class" required/>
                                                            </div>
                                                        </div>																
                                                    </div>
                                                </div>
                                                <div class="span4">
                                                    <div class="control-group">
                                                        <label class="control-label" for="">Target Price ( $ )</label>
                                                        <div class="controls">
                                                            <div class="">
                                                                <input type="number" name="targetprice" id="targetprice" class="span8" required/>
                                                            </div>
                                                        </div>																
                                                    </div>
                                                </div>
                                                <div class="span4">
                                                    <div class="control-group">
                                                        <label class="control-label" for="">Quantity<span style="color: red;"> *</span></label>
                                                        <div class="controls">
                                                            <div class="">
                                                                <input type="number" name="quantity" id="quantity" class="span4 mat_det_class" required/>
                                                            </div>
                                                        </div>																
                                                    </div>
                                                </div>
                                            </div>    
                                            <div class="row-fluid show-field" style="display: none;">    
                                                <div class="span2">
                                                    <div class="control-group">
                                                        <label class="control-label" for=""></label>
                                                        <div class="controls">
                                                            <div class="">
                                                                <button center type="button" id="add_cat_sub" class="btn btn-small btn-info">Add</button> 
                                                                <!--                                                                       <button center type="button" id="clear_cat_sub" class="btn btn-small btn-info">Clear</button> -->
                                                            </div>
                                                        </div>																
                                                    </div>
                                                </div>

                                            </div>
                                            <div class="row-fluid">
                                                <div class="span12">
                                                    <table id="cat_subcat_table" name="cat_subcat_table" class="table table-striped table-bordered table-hover">
                                                        <thead>
                                                            <tr>
                                                                <th>Category</th>
                                                                <th>Sub-Category</th>
                                                                <th>Item Name</th>
                                                                <th>Target Price ( $ )</th>
                                                                <th>Quantity</th>
                                                                <th>Price Per Quantity</th>
                                                                <th>Total Price</th>
                                                                <th>Expected Price</th>
                                                                <th>Expected Delivery Date</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach var="lineItem" items="${rfqDetails.rfqLineItems}" varStatus="status">
                                                                <tr>
                                                                    <td>${lineItem.category}</td>
                                                                    <td>${lineItem.subcategory}</td>
                                                                    <td>${lineItem.itemname}</td>
                                                                    <td>${lineItem.targetprice}</td>
                                                                    <td>${lineItem.quantity}</td>
                                                                    <td>${rfqDetails.rfqSupplierLineitem[status.index].quotepriceperquantity}</td>
                                                                    <td>${rfqDetails.rfqSupplierLineitem[status.index].totalprice}</td>
                                                                    <td>${rfqDetails.rfqSupplierLineitem[status.index].expectedprice}</td>
                                                                    <td>${rfqDetails.rfqSupplierLineitem[status.index].deliverydate}</td>
                                                                </tr>
                                                            </c:forEach>    
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>


                                            <!--                                                    </form>-->
                                        </div>
                                        <div class="step-pane" id="step3">
                                            <div id="attDivId">
                                                <h4><b>Attachment</b></h4>
                                                <hr/>
                                                <div class="row-fluid make-enable" style="display: none;">
                                                    <div class="span10">
                                                        <div class="control-group">

                                                            <div class="controls">
                                                                <input type="file" id="id-input-file-2" class="span10" name="file"/>	

                                                            </div>																
                                                        </div>
                                                    </div>
                                                </div>

                                                <c:forEach items="${rfqDetails.rfqAtmnt}" var="attmnt">
                                                    <div class="row-fluid">
                                                        <div class="span6">
                                                            <div class="control-group">
                                                                <!--<label class="control-label" for="">Name</label>-->
                                                                <div class="controls">
<!--                                                                    <input type="text" value="${attmnt.name}" class="span10 make-enable" name="name" id="name" readonly/>-->
                                                                    <a href="downloadFile.do?attmtId=${attmnt.id}" id="downloadFileLink">${attmnt.name}</a>
                                                                </div>																
                                                            </div>
                                                        </div>
                                                        <!--                                                        <div class="span6">
                                                                                                                    <div class="control-group">
                                                                                                                        <label class="control-label" for="">Description</label>
                                                                                                                        <div class="controls">
                                                                                                                            <textarea style="background-color: #f2f2f2;" id="attDescription" name="attDescription" rows="4" class="span10 make-enable" required readonly>${attmnt.description}</textarea>
                                                                                                                                                                                                        <button type="button" id="add_att_btn" class="btn btn-small btn-info pull-right">Add</button>
                                                                                                                                                                                            <input type="button" value="Add" id="add_att_btn" class="btn btn-small btn-info pull-right"/>                        
                                                                                                                        </div>																
                                                                                                                    </div>
                                                                                                                </div>-->
                                                    </div>
                                                </c:forEach>
                                            </div>
                                            <div id="injectContent">

                                            </div>
                                            <h4><b>Supplier Attachment</b></h4>
                                            <hr/>
                                            <c:forEach items="${rfqDetails.rfqSupplierAttachment}" var="attmnt">
                                                <div class="row-fluid">
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <!--<label class="control-label" for="">Name</label>-->
                                                            <div class="controls">
<!--                                                                    <input type="text" value="${attmnt.name}" class="span10 make-enable" name="name" id="name" readonly/>-->
                                                                <a href="downloadSupplierFile.do?attmtId=${attmnt.id}" id="downloadFileLink">${attmnt.name}</a>
                                                            </div>																
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                            <h4><b>Clauses</b></h4>
                                            <hr/>
                                            <div class="row-fluid">
                                                <div class="span6 show-field" style="display: none;">
                                                    <div class="control-group">
                                                        <div class="controls">
                                                            <div id="clausesDiv">
                                                                <label class="control-label" for=""><b>Available Clauses</b><span style="color: red;"> *</span></label>
                                                                <select multiple id="rfqClausesAvail" size="8" name="rfqClausesAvail">            
                                                                    <c:forEach var="availClauses" items="${rfqDetails.availRfeClauses}">
                                                                        <option value="${availClauses.clausesname}">${availClauses.clausesname}</option>
                                                                    </c:forEach>
                                                                </select>

                                                            </div>
                                                        </div>
                                                    </div>

                                                </div>
                                                <div class="span6">
                                                    <div class="control-group">
                                                        <label class="control-label" for=""><b>Selected Clauses</b><span style="color: red;"> *</span></label>
                                                        <div class="controls">
                                                            <div>
                                                                <select multiple id="rfqClausesSelected" name="rfqClausesSelected" class="make-enable" disabled>            
                                                                    <c:forEach var="clauses" items="${rfqDetails.rfqClauses}">
                                                                        <option value="${clauses.clausesname}">${clauses.clausesname}</option>
                                                                    </c:forEach>
                                                                </select>
                                                                <input type="hidden" id="rfeClauses" name="rfeClauses">
                                                            </div>
                                                        </div>
                                                    </div>

                                                </div>

                                            </div>
                                            <div class="row-fluid">
                                                <input type="hidden" id="cat_sub_details" name="cat_sub_details"/>
                                            </div>

                                        </div>
                                        <div class="step-pane" id="step4">
                                            <div class="row-fluid">
                                                <div class="span3">
                                                    <div class="control-group">
                                                        <label class="control-label" for=""><b>Approval 1</b><span style="color: red;"> *</span></label>
                                                        <div class="controls">
                                                            <div class="">
                                                                <input type="text" value="${rfqDetails.rfqApproval.get(0).approvername1}" id="approval1Readonly" name="approval1Readonly" class="approvalsClass" readonly/>
                                                                <select id="approval1" name="approval1" class="approvalsClass show-field" style="display: none;">
                                                                    <option>Edit Approval 1</option>
                                                                    <c:forEach var="approvals" items="${rfqDetails.rfeApprovals}">
                                                                        <option value=${approvals.userid}>${approvals.firstname} ${approvals.lastname}</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                        </div>																
                                                    </div>
                                                </div>
                                                <div class="span3">
                                                    <div class="control-group">
                                                        <label class="control-label" for=""><b>Email Id</b></label>
                                                        <div class="controls">
                                                            <div class="">
                                                                <label class="control-label" for="" id="approval1EmailId">${rfqDetails.rfqApproval.get(0).emailid1}</label>
                                                            </div>
                                                        </div>																
                                                    </div>
                                                </div>
                                                <div class="span3">
                                                    <div class="control-group">
                                                        <label class="control-label" for=""><b>Approval 2</b></label>
                                                        <div class="controls">
                                                            <div class="">
                                                                <input type="text" value="${rfqDetails.rfqApproval.get(0).approvername2}" id="approval2Readonly" name="approval2Readonly" class="approvalsClass" readonly/>
                                                                <select id="approval2" name="approval2" class="approvalsClass show-field" style="display: none;">
                                                                    <option>Edit Approval 2</option>
                                                                    <c:forEach var="approvals" items="${rfqDetails.rfeApprovals}">
                                                                        <option value=${approvals.userid}>${approvals.firstname} ${approvals.lastname}</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                        </div>																
                                                    </div>
                                                </div>
                                                <div class="span3">
                                                    <div class="control-group">
                                                        <label class="control-label" for=""><b>Email Id</b></label>
                                                        <div class="controls">
                                                            <div class="">
                                                                <label class="control-label" for="" id="approval2EmailId">${rfqDetails.rfqApproval.get(0).email2}</label>
                                                            </div>
                                                        </div>																
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row-fluid">
                                                <div class="span3">
                                                    <div class="control-group">
                                                        <label class="control-label" for=""><b>Approval 3</b></label>
                                                        <div class="controls">
                                                            <div class="">
                                                                <input type="text" value="${rfqDetails.rfqApproval.get(0).approvername3}" id="approval3Readonly" name="approval3Readonly" class="approvalsClass" readonly/>
                                                                <select id="approval3" name="approval3" class="approvalsClass show-field" style="display: none;">
                                                                    <option>Edit Approval 3</option>
                                                                    <c:forEach var="approvals" items="${rfqDetails.rfeApprovals}">
                                                                        <option value=${approvals.userid}>${approvals.firstname} ${approvals.lastname}</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                        </div>																
                                                    </div>
                                                </div>
                                                <div class="span3">
                                                    <div class="control-group">
                                                        <label class="control-label" for=""><b>Email Id</b></label>
                                                        <div class="controls">
                                                            <div class="">
                                                                <label class="control-label" for="" id="approval3EmailId">${rfqDetails.rfqApproval.get(0).email3}</label>
                                                            </div>
                                                        </div>																
                                                    </div>
                                                </div>
                                                <div class="span3">
                                                    <div class="control-group">
                                                        <label class="control-label" for=""><b>Approval 4</b></label>
                                                        <div class="controls">
                                                            <div class="">
                                                                <input type="text" value="${rfqDetails.rfqApproval.get(0).approvername4}" id="approval4Readonly" name="approval4Readonly" class="approvalsClass" readonly/>
                                                                <select id="approval4" name="approval4" class="approvalsClass show-field" style="display: none;">
                                                                    <option>Edit Approval 4</option>
                                                                    <c:forEach var="approvals" items="${rfqDetails.rfeApprovals}">
                                                                        <option value=${approvals.userid}>${approvals.firstname} ${approvals.lastname}</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                        </div>																
                                                    </div>
                                                </div>
                                                <div class="span3">
                                                    <div class="control-group">
                                                        <label class="control-label" for=""><b>Email Id</b></label>
                                                        <div class="controls">
                                                            <div class="">
                                                                <label class="control-label" for="" id="approval4EmailId">${rfqDetails.rfqApproval.get(0).email4}</label>
                                                            </div>
                                                        </div>																
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row-fluid">
                                                <div class="span3">
                                                    <div class="control-group">
                                                        <label class="control-label" for=""><b>Approval 5</b></label>
                                                        <div class="controls">
                                                            <div class="">
                                                                <input type="text" value="${rfqDetails.rfqApproval.get(0).approvername5}" id="approval5Readonly" name="approval5Readonly" class="approvalsClass" readonly/>
                                                                <select id="approval5" name="approval5" class="approvalsClass show-field" style="display: none;">
                                                                    <option>Edit Approval 5</option>
                                                                    <c:forEach var="approvals" items="${rfqDetails.rfeApprovals}">
                                                                        <option value=${approvals.userid}>${approvals.firstname} ${approvals.lastname}</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                        </div>																
                                                    </div>
                                                </div>
                                                <div class="span3">
                                                    <div class="control-group">
                                                        <label class="control-label" for=""><b>Email Id</b></label>
                                                        <div class="controls">
                                                            <div class="">
                                                                <label class="control-label" for="" id="approval5EmailId">${rfqDetails.rfqApproval.get(0).email5}</label>
                                                            </div>
                                                        </div>																
                                                    </div>
                                                </div>
                                                <input type="hidden" name="app1Name" id="app1Name"/>
                                                <input type="hidden" name="app2Name" id="app2Name"/>
                                                <input type="hidden" name="app3Name" id="app3Name"/>
                                                <input type="hidden" name="app4Name" id="app4Name"/>
                                                <input type="hidden" name="app5Name" id="app5Name"/>

                                                <input type="hidden" name="app1Email" id="app1Email"/>
                                                <input type="hidden" name="app2Email" id="app2Email"/>
                                                <input type="hidden" name="app3Email" id="app3Email"/>
                                                <input type="hidden" name="app4Email" id="app4Email"/>
                                                <input type="hidden" name="app5Email" id="app5Email"/>
                                                <input type="hidden" name="loggedUserId" id="loggedUserId" value="${username}"/>
                                                <input type="hidden" name="rfpId" id="rfpId"/>
                                                <input type="hidden" name="supplierId" id="supplierId" value="${rfqDetails.rfqsupplierUser.get(0).id}"/>
                                                
                                            </div>
                                        </div>

                                    </form>

                                    <div class="step-pane" id="step5">
                                        <h4 style="color: #005580;"><b>Purchase Requisition Details</b></h4>
                                        <hr>
                                        <form class="form-horizontal">
                                            <div class="row-fluid">
                                                <div class="span6">
                                                    <div class="control-group">
                                                        <label class="control-label" for=""><b>RFQ Title</b></label>
                                                        <label id="roRFQTitle" class="control-label"></label>
                                                        <!--                                                                    <input type="text" value="shjsadjhash" readonly>-->
                                                    </div>
                                                </div>
                                                <div class="span6">
                                                    <div class="control-group">
                                                        <label class="control-label" for=""><b>Status</b></label>
                                                        <label id="roStatus" class="control-label"></label>																
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row-fluid">
                                                <div class="span6">
                                                    <div class="control-group">
                                                        <label class="control-label" for=""><b>Description</b></label>
                                                        <label id="roDesc" class="control-label"></label>																
                                                    </div>
                                                </div>
                                                <div class="span6">
                                                    <div class="control-group">
                                                        <label class="control-label" for=""><b>Open Date</b></label>
                                                        <label id="roOpenDate" class="control-label"></label>																
                                                    </div>
                                                </div>
                                            </div>    

                                            <div class="row-fluid">    
                                                <div class="span6">
                                                    <div class="control-group">
                                                        <label class="control-label" for=""><b>Close Date</b></label>
                                                        <label id="roCloseDate" class="control-label"></label>																
                                                    </div>
                                                </div>
                                                <div class="span6">
                                                    <div class="control-group">
                                                        <label class="control-label" for=""><b>Time Left</b></label>
                                                        <label id="roTimeLeft" class="control-label"></label>																
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row-fluid">
                                                <div class="span6">
                                                    <div class="control-group">
                                                        <label class="control-label" for=""><b>Bill to Address</b></label>
                                                        <label id="roBillAdd" class="control-label"></label>																
                                                    </div>
                                                </div>
                                                <div class="span6">
                                                    <div class="control-group">
                                                        <label class="control-label" for=""><b>Ship to Address</b></label>
                                                        <label id="roShipAdd" class="control-label"></label>																
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row-fluid">    
                                                <div class="span6">
                                                    <div class="control-group">
                                                        <label class="control-label" for=""><b>Initiator Name</b></label>
                                                        <label id="roInitiator" class="control-label"></label>																
                                                    </div>
                                                </div>
                                                <div class="span6">
                                                    <div class="control-group">
                                                        <label class="control-label" for=""><b>Negotiation Style</b></label>
                                                        <label id="roNegoStyle" class="control-label"></label>																
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row-fluid">
                                                <div class="span6">
                                                    <div class="control-group">
                                                        <label class="control-label" for=""><b>Cost Code</b></label>
                                                        <label id="roCostCode" class="control-label"></label>																
                                                    </div>
                                                </div>
                                                <div class="span6">
                                                    <div class="control-group">
                                                        <label class="control-label" for=""><b>Payment Terms</b></label>
                                                        <label id="roPayTerm" class="control-label"></label>																
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row-fluid">
                                                <div class="span6">
                                                    <div class="control-group">
                                                        <label class="control-label" for=""><b>Project Name/Number</b></label>
                                                        <label id="roProjectName" class="control-label"></label>																
                                                    </div>
                                                </div>
                                            </div>
                                        </form>        
                                        <h4 style="color: #005580;"><b>Line Item Details</b></h4>
                                        <hr>
                                        <div class="row-fluid">
                                            <div class="span12">
                                                <table id="ro_cat_subcat_table" name="ro_cat_subcat_table" class="table table-striped table-bordered table-hover">
                                                    <thead>
                                                        <tr>
                                                            <th>Category</th>
                                                            <th>Sub-Category</th>
                                                            <th>Item Name</th>
                                                            <th>Target Price ( $ )</th>
                                                            <th>Quantity</th>

                                                        </tr>
                                                    </thead>
                                                    <tbody>

                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                        <h4 style="color: #005580;"><b>Attachment and Clauses</b></h4>
                                        <hr>
                                        <form class="form-horizontal">  
                                            <div class="row-fluid">
                                                <div class="span6">
                                                    <div class="control-group">
                                                        <label class="control-label" for=""><b>Attachment</b></label>
                                                        <label id="roAttachment" class="control-label"></label>																
                                                    </div>
                                                </div>

                                                <div class="span6">
                                                    <div class="control-group">
                                                        <label class="control-label" for=""><b>Name</b></label>
                                                        <label id="roAttName" class="control-label"></label>																
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row-fluid">

                                                <div class="span6">
                                                    <div class="control-group">
                                                        <label class="control-label" for=""><b>Description</b></label>
                                                        <label id="roAttDesc" class="control-label"></label>																
                                                    </div>
                                                </div>

                                                <div class="span6">
                                                    <div class="control-group">
                                                        <label class="control-label" for=""><b>Clauses</b></label>
                                                        <label id="roClauses" class="control-label"></label>																
                                                    </div>
                                                </div>

                                            </div>
                                        </form>    
                                        <h4 style="color: #005580;"><b>Approval Selection</b></h4>
                                        <hr>
                                        <form class="form-horizontal">    
                                            <div class="row-fluid">
                                                <div class="span6">
                                                    <div class="control-group">
                                                        <label class="control-label" for=""><b>Approval 1</b></label>
                                                        <label id="roApp1Name" class="control-label"></label>																
                                                    </div>
                                                </div>
                                                <div class="span6">
                                                    <div class="control-group">
                                                        <label class="control-label" for=""><b>Email Id</b></label>
                                                        <label id="roApp1Email" class="control-label"></label>																
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row-fluid">
                                                <div class="span6">
                                                    <div class="control-group">
                                                        <label class="control-label" for=""><b>Approval 2</b></label>
                                                        <label id="roApp2Name" class="control-label"></label>																
                                                    </div>
                                                </div>
                                                <div class="span6">
                                                    <div class="control-group">
                                                        <label class="control-label" for=""><b>Email Id</b></label>
                                                        <label id="roApp2Email" class="control-label"></label>																
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row-fluid">
                                                <div class="span6">
                                                    <div class="control-group">
                                                        <label class="control-label" for=""><b>Approval 3</b></label>
                                                        <label id="roApp3Name" class="control-label"></label>																
                                                    </div>
                                                </div>
                                                <div class="span6">
                                                    <div class="control-group">
                                                        <label class="control-label" for=""><b>Email Id</b></label>
                                                        <label id="roApp3Email" class="control-label"></label>																
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row-fluid">
                                                <div class="span6">
                                                    <div class="control-group">
                                                        <label class="control-label" for=""><b>Approval 4</b></label>
                                                        <label id="roApp4Name" class="control-label"></label>																
                                                    </div>
                                                </div>
                                                <div class="span6">
                                                    <div class="control-group">
                                                        <label class="control-label" for=""><b>Email Id</b></label>
                                                        <label id="roApp4Email" class="control-label"></label>																
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row-fluid">
                                                <div class="span6">
                                                    <div class="control-group">
                                                        <label class="control-label" for=""><b>Approval 5</b></label>
                                                        <label id="roApp5Name" class="control-label"></label>																
                                                    </div>
                                                </div>
                                                <div class="span6">
                                                    <div class="control-group">
                                                        <label class="control-label" for=""><b>Email Id</b></label>
                                                        <label id="roApp5Email" class="control-label"></label>																
                                                    </div>
                                                </div>
                                            </div>
                                            <!--                                                        <input type="submit" value="Submit"/>-->
                                        </form>
                                    </div>

                                </div>
                                <!--                                <h4 style="color: #005580;"><b>Approver Remarks Details</b></h4>                
                                                                <hr/>
                                                                <form class="form-vertical">
                                                                    <div class="row-fluid">
                                                                        <div class="span6">
                                                                            <div class="control-group">
                                                                                <label class="control-label" for="remarksBy">Remarks by</label>
                                                                                <div class="controls">
                                                                                    <div class="">
                                                                                        <input value="${rfqDetails.approvalComment.get(0).approvername}" type="text" name="remarksBy" id="remarksBy" class="span10" readonly/>
                                                                                    </div>
                                                                                </div>																
                                                                            </div>
                                                                        </div>
                                                                        <div class="span6">
                                                                            <div class="control-group">
                                                                                <label class="control-label" for="remarksDate">Remarks Date</label>
                                                                                <div class="controls">
                                                                                    <div class="">
                                                                                        <input type="text" value="${rfqDetails.approvalComment.get(0).commentdate}" id="remarksDate" name="remarksDate" class="span10" required readonly/>
                                
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                
                                                                        </div>
                                                                    </div>
                                                                    <div class="row-fluid">
                                                                        <div class="span6">
                                                                            <div class="control-group">
                                                                                <label class="control-label" for="remarksDesc">Remarks</label>
                                                                                <div class="controls">
                                                                                    <div class="">
                                                                                        <input type="text" name="pr" id="pr" class="span10" required/>
                                                                                        <textarea style="background-color: #f2f2f2;" id="remarksDesc" name="remarksDesc" rows="4" class="span10" readonly>${rfqDetails.approvalComment.get(0).comment}</textarea>
                                
                                                                                    </div>
                                                                                </div>																
                                                                            </div>
                                                                        </div>
                                                                        
                                                                    </div>
                                
                                                                </form>-->
                                <hr>

                                <div class="row-fluid wizard-actions">
                                    <button class="btn btn-info btn-prev"><i class="icon-arrow-left"></i>Prev</button>
                                    <button class="btn btn-info btn-next" data-last="Close " id="next_btn">Next
                                        <i class="icon-arrow-right icon-on-right"></i>
                                    </button>

                                </div>
                                <br>
                                <%                                    if (session.getAttribute("userType").equals("Approver")) {
                                %>
                                <div class="row-fluid align-center" id="approve_div_id">
                                    <button class="btn btn-purple" data-toggle="modal" data-target="#approveCommentModal">Approve</button>
                                    <button class="btn btn-pink" data-toggle="modal" data-target="#revisionCommentModal">Send for Revision</button>
                                    <button id="close_btn_id" class="btn btn-inverse">Close</button>
                                </div>
                                <%
                                    }
                                %>
                                <%                                    if (session.getAttribute("userType").equals("SCM User")) {
                                %>
                                <div class="row-fluid align-center" id="revision_update_div">
                                    <button id="edit_btn_id" class="btn btn-pink">Edit</button>
                                    <button id="update_btn_revsion_id" class="btn btn-purple show-field" style="display: none;">Update</button>
                                    <button id="send_btn_revsion_id" class="btn btn-success">Send</button>
                                    <button id="close_btn_id" class="btn btn-inverse">Close</button>
                                </div>
                                <%
                                    }
                                %>
                                <div class="row-fluid align-center">
                                    <button id="award_btn_id" class="btn btn-purple">Award</button>
                                    <button id="award_close_btn_id" class="btn btn-inverse">Close</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="approvelModal">                        
        <div class="modal fade" id="approveCommentModal" role="dialog">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Remarks</h4>
                    </div>
                    <div class="modal-body">
                        <form action="updateRfp.do" name="approve-form" id="approve-form" method="post">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                            <textarea class="form-control" style="" id="approvaeRemarks" name="approvaeRemarks" rows="6" required></textarea>
                            <input type="hidden" id="approve_rfpId" name="approve_rfpId" value="">
                            <input type="hidden" id="action" name="action" value="Approve">
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" id="approve_btn_id" class="btn btn-purple">Approve</button>
                        <button type="button" class="btn btn-inverse" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="revisionModal">                        
        <div class="modal fade" id="revisionCommentModal" role="dialog">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Remarks</h4>
                    </div>
                    <div class="modal-body">
                        <form action="updateRfp.do" name="revision-form" id="revision-form" method="post">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                            <textarea class="form-control" style="" id="revisionRemarks" name="revisionRemarks" rows="6" required></textarea>
                            <input type="hidden" id="revision_rfpId" name="revision_rfpId" value="">
                            <input type="hidden" id="action" name="action" value="Revision">
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" id="send_for_revision_btn_id" class="btn btn-pink">Send for Revision</button>
                        <button type="button" class="btn btn-inverse" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
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
            $('#fuelux-wizard').ace_wizard().on('change', function(e, info) {
                //alert(info.step);
                if (info.step === 1) {

                } else if (info.step === 2) {
                    var no_of_cat = $("#cat_subcat_table tr").length;
                    if (no_of_cat === 1)
                    {
                        //                            bootbox.dialog("Please add at least one category!", [{"label": "ok", "class": "btn-small btn-primary"}]);
                        //                            return false;
                    }
                    var selectedClsLen = $("select#rfqClausesSelected option").length;
                    if (selectedClsLen === 0)
                    {
                        //                            bootbox.dialog("Please select at least one Clause!", [{"label": "ok", "class": "btn-small btn-primary"}]);
                        //                            return false;
                    }
                } else if (info.step === 3) {

                } else if (info.step === 4) {
                    //   preview();
                }

            }).on('finished', function(e) {
                //                bootbox.dialog("Thank you! Your information was successfully saved!", [{
                //                "label" : "OK",
                //                        "class" : "btn-small btn-primary",
                //                        callback: function() {
                //                            //#("#PurReqDetBtn").click();
                //                            //location.href =  "registerRfpRequest.do"
                //                            $("#validation-emp").submit();
                //                        },
                //                }],
                //                        );
                location.href = "rfpStatus.do";
                //$("#validation-emp").submit();
            }).on('stepclick', function(e) {
                //return false;//prevent clicking on steps
            });

            $('.dialogs,.comments').slimScroll({
                height: '280px'
            });
            $('#id-input-file-1 , #id-input-file-2').ace_file_input({
                no_file: 'No File ...',
                btn_choose: 'Choose',
                btn_change: 'Change',
                droppable: false,
                onchange: null,
                thumbnail: false
            });
            var htmltext = '<div class="span12 materialbox" style="margin:0;"><div class="space-10"></div><div class="control-group"><button type="button" class="btn btn-small btn-danger remove_btn pull-right"><i class="icon-remove"></i></button><label class="control-label">Item Name</label><div class="controls"><div class="span12"><select id="material-drop"><option value="">Please Select</option><option value="cement">Cement</option><option value="brick">Bricks</option><option value="sand">Sand</option><option value="wood">Wood</option></select></div></div></div><div class="control-group"><label class="control-label">Item Number</label><div class="controls"><div class="span12"><input type="text" id="item" class="span12" /></div></div></div><div class="control-group"><label class="control-label">Cost</label><div class="controls"><div class="span12"><input type="text" id="cost" class="span12"/></div></div></div><div class="control-group"><label class="control-label">Quantity</label><div class="controls"><div class="span12"><input type="number" id="qty" class="span12"/></div></div></div></div>'

            var htmlString = '<div class="control-group"><div class="controls"><input style="margin-right:3px;" class="input-small" type="text" name="" placeholder="Name"><input style="margin-right:3px;" class="input-small" type="text" name="qty" placeholder="Quantity"><input style="margin-right:3px;" class="input-small" type="text" name="other" placeholder="Other"><button type="button" class="remove_btn btn btn-danger btn-small"><i class="icon-remove"></i></button></div></div>'
            var x = 1;
            $('#add_btn').click(function(e) {
                e.preventDefault();
                if (x < 5)
                {
                    x++;
                    $(this).parent().parent().parent('.form-vertical').append(htmltext);
                }
            });
            $('.form-vertical').on('click', '.remove_btn', function(e) {
                e.preventDefault();
                $(this).parent().parent('div').remove();
                x--;
            });
            var materialJson = [
                {name: 'cement', cost: '1000 per kg', item: 1234},
                {name: 'brick', cost: '100 per kg', item: 2334},
                {name: 'sand', cost: '500 per kg', item: 1534},
                {name: 'wood', cost: '800 per kg', item: 2540}
            ];
            $('#material-drop').on('change', function()
            {
                var material_name = $(this).val();
                for (item of materialJson)
                {
                    if (material_name == "")
                    {
                        $('#cost').val("");
                        $('#item').val("");
                    }
                    if (material_name == item.name)
                    {
                        $('#cost').val(item.cost);
                        $('#item').val(item.item);
                    }
                }
            });
        });

    </script>
</body>
</html>
