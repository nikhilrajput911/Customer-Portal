$(document).ready(function() {

    //alert("dsf");

    $("#edit_btn_id").click(function() {
        $("#validation-emp input, #validation-emp textarea").attr("readonly", false);
        $(".remove-style").removeAttr("style");
        $(".add-style").css("display", "none");

        var business = $("#ro_typeofbusiness").val();
        //alert(business);
        $("#typeofbusiness").val(business);

        $("#supplierstatus").val($("#ro_supplierstatus").val());
        $("#gstState").val($("#ro_gstState").val());
    });
    $("#update_btn_id").click(function() {
        $("#validation-emp").submit();
    });


    $("#acknowledge_btn_id").click(function() {
//        $(".show-supplier-fields").removeAttr("style");
//        $("#reject_btn_id").css("display", "none");
//        $("#acknowledge_btn_id").css("display", "none");
////        $("#supplier_save_btn_id").removeAttr("style");
//        $("#isAck").val("yes");
//
//        var no_of_category = ($("#supplier_cat_subcat_table tr").length - 1);
//        $("#noOfCategory").val(no_of_category);

        bootbox.confirm("Are you sure, you want to Accept!", function(result) {
            if (result === true)
            {

                location.href = "doActionOnRejectReleasedRfp.do?rfpId=" + window.location.search.substring(1).split("=")[1] + "&action=acknowledge";
                //location.href = "mytask.do";
            }

        });

    });

    $("#supplier_save_btn_id").click(function() {
//        alert("df");
//        location.href = "doSaveReleasedRfp.do";
        $("#validation-emp").submit();
    });

    $("#supplier_cat_subcat_table").on('change', '#price_per_quantity', function()
    {
        var price_per_q = $(this).val();
        var tr = $(this).closest('tr');
        var quantity = tr.children('td:eq(5)').text();
        var total_price = price_per_q * quantity;
//        tr.children('td:eq(7)').text(total_price);
        tr.children('td:eq(7)').children('input').val(total_price);
//        alert(tr.children('td:eq(7)').children('input'));
    });
    $("#supplier_cat_subcat_table").on('change', '#expected_price', function()
    {
        var exp_price = $(this).val();
//        alert(exp_price);
    });
    $("#supplier_cat_subcat_table").on('change', '#expected_delv_date', function()
    {
        var exp_date = $(this).val();
//        alert(exp_date);
    });

    $("#reject_btn_id").click(function() {
//        alert("ssa");
        bootbox.confirm("Are you sure, you want to Reject!", function(result) {
            if (result === true)
            {

                location.href = "doActionOnRejectReleasedRfp.do?rfpId=" + window.location.search.substring(1).split("=")[1] + "&action=reject";
                //location.href = "mytask.do";
            }

        });
    });
    $("#supplier_close_btn_id").click(function() {
        location.href = "mytask.do";
    });
    $("#close_btn_id").click(function() {
        location.href = "registeredSupplier.do";
    });

    var injectDivId = 1;

    $("#supplier_add_att_btn").click(function(e) {
//        alert("sd");
        var htmlText = '<div class="row-fluid"><div class="span10"><div class="control-group"><div class="controls">\n\
                        <input type="file" id="supplier-input-file-1" class="span10" name="file"/></div></div></div></div>\n\
                        <div class="row-fluid"><div class="span6"><div class="control-group"><label class="control-label" for="">Name</label>\n\
                        <div class="controls"><input type="text" class="span10" name="name" id="name"/></div></div></div>\n\
                        <div class="span6"><div class="control-group"><label class="control-label" for="">Description</label><div class="controls">\n\
                        <textarea id="supplierAttDescription" name="supplierAttDescription" rows="4" class="span10" required></textarea>\n\
                        <button type="button" class="btn btn-small btn-danger pull-right remove-class" id="remove_att_btn"><i class="icon-remove"></i></button>\n\
                        </div></div></div></div>';


        $("#injectContent").append("<div id='injectContent" + injectDivId + "'></div>");

        //var htmlTxt = $("#attDivId > div").clone().appendTo("#injectContent" + injectDivId + "").find("#add_att_btn").attr("value", "X").attr("class", "btn btn-small btn-danger pull-right remove-class");
        //htmlTxt.find("name").val("");
        $("#injectContent").append(htmlText);
        injectDivId++;

    });

    $("#injectContent").on("click", ".remove-class", function() {
        //alert($(this).prop("id"));
        $(this).parent().parent().parent().parent().parent().remove();
        //injectDivId--;
    });

    var rowId = 2;
    $("#add_supplier_cnct_details").click(function() {
//       alert("fggggf");
        var isValid = true;
        $(".contact-details").each(function() {
            if ($(this).val() === '' || $(this).val() === 'Select')
            {
                $(this).css({"border-color": "red"});
                isValid = false;
            }
        });
        if (isValid === false) 
        {
            bootbox.dialog("Please fill required fields!", [{"label": "ok", "class": "btn-small btn-primary"}]);
            return;
        }
        if($("#check_constraint").val() === 'false')
        {
            bootbox.dialog("Please fill required fields!", [{"label": "ok", "class": "btn-small btn-primary"}]);
            return;
        }
        
        var businessaddress = $("#businessaddress").val();
        var businesscountry = $("#businesscountry").val();
        var contactperson2 = $("#contactperson2").val();
        var contact2emailaddress = $("#contact2emailaddress").val();
        var contact2mobilenumber = $("#contact2mobilenumber").val();

        var element1=document.createElement('input');
        element1.type = 'hidden';
        element1.name = 'businessAddress';
        element1.value = businessaddress;

        var element2=document.createElement('input');
        element2.type = 'hidden';
        element2.name = 'businessCountry';
        element2.value = businesscountry;
        
        var element3=document.createElement('input');
        element3.type = 'hidden';
        element3.name = 'contactPerson2';
        element3.value = contactperson2;
        
        var element4=document.createElement('input');
        element4.type = 'hidden';
        element4.name = 'contact2EmailAddress';
        element4.value = contact2emailaddress;
        
        var element5=document.createElement('input');
        element5.type = 'hidden';
        element5.name = 'contact2MobileNumber';
        element5.value = contact2mobilenumber;
        
        $("#supplier_contact_details_div").append("<div id=" + rowId + "></div>")
        $("#" + rowId + "").append(element1);
        $("#" + rowId + "").append(element2);
        $("#" + rowId + "").append(element3);
        $("#" + rowId + "").append(element4);
        $("#" + rowId + "").append(element5);
        
        
        var tableRow = "<tr id=" + rowId + ">\n\
                        <td>" + element1.value + "</td>\n\
                        <td>" + element2.value + "</td>\n\
                        <td>" + element3.value + "</td>\n\
                        <td>" + element4.value + "</td>\n\
                        <td>" + element5.value + "</td>\n\
                        <td><button type='button' id='remove_supplier_cnct_details' class='btn btn-small btn-danger'><i class='icon-remove'></i></button></td></tr>";

        $("#supplier_cnct_details_table").children("tbody").append(tableRow);
        rowId++;
    });
    $(".contact-details").blur(function() {
        if ($(this).val() !== '' && $(this).val() !== 'Select')
        {
            $(this).removeAttr("style");
        }
    });
    $("#supplier_cnct_details_table").on('click', '#remove_supplier_cnct_details', function()
    {
        //alert("remove");
        //$(this).parent().parent().remove();
        $(this).closest('tr').remove();
        var divId = $(this).closest('tr').prop("id");
        $("#supplier_contact_details_div").find("#" + divId + "").remove();
        
    });
    $("#clear_supplier_cnct_details").click(function() {
//       alert("Sds"); 
        $(".contact-details").each(function() {
            if ($(this).prop('type') === 'select-one')
            {
                $(this).val('Select');
            }
            else
            {
                $(this).val('');
            }
        });
    });

    
    var bankRowId = 2;
    $("#add_supplier_bank_details").click(function() {
//       alert("fggggf");
        var isValid = true;
        $(".bank-details").each(function() {
            if ($(this).val() === '' || $(this).val() === 'Select')
            {
                $(this).css({"border-color": "red"});
                isValid = false;
            }
        });
        if (isValid === false)
        {
            bootbox.dialog("Please fill required fields!", [{"label": "ok", "class": "btn-small btn-primary"}]);
            return;
        }
        if($("#check_constraint").val() === 'false')
        {
            bootbox.dialog("Please fill required fields!", [{"label": "ok", "class": "btn-small btn-primary"}]);
            return;
        }
        
        var bankname = $("#bankname").val();
        var bankaccounttype = $("#bankaccounttype").val();
        var bankaccountnumber = $("#bankaccountnumber").val();
        var bankbranchname = $("#bankbranchname").val();
        var bankaddress = $("#bankaddress").val();
        var pincode = $("#pincode").val();
        var ifsccode = $("#ifsccode").val();
        var micrnumber = $("#micrnumber").val();
        var vendornameAsperbankrecord = $("#vendornameAsperbankrecord").val();
        var bankcurrency = $("#bankcurrency").val();

        var element1=document.createElement('input');
        element1.type = 'hidden';
        element1.name = 'bankName';
        element1.value = bankname;

        var element2=document.createElement('input');
        element2.type = 'hidden';
        element2.name = 'bankAccountType';
        element2.value = bankaccounttype;
        
        var element3=document.createElement('input');
        element3.type = 'hidden';
        element3.name = 'bankAccountNumber';
        element3.value = bankaccountnumber;
        
        var element4=document.createElement('input');
        element4.type = 'hidden';
        element4.name = 'bankBranchName';
        element4.value = bankbranchname;
        
        var element5=document.createElement('input');
        element5.type = 'hidden';
        element5.name = 'bankAddress';
        element5.value = bankaddress;
        
        var element6=document.createElement('input');
        element6.type = 'hidden';
        element6.name = 'pinCode';
        element6.value = pincode;

        var element7=document.createElement('input');
        element7.type = 'hidden';
        element7.name = 'ifscCode';
        element7.value = ifsccode;
        
        var element8=document.createElement('input');
        element8.type = 'hidden';
        element8.name = 'micrNumber';
        element8.value = micrnumber;
        
        var element9=document.createElement('input');
        element9.type = 'hidden';
        element9.name = 'vendorNameAsperBankRecord';
        element9.value = vendornameAsperbankrecord;
        
        var element10=document.createElement('input');
        element10.type = 'hidden';
        element10.name = 'bankCurrency';
        element10.value = bankcurrency;
        
        $("#supplier_bank_details_div").append("<div id=" + bankRowId + "></div>")
        $("#" + bankRowId + "").append(element1);
        $("#" + bankRowId + "").append(element2);
        $("#" + bankRowId + "").append(element3);
        $("#" + bankRowId + "").append(element4);
        $("#" + bankRowId + "").append(element5);
        $("#" + bankRowId + "").append(element6);
        $("#" + bankRowId + "").append(element7);
        $("#" + bankRowId + "").append(element8);
        $("#" + bankRowId + "").append(element9);
        $("#" + bankRowId + "").append(element10);
        

        var tableRow = "<tr id=" + bankRowId + ">\n\
                        <td>" + element1.value + "</td>\n\
                        <td>" + element2.value + "</td>\n\
                        <td>" + element3.value + "</td>\n\
                        <td>" + element4.value + "</td>\n\
                        <td>" + element5.value + "</td>\n\
                        <td>" + element6.value + "</td>\n\
                        <td>" + element7.value + "</td>\n\
                        <td>" + element8.value + "</td>\n\
                        <td>" + element9.value + "</td>\n\
                        <td>" + element10.value + "</td>\n\
                        <td><button type='button' id='remove_supplier_bank_details' class='btn btn-small btn-danger'><i class='icon-remove'></i></button></td></tr>";

        $("#supplier_bank_details_table").children("tbody").append(tableRow);
        bankRowId++;
    });
    $(".bank-details").blur(function() {
        if ($(this).val() !== '' && $(this).val() !== 'Select')
        {
            $(this).removeAttr("style");
        }
    });
    $("#supplier_bank_details_table").on('click', '#remove_supplier_bank_details', function()
    {
        $(this).closest('tr').remove();
        var divId = $(this).closest('tr').prop("id");
        $("#supplier_bank_details_div").find("#" + divId + "").remove();
    });
    $("#clear_supplier_bank_details").click(function() {
//       alert("Sds"); 
        $(".bank-details").each(function() {
            $(this).val('');
        });
    });
});
function supplierOnLoadFunction()
{
//    alert("on");
    var status = $("#RFQSupplierStatus").val();
    if (status !== "Awaiting Acknowledge")
    {
        $("#reject_btn_id").css("display", "none");
        $("#acknowledge_btn_id").css("display", "none");
    }
    if (status === "Awaiting Bid")
    {
        $(".show-supplier-fields").removeAttr("style");

        $("#isAck").val("yes");

        var no_of_category = ($("#supplier_cat_subcat_table tr").length - 1);
        $("#noOfCategory").val(no_of_category);

    }
}
