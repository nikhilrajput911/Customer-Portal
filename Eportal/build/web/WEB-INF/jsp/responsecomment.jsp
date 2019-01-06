
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="description" content="User login page" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />		

        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js" type="text/javascript"></script>

        <!--<link rel="stylesheet" href="https://cdn.datatables.net/1.10.18/css/jquery.dataTables.min.css" type="text/css"/>-->

        <!--<script src="https://cdn.datatables.net/1.10.18/js/jquery.dataTables.min.js" type="text/javascript"></script>-->


        <link href="css/bootstrap.min.css" rel="stylesheet" />
        <link href="css/bootstrap-responsive.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="css/font-awesome.min.css" />
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />

        <link rel="stylesheet" href="css/ace.min.css" />
        <link rel="stylesheet" href="css/ace-responsive.min.css" />
        <link rel="stylesheet" href="css/ace-skins.min.css" />
        <link rel="stylesheet" href="css/main.css" />

        <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/css/bootstrap-select.min.css" />
        <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/js/bootstrap-select.min.js"></script>

        <script src="js/response-management.js"></script>

        <script type="text/javascript">

        </script>

    </head>
    <body>

        <%@include file = "template.jsp" %>
        <div class="breadcrumbs" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="icon-home home-icon"></i><a href="responseManagement.do">Response Management</a>
                    <span class="divider"><i class="icon-angle-right arrow-icon"></i></span>
                </li>
                <li class="active">Response Comment</li>						
            </ul>
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
                    <form id="comment-form" method="post" action="makeComment.do" enctype="multipart/form-data">    
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
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
                                <button class="btn btn-small btn-info" id="post_comment_btn">Send</button>
                            </div>
                        </div>
                        <input type="hidden" id="messageContent" name="messageContent">
                        <input type="hidden" id="rfqid" name="rfqid">
                        <input type="hidden" id="supplierid" name="supplierid">
                    </form>
                    <div class="conversation">
                        <h4 class="smaller lighter green">Recent Activity</h4>
                        <hr>
                        <c:forEach var="que" items="${map.question}" varStatus="status">
                            <div class="itemdiv dialogdiv">
                                <div class="user">
                                    <img alt="Alexa's Avatar" src="${que.type == 'Answer' ? 'images/avatar4.png' : 'images/avatar.png'}" />
                                </div>
                                <div class="body" style="background-color: ${que.type == 'Answer' ? '#e6f7ff' : ''}">
                                    <div class="time" title="Comment Date"><span class="green">${que.questiondate} ago</span></div>
                                    <div class="name"><a href="">${que.initiatedby}</a></div>
                                    <div class="text">${que.askquestion}</div>
                                    <div><a href="downloadConversationAttachment.do?attmtId=${que.id}">${que.attachmentdescription}</a></div>
                                    <!--                                    <div class="tools"><a href="#" class="btn btn-minier btn-info"><i class="icon-only icon-share-alt"></i></a></div>-->
                                </div>
                            </div>
                        </c:forEach>    
                    </div>


                </div>
                <div class="span4">
                    <div class="personal-info">
                        <h4 class="smaller lighter green">RFP Details</h4>
                        <hr>
                        <div class="profile-user-inf profile-user-info-striped">
                            <div class="profile-info-row">
                                <div class="profile-info-name"> RFP Id </div>
                                <div class="profile-info-value">
                                    <span class=""><a id="rfpDetailsLink">RFP_${rfpHeader.rfqid}</a></span>
                                </div>
                            </div>
                            <div class="profile-info-row">
                                <div class="profile-info-name"> RFP Title </div>
                                <div class="profile-info-value">											
                                    <span class="">${rfpHeader.RFQTitle}</span>											
                                </div>
                            </div>
                            <div class="profile-info-row">
                                <div class="profile-info-name"> Time Left </div>
                                <div class="profile-info-value">
                                    <span class="">${rfpHeader.timeleft}</span>
                                </div>
                            </div>
                            <div class="profile-info-row">
                                <div class="profile-info-name"> Status </div>
                                <div class="profile-info-value">
                                    <span class="">${rfpHeader.rfqstatus}</span>
                                </div>
                            </div>
                            <div class="profile-info-row">
                                <div class="profile-info-name"> Supplier </div>
                                <div class="profile-info-value">
                                    <span class="">${supplier.companyname}</span>
                                </div>
                            </div>										
                        </div>
                    </div>
                    <div class="space-10"></div>
                    <div class="personal-info">
                        <h4 class="smaller lighter green">Requester Info</h4>
                        <hr>
                        <div class="center profile-user-info-striped">
                            <div class="profile-info-row">
                                <div class="profile-info-name labelName"><i class="icon-user icon"></i></div>			
                                <div class="profile-info-value labelValue">${rfpHeader.userDetailuserid.username}</div>
                            </div>									
                            <div class="profile-info-row">
                                <div class="profile-info-name labelName"><i class="icon-envelope icon"></i></div>			
                                <div class="profile-info-value labelValue">${rfpHeader.userDetailuserid.workemailid}</div>
                            </div>
                            <div class="profile-info-row">
                                <div class="profile-info-name labelName"><i class="icon-phone icon"></i></div>
                                <div class="profile-info-value labelValue">${rfpHeader.userDetailuserid.contactnumber}</div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
            <!--</div>-->                                        
            <!--</div>-->                                        
        </div>	
        <div class="modal fade" id="rfpDetailsModal" role="dialog">
            <div class="modal-dialog modal-sm">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">RFP Detail</h4>
                    </div>
                    <div class="modal-body">
                        <form>
                            <div class="row-fluid">
                                <div class="span6">
                                    <div class="control-group">
                                        <label class="control-label" for=""><b>RFQ Title</b></label>
                                        <label>${rfpHeader.RFQTitle}</label>

                                    </div>
                                </div>
                                <div class="span6">
                                    <div class="control-group">
                                        <label class="control-label" for=""><b>RFQ Status</b></label>
                                        <label>${rfpHeader.rfqstatus}</label>

                                    </div>
                                </div>        
                            </div>
                            <div class="row-fluid">
                                <div class="span6">
                                    <div class="control-group">
                                        <label class="control-label" for=""><b>Time Left</b></label>
                                        <label>${rfpHeader.timeleft}</label>

                                    </div>
                                </div>
                                <div class="span6">
                                    <div class="control-group">
                                        <label class="control-label" for=""><b>Creation Date</b></label>
                                        <label>${rfpHeader.creationdate}</label>

                                    </div>
                                </div>        
                            </div>
                            <div class="row-fluid">
                                <div class="span6">
                                    <div class="control-group">
                                        <label class="control-label" for=""><b>Open Date</b></label>
                                        <label>${rfpHeader.opendate}</label>

                                    </div>
                                </div>
                                <div class="span6">
                                    <div class="control-group">
                                        <label class="control-label" for=""><b>Close Date</b></label>
                                        <label>${rfpHeader.closedate}</label>

                                    </div>
                                </div>        
                            </div>
                            <div class="row-fluid">
                                <div class="span6">
                                    <div class="control-group">
                                        <label class="control-label" for=""><b>Cost Code</b></label>
                                        <label>${rfpHeader.costcode}</label>

                                    </div>
                                </div>
                                <div class="span6">
                                    <div class="control-group">
                                        <label class="control-label" for=""><b>Negotiation Style</b></label>
                                        <label>${rfpHeader.negotationstyle}</label>

                                    </div>
                                </div>        
                            </div>
                            <div class="row-fluid">
                                <div class="span6">
                                    <div class="control-group">
                                        <label class="control-label" for=""><b>Payment Terms</b></label>
                                        <label>${rfpHeader.paymentterms}</label>

                                    </div>
                                </div>
                                <div class="span6">
                                    <div class="control-group">
                                        <label class="control-label" for=""><b>Project Name / Number</b></label>
                                        <label>${rfpHeader.projectnamecode}</label>

                                    </div>
                                </div>        
                            </div>
                            <div class="row-fluid">
                                <div class="span6">
                                    <div class="control-group">
                                        <label class="control-label" for=""><b>Bill to Address</b></label>
                                        <label>${rfpHeader.billtoaddress}</label>

                                    </div>
                                </div>
                                <div class="span6">
                                    <div class="control-group">
                                        <label class="control-label" for=""><b>Ship to Address</b></label>
                                        <label>${rfpHeader.shiptoaddress}</label>

                                    </div>
                                </div>        
                            </div>
                            <div class="row-fluid">
                                
                                <div class="span12">
                                    <div class="control-group">
                                        <label class="control-label" for=""><b>Description</b></label>
                                        <label>${rfpHeader.description}</label>

                                    </div>
                                </div>        
                            </div>            
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-inverse" data-dismiss="modal">Close</button>
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
