$(document).ready(function() {
//    alert("kjhkjh");
//    $('#register').attr("disabled", true);

    $("#firstname").after("<div id='error' style='color:red;'></div>");
    $("#firstname").keyup(function() {
//       alert("up");
        var val = $(this).val();
        var regex = new RegExp('^[a-zA-Z][a-zA-Z\\s]+$');

        if (regex.test(val))
        {
            $(this).css("border-color", "red");
            $(this).siblings().html('');
//            $("#check_constraint").val('true');
        }
        else
        {
//            alert('Alphabets only!');
            $(this).css("border-color", "red");
            $(this).siblings().html('<b>No special character allowed<br>It can be maximum 20 characters long</b>');

//            $("#check_constraint").val('false');
        }
    });
    $("#lastname").after("<div id='error' style='color:red;'></div>");
    $("#lastname").keyup(function() {
//       alert("up");
        var val = $(this).val();
        var regex = new RegExp('^[a-zA-Z][a-zA-Z\\s]+$');

        if (regex.test(val))
        {
            $(this).css("border-color", "red");
            $(this).siblings().html('');
//            $("#check_constraint").val('true');
        }
        else
        {
//            alert('Alphabets only!');
            $(this).css("border-color", "red");
            $(this).siblings().html('<b>No special character allowed<br>Only alphabets and spaces only<br>It can be maximum 20 characters long</b>');

//            $("#check_constraint").val('false');
        }
    });
    $("#username").after("<div id='error' style='color:red;'></div>");
    $("#username").keyup(function() {
        var val = $(this).val();
        var regex = new RegExp('^[a-zA-Z0-9._-]{6,30}$');

        if (regex.test(val))
        {
            $(this).css("border-color", "");
            $(this).siblings().html('');
//            $("#check_constraint").val('true');
        }
        else
        {
            $(this).css("border-color", "red");
            $(this).siblings().html('Please enter  min-6 and max-50 characters alphanumeric username<br>It may contain _ . - starts with letter');
//            $("#check_constraint").val('false');
        }
    });
    $("#password").after("<div id='error' style='color:red;'></div>");
    $("#password").keyup(function() {
        var val = $(this).val();
//        var regex = new RegExp('^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])([a-zA-Z0-9]{8,30})$');
        var regex = new RegExp('^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$');
//        var regex = new RegExp('^(?=.{8,})(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$');
        if (regex.test(val))
        {
            $(this).css("border-color", "");
            $(this).siblings().html('');
//            $("#check_constraint").val('true');
        }
        else
        {
            $(this).css("border-color", "red");
            $(this).siblings().html('Password must contain at least one digit, at least one lower case, at least one upper case and at least one special symbol<br>you can choose special symbol from these @, #, $, %, ^, &, +, =');
//            $("#check_constraint").val('false');
        }
    });
    $("#confirmpassword").after("<div id='error' style='color:red;'></div>");
    $("#confirmpassword").keyup(function() {
        var val = $(this).val();
//        var regex = new RegExp('^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])([a-zA-Z0-9]{8,30})$');
        var regex = new RegExp('^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$');
//        var regex = new RegExp('^(?=.{8,})(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$');
        if (regex.test(val))
        {
            $(this).css("border-color", "");
            $(this).siblings().html('');
//            $("#check_constraint").val('true');
        }
        else
        {
            $(this).css("border-color", "red");
            $(this).siblings().html('Password must contain at least one digit, at least one lower case, at least one upper case and at least one special symbol<br>you can choose special symbol from these @, #, $, %, ^, &, +, =');
//            $("#check_constraint").val('false');
        }
    });
    $("#password").blur(function() {
        $("#confirmpassword").val('');
    });
    $("#confirmpassword").blur(function() {
        if ($("#password").val() !== '')
        {
            if ($("#confirmpassword").val() !== '')
            {
                if ($("#password").val() !== $("#confirmpassword").val())
                {
                    $("#confirmpassword").css("border-color", "red");
                    $("#confirmpassword").siblings().html('Password and confirm passwor must be same!');
//                    $("#check_constraint").val('false');
                }
                else
                {
//                    $("#check_constraint").val('true');
                }
            }
        }
    });
    $("#supervisoremailid").after("<div id='error' style='color:red;'></div>");
    $("#supervisoremailid").keyup(function() {
        var val = $(this).val();
        var regex = new RegExp('^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$');

        if (regex.test(val))
        {
            $(this).css("border-color", "");
            $(this).siblings().html('');
            $("#check_constraint").val('true');
        }
        else
        {
            $(this).css("border-color", "red");
            $(this).siblings().html('Please enter valid email address!');
            $("#check_constraint").val('false');
        }
    });
    $("#workemailid").after("<div id='error' style='color:red;'></div>");
    $("#workemailid").keyup(function() {
        var val = $(this).val();
        var regex = new RegExp('^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$');

        if (regex.test(val))
        {
            $(this).css("border-color", "");
            $(this).siblings().html('');
            $("#check_constraint").val('true');
        }
        else
        {
            $(this).css("border-color", "red");
            $(this).siblings().html('Please enter valid email address!');
            $("#check_constraint").val('false');
        }
    });
    $("#contactnumber").after("<div id='error' style='color:red;'></div>");
    $("#contactnumber").keyup(function() {
        var val = $(this).val();
        var regex = new RegExp('^[0-9]{10}$');

        if (regex.test(val))
        {
            $(this).css("border-color", "");
            $(this).siblings().html('');
            $("#check_constraint").val('true');
        }
        else
        {
            $(this).css("border-color", "red");
            $(this).siblings().html('Please enter 10 digits mobile number only!');
            $("#check_constraint").val('false');
        }
    });
    $("#supervisorname").after("<div id='error' style='color:red;'></div>");
    $("#supervisorname").keyup(function() {
//       alert("up");
        var val = $(this).val();
        var regex = new RegExp('^[a-zA-Z\\s]+$');

        if (regex.test(val))
        {
            $(this).css("border-color", "red");
            $(this).siblings().html('');
//            $("#check_constraint").val('true');
        }
        else
        {
//            alert('Alphabets only!');
            $(this).css("border-color", "red");
            $(this).siblings().html('<b>No special character allowed<br>It can be maximum 20 characters long</b>');

//            $("#check_constraint").val('false');
        }
    });
    $("#securityanswer1").after("<div id='error' style='color:red;'></div>");
    $("#securityanswer1").keyup(function() {
//       alert("up");
        var val = $(this).val();
        var regex = new RegExp('^[a-zA-Z\\s]+$');

        var q1 = $("#securityquestion1").val();

        if (q1 !== null)
        {
            $(this).css("border-color", "red");
            $(this).siblings().html('<b>Please select security question first');

            if (regex.test(val))
            {
                $(this).css("border-color", "red");
                $(this).siblings().html('');

//            $("#check_constraint").val('true');
            }
            else
            {
//            alert('Alphabets only!');
//            alert($("#securityquestion1").val());
                $(this).css("border-color", "red");
                $(this).siblings().html('<b>No special character allowed<br>Only alphabets<br>It can be maximum 20 characters long</b>');

//            $("#check_constraint").val('false');
            }
        }
        else
        {
            $(this).css("border-color", "red");
            $(this).siblings().html('<b>Please select security question first</b>');
            $("#securityquestion1").focus();
        }
    });
    $("#answer2").after("<div id='error' style='color:red;'></div>");
    $("#answer2").keyup(function() {
//       alert("up");
        var val = $(this).val();
        var regex = new RegExp('^[a-zA-Z\\s]+$');

        var q2 = $("#securityquestion2").val();

        if (q2 !== null)
        {
            $(this).css("border-color", "red");
            $(this).siblings().html('<b>Please select security question first');

            if (regex.test(val))
            {
                $(this).css("border-color", "red");
                $(this).siblings().html('');

//            $("#check_constraint").val('true');
            }
            else
            {
//            alert('Alphabets only!');
//            alert($("#securityquestion1").val());
                $(this).css("border-color", "red");
                $(this).siblings().html('<b>No special character allowed<br>Only alphabets<br>It can be maximum 20 characters long</b>');

//            $("#check_constraint").val('false');
            }
        }
        else
        {
            $(this).css("border-color", "red");
            $(this).siblings().html('<b>Please select security question first</b>');
            $("#securityquestion2").focus();
        }
    });
    $("#answer3").after("<div id='error' style='color:red;'></div>");
    $("#answer3").keyup(function() {
//       alert("up");
        var val = $(this).val();
        var regex = new RegExp('^[a-zA-Z\\s]+$');

        var q2 = $("#securityquestion3").val();

        if (q2 !== null)
        {
            $(this).css("border-color", "red");
            $(this).siblings().html('<b>Please select security question first');

            if (regex.test(val))
            {
                $(this).css("border-color", "red");
                $(this).siblings().html('');

//            $("#check_constraint").val('true');
            }
            else
            {
//            alert('Alphabets only!');
//            alert($("#securityquestion1").val());
                $(this).css("border-color", "red");
                $(this).siblings().html('<b>No special character allowed<br>Only alphabets<br>It can be maximum 20 characters long</b>');

//            $("#check_constraint").val('false');
            }
        }
        else
        {
            $(this).css("border-color", "red");
            $(this).siblings().html('<b>Please select security question first</b>');
            $("#securityquestion3").focus();
        }
    });
    var check = '';
    $("#securityquestion1").after("<div id='error' style='color:red;'></div>");
    $("#securityquestion1").change(function() {
//        alert(check.indexOf($(this).val()));
        if (check.indexOf($(this).val()) >= 0)
        {
            //alert('already selected, please choose another question');
            $(this).css("border-color", "red");
            $(this).siblings().html('Already selected, please select another security question');
            $("#securityquestion1").focus();
            $(this).val('default');
        }
        else if ($(this).val() !== null)
        {
            check = check + $(this).val() + '~';
            $(this).css("border-color", "");
            $(this).siblings().html('');
        }
        console.log(check);
    });
    $("#securityquestion2").after("<div id='error' style='color:red;'></div>");
    $("#securityquestion2").change(function() {
//        alert(check.indexOf($(this).val()));
        if (check.indexOf($(this).val()) >= 0)
        {
            //alert('already selected, please choose another question');
            $(this).css("border-color", "red");
            $(this).siblings().html('Already selected, please select another security question');
            $("#securityquestion2").focus();
            $(this).val('default');
        }
        else if ($(this).val() !== null)
        {
            check = check + $(this).val() + '~';
            $(this).css("border-color", "");
            $(this).siblings().html('');
        }
        console.log(check);
    });
    $("#securityquestion3").after("<div id='error' style='color:red;'></div>");
    $("#securityquestion3").change(function() {
//        alert(check.indexOf($(this).val()));
        if (check.indexOf($(this).val()) >= 0)
        {
//            alert('already selected, please choose another question');
            $(this).css("border-color", "red");
            $(this).siblings().html('Already selected, please select another security question');
            $("#securityquestion3").focus();
            $(this).val('default');
        }
        else if ($(this).val() !== null)
        {
            check = check + $(this).val() + '~';
            $(this).css("border-color", "");
            $(this).siblings().html('');
        }
        console.log(check);
    });
    $(".registration-field").blur(function() {
       $(this).css("border-color", ""); 
    });
    $("#back_btn").click(function(){
       location.href = "login.do"; 
    });
    $("#lastname").blur(function(){
        var fname = $("#firstname").val();
        var lname = $("#lastname").val();
        $("#updatedby").val(fname  + " " + lname);
        $("#createdby").val(fname  + " " + lname);
        
//        var date = new Date();
//        var today = date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getYear();
//        $("#creationdate").val(date);
//        $("#updatedate").val(date);
    });
});