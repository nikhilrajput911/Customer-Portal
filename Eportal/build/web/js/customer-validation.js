$(document).ready(function() {



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
            $(this).siblings().html('Alphabets and spaces only!');
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
            $(this).siblings().html('Alphabets and spaces only!');
//            $("#check_constraint").val('false');
        }
    });
    $("#emailid").after("<div id='error' style='color:red;'></div>");
    $("#emailid").keyup(function() {
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
    $("#username").after("<div id='error' style='color:red;'></div>");
    $("#username").keyup(function() {
        var val = $(this).val();
//        var minPassLen = $("#minPassLen").val();
//        alert(minPassLen);
        var regex = new RegExp('^[a-zA-Z0-9._-]{8,}$');

        if (regex.test(val))
        {
            $(this).css("border-color", "");
            $(this).siblings().html('');
            $("#check_constraint").val('true');
        }
        else
        {
            $(this).css("border-color", "red");
            $(this).siblings().html('Please enter  min-5 and max-30 characters alphanumeric username<br>It may contain _, ., - starts with letter');
            $("#check_constraint").val('false');
        }
    });
    $("#password").after("<div id='error' style='color:red;'></div>");
    $("#password").keyup(function() {
        var val = $(this).val();
//        var regex = new RegExp('^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])([a-zA-Z0-9]{8,30})$');
        var regex = new RegExp('^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$');
//        var regex = new RegExp('^(?=.{8,})(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$');
        var passconfig = $("#passconfig").val();
        var passConfigArray = passconfig.split(",");
        console.log("passconfigarray length: " + passConfigArray.length);
        var result = validatePassword(val, passConfigArray);
        console.log("result: " + result);

        if (result === true)
        {
            $(this).css("border-color", "");
            $(this).siblings().html('');
            $("#check_constraint").val('true');
            
            $("#add_user_btn").prop("disabled", false);
            $("#update_edituserbtn").prop("disabled", false);
        }
        else
        {
            var passLen = "";
            var specialCharCount = "";
            var uppCharCount = "";
            var lowCharCount = "";
            var numCount = "";

            if (passConfigArray.length > 0)
            {
                passLen = passConfigArray[0];
                specialCharCount = passConfigArray[1];
                uppCharCount = passConfigArray[2];
                lowCharCount = passConfigArray[3];
                numCount = passConfigArray[4];
            }

            $(this).css("border-color", "red");
            $(this).siblings().html('Password length must be ' + passLen + ' characters.<br>Password must contain ' + specialCharCount + ' special characters.<br>Password must contain ' + uppCharCount + ' uppercase characters.<br>Password must contain ' + lowCharCount + ' lowercase characters.<br>Password must contain ' + numCount + ' numberic characters.');
            $("#check_constraint").val('false');
            
            $("#add_user_btn").prop("disabled", true);
            $("#update_edituserbtn").prop("disabled", true);
        }
    });
    $("#confirmpassword").after("<div id='error' style='color:red;'></div>");
    $("#confirmpassword").keyup(function() {
        var val = $(this).val();
//        var regex = new RegExp('^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])([a-zA-Z0-9]{8,30})$');
        var regex = new RegExp('^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$');
//        var regex = new RegExp('^(?=.{8,})(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$');
        var passconfig = $("#passconfig").val();
        var passConfigArray = passconfig.split(",");
        console.log("passconfigarray length: " + passConfigArray.length);
        var result = validatePassword(val, passConfigArray);
        console.log("result: " + result);

        if (result === true)
        {
            $(this).css("border-color", "");
            $(this).siblings().html('');
            $("#check_constraint").val('true');
            
            $("#add_user_btn").prop("disabled", false);
            $("#update_edituserbtn").prop("disabled", false);
        }
        else
        {
            var passLen = "";
            var specialCharCount = "";
            var uppCharCount = "";
            var lowCharCount = "";
            var numCount = "";

            if (passConfigArray.length > 0)
            {
                passLen = passConfigArray[0];
                specialCharCount = passConfigArray[1];
                uppCharCount = passConfigArray[2];
                lowCharCount = passConfigArray[3];
                numCount = passConfigArray[4];
            }
            
            $(this).css("border-color", "red");
            $(this).siblings().html('Password length must be ' + passLen + ' characters.<br>Password must contain ' + specialCharCount + ' special characters.<br>Password must contain ' + uppCharCount + ' uppercase characters.<br>Password must contain ' + lowCharCount + ' lowercase characters.<br>Password must contain ' + numCount + ' numberic characters.');
            $("#check_constraint").val('false');
            
            $("#add_user_btn").prop("disabled", true);
            $("#update_edituserbtn").prop("disabled", true);
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
                    $("#check_constraint").val('false');
                }
                else
                {
                    $("#check_constraint").val('true');
                }
            }
        }
    });
    $("#mobileno").after("<div id='error' style='color:red;'></div>");
    $("#mobileno").keyup(function() {
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
});

function validatePassword(passVal, passConfigArray)
{
    console.log(passVal);
    if (passConfigArray.length > 0)
    {
        var passLen = passConfigArray[0];
        var specialCharCount = passConfigArray[1];
        var uppCharCount = passConfigArray[2];
        var lowCharCount = passConfigArray[3];
        var numCount = passConfigArray[4];

//        console.log("passLen: " + passLen);
//        console.log("specialCharCount: " + specialCharCount);
//        console.log("uppCharCount: " + uppCharCount);
//        console.log("lowCharCount: " + lowCharCount);
//        console.log("numCount: " + numCount);

        var len = passVal.toString().length;
        console.log("len: " + len);

        var numUpper = (passVal.match(/[A-Z]/g) || []).length;
        console.log("numUpper: " + numUpper);

        var numLower = (passVal.match(/[a-z]/g) || []).length;
        console.log("numLower: " + numLower);

        var numericCount = (passVal.match(/[0-9]/g) || []).length;
        console.log("numericCount: " + numericCount);

        var specialCount = (passVal.match(/[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/g) || []).length;
        console.log("specialCount: " + specialCount);

        if (len < passLen)
        {
            return false;
        }
        if (numUpper < uppCharCount)
        {
            return false;
        }
        if (numLower < lowCharCount)
        {
            return false;
        }
        if (numericCount < numCount)
        {
            return false;
        }
        if (specialCount < specialCharCount)
        {
            return false;
        }

        return true;
    }

}