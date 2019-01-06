<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>Login Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
    <meta name="description" content="User login page" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />		

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.3/js/bootstrapValidator.min.js" type="text/javascript"></script>
    <!--<script src="js/login.js"></script>-->
    <!--<script src="js/validate.js"></script>-->
    <link rel="stylesheet" href="css/login.css">
</head>
<!--<body style="background-image:url(images/backgrnd.jpg);">-->
<body onload="document.f.username.focus();">
    <!--    <div class="text-center">
            <h3>
                            <img style="height: 45px;" src="" alt="">
                <img src="images/logo.gif" alt=""/>
                <div class="clearfix"></div>
                <span style="color:#1fa67b;">Procurement</span>
    
            </h3>									
        </div>-->

    <!--<section id="login">-->
    <div class="container-fluid container-div">
<!--        <div class="row header-div">
            <div class="col-xs-6 col-lg-6 logo-name">
                <div style="color: orange;">
                    <h2><b>BPAAS Solutions</b></h2>
                </div>
            </div>
            <div class="col-xs-2 col-lg-2 contactUs">
                <div style="color: whitesmoke;">
                    <h4><b>Contact Us</b></h4>
                </div>
            </div>
            <div class="col-xs-2 col-lg-2 help">
                <div style="color: whitesmoke;">
                    <h4><b>Help</b></h4>
                </div>
            </div>
            <div class="col-xs-2 col-lg-2 language">
                <div style="color: whitesmoke;">
                    <h4><b>Language</b></h4>
                </div>
            </div>
        </div>-->
        <div class="row body-div">
            <div class="col-xs-8 login-div">
                <!--<img src="images.jpg">-->
                <h4 style="color: red;">${message}</h4>
            </div> 
            <div class="col-xs-4 login-content-div">
                <div style="color: whitesmoke;float: left; padding-top: 50px; padding-left: 10%;">
                    <h2><b>END-TO-END</b></h2>
                    <h4>P2P MANAGEMENT PORTAL</h4><br><br>
<!--                    <b>** Manage and Track Order<br><br>
                    ** Manage Your Relationship<br><br>
                    ** Grow Your Business-->
                    </b><hr><br> 
                    <!--<h4>New User ?</h4>-->
                    <!--<a href="toregister.do" style="color: orange; padding-bottom: 30px;"><h4><b>Create New Account</b></h4></a>-->
                    <h4><a href="login.do" style="color: orange; padding-top: 80px;"><h4><b> P2P Login</b></h4></a></h4>
                    <h4><a href="customerlogin.do" style="color: orange; padding-top: 80px;"><h4><b> Customer Login</b></h4></a></h4>
                    <h4><a href="supplierLogin.do" style="color: orange; padding-top: 80px;"><h4><b> Supplier Login</b></h4></a></h4>
                </div>
            </div>            
        </div>
        
    </div>   
    <!--</section>-->  




    <div class="modal fade forget-modal" tabindex="-1" role="dialog" aria-labelledby="myForgetModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">×</span>
                        <span class="sr-only">Close</span>
                    </button>
                    <h4 class="modal-title">Recovery password</h4>
                </div>
                <div class="modal-body">
                    <p>Type your email account</p>
                    <input type="email" name="recovery-email" id="recovery-email" class="form-control" autocomplete="off">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    <button type="button" class="btn btn-custom">Recovery</button>
                </div>
            </div> <!-- /.modal-content -->
        </div> <!-- /.modal-dialog -->
    </div> <!-- /.modal -->



    <!--
    <footer id="footer">
        <div class="container">
            <div class="row">
                <div class="col-xs-12">
                    <p>Page © - 2014</p>
                    <p>Powered by <strong><a href="http://www.facebook.com/tavo.qiqe.lucero" target="_blank">TavoQiqe</a></strong></p>
                </div>
            </div>
        </div>
    </footer>-->
</body>
</html>