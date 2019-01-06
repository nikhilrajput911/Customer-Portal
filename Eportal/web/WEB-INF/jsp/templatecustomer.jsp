
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="navbar">
    <div class="navbar-inner">
        <div class="container-fluid">
            <!--<span class="pull-left"><img style="height: 45px;" src="images/logo.jpg"></span>-->
            <!--<a href="#" class="brand"><small> Procurement</small></a>-->

            <ul class="nav ace-nav pull-right">

                <li class="light-blue">

                    <a data-toggle="dropdown" href="#" class="dropdown-toggle" style="display: none;" id="default_image_a">
                        <img class="nav-user-photo" src="avatars/user.jpg" alt="Jason's Photo" id="default_image"/>

                        <span class="user-info" style="font-size: 12px;">${fullname}</span>
                        <i class="icon-caret-down"></i>
                    </a>

                    <a data-toggle="dropdown" href="#" class="dropdown-toggle" id="set_image_a">
                        <img class="nav-user-photo" src="data:image/jpg;base64,${profilePic}" alt="Jason's Photo" id="set_image"/>

                        <span class="user-info" style="font-size: 12px;">${fullname}</span>
                        <i class="icon-caret-down"></i>
                    </a>

                    <ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-closer">
                        <li><a href="redirectcustomerhome.do"><i class="icon-home"></i>Home</a></li>
                        <li class="divider"></li>
                        <li><a href="customerlogout.do"><i class="icon-off"></i>Logout</a></li>
                    </ul>
                </li>
            </ul>


            <ul class="nav ace-nav pull-right">
                <li class="light-blue">
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                        <!--<span class="button__badge">15</span-->
                        <span class="badge">${NotificationList.size()}</span>
                    </a>
                    <ul class="sub-menu user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-closer">
                        <!--                        <li><a href="mytask.do">RFP101</a></li>
                                                <li class="divider"></li>-->

                        <c:forEach var="notification" items="${NotificationList}" varStatus="status">
                            <li name="notification-list"><input type="hidden" value="${notification.id}"><a href="customerresponsemgnt.do?supplierid=${notification.bpaasCustomersubuserId.id}"><span class="notification-content"><b>${notification.bpaasCustomersubuserId.fisrtname} ${notification.bpaasCustomersubuserId.lastname}:</b> 1 unread message</span></a></li>
                            <li class="divider"></li>
                            </c:forEach>
                    </ul>
                </li>
            </ul>

        </div>
    </div>
</div>

<div class="main-container container-fluid">
    <a class="menu-toggler" id="menu-toggler" href="#"><span class="menu-text"></span></a>

    <div class="sidebar" id="sidebar">				
        <ul class="nav nav-list">

        </ul>

        <!--        <div class="sidebar-collapse" id="sidebar-collapse">
                    <i class="icon-double-angle-left"></i>
                </div>-->
    </div>

    <div class="main-content">
        <div class="breadcrumbs" id="breadcrumbs">
            <ul class="breadcrumb nav-search">
                <li>
                    <a href="https://www.natsteel.com.sg/index.php/about-natsteel" target="_blank"><h5><b>About NatSteel</b></h5></a>
                    <a href="downloadcustomerusermanual.do"><h5><b>Help</b></h5></a>
                                <%
                                    if (session.getAttribute("userRole").equals("Admin")) {
                                %>    
                    <a href="customerreport.do"><h5><b>Report</b></h5></a>
                                <%
                                    }
                                %>
                                <%
                                    if (session.getAttribute("userRole").equals("Admin")) {
                                %>    
                    <a href="customerdashboardconfiguration.do"><h5><b>Images</b></h5></a>
                                <%
                                    }
                                %>
                                <%
                                    if (session.getAttribute("userRole").equals("Admin")) {
                                %>    
                    <a href="termsandcondition.do"><h5><b>Terms and Condition</b></h5></a>
                                <%
                                    }
                                %>
                                <%
                                    if (session.getAttribute("userRole").equals("Admin")) {
                                %>    
                    <a href="customerautomailconfiguration.do"><h5><b>Auto Mail Configuration</b></h5></a>
                                <%
                                    }
                                %>

                </li>						
            </ul>

        </div>


