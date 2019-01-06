
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="JSON.JSONObject, JSON.JSONArray,JSON.JSONException" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
        <!--<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />-->		

        <link rel="stylesheet" href="css/googleapis-font.css" />
        <link rel="stylesheet" href="css/ace.min.css" />
        <link rel="stylesheet" href="css/ace-responsive.min.css" />
        <link rel="stylesheet" href="css/ace-skins.min.css" />
        <link rel="stylesheet" href="css/main.css" />

        <link rel="stylesheet" href="css/loader.css" />



        <!--<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js" type="text/javascript"></script>-->

        <!--<link rel="stylesheet" href="https://cdn.datatables.net/1.10.18/css/jquery.dataTables.min.css" type="text/css"/>-->

        <!--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">-->

        <!--<script src="https://cdn.datatables.net/1.10.18/js/jquery.dataTables.min.js" type="text/javascript"></script>-->
        <!--        
                <script src="js/index.js"></script>-->
        <script src="js/jquery.min.js"></script>
        <link rel="stylesheet" href="css/font-awesome2.min.css" />

        <script src="js/customerdashboard.js"></script>

        <link rel="stylesheet" href="css/customer-notification.css" />
        <script src="js/customer-notification.js"></script>

        <!--<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>-->

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />


        <script type="text/javascript">
            $(document).ready(function() {
                $("#nextLinkId").click();

                var set_image = $("#set_image").prop("src").toString().split(",");
                if (set_image[1] === "NotFound")
                {
                    $("#default_image_a").css("display", "block");
                    $("#set_image_a").css("display", "none");
                }

//                $("#overlay").css("display", "none");
            });
            // Load google charts
