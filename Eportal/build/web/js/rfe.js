$(document).ready(function() {


    $("#rfp_summary_details_close_btn_id").click(function() {
        window.close();
    });
    $("#award_rfp_summary_details_close_btn_id").click(function() {
        window.close();
    });

    $("#search_btn_id").click(function() {
        var rfqno = $("#rfq_search_no").val();
        var rfqtitle = $("#rfq_search_title").val();
        var rfqstatus = $("#rfq_search_status").val();
        var supplierid = $("#rfq_search_supplier").val();
        var releasedrfq = $("#rfq_search_released").val();

//        alert(rfqno + ' ; ' + rfqtitle + ' ; ' + rfqstatus + ' ; ' + supplierid);

        $.ajax(
                {
                    type: "GET",
                    url: "rfeCont.do",
                    async: true,
                    data:
                            {
                                "reqFrom": "rfqsearchsummary",
                                "rfqid": rfqno,
                                "rfqtitle": rfqtitle,
                                "rfqstatus": rfqstatus,
                                "supplierid": supplierid,
                                "releasedrfq": releasedrfq
                            },
                    dataType: "json",
                    complete: function(responseJson)
                    {
                        var obj = $.parseJSON(responseJson.responseText);
//                        alert(obj.status);
                        var row = "";
                        if (obj.status === 'SUCCESS')
                        {
                            for (var i = 0; i < obj.data.length; i++)
                            {
                                row += "<tr><td>" + (i + 1) + "</td><td><a>RFQ_" + obj.data[i].RFQ_ID + "</a></td><td>" + obj.data[i].RFQ_STATUS + "</td><td>" + obj.data[i].RFQ_TITLE + "</td><td>" + obj.data[i].CREATION_DATE + "</td><td>" + obj.data[i].TIME_LEFT + "</td></tr>";
                            }
                        }
                        else
                        {
//                            row = "No Data Available";
                        }
                        $("#rfq_summary_table_div").css("display", "block");
                        $("#rfq_summary_table").children('tbody').html(row);

                        if ($.fn.DataTable.isDataTable('#rfq_summary_table')) {
                            table.fnDestroy();
                            $("#rfq_summary_table").children('tbody').html(row);
                            table = $('#rfq_summary_table').dataTable();
                            table.fnReloadAjax();
                            table = $('#rfq_summary_table').dataTable();
                        }
                        else
                        {
                            table = $('#rfq_summary_table').dataTable();
                        }
                    }
                });
    });

    $("#search_clear_btn_id").click(function() {
        $("#rfq_search_no").val("default");
        $("#rfq_search_no").selectpicker("refresh");

        $("#rfq_search_title").val("default");
        $("#rfq_search_title").selectpicker("refresh");

        $("#rfq_search_status").val("default");
        $("#rfq_search_status").selectpicker("refresh");

        $("#rfq_search_supplier").val("default");
        $("#rfq_search_supplier").selectpicker("refresh");
//        alert("dfsf");
        $("#rfq_search_released").val("default");
        $("#rfq_search_released").selectpicker("refresh");
    });

    $("#opendate").blur(function()
    {
        $("#closedate").attr("min", $('#opendate').val());
    });

    $("#closedate").blur(function()
    {

        var openDate = new Date($('#opendate').val());
        var closeDate = new Date($('#closedate').val());

        if (openDate == 'Invalid Date')
        {
            //alert("Please Select Open Date First!");
            bootbox.dialog("Please Select Open Date First!", [{"label": "ok", "class": "btn-small btn-primary"}]);
            $('#closedate').val('');
            return false;
        }
        else
        {
            var diff = new Date(closeDate.getTime() - openDate.getTime());

            var months = diff.getMonth();
            var days = diff.getDate();
            var mins = diff.getMinutes();
            var secs = diff.getSeconds();

            $('#timeleft').val(months + " months, " + days + " days, " + mins + " minutes");
        }
    });
    var rowId = 2;
    var category_details = "";
    $("#add_cat_sub").click(function()
    {
//        var values = [];
//        var options = $('#categoryId option');
//
//        values = $.map(options, function(option) {
//            return option.value;
//        });
//        console.log(values);
//        var cat_options="";
//        for(var i=0;i<values.length;i++)
//        {
//            cat_options =  cat_options + "<option>" + values[i] + "</option>";
//        }
//        console.log(cat_options);
        //alert("add");
//         alert($("#check_constraint").val());
        if ($("#check_constraint").val() === 'false')
        {
            bootbox.dialog("Please fill required fields!", [{"label": "ok", "class": "btn-small btn-primary"}]);
            return;
        }

        var category = $("#categoryId").val();
        var subcategory = $("#subcategoryId").val();
        var item = $("#itemname").val();
        var qty = $("#quantity").val();
        var price = $("#targetprice").val();
        //alert(category);
        if (category === "Select" || subcategory === "Select" || item === "" || qty === "")
        {

            var isValid = true;
            $(".mat_det_class").each(function() {
                if ($(this).val().trim() === "" || $(this).val() === "Select")
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

        }
//        alert($("#check_constraint").val());

        category_details = category_details + category + "~" + subcategory + "~" + item + "~" + price + "~" + qty + "<>";
        //alert($("#cat_subcat_table tr").length);
        var tableRow = "<tr id=row" + rowId + ">\n\
                        <td>" + category + "</td>\n\
                        <td>" + subcategory + "</td>\n\
                        <td>" + item + "</td>\n\
                        <td>" + price + "</td>\n\
                        <td>" + qty + "</td>\n\
        <td><button type='button' id='remove_cat_sub' class='btn btn-small btn-danger'><i class='icon-remove'></i></button></td></tr>";

        $("#cat_subcat_table").children("tbody").append(tableRow);
        rowId++;
        //alert(category_details);
        $("#cat_sub_details").val(category_details);
        console.log($("#cat_sub_details").val());
        $("#categoryId").val("Select");
        $("#subcategoryId").val("Select");
        $("#itemname").val("");
        $("#targetprice").val("");
        $("#quantity").val("");

    });

    $("#cat_subcat_table").on('click', '#remove_cat_sub', function()
    {
        //alert("remove");
        //$(this).parent().parent().remove();
        $(this).closest('tr').remove();
    });


    $("#clear_cat_sub").click(function()
    {
        $("#categoryId").val("Select");
        $("#subcategoryId").val("Select");
        $("#itemname").val("");
        $("#targetprice").val("");
        $("#quantity").val("");
    });





    $("#categoryId").change(function()
    {
        var category = $("#categoryId").val();
        //alert(category);

        $.ajax(
                {
                    type: "GET",
                    url: "rfeCont.do",
                    async: true,
                    data:
                            {
                                "reqFrom": "category",
                                "category": category
                            },
                    dataType: "json",
                    complete: function(responseJson)
                    {
                        var arr = $.parseJSON(responseJson.responseText);
                        //alert("done: " + arr.length);
                        $("#subcategoryId").find("option").remove();
                        $("<option>").val("Select").text("Select").appendTo("#subcategoryId");
                        for (var i = 0; i < arr.length; i++)
                        {
                            //console.log(arr[i]);
                            $("<option>").val(arr[i]).text(arr[i]).appendTo("#subcategoryId");
                        }
                    }
                });
    });

    $("#approved_rfp_select").change(function()
    {
        var approved_rfp = $("#approved_rfp_select").val();
        //alert(approved_rfp);

        $.ajax(
                {
                    type: "GET",
                    url: "rfeCont.do",
                    async: true,
                    data:
                            {
                                "reqFrom": "releaseRFP",
                                "approvedRfp": approved_rfp
                            },
                    dataType: "json",
                    complete: function(responseJson)
                    {
                        var obj = $.parseJSON(responseJson.responseText);
                        //alert(obj.RfqTitle);
                        $("#rfqTitle").val(obj.RfqTitle);
                        $("#timeLeft").val(obj.TimeLeft);
                        $("#description").val(obj.Desc);
                    }
                });
    });

    $("#released_rfp_select").change(function()
    {
        var released_rfp = $("#released_rfp_select").val();
        //        alert(released_rfp);

        $.ajax(
                {
                    type: "GET",
                    url: "rfeCont.do",
                    async: true,
                    data:
                            {
                                "reqFrom": "supplierRfpStatus",
                                "releasedRfp": released_rfp
                            },
                    dataType: "json",
                    complete: function(responseJson)
                    {
                        var arr = $.parseJSON(responseJson.responseText);
//                        alert(arr.length);
//                        var isAllAwaitingAward = true;
//
//                        for (var i = 0; i < arr.length; i++)
//                        {
//                            var status = arr[i].rfpStatus;
//                            if (status !== "Bid Submitted")
//                            {
//                                isAllAwaitingAward = false;
//                                break;
//                            }
                        //                        }
                        var row = "";
                        for (var i = 0; i < arr.length; i++)
                        {
                            var status = arr[i].rfpStatus;
                            //                            alert(status);
                            if (status === "Bid Submitted")
                            {
                                row += "<tr><td><input type='checkbox' class='AwardCheckBox'><input type='hidden' name='supplierId' value='" + arr[i].supplierId + "'></td><td>" + (i + 1) + "</td><td><a href='showAwardRfp.do?rfpid=" + arr[i].rfpId + "&supplierid=" + arr[i].supplierId + "'>" + arr[i].supplieName + "</a></td><td>" + status + "</td><td>" + arr[i].updateDate + "</td><td>" + arr[i].rfpCreateDate + "</td><td>" + arr[i].timeLeft + "</td></tr>";
                            }
                            else
                            {
                                row += "<tr><td></td><td>" + (i + 1) + "</td><td>" + arr[i].supplieName + "</td><td>" + status + "</td><td>" + arr[i].updateDate + "</td><td>" + arr[i].rfpCreateDate + "</td><td>" + arr[i].timeLeft + "</td></tr>";
                            }
                        }
                        $("#releasedRfeStatusTableDivId").css("display", "block");
                        $("#releasedRfeStatusTableId").children('tbody').html(row);

                        //                        var table = "";
                        if ($.fn.DataTable.isDataTable('#releasedRfeStatusTableId')) {
                            //                            alert("if");

                            table.fnDestroy();
                            $("#releasedRfeStatusTableId").children('tbody').html(row);
                            table = $('#releasedRfeStatusTableId').dataTable();
                            //                            table.resetPaging();
                            table.fnReloadAjax();
                            table = $('#releasedRfeStatusTableId').dataTable({
//                                "processing": true,
//                                "sPaginationType": "full_numbers",
//                                "oLanguage": {
//                                    "sLengthMenu": "_MENU_ records per page"
                                //                                }
                            });
                        }
                        else
                        {
//                            table = $('#tblFeeReceiptSearch').dataTable();
                            //                            table.fnReloadAjax();
                            table = $('#releasedRfeStatusTableId').dataTable({
//                                "processing": true,
//                                "sPaginationType": "full_numbers",
//                                "oLanguage": {
//                                    "sLengthMenu": "_MENU_ records per page"
                                //                                }
                            });
                            //                            alert("else");
                        }
                    }
                });
    });
    var supplierId = "";

    $("#releasedRfeStatusTableId").on("click", ".AwardCheckBox", function()
    {
        //        alert($(this).prop("checked"));
        var tr = $(this).closest('tr');
        var suppid = tr.children('td').find('input[name="supplierId"]').val();

        if ($(this).prop("checked") === true)
        {
//            alert("false len: " + supplierId.length);
            //            alert(supplierId);
            $("#awardAllSupplier_btn").css("display", "block");

            var tr = $(this).closest('tr');
            var rfqid = $("#released_rfp_select").val();
            $("#rfqid").val(rfqid);

            supplierId = supplierId + suppid + "~";

            $("#suppliersId").val(supplierId);
        }
        else if ($(this).prop("checked") === false)
        {
//            alert("false len: " + supplierId.length);
            //            alert(supplierId);

            supplierId = supplierId.replace(suppid + "~", "");
            $("#suppliersId").val(supplierId);

            if (supplierId.length === 0)
            {
                $("#awardAllSupplier_btn").css("display", "none");
            }

        }
    });

    $("#awardRFPFrom").submit(function() {
//        
//        bootbox.confirm("Are you sure, you want to Award all Supplier(s).", function(result) {
//            return result;
        //        });
    });

    $(".form-field").blur(function() {
        if ($(this).val() !== '') {
            $(this).css({"border-color": ""});
            //alert("removed");
        }
    });
    $(".mat_det_class").blur(function() {
        if ($(this).val() !== '') {
            $(this).css({"border-color": ""});
            //alert("removed");
        }
    });

    $("#next_btn").click(function(event) {
        var isValid = true;
//        $(".form-field").each(function() {
//            if ($(this).val() === '') {
//                alert($(this).val());
//                $(this).css({"border-color": "red"});
//                isValid = false;
//                event.stopImmediatePropagation();
//                $("#next_btn").off("click");
//            
//            }
//            else
//            {
//                alert("go ahead.");
//            }
        //        });
        //return isValid;
//        $(".form-field").blur(function(){
//            if($(this).val() !== '') {
//                $(this).css({"border-color": ""});
//                //alert("removed");
//            }
        //        });

    });
    $("#sameasbilltoadd").click(function() {
        if ($(this).is(":checked"))
        {
            var billto = $("#billtoaddress").val();
            $("#shiptoaddress").val(billto);
            //$("#shiptoaddress").attr("readonly", "readonly");
        }
        else
        {
            $("#shiptoaddress").val("");
        }
    });
    //$(".clausesClass").each(function() {
//    $("#rfeClauses").click(function(){
//         alert($(this).next().text());
    //    });
//   
    var clause = "";
    $("#rfqClausesAvail").click(function() {
        //alert($(this).val());


        var selectedClsLen = $("select#rfqClausesSelected option").length;
        //alert(selectedClsLen);
        if (selectedClsLen === 0)
        {
            clause = clause + $(this).val() + "~";
            //console.log(clause);
            //alert(clause.indexOf($(this).val()));

            $("<option>").val($(this).val()).text($(this).val()).appendTo("#rfqClausesSelected");
        }
        else
        {

            //alert(clause.indexOf($(this).val()));
            if (clause.indexOf($(this).val()) !== -1)
            {
                bootbox.dialog("Already Selected!", [{"label": "ok", "class": "btn-small btn-primary"}]);
                return;
            }
            else
            {
                clause = clause + $(this).val() + "~";

                //alert(clause.indexOf($(this).val()));

                $("<option>").val($(this).val()).text($(this).val()).appendTo("#rfqClausesSelected");
            }
        }
        console.log(clause);
        $("#rfeClauses").val(clause);

    });
//     
//     $("#rfqClausesSelected").change(function() {
//        alert($(this).val());
    //     });
    $("#rfqClausesSelected").click(function() {
        var selClause = $(this).val();
        //alert(selClause);
        clause = clause.replace(selClause + "~", "");
        console.log(clause);
        $("#rfqClausesSelected option[value='" + selClause + "']").remove();
        $("#rfeClauses").val(clause);
        console.log($("#rfeClauses").val());
    });
    var app1 = "";
    var app2 = "";
    var app3 = "";
    var app4 = "";
    var app5 = "";
    var apps = "";
    var app1Check = "";
    var app2Check = "";
    var app3Check = "";
    var app4Check = "";
    var app5Check = "";


    $(".approvalsClass").change(function() {
        //alert($(this).val()); 
        //alert($(this).prop("id"));
        if ($(this).val() !== "Select")
        {
            if ($(this).prop("id") === "approval1") {
                app1 = $(this).prop("id");
                app1Check = app1Check + $(this).val() + "#";
                console.log("app1: " + app1Check);
                $("#app1Name").val($(this).find("option:selected").text());
            }
            if ($(this).prop("id") === "approval2") {
                app2 = $(this).prop("id");
                app2Check = app2Check + $(this).val() + "#";
                console.log("app2: " + app2Check);
                $("#app2Name").val($(this).find("option:selected").text());
            }
            if ($(this).prop("id") === "approval3") {
                app3 = $(this).prop("id");
                app3Check = app3Check + $(this).val() + "#";
                console.log("app3: " + app3Check);
                $("#app3Name").val($(this).find("option:selected").text());
            }
            if ($(this).prop("id") === "approval4") {
                app4 = $(this).prop("id");
                app4Check = app4Check + $(this).val() + "#";
                console.log("app4: " + app4Check);
                $("#app4Name").val($(this).find("option:selected").text());
            }
            if ($(this).prop("id") === "approval5") {
                app5 = $(this).prop("id");
                app5Check = app5Check + $(this).val() + "#";
                console.log("app5: " + app5Check);
                $("#app5Name").val($(this).find("option:selected").text());
            }
            var id = $(this).val();
            $.ajax(
                    {
                        type: "GET",
                        url: "rfeCont.do",
                        async: true,
                        data:
                                {
                                    "reqFrom": "approvals",
                                    "approvalId": id
                                },
                        dataType: "json",
                        complete: function(responseJson)
                        {
                            var obj = $.parseJSON(responseJson.responseText);
                            //alert("done: " + obj.username);
                            //alert(app1);
                            if (app1 === "approval1") {
                                $("#approval1EmailId").text(obj.username);
                                app1 = "";
                                $("#app1Email").val(obj.username);
                                //alert($("#app1Email").val());
                            }
                            if (app2 === "approval2") {
                                $("#approval2EmailId").text(obj.username);
                                app2 = "";
                                $("#app2Email").val(obj.username);
                            }
                            if (app3 === "approval3") {
                                $("#approval3EmailId").text(obj.username);
                                app3 = "";
                                $("#app3Email").val(obj.username);
                            }
                            if (app4 === "approval4") {
                                $("#approval4EmailId").text(obj.username);
                                app4 = "";
                                $("#app4Email").val(obj.username);
                            }
                            if (app5 === "approval5") {
                                $("#approval5EmailId").text(obj.username);
                                app5 = "";
                                $("#app5Email").val(obj.username);
                            }

                        }
                    });
        }
        else
        {
            if ($(this).prop("id") === "approval1")
                $("#approval1EmailId").text("");
            if ($(this).prop("id") === "approval2")
                $("#approval2EmailId").text("");
            if ($(this).prop("id") === "approval3")
                $("#approval3EmailId").text("");
            if ($(this).prop("id") === "approval4")
                $("#approval4EmailId").text("");
            if ($(this).prop("id") === "approval5")
                $("#approval5EmailId").text("");

        }
        var app = $(this).val();
        if (apps.length !== 0 && apps.indexOf(app) !== -1)
        {
            bootbox.dialog("Already Selected!", [{"label": "ok", "class": "btn-small btn-primary"}]);
            $(this).val("Select");
            $(this).focus();
            return;
        }

        apps = apps + app + "#";
        console.log(apps);

    });

    $(".approvalsClass").blur(function() {
//        var app = $(this).val();
//        if(apps.length !== 0 && apps.indexOf(app) !== -1)
//        {
//            bootbox.dialog("Already Selected!", [{"label": "ok", "class": "btn-small btn-primary"}]);
//            $(this).val("Select");
//            return;
//        }
//        apps = apps + app + "#";
//        console.log(apps);
        //        
        //alert(app);
    });
    var injectDivId = 1;

    $("#add_att_btn").click(function(e) {
        var htmlText = '<div class="row-fluid"><div class="span10"><div class="control-group"><div class="controls">\n\
                        <input type="file" id="id-input-file-2" class="span10" name="file"/></div></div></div></div>\n\
                        <div class="row-fluid"><div class="span6"><div class="control-group"><label class="control-label" for="">Name</label>\n\
                        <div class="controls"><input type="text" class="span10" name="name" id="name"/></div></div></div>\n\
                        <div class="span6"><div class="control-group"><label class="control-label" for="">Description</label><div class="controls">\n\
                        <textarea id="attDescription" name="attDescription" rows="4" class="span10" required></textarea>\n\
                        <button type="button" class="btn btn-small btn-danger pull-right" id="remove_att_btn"><i class="icon-remove"></i></button>\n\
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
    $("#approve_btn_id").click(function() {
//            bootbox.confirm("Are you sure, you want to Approve this request!", function(result) {
//                if (result === true)
//                {
//                    //location.href = "updateRfp.do?rfpId=" + window.location.search.substring(1).split("=")[1] + "&action=Approve";
//                    //location.href = "mytask.do";
//                }

//            });
        //        location.href = "updateRfp.do?rfpId=" + window.location.search.substring(1).split("=")[1] + "&action=Approve";
        $("#approve-form").submit();
    });
    $("#send_for_revision_btn_id").click(function() {
//            bootbox.confirm("Are you sure, you want to send this request for Revision!", function(result) {
//                if (result === true)
//                {
//                    //location.href = "updateRfp.do?rfpId=" + window.location.search.substring(1).split("=")[1] + "&action=Revision";
//                    //location.href = "mytask.do";
//                }
//            });
        //        location.href = "updateRfp.do?rfpId=" + window.location.search.substring(1).split("=")[1] + "&action=Revision";
        $("#revision-form").submit();
    });
    $("#close_btn_id").click(function() {
        location.href = "mytask.do";
    });


    $("#suppier_award_close_btn_id").click(function() {
        location.href = "mytask.do";
//        var param = window.location.search.substring(1).split("&");
//        
//        alert(param + ", length: " + param.length);
        //        alert(param[0].split("=")[1]);

    });

    $("#award_close_btn_id").click(function() {
        location.href = "rfpStatus.do";
//        var param = window.location.search.substring(1).split("&");
//        
//        alert(param + ", length: " + param.length);
        //        alert(param[0].split("=")[1]);

    });
    $("#award_btn_id").click(function() {
        bootbox.confirm("Are you sure, you want to Award this RFP.", function(result) {
            if (result === true)
            {
                //                $("#validation-emp").submit();
                location.href = "doAwardRfp.do?rfpid=" + $("#rfpId").val() + "&supplierid=" + $("#supplierId").val();
            }
        });

    });


    $("#update_btn_revsion_id").click(function() {
        //location.href = "updateForRevision.do?rfpId=" + window.location.search.substring(1).split("=")[1];
        $("#validation-emp").submit();
    });

    $("#send_btn_revsion_id").click(function() {
        //location.href = "updateForRevision.do?rfpId=" + window.location.search.substring(1).split("=")[1];
        $("#validation-emp").submit();
    });
    $("#edit_btn_id").click(function() {
        //$(":input").removeAttr("readonly");
        $(".make-enable").removeAttr("style").removeAttr("readonly").removeAttr("disabled");
        $(".show-field").removeAttr("style");
        //$(".hidden-field").css("display", "none");
        var pt = $("#paymenttermsReadonly").val();
        //alert(pt);
        $("#paymenttermsReadonlyDiv").css("display", "none");
        $('input[name="paymentterms"][value="' + pt + '"]').prop("checked", true);

        var costCode = $("#costcodeReadonly").val();
        var negoStyle = $("#negotationstyleReadonly").val();

        $("#costcode").val(costCode);
        $("#Negotationstyle").val(negoStyle);

        $("#costCodeAndNegoStyleDiv").css("display", "block");

        $("#costcodeReadonlyDiv").css("display", "none");
        $("#negotationstyleReadonlyDiv").css("display", "none");
    });
    var selectedSupplierId = "";
    $("#availableSupplier").click(function() {

        var selectedSupplierLen = $("select#selectedSupplier option").length;
        //console.log(selectedSupplierLen);
        if (selectedSupplierLen !== 0)
        {
            var selectedSupplier = $(this).val();
            var temp = 0;
            $("#selectedSupplier > option").each(function() {
                //console.log(this.value + " : " + selectedSupplier);
                if (this.value === selectedSupplier)
                {
                    bootbox.dialog("Already selected!", [{"label": "ok", "class": "btn-small btn-primary"}]);
                    temp = 1;
                }
            });
            if (temp === 1)
            {
                return;
            }
            $("<option>").val($(this).val()).text($("#availableSupplier option:selected").text()).appendTo("#selectedSupplier");
            selectedSupplierId = selectedSupplierId + $(this).val() + "~";
        }
        else
        {
            $("<option>").val($(this).val()).text($("#availableSupplier option:selected").text()).appendTo("#selectedSupplier");
            selectedSupplierId = selectedSupplierId + $(this).val() + "~";
        }
        console.log("selectedSupplierId: " + selectedSupplierId);
        $("#selectedSupplierId").val(selectedSupplierId);
    });

    $("#select-all-suuplier-btn").click(function() {
        $("#selectedSupplier").empty();
        $("#selectedSupplier").append($("#availableSupplier option").clone());
        $("#select-all-suuplier-btn").prop("disabled", true);
        selectedSupplierId = "";
        $("#selectedSupplier > option").each(function() {
            //            console.log(this.value);
            selectedSupplierId = selectedSupplierId + $(this).val() + "~";
        });
        console.log("selectedSupplierId: " + selectedSupplierId);
        $("#selectedSupplierId").val(selectedSupplierId);
    });

    $("#deselect-all-suuplier-btn").click(function() {
        $("#selectedSupplier").empty();
        $("#select-all-suuplier-btn").prop("disabled", false);
        selectedSupplierId = "";
        console.log("selectedSupplierId: " + selectedSupplierId);
        $("#selectedSupplierId").val(selectedSupplierId);
    });
    $("#releaseRFPBtnId").click(function() {
        //alert("click");
        var rfp = $("#approved_rfp_select").val();
        //alert(rfp);
        if (rfp === null)
        {
            bootbox.dialog("Please Select RFP!", [{"label": "ok", "class": "btn-small btn-primary"}]);
            return false;
        }
        var selectedSupplierLen = $("select#selectedSupplier option").length;
        if (selectedSupplierLen === 0)
        {
            bootbox.dialog("Please select at least one supplier!", [{"label": "ok", "class": "btn-small btn-primary"}]);
            return false;
        }
    });


    $("#search_supplier").on("keyup", function() {
//        alert("dsfd");
        var value = $(this).val().toLowerCase();
        $("#availableSupplier option").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1);
        });
    });

});

function format(rfpid) {
    var subtable = "";

    $.ajax(
            {
                type: "GET",
                url: "rfeCont.do",
                async: false,
                data:
                        {
                            "reqFrom": "supplierOnRfpId",
                            "rfpid": rfpid
                        },
                dataType: "json",
                complete: function(responseJson)
                {
                    var obj = $.parseJSON(responseJson.responseText);
//                    alert(obj.supplierIdJArr);
                    //                    alert(obj.supplierNameJArr);
                    var tds = "";
                    for (var i = 0; i < obj.supplierIdJArr.length; i++)
                    {
                        tds = tds + (i + 1) + '. ' + '<a href=showSupplier.do?supplierId=' + obj.supplierIdJArr[i] + '>' + obj.supplierNameJArr[i] + '</a>' + '<br>';
                    }
                    subtable = '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;"><th>Suppliers</th>' +
                            '<tr>' +
                            '<td>' + tds + '</td>' +
                            '</tr>' +
                            '</table>';

                }
            });
    return subtable;

}
function onLoadForOpenDate()
{
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth() + 1; //January is 0!
    var yyyy = today.getFullYear();
    if (dd < 10) {
        dd = '0' + dd
    }
    if (mm < 10) {
        mm = '0' + mm
    }
//    alert("dsfd");
    today = yyyy + '-' + mm + '-' + dd;
    console.log(today);
    $("#opendate").attr("min", today);

}
function onLoadFunction()
{
    


    var status = $("#rfqstatus").val();
    var assignedTo = $("#assignedTo").val();
    var loggedUserId = $("#loggedUserId").val();

    //alert(status);
    if (status === "Awaiting Release" || status === "Sent for Revision" || assignedTo !== loggedUserId)
    {
        $("#approve_div_id").css("display", "none");
    }

    if (status !== "Sent for Revision")
    {
        $("#revision_update_div").css("display", "none");
    }

    if (status === "Awarded")
    {
        $("#award_btn_id").css("display", "none");
    }
    var param = window.location.search.substring(1).split("&");

//    alert(param + ", length: " + param.length);
    //    alert(param[0].split("=")[1]);

    $("#rfpId").val(param[0].split("=")[1]);

    $("#costCodeAndNegoStyleDiv").css("display", "none");

    $("#approve_rfpId").val(window.location.search.substring(1).split("=")[1]);
    //$("#downloadFileLink").prop("href", "downloadFile.do?rfpId="+window.location.search.substring(1).split("=")[1])
    $("#revision_rfpId").val(window.location.search.substring(1).split("=")[1]);


}