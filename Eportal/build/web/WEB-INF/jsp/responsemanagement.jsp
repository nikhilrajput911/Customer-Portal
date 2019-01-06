
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="description" content="User login page" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />		

        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js" type="text/javascript"></script>

        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.18/css/jquery.dataTables.min.css" type="text/css"/>

        <script src="https://cdn.datatables.net/1.10.18/js/jquery.dataTables.min.js" type="text/javascript"></script>


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
            $(document).ready(function()
            {


            });
        </script>

    </head>
    <body>

        <%@include file = "template.jsp" %>
        <div class="breadcrumbs" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="icon-home home-icon"></i><a href="#">Home</a>
                    <span class="divider"><i class="icon-angle-right arrow-icon"></i></span>
                </li>
                <li class="active">Response Management</li>						
            </ul>
        </div>
        <div class="page-content">
            <h5 class="center"><span style="color: green;">${message}</span></h5>
            <div class="row-fluid">
                <div class="span12">
                    <select id="awaiting_bid_rfp_select" name="awaiting_bid_rfp_select" data-width="100%" data-live-search="true" class="selectpicker" data-style="btn-primary">
                        <option value="" disabled selected hidden>Please choose any RFP...</option>
                        <c:forEach var="rfqHeader" items="${map.rfqHeader}">
                            <option value="${rfqHeader.rfqid}" class="align-left">${rfqHeader.RFQTitle}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>    
            <br><br>
            <div class="row-fluid table-responsive" id="awaiting_bid_rfp_table_div" style="display: none;">
                <div class="span12" style="">
                    <table id="bid_awaiting_rfq_table" class="table table-hover table-condensed">
                        <thead>
                            <tr>
                                <%                                    if (session.getAttribute("userType").equals("SCM User")) {
                                %>
                                <th class="center"><input type="checkbox" /></th>
                                    <%
                                }
                                    %>
                                <th>S.No.</th>  
                                <th>Supplier</th>
                                <th>RFQ No.</th>
                                <th>Status</th>

                            </tr>
                        </thead>

                        <tbody>
                        </tbody>
                    </table>
                </div>
            </div>
            <%
                if (session.getAttribute("userType").equals("SCM User")) {
            %>
            <div class="row-fluid">
                <div class="span6">
                    <input type="button" id="send_message_to_all" value="Send Message" data-toggle="modal" data-target="#sendMessageToAllModal" style="display: none;" class="btn btn-success">
                </div>
            </div>
            <%
                }
            %>
            <input type="hidden" value="<%= session.getAttribute("userType")%>" id="usertype">
            <!--</div>-->
            <!--</form>-->
            <!--</div>-->
            <!--</div>-->

            <!--                    </div>
                            </div>-->
            <!--</div>-->                                        
            <!--</div>-->                                        
        </div>				
    </div><!--/.main-content-->
</div><!--/.main-container-->
<div id="chatDiv">
    <div class="modal fade" id="sendMessageToAllModal" role="dialog">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Send Message</h4>
                </div>
                <div class="modal-body">
                    <form id="comment-form" method="post" action="makeCommentToAllSelectedSupplier.do" enctype="multipart/form-data">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        <textarea class="span5" style="" id="messageToAll" name="messageToAll" rows="6" cols=""></textarea>
                        <input type="file" id="id-input-file-1" class="span10" name="file"/>	
                        <input type="hidden" id="messageContentToAllSupplier" name="messageContentToAllSupplier">
                        <input type="hidden" id="allRfqid" name="allRfqid">
                        <input type="hidden" id="allSupplierid" name="allSupplierid">
                    </form>    
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-success" id="send_message_to_all_supplier">Send</button>
                    <button type="button" class="btn btn-inverse" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
</div>
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
