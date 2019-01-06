
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="description" content="User login page" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />		

        <!--<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js" type="text/javascript"></script>-->

        <script src="js/jquery.min.js"></script>
        <!--<link rel="stylesheet" href="https://cdn.datatables.net/1.10.18/css/jquery.dataTables.min.css" type="text/css"/>-->

        <!--<script src="https://cdn.datatables.net/1.10.18/js/jquery.dataTables.min.js" type="text/javascript"></script>-->


        <link href="css/bootstrap.min.css" rel="stylesheet" />
        <link href="css/bootstrap-responsive.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="css/font-awesome.min.css" />
        <!--<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />-->

        <link rel="stylesheet" href="css/googleapis-font.css" />
        <link rel="stylesheet" href="css/ace.min.css" />
        <link rel="stylesheet" href="css/ace-responsive.min.css" />
        <link rel="stylesheet" href="css/ace-skins.min.css" />
        <link rel="stylesheet" href="css/main.css" />

        <!--        <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/css/bootstrap-select.min.css" />
                <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/js/bootstrap-select.min.js"></script>-->

        <link rel="stylesheet" href="css/customer-notification.css" />
        <script src="js/customer-notification.js"></script>
        
        <script src="js/customer.js"></script>

        <script type="text/javascript">

        </script>
        <script>
            $(document).ready(function() {
                var set_image = $("#set_image").prop("src").toString().split(",");
                if (set_image[1] === "NotFound")
                {
                    $("#default_image_a").css("display", "block");
                    $("#set_image_a").css("display", "none");
                }
            });
        </script>
    </head>
    <body>

        <%@include file = "templatecustomer.jsp" %>
        <div class="breadcrumbs" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class=""></i><a href="customerresponse.do">Customers</a>
                    <span class="divider"><i class="icon-angle-right arrow-icon"></i></span>
                </li>
                <li class="active">Response Comment</li>					
            </ul>
            <div class="nav-search" id="nav-search">
                <a href="redirectcustomerhome.do"><h5><b>Go to Home</b></h5></a>
            </div>
        </div>
        <div class="page-content">					
            <!--            <ul class="nav nav-pills">
                            <li class="active"><a data-toggle="pill" href="#releaseRfp">Release RFP</a></li>
                            <li><a data-toggle="pill" href="#releasedRfp">Released RFP</a></li>
                        </ul>-->
            <!--<div class="tab-content">-->
            <!--<div id="releaseRfp" class="tab-pane fade in active">-->
            <div class="row-fluid">
                <div class="span8">
                    <form id="customer-comment-form" method="post" action="makecustomercomment.do" enctype="multipart/form-data">    
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        <input type="hidden" id="messageContent" name="messageContent">
                        <input type="hidden" id="supplierid" name="supplierid">
                        <div class="row-fluid">
                            <div class="span12">
                                <div class="widget-box">
                                    <div class="widget-header widget-header-small  header-color-blue"></div>
                                    <div class="widget-body">
                                        <div class="widget-main no-padding">
                                            <div class="wysiwyg-editor" id="editor2"></div>
                                        </div>						
                                    </div>
                                </div>
                                <div class="space-8"></div>
                                <input type="file" id="id-input-file-1" class="span10" name="file"/>	
                                <div class="space-10"></div>
                                <button class="btn btn-small btn-info" id="customer_post_btn">Send</button>
                            </div>
                        </div>

                    </form>
                    <div class="conversation">
                        <h4 class="smaller lighter green">Recent Activity</h4>
                        <hr>
                        <c:forEach var="notify" items="${notificationList}" varStatus="status">
                            <div class="itemdiv dialogdiv">
                                <div class="user">
                                    <img alt="Alexa's Avatar" src="${notify.commentby == 'Admin' ? 'images/avatar4.png' : 'images/avatar.png'}" />
                                </div>
                                <div class="body panel-shadow" style="background-color: ${notify.commentby == 'Admin' ? '#e6f7ff' : ''}">
                                    <div class="time" title="Comment Date"><span class="green"><fmt:formatDate value="${notify.commentdate}" pattern="dd-MM-yyyy HH:mm:ss"/></span></div>
                                    <div class="name"><a href="">${notify.commentby}</a></div>
                                    <div class="text">${notify.notification}</div>
                                    <div><a href="downloadcustomerconversationattachment.do?attmtId=${notify.id}">${notify.attachmentname}</a></div>
                                    <!--                                    <div class="tools"><a href="#" class="btn btn-minier btn-info"><i class="icon-only icon-share-alt"></i></a></div>-->
                                </div>
                            </div>
                        </c:forEach>
                    </div>


                </div>

            </div>
            <!--</div>-->                                        
            <!--</div>-->                                        
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
<script src="js/bootbox.min.js"></script>   
<script src="js/jquery.ui.touch-punch.min.js"></script>
<script src="js/jquery.slimscroll.min.js"></script>
<script src="js/ace-elements.min.js"></script>
<script src="js/ace.min.js"></script>	

<script src="js/markdown/markdown.min.js"></script>
<script src="js/markdown/bootstrap-markdown.min.js"></script>
<script src="js/jquery.hotkeys.min.js"></script>
<script src="js/bootstrap-wysiwyg.min.js"></script>	

<script type="text/javascript">
    $(function() {
        $('#editor2').css({'height': '200px'}).ace_wysiwyg({
            toolbar_place: function(toolbar) {
                return $(this).closest('.widget-box').find('.widget-header').prepend(toolbar);
            },
            toolbar:
                    [
                        'bold',
                        {name: 'italic', title: 'Change Title!', icon: 'icon-leaf'},
                        'strikethrough',
                        'underline',
                        null,
                        'insertunorderedlist',
                        'insertorderedlist',
                        null,
                        'justifyleft',
                        'justifycenter',
                        'justifyright'
                    ],
            speech_button: false
        });
        $('#input-attachment').ace_file_input({
            no_file: 'No File ...',
            btn_choose: 'Choose',
            btn_change: 'Change',
            droppable: false,
            onchange: null,
            thumbnail: false
        });
        $('#id-input-file-1').ace_file_input({
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