//            google.charts.load('current', {packages: ['corechart', 'bar']});
//            google.charts.setOnLoadCallback(drawChart);


            // Draw the chart and set the chart values
            function drawChart() {
                //                var total = document.getElementById('totalRFP').value;
                //                var approved = document.getElementById('approvedRFP').value;
                //                var pending = document.getElementById('pendingRFP').value;
                //                var released = document.getElementById('releasedRFP').value;
                //                var awarded = document.getElementById('awardedRFP').value;
                //                console.log(total);
                //                console.log(approved);
                //                console.log(pending);
//
//                var data = google.visualization.arrayToDataTable([
//                    ['Status', 'Count'],
//                    ['Assigned', 150],
//                    ['On hold', 100],
//                    ['Unpaid', 200],
//                    ['Overdue', 300],
//                    ['Total', 750]
//                ]);
//
//                var materialOptions = {
//                    chart: {
//                    },
//                    height: 200,
//                    width: 500,
//                    bars: 'vertical',
//                    bar: {groupWidth: '50%'}
//                };
//                var materialChart = new google.charts.Bar(document.getElementById('piechart'));
//                materialChart.draw(data, materialOptions);
            }
        </script>
        <style>
            .hoverCSS:hover {
                border-color: whitesmoke;
                border-width: 2px;
            }

            .button__badge {
                background-color: #fa3e3e;
                border-radius: 2px;
                color: white;

                padding: 1px 3px;
                font-size: 10px;

                position: absolute; /* Position the badge within the relatively positioned button */
                top: 0;
                right: 0;
            }
            .carousel-height {
                height: 300px;
            }
            .card-style-admin {
                width: 140px;
                height: 180px;
            }
            .span1-style-admin {
                width: 8.502906% !important;
            }
            .card-style-user {
                width: 160px;
                height: 180px;
            }
            .span1-style-user {
                width: 22.502906% !important;
            }
            .card-style-subadmin {
                width: 133px;
                height: 180px;
            }
            .span1-style-subadmin {
                width: 17.502906% !important;
            }
        </style>
    </head>

    <body>

        <%@include file = "templatecustomer.jsp" %>

        <!--        <div id="overlay">
                    <div id="loader"></div>
                </div>-->


        <div class="page-content">
            <input type="hidden" value="${ispassupdated}" id="ispasswordupdated">
            <input type="hidden" name="dmsip" id="dmsip" value="${dmsip}">
            <input type="hidden" name="docindex" id="docindex" value="${docindex}">
            <input type="hidden" value="${isPersonalInfoUpdated}" id="isPersonalInfoUpdated">
            <input type="hidden" id="userRole" value="${userRole}">    

            <div class="row-fluid">
                <div class="span12 align-center">
                    <image id="" class="img-rounded panel-shadow" src="data:image/jpg;base64,${headerImage}" width="100%" style="height: 120px;"/>

                </div>
            </div>
            <div class="space"></div>

            <%                    if (session.getAttribute("userRole").equals("Admin")) {
            %>

            <div class="row-fluid">
                <div class="span1 span1-style-admin">
                    <!--<a href="showsearchdocument.do" title="My Orders">-->
                    <div class="card panel-shadow card-style-admin">
                        <br><i class="fa fa-list-alt fa-4x align-center"></i>
                        <!--<span class="button__badge">15</span>-->
                        <div class="card-body">
                            <h5 class="card-title align-center"><b><a href="customermyorders.do">My <br>Projects</a></b></h5>
                        </div>
                    </div>
                    <!--</a>-->
                </div>
                <!--<div class="span1"></div>-->

                <div class="span1 span1-style-admin">
                    <!--<a title="My Documents" href="showsearchdocument.do">-->
                    <div class="card panel-shadow card-style-admin">
                        <br><i class="fa fa-file-text fa-4x align-center"></i>
                        <div class="card-body">
                            <h5 class="card-title align-center"><b><a href="showsearchdocument.do">Project <br>Documents</a></b></h5>
                        </div>
                    </div>
                    <!--</a>-->
                </div>
                <!--<div class="span1"></div>-->

                <div class="span1 span1-style-admin">
                    <!--<a title="My Statements" href="customerstatement.do">-->
                    <div class="card panel-shadow card-style-admin">
                        <br><i class="fa fa-book fa-4x align-center"></i>
                        <div class="card-body">
                            <h5 class="card-title align-center"><b><a href="customerstatement.do">My <br>Statements</a></b></h5>
                        </div>
                    </div>
                    <!--</a>-->
                </div>
                <!--<div class="span1"></div>-->

                <div class="span1 span1-style-admin">
                    <!--<a title="Portal Guide">-->
                    <div class="card panel-shadow card-style-admin">
                        <br><i class="fa fa-file-text fa-4x align-center"></i>
                        <div class="card-body">
                            <h5 class="card-title align-center"><b><a href="customercontractdocument.do">Customer <br>Document</a></b></h5>
                        </div>
                    </div>
                    <!--</a>-->
                </div>

                <div class="span1 span1-style-admin">
                    <div class="card panel-shadow card-style-admin">
                        <br><i class="fa fa-envelope fa-4x align-center"></i>
                        <div class="card-body">
                            <h5 class="card-title align-center"><b><a href="customerresponse.do">Response <br>Management</a></b></h5>
                        </div>
                    </div>
                </div>

                <div class="span1 span1-style-admin">
                    <div class="card panel-shadow card-style-admin">
                        <br><i class="fa fa-user fa-4x align-center"></i>
                        <div class="card-body">
                            <h5 class="card-title align-center"><b><a href="manageuser.do">Manage <br>User</a></b></h5>
                        </div>
                    </div>
                </div>

                <div class="span1 span1-style-admin">
                    <div class="card panel-shadow card-style-admin">
                        <br><i class="fa fa-users fa-4x align-center"></i>
                        <div class="card-body">
                            <h5 class="card-title align-center"><b><a href="managegroup.do">Manage <br>Group</a></b></h5>
                        </div>
                    </div>
                </div>
                <!--<div class="span1"></div>-->

                <div class="span1 span1-style-admin">
                    <div class="card panel-shadow card-style-admin">
                        <br><i class="fa fa-key fa-4x align-center"></i>
                        <div class="card-body">
                            <h5 class="card-title align-center"><b><a href="customerpasswordconfig.do">Password <br>Configuration</a></b></h5>
                        </div>
                    </div>
                </div>

                <div class="span1 span1-style-admin">
                    <!--<a title="Edit Company Profile">-->
                    <div class="card panel-shadow card-style-admin">
                        <br><i class="fa fa-pencil-square-o fa-4x align-center"></i>
                        <div class="card-body">
                            <h5 class="card-title align-center"><b><a href="customereditprofile.do">Edit <br>Customer Profile</a></b></h5>
                        </div>
                    </div>
                    <!--</a>-->
                </div>

            </div>
            <%                    }
            %>

            <div class="space"></div>

            <%                    if (session.getAttribute("userRole").equals("Company Admin")) {
            %>
            <div class="row-fluid">
                <div class="span8">
                    <div class="row-fluid">
                        <div class="span1 span1-style-subadmin">
                            <!--<a href="showsearchdocument.do" title="My Orders">-->
                            <div class="card panel-shadow card-style-subadmin">
                                <br><i class="fa fa-list-alt fa-4x align-center"></i>
                                <!--<span class="button__badge">15</span>-->
                                <div class="card-body">
                                    <h5 class="card-title align-center"><b><a href="customermyorders.do">My <br>Projects</a></b></h5>
                                </div>
                            </div>
                            <!--</a>-->
                        </div>
                        <!--<div class="span1"></div>-->

                        <div class="span1 span1-style-subadmin">
                            <!--<a title="My Documents" href="showsearchdocument.do">-->
                            <div class="card panel-shadow card-style-subadmin">
                                <br><i class="fa fa-file-text fa-4x align-center"></i>
                                <div class="card-body">
                                    <h5 class="card-title align-center"><b><a href="showsearchdocument.do">Project <br>Documents</a></b></h5>
                                </div>
                            </div>
                            <!--</a>-->
                        </div>
                        <!--<div class="span1"></div>-->

                        <div class="span1 span1-style-subadmin">
                            <!--<a title="My Statements" href="customerstatement.do">-->
                            <div class="card panel-shadow card-style-subadmin">
                                <br><i class="fa fa-book fa-4x align-center"></i>
                                <div class="card-body">
                                    <h5 class="card-title align-center"><b><a href="customerstatement.do">My <br>Statements</a></b></h5>
                                </div>
                            </div>
                            <!--</a>-->
                        </div>
                        <!--<div class="span1"></div>-->

                        <div class="span1 span1-style-subadmin">
                            <!--<a title="Portal Guide">-->
                            <div class="card panel-shadow card-style-subadmin">
                                <br><i class="fa fa-file-text fa-4x align-center"></i>
                                <div class="card-body">
                                    <h5 class="card-title align-center"><b><a href="customercontractdocument.do">Customer <br>Document</a></b></h5>
                                </div>
                            </div>
                            <!--</a>-->
                        </div>
                        
                        <div class="span1 span1-style-subadmin">
                            <div class="card panel-shadow card-style-subadmin">
                                <br><i class="fa fa-envelope fa-4x align-center"></i>
                                <div class="card-body">
                                    <h5 class="card-title align-center"><b><a href="customerresponse.do">Response <br>Management</a></b></h5>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="space"></div>
                    <div class="row-fluid">
                        

                        <div class="span1 span1-style-subadmin">
                            <div class="card panel-shadow card-style-subadmin">
                                <br><i class="fa fa-users fa-4x align-center"></i>
                                <div class="card-body">
                                    <h5 class="card-title align-center"><b><a href="managegroup.do">Manage <br>Group</a></b></h5>
                                </div>
                            </div>
                        </div>
                        <!--<div class="span1"></div>-->
                        <div class="span1 span1-style-subadmin">
                            <div class="card panel-shadow card-style-subadmin">
                                <br><i class="fa fa-user fa-4x align-center"></i>
                                <div class="card-body">
                                    <h5 class="card-title align-center"><b><a href="manageuser.do">Manage <br>User</a></b></h5>
                                </div>
                            </div>
                        </div>


                        <div class="span1 span1-style-subadmin">
                            <!--<a title="Edit Company Profile">-->
                            <div class="card panel-shadow card-style-subadmin">
                                <br><i class="fa fa-pencil-square-o fa-4x align-center"></i>
                                <div class="card-body">
                                    <h5 class="card-title align-center"><b><a href="customereditprofile.do">Edit <br>Customer Profile</a></b></h5>
                                </div>
                            </div>
                            <!--</a>-->
                        </div>
                        
                        <div class="span1 span1-style-subadmin">
                            <!--<a title="Terms and Conditions">-->
                            <div class="card panel-shadow card-style-subadmin">
                                <br><i class="fa fa-book fa-4x align-center"></i>
                                <div class="card-body">
                                    <h5 class="card-title align-center"><b><a href="#" onclick="DownloadDoc();">Terms <br>and Conditions</a></b></h5>
                                </div>
                            </div>
                            <!--</a>-->
                        </div>
                    </div>
                    
                </div>
                <div class="span4">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            Notification
                        </div>
                        <div class="panel-body">
                            <h4 class="hidden">No New Notifications</h4>
                            <marquee style="height:300px;color:black;" direction="up" scrollamount="3" onmouseover="this.stop()" onmouseout="this.start()">
                                <input type="hidden" value="${NotificationList.size()}" name="notification_count">
                                <c:forEach var="notification" items="${NotificationList}" varStatus="status">
                                    <div class="itemdiv dialogdiv">
                                        <div class="user">
                                            <div class="setDate center"><h4>${status.count}</h4></div>
                                        </div>
                                        <div class="body">								
                                            <div class="time"><span class="green"><fmt:formatDate value="${notification.commentdate}" pattern="dd-MM-yyyy HH:mm:ss"/></span></div>					
                                            <div class="name">${notification.ngCpCustomersubuserAdmin.personalfirstname} ${notification.ngCpCustomersubuserAdmin.personallastname}</div>
                                            <div class="text"><input type="hidden" value="${notification.id}"><a href="customerresponsemgnt.do?supplierid=${notification.bpaasCustomersubuserId.id}">${notification.notification}</a></div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </marquee>
                        </div>
                    </div>
                </div>
            </div>
            <%
                }
            %>

            <%                    if (!session.getAttribute("userRole").equals("Admin") && !session.getAttribute("userRole").equals("Company Admin")) {
            %>

            <div class="row-fluid">
                <div class="span8">
                    <div class="row-fluid">    
                        <div class="span1 span1-style-user">
                            <!--<a href="showsearchdocument.do" title="My Orders">-->
                            <div class="card panel-shadow card-style-user">
                                <br><i class="fa fa-list-alt fa-4x align-center"></i>
                                <!--<span class="button__badge">15</span>-->
                                <div class="card-body">
                                    <h5 class="card-title align-center"><b><a href="customermyorders.do">My <br>Projects</a></b></h5>
                                </div>
                            </div>
                            <!--</a>-->
                        </div>
                        <!--<div class="span1"></div>-->

                        <div class="span1 span1-style-user">
                            <!--<a title="My Documents" href="showsearchdocument.do">-->
                            <div class="card panel-shadow card-style-user">
                                <br><i class="fa fa-file-text fa-4x align-center"></i>
                                <div class="card-body">
                                    <h5 class="card-title align-center"><b><a href="showsearchdocument.do">Project <br>Documents</a></b></h5>
                                </div>
                            </div>
                            <!--</a>-->
                        </div>
                        <!--<div class="span1"></div>-->

                        <div class="span1 span1-style-user">
                            <!--<a title="My Statements" href="customerstatement.do">-->
                            <div class="card panel-shadow card-style-user">
                                <br><i class="fa fa-book fa-4x align-center"></i>
                                <div class="card-body">
                                    <h5 class="card-title align-center"><b><a href="customerstatement.do">My <br>Statements</a></b></h5>
                                </div>
                            </div>
                            <!--</a>-->
                        </div>
                        <!--<div class="span1"></div>-->

                        <div class="span1 span1-style-user">
                            <!--<a title="Portal Guide">-->
                            <div class="card panel-shadow card-style-user">
                                <br><i class="fa fa-file-text fa-4x align-center"></i>
                                <div class="card-body">
                                    <h5 class="card-title align-center"><b><a href="customercontractdocument.do">Customer <br>Document</a></b></h5>
                                </div>
                            </div>
                            <!--</a>-->
                        </div>
                    </div>
                    <div class="space"></div>
                    <!--<div class="span2"></div>-->
                    <div class="row-fluid">
                        <div class="span1 span1-style-user">
                            <!--<a title="Terms and Conditions">-->
                            <div class="card panel-shadow card-style-user">
                                <br><i class="fa fa-book fa-4x align-center"></i>
                                <div class="card-body">
                                    <h5 class="card-title align-center"><b><a href="#" onclick="DownloadDoc();">Terms <br>and Conditions</a></b></h5>
                                </div>
                            </div>
                            <!--</a>-->
                        </div>
                        <!--<div class="span1"></div>-->

                        <div class="span1 span1-style-user">
                            <!--<a title="Edit Company Profile">-->
                            <div class="card panel-shadow card-style-user">
                                <br><i class="fa fa-pencil-square-o fa-4x align-center"></i>
                                <div class="card-body">
                                    <h5 class="card-title align-center"><b><a href="customereditprofile.do">Edit <br>Customer Profile</a></b></h5>
                                </div>
                            </div>
                            <!--</a>-->
                        </div>
                        <!--<div class="span1"></div>-->

                        <div class="span1 span1-style-user">
                            <div class="card panel-shadow card-style-user">
                                <br><i class="fa fa-envelope fa-4x align-center"></i>
                                <div class="card-body">
                                    <h5 class="card-title align-center"><b><a href="customerresponse.do">Response <br>Management</a></b></h5>
                                </div>
                            </div>
                        </div>
                        <!--<div class="span1"></div>-->
                    </div>
                </div>
                <div class="span4">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            Notification
                        </div>
                        <div class="panel-body">
                            <h4 class="hidden">No New Notifications</h4>
                            <marquee style="height:300px;color:black;" direction="up" scrollamount="3" onmouseover="this.stop()" onmouseout="this.start()">
                                <input type="hidden" value="${NotificationList.size()}" name="notification_count">
                                <c:forEach var="notification" items="${NotificationList}" varStatus="status">
                                    <div class="itemdiv dialogdiv">
                                        <div class="user">
                                            <div class="setDate center"><h4>${status.count}</h4></div>
                                        </div>
                                        <div class="body">								
                                            <div class="time"><span class="green"><fmt:formatDate value="${notification.commentdate}" pattern="dd-MM-yyyy HH:mm:ss"/></span></div>					
                                            <div class="name">${notification.ngCpCustomersubuserAdmin.personalfirstname} ${notification.ngCpCustomersubuserAdmin.personallastname}</div>
                                            <div class="text"><input type="hidden" value="${notification.id}"><a href="customerresponsemgnt.do?supplierid=${notification.bpaasCustomersubuserId.id}">${notification.notification}</a></div>
                                        </div>
                                    </div>
                                </c:forEach>

                            </marquee>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <%                    }
        %>

        <%                    if (session.getAttribute("userRole").equals("Admin") || session.getAttribute("userRole").equals("Company Admin")) {
        %>
        <!--            <div class="row-fluid">
                        <div class="span2">
                            <div class="card panel-shadow">
                                <br><i class="fa fa-envelope fa-4x align-center"></i>
                                <div class="card-body">
                                    <h5 class="card-title align-center"><b><a href="customerresponse.do">Response <br>Management</a></b></h5>
                                </div>
                            </div>
                        </div>
                        <div class="span1"></div>
        
                        <div class="span2">
                            <div class="card panel-shadow">
                                <br><i class="fa fa-user fa-4x align-center"></i>
                                <div class="card-body">
                                    <h5 class="card-title align-center"><b><a href="manageuser.do">Manage <br>User</a></b></h5>
                                </div>
                            </div>
                        </div>
                        <div class="span1"></div>
        
        
                        <div class="span2">
                            <div class="card panel-shadow">
                                <br><i class="fa fa-users fa-4x align-center"></i>
                                <div class="card-body">
                                    <h5 class="card-title align-center"><b><a href="managegroup.do">Manage <br>Group</a></b></h5>
                                </div>
                            </div>
                        </div>
                        <div class="span1"></div>
        
                        <div class="span2">
                            <div class="card panel-shadow">
                                <br><i class="fa fa-key fa-4x align-center"></i>
                                <div class="card-body">
                                    <h5 class="card-title align-center"><b><a href="customerpasswordconfig.do">Password <br>Configuration</a></b></h5>
                                </div>
                            </div>
                        </div>
                    </div>-->
        <%                    }
        %>

        <div class="space"></div>

        <!--            <div class="row-fluid">
                        <div class="span12 align-center">
                            <image class="img-rounded panel-shadow" src="images/natsteel_home.jpg" width="100%"/>
        
                        </div>
                    </div>-->

        <div class="row-fluid">
            <div class="span12 align-center">
                <div id="myCarousel" class="carousel slide" data-ride="carousel">
                    <!-- Indicators -->
                    <ol class="carousel-indicators">
                        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                        <li data-target="#myCarousel" data-slide-to="1"></li>
                        <li data-target="#myCarousel" data-slide-to="2"></li>
                    </ol>

                    <!-- Wrapper for slides -->
                    <div class="carousel-inner carousel-height">
                        <div class="item active">
                            <img src="data:image/jpg;base64,${carouse1Image1}" alt="" style="width:100%;">
                        </div>

                        <div class="item">
                            <img src="data:image/jpg;base64,${carouse1Image2}" alt="" style="width:100%;">
                        </div>

                        <div class="item">
                            <img src="data:image/jpg;base64,${carouse1Image3}" alt="" style="width:100%;">
                        </div>
                    </div>

                    <!-- Left and right controls -->
                    <a class="left carousel-control" href="#myCarousel" data-slide="prev" title="Previous">
                        <span class="glyphicon glyphicon-chevron-left"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="right carousel-control" href="#myCarousel" data-slide="next" title="Next" id="nextLinkId">
                        <span class="glyphicon glyphicon-chevron-right"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>

            </div>
        </div>

        <div id="modalDivId" class="hidden">
            <div class="modal fade" id="updatePasswordAtFirst" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <!--<button type="button" class="close" data-dismiss="modal">&times;</button>-->
                            <h4 class="modal-title">Please Update Your Password</h4>
                        </div>
                        <div class="modal-body">
                            <form id="updatePasswordForm" class="form-control" method="post" action="updatepass.do">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                <!--                                <div class="space"></div>-->
                                <input type="hidden" id="passconfig" name="passconfig" value="${passconfig}">    
                                <div class="row-fluid">
                                    <div class="span12">
                                        <div class="control-group">
                                            <label class="control-label" for="newpassword" style="color: black;">New Password</label>
                                            <div class="controls">
                                                <div class="">
                                                    <input type="password" name="newpassword" id="newpassword" style="border-radius: 20px !important;" class="span12 form-field" required/>
                                                </div>
                                            </div>																
                                        </div>
                                    </div>
                                </div>

                                <div class="row-fluid">
                                    <div class="span12">
                                        <div class="control-group">
                                            <label class="control-label" for="confirmpassword" style="color: black;">Confirm Password</label>
                                            <div class="controls">
                                                <div class="">
                                                    <input type="password" name="confirmpassword" id="confirmpassword" style="border-radius: 20px !important;" class="span12 form-field" required/>
                                                </div>
                                            </div>																
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" id="updatePasswordSubmitBtn" class="btn btn-primary">Submit</button>
                            <!--<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>-->
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
<script type="text/javascript">
    if ("ontouchend" in document)
        document.write("<script src='js/jquery.mobile.custom.min.js'>" + "<" + "/script>");
