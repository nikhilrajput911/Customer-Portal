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

        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js" type="text/javascript"></script>
        <!--<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.3/js/bootstrapValidator.min.js" type="text/javascript"></script>-->
        <!--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/boots/trap.min.js"></script>-->
        <script src="js/supplier.js"></script>


        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    </head>
    <body onload="onLoadFunction();">
        <%@include file = "template.jsp" %>
        <div class="page-content">					
            <div class="row-fluid">
                <div class="col-md-12">
                    <div class="widget-box">
                        <div class="widget-header widget-header-blue widget-header-flat">
                            <h4 class="lighter">Supplier Details</h4>
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
                                                <span class="title">Bank Details</span>
                                            </li>
                                            <li data-target="#step4">
                                                <span class="step">4</span>
                                                <span class="title">Attachments</span>
                                            </li>

                                        </ul>
                                    </div>
                                    <hr/>
                                    <div class="step-content row-fluid position-relative" id="step-container">
                                        <form class="form-vertical" id="validation-emp" method="post" action="updateSupplier.do">
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                            <input type="hidden" id="check_constraint" value="true"/>
                                            <div class="step-pane active" id="step1">
                                                <div class="row-fluid">

                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">Supplier Status<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <input type="text" id="ro_supplierstatus" name="ro_supplierstatus" class="span10 add-style" value="${supplierDetails.supplier.supplierstatus}" required/>
                                                                    <select id="supplierstatus" name="supplierstatus" class="span10 remove-style" style="display: none;" required>
                                                                        <option>Select</option>
                                                                        <c:forEach var="status" items="${supplierDetails.suppStatus}">
                                                                            <option>${status.supplierstatus}</option>
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
                                                            <label class="control-label" for="">Company Name<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <input type="hidden" value="${supplierDetails.supplier.id}" id="id" name="id">
                                                                    <input value="${supplierDetails.supplier.companyname}" type="text" name="companyname" id="companyname" class="span10" required/>
                                                                </div>
                                                            </div>																
                                                        </div>
                                                    </div>
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">Type of Business<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <input value="${supplierDetails.supplier.typeofbusiness}" type="text" name="ro_typeofbusiness" id="ro_typeofbusiness" class="span10 add-style" required/>
                                                                    <select id="typeofbusiness" name="typeofbusiness" class="span10 remove-style" style="display: none;" required>
                                                                        <option>Select</option>
                                                                        <c:forEach var="business" items="${supplierDetails.bussType}">
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
                                                                    <textarea style="background-color: #f2f2f2;" id="registeredaddress" name="registeredaddress" rows="4" class="span10 remove-style" required>${supplierDetails.supplier.registeredaddress}</textarea>

                                                                </div>
                                                            </div>																
                                                        </div>
                                                    </div>
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">Official Website<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <input type="text" name="website" id="website" class="span10" value="${supplierDetails.supplier.website}" required/>
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
                                                                    <input type="text" id="ownername" name="ownername" class="span10" value="${supplierDetails.supplier.ownername}" required/>

                                                                </div>
                                                            </div>
                                                        </div>

                                                    </div>
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">Owner Mobile Number<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <input type="text" name="ownermobilenumber" id="ownermobilenumber" class="span10" value="${supplierDetails.supplier.ownermobilenumber}" required/>
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
                                                                    <input type="text" id="owneremailid" name="owneremailid" class="span10" value="${supplierDetails.supplier.owneremailid}" required/>

                                                                </div>
                                                            </div>
                                                        </div>

                                                    </div>
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">Staff Strength<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <input type="text" id="staffstrength" name="staffstrength" class="span10" value="${supplierDetails.supplier.staffstrength}" required/>

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
                                                                    <input type="text" name="companypannumber" id="companypannumber" class="span10" value="${supplierDetails.supplier.companypannumber}" required/>
                                                                </div>
                                                            </div>																
                                                        </div>
                                                    </div>
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">PF Reg. Number<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <input type="text" id="pfregnumber" name="pfregnumber" class="span10" value="${supplierDetails.supplier.pfregnumber}" required/>

                                                                </div>
                                                            </div>
                                                        </div>

                                                    </div>
                                                </div>
                                                <div class="row-fluid">
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">Country<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <input type="text" name="ro_country" id="ro_country" class="span10 add-style" value="${supplierDetails.supplier.country}" required/>
                                                                    <select id="country" name="country" class="span10 remove-style" style="display: none;" required>
                                                                        <option>Select</option>
                                                                        <c:forEach var="cntry" items="${supplierDetails.suppCntry}">
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
                                                                    <input type="text" name="username" id="username" class="span10" value="${supplierDetails.supplier.username}" required/>
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
                                                                    <input type="password" id="password" name="password" class="span10" value="${supplierDetails.supplier.password}" required/>

                                                                </div>
                                                            </div>
                                                        </div>

                                                    </div>
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">Confirm Password<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <input type="password" id="confirmpassword" name="confirmpassword" class="span10" value="${supplierDetails.supplier.confirmpassword}" required/>

                                                                </div>
                                                            </div>
                                                        </div>

                                                    </div>
                                                </div>

                                                <div class="row-fluid">
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">GST Number<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <input type="text" id="gstnumber" name="gstnumber" class="span10" value="${supplierDetails.supplier.gstnumber}" required/>

                                                                </div>
                                                            </div>
                                                        </div>

                                                    </div>
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">GST State<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <div class="">
                                                                    <input type="text" id="ro_gstState" name="ro_gstState" class="span10 add-style" value="${supplierDetails.supplier.gstState}" required/>
                                                                    <select id="gstState" name="gstState" class="span10 remove-style" style="display: none;" required>
                                                                        <option>Select</option>
                                                                        <c:forEach var="state" items="${supplierDetails.gstState}">
                                                                            <option>${state.gstState}</option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </div>
                                                            </div>																
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="step-pane" id="step2">
                                                <div class="row-fluid">
                                                    <div class="span12">
                                                        <table id="supplier_contact_details_table" name="supplier_contact_details_table" class="table table-striped table-bordered table-hover">
                                                            <thead>
                                                                <tr>
                                                                    <th>Business Address</th>
                                                                    <th>State</th>
                                                                    <th>Contact Person Name</th>
                                                                    <th>Contact Person Email-Id</th>
                                                                    <th>Contact Person Mob.</th>
                                                                    <th class="show-field" style="display: none;"></th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <c:forEach var="contact" items="${supplierDetails.suppConatct}">
                                                                    <tr>
                                                                        <td>${contact.businessaddress}</td>
                                                                        <td>${contact.state}</td>
                                                                        <td>${contact.contactperson2}</td>
                                                                        <td>${contact.contactperson2}</td>
                                                                        <td>${contact.contact2mobilenumber}</td>
                                                                        <!--<td class="show-field" style="display: none;"><button type='button' id='remove_cat_sub' class='btn btn-small btn-danger'><i class='icon-remove'></i></button></td>-->
                                                                    </tr>
                                                                </c:forEach>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="step-pane" id="step3">
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
                                                                    <th class="show-field" style="display: none;"></th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <c:forEach var="bank" items="${supplierDetails.supplierBankDetails}">
                                                                    <tr>
                                                                        <td>${bank.bankname}</td>
                                                                        <td>${bank.bankaccounttype}</td>
                                                                        <td>${bank.bankaccountnumber}</td>
                                                                        <td>${bank.bankbranchname}</td>
                                                                        <td>${bank.bankaddress}</td>
                                                                        <td>${bank.pincode}</td>
                                                                        <td>${bank.ifsccode}</td>
                                                                        <td>${bank.micrnumber}</td>
                                                                        <td>${bank.vendornameAsperbankrecord}</td>
                                                                        <td>${bank.currency}</td>
                                                                        <!--<td class="show-field" style="display: none;"><button type='button' id='remove_cat_sub' class='btn btn-small btn-danger'><i class='icon-remove'></i></button></td>-->
                                                                    </tr>
                                                                </c:forEach>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="step-pane" id="step4">
                                                <div class="row-fluid">
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">PAN Card<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <a href="downloadSupplierAttachment.do?attmtId=${supplierDetails.supplierAtt.id}&type=pancard">${supplierDetails.supplierAtt.pancardName}</a>

                                                            </div>																
                                                        </div>
                                                    </div>
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">Cancelled Cheque<span style="color: red;"> *</span></label>
                                                            <div class="controls">
                                                                <a href="downloadSupplierAttachment.do?attmtId=${supplierDetails.supplierAtt.id}&type=cancelledcheque">${supplierDetails.supplierAtt.cancelledchequeName}</a>

                                                            </div>																
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row-fluid">
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">Company Incorporation Letter<span style="color: red;"> *</span></label>
                                                            <div class="controls">

                                                                <a href="downloadSupplierAttachment.do?attmtId=${supplierDetails.supplierAtt.id}&type=companyincorpletter">${supplierDetails.supplierAtt.cmpnyincorpletterName}</a>

                                                            </div>																
                                                        </div>
                                                    </div>
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">GST Certificate<span style="color: red;"> *</span></label>
                                                            <div class="controls">

                                                                <a href="downloadSupplierAttachment.do?attmtId=${supplierDetails.supplierAtt.id}&type=gstcertificate">${supplierDetails.supplierAtt.gstcertificateName}</a>

                                                            </div>																
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row-fluid">
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for="">Other</label>
                                                            <div class="controls">
                                                                    <a href="downloadSupplierAttachment.do?attmtId=${supplierDetails.supplierAtt.id}&type=other">${supplierDetails.supplierAtt.otherName}</a>
                                                                
                                                            </div>																
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>                        
                                        </form>
                                        <div class="step-pane" id="step3">
                                            <h4 style="color: #005580;"><b>Supplier Details</b></h4>
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
                                                            <label class="control-label" for=""><b>Business Address</b></label>
                                                            <label id="roBusinessaddress" class="control-label"></label>																
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
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for=""><b>Contact Person Name</b></label>
                                                            <label id="roContactperson2" class="control-label"></label>																
                                                        </div>
                                                    </div>
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for=""><b>Contact Person Mobile Number</b></label>
                                                            <label id="roContact2mobilenumber" class="control-label"></label>																
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row-fluid">
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for=""><b>Contact Person Email-Id</b></label>
                                                            <label id="roContact2emailaddress" class="control-label"></label>																
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
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for=""><b>Supplier Status</b></label>
                                                            <label id="roSupplierstatus" class="control-label"></label>																
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row-fluid">
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for=""><b>GST Number</b></label>
                                                            <label id="roGstnumber" class="control-label"></label>																
                                                        </div>
                                                    </div>
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for=""><b>GST State</b></label>
                                                            <label id="roGst_state" class="control-label"></label>																
                                                        </div>
                                                    </div>
                                                </div>
                                                <h4 style="color: #005580;"><b>Supplier Bank Details</b></h4>
                                                <hr>
                                                <div class="row-fluid">
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for=""><b>Bank Name</b></label>
                                                            <label id="roBankname" class="control-label"></label>																
                                                        </div>
                                                    </div>
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for=""><b>Bank Account Number</b></label>
                                                            <label id="roBankaccountnumber" class="control-label"></label>																
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row-fluid">
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for=""><b>Account Type</b></label>
                                                            <label id="roBankaccounttype" class="control-label"></label>																
                                                        </div>
                                                    </div>
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for=""><b>Vendor’s Name (as per bank records)</b></label>
                                                            <label id="roVendorname_asperbankrecord" class="control-label"></label>																
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row-fluid">
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for=""><b>Branch Name</b></label>
                                                            <label id="roBankbranchname" class="control-label"></label>																
                                                        </div>
                                                    </div>
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for=""><b>Bank Address</b></label>
                                                            <label id="roBankaddress" class="control-label"></label>																
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row-fluid">
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for=""><b>Pin Code</b></label>
                                                            <label id="roPincode" class="control-label"></label>																
                                                        </div>
                                                    </div>
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for=""><b>IFSC Code</b></label>
                                                            <label id="roIfsccode" class="control-label"></label>																
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row-fluid">
                                                    <div class="span6">
                                                        <div class="control-group">
                                                            <label class="control-label" for=""><b>MICR Number</b></label>
                                                            <label id="roMicrnumber" class="control-label"></label>																
                                                        </div>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>

                                    </div>
                                    <hr />
                                    <div class="row-fluid wizard-actions">
                                        <button class="btn btn-purple btn-prev btn-small"><i class="icon-arrow-left"></i>Prev</button>
                                        <button class="btn btn-purple btn-next btn-small" data-last="Close " id="next_btn">Next
                                            <i class="icon-arrow-right icon-on-right"></i>
                                        </button>

                                    </div>
                                    <br>
                                    <div id="editUpdateDiv" class="row-fluid align-center">
                                        <button id="edit_btn_id" class="btn btn-pink">Edit</button>
                                        <button id="update_btn_id" class="btn btn-purple remove-style" style="display: none;">Update</button>
                                        <button id="close_btn_id" class="btn btn-inverse">Close</button>
                                    </div>
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
        <script src="js/supplier.js"></script>
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
                        //preview();
//                        var no_of_cat = $("#cat_subcat_table tr").length;
//                        if (no_of_cat === 1)
//                        {
//                            //                            bootbox.dialog("Please add at least one category!", [{"label": "ok", "class": "btn-small btn-primary"}]);
//                            //                            return false;
//                        }
//                        var selectedClsLen = $("select#rfqClausesSelected option").length;
//                        if (selectedClsLen === 0)
//                        {
//                            //                            bootbox.dialog("Please select at least one Clause!", [{"label": "ok", "class": "btn-small btn-primary"}]);
//                            //                            return false;
//                        }
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
                    //location.href = "register.do";
                    //$("#validation-emp").submit();
                    location.href = "registeredSupplier.do";
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
            function onLoadFunction()
            {
                $("#validation-emp input, #validation-emp textarea").attr("readonly", true);
                //$("##validation-emp textarea").css('background-color' , '#FF0000');
            }


        </script>
    </body>
</html>
