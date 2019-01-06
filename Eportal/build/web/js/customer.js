$(document).ready(function() {

    $("#sendMail").click(function() {
        var to = $("#mailTo").val();
        var cc = $("#mailCC").val();
        var subject = $("#mailSubject").val();
        var mailbody = $("#mailBody").val();
        var customer = $("#customerId option:selected").text();
        var project = $("#projectId option:selected").text();

        console.log("customer: " + customer);
        console.log("project: " + project);

        console.log("to: " + to);
        console.log("cc: " + cc);
        console.log("subject: " + subject);
        console.log("mailbody1: " + mailbody);

//        mailbody = mailbody.toString().split("\n").join("<br/>").split(" ").join("~");
        mailbody = mailbody.toString().split("\n").join("<br/>");

        var htmlMailBody = "<html><head>"
                + "<style>"
                + "table,body {    font-family: Calibri, sans-serif;    border-collapse: collapse;    width: 75%;}td, th {    border: 1px solid black;    text-align: left;    padding: 8px;}th{background-color: #dddddd;}"
                + "</style>"
                + "<script type=\"text/javascript\">"
                + " src=\"https://gc.kis.v2.scr.kaspersky-labs.com/471A31DC-05D0-7447-B4B5-B98A1CCD2B4E/main.js\" charset=\"UTF-8\""
                + "</script>"
                + "</head>"
                + "<body>"
                + mailbody + "<br><br>"
                + "<table>"
                + "<thead><tr><th>Document</th><th>Date</th><th>Customer Name</th></tr></thead>"
                + "<tbody>";


        console.log("mailbody4: " + mailbody);


        if (to === "")
        {
            $("#mailTo").css("border-color", "red");
            $("#mailTo").focus();
            return false;
        }
        else
        {
            $("#mailTo").css("border-color", "");
        }
//
//        if (cc === "")
//        {
//            $("#mailCC").css("border-color", "red");
//            return false;
//        }
//        else
//        {
//            $("#mailCC").css("border-color", "");
//        }

        if (subject === "")
        {
            $("#mailSubject").css("border-color", "red");
            $("#mailSubject").focus();
            return false;
        }
        else
        {
            $("#mailSubject").css("border-color", "");
        }

        if (mailbody === "")
        {
            $("#mailBody").css("border-color", "red");
            $("#mailBody").focus();
            return false;
        }
        else
        {
            $("#mailBody").css("border-color", "");
        }

        console.log("allSelectedDocIndex: " + allSelectedDocIndex);
        var array = new Array();
        for (var temp in allSelectedDocIndex)
        {
            if (allSelectedDocIndex[temp] !== "pojectid" && allSelectedDocIndex[temp] !== "invoice" && allSelectedDocIndex[temp] !== "singeddo"
                    && allSelectedDocIndex[temp] !== "unsingeddo" && allSelectedDocIndex[temp] !== "millcertificate" && allSelectedDocIndex[temp] !== "dosummary"
                    && allSelectedDocIndex[temp] !== "debitnote" && allSelectedDocIndex[temp] !== "creditnote" && allSelectedDocIndex[temp] !== "dwg"
                    && allSelectedDocIndex[temp].indexOf("INV_") === -1 && allSelectedDocIndex[temp].indexOf("SIGNEDDO_") === -1
                    && allSelectedDocIndex[temp].indexOf("MILLCERT_") === -1 && allSelectedDocIndex[temp].indexOf("UNS_IGNEDDO_") === -1)
            {
                array.push(allSelectedDocIndex[temp]);
            }
        }

        console.log("array: " + array);
        var docIndexes = array;

        console.log("docIndexForDownloadAll: " + docIndexForDownloadAll);

        var tempArray = [];
        tempArray = docIndexForDownloadAll.filter(function(e) {
            return allSelectedDocIndex.indexOf(e) < 0;
        });
        console.log("tempArray: " + tempArray);

        for (var temp in tempArray)
        {
            delete dataByIndexes[tempArray[temp]];
        }
        console.log("dataByIndexes: " + dataByIndexes);
        var tempData = "";
        var temprow = "";
        for (var temp in dataByIndexes)
        {
            console.log("temp: " + temp);
            console.log("data: " + dataByIndexes[temp]);
            var docName = dataByIndexes[temp].split(",")[1];
            tempData += docName + ";";
//            console.log("docName: " + docName);
            temprow += "<tr><td>" + docName + "</td><td>" + documentDateByIndexes[temp] + "</td><td>" + customer + "</td></tr>";

        }
        console.log("tempData: " + tempData);
//
//        for (var temp in documentDateByIndexes)
//        {
//            console.log("InsertDate: " + documentDateByIndexes[temp]);
//        }

        htmlMailBody += temprow;

        htmlMailBody += "</tbody>"
                + "</table><br><br>"
                + "For any clarifications please contact mailid@natsteel.com.sg <br><br> Thanks<br>NatSteel Team."
                + "</body>"
                + "</html>";

        console.log("htmlMailBody: " + htmlMailBody);

        $.ajax(
                {
                    type: "GET",
                    url: "rfeCont.do",
                    async: true,
                    data:
                            {
                                "reqFrom": "CustomerMail",
                                "MailTo": to,
                                "MailCc": cc,
                                "MailSubject": subject,
                                "MailBody": htmlMailBody,
                                "DocIndexes": docIndexes.toString(),
                                "DocName": tempData,
                                "CustomerName": customer,
                                "ProjectName": project
                            },
                    dataType: "json",
                    complete: function(responseJson)
                    {
//                        var obj = $.parseJSON(responseJson.responseText);
//                            alert(obj.Status);
//                        console.log(obj.Status);

                    }
                });
        $("#sendMailModal").modal("hide");
        alert("Mail Sent Successfully");
    });
    var arrayForMultipleZip = [];

    $('#container').on("changed.jstree", function(e, data) {
        console.log(data.selected);
//                    console.log(data.selected.length);
        allSelectedDocIndex = data.selected;
//        console.log(data.instance.get_node(data.selected).text);
//        for (var i = 0; i < data.selected.length; i++)
//        {
//            console.log("text: " + data.instance.get_node(data.selected[i]).text.trim());
//        }
        console.log("allSelectedDocIndex: " + allSelectedDocIndex);

        var role = $("#userRole").val();

        if (allSelectedDocIndex.length === 0)
        {
            $("#downloadselected").addClass("hidden");

            if (role === "Admin" || role === "Company Admin")
            {
                $("#mailSelectedDocument").addClass("hidden");
            }
        }
        else
        {
            $("#downloadselected").removeClass("hidden");

            if (role === "Admin" || role === "Company Admin")
            {
                $("#mailSelectedDocument").removeClass("hidden");
            }
        }



        var array = new Array();
        var tempArray = [];
        var isSelectedAll = false;

        for (var temp in allSelectedDocIndex)
        {

            if (allSelectedDocIndex[temp] === "pojectid")
            {
                isSelectedAll = true;

            }
            if (allSelectedDocIndex[temp] !== "pojectid" && allSelectedDocIndex[temp] !== "invoice" && allSelectedDocIndex[temp] !== "singeddo"
                    && allSelectedDocIndex[temp] !== "unsingeddo" && allSelectedDocIndex[temp] !== "millcertificate" && allSelectedDocIndex[temp] !== "dosummary"
                    && allSelectedDocIndex[temp] !== "debitnote" && allSelectedDocIndex[temp] !== "creditnote" && allSelectedDocIndex[temp] !== "dwg"
                    && allSelectedDocIndex[temp].indexOf("INV_") === -1 && allSelectedDocIndex[temp].indexOf("SIGNEDDO_") === -1
                    && allSelectedDocIndex[temp].indexOf("MILLCERT_") === -1 && allSelectedDocIndex[temp].indexOf("UNS_IGNEDDO_") === -1)
            {
                console.log("temp: " + allSelectedDocIndex[temp].indexOf("UNSIGNEDDO"));
                array.push(allSelectedDocIndex[temp]);
                tempArray.push(allSelectedDocIndex[temp]);
            }
        }
        if (arrayForMultipleZip.length !== 0)
        {
            arrayForMultipleZip.push("#");
        }
        
        
        arrayForMultipleZip.push(tempArray.toString());

        console.log("arrayForMultipleZip: " + arrayForMultipleZip);
        if (isSelectedAll === true)
        {
            $("#downloadselected").prop("disabled", true);
        }
        else
        {
            $("#downloadselected").prop("disabled", false);
        }
        console.log("array: " + array);
        if (array.length !== 1)
        {
            $("#viewSelectedDocument").addClass("hidden");
        }
        else
        {
            $("#viewSelectedDocument").removeClass("hidden");
        }
    });

    $("#personalcontactnumber").after("<div id='error' style='color:red;'></div>");
    $("#personalcontactnumber").keyup(function() {
        var val = $(this).val();
        var regex = new RegExp('^[0-9]+$');

        if (regex.test(val))
        {
            $(this).css("border-color", "");
            $(this).siblings().html('');
            $("#check_constraint").val('true');
            $("#add_user_btn").prop("disabled", false);
            $("#update_edituserbtn").prop("disabled", false);
            $("#submitCompanyProfile").prop("disabled", false);
        }
        else
        {
            $(this).css("border-color", "red");
            $(this).siblings().html('Please enter numbers only!');
            $("#check_constraint").val('false');
            $("#add_user_btn").prop("disabled", true);
            $("#update_edituserbtn").prop("disabled", true);
            $("#submitCompanyProfile").prop("disabled", true);
        }
    });

    $("#personalemailid").after("<div id='error' style='color:red;'></div>");
    $("#personalemailid").keyup(function() {
        var val = $(this).val();
        var regex = new RegExp('^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$');

        if (regex.test(val))
        {
            $(this).css("border-color", "");
            $(this).siblings().html('');
            $("#check_constraint").val('true');
            $("#add_user_btn").prop("disabled", false);
            $("#submitCompanyProfile").prop("disabled", false);
        }
        else
        {
            $(this).css("border-color", "red");
            $(this).siblings().html('Please enter valid email address!');
            $("#check_constraint").val('false');
            $("#add_user_btn").prop("disabled", true);
            $("#submitCompanyProfile").prop("disabled", true);
        }
    });
//    $("#my_project_fromDate").blur(function()
//    {
//        $("#my_project_toDate").attr("min", $('#my_project_fromDate').val());
//        $("#my_project_toDate").val('');
//    });

//    $("#my_project_toDate").blur(function() {
//
//        var uploadFromDate = new Date($('#my_project_fromDate').val());
//
//        console.log(uploadFromDate);
//
//        if (uploadFromDate == "Invalid Date")
//        {
//            //alert("Please Select Open Date First!");
//            bootbox.dialog("Please enter from date First!", [{"label": "ok", "class": "btn-small btn-primary"}]);
//            $('#my_project_toDate').val('');
//            return false;
//        }
//        else
//        {
//            console.log("else");
//        }
//    });
//    var multipleDownloadDataArray = "";
    $("#projectIdMultiple").change(function() {
//        $("#projectIds").val($(this).val());
        var len = $('#ro_projectname').has('option').length;
//        console.log("len: " + len);

        var new_projects = $(this).val() + "";

//        console.log("New Projects1: " + new_projects);
        var to_be_mapp_project = "";
        var old_project_array = [];
        if (len > 0)
        {
//            var old_project_array = [];

            $("#ro_projectname option").each(function() {

                var old_project = $(this).val();

                old_project_array.push(old_project);

            });
        }
//        console.log("old_project_array: " + (old_project_array) + " ,old_project_array len: " + old_project_array.length);

        console.log("New Projects: " + new_projects + " ,New Projects len: " + new_projects.length);

        var new_project_array = new_projects.split(",");

        console.log("new_project_array: " + new_project_array);

        if (old_project_array.length > 0)
        {
            var temp = [];
            for (var i = 0; i < new_project_array.length; i++)
            {
                if (old_project_array.toString().indexOf(new_project_array[i]) === -1)
                {
                    temp.push(new_project_array[i]);
                }
            }
            console.log("temp: " + temp);
            if (temp != "null")
            {
                if (temp.length === 0)
                {
                    to_be_mapp_project = old_project_array;
                }
                else
                {
                    to_be_mapp_project = old_project_array + "," + temp;
                }
            }
            console.log("to_be_mapp_project: " + to_be_mapp_project);
        }
        else
        {
            to_be_mapp_project = new_projects;
            console.log("to_be_mapp_project: " + to_be_mapp_project);
        }

//        console.log(old_project_array === new_projects);

        $("#projectIds").val(to_be_mapp_project + "");

    });

    $("#avail_document_multiple").change(function() {
//        $("#projectIds").val($(this).val());
        var len = $('#document_right').has('option').length;
//        console.log("len: " + len);

        var new_projects = $(this).val() + "";

//        console.log("New Projects1: " + new_projects);
        var to_be_mapp_project = "";
        var old_project_array = [];
        if (len > 0)
        {
//            var old_project_array = [];

            $("#document_right option").each(function() {

                var old_project = $(this).val();

                old_project_array.push(old_project);

            });
        }
//        console.log("old_project_array: " + (old_project_array) + " ,old_project_array len: " + old_project_array.length);

        console.log("New Projects: " + new_projects + " ,New Projects len: " + new_projects.length);

        var new_project_array = new_projects.split(",");

        console.log("new_project_array: " + new_project_array);

        if (old_project_array.length > 0)
        {
            var temp = [];
            for (var i = 0; i < new_project_array.length; i++)
            {
                if (old_project_array.toString().indexOf(new_project_array[i]) === -1)
                {
                    temp.push(new_project_array[i]);
                }
            }
            console.log("temp: " + temp);
            if (temp != "null")
            {
                if (temp.length === 0)
                {
//                    to_be_mapp_project = old_project_array;
                }
                else
                {
//                    to_be_mapp_project = old_project_array + "," + temp;
                    to_be_mapp_project = temp;
                }
            }
            console.log("to_be_mapp_project: " + to_be_mapp_project);
        }
        else
        {
            to_be_mapp_project = new_projects;
            console.log("to_be_mapp_project: " + to_be_mapp_project);
        }

//        console.log(old_project_array === new_projects);

        $("#documentIds").val(to_be_mapp_project + "");

    });

    $("#customerId").change(function() {
        var customerId = $(this).val();
        console.log(customerId);
        $("#sessionCustomerId").val(customerId);
//        $("#projectId option").remove();
//        $("<option>").val("").text("--Select--").appendTo("#projectId");
//        getProjectByJqAjax();

        $.ajax(
                {
                    type: "GET",
                    url: "rfeCont.do",
                    async: true,
                    data:
                            {
                                "reqFrom": "findProjectByCustomerId",
                                "CustomerId": customerId

                            },
                    dataType: "json",
                    complete: function(responseJson)
                    {
                        var obj = $.parseJSON(responseJson.responseText);
//                            alert(obj.Status);
                        console.log(obj.Status);
                        $("#projectId option").remove();
                        $("<option>").val("--Select--").text("--Select--").appendTo("#projectId");
                        console.log("projects: " + obj.status);
                        if (obj.status === "FOUND")
                        {
                            for (var i = 0; i < obj.data.length; i++)
                            {
                                console.log("P.CODE: " + obj.data[i].PROJECT_CODE + ", P.NAME" + obj.data[i].PROJECT_NAME);
                                $("<option>").val(obj.data[i].PROJECT_CODE).text(obj.data[i].PROJECT_NAME).appendTo("#projectId");
                            }
                        }
                    }
                });
    });

    $("#addDocument").click(function() {
        //       alert("dfsf");
        var invoice = $("#id-input-file-1").val();
        var mill_certificate = $("#id-input-file-2").val();
        var purchase_order = $("#id-input-file-3").val();
        var statement_account = $("#id-input-file-4").val();
        var ar_listing = $("#id-input-file-5").val();
        var debit_note = $("#id-input-file-6").val();
        var credit_note = $("#id-input-file-9").val();
        var delivery_order = $("#id-input-file-7").val();
        var sales_terms_conditions = $("#id-input-file-8").val();

        if (sales_terms_conditions !== '' || delivery_order !== '' || credit_note !== '' || debit_note !== '' || ar_listing !== '' || statement_account !== ''
                || purchase_order !== '' || mill_certificate !== '' || invoice !== '')
        {
            $("#document_form").submit();
            //            bootbox.dialog("Document(s) successfully added.", [{"label": "ok", "class": "btn-small btn-primary"}]);
        }
        else
        {
            bootbox.dialog("Please add at least one document!", [{
                    "label": "ok",
                    "class": "btn-small btn-primary"
                }]);
        }
        //        var sn = 1;
        //        var invoice = $("#id-input-file-1").val();
        //        var row = '';
        //        alert(invoice);
        //        if (invoice !== '')
        //        {
        ////            alert();
        //            row += "<tr><td>" + sn + "</td><td>Invoice</td><td>" + invoice.split('\\')[2] + "</td><td>Uploaded</td></tr>";
        //
        //            sn++;
        //        }
        //        var mill_certificate = $("#id-input-file-2").val();
        ////        alert(invoice);
        //        if (mill_certificate !== '')
        //        {
        ////            alert(mill_certificate[0].files[0]['name']);
        //            row += "<tr><td>" + sn + "</td><td>Mill Certificate</td><td>" + mill_certificate.split('\\')[2] + "</td><td>Uploaded</td></tr>";
        //
        //            sn++;
        //        }
        //        var purchase_order = $("#id-input-file-3").val();
        ////        alert(invoice);
        //        if (purchase_order !== '')
        //        {
        ////            alert(purchase_order[0].files[0]['name']);
        //            row += "<tr><td>" + sn + "</td><td>Purchase Order</td><td>" + purchase_order.split('\\')[2] + "</td><td>Uploaded</td></tr>";
        //
        //            sn++;
        //        }
        //        var statement_account = $("#id-input-file-4").val();
        ////        alert(invoice);
        //        if (statement_account !== '')
        //        {
        ////            alert(statement_account[0].files[0]['name']);
        //            row += "<tr><td>" + sn + "</td><td>Statement of Account</td><td>" + statement_account.split('\\')[2] + "</td><td>Uploaded</td></tr>";
        //
        //            sn++;
        //        }
        //        var ar_listing = $("#id-input-file-5").val();
        ////        alert(invoice);
        //        if (ar_listing !== '')
        //        {
        ////            alert(ar_listing[0].files[0]['name']);
        //            row += "<tr><td>" + sn + "</td><td>AR Listing</td><td>" + ar_listing.split('\\')[2] + "</td><td>Uploaded</td></tr>";
        //
        //            sn++;
        //        }
        //        var debit_note = $("#id-input-file-6").val();
        ////        alert(invoice);
        //        if (debit_note !== '')
        //        {
        ////            alert(debit_credit_note[0].files[0]['name']);
        //            row += "<tr><td>" + sn + "</td><td>Debit Note</td><td>" + debit_note.split('\\')[2] + "</td><td>Uploaded</td></tr>";
        //
        //            sn++;
        //        }
        //        var credit_note = $("#id-input-file-9").val();
        ////        alert(invoice);
        //        if (credit_note !== '')
        //        {
        ////            alert(debit_credit_note[0].files[0]['name']);
        //            row += "<tr><td>" + sn + "</td><td>Credit Note</td><td>" + credit_note.split('\\')[2] + "</td><td>Uploaded</td></tr>";
        //
        //            sn++;
        //        }
        //
        //        var delivery_order = $("#id-input-file-7").val();
        ////        alert(invoice);
        //        if (delivery_order !== '')
        //        {
        ////            alert(delivery_order[0].files[0]['name']);
        //            row += "<tr><td>" + sn + "</td><td>Delivery Order</td><td>" + delivery_order.split('\\')[2] + "</td><td>Uploaded</td></tr>";
        //
        //            sn++;
        //        }
        //        var sales_terms_conditions = $("#id-input-file-8").val();
        ////        alert(invoice);
        //        if (sales_terms_conditions !== '')
        //        {
        ////            alert(sales_terms_conditions[0].files[0]['name']);
        //            row += "<tr><td>" + sn + "</td><td>Sales Terms and Conditions</td><td>" + sales_terms_conditions.split('\\')[2] + "</td><td>Uploaded</td></tr>";
        //
        //            sn++;
        //        }
        //        alert("dsf");

    });

    $("#document-table").on('click', '#remove_doc_sub', function()
    {
        //alert("remove");
        //$(this).parent().parent().remove();
        $(this).closest('tr').remove();
    });
    //    $("#searchDocument").click(function() {
    //
    //        var searchBy = "*";
    //        var docType = "Invoice";
    //        var fromDate = $("#from_date").val();
    //        var toDate = $("#to_date").val();
    ////
    ////        if (searchBy === '' || docType === 'Select')
    ////        {
    ////            bootbox.dialog("Please enter search criteria!", [{"label": "OK", "class": "btn-small btn-primary"}]);
    ////        }
    ////        else
    //        {
    //
    //            $.ajax(
    //                    {
    //                        type: "GET",
    //                        url: "rfeCont.do",
    //                        async: true,
    //                        data:
    //                                {
    //                                    "reqFrom": "SearchDoc",
    //                                    "DocType": docType,
    //                                    "SearchBy": searchBy,
    //                                    "fromDate": fromDate,
    //                                    "toDate": toDate
    //                                },
    //                        dataType: "json",
    //                        complete: function(responseJson)
    //                        {
    //                            var obj = $.parseJSON(responseJson.responseText);
    ////                            alert(obj.Status);
    //                            var row = '';
    //                            if (obj.Status === 'success')
    //                            {
    //                                for (var i = 0; i < obj.Data.length; i++)
    //                                {
    //                                    row += "<tr><td>" + (i + 1) + "</td><td>" + obj.Data[i].docType + "</td><td>" + obj.Data[i].docName + "</td><td><a href='downloadCustomerDocument.do?docId=" + obj.Data[i].docId + "'>" + obj.Data[i].docName + "</a></td><td>" + obj.Data[i].uploadedDate + "</td></tr>";
    //                                }
    //                                $("#Search_document_table_div").css("display", "block");
    //                                $("#advsearchdownloadall").css("display", "block");
    //
    //                                $("#Search_document_table").children("tbody").html(row);
    //                                
    //                                if ($.fn.DataTable.isDataTable('#Search_document_table')) {
    //                                    table.fnDestroy();
    //                                    $("#Search_document_table").children('tbody').html(row);
    //                                    table = $('#Search_document_table').dataTable();
    //                                    //                            table.resetPaging();
    //                                    table.fnReloadAjax();
    //                                    table = $('#Search_document_table').dataTable({
    //                                    });
    //                                }
    //                                else
    //                                {
    //                                    table = $('#Search_document_table').dataTable({});
    //                                }
    //                            }
    //                            else
    //                            {
    //                                bootbox.dialog("No date available!", [{"label": "OK", "class": "btn-small btn-primary"}]);
    //                                $("#Search_document_table_div").css("display", "none");
    //                            }
    //                        }
    //                    });
    //        }
    //
    //    });
    $(".subuser-form-field").blur(function() {
        if ($(this).val() !== '')
        {
            $(this).css("border-color", "");
        }
    });
    $("#clearcriteriabtn").click(function() {

        location.reload(true);
//        $(".search-doc-form-field").each(function() {
//        
//            if ($(this).prop('type') === 'text' || $(this).prop('type') === 'date')
//            {
//                $(this).val('');
//            }
//        });
//        $("#customerId").val("--Select--");
//        $("#docType").val("--Select--");
//        $("#projectId").val("");


    });
    $("#advsearch").click(function() {
        location.href = "custadvsearch.do";
    });
    $("#advsearchclose").click(function() {
        location.href = "showsearchdocument.do";
    });
    $("#advclearcriteriabtn").click(function() {
        location.reload(true);
//        $(".search-doc-form-field").each(function() {
//            if ($(this).prop('type') === 'select-one')
//            {
//                $(this).val('--Select--');
//            }
//            if ($(this).prop('type') === 'text' || $(this).prop('type') === 'date')
//            {
//                $(this).val('');
//            }
//        });
//        
    });
    $("#statementclearcriteria").click(function() {
        location.reload(true);
//        console.log("role: " + $("#userRole").val());
//        if ($("#userRole").val() === "Admin")
//        {
//            $("#statementcustomerId").val("");
//        }
//        $("#doctype").val("All");
//        $("#transmonth").val("Month");
//        $("#transyear").val("Year");
//
//        $("#statement_from_date").val("");
//        $("#statement_to_date").val("");
    });
    $("#docType1").change(function() {
        //        alert("fsasf");
        var type = $("#docType1 option:selected").text();
        console.log("type: " + type);

        if (type !== 'Select')
        {
            if (type === 'Mill Certificate' || type === 'Delivery Order Summary')
            {
                $("#docNo1").prop("disabled", true);
                $("#docNo1").css("border-color", "");
            }
            else
            {
                $("#docNo1").prop("disabled", false);
            }
            $("#docNo1").val("");
        }
        //            if ($(this).val() === 'Invoice')
        //            {
        //                $("#invoiceDetailsPanel").removeAttr("style");
        //
        //                $("#arListingDetailsPanel").css("display", "none");
        //                $("#deliveryOrderSummaryDetailsPanel").css("display", "none");
        //                $("#millCertDetailsPanel").css("display", "none");
        //                $("#soaDetailsPanel").css("display", "none");
        //                $("#debitNoteDetailsPanel").css("display", "none");
        //                $("#creditNoteDetailsPanel").css("display", "none");
        //                $("#salesTermsCondDetailsPanel").css("display", "none");
        //                $("#contractDocDetailsPanel").css("display", "none");
        //                $("#enggDocDetailsPanel").css("display", "none");
        //            }
        //            else if ($(this).val() === 'AR Listing')
        //            {
        //                $("#arListingDetailsPanel").removeAttr("style");
        //
        //                $("#invoiceDetailsPanel").css("display", "none");
        //                $("#deliveryOrderSummaryDetailsPanel").css("display", "none");
        //                $("#millCertDetailsPanel").css("display", "none");
        //                $("#soaDetailsPanel").css("display", "none");
        //                $("#debitNoteDetailsPanel").css("display", "none");
        //                $("#creditNoteDetailsPanel").css("display", "none");
        //                $("#salesTermsCondDetailsPanel").css("display", "none");
        //                $("#contractDocDetailsPanel").css("display", "none");
        //                $("#enggDocDetailsPanel").css("display", "none");
        //            }
        //            else if ($(this).val() === 'Delivery Order and PO Summary')
        //            {
        //                $("#deliveryOrderSummaryDetailsPanel").removeAttr("style");
        //
        //                $("#invoiceDetailsPanel").css("display", "none");
        //                $("#arListingDetailsPanel").css("display", "none");
        //                $("#millCertDetailsPanel").css("display", "none");
        //                $("#soaDetailsPanel").css("display", "none");
        //                $("#debitNoteDetailsPanel").css("display", "none");
        //                $("#creditNoteDetailsPanel").css("display", "none");
        //                $("#salesTermsCondDetailsPanel").css("display", "none");
        //                $("#contractDocDetailsPanel").css("display", "none");
        //                $("#enggDocDetailsPanel").css("display", "none");
        //            }
        //            else if ($(this).val() === 'Mill Certificate')
        //            {
        //                $("#millCertDetailsPanel").removeAttr("style");
        //
        //                $("#invoiceDetailsPanel").css("display", "none");
        //                $("#arListingDetailsPanel").css("display", "none");
        //                $("#deliveryOrderSummaryDetailsPanel").css("display", "none");
        //                $("#soaDetailsPanel").css("display", "none");
        //                $("#debitNoteDetailsPanel").css("display", "none");
        //                $("#creditNoteDetailsPanel").css("display", "none");
        //                $("#salesTermsCondDetailsPanel").css("display", "none");
        //                $("#contractDocDetailsPanel").css("display", "none");
        //                $("#enggDocDetailsPanel").css("display", "none");
        //            }
        //            else if ($(this).val() === 'Statement of Account')
        //            {
        //                $("#soaDetailsPanel").removeAttr("style");
        //
        //                $("#invoiceDetailsPanel").css("display", "none");
        //                $("#arListingDetailsPanel").css("display", "none");
        //                $("#deliveryOrderSummaryDetailsPanel").css("display", "none");
        //                $("#millCertDetailsPanel").css("display", "none");
        //                $("#debitNoteDetailsPanel").css("display", "none");
        //                $("#creditNoteDetailsPanel").css("display", "none");
        //                $("#salesTermsCondDetailsPanel").css("display", "none");
        //                $("#contractDocDetailsPanel").css("display", "none");
        //                $("#enggDocDetailsPanel").css("display", "none");
        //            }
        //            else if ($(this).val() === 'Debit Note')
        //            {
        //                $("#debitNoteDetailsPanel").removeAttr("style");
        //
        //                $("#invoiceDetailsPanel").css("display", "none");
        //                $("#arListingDetailsPanel").css("display", "none");
        //                $("#deliveryOrderSummaryDetailsPanel").css("display", "none");
        //                $("#millCertDetailsPanel").css("display", "none");
        //                $("#soaDetailsPanel").css("display", "none");
        //                $("#creditNoteDetailsPanel").css("display", "none");
        //                $("#salesTermsCondDetailsPanel").css("display", "none");
        //                $("#contractDocDetailsPanel").css("display", "none");
        //                $("#enggDocDetailsPanel").css("display", "none");
        //            }
        //            else if ($(this).val() === 'Credit Note')
        //            {
        //                $("#creditNoteDetailsPanel").removeAttr("style");
        //
        //                $("#invoiceDetailsPanel").css("display", "none");
        //                $("#arListingDetailsPanel").css("display", "none");
        //                $("#deliveryOrderSummaryDetailsPanel").css("display", "none");
        //                $("#millCertDetailsPanel").css("display", "none");
        //                $("#soaDetailsPanel").css("display", "none");
        //                $("#debitNoteDetailsPanel").css("display", "none");
        //                $("#salesTermsCondDetailsPanel").css("display", "none");
        //                $("#contractDocDetailsPanel").css("display", "none");
        //                $("#enggDocDetailsPanel").css("display", "none");
        //            }
        //            else if ($(this).val() === 'Sales Terms and Conditions')
        //            {
        //                $("#salesTermsCondDetailsPanel").removeAttr("style");
        //
        //                $("#invoiceDetailsPanel").css("display", "none");
        //                $("#arListingDetailsPanel").css("display", "none");
        //                $("#deliveryOrderSummaryDetailsPanel").css("display", "none");
        //                $("#millCertDetailsPanel").css("display", "none");
        //                $("#soaDetailsPanel").css("display", "none");
        //                $("#debitNoteDetailsPanel").css("display", "none");
        //                $("#creditNoteDetailsPanel").css("display", "none");
        //                $("#contractDocDetailsPanel").css("display", "none");
        //                $("#enggDocDetailsPanel").css("display", "none");
        //            }
        //            else if ($(this).val() === 'Contract Document')
        //            {
        //                $("#contractDocDetailsPanel").removeAttr("style");
        //
        //                $("#invoiceDetailsPanel").css("display", "none");
        //                $("#arListingDetailsPanel").css("display", "none");
        //                $("#deliveryOrderSummaryDetailsPanel").css("display", "none");
        //                $("#millCertDetailsPanel").css("display", "none");
        //                $("#soaDetailsPanel").css("display", "none");
        //                $("#debitNoteDetailsPanel").css("display", "none");
        //                $("#creditNoteDetailsPanel").css("display", "none");
        //                $("#salesTermsCondDetailsPanel").css("display", "none");
        //                $("#enggDocDetailsPanel").css("display", "none");
        //            }
        //            else if ($(this).val() === ' Engineering Document (DWG)')
        //            {
        //                $("#enggDocDetailsPanel").removeAttr("style");
        //
        //                $("#invoiceDetailsPanel").css("display", "none");
        //                $("#arListingDetailsPanel").css("display", "none");
        //                $("#deliveryOrderSummaryDetailsPanel").css("display", "none");
        //                $("#millCertDetailsPanel").css("display", "none");
        //                $("#soaDetailsPanel").css("display", "none");
        //                $("#debitNoteDetailsPanel").css("display", "none");
        //                $("#creditNoteDetailsPanel").css("display", "none");
        //                $("#salesTermsCondDetailsPanel").css("display", "none");
        //                $("#contractDocDetailsPanel").css("display", "none");
        //            }
        //        }
        //        else
        //        {
        //            $("#enggDocDetailsPanel").css("display", "none");
        //            $("#invoiceDetailsPanel").css("display", "none");
        //            $("#arListingDetailsPanel").css("display", "none");
        //            $("#deliveryOrderSummaryDetailsPanel").css("display", "none");
        //            $("#millCertDetailsPanel").css("display", "none");
        //            $("#soaDetailsPanel").css("display", "none");
        //            $("#debitNoteDetailsPanel").css("display", "none");
        //            $("#creditNoteDetailsPanel").css("display", "none");
        //            $("#salesTermsCondDetailsPanel").css("display", "none");
        //            $("#contractDocDetailsPanel").css("display", "none");
        //        }
    });


    $("#secQue1").change(function() {
        $("#secAns1").val('');
    });
    $("#secQue2").change(function() {
        $("#secAns2").val('');
    });
    $("#secQue3").change(function() {
        $("#secAns3").val('');
    });

    $("#submitCompanyProfile").click(function() {

        var secAns1 = $("#secAns1").val();
        var secAns2 = $("#secAns2").val();
        var secAns3 = $("#secAns3").val();

        var personalemailid = $("#personalemailid").val();
        var personalcontactnumber = $("#personalcontactnumber").val();

        if (personalemailid === "")
        {
            $("#personalemailid").focus();
//            $("#secQue1").css("border-color", "red");
            return false;
        }

        if (personalcontactnumber === "")
        {
            $("#personalcontactnumber").focus();
//            $("#secQue1").css("border-color", "red");
            return false;
        }

        var secQue1 = $("#secQue1 option:selected").html();
        var secQue2 = $("#secQue2 option:selected").html();
        var secQue3 = $("#secQue3 option:selected").html();
//        
//        console.log(secQue1);
//        console.log(secQue2);
//        console.log(secQue3);
        if (secQue1 === "--Select--")
        {
            $("#secQue1").focus();
//            $("#secQue1").css("border-color", "red");
            return false;
        }
        else
        {
            $("#secQue1").css("border-color", "");
        }

        if (secQue2 === "--Select--")
        {
            $("#secQue2").focus();
            return false;
        }
        else
        {
            $("#secQue2").css("border-color", "");
        }

        if (secQue3 === "--Select--")
        {
            $("#secQue3").focus();
            return false;
        }
        else
        {
            $("#secQue3").css("border-color", "");
        }

        if (secQue1 !== "" && secAns1 === "")
        {
            $("#secAns1").css("border-color", "red");
            return false;
        }
        else
        {
            $("#secAns1").css("border-color", "");
        }
        if (secQue2 !== "" && secAns2 === "")
        {
            $("#secAns2").css("border-color", "red");
            return false;
        }
        else
        {
            $("#secAns2").css("border-color", "");
        }
        if (secQue3 !== "" && secAns3 === "")
        {
            $("#secAns3").css("border-color", "red");
            return false;
        }
        else
        {
            $("#secAns3").css("border-color", "");
        }

//        $("#overlay").css("display", "block");
    });
    $("#editCompanyProfile").click(function() {
        $(".customer-form-field").each(function() {
            $(this).prop("disabled", false);
            //            alert($(this).prop("type"));
            if ($(this).prop("type") === 'textarea')
            {
                $(this).removeAttr("style");

            }
        });
        $("#submitCompanyProfile").removeAttr("style");
        $("#editCompanyProfile").css("display", "none");

//        if ($("#secQue1").val() === "--Select--")
        {
            $("#secQue1").prop("disabled", false);
            $("#secAns1").prop("disabled", false);
        }
//        if ($("#secQue2").val() === "--Select--")
        {
            $("#secQue2").prop("disabled", false);
            $("#secAns2").prop("disabled", false);
        }
//        if ($("#secQue3").val() === "--Select--")
        {
            $("#secQue3").prop("disabled", false);
            $("#secAns3").prop("disabled", false);
        }
    });
    var injectDivId = 1;
    $("#add_another_criteria").click(function() {

        var noOfSelect = parseInt($("#noOfSelect").val()) + 1;
        $("#noOfSelect").val(noOfSelect);

        var options = '';
        var size = document.getElementsByTagName('select').length;
        var role = $("#userRole").val();
        if (role === "Admin") {
            size = size - 1;
        }
        console.log("size1: " + size);

        $("#docType1 option").each(function() {
            //            alert($(this).val());
            options = options + '<option value="' + $(this).val() + '">' + $(this).text() + '</option>';
        });
        //        alert(options);
        console.log(options);
        console.log("size2: " + size);

        var htmlText = '<div class="row-fluid"><div class="span6"><div class="control-group"><label class="control-label" for="">Select Category of Document</label>\n\
                        <div class="controls"><div class=""><select id="docType' + size + '" name="adv_docType" class="span10 search-doc-form-field check-duplicate-category">' + options + '</select>\n\
                        </div></div></div></div><div class="span6"><div class="control-group"><label class="control-label" for="">Document Reference</label><div class="controls">\n\
                        <div class=""><input type="text" name="documentreference" id="docNo' + size + '" class="span10 search-doc-form-field" required/>\n\
                        <input type="button" value="X" title="Remove criteria" class="btn btn-small btn-danger pull-right remove-criteria"/></div></div></div></div></div>';

        $("#injectCriteria").append("<div id='injectCriteria" + injectDivId + "'></div>");

        $("#injectCriteria" + injectDivId).append(htmlText);
        injectDivId++;
    });
    $("#injectCriteria").on("click", ".remove-criteria", function() {
        //alert($(this).prop("id"));
        $(this).parent().parent().parent().parent().parent().parent().remove();
        //injectDivId--;
        var noOfSelect = parseInt($("#noOfSelect").val()) - 1;
        $("#noOfSelect").val(noOfSelect);
    });

    $("#injectCriteria").on("change", ".check-duplicate-category", function() {

        var selectedDocSelectId = $(this).prop("id");
        console.log("doc type: " + $("#" + selectedDocSelectId + " option:selected").text());

        if ($("#" + selectedDocSelectId + " option:selected").text() !== "--Select--")
        {

            var idno = selectedDocSelectId.substring(selectedDocSelectId.length - 1);

            console.log("len: " + selectedDocSelectId.length);
            console.log("idno: " + idno);
            if ($("#" + selectedDocSelectId + " option:selected").text() === "Mill Certificate" || $("#" + selectedDocSelectId + " option:selected").text() === "Delivery Order Summary")
            {
                $("#docNo" + idno).prop("disabled", true);
            }
            else
            {
                $("#docNo" + idno).prop("disabled", false);
            }
        }

        var docType = [];
        $(".check-duplicate-category").each(function() {
//            console.log($(this).prop("id"));
            var docTypeText = $("#" + $(this).prop("id") + " option:selected").text();
            console.log("docTypeText: " + docTypeText);

            if (docTypeText !== "--Select--")
            {
                docType.push($("#" + $(this).prop("id") + " option:selected").text());

                var result = hasDuplicate(docType);
//            console.log("result: " + result);
                if (result === "Found")
                {
//                $("#" + $(this).prop("id")).val("Select");
//                $("#" + $(this).prop("id")).css("border-color", "red");
                    $("#advsearchDocument").prop("disabled", true);
                    bootbox.dialog("Please select unique criteria!", [{"label": "ok", "class": "btn-small btn-primary"}]);
                    return false;
                }
                else
                {
                    $("#advsearchDocument").prop("disabled", false);
                }
            }
        });

    });

    $(".check-duplicate-category").change(function() {
        var docType = [];
        $(".check-duplicate-category").each(function() {
//            console.log($(this).prop("id"));
            var docTypeText = $("#" + $(this).prop("id") + " option:selected").text();
            console.log("docTypeText: " + docTypeText);

            if (docTypeText !== "--Select--")
            {
                docType.push($("#" + $(this).prop("id") + " option:selected").text());

//            console.log(docType);
                var result = hasDuplicate(docType);
//            console.log("result: " + result);
                if (result === "Found")
                {
//                $("#" + $(this).prop("id")).val("Select");
//                $("#" + $(this).prop("id")).css("border-color", "red");
                    $("#advsearchDocument").prop("disabled", false);
                    bootbox.dialog("Please select unique criteria!", [{"label": "ok", "class": "btn-small btn-primary"}]);
                    return false;
                }
                else
                {
                    $("#advsearchDocument").prop("disabled", false);
                }
            }
        });

    });

    function hasDuplicate(array)
    {
//        console.log("in function: " + array);
        var valuesSoFar = [];
        var status = "NotFound";
        for (var i = 0; i < array.length; i++)
        {
//            console.log("index: " + valuesSoFar.indexOf(array[i]));
//            console.log("valuesSoFar: " + valuesSoFar);
            if (valuesSoFar.indexOf(array[i]) !== -1)
            {
                status = "Found";
            }
            valuesSoFar.push(array[i]);
        }
//        console.log("valuesSoFar: " + valuesSoFar);
        return status;
    }

    $("#reset_btn_id").click(function() {
        var isValid = true;
        $(".reset-form-field").each(function() {
            if ($(this).val() === "")
            {
                $(this).css("border-color", "red");
                isValid = false;
            }
        });
        if (isValid === true)
        {
            $("#reset-password-form").submit();
        }
        else
        {
            return false;
        }
    });

    $("#sec_que_ans1").after("<div id='error' style='color:red;'></div>");
    $("#sec_que_ans2").after("<div id='error' style='color:red;'></div>");
    $("#sec_que_ans3").after("<div id='error' style='color:red;'></div>");

    $("#change-password").click(function() {

        var ans1 = $("#sec_que_ans1").val();
        var ans2 = $("#sec_que_ans2").val();
        var ans3 = $("#sec_que_ans3").val();

        var username = $("#customer-username-forgot").val();

        var isAdmin = $("#isAdmin").val();
        console.log("isAdmin: " + isAdmin);

        if (username === "")
        {
            $("#customer-username-forgot").css("border-color", "red");
            $("#customer-username-forgot").siblings().html('Please enter username!');
            return false;
        }
        else
        {
            $("#customer-username-forgot").css("border-color", "");
            $("#customer-username-forgot").siblings().html('');
        }

//        if (isAdmin !== "true")
        {
            if (ans1 === "")
            {
                $("#sec_que_ans1").css("border-color", "red");
                $("#sec_que_ans1").siblings().html('Please enter answer!');
                return false;
            }
            else if (ans1 !== $("#verify_sec_que_ans1").val())
            {
                $("#sec_que_ans1").css("border-color", "red");
                $("#sec_que_ans1").siblings().html('Please enter correct answer!');
                return false;
            }
            else
            {
                $("#sec_que_ans1").css("border-color", "");
                $("#sec_que_ans1").siblings().html('');
            }

            if (ans2 === "")
            {
                $("#sec_que_ans2").css("border-color", "red");
                $("#sec_que_ans2").siblings().html('Please enter answer!');
                return false;
            }
            else if (ans2 !== $("#verify_sec_que_ans2").val())
            {
                $("#sec_que_ans2").css("border-color", "red");
                $("#sec_que_ans2").siblings().html('Please enter correct answer!');
                return false;
            }
            else
            {
                $("#sec_que_ans2").css("border-color", "");
                $("#sec_que_ans2").siblings().html('');
            }

            if (ans3 === "")
            {
                $("#sec_que_ans3").css("border-color", "red");
                $("#sec_que_ans3").siblings().html('Please enter answer!');
                return false;
            }
            else if (ans3 !== $("#verify_sec_que_ans3").val())
            {
                $("#sec_que_ans3").css("border-color", "red");
                $("#sec_que_ans3").siblings().html('Please enter correct answer!');
                return false;
            }
            else
            {
                $("#sec_que_ans3").css("border-color", "");
                $("#sec_que_ans3").siblings().html('');
            }
        }
    });

    $("#customer-username-forgot").after("<div id='error' style='color:red;'></div>");
    $("#customer-username-forgot").blur(function() {
        var username = $(this).val();
        $.ajax(
                {
                    type: "GET",
                    url: "rfeCont.do",
                    async: true,
                    data:
                            {
                                "reqFrom": "CustomerUsernameCheckingAnsSecQue",
                                "UserName": username

                            },
                    dataType: "json",
                    complete: function(responseJson)
                    {
                        var obj = $.parseJSON(responseJson.responseText);
//                        alert(obj.Role);
                        if (obj.Status === 'NotExits')
                        {
//                                alert(obj.Status);
                            $("#customer-username-forgot").css("border-color", "red");
                            $("#customer-username-forgot").siblings().html('Username does not exist!');
                            $("#change-password").prop("disabled", true);

                            $("#sec_que1").val("");
                            $("#sec_que2").val("");
                            $("#sec_que3").val("");

                            $("#verify_sec_que_ans1").val("");
                            $("#verify_sec_que_ans2").val("");
                            $("#verify_sec_que_ans3").val("");

                            $("#sec_que_ans1").prop("readonly", true);
                            $("#sec_que_ans2").prop("readonly", true);
                            $("#sec_que_ans3").prop("readonly", true);
                        }
                        else
                        {
//                                alert(obj.Status);
                            $("#isAdmin").val(obj.Role);
                            $("#userid").val(obj.UserId);
                            if (obj.Role !== true)
                            {
//                                $("#sec_que1").css("display", "block");
//                                $("#sec_que2").css("display", "block");
//                                $("#sec_que3").css("display", "block");
//
//                                $("#sec_que_ans1").css("display", "block");
//                                $("#sec_que_ans2").css("display", "block");
//                                $("#sec_que_ans3").css("display", "block");

                                if (obj.Question.QuestionArr.length === 0)
                                {
                                    $("#customer-username-forgot").css("border-color", "red");
                                    $("#customer-username-forgot").siblings().html('Please update security questions first!');
                                    $("#change-password").prop("disabled", true);

                                    $("#sec_que1").val("");
                                    $("#sec_que2").val("");
                                    $("#sec_que3").val("");

                                    $("#verify_sec_que_ans1").val("");
                                    $("#verify_sec_que_ans2").val("");
                                    $("#verify_sec_que_ans3").val("");

                                    $("#sec_que_ans1").prop("readonly", true);
                                    $("#sec_que_ans2").prop("readonly", true);
                                    $("#sec_que_ans3").prop("readonly", true);
                                }
                                else
                                {


                                    $("#customer-username-forgot").css("border-color", "");
                                    $("#customer-username-forgot").siblings().html('');
                                    $("#change-password").prop("disabled", false);
//                            console.log(obj.Question.QuestionArr);
//                            console.log(obj.Answer.AnswerArr);

                                    $("#sec_que1").val(obj.Question.QuestionArr[0]);
                                    $("#sec_que2").val(obj.Question.QuestionArr[1]);
                                    $("#sec_que3").val(obj.Question.QuestionArr[2]);

                                    $("#verify_sec_que_ans1").val(obj.Answer.AnswerArr[0]);
                                    $("#verify_sec_que_ans2").val(obj.Answer.AnswerArr[1]);
                                    $("#verify_sec_que_ans3").val(obj.Answer.AnswerArr[2]);

                                    $("#sec_que_ans1").prop("readonly", false);
                                    $("#sec_que_ans2").prop("readonly", false);
                                    $("#sec_que_ans3").prop("readonly", false);
                                }
                            }
                            else
                            {
//                                $("#sec_que1").val("");
//                                $("#sec_que2").val("");
//                                $("#sec_que3").val("");
//
//                                $("#verify_sec_que_ans1").val("");
//                                $("#verify_sec_que_ans2").val("");
//                                $("#verify_sec_que_ans3").val("");
//
//                                $("#sec_que_ans1").prop("readonly", true);
//                                $("#sec_que_ans2").prop("readonly", true);
//                                $("#sec_que_ans3").prop("readonly", true);
//
//                                $("#sec_que1").css("display", "none");
//                                $("#sec_que2").css("display", "none");
//                                $("#sec_que3").css("display", "none");
//
//                                $("#sec_que_ans1").css("display", "none");
//                                $("#sec_que_ans2").css("display", "none");
//                                $("#sec_que_ans3").css("display", "none");
                            }
                        }
                    }
                });
    });

    $("#customer-username").after("<div id='error' style='color:red;'></div>");
    $("#customer-username").blur(function() {
        var username = $(this).val();
        $.ajax(
                {
                    type: "GET",
                    url: "rfeCont.do",
                    async: true,
                    data:
                            {
                                "reqFrom": "CustomerUsernameChecking",
                                "UserName": username

                            },
                    dataType: "json",
                    complete: function(responseJson)
                    {
                        var obj = $.parseJSON(responseJson.responseText);
                        //                            alert(obj.Status);
                        if (obj.Status === 'NotExits')
                        {
                            //                                alert(obj.Status);
                            $("#customer-username").css("border-color", "red");
                            $("#customer-username").siblings().html('Username does not exist!');
                            $("#check_constraint").val('false');
                            $("#change-password").prop("disabled", true);
                        }
                        else
                        {
                            //                                alert(obj.Status);
                            $("#customer-username").css("border-color", "");
                            $("#customer-username").siblings().html('');
                            $("#check_constraint").val('true');
                            $("#change-password").prop("disabled", false);
                        }
                    }
                });
    });

    $("#username").change(function() {
        var username = $(this).val();
        $.ajax(
                {
                    type: "GET",
                    url: "rfeCont.do",
                    async: true,
                    data:
                            {
                                "reqFrom": "CustomerUsernameChecking",
                                "UserName": username

                            },
                    dataType: "json",
                    complete: function(responseJson)
                    {
                        var obj = $.parseJSON(responseJson.responseText);
//                            alert(obj.Status);
                        if (obj.Status === 'NotExits')
                        {
//                                alert(obj.Status);
                            $("#username").css("border-color", "");
                            $("#username").siblings().html('');
//                            $("#check_constraint").val('false');
                            $("#add_user_btn").prop("disabled", false);

                            $("#update_edituserbtn").prop("disabled", false);
                        }
                        else
                        {
//                                alert(obj.Status);

                            $("#username").css("border-color", "red");
                            $("#username").siblings().html('Username already exist!');
//                            $("#check_constraint").val('true');
                            $("#add_user_btn").prop("disabled", true);

                            $("#update_edituserbtn").prop("disabled", true);
                        }
                    }
                });
    });

    $("#add_user_btn").click(function() {
        var username = $("#username").val();
        var customer = $("#customerIds").val();
        console.log("customer: " + customer);
        if (customer === "" || customer === "--Select--")
        {
            bootbox.dialog("Please select customers first!", [{"label": "ok", "class": "btn-small btn-primary"}]);
            return false;
        }
        var personalfirstname = $("#personalfirstname").val();
        if (personalfirstname === "")
        {
            bootbox.dialog("Please enter personal first name!", [{"label": "ok", "class": "btn-small btn-primary"}]);
            return false;
        }
        var personallastname = $("#personallastname").val();
        if (personallastname === "")
        {
            bootbox.dialog("Please enter personal last name!", [{"label": "ok", "class": "btn-small btn-primary"}]);
            return false;
        }

        if ($("#password").val() !== "" && $("#confirmpassword").val() === "")
        {
            bootbox.dialog("Please enter confirm password!", [{"label": "ok", "class": "btn-small btn-primary"}]);
            return false;
        }
        if ($("#password").val() !== $("#confirmpassword").val())
        {
            bootbox.dialog("Password and confirm password must be same!", [{"label": "ok", "class": "btn-small btn-primary"}]);
            return false;
        }
        if (username === "")
        {
            bootbox.dialog("Please enter username!", [{"label": "ok", "class": "btn-small btn-primary"}]);
            return false;
        }
        bootbox.confirm("Default Password for this user is natsteel351", function(result) {
            if (result === true)
            {
                $("#add_user_form").submit();
            }

        });
    });

    $("#oldpassword").after("<div id='error' style='color:red;'></div>");
    $("#oldpassword").blur(function() {
        var username = $("#customer-username").val();
        var oldpassword = $(this).val();
        //        alert(oldpassword);
        $.ajax(
                {
                    type: "GET",
                    url: "rfeCont.do",
                    async: true,
                    data:
                            {
                                "reqFrom": "CustomerUsernameAndPasswordChecking",
                                "UserName": username,
                                "OldPassword": oldpassword
                            },
                    dataType: "json",
                    complete: function(responseJson)
                    {
                        var obj = $.parseJSON(responseJson.responseText);
                        //                            alert(obj.Status);
                        if (obj.Status === 'NotExits')
                        {
                            //                                alert(obj.Status);
                            $("#oldpassword").css("border-color", "red");
                            $("#oldpassword").siblings().html('Wrong password!');
                            $("#check_constraint").val('false');
                            $("#change-password").prop("disabled", true);
                        }
                        else
                        {
                            //                                alert(obj.Status);
                            $("#oldpassword").css("border-color", "");
                            $("#oldpassword").siblings().html('');
                            $("#check_constraint").val('true');
                            $("#change-password").prop("disabled", false);
                        }
                    }
                });
    });
    //    $("#oldpassword").after("<div id='error' style='color:red;'></div>");
//    $("#oldpassword").keyup(function() {
//        var val = $(this).val();
//        //        var regex = new RegExp('^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])([a-zA-Z0-9]{8,30})$');
//        var regex = new RegExp('^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$');
//        //        var regex = new RegExp('^(?=.{8,})(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$');
//        var passconfig = $("#passconfig").val();
//        var passConfigArray = passconfig.split(",");
//        console.log("passconfigarray length: " + passConfigArray.length);
//        var result = validatePassword(val, passConfigArray);
//        console.log("result: " + result);
//
//        
//        if (result === true)
//        {
//            $(this).css("border-color", "");
//            $(this).siblings().html('');
//            $("#check_constraint").val('true');
//            $("#change-password").prop("disabled", true);
//        }
//        else
//        {
//            var passLen = "";
//            var specialCharCount = "";
//            var uppCharCount = "";
//            var lowCharCount = "";
//            var numCount = "";
//
//            if (passConfigArray.length > 0)
//            {
//                passLen = passConfigArray[0];
//                specialCharCount = passConfigArray[1];
//                uppCharCount = passConfigArray[2];
//                lowCharCount = passConfigArray[3];
//                numCount = passConfigArray[4];
//            }
//            
//            $(this).css("border-color", "red");
//            $(this).siblings().html('Password length must be ' + passLen + ' characters.<br>Password must contain ' + specialCharCount + ' special characters.<br>Password must contain ' + uppCharCount + ' uppercase characters.<br>Password must contain ' + lowCharCount + ' lowercase characters.<br>Password must contain ' + numCount + ' numberic characters.');
//            $("#check_constraint").val('false');
//            $("#change-password").prop("disabled", false);
//        }
//    });
    $("#newpassword").after("<div id='error' style='color:red;'></div>");
    $("#newpassword").keyup(function() {
        var val = $(this).val();
//        var regex = new RegExp('^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])([a-zA-Z0-9]{8,30})$');
        var regex = new RegExp('^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$');
//        var regex = new RegExp('^(?=.{8,})(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$');
        var passconfig = $("#passconfig").val();
        var passConfigArray = passconfig.split(",");
        console.log("passconfigarray length: " + passConfigArray.length);
        var result = validatePassword(val, passConfigArray);
        console.log("result: " + result);


        if (result === true)
        {
            $(this).css("border-color", "");
            $(this).siblings().html('');
//            $("#check_constraint").val('true');
            $("#reset_btn_id").prop("disabled", false);
            $("#change-password").prop("disabled", false);
        }
        else
        {
            var passLen = "";
            var specialCharCount = "";
            var uppCharCount = "";
            var lowCharCount = "";
            var numCount = "";

            if (passConfigArray.length > 0)
            {
                passLen = passConfigArray[0];
                specialCharCount = passConfigArray[1];
                uppCharCount = passConfigArray[2];
                lowCharCount = passConfigArray[3];
                numCount = passConfigArray[4];
            }


            $(this).css("border-color", "red");
            $(this).siblings().html('Password length must be ' + passLen + ' characters.<br>Password must contain ' + specialCharCount + ' special characters.<br>Password must contain ' + uppCharCount + ' uppercase characters.<br>Password must contain ' + lowCharCount + ' lowercase characters.<br>Password must contain ' + numCount + ' numberic characters.');
//            $("#check_constraint").val('false');
            $("#reset_btn_id").prop("disabled", true);

            $("#change-password").prop("disabled", true);
        }
    });

    $("#confirmnewpassword").after("<div id='error' style='color:red;'></div>");
    $("#confirmnewpassword").keyup(function() {
        var val = $(this).val();
//        var regex = new RegExp('^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])([a-zA-Z0-9]{8,30})$');
        var regex = new RegExp('^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$');
//        var regex = new RegExp('^(?=.{8,})(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$');
        var passconfig = $("#passconfig").val();
        var passConfigArray = passconfig.split(",");
        console.log("passconfigarray length: " + passConfigArray.length);
        var result = validatePassword(val, passConfigArray);
        console.log("result: " + result);


        if (result === true)
        {
            $(this).css("border-color", "");
            $(this).siblings().html('');
//            $("#check_constraint").val('true');
            $("#reset_btn_id").prop("disabled", false);
        }
        else
        {
            var passLen = "";
            var specialCharCount = "";
            var uppCharCount = "";
            var lowCharCount = "";
            var numCount = "";

            if (passConfigArray.length > 0)
            {
                passLen = passConfigArray[0];
                specialCharCount = passConfigArray[1];
                uppCharCount = passConfigArray[2];
                lowCharCount = passConfigArray[3];
                numCount = passConfigArray[4];
            }

            $(this).css("border-color", "red");
            $(this).siblings().html('Password length must be ' + passLen + ' characters.<br>Password must contain ' + specialCharCount + ' special characters.<br>Password must contain ' + uppCharCount + ' uppercase characters.<br>Password must contain ' + lowCharCount + ' lowercase characters.<br>Password must contain ' + numCount + ' numberic characters.');
//            $("#check_constraint").val('false');
            $("#reset_btn_id").prop("disabled", true);
        }
    });

    $("#confirmnewpassword").blur(function() {
        if ($("#newpassword").val() !== '')
        {
            if ($("#confirmnewpassword").val() !== '')
            {
                if ($("#newpassword").val() !== $("#confirmnewpassword").val())
                {
                    $("#confirmnewpassword").css("border-color", "red");
                    $("#confirmnewpassword").siblings().html('Password and confirm passwor must be same!');
//                    $("#check_constraint").val('false');
                    $("#reset_btn_id").prop("disabled", true);
                }
                else
                {
//                    $("#check_constraint").val('true');
                    $("#reset_btn_id").prop("disabled", false);
                }
            }
        }
    });

    $("#customer_post_btn").click(function() {
        //       alert("dff");
        var message = $(".wysiwyg-editor").text();
        $("#messageContent").val(message);
        //        alert("sdfadsf");
        var param = window.location.search.substring(1).split("&").toString();
        //        alert(param.split("="));
        $("#supplierid").val(param.split("=")[1]);
        //        alert(encodeURIComponent(message));
        //        alert($("#supplierid").val());
        //        alert($("#messageContent").val());
        $("#customer-comment-form").submit();

    });
    $("#customerIds").change(function() {
        //        alert("asd");
        var custId = $(this).val();
        //    alert(custId);
        $.ajax(
                {
                    type: "GET",
                    url: "rfeCont.do",
                    async: true,
                    data:
                            {
                                "reqFrom": "CustomerDetails",
                                "CustomerId": custId
                            },
                    dataType: "json",
                    complete: function(responseJson)
                    {
                        var obj = $.parseJSON(responseJson.responseText);
                        //                            alert(obj);

                        $("#fisrtname").val(obj.FNAME);
                        $("#lastname").val(obj.LNAME);
                        $("#custaddline1").val(obj.ADDLINE1);
                        $("#custaddline2").val(obj.ADDLINE2);
                        $("#custaddline3").val(obj.ADDLINE3);
                        $("#emailid").val(obj.EMAILID);

                        console.log("ProjectList: " + obj.ProjectList.length);
                        $("#avail_project_multiple option").remove();

                        for (var i = 0; i < obj.ProjectList.length; i++)
                        {
                            $("<option>").val(obj.ProjectList[i].PROJECT_ID).text(obj.ProjectList[i].PROJECT_NAME).appendTo("#avail_project_multiple");
                        }
                    }
                });
    });
    $("#close_edituserbtn").click(function() {
        location.href = "manageuser.do";
    });

    $("#reset_password_link").click(function() {
        $("#pass_word").val("natsteel351");
        $("#confirm_password").val("natsteel351");
        bootbox.dialog("Default Password is <b>natsteel351</b>", [{"label": "ok", "class": "btn-small btn-primary"}]);
    });

    $("#edit_edituserbtn").click(function() {

        var userRole = $("#loggedInUserRole").val();
        console.log("userRole: " + userRole);

        $("#update_edituserbtn").removeAttr("style");
        $("#delete_edituserbtn").removeAttr("style");

        if (userRole === "Admin")
        {
            $("#reset_password_link").removeAttr("style");
        }
        $(this).css("display", "none");
        //$(".edit-customer-user-form").removeAttr("readonly");
        $("#status").val($("#ro_status").val());

        $("#status").removeAttr("style");
        $("#ro_status").css("display", "none");

        if (userRole === "Admin")
        {
//            $("#username").removeAttr("readonly");
//            $("#password").removeAttr("readonly");
//            $("#confirmpassword").removeAttr("readonly");
        }

        $("#projectIdMultiple").prop("disabled", false);
        $("#avail_document_multiple").prop("disabled", false);

        $("#personalfirstname").prop("readonly", false);
        $("#personallastname").prop("readonly", false);
        $("#personalemailid").prop("readonly", false);
        $("#personalcontactnumber").prop("readonly", false);
    });
    $("#delete_edituserbtn").click(function() {
        var param = window.location.search.substring(1).toString();
        //        alert(param.split("=")[1]);
        bootbox.confirm("Are you sure, you want to delete this user!", function(result) {
            if (result === true)
            {
                location.href = "deletecustomeruser.do?uid=" + param.split("=")[1];
            }

        });

    });

    $("#remove_project_btn").click(function() {

        var len = $('#ro_projectname').has('option').length;
        console.log(len);


        var pid = $('#ro_projectname option:selected').val();
        console.log("pid: " + pid);

        if (pid !== undefined)
        {
            var param = window.location.search.substring(1).toString();
            bootbox.confirm("Are you sure, you want to remove selected project!", function(result) {
                if (result === true)
                {
                    location.href = "removecustomerproject.do?uid=" + param.split("=")[1] + "&pid=" + pid;
//                    $("#overlay").css("display", "block");
                }

            });
        }
        else
        {
            bootbox.dialog("Please select project!", [{"label": "ok", "class": "btn-small btn-primary"}]);
            return false;
        }
    });


    $("#remove_document_btn").click(function() {

        var len = $('#document_right').has('option').length;
        console.log(len);


        var gid = $('#document_right option:selected').val();
        console.log("gid: " + gid);

        if (gid !== undefined)
        {
            var param = window.location.search.substring(1).toString();
            bootbox.confirm("Are you sure, you want to remove selected document!", function(result) {
                if (result === true)
                {
                    location.href = "removecustomerdocument.do?uid=" + param.split("=")[1] + "&gid=" + gid;
//                    $("#overlay").css("display", "block");
                }

            });
        }
        else
        {
            bootbox.dialog("Please select document!", [{"label": "ok", "class": "btn-small btn-primary"}]);
            return false;
        }
    });

    $("#update_edituserbtn").click(function() {

        if ($("#password").val() !== "" && $("#confirmpassword").val() === "")
        {
            bootbox.dialog("Please enter confirm password!", [{"label": "ok", "class": "btn-small btn-primary"}]);
            return false;
        }
        if ($("#password").val() !== $("#confirmpassword").val())
        {
            bootbox.dialog("Password and confirm password must be same!", [{"label": "ok", "class": "btn-small btn-primary"}]);
            return false;
        }

        bootbox.confirm("Are you sure, you want to update this user!", function(result) {
            if (result === true)
            {
                var param = window.location.search.substring(1).toString();
                $("#uid").val(param.split("=")[1]);
                $("#update_user_form").submit();
            }

        });
    });



    var selectedCustomerId = "";
    $("#avail_customers").click(function() {

        $("#deselect-customer-btn").addClass("hidden");

        var selectedSupplierLen = $("select#mapped_customers option").length;
        //console.log(selectedSupplierLen);
        if (selectedSupplierLen !== 0)
        {
            var selectedSupplier = $(this).val();
            var temp = 0;
            $("#mapped_customers > option").each(function() {
                //console.log(this.value + " : " + selectedSupplier);
                if (this.value === selectedSupplier)
                {
                    bootbox.dialog("Already selected!", [{
                            "label": "ok",
                            "class": "btn-small btn-primary"
                        }]);
                    temp = 1;
                }
            });
            if (temp === 1)
            {
                return;
            }
            bootbox.dialog("Customer added.", [{"label": "ok", "class": "btn-small btn-primary"}]);

            $("<option>").val($(this).val()).text($("#avail_customers option:selected").text()).appendTo("#mapped_customers");
            selectedCustomerId = selectedCustomerId + $(this).val() + "~";

            $("#mappcustomertogroupbtn").removeClass("hidden");
            $("#clear-customer-btn").removeClass("hidden");
        }
        else
        {
            bootbox.dialog("Customer added.", [{"label": "ok", "class": "btn-small btn-primary"}]);

            $("<option>").val($(this).val()).text($("#avail_customers option:selected").text()).appendTo("#mapped_customers");
            selectedCustomerId = selectedCustomerId + $(this).val() + "~";

            $("#mappcustomertogroupbtn").removeClass("hidden");
            $("#clear-customer-btn").removeClass("hidden");
        }
        console.log("mapped_customers: " + selectedCustomerId);
        $("#selectedSupplierId").val(selectedCustomerId);
    });


    $("#select-all-customer-btn").click(function() {
//        $("#mapped_customers").empty();
//        $("#mapped_customers").append($("#avail_customers option").clone());
//        $("#select-all-customer-btn").prop("disabled", true);
//        selectedCustomerId = "";
//        $("#avail_customers > option").each(function() {
//            //            console.log(this.value);
//            selectedCustomerId = selectedCustomerId + $(this).val() + "~";
//        });
//        console.log("selectedSupplierId: " + selectedCustomerId);
//        $("#selectedSupplierId").val(selectedCustomerId);
//
//        $("#mappcustomertogroupbtn").removeClass("hidden");

        var mapp_array = [];
        var mapp_array_text = [];

        var selectedCustomerId = "";
        $("#mapped_customers > option").each(function() {
//            console.log(this.value);
            mapp_array.push(this.value);
            mapp_array_text.push(this.text);
        });
        console.log("mapp_array: " + mapp_array);
        if (mapp_array.length > 0)
        {
            $("#select-all-customer-btn").prop("disabled", true);

            var avail_array = [];
            var avail_array_text = [];


            $("#avail_customers > option").each(function() {
//            console.log(this.text);
                avail_array.push($(this).val());
                avail_array_text.push(this.text);
            });



            console.log("avail_array: " + avail_array);
            console.log("avail_array_text: " + avail_array_text);

            var temp = [];
            var temp_text = [];

            temp = avail_array.filter(function(el) {
                return mapp_array.indexOf(el) < 0;
            });

            temp_text = avail_array_text.filter(function(el) {
                return mapp_array_text.indexOf(el) < 0;
            });


            console.log("temp: " + temp);
            console.log("temp_text: " + temp_text);

            if (temp.length > 0)
            {
                for (var i = 0; i < temp.length; i++)
                {
                    $("<option>").val(temp[i]).text(temp_text[i]).appendTo("#mapped_customers");
                    selectedCustomerId = selectedCustomerId + temp[i] + "~";
                }
            }
        }
        else
        {
            $("#avail_customers > option").each(function() {
//            console.log(this.text);
                selectedCustomerId = selectedCustomerId + $(this).val() + "~";

            });
            $("#mapped_customers").empty();
            $("#mapped_customers").append($("#avail_customers option").clone());

        }
        console.log("selectedSupplierId: " + selectedCustomerId);
        $("#selectedSupplierId").val(selectedCustomerId);

        $("#mappcustomertogroupbtn").removeClass("hidden");

    });
    $("#deselect-all-customer-btn").click(function() {
        $("#mapped_customers").empty();
        $("#select-all-customer-btn").prop("disabled", false);
        selectedCustomerId = "";
        console.log("mapped_customers: " + selectedCustomerId);
        $("#selectedSupplierId").val(selectedCustomerId);
    });
    $("#search_customer").on("keyup", function() {
        //        alert("dsfd");
        var value = $(this).val().toLowerCase();
        $("#avail_customers option").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1);
        });
    });
    $("#mappcustomertogroupbtn").click(function() {
        //        alert("sdfs");
        var selectedCustomerLen = $("select#mapped_customers option").length;
        if (selectedCustomerLen == 0)
        {
            bootbox.dialog("Please select customers first!", [{
                    "label": "ok",
                    "class": "btn-small btn-primary"
                }]);
            return false;
        }

        bootbox.confirm("Are you sure, you want to update this user!", function(result) {
            if (result === true)
            {
                var param = window.location.search.substring(1).toString();
                $("#gid").val(param.split("=")[1]);
                $("#group-mapping-form").submit();
            }

        });
    });

    $("#mapped_customers").click(function() {
        //        alert($(this).val());
        var param = window.location.search.substring(1).toString();
        var gid = param.split("=")[1];
        var custId = $(this).val();
        //        alert(gid + " : " + custId);
        $("#gid").val(gid);
        $("#custId").val(custId);

        $("#deselect-customer-btn").removeClass("hidden");

        //        $('#mapped_customers option:selected').remove();

    });
    $("#deselect-customer-btn").click(function() {

        var gid = $("#gid").val();
        var custId = $("#custId").val();
        //        alert(gid + " : " + custId);
        var x = $("#selectedSupplierId").val();

        if (x === "")
        {
            $("#mappcustomertogroupbtn").addClass("hidden");
        }

        if (x.toString().indexOf(custId + "~") !== -1)
        {
            var temp = x.toString().replace(custId + "~", "");
            $("#selectedSupplierId").val(temp);

            if (temp === "")
            {
                $("#mappcustomertogroupbtn").addClass("hidden");
            }
        }
        $.ajax(
                {
                    type: "GET",
                    url: "rfeCont.do",
                    async: true,
                    data:
                            {
                                "reqFrom": "UnmappCustomerFromGroup",
                                "Gid": gid,
                                "CustId": custId
                            },
                    dataType: "json",
                    complete: function(responseJson)
                    {
                        var obj = $.parseJSON(responseJson.responseText);
                        //                        alert(obj.status);
                        if (obj.status === "Removed")
                        {
//                            location.href = "mappedgroup.do?gid=" + gid;
                            $("#mapped_customers option[value=" + custId + "]").remove();
                        }
                        else
                        {
                            $("#mapped_customers option[value=" + custId + "]").remove();
//                            $("#overlay").css("display", "none");
                        }
                    }
                });
    });

    var selectedProjectId = "";
    $("#avail_project").click(function() {

        var selectedSupplierLen = $("select#mapped_project option").length;
        //console.log(selectedSupplierLen);
        if (selectedSupplierLen !== 0)
        {
            var selectedSupplier = $(this).val();
            var temp = 0;
            $("#mapped_project > option").each(function() {
                //console.log(this.value + " : " + selectedSupplier);
                if (this.value === selectedSupplier)
                {
                    bootbox.dialog("Already selected!", [{
                            "label": "ok",
                            "class": "btn-small btn-primary"
                        }]);
                    temp = 1;
                }
            });
            if (temp === 1)
            {
                return;
            }
            $("<option>").val($(this).val()).text($("#avail_project option:selected").text()).appendTo("#mapped_project");
            selectedProjectId = selectedProjectId + $(this).val() + "~";
        }
        else
        {
            $("<option>").val($(this).val()).text($("#avail_project option:selected").text()).appendTo("#mapped_project");
            selectedProjectId = selectedProjectId + $(this).val() + "~";
        }
        console.log("mapped_customers: " + selectedProjectId);
        //        $("#selectedSupplierId").val(selectedProjectId);
    });
    $("#search_project").on("keyup", function() {
        //        alert("dsfd");
        var value = $(this).val().toLowerCase();
        $("#avail_project option").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1);
        });
    });

    $("#mapped_project").click(function() {
        //        alert($(this).val());

        $('#mapped_project option:selected').remove();

    });
    $("#adv_docType").change(function() {
        //        alert("fsasf");
        if ($(this).val() !== 'Select')
        {
            if ($(this).val() === 'Mill Certificate')
            {
                $("#documentreference").prop("disabled", true);
            }
            else
            {
                $("#documentreference").prop("disabled", false);
            }
        }
    });

    $("#clearMyOrderCriteria").click(function() {

        location.reload(true);

//        if ($("#userRole").val() === "Admin")
//        {
//            $("#customerId").val('--Select--');
//        }
//        console.log("project: " + $("#projectId").val());
//        $("#projectId").val("");



    });

    $("#searchDocument").click(function() {


        var role = $("#userRole").val();
        console.log(role);

        if (role !== "Admin" && role !== "Company Admin")
        {
            var docList = "";
            $("#docType option").each(function() {
                docList = docList + $(this).text() + ",";
            });
            console.log("docList: " + docList);
        }

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



        var projectId = $("#projectId").val();
        console.log("projectId: " + projectId);

        if (projectId === "--Select--" || projectId === "")
        {
            bootbox.dialog("Please select project!", [{
                    "label": "ok",
                    "class": "btn-small btn-primary"
                }]);
            $("#projectId").css("border-color", "red");
            return false;
        }


        if ($("#my_project_from_date").val() !== "" && $("#my_project_to_date").val() === "")
        {
            bootbox.dialog("Please enter to date!", [{
                    "label": "ok",
                    "class": "btn-small btn-primary"
                }]);
            return false;
        }
        else
        {

//            $("#overlay").css("display", "block");
            console.log("start loading...");
//            $("#advsearchdownloadall").css("display", "block");
            $("#projectId").css("border-color", "");

            var xmlInput = "<InputCriteria>";
            xmlInput += "<CustomerId>" + customerid + "</CustomerId>";
            xmlInput += "<ProjectId>" + projectId + "</ProjectId>";

            if ($("#my_project_from_date").val() !== "" && $("#my_project_to_date").val() !== "")
            {
                xmlInput += "<FromDate>" + $("#my_project_from_date").val() + "</FromDate>";
                xmlInput += "<ToDate>" + $("#my_project_to_date").val() + "</ToDate>";
            }

            xmlInput += "</InputCriteria>";




            var dmsip = $("#dmsip").val();
            console.log(dmsip);

//            var URLParam = 'InputXML=' + xmlInput + '&RequestType=advanced';
            var URLParam = 'InputXML=' + xmlInput + '&RequestType=projectTree';
            console.log("URLParam: " + URLParam);

            var serviceUrl = dmsip + "/WebServiceCall/CallWebService";
            console.log("serviceUrl: " + serviceUrl);

//            myProjectTreeStructure("");
//            findDocumentByProjectIdAndCustomerId("");
//            CustomerPortalapp/ng/search/advanced
//
            $.ajax({
                type: "POST",
                url: serviceUrl,
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                dataType: "xml",
                data: URLParam,
                async: false,
                success: function(data, textStatus, jqXHR) {
                    //                    alert("success: " + data);
                    console.log("success: " + data);
                    myProjectTreeStructure(data);

                }
            });
        }
        console.log("end loading...");
//        $("#overlay").css("display", "none");
    });

    $("#advsearchDocument").click(function() {

//        $("#overlay").css("display", "block");

        console.log("Before Calling SetXML");
        toSetXML();
        console.log("after Calling SetXML");

//        $("#overlay").css("display", "none");
    });
    $("#docType").change(function() {
        //        alert("fsasf");
        var type = $("#docType option:selected").text();
        if (type !== 'Select')
        {
            if (type === 'Mill Certificate' || type === 'Delivery Order Summary')
            {
                $("#docref").prop("disabled", true);
                $("#docref").css("border-color", "");
            }
            else
            {
                $("#docref").prop("disabled", false);
            }
            $("#my_project_fromDate").val("");
            $("#my_project_toDate").val("");
            $("#docref").val("");
        }
    });

    $("#getProjectDocumentBtn").click(function() {



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
//                $("#docref").focus();
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


        var projectId = $("#projectId").val();
        //alert(projectId);

        var docType = $("#docType").val();
        var docTypeText = $("#docType option:selected").text();
        //alert(docType);
        //alert(docTypeText);

        var docref = $("#docref").val();
        //alert(docref);



        if (projectId === "--Select--" || projectId === "")
        {
            bootbox.dialog("Please select project!", [{
                    "label": "ok",
                    "class": "btn-small btn-primary"
                }]);
//            $("#projectId").focus();
            $("#projectId").css("border-color", "red");
            return false;
        }
        else
        {
            $("#projectId").css("border-color", "");
        }

        if (docType === "--Select--")
        {
            bootbox.dialog("Please select document category!", [{
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

        if (docTypeText !== 'Mill Certificate' && docTypeText !== 'Delivery Order Summary' && docref === "")
        {
//            bootbox.dialog("Please enter document reference!", [{
//                    "label": "ok",
//                    "class": "btn-small btn-primary"
//                }]);
//            $("#docref").css("border-color", "red");
//            return false;
        }
        else
        {
            $("#docref").css("border-color", "");
        }

        if (docTypeText === 'Mill Certificate' || docTypeText === 'Delivery Order Summary')
        {
            if ($("#my_project_fromDate").val() === "")
            {
                bootbox.dialog("Please enter from date!", [{
                        "label": "ok",
                        "class": "btn-small btn-primary"
                    }]);
                $("#my_project_fromDate").css("border-color", "red");
                return false;
            }
            else
            {
                $("#my_project_fromDate").css("border-color", "");
            }
            if ($("#my_project_toDate").val() === "")
            {
                bootbox.dialog("Please enter to date!", [{
                        "label": "ok",
                        "class": "btn-small btn-primary"
                    }]);
                $("#my_project_toDate").css("border-color", "red");
                return false;

            }
            else
            {
                $("#my_project_toDate").css("border-color", "");
            }
        }

        if ($("#my_project_fromDate").val() !== "")
        {
            if ($("#my_project_toDate").val() === "")
            {
                bootbox.dialog("Please enter to date!", [{
                        "label": "ok",
                        "class": "btn-small btn-primary"
                    }]);
                $("#my_project_toDate").css("border-color", "red");
                return false;

            }
            else
            {
                $("#my_project_toDate").css("border-color", "");
            }
        }
//        if ((docTypeText === 'Invoice' || docTypeText === 'Credit Note' || docTypeText === 'Debit Note') && docref === "")
//        {
//            bootbox.dialog("Please Enter Document Reference No.", [{
//                    "label": "ok",
//                    "class": "btn-small btn-primary"
//                }]);
//            $("#docref").focus();
//            return false;
//        }
        var documentCategory = "";

        if (docTypeText === "Invoice" || docTypeText === "Debit Note" || docTypeText === "Credit Note" || docTypeText === "Delivery Order Summary")
        {
            documentCategory = docType;
        }
        else
        {
            documentCategory = docTypeText;
        }
        console.log("documentCategory: " + documentCategory);

//        $("#overlay").css("display", "block");
        console.log("start loading...");

        {
            $("#Search_document_table_div").css("display", "block");

            var xmlInput = "<InputCriteria>";
            xmlInput += "<CustomerId>" + customerid + "</CustomerId>";
            xmlInput += "<ProjectId>" + projectId + "</ProjectId>";


            console.log($("#my_project_fromDate").val());
            console.log($("#my_project_toDate").val());

            if ($("#my_project_fromDate").val() !== "" && $("#my_project_toDate").val() !== "")
            {
                xmlInput += "<FromDate>" + $("#my_project_fromDate").val() + "</FromDate>";
                xmlInput += "<ToDate>" + $("#my_project_toDate").val() + "</ToDate>";
            }
            xmlInput += "<DocumentDetails>";
            xmlInput += "<DocumentType>" + documentCategory + "</DocumentType>";
            xmlInput += "<ReferenceNo>" + docref + "</ReferenceNo>";
            xmlInput += "</DocumentDetails>";
            xmlInput += "</InputCriteria>";


            console.log(xmlInput);

            var dmsip = $("#dmsip").val();
            console.log(dmsip);

            var URLParam = 'InputXML=' + xmlInput + '&RequestType=advanced';
            console.log("URLParam: " + URLParam);

            var serviceUrl = dmsip + "/WebServiceCall/CallWebService";
            console.log("serviceUrl: " + serviceUrl);

//            CustomerPortalapp/ng/search/advanced

            $.ajax({
                type: "POST",
                url: serviceUrl,
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                dataType: "xml",
                data: URLParam,
                success: function(data, textStatus, jqXHR) {
                    console.log("success: " + data);
                    searchDocumentByCategory(data);

                }
            });
        }

//        $("#overlay").css("display", "none");
        console.log("end loading...");
    });
});

var xmlInputForDownloadAll = "";
var docIndexForDownloadAll = "";

function searchDocumentByCategory(xmlre)
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
        DocInsertDate.push($(this).text());
    });