</script>
<script src="js/bootstrap.min.js"></script>		
<script src="js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="js/jquery.ui.touch-punch.min.js"></script>
<script src="js/jquery.slimscroll.min.js"></script>
<script src="js/ace-elements.min.js"></script>
<script src="js/ace.min.js"></script>	

<script type="text/javascript">
    $('.dialogs,.comments').slimScroll({
        height: '200px'
    });
    function DownloadDoc() {
        console.log("Inside Download Sales Terms & Conditions Doc");

        var docindex = $("#docindex").val();
        console.log("docindex: " + docindex);

        var xmlInput = "%3CInputCriteria%3E";

        xmlInput += "%3CDocumentDetails%3E";
        xmlInput += "%3CDocumentIndex%3E" + docindex + "%3C/DocumentIndex%3E";
        xmlInput += "%3C/DocumentDetails%3E";

        xmlInput += "%3C/InputCriteria%3E";
        console.log("Input XML for Contract Document:" + xmlInput);

        var dmsip = $("#dmsip").val();

//        var URLParam = 'InputXML=' + xmlInput + '&RequestType=download&ViewOption=N';
        var URLParam = 'InputXML=' + docindex + '&RequestType=download&ViewOption=N';

        console.log("URLParam: " + URLParam);

        var serviceUrl = dmsip + "/WebServiceCall/CallWebService";
        console.log("serviceUrl: " + serviceUrl);

        window.open(dmsip + "/WebServiceCall/Download?" + URLParam, "_blank");

//        CustomerPortalapp/ng/search/download

//        $.ajax({
//            type: "POST",
//            url: serviceUrl,
//            contentType: "application/x-www-form-urlencoded; charset=utf-8",
//            dataType: "xml",
//            data: URLParam,
//            success: function(data, textStatus, jqXHR) {
//                console.log("success: " + data);
//                DownloadDocLink(data);
//            }
//        });
    }
    function DownloadDocLink(oXML) {
        console.log("Inside parse the Download XMl:" + oXML);
        var xmlString = XMLToString(oXML);
        var xmlDoc = $.parseXML(xmlString); //Parse the XML String to get data
        var $xml = $(xmlDoc);
        var $DownloadURL = $xml.find('DownloadURL');
        var DownloadDocURL = $DownloadURL.text();
        console.log("Parsed Download URL:" + DownloadDocURL);
        window.open(DownloadDocURL);
    }

    function XMLToString(oXML)
    {
        console.log("Inside XMLToString value");
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

</script>
</body>
</html>
