<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html >
    <head>
        <link href="css/customCSS.css" rel="stylesheet"> 
    </head>
    <body onload="document.f.username.focus();">
        <div> 
            <img id="" class="img-rounded panel-shadow" src="images/finalLogo.png" style="width:220px;position:absolute;top:15px;left:30px;">
            <div class="content">

                <div class="title" ><!--<center>
                <img id="" class="img-rounded panel-shadow" src="img/NatSteelTransaparentLogo.png" style="width: 230px;"> </center> -->
                    <!--<img src="img/customerlogin.png" >   -->
                    <center>Customer Portal</center> </div></br>
                <form name="f" role="form" action="customerhome.do" method="post" id="login-form">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <label for="userId">Username</label>&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="text" id="username" name="username" style="height:24px;width:170px; border-radius:5px;border: none;"placeholder="" required="true"/> </br></br> 
                    <label for="passTxt">Password</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="password" id="password" name="password" style="height:24px;width:170px;border-radius:5px;border: none;" placeholder="" required="true"/> </br> </br>
                    <input type="checkbox" id="remember" name="remember" value=""/>
                    <label for="rememberMe">Remember Me</label></br> </br>

                    <center>
                        <h1 style="font-size: 15px;color: red;text-align: center;">
                            ${message}
                        </h1>
                        <!--<button type="submit">Submit</button></br> </br>--> 
                        <input type="submit" id="btn-login" class="submit-btn" value="Submit">
                    </center>
                </form>
                <center><!--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-->
                    <a href="customerforgetpassword.do" class="forget" style="color: #c6c6c6; text-align:right">Update your password?</a></br></center>
                <center><!--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
                    <a href="customerforgotpassword.do" class="forget" style="color: #c6c6c6;">Forgot password?</a></center>
            </div>
            <div class="banner-text" style="width:600px;height:125px;position:absolute;left:130px;top:300px;border-radius:10px;color:white;">
                <h2 style="padding-left:10px">A Leading Pan-Asian Steel Company</h2>
                <p  style="padding-left:10px">
                    Natsteel delivers premium reinforcement steel products and solutions for Asia's construction industry.</p>
            </div>
        </div>
        <!--<span class="oafooter" style="position:absolute;top:620px;color:black;left:130px">This site is best viewed with IE 10 &amp; 11, Microsoft Edge 20, Chrome 55 to 64, Firefox 53 to 58, Safari 10 &amp; 11 and Screen resolution 1366 X 768.</span>-->
    </body>
</html>