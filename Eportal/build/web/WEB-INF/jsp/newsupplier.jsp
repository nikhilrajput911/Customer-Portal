<%-- 
    Document   : newsupplier
    Created on : 20 Jul, 2018, 2:30:51 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
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

        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.3/js/bootstrapValidator.min.js" type="text/javascript"></script>

        <script src="js/supplier.js"></script>
        <script src="js/supplier-validations.js"></script>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    </head>
    <body>
        <%@include file = "template.jsp" %>
        <div class="breadcrumbs" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="icon-book"></i><a href=""><span class="menu-text"> Supplier Management </span></a>
                    <span class="divider"><i class="icon-angle-right arrow-icon"></i></span>
                </li>
                <li class="active">Register Supplier</li>						
            </ul>
        </div>
        <div class="page-content">					
            <div class="row-fluid">
                <div class="col-md-12">
                    <div class="widget-box">
                        <div class="widget-header widget-header-blue widget-header-flat">
                            <h4 class="lighter">Supplier Registration</h4>
                        </div>
                        <div class="widget-body">
                            <div class="widget-main">
                                <div class="row-fluid">
                                    <div id="fuelux-wizard" class="row-fluid" data-target="#step-container">
                                        <ul class="wizard-steps">
                                            <li data-target="#step1" class="active">
                                                <span class="step">1</span>
                                                <span class="title">Supplier Information</span>
                                            </li>
                                            <li data-target="#step2">
                                                <span class="step">2</span>
                                                <span class="title">Contact Details</span>
                                            </li>
                                            <li data-target="#step3">
                                                <span class="step">3</span>
                                                <span class="title">Supplier Bank Details</span>
                                            </li>
                                            <li data-target="#step4">
                                                <span class="step">4</span>
                                                <span class="title">Attachment</span>
                                            </li>
                                            <li data-target="#step5">
                                                <span class="step">5</span>
                                                <span class="title">Preview</span>
                                            </li>


                                        </ul>
                                    </div>
                                    <hr/>
                                    <div class="step-content row-fluid position-relative" id="step-container">
                                        <form class="form-vertical" id="validation-emp" method="post" action="registerSupplier.do" enctype="multipart/form-data">
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
<!--                                            <input type="hidden" value="${username}" id="updatedby" name="updatedby">
<input type="hidden" value="${username}" id="createdby" name="createdby">-->
                                            <input type="hidden" id="check_constraint" value="true"/>
                                            
                                            <div class="step-pane active" id="step1">
                                                <div class="row-fluid">
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">Company Name<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <input type="text" name="companyname" id="companyname" class="span10 supplier-form-field" required/>
                                                                </div>
                                                            </div>																
                                                        </div>
                                                    </div>
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">Type of Business<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <select id="typeofbusiness" name="typeofbusiness" class="span10 supplier-form-field" required>
                                                                        <option>Select</option>
                                                                        <c:forEach var="business" items="${supplierLOV.bussType}">
                                                                            <option>${business.typeofbusiness}</option>
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
                                                            <label class="control-label" for="">Registered Address<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <!--<input type="text" name="pr" id="pr" class="span10" required/>-->
                                                                    <textarea id="registeredaddress" name="registeredaddress" rows="4" class="span10 supplier-form-field" required></textarea>

                                                                </div>
                                                            </div>																
                                                        </div>
                                                    </div>
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">Official Website<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <input type="text" name="website" id="website" class="span10 supplier-form-field" required/>
                                                                </div>
                                                            </div>																
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row-fluid">

                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">Owner Name<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <input type="text" id="ownername" name="ownername" class="span10 supplier-form-field" required/>

                                                                </div>
                                                            </div>
                                                        </div>

                                                    </div>
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">Owner Mobile Number<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <input type="text" name="ownermobilenumber" id="ownermobilenumber" class="span10 supplier-form-field" required/>
                                                                </div>
                                                            </div>																
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row-fluid">

                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">Owner Email-ID<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <input type="text" id="owneremailid" name="owneremailid" class="span10 supplier-form-field" required/>

                                                                </div>
                                                            </div>
                                                        </div>

                                                    </div>
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">Staff Strength<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <input type="text" id="staffstrength" name="staffstrength" class="span10 supplier-form-field" required/>

                                                                </div>
                                                            </div>
                                                        </div>

                                                    </div>
                                                </div>
                                                <div class="row-fluid">
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">Company PAN Number<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <input type="text" name="companypannumber" id="companypannumber" class="span10 supplier-form-field" required/>
                                                                </div>
                                                            </div>																
                                                        </div>
                                                    </div>
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">PF Reg. Number<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <input type="text" id="pfregnumber" name="pfregnumber" class="span10 supplier-form-field" required/>

                                                                </div>
                                                            </div>
                                                        </div>

                                                    </div>
                                                </div>
                                                <div class="row-fluid">
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="suppliercountry">Country<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <!--<textarea rows="4"></textarea>-->
                                                                    <select id="suppliercountry" name="country" class="span10 supplier-form-field" required>
                                                                        <option>Select</option>
                                                                        <c:forEach var="cntry" items="${supplierLOV.userCntry}">
                                                                            <option>${cntry.countryName}</option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">Username<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <input type="text" name="username" id="username" class="span10 supplier-form-field" required/>
                                                                </div>
                                                            </div>																
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row-fluid">
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">Password<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <input type="password" id="password" name="password" class="span10 supplier-form-field" required/>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">Confirm Password<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <input type="password" id="confirmpassword" name="confirmpassword" class="span10 supplier-form-field" required/>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row-fluid">
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">GST State<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <select id="gstState" name="gstState" class="span10 supplier-form-field" required>
                                                                        <option>Select</option>
                                                                        <c:forEach var="state" items="${supplierLOV.gstState}">
                                                                            <option>${state.gstState}</option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </div>
                                                            </div>																
                                                        </div>
                                                    </div>
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">GST Number<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <input type="text" id="gstnumber" name="gstnumber" class="span10 supplier-form-field" required/>

                                                                </div>
                                                            </div>
                                                        </div>

                                                    </div>
                                                </div>
                                            </div>
                                            <div class="step-pane" id="step2">
                                                <div class="row-fluid">
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="businessaddress">Business Address<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <!--<input type="text" value="" name="prd" id="prd" class="span10" required/>-->
                                                                    <textarea id="businessaddress" name="businessaddress" rows="4" class="span10 contact-details" required></textarea>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="businesscountry">State<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <select id="businesscountry" name="businesscountry" class="span10 contact-details" required>
                                                                        <option>Select</option>
                                                                        <c:forEach var="cntry" items="${supplierLOV.userCntry}">
                                                                            <option>${cntry.countryName}</option>
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
                                                            <label class="control-label" for="">Contact Person Name<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <input type="text" name="contactperson2" id="contactperson2" class="span10 contact-details" required/>
                                                                </div>
                                                            </div>																
                                                        </div>
                                                    </div>
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">Contact Person Email-Id<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <input type="text" name="contact2emailaddress" id="contact2emailaddress" class="span10 contact-details" required/>
                                                                </div>
                                                            </div>																
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row-fluid">

                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">Contact Person Mobile Number<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <input type="text" id="contact2mobilenumber" name="contact2mobilenumber" class="span10 contact-details" required/>

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
                                                                    <button center type="button" id="add_supplier_cnct_details" class="btn btn-small btn-info">Add</button> 
                                                                    <button center type="button" id="clear_supplier_cnct_details" class="btn btn-small btn-danger">Clear</button> 
                                                                </div>
                                                            </div>																
                                                        </div>
                                                    </div>

                                                </div>
                                                <div class="row-fluid">
                                                    <div class="span12">
                                                        <table id="supplier_cnct_details_table" name="supplier_cnct_details_table" class="table table-striped table-bordered table-hover">
                                                            <thead>
                                                                <tr>
                                                                    <th>Business Address</th>
                                                                    <th>State</th>
                                                                    <th>Contact Person Name</th>
                                                                    <th>Contact Person Email-Id</th>
                                                                    <th>Contact Person Mob.</th>
                                                                    <th></th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>

                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                                <div id="supplier_contact_details_div">

                                                </div>
                                            </div>
                                            <div class="step-pane" id="step3">
                                                <div class="row-fluid">
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">Bank Name<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <input type="text" id="bankname" name="bankname" class="span10 bank-details" required/>

                                                                </div>
                                                            </div>
                                                        </div>

                                                    </div>

                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">Account Type<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <input type="text" id="bankaccounttype" name="bankaccounttype" class="span10 bank-details" required/>

                                                                </div>
                                                            </div>
                                                        </div>

                                                    </div>
                                                </div>
                                                <div class="row-fluid">
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">Bank Account Number<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <input type="text" id="bankaccountnumber" name="bankaccountnumber" class="span10 bank-details" required/>

                                                                </div>
                                                            </div>																
                                                        </div>
                                                    </div>
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">Confirm Bank Account Number<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <input type="text" id="confirmaccountnumber" name="confirmaccountnumber" class="span10 bank-details" required/>

                                                                </div>
                                                            </div>																
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row-fluid">
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">Branch Name<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <input type="text" id="bankbranchname" name="bankbranchname" class="span10 bank-details" required/>

                                                                </div>
                                                            </div>
                                                        </div>

                                                    </div>
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">Bank Address<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <input type="text" id="bankaddress" name="bankaddress" class="span10 bank-details" required/>

                                                                </div>
                                                            </div>																
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row-fluid">
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">Pin Code<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <input type="text" id="pincode" name="pincode" class="span10 bank-details" required/>

                                                                </div>
                                                            </div>
                                                        </div>

                                                    </div>
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">IFSC Code<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <input type="text" id="ifsccode" name="ifsccode" class="span10 bank-details" required/>

                                                                </div>
                                                            </div>																
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row-fluid">
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">MICR Number<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <input type="text" id="micrnumber" name="micrnumber" class="span10 bank-details" required/>

                                                                </div>
                                                            </div>
                                                        </div>

                                                    </div>
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">Vendor’s Name (as per bank records)<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <input type="text" id="vendornameAsperbankrecord" name="vendornameAsperbankrecord" class="span10 bank-details" required/>

                                                                </div>
                                                            </div>																
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row-fluid">
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">Currency<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <select type="text" id="bankcurrency" name="bankcurrency" class="span10 bank-details" required>
                                                                        <option>Select</option>
                                                                        <c:forEach var="currency" items="${supplierLOV.currency}">
                                                                            <option>${currency.code}</option>
                                                                        </c:forEach>
                                                                    </select>
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
                                                                    <button center type="button" id="add_supplier_bank_details" class="btn btn-small btn-info">Add</button> 
                                                                    <button center type="button" id="clear_supplier_bank_details" class="btn btn-small btn-danger">Clear</button> 
                                                                </div>
                                                            </div>																
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row-fluid">
                                                    <div class="span12">
                                                        <table id="supplier_bank_details_table" name="supplier_bank_details_table" class="table table-striped table-bordered table-hover">
                                                            <thead>
                                                                <tr>
                                                                    <th>Bank Name</th>
                                                                    <th>Account Type</th>
                                                                    <th>Bank Account No.</th>
                                                                    <th>Branch Name</th>
                                                                    <th>Branch Address</th>
                                                                    <th>Pin Code</th>
                                                                    <th>IFSC Code</th>
                                                                    <th>MICR No.</th>
                                                                    <th>Vendor's Name</th>
                                                                    <th>Currency</th>
                                                                    <th></th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>

                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                                <div id="supplier_bank_details_div">

                                                </div>
                                            </div>
                                            <div class="step-pane" id="step4">
                                                <div class="row-fluid">
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">PAN Card<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <input type="file" id="id-input-file-1" class="span10" name="pancard"/>	

                                                                </div>
                                                            </div>
                                                        </div>

                                                    </div>
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">Canceled Cheque<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <input type="file" id="id-input-file-2" class="span10" name="cancelledcheque"/>	
                                                                </div>
                                                            </div>																
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row-fluid">
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">Company Incorporation Letter<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <input type="file" id="id-input-file-3" class="span10" name="compnanyincopletter"/>	

                                                                </div>
                                                            </div>
                                                        </div>

                                                    </div>
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">GST Certificate<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <input type="file" id="id-input-file-4" class="span10" name="gstcertificate"/>	
                                                                </div>
                                                            </div>																
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row-fluid">
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">other</label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <input type="file" id="id-input-file-5" class="span10" name="otherfile"/>	

                                                                </div>
                                                            </div>
                                                        </div>

                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                        <div class="step-pane" id="step5">
                                            <h4 style="color: #005580;"><b>Supplier Information</b></h4>
                                            <hr>
                                            <form class="form-horizontal">
                                                <div class="row-fluid">
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for=""><b>Company Name</b></label>
                                                            <label id="roCompanyname" class="control-label"></label>
                                                        </div>
                                                    </div>
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for=""><b>Type Of Business</b></label>
                                                            <label id="roTypeofbusiness" class="control-label"></label>																
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row-fluid">
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for=""><b>Registered Address</b></label>
                                                            <label id="roRegisteredaddress" class="control-label"></label>																
                                                        </div>
                                                    </div>

                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for=""><b>Staff Strength</b></label>
                                                            <label id="roStaffstrength" class="control-label"></label>																
                                                        </div>
                                                    </div>
                                                </div>    

                                                <div class="row-fluid">    
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for=""><b>Official Website</b></label>
                                                            <label id="roWebsite" class="control-label"></label>																
                                                        </div>
                                                    </div>
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for=""><b>Owner Name</b></label>
                                                            <label id="roOwnername" class="control-label"></label>																
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row-fluid">
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for=""><b>Owner Mobile Number</b></label>
                                                            <label id="roOwnermobilenumber" class="control-label"></label>																
                                                        </div>
                                                    </div>
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for=""><b>Owner Email-ID</b></label>
                                                            <label id="roOwneremailid" class="control-label"></label>																
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="row-fluid">

                                                </div>
                                                <div class="row-fluid">
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for=""><b>Company PAN Number</b></label>
                                                            <label id="roCompanypannumber" class="control-label"></label>																
                                                        </div>
                                                    </div>
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for=""><b>PF Reg. Number</b></label>
                                                            <label id="roPfregnumber" class="control-label"></label>																
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row-fluid">
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for=""><b>Username</b></label>
                                                            <label id="roUsername" class="control-label"></label>																
                                                        </div>
                                                    </div>
                                                    <!--                                                    <div class="span6">
                                                                                                            <div class="control-group">
                                                                                                                <label class="control-label" for=""><b>Supplier Status</b></label>
                                                                                                                <label id="roSupplierstatus" class="control-label"></label>																
                                                                                                            </div>
                                                                                                        </div>-->
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for=""><b>GST Number</b></label>
                                                            <label id="roGstnumber" class="control-label"></label>																
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row-fluid">

                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for=""><b>GST State</b></label>
                                                            <label id="roGst_state" class="control-label"></label>																
                                                        </div>
                                                    </div>
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for=""><b>Country</b></label>
                                                            <label id="roSupplier_Cuntry" class="control-label"></label>																
                                                        </div>
                                                    </div>
                                                </div>
                                                <h4 style="color: #005580;"><b>Contact Details</b></h4>
                                                <hr>
                                                <div class="row-fluid">
                                                    <div class="span12">
                                                        <table id="ro_contact_details_table" name="ro_contact_details_table" class="table table-striped table-bordered table-hover">
                                                            <thead>
                                                                <tr>
                                                                    <th>Business Address</th>
                                                                    <th>State</th>
                                                                    <th>Contact Person Name</th>
                                                                    <th>Contact Person Email-Id</th>
                                                                    <th>Contact Person Mob.</th>

                                                                </tr>
                                                            </thead>
                                                            <tbody>

                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                                <h4 style="color: #005580;"><b>Supplier Bank Details</b></h4>
                                                <hr>
                                                <div class="row-fluid">
                                                    <div class="span12">
                                                        <table id="ro_bank_details_table" name="ro_bank_details_table" class="table table-striped table-bordered table-hover">
                                                            <thead>
                                                                <tr>
                                                                    <th>Bank Name</th>
                                                                    <th>Account Type</th>
                                                                    <th>Bank Account No.</th>
                                                                    <th>Branch Name</th>
                                                                    <th>Branch Address</th>
                                                                    <th>Pin Code</th>
                                                                    <th>IFSC Code</th>
                                                                    <th>MICR No.</th>
                                                                    <th>Vendor's Name</th>
                                                                    <th>Currency</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>

                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                                <!--                                                <h4 style="color: #005580;"><b>Attachments</b></h4>
                                                                                                <hr>
                                                                                                <div class="row-fluid">
                                                                                                    <div class="span6">
                                                                                                        <div class="control-group">
                                                                                                            <label class="control-label" for=""><b>Pan Card</b></label>
                                                                                                            <label id="roPanCard" class="control-label"></label>																
                                                                                                        </div>
                                                                                                    </div>
                                                                                                    <div class="span6">
                                                                                                        <div class="control-group">
                                                                                                            <label class="control-label" for=""><b>Cancelled Cheque</b></label>
                                                                                                            <label id="roCancelledCheque" class="control-label"></label>																
                                                                                                        </div>
                                                                                                    </div>
                                                                                                </div>
                                                                                                <div class="row-fluid">
                                                                                                    <div class="span6">
                                                                                                        <div class="control-group">
                                                                                                            <label class="control-label" for=""><b>Company Incorporation Letter</b></label>
                                                                                                            <label id="roCompanyIncorpLetter" class="control-label"></label>																
                                                                                                        </div>
                                                                                                    </div>
                                                                                                    <div class="span6">
                                                                                                        <div class="control-group">
                                                                                                            <label class="control-label" for=""><b>GST Certificate</b></label>
                                                                                                            <label id="roGstCertificate" class="control-label"></label>																
                                                                                                        </div>
                                                                                                    </div>
                                                                                                </div>
                                                                                                <div class="row-fluid">
                                                                                                    <div class="span6">
                                                                                                        <div class="control-group">
                                                                                                            <label class="control-label" for=""><b>Other</b></label>
                                                                                                            <label id="roOtherDoc" class="control-label"></label>																
                                                                                                        </div>
                                                                                                    </div>
                                                                                                </div>-->
                                            </form>
                                        </div>

                                    </div>
                                    <hr />
                                    <div class="row-fluid wizard-actions">
                                        <button class="btn btn-purple btn-prev btn-small"><i class="icon-arrow-left"></i>Prev</button>
                                        <button class="btn btn-purple btn-next btn-small" data-last="Register " id="next_btn">Next
                                            <i class="icon-arrow-right icon-on-right"></i>
                                        </button>

                                    </div>
                                    <br>
                                </div>
                            </div>
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
                        preview1();
                    } else if (info.step === 2) {
                        preview2();
                        var no_of_contact = $("#supplier_cnct_details_table tr").length;
                        if (no_of_contact === 1)
                        {
                            bootbox.dialog("Please add at least one contact details!", [{"label": "ok", "class": "btn-small btn-primary"}]);
                            return false;
                        }

                    } else if (info.step === 3) {
                        preview3();
                        
                        var bank_details = $("#supplier_bank_details_table tr").length;
                        if (bank_details === 1)
                        {
                            bootbox.dialog("Please add at least one bank details!", [{"label": "ok", "class": "btn-small btn-primary"}]);
                            return false;
                        }
                    } else if (info.step === 4) {
                        //preview4();
//                        alert($("#id-input-file-1")[0].files[0]);
                        if ($("#id-input-file-1")[0].files[0] === undefined)
                        {
                            bootbox.dialog("Please choose pan card file", [{"label": "ok", "class": "btn-small btn-primary"}]);
                            return false;
                        }
                        if ($("#id-input-file-2")[0].files[0] === undefined)
                        {
                            bootbox.dialog("Please choose cancelled cheque file", [{"label": "ok", "class": "btn-small btn-primary"}]);
                            return false;
                        }
                        if ($("#id-input-file-3")[0].files[0] === undefined)
                        {
                            bootbox.dialog("Please choose company incorporation letter file", [{"label": "ok", "class": "btn-small btn-primary"}]);
                            return false;
                        }
                        if ($("#id-input-file-4")[0].files[0] === undefined)
                        {
                            bootbox.dialog("Please choose gst certificate file", [{"label": "ok", "class": "btn-small btn-primary"}]);
                            return false;
                        }
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
                            //location.href = "register.do";
//                    $("#validation-emp").submit();
                }).on('stepclick', function(e) {
                    //return false;//prevent clicking on steps
                });

                $('.dialogs,.comments').slimScroll({
                    height: '280px'
                });
                $('#id-input-file-1, #id-input-file-2, #id-input-file-3, #id-input-file-4, #id-input-file-5').ace_file_input({
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
            function preview1()
            {
                //alert("Preview");
                $("#roCompanyname").text($("#companyname").val());
                $("#roTypeofbusiness").text($("#typeofbusiness").val());
                $("#roRegisteredaddress").text($("#registeredaddress").val());
                $("#roWebsite").text($("#website").val());
                $("#roOwnername").text($("#ownername").val());
                $("#roOwnermobilenumber").text($("#ownermobilenumber").val());
                $("#roOwneremailid").text($("#owneremailid").val());
                $("#roStaffstrength").text($("#staffstrength").val());
                $("#roCompanypannumber").text($("#companypannumber").val());
                $("#roPfregnumber").text($("#pfregnumber").val());
                $("#roUsername").text($("#username").val());
                $("#roSupplierstatus").text($("#supplierstatus").val());
                $("#roGstnumber").text($("#gstnumber").val());
                $("#roGst_state").text($("#gstState").val());
                $("#roSupplier_Cuntry").text($("#suppliercountry").val());

            }
            function preview2()
            {
                $("#ro_contact_details_table tbody").empty();
                $("#supplier_cnct_details_table tbody tr").clone().appendTo("#ro_contact_details_table tbody");
                $("#ro_contact_details_table tbody tr").find("td:last-child").remove();
            }
            function preview3()
            {
                $("#ro_bank_details_table tbody").empty();
                $("#supplier_bank_details_table tbody tr").clone().appendTo("#ro_bank_details_table tbody");
                $("#ro_bank_details_table tbody tr").find("td:last-child").remove();

            }
//            function preview4()
//            {
//                if ($("#id-input-file-1")[0].files[0] !== undefined)
//                {
//                    $("#roPanCard").text($("#id-input-file-1")[0].files[0]['name']);
//                }
//                if ($("#id-input-file-2")[0].files[0] !== undefined)
//                {
//                    $("#roCancelledCheque").text($("#id-input-file-2")[0].files[0]['name']);
//                }
//                if ($("#id-input-file-3")[0].files[0] !== undefined)
//                {
//                    $("#roCompanyIncorpLetter").text($("#id-input-file-3")[0].files[0]['name']);
//                }
//                if ($("#id-input-file-4")[0].files[0] !== undefined)
//                {
//                    $("#roGstCertificate").text($("#id-input-file-4")[0].files[0]['name']);
//                }
//                if ($("#id-input-file-5")[0].files[0] !== undefined)
//                {
//                    $("#roOtherDoc").text($("#id-input-file-5")[0].files[0]['name']);
//                }
//            }
        </script>
    </body>
</html>
