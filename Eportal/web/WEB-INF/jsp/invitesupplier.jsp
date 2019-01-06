
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>Invite Supplier</title>

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

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>

    <body>

        <%@include file = "template.jsp" %>
        <div class="breadcrumbs" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="icon-book"></i><a href=""><span class="menu-text"> Supplier Management </span></a>
                    <span class="divider"><i class="icon-angle-right arrow-icon"></i></span>
                </li>
                <li class="active">Invite Supplier</li>						
            </ul>
        </div>
        <div class="page-content">					
            <div class="row-fluid">
                <div class="col-md-12">
                    <div class="widget-box">
                        <div class="widget-header widget-header-blue widget-header-flat">
                            <h4 class="lighter">Invite Supplier</h4>
                        </div>
                        <div class="widget-body">
                            <div class="widget-main">
                                <form class="form-vertical" id="invite-supplier-form" name="invite-supplier-form" method="post" action="">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                    <div class="row-fluid">
                                        <div class="span6">
                                            <div class="control-group">
                                                <label class="control-label" for="">Company Name<span style="color: red;"> *</span></label>
                                                <div class="controls">
                                                    <div class="">
                                                        <input type="text" name="companyname" id="companyname" class="span10" required/>
                                                    </div>
                                                </div>																
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row-fluid">
                                        <div class="span6">
                                            <div class="control-group">
                                                <label class="control-label" for="">Contact Person Email-Id<span style="color: red;"> *</span></label>
                                                <div class="controls">
                                                    <div class="">
                                                        <input type="text" name="contactpersonemail" id="contactpersonemail" class="span10" required/>
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
                                                        <input type="text" name="contactpersonname" id="contactpersonname" class="span10" required/>
                                                    </div>
                                                </div>																
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row-fluid">
                                        <div class="span4">
                                            <input type="submit" value="Invite" class="btn btn-success"/>
                                        </div>
                                    </div>
                                </form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>    

        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>				
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
        </script>
    </body>
</html>
