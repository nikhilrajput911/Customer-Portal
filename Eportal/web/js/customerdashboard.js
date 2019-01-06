$(document).ready(function() {



    var ispasswordupdated = $("#ispasswordupdated").val();
    var isPersonalInfoUpdated = $("#isPersonalInfoUpdated").val();
    var userRole = $("#userRole").val();

    if (ispasswordupdated !== "Yes")
    {
        $("#modalDivId").removeClass("hidden");
        $("#updatePasswordAtFirst").modal({backdrop: 'static', keyboard: false});
        $("#updatePasswordAtFirst").modal("show");
    }
    else
    {
        if (isPersonalInfoUpdated !== "Yes")
        {
            if (userRole !== "Admin")
            {
                location.href = "customereditprofile.do";
            }
        }
    }
//        $("#updatePasswordSubmitBtn").prop("disabled", true);

    $("#updatePasswordSubmitBtn").click(function() {

        var newpassword = $("#newpassword").val();
        var confirmpassword = $("#confirmpassword").val();
        var isValid = true;

        $("input[type='password']").each(function() {
            if ($(this).val() === "")
            {
                $(this).css("border-color", "red");
                isValid = false;
            }
            else
            {

            }
        });
        if (isValid === false)
        {
            return false;
        }
        else {
            $("#updatePasswordAtFirst").modal("hide");
            $("#updatePasswordForm").submit();
        }

    });

    $("#newpassword").after("<div id='error' style='color:red;'></div>");
    $("#newpassword").keyup(function() {
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
//                $("#check_constraint").val('true');
            $("#updatePasswordSubmitBtn").prop("disabled", false);
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
//                $("#check_constraint").val('false');
            $("#updatePasswordSubmitBtn").prop("disabled", true);
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
//                $("#check_constraint").val('true');
            $("#updatePasswordSubmitBtn").prop("disabled", false);
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
//                $("#check_constraint").val('false');
            $("#updatePasswordSubmitBtn").prop("disabled", true);
        }
    });
    $("#newpassword").blur(function() {
        $("#confirmpassword").val('');
        $("#updatePasswordSubmitBtn").prop("disabled", true);
    });
    $("#confirmpassword").blur(function() {
        if ($("#newpassword").val() !== '')
        {
            if ($("#confirmpassword").val() !== '')
            {
                if ($("#newpassword").val() !== $("#confirmpassword").val())
                {
                    $("#confirmpassword").css("border-color", "red");
                    $("#confirmpassword").siblings().html('Password and confirm password must be same!');
//                        $("#check_constraint").val('false');
                    $("#updatePasswordSubmitBtn").prop("disabled", true);
                }
                else
                {
//                        $("#check_constraint").val('true');
                    $("#updatePasswordSubmitBtn").prop("disabled", false);
                }
            }
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