//    $("#searchDocumentDownloadAll").removeAttr("style");

    if (DocIndex.length > 0)
    {
        $("#advsearchdownloadall").css("display", "block");
    }
    else
    {
        $("#advsearchdownloadall").css("display", "none");
    }

    var xmlInputDownload = "";
    var row = "";
    var n = "N";
    var y = "Y";

    docIndexForDownloadAll = "";

    for (var i = 0; i < DocIndex.length; i++)
    {

        var temp = DocIndex[i] + "," + DocName[i] + "," + DocExt[i];
        multipleDownloadDataArray = multipleDownloadDataArray + temp + "<>";


        row += "<tr><td>" + (i + 1) + "</td><td>" + DocName[i] + "</a></td><td>" + DocType[i] + "</td><td>" + DocExt[i] + "</td><td>" + DocInsertDate[i] + "</td><td class='center'><a href='#' onclick=downloadSingleDoc(" + DocIndex[i] + ",'" + y + "') title='View Document'><i class='fa fa-eye fa-2x'></i></a></td><td class='center'><a href='#' onclick=\"downloadSingleDoc(" + DocIndex[i] + ",'" + n + "');customerAuditReport(" + DocIndex[i] + ",'" + DocName[i] + "','" + DocExt[i] + "','" + y + "');\" title='Download Document'><i class='fa fa-download fa-2x'></i></a></td></tr>";

        xmlInputDownload += "%3CDocumentDetails%3E";
        xmlInputDownload += "%3CDocumentIndex%3E" + DocIndex[i] + "%3C/DocumentIndex%3E";
        xmlInputDownload += "%3C/DocumentDetails%3E";

        if (i === 0)
        {
            docIndexForDownloadAll = docIndexForDownloadAll + DocIndex[i];
        }
        else
        {
            docIndexForDownloadAll = docIndexForDownloadAll + "," + DocIndex[i];
        }
    }
    console.log("xmlInputDownload: " + xmlInputDownload);
    console.log("docIndexForDownloadAll: " + docIndexForDownloadAll);

    xmlInputForDownloadAll = "%3CInputCriteria%3E";
    xmlInputForDownloadAll += xmlInputDownload;
    xmlInputForDownloadAll += "%3C/InputCriteria%3E";



    console.log("xmlInputForDownloadAll: " + xmlInputForDownloadAll);


    $("#Search_document_table_div").css("display", "block");
    $("#Search_document_table").children("tbody").html(row);

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
var multipleDownloadDataArray = "";
var tempMultipleDownloadDataArray = "";
function findDocumentByProjectIdAndCustomerId(xmlre)
{
    console.log("findDocumentByProjectIdAndCustomerId:" + xmlre);
    var xmlString = XMLToString(xmlre); //Convert the XML Object to a String
    var xmlDoc = $.parseXML(xmlString); //Parse the XML String to get data
    var $xml = $(xmlDoc);

    var $ProjectDetails = $xml.find('DocumentDetails');

    console.log("$ProjectDetails:" + $ProjectDetails);

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
        DocInsertDate.push($(this).text());
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
    console.log("DocIndex Length:" + DocIndex.length + " DocIndex[0]:" + DocIndex[0]);

    var n = "N";
    var y = "Y";
    var docIndex = "";
    var docName = "";
    var docType = "";
    docIndexForDownloadAll = "";

    var loc = location.href.toString();
    console.log("loc: " + loc);
    console.log(loc.indexOf("customermyorders.do") !== -1);
    var myOrderDocList = "";

    var role = $("#userRole").val();
    console.log("role: " + role);

    if (loc.indexOf("customermyorders.do") !== -1 && role !== "Admin" && role !== "Company Admin")
    {
        $("#docType option").each(function() {
            myOrderDocList = myOrderDocList + "," + $(this).text();
        });
        console.log("myOrderDocList: " + myOrderDocList);
    }
    for (var i = 0; i < DocIndex.length; i++)
    {
        console.log("DocType[i]: " + DocType[i]);
        if (loc.indexOf("customermyorders.do") !== -1 && role !== "Admin" && role !== "Company Admin")
        {
            if (myOrderDocList !== "")
            {
                if (DocType[i] === "DO Summary" && myOrderDocList.indexOf("Delivery Order Summary") !== -1)
                {
                    row += "<tr><td>" + (i + 1) + "</td><td class='hideColumn'>" + DocIndex[i] + "</td><td>" + DocName[i] + "</td><td>" + DocType[i] + "</td><td>" + DocExt[i] + "</td><td class='hideColumn'>" + DocImgIndex[i] + "</td><td>" + DocInsertDate[i] + "</td><td class='center'><a href='#' onclick=downloadSingleDoc(" + DocIndex[i] + ",'" + y + "'); title='View Document'><i class='fa fa-eye fa-2x'></i></a></td><td class='center'><a href='#' onclick=\"downloadSingleDoc(" + DocIndex[i] + ",'" + n + "');customerAuditReport(" + DocIndex[i] + ",'" + DocName[i] + "','" + DocExt[i] + "','" + y + "');\" title='Download Document'><i class='fa fa-download fa-2x'></i></a></td></tr>";
                }
                else if (DocType[i] !== "" && myOrderDocList.indexOf(DocType[i]) !== -1)
                {
                    row += "<tr><td>" + (i + 1) + "</td><td class='hideColumn'>" + DocIndex[i] + "</td><td>" + DocName[i] + "</td><td>" + DocType[i] + "</td><td>" + DocExt[i] + "</td><td class='hideColumn'>" + DocImgIndex[i] + "</td><td>" + DocInsertDate[i] + "</td><td class='center'><a href='#' onclick=downloadSingleDoc(" + DocIndex[i] + ",'" + y + "'); title='View Document'><i class='fa fa-eye fa-2x'></i></a></td><td class='center'><a href='#' onclick=\"downloadSingleDoc(" + DocIndex[i] + ",'" + n + "');customerAuditReport(" + DocIndex[i] + ",'" + DocName[i] + "','" + DocExt[i] + "','" + y + "');\" title='Download Document'><i class='fa fa-download fa-2x'></i></a></td></tr>";
                }
            }
        }
        else
        {
            row += "<tr><td>" + (i + 1) + "</td><td class='hideColumn'>" + DocIndex[i] + "</td><td>" + DocName[i] + "</td><td>" + DocType[i] + "</td><td>" + DocExt[i] + "</td><td class='hideColumn'>" + DocImgIndex[i] + "</td><td>" + DocInsertDate[i] + "</td><td class='center'><a href='#' onclick=downloadSingleDoc(" + DocIndex[i] + ",'" + y + "'); title='View Document'><i class='fa fa-eye fa-2x'></i></a></td><td class='center'><a href='#' onclick=\"downloadSingleDoc(" + DocIndex[i] + ",'" + n + "');customerAuditReport(" + DocIndex[i] + ",'" + DocName[i] + "','" + DocExt[i] + "','" + y + "');\" title='Download Document'><i class='fa fa-download fa-2x'></i></a></td></tr>";
        }
        var temp = DocIndex[i] + "," + DocName[i] + "," + DocExt[i];
        multipleDownloadDataArray = multipleDownloadDataArray + temp + "<>";


//        row += "<tr><td>" + (i + 1) + "</td><td class='hideColumn'>" + DocIndex[i] + "</td><td><a href='#' onclick=downloadSingleDoc(" + DocIndex[i] + ");customerAuditReport(" + DocIndex[i] + ",'" + DocName[i] + "','" + DocType[i] + "');>" + DocName[i] + "</a></td><td>" + DocType[i] + "</td><td>" + DocExt[i] + "</td><td class='hideColumn'>" + DocImgIndex[i] + "</td><td>" + DocInsertDate[i] + "</td></tr>";

        xmlInputDownload += "%3CDocumentDetails%3E";
        xmlInputDownload += "%3CDocumentIndex%3E" + DocIndex[i] + "%3C/DocumentIndex%3E";
        xmlInputDownload += "%3C/DocumentDetails%3E";


        if (i === 0)
        {
            docIndexForDownloadAll = docIndexForDownloadAll + DocIndex[i];
        }
        else
        {
            docIndexForDownloadAll = docIndexForDownloadAll + "," + DocIndex[i];
        }
    }
    console.log("multipleDownloadDataArray: " + multipleDownloadDataArray);
    console.log("xmlInputDownload: " + xmlInputDownload);
    console.log("docIndexForDownloadAll: " + docIndexForDownloadAll);

    xmlInputForDownloadAll = "%3CInputCriteria%3E";
    xmlInputForDownloadAll += xmlInputDownload;
    xmlInputForDownloadAll += "%3C/InputCriteria%3E";
    console.log("xmlInputForDownloadAll: " + xmlInputForDownloadAll);

    $("#myOrderDocumentTableDivId").css("display", "block");
    $("#Search_document_table").children("tbody").html(row);

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
var allDocIndexes = [];
var dataByIndexes = {};
var selectedDataEntry = {};
var documentDateByIndexes = {};
var allSelectedDocIndex = [];
function myProjectTreeStructure(xmlre)
{
    allDocIndexes = [];
    dataByIndexes = {};
    documentDateByIndexes = {};
//    xmlre = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
//            + "<OutputCriteria>"
//            + "<CustomerId>1101215</CustomerId>"
//            + "<DOSummary>"
//            + "<DocumentIndex>1001</DocumentIndex>"
//            + "<DocumentName>NatSteel DO10.11.2018-10.11.2018-()</DocumentName>"
//            + "<DocumentType>DO Summary</DocumentType>"
//            + "<Extension>XLS</Extension>"
//            + "<ImageIndex>7848</ImageIndex>"
//            + "<InsertDate>10-11-2018</InsertDate>"
//            + "</DOSummary>"
//            + "<CreditNote>"
//            + "<DocumentIndex>1012</DocumentIndex>"
//            + "<DocumentName>CR-1070011438</DocumentName>"
//            + "<DocumentType>Credit Note</DocumentType>"
//            + "<Extension>PDF</Extension>"
//            + "<ImageIndex>7961</ImageIndex>"
//            + "<InsertDate>16-11-2018</InsertDate>"
//            + "</CreditNote>"
//            + "<DebitNote>"
//            + "<DocumentIndex>1002</DocumentIndex>"
//            + "<DocumentName>1070011438</DocumentName>"
//            + "<DocumentType>Debit Note</DocumentType>"
//            + "<Extension>PDF</Extension>"
//            + "<ImageIndex>7961</ImageIndex>"
//            + "<InsertDate>16-11-2018</InsertDate>"
//            + "</DebitNote>"
//            + "<DebitNote>"
//            + "<DocumentIndex>1003</DocumentIndex>"
//            + "<DocumentName>1070011438</DocumentName>"
//            + "<DocumentType>Debit Note</DocumentType>"
//            + "<Extension>PDF</Extension>"
//            + "<ImageIndex>7778</ImageIndex>"
//            + "<InsertDate>16-11-2018</InsertDate>"
//            + "</DebitNote>"
//            + "<Invoice>"
//            + "<DocumentIndex>1004</DocumentIndex>"
//            + "<DocumentName>Invoice 1050483764</DocumentName>"
//            + "<DocumentType>Invoice</DocumentType>"
//            + "<Extension>PDF</Extension>"
//            + "<ImageIndex>8577</ImageIndex>"
//            + "<InsertDate>10-11-2018</InsertDate>"
//            + "<SignedDO>"
//            + "<DocumentIndex>1005</DocumentIndex>"
//            + "<DocumentName>Signed DO Summary 1041203154</DocumentName>"
//            + "<DocumentType>Signed DO</DocumentType>"
//            + "<Extension>PDF</Extension>"
//            + "<ImageIndex>8578</ImageIndex>"
//            + "<InsertDate>10-11-2018</InsertDate>"
//            + "</SignedDO>"
//            + "<SignedDO>"
//            + "<DocumentIndex>1006</DocumentIndex>"
//            + "<DocumentName>Signed DO Summary 1041202887</DocumentName>"
//            + "<DocumentType>Signed DO</DocumentType>"
//            + "<Extension>PDF</Extension>"
//            + "<ImageIndex>8584</ImageIndex>"
//            + "<InsertDate>10-11-2018</InsertDate>"
//            + "</SignedDO>"
//            + "</Invoice>"
//            + "<Invoice>"
//            + "<DocumentIndex>1007</DocumentIndex>"
//            + "<DocumentName>Invoice 1050483763</DocumentName>"
//            + "<DocumentType>Invoice</DocumentType>"
//            + "<Extension>PDF</Extension>"
//            + "<ImageIndex>8583</ImageIndex>"
//            + "<InsertDate>10-11-2018</InsertDate>"
//            + "<SignedDO>"
//            + "<DocumentIndex>1008</DocumentIndex>"
//            + "<DocumentName>Signed DO Summary 1041203154</DocumentName>"
//            + "<DocumentType>Signed DO</DocumentType>"
//            + "<Extension>PDF</Extension>"
//            + "<ImageIndex>8578</ImageIndex>"
//            + "<InsertDate>10-11-2018</InsertDate>"
//            + "</SignedDO>"
//            + "<SignedDO>"
//            + "<DocumentIndex>1009</DocumentIndex>"
//            + "<DocumentName>Signed DO Summary 1041202887</DocumentName>"
//            + "<DocumentType>Signed DO</DocumentType>"
//            + "<Extension>PDF</Extension>"
//            + "<ImageIndex>8584</ImageIndex>"
//            + "<InsertDate>10-11-2018</InsertDate>"
//            + "</SignedDO>"
//            + "</Invoice>"
//            + "<maincode>0</maincode>"
//            + "<ProjectId>107636</ProjectId>"
//            + "<Type>S</Type>"
//            + "<UnSignedDO>"
//            + "<DocumentIndex>1010</DocumentIndex>"
//            + "<DocumentName>DO Detail 1041194365</DocumentName>"
//            + "<DocumentType>UnSigned DO</DocumentType>"
//            + "<Extension>PDF</Extension>"
//            + "<ImageIndex>8593</ImageIndex>"
//            + "<MillCertificate>"
//            + "<DocumentIndex>1021</DocumentIndex>"
//            + "<DocumentName>Mill Detail 1041194365</DocumentName>"
//            + "<DocumentType>Mill Certificate</DocumentType>"
//            + "<Extension>PDF</Extension>"
//            + "<ImageIndex>8593</ImageIndex>"
//            + "</MillCertificate>"
//            + "</UnSignedDO>"
//            + "<DWG>"
//            + "<DocumentIndex>1011</DocumentIndex>"
//            + "<DocumentName>DWG 1041194365</DocumentName>"
//            + "<DocumentType>DWG</DocumentType>"
//            + "<Extension>PDF</Extension>"
//            + "<ImageIndex>9000</ImageIndex>"
//            + "</DWG>"
//            + "</OutputCriteria>";

    console.log("findDocumentByProjectIdAndCustomerId:" + xmlre);
//    var xmlString = XMLToString(xmlre); //Convert the XML Object to a String
//    var xmlDoc = $.parseXML(xmlString); //Parse the XML String to get data


    var xmlDoc = $.parseXML(xmlre); //Temp

    var $xml = $(xmlDoc);

    var DOSummaryDocIndex = [];
    var DOSummaryDocName = [];
    var DOSummaryDocType = [];
    var DOSummaryDocExt = [];
    var DOSummaryInsertDate = [];

    var DebitNoteDocIndex = [];
    var DebitNoteDocName = [];
    var DebitNoteDocType = [];
    var DebitNoteDocExt = [];
    var DebitNoteInsertDate = [];

    var CreditNoteDocIndex = [];
    var CreditNoteDocName = [];
    var CreditNoteDocType = [];
    var CreditNoteDocExt = [];
    var CreditNoteInsertDate = [];

    var DWGDocIndex = [];
    var DWGDocName = [];
    var DWGDocType = [];
    var DWGDocExt = [];
    var DWGInsertDate = [];

    var InvoiceDocIndex = [];
    var InvoiceDocName = [];
    var InvoiceDocType = [];
    var InvoiceInsertDate = [];

    var InvoiceOnlyDocIndex = [];
    var InvoiceOnlyDocName = [];
    var InvoiceOnlyDocType = [];

    var SingedDODocIndex = [];
    var SingedDODocName = [];
    var SingedDODocType = [];

    var UnsingedDODocIndex = [];
    var UnsingedDODocName = [];
    var UnsingedDODocType = [];
    var UnsingedDOInsertDate = [];

    var UnsingedDOOnlyDocIndex = [];
    var UnsingedDOOnlyDocName = [];
    var UnsingedDOOnlyDocType = [];


    var MillCertificateDocIndex = [];
    var MillCertificateDocName = [];
    var MillCertificateDocType = [];
    var MillCertificateInsertDate = [];

    var MillCertificateOnlyDocIndex = [];
    var MillCertificateOnlyDocName = [];
    var MillCertificateOnlyDocType = [];


    var $DOSummary = $xml.find('DOSummary');
    var $DebitNote = $xml.find('DebitNote');
    var $CreditNote = $xml.find('CreditNote');
    var $DWG = $xml.find('DWG');

    var $Invoice = $xml.find('Invoice');
    var $SingedDO = $xml.find('SignedDO');

    var $UnsignedDO = $xml.find('UnSignedDO');
    var $MillCertificate = $xml.find('MillCertificate');

    //Invoice and Singed DO Begins

    $Invoice.find('DocumentName').each(function() {
        InvoiceDocName.push($(this).text());
//        alert($(this).text());
    });
    $Invoice.find('DocumentIndex').each(function() {
        InvoiceDocIndex.push($(this).text());
    });
    $Invoice.find('DocumentType').each(function() {
        InvoiceDocType.push($(this).text());
    });
    $Invoice.find('InsertDate').each(function() {
        InvoiceInsertDate.push($(this).text());
    });
//    console.log("InvoiceDocType: " + InvoiceDocType);

    $SingedDO.find('DocumentName').each(function() {
        SingedDODocName.push($(this).text());
    });
    $SingedDO.find('DocumentIndex').each(function() {
        SingedDODocIndex.push($(this).text());
    });
    $SingedDO.find('DocumentType').each(function() {
        SingedDODocType.push($(this).text());
    });
    console.log("SingedDODocIndex: " + SingedDODocIndex);

    InvoiceOnlyDocIndex = InvoiceDocIndex.filter(function(e) {
        return SingedDODocIndex.indexOf(e) < 0;
    });
    console.log("InvoiceOnlyDocIndex: " + InvoiceOnlyDocIndex);

    InvoiceOnlyDocName = InvoiceDocName.filter(function(e) {
        return SingedDODocName.indexOf(e) < 0;
    });
//    console.log("InvoiceOnlyDocName: " + InvoiceOnlyDocName);

    InvoiceOnlyDocType = InvoiceDocType.filter(function(e) {
        return SingedDODocType.indexOf(e) < 0;
    });
//    console.log("InvoiceOnlyDocType: " + InvoiceOnlyDocType);

    //Invoice and Singed DO Ends

    //Unsinged DO and Mill Certificate begins

    $UnsignedDO.find('DocumentName').each(function() {
        UnsingedDODocName.push($(this).text());
//        alert($(this).text());
    });
    $UnsignedDO.find('DocumentIndex').each(function() {
        UnsingedDODocIndex.push($(this).text());
    });
    $UnsignedDO.find('DocumentType').each(function() {
        UnsingedDODocType.push($(this).text());
    });
    $UnsignedDO.find('InsertDate').each(function() {
        UnsingedDOInsertDate.push($(this).text());
    });
//    console.log("UnsingedDODocIndex: " + UnsingedDODocIndex);

    $MillCertificate.find('DocumentName').each(function() {
        MillCertificateDocName.push($(this).text());
//        alert($(this).text());
    });
    $MillCertificate.find('DocumentIndex').each(function() {
        MillCertificateDocIndex.push($(this).text());
    });

    console.log("MillCertificateDocIndex 11: " + MillCertificateDocIndex);

    $MillCertificate.find('DocumentType').each(function() {
        MillCertificateDocType.push($(this).text());
    });
    console.log("MillCertificateDocIndex: " + MillCertificateDocIndex);


    $MillCertificate.find('InsertDate').each(function() {
        MillCertificateInsertDate.push($(this).text());
    });

    UnsingedDOOnlyDocIndex = UnsingedDODocIndex.filter(function(e) {
        return MillCertificateDocIndex.indexOf(e) < 0;
    });
    console.log("UnsingedDOOnlyDocIndex: " + UnsingedDOOnlyDocIndex);

    UnsingedDOOnlyDocName = UnsingedDODocName.filter(function(e) {
        return MillCertificateDocName.indexOf(e) < 0;
    });
//    console.log("UnsingedDOOnlyDocName: " + UnsingedDOOnlyDocName);

    UnsingedDOOnlyDocType = UnsingedDODocType.filter(function(e) {
        return MillCertificateDocType.indexOf(e) < 0;
    });



    UnsingedDOOnlyDocIndex = UnsingedDOOnlyDocIndex.filter(function(e) {
        return InvoiceDocIndex.indexOf(e) < 0;
    });
    console.log("UnsingedDOOnlyDocIndex: " + UnsingedDOOnlyDocIndex);

    UnsingedDOOnlyDocName = UnsingedDOOnlyDocName.filter(function(e) {
        return InvoiceDocName.indexOf(e) < 0;
    });
//    console.log("UnsingedDOOnlyDocName: " + UnsingedDOOnlyDocName);

    UnsingedDOOnlyDocType = UnsingedDOOnlyDocType.filter(function(e) {
        return InvoiceDocType.indexOf(e) < 0;
    });



    MillCertificateOnlyDocIndex = MillCertificateDocIndex.filter(function(e) {
        return InvoiceDocIndex.indexOf(e) < 0;
    });
    console.log("MillCertificateOnlyDocIndex 22: " + MillCertificateOnlyDocIndex);

    MillCertificateOnlyDocName = MillCertificateDocName.filter(function(e) {
        return InvoiceDocName.indexOf(e) < 0;
    });
//    console.log("UnsingedDOOnlyDocName: " + UnsingedDOOnlyDocName);

    MillCertificateOnlyDocType = MillCertificateDocType.filter(function(e) {
        return InvoiceDocType.indexOf(e) < 0;
    });
//    console.log("UnsingedDOOnlyDocType: " + UnsingedDOOnlyDocType);
    //Unsinged DO and Mill Certificate ends

    $DOSummary.find('DocumentName').each(function() {
        DOSummaryDocName.push($(this).text());
    });
    $DOSummary.find('DocumentIndex').each(function() {
        DOSummaryDocIndex.push($(this).text());
    });
    $DOSummary.find('DocumentType').each(function() {
        DOSummaryDocType.push($(this).text());
    });
    $DOSummary.find('Extension').each(function() {
        DOSummaryDocExt.push($(this).text());
    });
    $DOSummary.find('InsertDate').each(function() {
        DOSummaryInsertDate.push($(this).text());
    });
    console.log("DOSummaryDocIndex: " + DOSummaryDocIndex);

    $DebitNote.find('DocumentName').each(function() {
        DebitNoteDocName.push($(this).text());
    });
    $DebitNote.find('DocumentIndex').each(function() {
        DebitNoteDocIndex.push($(this).text());
    });
    $DebitNote.find('DocumentType').each(function() {
        DebitNoteDocType.push($(this).text());
    });
    $DebitNote.find('Extension').each(function() {
        DebitNoteDocExt.push($(this).text());
    });
    $DebitNote.find('InsertDate').each(function() {
        DebitNoteInsertDate.push($(this).text());
    });
    console.log("DebitNoteDocIndex: " + DebitNoteDocIndex);

    $CreditNote.find('DocumentName').each(function() {
        CreditNoteDocName.push($(this).text());
    });
    $CreditNote.find('DocumentIndex').each(function() {
        CreditNoteDocIndex.push($(this).text());
    });
    $CreditNote.find('DocumentType').each(function() {
        CreditNoteDocType.push($(this).text());
    });
    $CreditNote.find('Extension').each(function() {
        CreditNoteDocExt.push($(this).text());
    });
    $CreditNote.find('InsertDate').each(function() {
        CreditNoteInsertDate.push($(this).text());
    });
    console.log("CreditNoteDocIndex: " + CreditNoteDocIndex);

    $DWG.find('DocumentName').each(function() {
        DWGDocName.push($(this).text());
    });
    $DWG.find('DocumentIndex').each(function() {
        DWGDocIndex.push($(this).text());
    });
    $DWG.find('DocumentType').each(function() {
        DWGDocType.push($(this).text());
    });
    $DWG.find('Extension').each(function() {
        DWGDocExt.push($(this).text());
    });
    $DWG.find('InsertDate').each(function() {
        DWGInsertDate.push($(this).text());
    });
    console.log("DWGDocIndex: " + DWGDocIndex);
//    alert("DOSummaryDocName: " + DOSummaryDocName);
//    alert("DebitNoteDocName: " + DebitNoteDocName);
    var n = "N";
    var y = "Y";

    var loc = location.href.toString();
    console.log("loc: " + loc);
    console.log(loc.indexOf("customermyorders.do") !== -1);

    var myOrderDocList = "";

    var role = $("#userRole").val();
    console.log("role: " + role);

    if (loc.indexOf("customermyorders.do") !== -1 && role !== "Admin" && role !== "Company Admin")
    {
        $("#docType option").each(function() {
            myOrderDocList = myOrderDocList + "," + $(this).text();
        });
        console.log("myOrderDocList: " + myOrderDocList);
    }
    var isInvoice = false;
    var isUnsingedDo = false;
    var isDOSummary = false;
    var isCreditNote = false;
    var isDebitNote = false;
    var isDWG = false;
    var isUser = false;

    if (role !== "Admin" && role !== "Company Admin")
    {
        isUser = true;

        if (myOrderDocList.indexOf("Invoice") !== -1)
        {
            isInvoice = true;
        }
        if (myOrderDocList.indexOf("Unsigned DO") !== -1)
        {
            isUnsingedDo = true;
        }
        if (myOrderDocList.indexOf("Delivery Order Summary") !== -1)
        {
            isDOSummary = true;
        }
        if (myOrderDocList.indexOf("Credit Note") !== -1)
        {
            isCreditNote = true;
        }
        if (myOrderDocList.indexOf("Debit Note") !== -1)
        {
            isDebitNote = true;
        }

        if (myOrderDocList.indexOf("Engineering Document (DWG)") !== -1)
        {
            isDWG = true;
        }
    }
//    docIndexForDownloadAll = docIndexForDownloadAll + "," + DocIndex[i];

    console.log("UnsingedDODocIndex: 11 " + UnsingedDODocIndex);
    console.log("UnsingedDOOnlyDocIndex: 22 " + UnsingedDOOnlyDocIndex);
    console.log("UnsingedDOInsertDate: 33 " + UnsingedDOInsertDate);

    var tempUnsignedInsertDate = {};

    for (i = 0; i < UnsingedDODocIndex.length; i++)
    {
        tempUnsignedInsertDate[UnsingedDODocIndex[i]] = UnsingedDOInsertDate[i];

    }

    for (i = 0; i < UnsingedDOOnlyDocIndex.length; i++)
    {
        documentDateByIndexes[UnsingedDOOnlyDocIndex[i]] = tempUnsignedInsertDate[UnsingedDOOnlyDocIndex[i]];
    }

    var tempMillInsertDate = {};

    for (i = 0; i < MillCertificateDocIndex.length; i++)
    {
        tempMillInsertDate[MillCertificateDocIndex[i]] = MillCertificateInsertDate[i];

    }

    for (i = 0; i < MillCertificateOnlyDocIndex.length; i++)
    {
        documentDateByIndexes[MillCertificateOnlyDocIndex[i]] = tempMillInsertDate[MillCertificateOnlyDocIndex[i]];
    }
    //Tree Structure Begins

    //JsTree begins
    var dataArrayCollection = [];

    var jsTreeStructure = "<li>" + $("#projectId option:selected").text() + "<ul>";

    jsTreeStructure += "<li>Invoice<ul>";

    var invoiceDiv = "<div style='padding-top:10px'>"
            + "<li><span class='my-caret'><span class='treeHeader font-style-second'>Invoice</span></span>"
            + "<ul class='nested'>";

    var projectObj = {id: "pojectid", parent: "#", text: $("#projectId option:selected").text(), state: {"opened": true}};

    dataArrayCollection.push(projectObj);

    var invoiceObj = {id: "invoice", parent: "pojectid", text: "Invoice"};

    if (isUser === false || (isInvoice === true && isUser === true))
    {
        if (InvoiceOnlyDocName.length > 0)
        {
            dataArrayCollection.push(invoiceObj);
        }
    }

    var tempInvoiceIndex = "";

    var tempSignedDOIndex = "";
    var isSignedDOAvail = false;

    var tempMillCertIndex = "";
    var isMillCertAvail = false;

    var tempUnsingedDOIndex = "";
    var isUnsingedDOAvail = false;

    for (i = 0; i < InvoiceDocName.length; i++) {
        invoiceDiv += "<li class='font-style-third'><input type='checkbox' value='" + InvoiceDocIndex[i] + "' class='download-selected-doc' style='width: 13px;'>&nbsp" + InvoiceDocName[i] + "&nbsp&nbsp<a href='#' onclick=downloadSingleDoc(" + InvoiceDocIndex[i] + ",'" + y + "'); title='View Document'><i class='fa fa-eye fa-1x'></i></a>&nbsp&nbsp<a href='#' onclick=\"downloadSingleDoc(" + InvoiceDocIndex[i] + ",'" + n + "');customerAuditReport(" + InvoiceDocIndex[i] + ",'" + InvoiceDocName[i] + "','" + InvoiceDocType[i] + "','" + y + "');\" title='Download Document'><i class='fa fa-download fa-x'></i></a></li>";
        var temp = InvoiceDocIndex[i] + "," + (InvoiceDocName[i] + " PDF") + "," + InvoiceDocType[i];
        multipleDownloadDataArray = multipleDownloadDataArray + temp + "<>";
        dataByIndexes[InvoiceDocIndex[i]] = temp;

        documentDateByIndexes[InvoiceDocIndex[i]] = InvoiceInsertDate[i];

        jsTreeStructure += "<li>" + InvoiceDocName[i] + "</li>";

        if (InvoiceDocType[i] === "Invoice")
        {
            tempInvoiceIndex = InvoiceDocIndex[i];
            isSignedDOAvail = false;
            isMillCertAvail = false;
            isUnsingedDOAvail = false;

            var invoiceDoc = {id: "INV_" + InvoiceDocIndex[i], parent: "invoice", text: InvoiceDocName[i]};

            dataArrayCollection.push(invoiceDoc);

            var tempObj = {};
            tempObj.id = InvoiceDocIndex[i];
            tempObj.parent = "INV_" + InvoiceDocIndex[i];
            tempObj.text = InvoiceDocName[i];
//        tempObj.a_attr = {"href": "Link"};

            if (isUser === false || (isInvoice === true && isUser === true))
            {
                if (InvoiceOnlyDocName.length > 0)
                {
                    dataArrayCollection.push(tempObj);
                }
            }
        }
        if (InvoiceDocType[i] === "Signed DO")
        {

            var invoiceDoc = {id: "SIGNEDDO_" + InvoiceDocIndex[i], parent: "INV_" + tempInvoiceIndex, text: "Delivery Order"};

            if (isSignedDOAvail === false)
            {
                tempSignedDOIndex = InvoiceDocIndex[i];
                dataArrayCollection.push(invoiceDoc);
                isSignedDOAvail = true;
            }
            var tempObj = {};
            tempObj.id = InvoiceDocIndex[i];
            tempObj.parent = "SIGNEDDO_" + tempSignedDOIndex;
            tempObj.text = InvoiceDocName[i];

            if (isUser === false || (isInvoice === true && isUser === true))
            {
                if (InvoiceDocName.length > 0)
                {
                    dataArrayCollection.push(tempObj);
                }
            }
        }
        if (InvoiceDocType[i] === "Mill Certificate")
        {
            var invoiceDoc = {id: "MILLCERT_" + InvoiceDocIndex[i], parent: "INV_" + tempInvoiceIndex, text: "Mill Certificate"};

            if (isMillCertAvail === false)
            {
                tempMillCertIndex = InvoiceDocIndex[i];
                dataArrayCollection.push(invoiceDoc);
                isMillCertAvail = true;
            }

            var tempObj = {};
            tempObj.id = InvoiceDocIndex[i];
            tempObj.parent = "MILLCERT_" + tempMillCertIndex;
            tempObj.text = InvoiceDocName[i];

            if (isUser === false || (isInvoice === true && isUser === true))
            {
                if (InvoiceDocName.length > 0)
                {
                    dataArrayCollection.push(tempObj);
                }
            }
        }
        if (InvoiceDocType[i] === "UnSigned DO")
        {
            var invoiceDoc = {id: "UNS_IGNEDDO_" + InvoiceDocIndex[i], parent: "INV_" + tempInvoiceIndex, text: "Unsigned DO"};

            if (isUnsingedDOAvail === false)
            {
                tempUnsingedDOIndex = InvoiceDocIndex[i];
                dataArrayCollection.push(invoiceDoc);
                isUnsingedDOAvail = true;
            }

            var tempObj = {};
            tempObj.id = InvoiceDocIndex[i];
            tempObj.parent = "UNS_IGNEDDO_" + tempUnsingedDOIndex;
            tempObj.text = InvoiceDocName[i];

            if (isUser === false || (isInvoice === true && isUser === true))
            {
                if (InvoiceDocName.length > 0)
                {
                    dataArrayCollection.push(tempObj);
                }
            }
        }
    }

    console.log("documentDateByIndexes: " + documentDateByIndexes);

    jsTreeStructure += "<li>Singed DO<ul>";



//    console.log("dataByIndexes: " + dataByIndexes);

    $("input[type='checkbox']").addClass("download-selected-doc");


    var singedDODiv = "<div style='padding-top:10px'>"
            + "<li><span class='my-caret'><span class='treeHeader font-style-second'>Signed DO</span></span>"
            + "<ul class='nested'>";

    var singedDOObj = {id: "singeddo", parent: "invoice", text: "Signed DO"};

    if (isUser === false || (isInvoice === true && isUser === true))
    {
        if (InvoiceOnlyDocName.length > 0)
        {
//            dataArrayCollection.push(singedDOObj);
        }
    }

    for (i = 0; i < SingedDODocName.length; i++) {
        singedDODiv += "<li class='font-style-third'><input type='checkbox' value='" + SingedDODocIndex[i] + "' class='download-selected-doc' style='width: 13px;'>&nbsp" + SingedDODocName[i] + "&nbsp&nbsp<a href='#' onclick=downloadSingleDoc(" + SingedDODocIndex[i] + ",'" + y + "'); title='View Document'><i class='fa fa-eye fa-1x'></i></a>&nbsp&nbsp<a href='#' onclick=\"downloadSingleDoc(" + SingedDODocIndex[i] + ",'" + n + "');customerAuditReport(" + SingedDODocIndex[i] + ",'" + SingedDODocName[i] + "','" + SingedDODocType[i] + "','" + y + "');\" title='Download Document'><i class='fa fa-download fa-x'></i></a></li>";
        var temp = SingedDODocIndex[i] + "," + (SingedDODocName[i] + " PDF") + "," + SingedDODocType[i];
        multipleDownloadDataArray = multipleDownloadDataArray + temp + "<>";
        dataByIndexes[SingedDODocIndex[i]] = temp;

        jsTreeStructure += "<li>" + SingedDODocName[i] + "</li>";


        var tempObj = {};
        tempObj.id = SingedDODocIndex[i];
        tempObj.parent = "singeddo";
        tempObj.text = SingedDODocName[i];

        if (isUser === false || (isInvoice === true && isUser === true))
        {
            if (InvoiceOnlyDocName.length > 0)
            {
//                dataArrayCollection.push(tempObj);
            }
        }
    }

    jsTreeStructure += "</ul></ul></li>";

    singedDODiv += "</ul>"
            + "</li>"
            + "</div>";
    console.log("invoiceDiv: " + invoiceDiv);

    invoiceDiv += singedDODiv;

    invoiceDiv += "</ul>"
            + "</li>"
            + "</div>";
    console.log("invoiceDiv: " + invoiceDiv);

    jsTreeStructure += "<li>Unsinged DO<ul>";

    var unsingedDODiv = "<div style='padding-top:10px'>"
            + "<li><span class='my-caret'><span class='treeHeader font-style-second'>Unsinged DO</span></span>"
            + "<ul class='nested'>";

    var unsingedDOObj = {id: "unsingeddo", parent: "pojectid", text: "Unsigned DO"};

    if (isUser === false || (isUnsingedDo === true && isUser === true))
    {
        if (UnsingedDOOnlyDocName.length > 0)
        {
            dataArrayCollection.push(unsingedDOObj);
        }
    }
    for (i = 0; i < UnsingedDOOnlyDocName.length; i++) {
        unsingedDODiv += "<li class='font-style-third'><input type='checkbox' value='" + UnsingedDOOnlyDocIndex[i] + "' class='download-selected-doc' style='width: 13px;'>&nbsp" + UnsingedDOOnlyDocName[i] + "&nbsp&nbsp<a href='#' onclick=downloadSingleDoc(" + UnsingedDOOnlyDocIndex[i] + ",'" + y + "'); title='View Document'><i class='fa fa-eye fa-1x'></i></a>&nbsp&nbsp<a href='#' onclick=\"downloadSingleDoc(" + UnsingedDOOnlyDocIndex[i] + ",'" + n + "');customerAuditReport(" + UnsingedDOOnlyDocIndex[i] + ",'" + UnsingedDOOnlyDocName[i] + "','" + UnsingedDOOnlyDocType[i] + "','" + y + "');\" title='Download Document'><i class='fa fa-download fa-x'></i></a></li></li>";
        var temp = UnsingedDOOnlyDocIndex[i] + "," + (UnsingedDOOnlyDocName[i] + " PDF") + "," + UnsingedDOOnlyDocType[i];
        multipleDownloadDataArray = multipleDownloadDataArray + temp + "<>";
        dataByIndexes[UnsingedDOOnlyDocIndex[i]] = temp;

        jsTreeStructure += "<li>" + UnsingedDOOnlyDocName[i] + "</li>";


        var tempObj = {};
        tempObj.id = UnsingedDOOnlyDocIndex[i];
        tempObj.parent = "unsingeddo";
        tempObj.text = UnsingedDOOnlyDocName[i];

        if (isUser === false || (isUnsingedDo === true && isUser === true))
        {
            if (UnsingedDOOnlyDocName.length > 0)
            {
                dataArrayCollection.push(tempObj);
            }
        }
    }

    jsTreeStructure += "<li>Mill Certificate<ul>";



    var millDiv = "<div style='padding-top:10px'>"
            + "<li><span class='my-caret'><span class='treeHeader font-style-second'>Mill Certificate</span></span>"
            + "<ul class='nested'>";

    var millObj = {id: "millcertificate", parent: "unsingeddo", text: "Mill Certificate"};

    if (isUser === false || (isUnsingedDo === true && isUser === true))
    {
        if (MillCertificateOnlyDocIndex.length > 0)
        {
            dataArrayCollection.push(millObj);
        }
    }

    for (i = 0; i < MillCertificateOnlyDocName.length; i++) {
        millDiv += "<li class='font-style-third'><input type='checkbox' value='" + MillCertificateOnlyDocIndex[i] + "' class='download-selected-doc' style='width: 13px;'>&nbsp" + MillCertificateOnlyDocName[i] + "&nbsp&nbsp<a href='#' onclick=downloadSingleDoc(" + MillCertificateOnlyDocIndex[i] + ",'" + y + "'); title='View Document'><i class='fa fa-eye fa-1x'></i></a>&nbsp&nbsp<a href='#' onclick=\"downloadSingleDoc(" + MillCertificateOnlyDocIndex[i] + ",'" + n + "');customerAuditReport(" + MillCertificateOnlyDocIndex[i] + ",'" + MillCertificateOnlyDocName[i] + "','" + MillCertificateOnlyDocType[i] + "','" + y + "');\" title='Download Document'><i class='fa fa-download fa-x'></i></a></li>";
        var temp = MillCertificateOnlyDocIndex[i] + "," + (MillCertificateOnlyDocName[i] + " PDF") + "," + MillCertificateOnlyDocType[i];
        multipleDownloadDataArray = multipleDownloadDataArray + temp + "<>";
        dataByIndexes[MillCertificateOnlyDocIndex[i]] = temp;

        jsTreeStructure += "<li>" + MillCertificateOnlyDocName[i] + "</li>";


        var tempObj = {};
        tempObj.id = MillCertificateOnlyDocIndex[i];
        tempObj.parent = "millcertificate";
        tempObj.text = MillCertificateOnlyDocName[i];

        if (isUser === false || (isUnsingedDo === true && isUser === true))
        {
            if (UnsingedDOOnlyDocName.length > 0)
            {
                dataArrayCollection.push(tempObj);
            }
        }
    }

    jsTreeStructure += "</ul></ul></li>";

    millDiv += "</ul>"
            + "</li>"
            + "</div>";
    console.log("millDiv: " + millDiv);

    unsingedDODiv += millDiv;

    unsingedDODiv += "</ul>"
            + "</li>"
            + "</div>";
    console.log("unsingedDODiv: " + unsingedDODiv);

    jsTreeStructure += "<li>DO Summary<ul>";

    var doSummaryDiv = "<div style='padding-top:10px'>"
            + "<li><span class='my-caret'><span class='treeHeader font-style-second'>DO Summary</span></span>"
            + "<ul class='nested'>";

    var dosummaryObj = {id: "dosummary", parent: "pojectid", text: "DO Summary"};

    if (isUser === false || (isDOSummary === true && isUser === true))
    {
        if (DOSummaryDocName.length > 0)
        {
            dataArrayCollection.push(dosummaryObj);
        }
    }

    for (i = 0; i < DOSummaryDocName.length; i++) {
        doSummaryDiv += "<li class='font-style-third'><input type='checkbox' value='" + DOSummaryDocIndex[i] + "' class='download-selected-doc' style='width: 13px;'>&nbsp" + DOSummaryDocName[i] + "&nbsp&nbsp<a href='#' onclick=downloadSingleDoc(" + DOSummaryDocIndex[i] + ",'" + y + "'); title='View Document'><i class='fa fa-eye fa-1x'></i></a>&nbsp&nbsp<a href='#' onclick=\"downloadSingleDoc(" + DOSummaryDocIndex[i] + ",'" + n + "');customerAuditReport(" + DOSummaryDocIndex[i] + ",'" + DOSummaryDocName[i] + "','" + DOSummaryDocType[i] + "','" + y + "');\" title='Download Document'><i class='fa fa-download fa-x'></i></a></li>";
        var temp = DOSummaryDocIndex[i] + "," + (DOSummaryDocName[i] + " " + DOSummaryDocExt[i]) + "," + DOSummaryDocType[i];
        multipleDownloadDataArray = multipleDownloadDataArray + temp + "<>";
        dataByIndexes[DOSummaryDocIndex[i]] = temp;

        documentDateByIndexes[DOSummaryDocIndex[i]] = DOSummaryInsertDate[i];

        jsTreeStructure += "<li>" + DOSummaryDocName[i] + "</li>";


        var tempObj = {};
        tempObj.id = DOSummaryDocIndex[i];
        tempObj.parent = "dosummary";
        tempObj.text = DOSummaryDocName[i];

        if (isUser === false || (isDOSummary === true && isUser === true))
        {
            dataArrayCollection.push(tempObj);
        }
    }

    jsTreeStructure += "</ul></li>";

    doSummaryDiv += "</ul>"
            + "</li>"
            + "</div>";
    console.log("doSummaryDiv: " + doSummaryDiv);

    jsTreeStructure += "<li>Debit Note<ul>";

    var debitNoteDiv = "<div style='padding-top:10px'>"
            + "<li><span class='my-caret'><span class='treeHeader font-style-second'>Debit Note</span></span>"
            + "<ul class='nested'>";

    var debitnoteObj = {id: "debitnote", parent: "pojectid", text: "Debit Note"};

    if (isUser === false || (isDebitNote === true && isUser === true))
    {
        if (DebitNoteDocName.length > 0)
        {
            dataArrayCollection.push(debitnoteObj);
        }
    }

    for (i = 0; i < DebitNoteDocName.length; i++) {
        debitNoteDiv += "<li class='font-style-third'><input type='checkbox' value='" + DebitNoteDocIndex[i] + "' class='download-selected-doc' style='width: 13px;'>&nbsp" + DebitNoteDocName[i] + "&nbsp&nbsp<a href='#' onclick=downloadSingleDoc(" + DebitNoteDocIndex[i] + ",'" + y + "'); title='View Document'><i class='fa fa-eye fa-1x'></i></a>&nbsp&nbsp<a href='#' onclick=\"downloadSingleDoc(" + DebitNoteDocIndex[i] + ",'" + n + "');customerAuditReport(" + DebitNoteDocIndex[i] + ",'" + DebitNoteDocName[i] + "','" + DebitNoteDocType[i] + "','" + y + "');\" title='Download Document'><i class='fa fa-download fa-x'></i></a></li>";
        var temp = DebitNoteDocIndex[i] + "," + (DebitNoteDocName[i] + " " + DebitNoteDocExt[i]) + "," + DebitNoteDocType[i];
        multipleDownloadDataArray = multipleDownloadDataArray + temp + "<>";
        dataByIndexes[DebitNoteDocIndex[i]] = temp;

        documentDateByIndexes[DebitNoteDocIndex[i]] = DebitNoteInsertDate[i];

        jsTreeStructure += "<li>" + DebitNoteDocName[i] + "</li>";


        var tempObj = {};
        tempObj.id = DebitNoteDocIndex[i];
        tempObj.parent = "debitnote";
        tempObj.text = DebitNoteDocName[i];

        if (isUser === false || (isDebitNote === true && isUser === true))
        {
            dataArrayCollection.push(tempObj);
        }
    }

    jsTreeStructure += "</ul></li>";

    debitNoteDiv += "</ul>"
            + "</li>"
            + "</div>";
    console.log("debitNoteDiv: " + debitNoteDiv);

    jsTreeStructure += "<li>Credit Note<ul>";

    var creditNoteDiv = "<div style='padding-top:10px'>"
            + "<li><span class='my-caret'><span class='treeHeader font-style-second'>Credit Note</span></span>"
            + "<ul class='nested'>";

    var creditnoteObj = {id: "creditnote", parent: "pojectid", text: "Credit Note"};

    if (isUser === false || (isCreditNote === true && isUser === true))
    {
        if (CreditNoteDocName.length > 0)
        {
            dataArrayCollection.push(creditnoteObj);
        }
    }

    for (i = 0; i < CreditNoteDocName.length; i++) {
        creditNoteDiv += "<li class='font-style-third'><input type='checkbox' value='" + CreditNoteDocIndex[i] + "' class='download-selected-doc' style='width: 13px;'>&nbsp" + CreditNoteDocName[i] + "&nbsp&nbsp<a href='#' onclick=downloadSingleDoc(" + CreditNoteDocIndex[i] + ",'" + y + "'); title='View Document'><i class='fa fa-eye fa-1x'></i></a>&nbsp&nbsp<a href='#' onclick=\"downloadSingleDoc(" + CreditNoteDocIndex[i] + ",'" + n + "');customerAuditReport(" + CreditNoteDocIndex[i] + ",'" + CreditNoteDocName[i] + "','" + CreditNoteDocType[i] + "','" + y + "');\" title='Download Document'><i class='fa fa-download fa-x'></i></a></li>";
        var temp = CreditNoteDocIndex[i] + "," + (CreditNoteDocName[i] + " " + CreditNoteDocExt[i]) + "," + CreditNoteDocType[i];
        multipleDownloadDataArray = multipleDownloadDataArray + temp + "<>";
        dataByIndexes[CreditNoteDocIndex[i]] = temp;

        documentDateByIndexes[CreditNoteDocIndex[i]] = CreditNoteInsertDate[i];

        jsTreeStructure += "<li>" + CreditNoteDocName[i] + "</li>";


        var tempObj = {};
        tempObj.id = CreditNoteDocIndex[i];
        tempObj.parent = "creditnote";
        tempObj.text = CreditNoteDocName[i];

        if (isUser === false || (isCreditNote === true && isUser === true))
        {
            dataArrayCollection.push(tempObj);
        }
    }

    jsTreeStructure += "</ul></li>";

    creditNoteDiv += "</ul>"
            + "</li>"
            + "</div>";
    console.log("creditNoteDiv: " + creditNoteDiv);

    jsTreeStructure += "<li>Engineering Document (DWG)<ul>";

    var dwgDiv = "<div style='padding-top:10px'>"
            + "<li><span class='my-caret'><span class='treeHeader font-style-second'>Engineering Document (DWG)</span></span>"
            + "<ul class='nested'>";

    var dwgObj = {id: "dwg", parent: "pojectid", text: "Engineering Document (DWG)"};

    if (isUser === false || (isDWG === true && isUser === true))
    {
        if (DWGDocIndex.length > 0)
        {
            dataArrayCollection.push(dwgObj);
        }
    }

    for (i = 0; i < DWGDocName.length; i++) {
        dwgDiv += "<li class='font-style-third'><input type='checkbox' value='" + DWGDocIndex[i] + "' class='download-selected-doc' style='width: 13px;'>&nbsp" + DWGDocName[i] + "&nbsp&nbsp<a href='#' onclick=downloadSingleDoc(" + DWGDocIndex[i] + ",'" + y + "'); title='View Document'><i class='fa fa-eye fa-1x'></i></a>&nbsp&nbsp<a href='#' onclick=\"downloadSingleDoc(" + DWGDocIndex[i] + ",'" + n + "');customerAuditReport(" + DWGDocIndex[i] + ",'" + DWGDocName[i] + "','" + DWGDocType[i] + "','" + y + "');\" title='Download Document'><i class='fa fa-download fa-x'></i></a></li>";
        var temp = DWGDocIndex[i] + "," + (DWGDocName[i] + " " + DWGDocExt[i]) + "," + DWGDocType[i];
        multipleDownloadDataArray = multipleDownloadDataArray + temp + "<>";
        dataByIndexes[DWGDocIndex[i]] = temp;

        documentDateByIndexes[DWGDocIndex[i]] = DWGInsertDate[i];

        jsTreeStructure += "<li>" + DWGDocName[i] + "</li>";


        var tempObj = {};
        tempObj.id = DWGDocIndex[i];
        tempObj.parent = "dwg";
        tempObj.text = DWGDocName[i];

        if (isUser === false || (isDWG === true && isUser === true))
        {
            dataArrayCollection.push(tempObj);
        }
    }

    jsTreeStructure += "</ul></li>";

    tempMultipleDownloadDataArray = multipleDownloadDataArray;

    dwgDiv += "</ul>"
            + "</li>"
            + "</div>";
    console.log("dwgDiv: " + dwgDiv);



    var treeStructure = "";

    if (role !== "Admin" && role !== "Company Admin")
    {
        treeStructure = "<li>"
                + "<span class='my-caret'>"
                + "<span class='treeHeader font-style-first'>" + $("#projectId option:selected").text() + "</span>"
                + "</span>"
                + "<ul class='nested'>";

        if (myOrderDocList.indexOf("Invoice") !== -1)
        {


            treeStructure += invoiceDiv;
            allDocIndexes = allDocIndexes.concat(InvoiceDocIndex);
        }
        console.log("Invoice index: " + allDocIndexes);
        if (myOrderDocList.indexOf("Unsigned DO") !== -1)
        {
            treeStructure += unsingedDODiv;
            allDocIndexes = allDocIndexes.concat(UnsingedDOOnlyDocIndex, MillCertificateDocIndex);
        }
        console.log("Unsigned DO: " + allDocIndexes);
        if (myOrderDocList.indexOf("Delivery Order Summary") !== -1)
        {
            treeStructure += doSummaryDiv;
            allDocIndexes = allDocIndexes.concat(DOSummaryDocIndex);
        }
        console.log("DO Summary: " + allDocIndexes);
        if (myOrderDocList.indexOf("Credit Note") !== -1)
        {
            treeStructure += creditNoteDiv;
            allDocIndexes = allDocIndexes.concat(CreditNoteDocIndex);
        }
        console.log("Credit Note: " + allDocIndexes);
        if (myOrderDocList.indexOf("Debit Note") !== -1)
        {
            treeStructure += debitNoteDiv;
            allDocIndexes = allDocIndexes.concat(DebitNoteDocIndex);
        }
        console.log("Debit Note: " + allDocIndexes);

        if (myOrderDocList.indexOf("Engineering Document (DWG)") !== -1)
        {
            treeStructure += dwgDiv;
            allDocIndexes = allDocIndexes.concat(DWGDocIndex);
        }
        console.log("Debit Note: " + allDocIndexes);

        treeStructure += "</ul>"
                + "</li>";
    }
    else
    {


        treeStructure = "<li>"
                + "<span class='my-caret'>"
                + "<span class='treeHeader font-style-first'>" + $("#projectId option:selected").text() + "</span>"
                + "</span>"
                + "<ul class='nested'>"
                + invoiceDiv

                + unsingedDODiv

                + doSummaryDiv

                + creditNoteDiv

                + debitNoteDiv

                + dwgDiv

                + "</ul>"
                + "</li>";
        allDocIndexes = allDocIndexes.concat(InvoiceDocIndex, SingedDODocIndex, UnsingedDOOnlyDocIndex, MillCertificateDocIndex, DOSummaryDocIndex, DebitNoteDocIndex, CreditNoteDocIndex, DWGDocIndex);
    }

    docIndexForDownloadAll = allDocIndexes;

    console.log("treeStructure: " + treeStructure);
    console.log("multipleDownloadDataArray: " + multipleDownloadDataArray);
    console.log("allDocIndexes: " + allDocIndexes);
    console.log("docIndexForDownloadAll: " + docIndexForDownloadAll);

    if (allDocIndexes.length > 0)
    {
        $("#advsearchdownloadall").removeClass("hidden");
        $("#search_jstree_input").removeClass("hidden");
    }
    else
    {
        $("#advsearchdownloadall").addClass("hidden");
        $("#search_jstree_input").addClass("hidden");
    }
//
//    if (allDocIndexes.length > 0)
//    {
//        $("#docUL").html("");
//        $("#docUL").append(treeStructure);
//    }
//    else
//    {
//        $("#docUL").html("");
//        $("#docUL").append("<h4>No records found.</h4>");
//    }
    var toggle = document.getElementsByClassName("my-caret");
    var i;

    for (i = 0; i < toggle.length; i++) {
        toggle[i].addEventListener("click", function() {
            this.parentElement.querySelector(".nested").classList.toggle("active");
            this.classList.toggle("caret-down");
        });
    }


    jsTreeStructure += "</ul></li>";

    console.log("jsTreeStructure: " + jsTreeStructure);

    if (allDocIndexes.length === 0)
    {
        $("#message").html("<h4>No records found.</h4>");

        $("#downloadselected").addClass("hidden");
        $("#viewSelectedDocument").addClass("hidden");
        $("#mailSelectedDocument").addClass("hidden");

        dataArrayCollection = [];

        $('#container').jstree("destroy");

        $('#container').jstree({
            "plugins": ["checkbox"],
            "core": {
                "data": dataArrayCollection
            }
        });

//        $("#container").jstree("refresh");
//        $('#container').on("changed.jstree");
    }
    else
    {
        $("#downloadselected").addClass("hidden");
        $("#viewSelectedDocument").addClass("hidden");
        $("#mailSelectedDocument").addClass("hidden");

        $("#message").html("");

//        $('#container').jstree();
//
//        $('#container').data('jstree', false).empty();

//        $('#container').jstree(true).refresh_node('1');

        $('#container').jstree("destroy");



        $('#container').jstree({
            "plugins": ["checkbox", "search"],
            "core": {
                "data": dataArrayCollection
            }
        });

//        $('#container').on("changed.jstree");
//        $('#container').jstree(true).refresh_node('1');

//        $("#container").jstree("refresh");
//        $("#container").jstree(true).refresh();

        $('#container').on("changed.jstree", function(e, data) {
            console.log(data.selected);
            allSelectedDocIndex = data.selected;
            console.log("allSelectedDocIndex: " + allSelectedDocIndex);

            if (allSelectedDocIndex.length === 0)
            {
                $("#downloadselected").addClass("hidden");

                if (role === "Admin" || role === "Company Admin")
                {
                    $("#mailSelectedDocument").addClass("hidden");
                }
            }
            else
            {
                $("#downloadselected").removeClass("hidden");

                if (role === "Admin" || role === "Company Admin")
                {
                    $("#mailSelectedDocument").removeClass("hidden");
                }
            }


            var isSelectedAll = false;


            var array = new Array();
            for (var temp in allSelectedDocIndex)
            {
                if (allSelectedDocIndex[temp] === "pojectid")
                {
                    isSelectedAll = true;
                }
                if (allSelectedDocIndex[temp] !== "pojectid" && allSelectedDocIndex[temp] !== "invoice" && allSelectedDocIndex[temp] !== "singeddo"
                        && allSelectedDocIndex[temp] !== "unsingeddo" && allSelectedDocIndex[temp] !== "millcertificate" && allSelectedDocIndex[temp] !== "dosummary"
                        && allSelectedDocIndex[temp] !== "debitnote" && allSelectedDocIndex[temp] !== "creditnote" && allSelectedDocIndex[temp] !== "dwg"
                        && allSelectedDocIndex[temp].indexOf("INV_") === -1 && allSelectedDocIndex[temp].indexOf("SIGNEDDO_") === -1
                        && allSelectedDocIndex[temp].indexOf("MILLCERT_") === -1 && allSelectedDocIndex[temp].indexOf("UNS_IGNEDDO_") === -1)
                {
                    array.push(allSelectedDocIndex[temp]);
                }
            }


            if (isSelectedAll === true)
            {
                $("#downloadselected").prop("disabled", true);
            }
            else
            {
                $("#downloadselected").prop("disabled", false);
            }

            if (array.length !== 1)
            {
                $("#viewSelectedDocument").addClass("hidden");
            }
            else
            {
                $("#viewSelectedDocument").removeClass("hidden");
            }
        });
    }
    console.log("dataArrayCollection: " + dataArrayCollection);

    console.log("allDocIndexes.length: " + allDocIndexes.length);
//
//    for (var temp in dataArrayCollection)
//    {
//        console.log(dataArrayCollection[temp]);
//    }

//    console.log("tempIndex: " + tempIndex);
    //Tree Structure Ends

//    var DocIndex = [];
//    var DocName = [];
//    var DocType = [];
//    var DocExt = [];
//    var DocImgIndex = [];
//    var DocInsertDate = [];
//
//    var $DocumentIndex = $DOSummary.find('DocumentIndex').each(function() {
//        //        alert($(this).text());
//        DocIndex.push($(this).text());
//    });
//    var $DocumentName = $DOSummary.find('DocumentName').each(function() {
//        //        alert($(this).text());
//        DocName.push($(this).text());
//    });
//    var $DocumentType = $DOSummary.find('DocumentType').each(function() {
//        //        alert($(this).text());
//        DocType.push($(this).text());
//    });
//    var $Extension = $DOSummary.find('Extension').each(function() {
//        //        alert($(this).text());
//        DocExt.push($(this).text());
//    });
//    var $ImageIndex = $DOSummary.find('ImageIndex').each(function() {
//        //        alert($(this).text());
//        DocImgIndex.push($(this).text());
//    });
//    var $InsertDate = $DOSummary.find('InsertDate').each(function() {
//        //        alert($(this).text());
//        DocInsertDate.push($(this).text());
//    });
//    var row = "";
//
//    if (DocIndex.length > 0)
//    {
//        $("#advsearchdownloadall").css("display", "block");
//    }
//    else
//    {
//        $("#advsearchdownloadall").css("display", "none");
//    }
//
//    $("#Search_document_table").children("tbody").html("");
//
//    var xmlInputDownload = "";
//    console.log("DocIndex Length:" + DocIndex.length + " DocIndex[0]:" + DocIndex[0]);
//
//    var docIndex = "";
//    var docName = "";
//    var docType = "";
//    docIndexForDownloadAll = "";
//

//    for (var i = 0; i < DocIndex.length; i++)
//    {
//        console.log("DocType[i]: " + DocType[i]);
//        if (loc.indexOf("customermyorders.do") !== -1 && role !== "Admin" && role !== "Company Admin")
//        {
//            if (myOrderDocList !== "")
//            {
//                if (DocType[i] === "DO Summary" && myOrderDocList.indexOf("Delivery Order Summary") !== -1)
//                {
//                    row += "<tr><td>" + (i + 1) + "</td><td class='hideColumn'>" + DocIndex[i] + "</td><td>" + DocName[i] + "</td><td>" + DocType[i] + "</td><td>" + DocExt[i] + "</td><td class='hideColumn'>" + DocImgIndex[i] + "</td><td>" + DocInsertDate[i] + "</td><td class='center'><a href='#' onclick=downloadSingleDoc(" + DocIndex[i] + ",'" + y + "'); title='View Document'><i class='fa fa-eye fa-2x'></i></a></td><td class='center'><a href='#' onclick=\"downloadSingleDoc(" + DocIndex[i] + ",'" + n + "');customerAuditReport(" + DocIndex[i] + ",'" + DocName[i] + "','" + DocExt[i] + "','" + y + "');\" title='Download Document'><i class='fa fa-download fa-2x'></i></a></td></tr>";
//                }
//                else if (DocType[i] !== "" && myOrderDocList.indexOf(DocType[i]) !== -1)
//                {
//                    row += "<tr><td>" + (i + 1) + "</td><td class='hideColumn'>" + DocIndex[i] + "</td><td>" + DocName[i] + "</td><td>" + DocType[i] + "</td><td>" + DocExt[i] + "</td><td class='hideColumn'>" + DocImgIndex[i] + "</td><td>" + DocInsertDate[i] + "</td><td class='center'><a href='#' onclick=downloadSingleDoc(" + DocIndex[i] + ",'" + y + "'); title='View Document'><i class='fa fa-eye fa-2x'></i></a></td><td class='center'><a href='#' onclick=\"downloadSingleDoc(" + DocIndex[i] + ",'" + n + "');customerAuditReport(" + DocIndex[i] + ",'" + DocName[i] + "','" + DocExt[i] + "','" + y + "');\" title='Download Document'><i class='fa fa-download fa-2x'></i></a></td></tr>";
//                }
//            }
//        }
//        else
//        {
//            row += "<tr><td>" + (i + 1) + "</td><td class='hideColumn'>" + DocIndex[i] + "</td><td>" + DocName[i] + "</td><td>" + DocType[i] + "</td><td>" + DocExt[i] + "</td><td class='hideColumn'>" + DocImgIndex[i] + "</td><td>" + DocInsertDate[i] + "</td><td class='center'><a href='#' onclick=downloadSingleDoc(" + DocIndex[i] + ",'" + y + "'); title='View Document'><i class='fa fa-eye fa-2x'></i></a></td><td class='center'><a href='#' onclick=\"downloadSingleDoc(" + DocIndex[i] + ",'" + n + "');customerAuditReport(" + DocIndex[i] + ",'" + DocName[i] + "','" + DocExt[i] + "','" + y + "');\" title='Download Document'><i class='fa fa-download fa-2x'></i></a></td></tr>";
//        }
//        var temp = DocIndex[i] + "," + DocName[i] + "," + DocExt[i];
//        multipleDownloadDataArray = multipleDownloadDataArray + temp + "<>";
//
//
////        row += "<tr><td>" + (i + 1) + "</td><td class='hideColumn'>" + DocIndex[i] + "</td><td><a href='#' onclick=downloadSingleDoc(" + DocIndex[i] + ");customerAuditReport(" + DocIndex[i] + ",'" + DocName[i] + "','" + DocType[i] + "');>" + DocName[i] + "</a></td><td>" + DocType[i] + "</td><td>" + DocExt[i] + "</td><td class='hideColumn'>" + DocImgIndex[i] + "</td><td>" + DocInsertDate[i] + "</td></tr>";
//
//        xmlInputDownload += "%3CDocumentDetails%3E";
//        xmlInputDownload += "%3CDocumentIndex%3E" + DocIndex[i] + "%3C/DocumentIndex%3E";
//        xmlInputDownload += "%3C/DocumentDetails%3E";
//
//
//        if (i === 0)
//        {
//            docIndexForDownloadAll = docIndexForDownloadAll + DocIndex[i];
//        }
//        else
//        {
//            docIndexForDownloadAll = docIndexForDownloadAll + "," + DocIndex[i];
//        }
//    }
//    console.log("multipleDownloadDataArray: " + multipleDownloadDataArray);
//    console.log("xmlInputDownload: " + xmlInputDownload);
//    console.log("docIndexForDownloadAll: " + docIndexForDownloadAll);
//
//    xmlInputForDownloadAll = "%3CInputCriteria%3E";
//    xmlInputForDownloadAll += xmlInputDownload;
//    xmlInputForDownloadAll += "%3C/InputCriteria%3E";
//    console.log("xmlInputForDownloadAll: " + xmlInputForDownloadAll);

//
//    $("#myOrderDocumentTableDivId").css("display", "block");
//    $("#Search_document_table").children("tbody").html(row);
//
//    if ($.fn.DataTable.isDataTable('#Search_document_table')) {
//        table.fnDestroy();
//        $("#Search_document_table").children('tbody').html(row);
//        table = $('#Search_document_table').dataTable();
//        table.fnReloadAjax();
//        table = $('#Search_document_table').dataTable();
//    }
//    else
//    {
//        table = $('#Search_document_table').dataTable();
//    }
}
function viewSingleDoc(docIndex)
{
    //alert("docIndex: " + docIndex);

    var xmlInput = "%3CInputCriteria%3E";
    xmlInput += "%3CDocumentDetails%3E";
    xmlInput += "%3CDocumentIndex%3E" + docIndex + "%3C/DocumentIndex%3E";
    xmlInput += "%3C/DocumentDetails%3E";
    xmlInput += "%3C/InputCriteria%3E";

    var dmsip = $("#dmsip").val();
    console.log(dmsip);

//    var URLParam = 'InputXML=' + xmlInput + '&RequestType=download';
    var URLParam = "";
    var isView = "Y";
    console.log("isView: " + isView);

    if (isView === "Y")
    {
        URLParam = 'InputXML=' + docIndex + '&RequestType=download&ViewOption=Y';
    }
    else if (isView === "N")
    {
        URLParam = 'InputXML=' + docIndex + '&RequestType=download&ViewOption=N';
    }
    console.log("URLParam: " + URLParam);

    window.open(dmsip + "/WebServiceCall/Download?" + URLParam, "_blank");


//    var serviceUrl = dmsip + "/WebServiceCall/CallWebService";
//    console.log("serviceUrl: " + serviceUrl);

//    CustomerPortalapp/ng/search/download

//    $.ajax({
//        type: "POST",
//        url: serviceUrl,
//        contentType: "application/x-www-form-urlencoded; charset=utf-8",
//        dataType: "xml",
//        data: URLParam,
//        success: function(data, textStatus, jqXHR) {
//            //            alert("success: " + data);
//            console.log("response: " + data);
//            getDownloadUrl(data);
//
//        }
//    });
}
function downloadSingleDoc(docIndex, isView)
{
    //alert("docIndex: " + docIndex);

    var xmlInput = "%3CInputCriteria%3E";
    xmlInput += "%3CDocumentDetails%3E";
    xmlInput += "%3CDocumentIndex%3E" + docIndex + "%3C/DocumentIndex%3E";
    xmlInput += "%3C/DocumentDetails%3E";
    xmlInput += "%3C/InputCriteria%3E";

    var dmsip = $("#dmsip").val();
    console.log(dmsip);

//    var URLParam = 'InputXML=' + xmlInput + '&RequestType=download';
    var URLParam = "";
    console.log("isView: " + isView);

    if (isView === "Y")
    {
        URLParam = 'InputXML=' + docIndex + '&RequestType=download&ViewOption=Y';
    }
    else if (isView === "N")
    {
        URLParam = 'InputXML=' + docIndex + '&RequestType=download&ViewOption=N';
    }
    console.log("URLParam: " + URLParam);

    window.open(dmsip + "/WebServiceCall/Download?" + URLParam, "_blank");


//    var serviceUrl = dmsip + "/WebServiceCall/CallWebService";
//    console.log("serviceUrl: " + serviceUrl);

//    CustomerPortalapp/ng/search/download

//    $.ajax({
//        type: "POST",
//        url: serviceUrl,
//        contentType: "application/x-www-form-urlencoded; charset=utf-8",
//        dataType: "xml",
//        data: URLParam,
//        success: function(data, textStatus, jqXHR) {
//            //            alert("success: " + data);
//            console.log("response: " + data);
//            getDownloadUrl(data);
//
//        }
//    });
}
function saveMyOrderMultipleDoc()
{
    var customerId = $("#sessionCustomerId").val();
    console.log("customerId in audit report for multiple: " + customerId);

    console.log("docIndexForDownloadAll: " + docIndexForDownloadAll);

    var tempArray = [];
    tempArray = docIndexForDownloadAll.filter(function(e) {
        return allSelectedDocIndex.indexOf(e) < 0;
    });
    console.log("tempArray: " + tempArray);

    for (var temp in tempArray)
    {
        delete dataByIndexes[tempArray[temp]];
    }

    var tempData = "";
    for (var temp in dataByIndexes)
    {
//        console.log("temp: " + selectedDataEntry[temp]);
        tempData += dataByIndexes[temp] + "<>";
    }
    console.log("tempData: " + tempData);



    $.ajax(
            {
                type: "GET",
                url: "rfeCont.do",
                async: true,
                data:
                        {
                            "reqFrom": "CustomerAuditReportForMultiple",
                            "documentsData": tempData,
                            "CustomerId": customerId
                        },
                dataType: "json",
                complete: function(responseJson)
                {
                    var obj = $.parseJSON(responseJson.responseText);
//                        alert(obj.status);
//                        console.log(obj.ReportId);
                }
            });
}
function saveMultipleDoc()
{
    var customerId = $("#sessionCustomerId").val();
    console.log("customerId in audit report for multiple: " + customerId);

    console.log("advsearchdownloadall: " + multipleDownloadDataArray);


    $.ajax(
            {
                type: "GET",
                url: "rfeCont.do",
                async: true,
                data:
                        {
                            "reqFrom": "CustomerAuditReportForMultiple",
                            "documentsData": multipleDownloadDataArray,
                            "CustomerId": customerId
                        },
                dataType: "json",
                complete: function(responseJson)
                {
                    var obj = $.parseJSON(responseJson.responseText);
//                        alert(obj.status);
//                        console.log(obj.ReportId);
                }
            });
}
function downloadSelectedMultipleDoc()
{
    console.log("allSelectedDocIndex: " + allSelectedDocIndex);
    var array = new Array();
    for (var temp in allSelectedDocIndex)
    {
        if (allSelectedDocIndex[temp] !== "pojectid" && allSelectedDocIndex[temp] !== "invoice" && allSelectedDocIndex[temp] !== "singeddo"
                && allSelectedDocIndex[temp] !== "unsingeddo" && allSelectedDocIndex[temp] !== "millcertificate" && allSelectedDocIndex[temp] !== "dosummary"
                && allSelectedDocIndex[temp] !== "debitnote" && allSelectedDocIndex[temp] !== "creditnote" && allSelectedDocIndex[temp] !== "dwg"
                && allSelectedDocIndex[temp].indexOf("INV_") === -1 && allSelectedDocIndex[temp].indexOf("SIGNEDDO_") === -1
                && allSelectedDocIndex[temp].indexOf("MILLCERT_") === -1 && allSelectedDocIndex[temp].indexOf("UNS_IGNEDDO_") === -1)
        {
            array.push(allSelectedDocIndex[temp]);
        }
    }

    console.log("array: " + array);
    allSelectedDocIndex = [];
    allSelectedDocIndex = array;
    console.log("allSelectedDocIndex: " + allSelectedDocIndex);

    console.log("docIndex's: " + allSelectedDocIndex);

    var dmsip = $("#dmsip").val();
    console.log(dmsip);

    var URLParam = 'InputXML=' + allSelectedDocIndex + '&RequestType=download&ViewOption=N';
    console.log("URLParam: " + URLParam);

    window.open(dmsip + "/WebServiceCall/Download?" + URLParam, "_blank");

}
function downloadMultipleDoc()
{
    console.log("docIndex's: " + docIndexForDownloadAll);

    var dmsip = $("#dmsip").val();
    console.log(dmsip);

    var URLParam = 'InputXML=' + docIndexForDownloadAll + '&RequestType=download&ViewOption=N';
    console.log("URLParam: " + URLParam);

    window.open(dmsip + "/WebServiceCall/Download?" + URLParam, "_blank");

//    var serviceUrl = dmsip + "/WebServiceCall/CallWebService";
//    console.log("serviceUrl: " + serviceUrl);

//    CustomerPortalapp/ng/search/download

//    $.ajax({
//        type: "POST",
//        url: serviceUrl,
//        contentType: "application/x-www-form-urlencoded; charset=utf-8",
//        dataType: "xml",
//        data: URLParam,
//        success: function(data, textStatus, jqXHR) {
//            //            alert("success: " + data);
//            console.log("response: " + data);
//            getDownloadUrl(data);
//
//        }
//    });
}

function getDownloadUrl(xmlre)
{
    var xmlString = XMLToString(xmlre); //Convert the XML Object to a String
    var xmlDoc = $.parseXML(xmlString); //Parse the XML String to get data
    var $xml = $(xmlDoc);
    var $DownloadUrl = $xml.find('DownloadURL');
    console.log("url: " + $DownloadUrl.text().replace("&amp", ""));
    window.open($DownloadUrl.text().replace("&amp", ""));
}
function getProjectList()
{
    if ($("#userRole").val() === "Admin")
    {
        $("#sessionCustomerId").val("");
    }
    getProjectByJqAjax();
}
function getProjectByJqAjax()
{
    //    alert("getting...");


    var xmlInput = "<InputCriteria>";
    xmlInput += "<CustomerId>" + $("#sessionCustomerId").val() + "</CustomerId>";
    xmlInput += "</InputCriteria>";

    var dmsip = $("#dmsip").val();
    console.log(dmsip);

    var URLParam = 'InputXML=' + xmlInput + '&RequestType=project';
    console.log("URLParam: " + URLParam);

    var serviceUrl = dmsip + "/WebServiceCall/CallWebService";
    console.log("serviceUrl: " + serviceUrl);

//    CustomerPortalapp/ng/search/project

    $.ajax({
        type: "POST",
        url: serviceUrl,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        dataType: "xml",
        data: URLParam,
        success: function(data, textStatus, jqXHR) {
            //            alert("success: " + data);
            getAllProjectIds(data);

        }
    });
}

function getAllProjectIds(xmlre) {
    //    alert("in myFunction: " + xmlre);
    var xmlString = XMLToString(xmlre); //Convert the XML Object to a String
    var xmlDoc = $.parseXML(xmlString); //Parse the XML String to get data
    var $xml = $(xmlDoc);
    var $ProjectDetails = $xml.find('ProjectDetails');

    var values = [];
    var names = [];
    var $ProjectId = $ProjectDetails.find('ProjectId').each(function() {
        //        alert($(this).text());
        values.push($(this).text());
    });
    var $ProjectName = $ProjectDetails.find('ProjectName').each(function() {
        //        alert($(this).text());
        names.push($(this).text());
    });
    for (var k = 0; k < values.length; k++) {
        $("<option>").val(values[k]).text(names[k]).appendTo("#projectId");
    }
}
function XMLToString(oXML)
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
function getYears()
{
    var date = new Date();
    var currentYear = date.getFullYear();
    console.log("currentYear: " + currentYear);
    var temp = currentYear;
//    for (var i = 5; i >= 1; i--)
//    {
//        $("<option>").val(temp - i).text(temp - i).appendTo("#transyear");
//
//
//    }
    $("<option>").val(currentYear).text(currentYear).appendTo("#transyear");

    temp = currentYear;

    for (var i = 1; i <= 32; i++)
    {
        $("<option>").val(temp + i).text(temp + i).appendTo("#transyear");
    }
}


function toSetXML() {
    console.log("Inside SetXML");

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

    var projectId = $("#projectId").val();
    var docTypeText = $("#docType1 option:selected").text();

    if (projectId === "--Select--" || projectId === "")
    {
        bootbox.dialog("Please select project!", [{
                "label": "ok",
                "class": "btn-small btn-primary"
            }]);
        $("#projectId").css("border-color", "red");
        return false;
    }
    else
    {
        $("#projectId").css("border-color", "");
    }

    var InputXML = "<InputCriteria>";
    InputXML += "<CustomerId>" + customerid + "</CustomerId>";
    InputXML += "<ProjectId>" + $("#projectId").val() + "</ProjectId>";


    if ($("#adv_from_date").val() !== "")
    {
        if ($("#adv_to_date").val() === "")
        {
            bootbox.dialog("Please enter to date!", [{
                    "label": "ok",
                    "class": "btn-small btn-primary"
                }]);
            $("#adv_to_date").css("border-color", "red");
            return false;
        }
        else
        {
            $("#adv_to_date").css("border-color", "");
        }
    }

    if ($("#adv_from_date").val() !== "" && $("#adv_to_date").val() !== "")
    {
        InputXML += "<FromDate>" + $("#adv_from_date").val() + "</FromDate>";
        InputXML += "<ToDate>" + $("#adv_to_date").val() + "</ToDate>";
    }

    var doctype = '';
    var docNo = '';
    var docTypeText = '';
    var fromDate = $("#adv_from_date").val();
    var toDate = $("#adv_to_date").val();

    console.log("before size: " + document.getElementsByTagName('select').length);
    console.log("size in jquery: " + $("select").length);
    console.log("noOfSelect: " + $("#noOfSelect").val());

//    var size = document.getElementsByTagName('select').length - 1;
    var size = $("#noOfSelect").val() - 1;

    console.log("No of Dropdown Selected: " + size);

    var role = $("#userRole").val();
    if (role === "Admin") {
        size = size - 1;
    }

    for (var i = 1; i <= size; i++) {
        console.log("i:: " + i)

        doctype = $("#docType" + i).val();
        docNo = $("#docNo" + i).val();
        docTypeText = $("#docType" + i + " option:selected").text();
        console.log("docTypeText: " + docTypeText);

        if (docTypeText === "--Select--")
        {
            bootbox.dialog("Please Select Document Category!", [{
                    "label": "OK",
                    "class": "btn-small btn-primary"
                }]);
            $("#docType" + i).css("border-color", "red");
            return false;
        }
        else
        {
            $("#docType" + i).css("border-color", "");
        }

        if ((docTypeText !== 'Mill Certificate' && docTypeText !== "Delivery Order Summary") && docNo === "")
        {
            bootbox.dialog("Please Enter Document Reference No.", [{
                    "label": "OK",
                    "class": "btn-small btn-primary"
                }]);
            $("#docNo" + i).css("border-color", "red");
            return false;
        }
        else
        {
            $("#docNo" + i).css("border-color", "");
        }

        if (docTypeText === "Delivery Order Summary" || docTypeText === "Mill Certificate")
        {
            if (fromDate === "")
            {
                bootbox.dialog("Please Enter From Date!", [{
                        "label": "ok",
                        "class": "btn-small btn-primary"
                    }]);
                $("#adv_from_date").css("border-color", "red");
                return false;
            }
            else
            {
                $("#adv_from_date").css("border-color", "");
            }

            if (toDate === "")
            {
                bootbox.dialog("Please Enter To Date!", [{
                        "label": "ok",
                        "class": "btn-small btn-primary"
                    }]);
                $("#adv_to_date").css("border-color", "red");
                return false;
            }
            else
            {
                $("#adv_to_date").css("border-color", "");
            }
        }
        if (docTypeText === "Mill Certificate" || docTypeText === "Delivery Order Summary")
        {
            $("#docNo" + i).prop("disabled", true);
            $("#docNo" + i).val("");
        }
        else
        {
            $("#docNo" + i).prop("disabled", false);
        }

        var documentCategory = "";

        if (docTypeText === "Invoice" || docTypeText === "Debit Note" || docTypeText === "Credit Note" || docTypeText === "Delivery Order Summary")
        {
            documentCategory = doctype;
        }
        else
        {
            documentCategory = docTypeText;
        }
        console.log("documentCategory: " + documentCategory);

        InputXML += "<DocumentDetails>";
        InputXML += "<DocumentType>" + documentCategory + "</DocumentType>";
        InputXML += "<ReferenceNo>" + docNo + "</ReferenceNo>";
        InputXML += "</DocumentDetails>";
    }
    InputXML += "</InputCriteria>";
    console.log("InputXML:" + InputXML);


//    $("#overlay").css("display", "block");
    console.log("start loading...");

    var dmsip = $("#dmsip").val();
    console.log(dmsip);

    var URLParam = 'InputXML=' + InputXML + '&RequestType=advanced';
    console.log("URLParam: " + URLParam);

    var serviceUrl = dmsip + "/WebServiceCall/CallWebService";
    console.log("serviceUrl: " + serviceUrl);

//    CustomerPortalapp/ng/search/advanced

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
}
//
//function customerAuditReport(docIndex, docName, docType, is)
//{
//    console.log(docIndex);
//    console.log(docName);
//    console.log(docType);
//
//    $.ajax(
//            {
//                type: "GET",
//                url: "rfeCont.do",
//                async: true,
//                data:
//                        {
//                            "reqFrom": "CustomerAuditReport",
//                            "DocIndex": docIndex,
//                            "DocName": docName,
//                            "DocType": docType
//                        },
//                dataType: "json",
//                complete: function(responseJson)
//                {
//                    var obj = $.parseJSON(responseJson.responseText);
////                        alert(obj.status);
//                    console.log(obj.ReportId);
//                }
//            });
//
//}


function validatePassword(passVal, passConfigArray)
{
    console.log(passVal);
    if (passConfigArray.length > 0)
    {
        var passLen = passConfigArray[0];
        var specialCharCount = passConfigArray[1];
        var uppCharCount = passConfigArray[2];
        var lowCharCount = passConfigArray[3];
        var numCount = passConfigArray[4];

//        console.log("passLen: " + passLen);
//        console.log("specialCharCount: " + specialCharCount);
//        console.log("uppCharCount: " + uppCharCount);
//        console.log("lowCharCount: " + lowCharCount);
//        console.log("numCount: " + numCount);

        var len = passVal.toString().length;
        console.log("len: " + len);

        var numUpper = (passVal.match(/[A-Z]/g) || []).length;
        console.log("numUpper: " + numUpper);

        var numLower = (passVal.match(/[a-z]/g) || []).length;
        console.log("numLower: " + numLower);

        var numericCount = (passVal.match(/[0-9]/g) || []).length;
        console.log("numericCount: " + numericCount);

        var specialCount = (passVal.match(/[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/g) || []).length;
        console.log("specialCount: " + specialCount);

        if (len < passLen)
        {
            return false;
        }
        if (numUpper < uppCharCount)
        {
            return false;
        }
        if (numLower < lowCharCount)
        {
            return false;
        }
        if (numericCount < numCount)
        {
            return false;
        }
        if (specialCount < specialCharCount)
        {
            return false;
        }

        return true;
    }

}

function customerAuditReport(docIndex, docName, docType, isView)
{
    console.log(docIndex);
    console.log(docName);
    console.log(docType);

    var customerId = $("#sessionCustomerId").val();
    console.log("customerId in audit report: " + customerId);

    $.ajax(
            {
                type: "GET",
                url: "rfeCont.do",
                async: true,
                data:
                        {
                            "reqFrom": "CustomerAuditReport",
                            "DocIndex": docIndex,
                            "DocName": docName,
                            "DocType": docType,
                            "CustomerId": customerId
                        },
                dataType: "json",
                complete: function(responseJson)
                {
//                    var obj = $.parseJSON(responseJson.responseText);
//                        alert(obj.status);
//                    console.log("ReportId audit report: " + obj.ReportId);
                }
            });

}