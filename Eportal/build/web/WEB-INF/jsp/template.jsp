
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="navbar">
    <div class="navbar-inner">
        <div class="container-fluid">
            <span class="pull-left"><img style="height: 45px;" src="images/logo.jpg"></span>
            <a href="#" class="brand"><small> Procurement</small></a>

            <ul class="nav ace-nav pull-right">

                <li class="light-blue">

                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                        <img class="nav-user-photo" src="avatars/user.jpg" alt="Jason's Photo" />
                        <span class="user-info"><small>Welcome,</small>${name}</span>
                        <i class="icon-caret-down"></i>
                    </a>

                    <ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-closer">
                        <%
                            if (session.getAttribute("userType").equals("Supplier")) {
                        %>
                        <li><a href="supplierdashboard.do"><i class="icon-home"></i>Home</a></li>
                            <%
                            } else {
                            %>
                        <li><a href="home.do"><i class="icon-home"></i>Home</a></li>
                            <%
                                }
                            %>
                        <li class="divider"></li>
                        <li><a href="mytask.do"><i class="icon-tasks"></i>My Task</a></li>
                        <li class="divider"></li>
                        <li><a href="logout.do"><i class="icon-off"></i>Logout</a></li>
                    </ul>
                </li>
            </ul>

            <ul class="nav ace-nav pull-right">
                <li class="light-blue">
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                        <!--<img class="nav-user-photo" src="images/notification.jpg" alt="Notification"></img>-->
                        <button type="button" class="btn btn-primary btn-small">Notification <span class="badge">${jsonObj.get('headers').length()}</span></button>

                    </a>
                    <ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-closer">
                        <li><a href="mytask.do">RFP101</a></li>
                        <li class="divider"></li>
                        <li><a href="mytask.do">RFP102</a></li>
                        <li class="divider"></li>
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
            <li class="active">
                <%
                    if (session.getAttribute("userType").equals("Supplier")) {
                %>
                <a href="supplierdashboard.do">
                    <i class="icon-dashboard"></i>
                    <span class="menu-text"> Home </span>
                </a>
                <%
                } else {
                %>
                <a href="home.do">
                    <i class="icon-dashboard"></i>
                    <span class="menu-text"> Home </span>
                </a>
                <%
                    }
                %>
            </li>
            <%
                if (!session.getAttribute("userType").equals("Approver") && !session.getAttribute("userType").equals("Supplier")) {
            %>
            <li>
                <a href="#" class="dropdown-toggle">
                    <i class="icon-desktop"></i><span class="menu-text"> RFP Management </span>
                    <b class="arrow icon-angle-down"></b>
                </a>
                <ul class="submenu">
                    <li>
                        <a href="createrfp.do"><i class="icon-double-angle-right"></i>Create RFP</a>
                    </li>
                    <li>
                        <a href="releaseRfp.do"><i class="icon-double-angle-right"></i>Release RFP</a>
                    </li>
                    <li>
                        <a href="rfpStatus.do"><i class="icon-double-angle-right"></i>Award RFP</a>
                    </li>
                </ul>
            </li>														
            <%
                }
            %>
            <li>
                <a href="mytask.do">
                    <i class="icon-tasks"></i>
                    <span class="menu-text"> My Task</span>
                </a>
            </li>
            <%
                if (session.getAttribute("userType").equals("SCM User")) {
            %>
            <li>
                <a href="rfpstatussummary.do">
                    <i class="icon-star"></i>
                    <span class="menu-text"> RFP Status</span>
                </a>
            </li>
            <%
                }
            %>
            <%
                if (!session.getAttribute("userType").equals("Approver") && !session.getAttribute("userType").equals("Supplier")) {
            %>
            <li>
                <a href="#" class="dropdown-toggle">
                    <i class="icon-book"></i><span class="menu-text"> Supplier Management </span>
                    <b class="arrow icon-angle-down"></b>
                </a>
                <ul class="submenu">
                    <li>
                        <a href="registeredSupplier.do"><i class="icon-double-angle-right"></i>Registered Suppliers</a>
                    </li>
                    <li>
                        <a href="newsupplier.do"><i class="icon-double-angle-right"></i>New Supplier Registration</a>
                    </li>
                    <li>
                        <a href="inviteSupplier.do"><i class="icon-double-angle-right"></i>Invite Supplier</a>
                    </li>
                </ul>
            </li>
            <%
                }
            %>
            <%
                if (session.getAttribute("userType").equals("SCM User")) {
            %>
            <li>
                <a href="#" class="dropdown-toggle">
                    <i class="icon-picture"></i>
                    <span class="menu-text"> Report Management </span>
                    <b class="arrow icon-angle-down"></b>
                </a>
                <ul class="submenu">
                    <li>
                        <a href="budgetComparison.do"><i class="icon-double-angle-right"></i>Budget Comparison</a>
                    </li>
                    
                </ul>
            </li>
            <%
                }
            %>
            <%
                if (!session.getAttribute("userType").equals("Approver")) {
            %>
            <li>
                <a href="responseManagement.do">
                    <i class="icon-text-width"></i>
                    <span class="menu-text"> Response Management </span>
                </a>
            </li>
            <%
                }
            %>
            <li>
                <a href="#">
                    <i class="icon-text-width"></i>
                    <span class="menu-text"> About Us </span>
                </a>
            </li>
            <!--                    <li>
                                    <a href="#">
                                        <i class="icon-text-width"></i>
                                        <span class="menu-text"> Contract Management </span>
                                    </a>
                                </li>								-->
        </ul>

        <div class="sidebar-collapse" id="sidebar-collapse">
            <i class="icon-double-angle-left"></i>
        </div>
    </div>
    <div class="main-content">
        <div class="breadcrumbs" id="breadcrumbs">
            <!--                    <ul class="breadcrumb">
                                    <li>
                                        <i class="icon-home home-icon"></i><a href="home.do">Home</a>							
                                    </li>						
                                </ul>-->
            <div class="nav-search" id="nav-search">
                <form class="form-search" />
                <span class="input-icon">
                    <input type="text" placeholder="Search ..." class="input-large nav-search-input" id="nav-search-input" autocomplete="off" />
                    <i class="icon-search nav-search-icon"></i>
                </span>
                </form>
            </div>
        </div>

