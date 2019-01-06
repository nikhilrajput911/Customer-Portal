$(document).ready(function() {

    $("#companyname").after("<div id='error' style='color:red;'></div>");
    $("#companyname").keyup(function() {
//       alert("up");
        var val = $(this).val();
        var regex = new RegExp('^[a-zA-Z\\s]{1,50}$');

        if (regex.test(val))
        {
            $(this).css("border-color", "");
            $(this).siblings().html('');
            $("#check_constraint").val('true');
        }
        else
        {
//            alert('Alphabets only!');
            $(this).css("border-color", "red");
            $(this).siblings().html('Alphabets only with max 50 characters!');
            $("#check_constraint").val('false');
        }
    });

    $("#registeredaddress").after("<div id='error' style='color:red;'></div>");
    $("#registeredaddress").keyup(function() {
        var val = $(this).val();
        var regex = new RegExp('^[A-Za-z0-9\\s,-]{1,100}$');

        if (regex.test(val))
        {
            $(this).css("border-color", "");
            $(this).siblings().html('');
            $("#check_constraint").val('true');
        }
        else
        {
            $(this).css("border-color", "red");
            $(this).siblings().html('Alphnumeric only with , and - max 100 characters!');
            $("#check_constraint").val('false');
        }
    });

    $("#ownername").after("<div id='error' style='color:red;'></div>");
    $("#ownername").keyup(function() {
        var val = $(this).val();
        var regex = new RegExp('^[A-Za-z\\s]{1,30}$');

        if (regex.test(val))
        {
            $(this).css("border-color", "");
            $(this).siblings().html('');
            $("#check_constraint").val('true');
        }
        else
        {
            $(this).css("border-color", "red");
            $(this).siblings().html('Alphabets only with 30 characters!');
            $("#check_constraint").val('false');
        }
    });

    $("#ownermobilenumber").after("<div id='error' style='color:red;'></div>");
    $("#ownermobilenumber").keyup(function() {
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

    $("#owneremailid").after("<div id='error' style='color:red;'></div>");
    $("#owneremailid").keyup(function() {
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

    $("#staffstrength").after("<div id='error' style='color:red;'></div>");
    $("#staffstrength").keyup(function() {
        var val = $(this).val();
        var regex = new RegExp('^[0-9-]{1,10}$');

        if (regex.test(val))
        {
            $(this).css("border-color", "");
            $(this).siblings().html('');
            $("#check_constraint").val('true');
        }
        else
        {
            $(this).css("border-color", "red");
            $(this).siblings().html('Please enter numeric with - character having max 10 characters!');
            $("#check_constraint").val('false');
        }
    });
    $("#companypannumber").after("<div id='error' style='color:red;'></div>");
    $("#companypannumber").keyup(function() {
        var val = $(this).val();
        var regex = new RegExp('^[A-Z0-9]{10}$');

        if (regex.test(val))
        {
            $(this).css("border-color", "");
            $(this).siblings().html('');
            $("#check_constraint").val('true');
        }
        else
        {
            $(this).css("border-color", "red");
            $(this).siblings().html('Please enter 10 digits alphanumeric characters!');
            $("#check_constraint").val('false');
        }
    });
    $("#username").after("<div id='error' style='color:red;'></div>");
    $("#username").keyup(function() {
        var val = $(this).val();
        var regex = new RegExp('^[a-zA-Z0-9._-]{5,30}$');

        if (regex.test(val))
        {
            $(this).css("border-color", "");
            $(this).siblings().html('');
            $("#check_constraint").val('true');
        }
        else
        {
            $(this).css("border-color", "red");
            $(this).siblings().html('Please enter  min-5 and max-50 characters alphanumeric username, it may contain _ . - starts with letter');
            $("#check_constraint").val('false');
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
            $("#check_constraint").val('true');
        }
        else
        {
            $(this).css("border-color", "red");
            $(this).siblings().html('Password must contain at least one digit, at least one lower case, at least one upper case and at least one special symbol, \n you can choose symbol from these @, #, $, %, ^, &, +, =');
            $("#check_constraint").val('false');
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
            $("#check_constraint").val('true');
        }
        else
        {
            $(this).css("border-color", "red");
            $(this).siblings().html('Password must contain at least one digit, at least one lower case, at least one upper case and at least one special symbol, \n you can choose symbol from these @, #, $, %, ^, &, +, =');
            $("#check_constraint").val('false');
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
    $("#gstnumber").after("<div id='error' style='color:red;'></div>");
    $("#gstnumber").keyup(function() {
        var val = $(this).val();
        var regex = new RegExp('^[A-Z0-9-]{10}$');
        if (regex.test(val))
        {
            $(this).css("border-color", "");
            $(this).siblings().html('');
            $("#check_constraint").val('true');
        }
        else
        {
            $(this).css("border-color", "red");
            $(this).siblings().html('GST Number must be of 10 characters only!');
            $("#check_constraint").val('false');
        }
    });
    $("#businessaddress").after("<div id='error' style='color:red;'></div>");
    $("#businessaddress").keyup(function() {
        var val = $(this).val();
        var regex = new RegExp('^[A-Za-z0-9\\s,-]{1,100}$');

        if (regex.test(val))
        {
            $(this).css("border-color", "");
            $(this).siblings().html('');
            $("#check_constraint").val('true');
        }
        else
        {
            $(this).css("border-color", "red");
            $(this).siblings().html('Business address can be of maximum 100 characters and it can contain special symbol like , and -');
            $("#check_constraint").val('false');
        }
    });
    $("#contactperson2").after("<div id='error' style='color:red;'></div>");
    $("#contactperson2").keyup(function() {
        var val = $(this).val();
        var regex = new RegExp('^[A-Za-z\\s]{1,30}$');

        if (regex.test(val))
        {
            $(this).css("border-color", "");
            $(this).siblings().html('');
            $("#check_constraint").val('true');
        }
        else
        {
            $(this).css("border-color", "red");
            $(this).siblings().html('Contact Person Name must contain alphabets and spaces only!');
            $("#check_constraint").val('false');
        }
    });

    $("#contact2mobilenumber").after("<div id='error' style='color:red;'></div>");
    $("#contact2mobilenumber").keyup(function() {
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

    $("#contact2emailaddress").after("<div id='error' style='color:red;'></div>");
    $("#contact2emailaddress").keyup(function() {
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

    $("#bankname").after("<div id='error' style='color:red;'></div>");
    $("#bankname").keyup(function() {
        var val = $(this).val();
        var regex = new RegExp('^[A-Za-z\\s]{1,50}$');

        if (regex.test(val))
        {
            $(this).css("border-color", "");
            $(this).siblings().html('');
            $("#check_constraint").val('true');
        }
        else
        {
            $(this).css("border-color", "red");
            $(this).siblings().html('Bank name may contain alphabets and spaces only');
            $("#check_constraint").val('false');
        }
    });
    $("#bankaccounttype").after("<div id='error' style='color:red;'></div>");
    $("#bankaccounttype").keyup(function() {
        var val = $(this).val();
        var regex = new RegExp('^[A-Za-z\\s]{1,30}$');

        if (regex.test(val))
        {
            $(this).css("border-color", "");
            $(this).siblings().html('');
            $("#check_constraint").val('true');
        }
        else
        {
            $(this).css("border-color", "red");
            $(this).siblings().html('Account type may contain alphabets and spaces only');
            $("#check_constraint").val('false');
        }
    });
    $("#bankaccountnumber").after("<div id='error' style='color:red;'></div>");
    $("#bankaccountnumber").keyup(function() {
        var val = $(this).val();
        var regex = new RegExp('^[0-9]{10,30}$');

        if (regex.test(val))
        {
            $(this).css("border-color", "");
            $(this).siblings().html('');
            $("#check_constraint").val('true');
        }
        else
        {
            $(this).css("border-color", "red");
            $(this).siblings().html('Please enter account number in digits only <br>It can be maximum of 10-30 digits only');
            $("#check_constraint").val('false');
        }
    });
    $("#confirmaccountnumber").after("<div id='error' style='color:red;'></div>");
    $("#confirmaccountnumber").keyup(function() {
        var val = $(this).val();
        var regex = new RegExp('^[0-9]{10,30}$');

        if (regex.test(val))
        {
            $(this).css("border-color", "");
            $(this).siblings().html('');
            $("#check_constraint").val('true');
        }
        else
        {
            $(this).css("border-color", "red");
            $(this).siblings().html('Please enter account number in digits only <br>It can be maximum of 10-30 digits only');
            $("#check_constraint").val('false');
        }
    });
    $("#bankaccountnumber").blur(function() {
        $("#confirmaccountnumber").val('');
    });
    $("#confirmaccountnumber").blur(function() {
        if ($("#bankaccountnumber").val() !== '')
        {
            if ($("#confirmaccountnumber").val() !== '')
            {
                if ($("#confirmaccountnumber").val() !== $("#bankaccountnumber").val())
                {
                    $(this).css("border-color", "red");
                    $(this).siblings().html('Confirm account number must be equal to account number');
                    $("#check_constraint").val('false');
                }
                else
                {
                    $("#check_constraint").val('true');
                }
            }
        }
    });
    $("#bankbranchname").after("<div id='error' style='color:red;'></div>");
    $("#bankbranchname").keyup(function() {
        var val = $(this).val();
        var regex = new RegExp('^[A-Za-z\\s]{1,30}$');

        if (regex.test(val))
        {
            $(this).css("border-color", "");
            $(this).siblings().html('');
            $("#check_constraint").val('true');
        }
        else
        {
            $(this).css("border-color", "red");
            $(this).siblings().html('Branch name may contain alphabets and spaces only <br>It may be 30 characters long');
            $("#check_constraint").val('false');
        }
    });
    $("#bankaddress").after("<div id='error' style='color:red;'></div>");
    $("#bankaddress").keyup(function() {
        var val = $(this).val();
        var regex = new RegExp('^[A-Za-z\\s]{1,50}$');

        if (regex.test(val))
        {
            $(this).css("border-color", "");
            $(this).siblings().html('');
            $("#check_constraint").val('true');
        }
        else
        {
            $(this).css("border-color", "red");
            $(this).siblings().html('Address may contain alphabets and spaces <br>only <br>It may be 50 characters long');
            $("#check_constraint").val('false');
        }
    });
    $("#pincode").after("<div id='error' style='color:red;'></div>");
    $("#pincode").keyup(function() {
        var val = $(this).val();
        var regex = new RegExp('^[0-9]{6,10}$');

        if (regex.test(val))
        {
            $(this).css("border-color", "");
            $(this).siblings().html('');
            $("#check_constraint").val('true');
        }
        else
        {
            $(this).css("border-color", "red");
            $(this).siblings().html('Please enter pin code in digits only <br>It must be between 6-10 digits only');
            $("#check_constraint").val('false');
        }
    });
    $("#ifsccode").after("<div id='error' style='color:red;'></div>");
    $("#ifsccode").keyup(function() {
        var val = $(this).val();
        var regex = new RegExp('^[A-Z0-9]{10,30}$');

        if (regex.test(val))
        {
            $(this).css("border-color", "");
            $(this).siblings().html('');
            $("#check_constraint").val('true');
        }
        else
        {
            $(this).css("border-color", "red");
            $(this).siblings().html('It must be between 10-30 characters only<br>Only numbers and characters only<br>No special character');
            $("#check_constraint").val('false');
        }
    });
    $("#micrnumber").after("<div id='error' style='color:red;'></div>");
    $("#micrnumber").keyup(function() {
        var val = $(this).val();
        var regex = new RegExp('^[0-9]{3,30}$');

        if (regex.test(val))
        {
            $(this).css("border-color", "");
            $(this).siblings().html('');
            $("#check_constraint").val('true');
        }
        else
        {
            $(this).css("border-color", "red");
            $(this).siblings().html('Must be between 10-30 digits<br>Only numbers<br>No special characters');
            $("#check_constraint").val('false');
        }
    });
    $("#vendornameAsperbankrecord").after("<div id='error' style='color:red;'></div>");
    $("#vendornameAsperbankrecord").keyup(function() {
        var val = $(this).val();
        var regex = new RegExp('^[A-Za-z\\s]{0,30}$');

        if (regex.test(val))
        {
            $(this).css("border-color", "");
            $(this).siblings().html('');
            $("#check_constraint").val('true');
        }
        else
        {
            $(this).css("border-color", "red");
            $(this).siblings().html('Alphabets only<br>Can not be more than 30 characters');
            $("#check_constraint").val('false');
        }
    });
    $(".supplier-form-field").blur(function(){
       $(this).css({"border-color":""}); 
    });
});