
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js" type="text/javascript"></script>
        <script src="js/rfe.js"></script>
        <script src="js/rfe-validations.js"></script>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
    <style>
        textarea
        {
            resize: none;
        }
    </style>
    <body onload="onLoadForOpenDate();">
        <%@include file = "template.jsp" %>
        <div class="breadcrumbs" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="icon-desktop"></i><a href="#"><span class="menu-text"> RFP Management </span></a>
                    <span class="divider"><i class="icon-angle-right arrow-icon"></i></span>
                </li>
                <li class="active">Create RFP</li>						
            </ul>
        </div>
        <div class="page-content">					
            <div class="row-fluid">
                <div class="col-md-12">
                    <div class="widget-box">
                        <div class="widget-header widget-header-blue widget-header-flat">
                            <h4 class="lighter">Create RFP</h4>
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
                                            <li data-target="#step4">
                                                <span class="step">4</span>
                                                <span class="title">Approval Selection</span>
                                            </li>
                                            <li data-target="#step5">
                                                <span class="step">5</span>
                                                <span class="title">Preview</span>
                                            </li>

                                        </ul>
                                    </div>
                                    <hr/>
                                    <div class="step-content row-fluid position-relative" id="step-container">
                                        <form class="form-vertical" id="validation-emp" method="post" action="registerRfpRequest.do" enctype="multipart/form-data">
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                            <input type="hidden" id="check_constraint" value="true"/>    
                                            <div class="step-pane active" id="step1">

                                                <div class="row-fluid">
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">RFQ Title<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <input type="text" name="RFQTitle" id="RFQTitle" class="span10 form-field" required/>
                                                                </div>
                                                            </div>																
                                                        </div>
                                                    </div>
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">Status<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <input type="text" value="Open" id="rfqstatus" name="rfqstatus" class="span10 form-field" readonly/>

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
                                                                    <input id="initiator_name" type="text" name="updatedby" id="updatedby" value="${username}" class="span10 form-field"/>
                                                                </div>
                                                            </div>																
                                                        </div>
                                                    </div>
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">Project Name/Number<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <input type="text" name="Projectnamecode" id="Projectnamecode" class="span10 form-field" required/>
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
                                                                    <input type="date" value="" name="opendate" id="opendate" class="span10 form-field" required/>
                                                                </div>
                                                            </div>
                                                        </div>

                                                    </div>
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">Close Date<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <input type="date" name="closedate" id="closedate" class="span10 form-field" required/>
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
                                                                    <input type="text" value="" name="timeleft" id="timeleft" class="span10" readonly/>
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
                                                                    <textarea id="description" name="description" rows="4" class="span10"></textarea>
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
                                                                    <textarea id="billtoaddress" name="billtoaddress" rows="4" class="span10 form-field" required></textarea>
                                                                </div>
                                                            </div>																
                                                        </div>
                                                    </div>
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">Ship to Address<span style="color: red;"> *</span> ( <input type="checkbox" id="sameasbilltoadd"/>Same as Bill to Address)</label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <!--<input type="text" value="" name="prd" id="prd" class="span10" required/>-->
                                                                    <textarea id="shiptoaddress" name="shiptoaddress" rows="4" class="span10 form-field" required></textarea>
                                                                </div>
                                                            </div>
                                                        </div>

                                                    </div>
                                                </div>

                                                <div class="row-fluid">
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">Cost Code<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <select id="costcode" name="costcode" class="span10 form-field" required>
                                                                        <option>Select</option>
                                                                        <c:forEach var="costCode" items="${rfeLOV.rfeCostCode}">
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
                                                                        <option>Select</option>
                                                                        <c:forEach var="style" items="${rfeLOV.rfeNegoStyle}">
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
                                                                    <input type="radio" name="paymentterms" id="paymentterms" value="30" checked> 30
                                                                    <div class="clearfix"></div>
                                                                    <input type="radio" name="paymentterms" id="paymentterms" value="45"> 45
                                                                    <div class="clearfix"></div>
                                                                    <input type="radio" name="paymentterms" id="paymentterms" value="60"> 60
                                                                </label>
                                                            </div>
                                                        </div>

                                                    </div>            

                                                </div>
                                                <!--                                                        <input type="submit" value="Submit" id="PurReqDetBtn">-->
                                                <!--                                                    </form>-->
                                            </div>
                                            <div class="step-pane" id="step2">

                                                <div class="row-fluid">
                                                    <div class="span4">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">Category<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <select id="categoryId" class="span10 mat_det_class" required>
                                                                        <option>Select</option>
                                                                        <c:forEach var="category" items="${rfeLOV.rfeCategory}">
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
                                                <div class="row-fluid">
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
                                                <div class="row-fluid">    
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
                                                                    <th></th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>

                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>


                                                <!--                                                    </form>-->
                                            </div>
                                            <div class="step-pane" id="step3">
                                                <div id="attDivId">
                                                    <div class="row-fluid">
                                                        <div class="span10">
                                                            <div class="control-group">

                                                                <div class="controls">
                                                                    <input type="file" id="id-input-file-2" class="span10" name="file"/>	

                                                                </div>																
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row-fluid">
                                                        <div class="span6">
                                                            <div class="control-group">
                                                                <label class="control-label" for="">Name</label>
                                                                <div class="controls">
                                                                    <input type="text" class="span10" name="name" id="name"/>

                                                                </div>																
                                                            </div>
                                                        </div>
                                                        <div class="span6">
                                                            <div class="control-group">
                                                                <label class="control-label" for="">Description</label>
                                                                <div class="controls">
                                                                    <textarea id="attDescription" name="attDescription" rows="4" class="span10" required></textarea>
                                                                    <!--                                                                            <button type="button" id="add_att_btn" class="btn btn-small btn-info pull-right">Add</button>-->
                                                                    <input type="button" value="Add" id="add_att_btn" class="btn btn-small btn-info pull-right"/>                        
                                                                </div>																
                                                            </div>
                                                        </div>
                                                    </div>

                                                </div>
                                                <div id="injectContent">

                                                </div>
                                                <h4><b>Clauses</b></h4>
                                                <hr/>
                                                <div class="row-fluid">
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <div class="controls">
                                                                <div id="clausesDiv">
                                                                    <label class="control-label" for=""><b>Available Clauses</b><span style="color: red;"> *</span></label>
                                                                    <select name="cars" id="rfqClausesAvail" size="8" name="rfqClausesAvail">            
                                                                        <c:forEach var="clauses" items="${rfeLOV.rfeClauses}">
                                                                            <option value="${clauses.clausesname}">${clauses.clausesname}</option>
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
                                                                    <select name="cars" multiple id="rfqClausesSelected" name="rfqClausesSelected" size="8" >            

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
                                                                    <select id="approval1" name="approval1" class="approvalsClass">
                                                                        <option>Select</option>
                                                                        <c:forEach var="approvals" items="${rfeLOV.rfeApprovals}">
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
                                                                    <label class="control-label" for="" id="approval1EmailId"></label>
                                                                </div>
                                                            </div>																
                                                        </div>
                                                    </div>
                                                    <div class="span3">
                                                        <div class="control-group">
                                                            <label class="control-label" for=""><b>Approval 2</b></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <select id="approval2" name="approval2" class="approvalsClass">
                                                                        <option>Select</option>
                                                                        <c:forEach var="approvals" items="${rfeLOV.rfeApprovals}">
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
                                                                    <label class="control-label" for="" id="approval2EmailId"></label>
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
                                                                    <select id="approval3" name="approval3" class="approvalsClass">
                                                                        <option>Select</option>
                                                                        <c:forEach var="approvals" items="${rfeLOV.rfeApprovals}">
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
                                                                    <label class="control-label" for="" id="approval3EmailId"></label>
                                                                </div>
                                                            </div>																
                                                        </div>
                                                    </div>
                                                    <div class="span3">
                                                        <div class="control-group">
                                                            <label class="control-label" for=""><b>Approval 4</b></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <select id="approval4" name="approval4" class="approvalsClass">
                                                                        <option>Select</option>
                                                                        <c:forEach var="approvals" items="${rfeLOV.rfeApprovals}">
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
                                                                    <label class="control-label" for="" id="approval4EmailId"></label>
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
                                                                    <select id="approval5" name="approval5" class="approvalsClass">
                                                                        <option>Select</option>
                                                                        <c:forEach var="approvals" items="${rfeLOV.rfeApprovals}">
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
                                                                    <label class="control-label" for="" id="approval5EmailId"></label>
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
                                                    <!--                                                    <div class="span6">
                                                                                                            <div class="control-group">
                                                                                                                <label class="control-label" for=""><b>Attachment</b></label>
                                                                                                                <label id="roAttachment" class="control-label"></label>																
                                                                                                            </div>
                                                                                                        </div>-->

                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for=""><b>Name</b></label>
                                                            <label id="roAttName" class="control-label"></label>																
                                                        </div>
                                                    </div>
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for=""><b>Description</b></label>
                                                            <label id="roAttDesc" class="control-label"></label>																
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row-fluid">



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
                                    <hr />
                                    <div class="row-fluid wizard-actions">
                                        <button class="btn btn-info btn-prev"><i class="icon-arrow-left"></i>Prev</button>
                                        <button class="btn btn-info btn-next" data-last="Finish " id="next_btn">Next
                                            <i class="icon-arrow-right icon-on-right"></i>
                                        </button>
                                        <!--                                                <button>Click</button>-->

                                    </div>
                                </div>
                            </div>
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
                    bootbox.dialog("Please add at least one category!", [{"label": "ok", "class": "btn-small btn-primary"}]);
                    return false;
                }

            } else if (info.step === 3) {
                var selectedClsLen = $("select#rfqClausesSelected option").length;
                if (selectedClsLen === 0)
                {
                    bootbox.dialog("Please select at least one Clause!", [{"label": "ok", "class": "btn-small btn-primary"}]);
                    return false;
                }
            } else if (info.step === 4) {
                var app1 = $("#approval1").val();
                if (app1 === 'Select')
                {
                    bootbox.dialog("Please select at least one approval!", [{"label": "ok", "class": "btn-small btn-primary"}]);
                    $("#approval1").css('border-color', 'red');
                    return false;
                }
                preview();
            }

        }).on('finished', function(e) {
            bootbox.dialog("Thank you! Your information was successfully saved!", [{
            "label" : "OK",
                    "class" : "btn-small btn-primary",
                    callback: function() {
                        //#("#PurReqDetBtn").click();
                        //location.href =  "registerRfpRequest.do"
                        $("#validation-emp").submit();
                    },
            }],
                    );
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
    function preview()
    {
        //alert("Preview");
        $("#roRFQTitle").text($("#RFQTitle").val());
        $("#roStatus").text($("#rfqstatus").val());
        $("#roDesc").text($("#description").val());
        $("#roOpenDate").text($("#opendate").val());
        $("#roCloseDate").text($("#closedate").val());
        $("#roTimeLeft").text($("#timeleft").val());
        $("#roBillAdd").text($("#billtoaddress").val());
        $("#roShipAdd").text($("#shiptoaddress").val());
        $("#roInitiator").text($("#updatedby").val());
        $("#roNegoStyle").text($("#Negotationstyle").val());
        $("#roCostCode").text($("#costcode").val());
        $("#roPayTerm").text($("#paymentterms").val());
        $("#roProjectName").text($("#Projectnamecode").val());

        $("#ro_cat_subcat_table tbody").empty();

        //var no_of_cat = $("#cat_subcat_table tbody tr").html();
        //alert(no_of_cat);
        //$("#cat_subcat_table thead tr").clone().appendTo("#ro_cat_subcat_table thead");
        $("#cat_subcat_table tbody tr").clone().appendTo("#ro_cat_subcat_table tbody");

        //$("#ro_cat_subcat_table thead tr").find("th:last-child").remove();
        $("#ro_cat_subcat_table tbody tr").find("td:last-child").remove();

        //alert(res);
        //$("#ro_cat_subcat_table tbody").append(no_of_cat);
        //$("#cat_subcat_table tbody").append(no_of_cat);
        //$("#cat_subcat_table").closest("tr").clone().appendTo("#ro_cat_subcat_table");


        var clauses = $("#rfeClauses").val();
        if (clauses !== '' || clauses !== null)
        {
            clauses = clauses.split('~');
            $("#roClauses").text(clauses)
//                    alert(clauses);
//                    clauses = clauses.substring(0, clauses.length - 1);
//                    alert(clauses);
        }
        if ($("#approval1 option:selected").html() !== 'Select')
        {
            $("#roApp1Name").text($("#approval1 option:selected").html());
        }
        $("#roApp1Email").text($("#approval1EmailId").text());

        if ($("#approval2 option:selected").html() !== 'Select')
        {
            $("#roApp2Name").text($("#approval2 option:selected").html());
        }
        $("#roApp2Email").text($("#approval2EmailId").text());

        if ($("#approval3 option:selected").html() !== 'Select')
        {
            $("#roApp3Name").text($("#approval3 option:selected").html());
        }
        $("#roApp3Email").text($("#approval3EmailId").text());

        if ($("#approval4 option:selected").html() !== 'Select')
        {
            $("#roApp4Name").text($("#approval4 option:selected").html());
        }
        $("#roApp4Email").text($("#approval4EmailId").text());

        if ($("#approval5 option:selected").html() !== 'Select')
        {
            $("#roApp5Name").text($("#approval5 option:selected").html());
        }
        $("#roApp5Email").text($("#approval5EmailId").text());

        $("#roAttName").text($("#name").val());
        $("#roAttDesc").text($("#attDescription").val());
        //alert($("#id-input-file-2")[0].files[0]);
//        if ($("#id-input-file-2")[0].files[0] !== undefined)
//        {
//            $("#roAttachment").text($("#id-input-file-2")[0].files[0]['name']);
//        }
    }
</script>
</body>
</html>

