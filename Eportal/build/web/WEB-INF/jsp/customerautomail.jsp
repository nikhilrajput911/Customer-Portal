
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
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>

        <link href="css/bootstrap.min.css" rel="stylesheet" />
        <link href="css/bootstrap-responsive.min.css" rel="stylesheet" />
        <!--<link rel="stylesheet" href="css/font-awesome.min.css" />-->
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

        <link rel="stylesheet" href="css/font-awesome2.min.css" />
        <!--<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet" />-->
        <!--<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>-->

        <link rel="stylesheet" href="css/customer-notification.css" />
        <script src="js/customer-notification.js"></script>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

        <script src="js/customer.js"></script>

        <style>
            .mail-modal {
                width: 500px;
            }
            .mail-modal-customer-select {
                width: 514px;
            }
        </style>

        <script>
            $(document).ready(function() {
                var set_image = $("#set_image").prop("src").toString().split(",");
                if (set_image[1] === "NotFound")
                {
                    $("#default_image_a").css("display", "block");
                    $("#set_image_a").css("display", "none");
                }

                $("#customername").change(function() {

                    var cid = $(this).val();
                    console.log("cid: " + cid);
                    if (cid !== "--Select--")
                    {
                        $.ajax(
                                {
                                    type: "GET",
                                    url: "rfeCont.do",
                                    async: true,
                                    data:
                                            {
                                                "reqFrom": "UserDetailsByCustomerSeededCid",
                                                "Cid": cid
                                            },
                                    dataType: "json",
                                    complete: function(responseJson)
                                    {
                                        var obj = $.parseJSON(responseJson.responseText);
//                            alert(obj.Status);
                                        console.log(obj.status);
                                        var row = "";
                                        if (obj.status === "Found")
                                        {

                                            for (var i = 0; i < obj.Result.length; i++)
                                            {
//                                                console.log(obj.Result[i].Username);
                                                var emailid = ""
                                                console.log(obj.Result[i].Emailid);
                                                if (obj.Result[i].Emailid !== undefined)
                                                {
                                                    emailid = obj.Result[i].Emailid;
                                                }
                                                if (obj.Result[i].Rights !== "Non")
                                                {
                                                    var rights = obj.Result[i].Rights.split(",");
                                                    console.log("rights: " + rights);
                                                    row += "<tr><td>" + (i + 1) + "</td><td>" + obj.Result[i].Username + "</td><td><input type='text' value='" + emailid + "' disabled></td><td class='center'><input type='checkbox' name='invoice' class='doctype' disabled='true' " + (rights[0] === 'Y' ? 'checked' : '') + "></td><td class='center'><input type='checkbox' name='singeddo' class='doctype' disabled='true' " + (rights[1] === 'Y' ? 'checked' : '') + "></td><td class='center hidden'><input type='checkbox' name='unsingeddo' class='doctype' disabled='true' " + (rights[2] === 'Y' ? 'checked' : '') + "></td><td class='center'><input type='checkbox' name='debitnote' class='doctype' disabled='true' " + (rights[3] === 'Y' ? 'checked' : '') + "></td><td class='center'><input type='checkbox' name='creditnote' class='doctype' disabled='true' " + (rights[4] === 'Y' ? 'checked' : '') + "></td><td class='center'><input type='checkbox' name='arlisting' class='doctype' disabled='true' " + (rights[5] === 'Y' ? 'checked' : '') + "></td><td class='center'><input type='checkbox' name='soa' class='doctype' disabled='true' " + (rights[6] === 'Y' ? 'checked' : '') + "></td><td><a class='edit-column' title='Edit'><i class='fa fa-pencil-square-o fa-2x align-center'></i></a></td></tr>";
                                                }
                                                else
                                                {
                                                    row += "<tr><td>" + (i + 1) + "</td><td>" + obj.Result[i].Username + "</td><td><input type='text' value='" + emailid + "' disabled></td><td class='center'><input type='checkbox' name='invoice' class='doctype' disabled='true'></td><td class='center'><input type='checkbox' name='singeddo' class='doctype' disabled='true'></td><td class='center hidden'><input type='checkbox' name='unsingeddo' class='doctype' disabled='true'></td><td class='center'><input type='checkbox' name='debitnote' class='doctype' disabled='true'></td><td class='center'><input type='checkbox' name='creditnote' class='doctype' disabled='true'></td><td class='center'><input type='checkbox' name='arlisting' class='doctype' disabled='true'></td><td class='center'><input type='checkbox' name='soa' class='doctype' disabled='true'></td><td><a class='edit-column' title='Edit'><i class='fa fa-pencil-square-o fa-2x align-center'></i></a></td></tr>";
                                                }
                                            }
                                            $("#submitautomail").removeClass("hidden");

                                            $("#user_table_div").removeClass("hidden");

                                            $("#user_table").children("tbody").html(row);


                                            if ($.fn.DataTable.isDataTable('#user_table')) {
                                                table2.fnDestroy();

                                                $("#user_table").children('tbody').html(row);

                                                table2 = $('#user_table').dataTable();

                                            }
                                            else
                                            {
                                                table2 = $('#user_table').dataTable();

                                            }
                                        }
                                        else
                                        {
                                            alert("No Records Found.");
                                            console.log("not found");
                                        }
                                    }
                                });

                    }
                    else
                    {
                        alert("Please select customer!");
                        $("#user_table_div").addClass("hidden");
                        $("#submitautomail").addClass("hidden");
                    }
                });


                $("#user_table").on("click", ".edit-column", function() {

                    var obj = $(this);

                    if ($(this).prop("title") === "Update")
                    {
                        console.log("update");

                        var customerName = $("#customername option:selected").text();
                        var customerCode = $("#customername").val();

                        var username = $(this).parent().parent().find("td").eq(1).html();
                        var emailid = $(this).parent().parent().find("td").eq(2).children().val();

                        var isInvoice = $(this).parent().parent().find("td").eq(3).children().prop("checked");
                        var isSingeddo = $(this).parent().parent().find("td").eq(4).children().prop("checked");
                        var isUnsingeddo = $(this).parent().parent().find("td").eq(5).children().prop("checked");
                        var isDebitnote = $(this).parent().parent().find("td").eq(6).children().prop("checked");
                        var isCreditnote = $(this).parent().parent().find("td").eq(7).children().prop("checked");
                        var isArlisting = $(this).parent().parent().find("td").eq(8).children().prop("checked");
                        var isSoa = $(this).parent().parent().find("td").eq(9).children().prop("checked");

                        var invoice = $(this).parent().parent().find("td").eq(3).children().prop("name");
                        var singeddo = $(this).parent().parent().find("td").eq(4).children().prop("name");
                        var unsingeddo = $(this).parent().parent().find("td").eq(5).children().prop("name");
                        var debitnote = $(this).parent().parent().find("td").eq(6).children().prop("name");
                        var creditnote = $(this).parent().parent().find("td").eq(7).children().prop("name");
                        var arlisting = $(this).parent().parent().find("td").eq(8).children().prop("name");
                        var soa = $(this).parent().parent().find("td").eq(9).children().prop("name");

                        if (isInvoice === false && isSingeddo === false && isUnsingeddo === false && isDebitnote === false && isCreditnote === false && isArlisting === false && isSoa === false)
                        {
                            alert("Please select atleast one document!");
                            return;
                        }
                        else
                        {
                            var temp = "";
                            if (emailid === "")
                            {
                                emailid = "Non";
                            }
                            temp += customerCode + "," + customerName + "," + username + "," + emailid + ",";
                            if (isInvoice === true)
                            {
                                temp += "Y" + ",";
                            }
                            else
                            {
                                temp += "N" + ",";
                            }
                            if (isSingeddo === true)
                            {
                                temp += "Y" + ",";
                            }
                            else
                            {
                                temp += "N" + ",";
                            }
                            if (isUnsingeddo === true)
                            {
                                temp += "Y" + ",";
                            }
                            else
                            {
                                temp += "N" + ",";
                            }
                            if (isDebitnote === true)
                            {
                                temp += "Y" + ",";
                            }
                            else
                            {
                                temp += "N" + ",";
                            }
                            if (isCreditnote === true)
                            {
                                temp += "Y" + ",";
                            }
                            else
                            {
                                temp += "N" + ",";
                            }
                            if (isArlisting === true)
                            {
                                temp += "Y" + ",";
                            }
                            else
                            {
                                temp += "N" + ",";
                            }
                            if (isSoa === true)
                            {
                                temp += "Y" + ",";
                            }
                            else
                            {
                                temp += "N" + ",";
                            }
                            console.log("temp: " + temp);

//                            updateUserEmailStatus(temp, obj);


                            $.ajax(
                                    {
                                        type: "GET",
                                        url: "rfeCont.do",
                                        data:
                                                {
                                                    "reqFrom": "UpdateUserEmailStatus",
                                                    "Data": temp
                                                },
                                        dataType: "json",
                                        complete: function(responseJson)
                                        {
                                            alert("Details has been updated successfully.");

                                        }
                                    });
                                    
                            $(this).parent().parent().find("td").eq(2).children().prop("disabled", true);        
                                    
                            $(this).parent().parent().find("td").eq(3).children().prop("disabled", true);
                            $(this).parent().parent().find("td").eq(4).children().prop("disabled", true);
                            $(this).parent().parent().find("td").eq(5).children().prop("disabled", true);
                            $(this).parent().parent().find("td").eq(6).children().prop("disabled", true);
                            $(this).parent().parent().find("td").eq(7).children().prop("disabled", true);
                            $(this).parent().parent().find("td").eq(8).children().prop("disabled", true);
                            $(this).parent().parent().find("td").eq(9).children().prop("disabled", true);

                            $(this).children().prop("class", "fa fa-pencil-square-o fa-2x align-center");
                            $(this).prop("title", "Edit");
                        }
                    }
                    else
                    {
                        console.log("edit");
                        $(this).parent().parent().find("td").eq(2).children().prop("disabled", false);
                        
                        $(this).parent().parent().find("td").eq(3).children().prop("disabled", false);
                        $(this).parent().parent().find("td").eq(4).children().prop("disabled", false);
                        $(this).parent().parent().find("td").eq(5).children().prop("disabled", false);
                        $(this).parent().parent().find("td").eq(6).children().prop("disabled", false);
                        $(this).parent().parent().find("td").eq(7).children().prop("disabled", false);
                        $(this).parent().parent().find("td").eq(8).children().prop("disabled", false);
                        $(this).parent().parent().find("td").eq(9).children().prop("disabled", false);

                        $(this).children().prop("class", "fa fa-save fa-2x align-center");
                        $(this).prop("title", "Update");

                    }
                });

                $("#addUserBtn").click(function()
                {
                    $("#autoMailModalDiv").removeClass("hidden");

                    $("#adduser_customername").val("--Select--");
                    $("#add_username").val("");
                    $("#useremailid").val("");

                    $("#add_invoice").prop("checked", false);
                    $("#add_singeddo").prop("checked", false);
                    $("#add_unsingeddo").prop("checked", false);
                    $("#add_debitnote").prop("checked", false);
                    $("#add_creditnote").prop("checked", false);
                    $("#add_arlisting").prop("checked", false);
                    $("#add_soa").prop("checked", false);
                    
                    $("#errorUserMailTo").html('');
                    $("#errorUsername").html("");
                    
                    $("#addUserAutoMail").prop("disabled", false);

                    $("#sendAutoMailModal").modal("show");
                });


                $("#useremailid").after("<div id='errorUserMailTo' style='color:red;'></div>");
                $("#useremailid").blur(function() {
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
                                $("#errorUserMailTo").html('');
                                $("#addUserAutoMail").prop("disabled", false);
                            }
                            else
                            {
//                           console.log("else");
                                $("#errorUserMailTo").html('Please enter valid email address!');
                                $("#addUserAutoMail").prop("disabled", true);
                            }
                        }

                    }
                    else
                    {
                        $("#errorUserMailTo").html('');
                    }
                });

                $("#addUserAutoMail").click(function() {
                    var customerId = $("#adduser_customername").val();
                    var customerName = $("#adduser_customername option:selected").text();

                    var username = $("#add_username").val();
                    var emailId = $("#useremailid").val();

                    if (customerName === "--Select--")
                    {
                        $("#adduser_customername").focus();
                        return false;
                    }

                    if (username === "")
                    {
                        $("#add_username").focus();
                        return false;
                    }

                    if (emailId === "")
                    {
                        $("#useremailid").focus();
                        return false;
                    }

                    console.log("customerId: " + customerId);

                    var invoice = "";
                    var singedDO = "";
                    var unsingedDO = "";
                    var debitNote = "";
                    var creditNote = "";
                    var arListing = "";
                    var soa = "";

                    if ($("#add_invoice").prop("checked") === true)
                    {
                        invoice = "Y";
                    }
                    else
                    {
                        invoice = "N";
                    }

                    if ($("#add_singeddo").prop("checked") === true)
                    {
                        singedDO = "Y";
                    }
                    else
                    {
                        singedDO = "N";
                    }

                    if ($("#add_unsingeddo").prop("checked") === true)
                    {
                        unsingedDO = "Y";
                    }
                    else
                    {
                        unsingedDO = "N";
                    }

                    if ($("#add_debitnote").prop("checked") === true)
                    {
                        debitNote = "Y";
                    }
                    else
                    {
                        debitNote = "N";
                    }

                    if ($("#add_creditnote").prop("checked") === true)
                    {
                        creditNote = "Y";
                    }
                    else
                    {
                        creditNote = "N";
                    }

                    if ($("#add_arlisting").prop("checked") === true)
                    {
                        arListing = "Y";
                    }
                    else
                    {
                        arListing = "N";
                    }

                    if ($("#add_soa").prop("checked") === true)
                    {
                        soa = "Y";
                    }
                    else
                    {
                        soa = "N";
                    }


                    if (invoice === "N" && singedDO === "N" && unsingedDO === "N" && debitNote === "N" && creditNote === "N" && arListing === "N" && soa === "N")
                    {
                        alert("Please check at least one document!");
                        return false;
                    }

                    console.log("done;");

                    var temp = customerId + "~" + customerName + "~" + username + "~" + emailId + "~" + invoice + "~" + singedDO + "~" + unsingedDO + "~" + debitNote + "~" + creditNote + "~" + arListing + "~" + soa;

                    $.ajax(
                            {
                                type: "GET",
                                url: "rfeCont.do",
                                data:
                                        {
                                            "reqFrom": "AddAutoMailUser",
                                            "Data": temp
                                        },
                                dataType: "json",
                                complete: function(responseJson)
                                {
                                    alert("User has been addedd successfully.");
                                    $("#sendAutoMailModal").modal("hide");
                                }
                            });
                });

                $("#add_username").after("<div id='errorUsername' style='color:red;'></div>");
                $("#add_username").change(function() {
                    var username = $(this).val();
                    if (username !== "")
                    {
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
                                        if (obj.Status === 'NotExits')
                                        {
                                            $("#add_username").css("border-color", "");
                                            $("#errorUsername").html("");
                                            $("#addUserAutoMail").prop("disabled", false);
                                        }
                                        else
                                        {
                                            $("#add_username").css("border-color", "red");
                                            $("#errorUsername").html("User already exists!");
                                            $("#addUserAutoMail").prop("disabled", true);
                                        }
                                    }
                                });
                    }
                });
            });
            function updateUserEmailStatus(data, obj)
            {


            }
        </script>

    </head>

    <body>

        <%@include file = "templatecustomer.jsp" %>
        <div class="breadcrumbs" id="breadcrumbs">
            <ul class="breadcrumb">
                <li class="align-center">
                    <h4>Auto Mail Configuration</h4>

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
                    <div class="row-fluid">
                        <div class="span6">
                            <div class="control-group">
                                <label class="control-label" for="customerId">Customer Name</label>
                                <div class="controls">
                                    <div class="">
                                        <select id="customername" name="customername" class="span10 myorder-form-field" required>
                                            <option>--Select--</option>
                                            <c:forEach var="customer" items="${customerList}" varStatus="status">
                                                <option value="${customer.cid}#${customer.customercode}">${customer.customername}</option>
                                            </c:forEach>
                                        </select>

                                    </div>
                                </div>																
                            </div>
                        </div>
                        <div class="span6">
                            <div class="control-group">
                                <label class="control-label" for=""></label>
                                <div class="controls">
                                    <div class="align-right">
                                        <input type="button" id="addUserBtn" value="Add User" class="btn btn-pink hidden">
                                    </div>
                                </div>																
                            </div>
                        </div>
                    </div>
                    <div class="row-fluid table-responsive">
                        <div class="span12">
                            <div id="user_table_div" class="hidden">
                                <table id="user_table" class="table table-striped table-bordered table-hover">
                                    <thead>
                                    <th>S.No.</th>
                                    <th>Username</th>
                                    <th>Email Id</th>
                                    <th>Invoice</th>
                                    <th>Signed DO</th>
                                    <th class="hidden">Unsigned DO</th>
                                    <th>Debit Note</th>
                                    <th>Credit Note</th>
                                    <th>AR Listing</th>
                                    <th>SOA</th>
                                    <th></th>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="row-fluid">
                        <div class="span12">
                            <div class="align-center">
                                <!--<input type="button" id="submitautomail" value="submit" class="btn btn-primary hidden">-->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div id="autoMailModalDiv" class="hidden">
            <div class="modal fade" id="sendAutoMailModal" role="dialog">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Add User</h4>
                        </div>
                        <div class="modal-body">
                            <form id="mail_form" method="post" action="" enctype="multipart/form-data">

                                <label class="" for=""><b>Customer Name</b></label>
                                <select id="adduser_customername" name="adduser_customername" class="mail-modal-customer-select" required>
                                    <option>--Select--</option>
                                    <c:forEach var="customer" items="${customerList}" varStatus="status">
                                        <option value="${customer.cid}#${customer.customercode}">${customer.customername}</option>
                                    </c:forEach>
                                </select>

                                <label class="" for=""><b>Username</b></label>
                                <input type="text" placeholder="" id="add_username" name="add_username" class="mail-modal">

                                <label class="" for=""><b>User Email Id</b></label>
                                <input type="text" placeholder="" id="useremailid" name="useremailid" class="mail-modal">

                                <label class="" for=""><b>Document Type</b></label>
                                <table>
                                    <tr>
                                        <td><input type="checkbox" name="invoice" id="add_invoice"> Invoice</td>
                                        <td style="color: white;">xxxxxxx</td>
                                        <td><input type="checkbox" name="singeddo" id="add_singeddo"> Signed DO</td>
                                        <td style="color: white;">xxxxxxx</td>
                                        <td class="hidden"><input type="checkbox" name="unsingeddo" id="add_unsingeddo"> Unsigned DO</td>
                                        <td><input type="checkbox" name="debitnote" id="add_debitnote"> Debit Note</td>
                                    </tr>
                                    <tr>
                                        <td><input type="checkbox" name="creditnote" id="add_creditnote"> Credit Note</td>
                                        <td style="color: white;">xxxxxxx</td>
                                        <td><input type="checkbox" name="arlisting" id="add_arlisting"> AR Listing</td>
                                        <td style="color: white;">xxxxxxx</td>
                                        <td><input type="checkbox" name="soa" id="add_soa"> SOA</td>
                                    </tr>
                                </table>

                            </form>    
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-success" id="addUserAutoMail">Add</button>
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
