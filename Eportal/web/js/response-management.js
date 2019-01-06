var jsonBarChartData;
$(document).ready(function() {

    $("#awaiting_bid_rfp_select").change(function() {
        var rfqid = $(this).val();
//       alert(rfqid);
        $.ajax(
                {
                    type: "GET",
                    url: "rfeCont.do",
                    async: true,
                    data:
                            {
                                "reqFrom": "responseManagement",
                                "rfqId": rfqid
                            },
                    dataType: "json",
                    complete: function(responseJson)
                    {
                        var arr = $.parseJSON(responseJson.responseText);
                        var row = "";
                        $("#allRfqid").val($("#awaiting_bid_rfp_select").val());
                        var supplierIds = '';
                        var usertype = $("#usertype").val();
                        
                        for (var i = 0; i < arr.length; i++)
                        {
                            supplierIds = supplierIds + arr[i].supplierId + '~';
                            if(usertype === "SCM User")
                                row += "<tr><td class='center'><input type='checkbox'/></td><td>" + (i + 1) + "</td><td><a href='responseComment.do?rfqid=" + arr[i].rfqId + "&supplierid=" + arr[i].supplierId + "'>" + arr[i].supplierName + "</a></td><td>RFP_" + arr[i].rfqId + "</td><td>" + arr[i].rfqStatus + "</td></tr>"
                            else
                                row += "<tr><td>" + (i + 1) + "</td><td><a href='responseComment.do?rfqid=" + arr[i].rfqId + "&supplierid=" + arr[i].supplierId + "'>" + arr[i].supplierName + "</a></td><td>RFP_" + arr[i].rfqId + "</td><td>" + arr[i].rfqStatus + "</td></tr>"
                        }
                        $("#allSupplierid").val(supplierIds);
                        
                        $("#awaiting_bid_rfp_table_div").css("display", "block");
                        $("#bid_awaiting_rfq_table").children('tbody').html(row);

                        if ($.fn.DataTable.isDataTable('#bid_awaiting_rfq_table')) {
//                            alert("if");

                            table.fnDestroy();
                            $("#bid_awaiting_rfq_table").children('tbody').html(row);
                            table = $('#bid_awaiting_rfq_table').dataTable();
//                            table.resetPaging();
                            table.fnReloadAjax();
                            table = $('#bid_awaiting_rfq_table').dataTable({
//                                "processing": true,
//                                "sPaginationType": "full_numbers",
//                                "oLanguage": {
//                                    "sLengthMenu": "_MENU_ records per page"
//                                }
                                buttons: [
                                    'copy', 'excel', 'pdf'
                                ]
                            });
                        }
                        else
                        {
//                            table = $('#tblFeeReceiptSearch').dataTable();
//                            table.fnReloadAjax();
                            table = $('#bid_awaiting_rfq_table').dataTable({
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

    $('#bid_awaiting_rfq_table th input:checkbox').on('click', function() {
        if($(this).prop('checked'))
        {
            $("#send_message_to_all").css("display", "block");
        }
        else
        {
            $("#send_message_to_all").css("display", "none");
        }
        var that = this;
        $(this).closest('#bid_awaiting_rfq_table').find('tr > td:first-child input:checkbox')
                .each(function() {
                    this.checked = that.checked;
                    $(this).closest('tr').toggleClass('selected');
                });
    });
    
    $("#send_message_to_all_supplier").click(function() {
        var message = $("#messageToAll").val();
//        alert(message);
        $("#messageContentToAllSupplier").val(message);
        
        if(message.trim() === '')
        {
//            alert('sadasd');
            $("#messageToAll").css("border-color","red");
            $("#messageToAll").focus();
            return false;
        }
        $("#comment-form").submit();
    });
    
    $("#rfpDetailsLink").click(function() {
//       alert("nbvmnb");
        var rfqid = $(this).html();
//        alert(rfqid);

        $("#rfpDetailsModal").modal("show");
    });

    $("#post_comment_btn").click(function() {
//       alert("dff");
        var message = $(".wysiwyg-editor").text();
        $("#messageContent").val(message);

        var param = window.location.search.substring(1).split("&");

        $("#rfqid").val(param[0].split("=")[1]);
        $("#supplierid").val(param[1].split("=")[1]);
//        alert(encodeURIComponent(message));
        $("#comment-form").submit();

    });

    $("#approved_rfp_select").change(function() {
        var rfqid = $(this).val();
//       alert(rfqid);
        $.ajax(
                {
                    type: "GET",
                    url: "rfeCont.do",
                    async: true,
                    data:
                            {
                                "reqFrom": "budgetComparison",
                                "rfqId": rfqid
                            },
                    dataType: "json",
                    complete: function(responseJson)
                    {
                        var obj = $.parseJSON(responseJson.responseText);
                        console.log(obj.chartData.length);
                        jsonBarChartData = obj.chartData;

                        var arr = obj.tableData;
                        var row = "";
                        for (var i = 0; i < arr.length; i++)
                        {
                            row += "<tr><td>" + (i + 1) + "</td><td>" + arr[i].supplierName + "</td><td>RFP_" + arr[i].rfqid + "</td><td>" + arr[i].timeLeft + "</td><td>" + arr[i].quantity + "</td><td>" + arr[i].quotPricePerQuantity + "</td><td>" + arr[i].expectedPrice + "</td><td>" + arr[i].totalPrice + "</td><td>" + arr[i].targetPrice + "</td><tr>";

                        }
                        $("#tableDivId").css("display", "block");
                        $("#budgetComparisonTable").children('tbody').html(row);

                        loadBarChart();

                    }
                });
    });
});
function loadBarChart()
{
    google.charts.load('current', {'packages': ['bar']});
    google.charts.setOnLoadCallback(drawChart);
}
function drawChart() {

    var barChartData = [];
    var header = ['Supplier', 'Target Price', 'Total Price', 'Expected Price'];

    barChartData.push(header);
//    console.log(jsonBarChartData.length);

    for (var i = 0; i < jsonBarChartData.length; i++)
    {
        var temp = [];
        temp.push(jsonBarChartData[i].chart_supplierName);
        temp.push(jsonBarChartData[i].chart_targetPrice);
        temp.push(jsonBarChartData[i].chart_totalPrice);
        temp.push(jsonBarChartData[i].chart_expectedPrice);

        barChartData.push(temp);
    }
//    console.log(barChartData);

    var data = google.visualization.arrayToDataTable(barChartData);


    var options = {
        chart: {
            title: 'Supplier Bid Comparison',
            subtitle: 'Target Price, Total Price and Expected Price',
        },
        bars: 'vertical', // Required for Material Bar Charts.
        height: 400,
        colors: ['#1b9e77', '#d95f02', '#7570b3'],
        vAxis: {format: 'decimal'}
    };

    var chart = new google.charts.Bar(document.getElementById('materialBarChartDiv'));

    chart.draw(data, google.charts.Bar.convertOptions(options));
}