$(document).ready(function() {
    
   $("#RFQTitle").after("<div id='error' style='color:red;'></div>");
   $("#RFQTitle").keyup(function() {
//       alert("up");
        var val = $(this).val();
        var regex = new RegExp('^[a-zA-Z][a-zA-Z\\s]+$');
        
        if(regex.test(val))
        {
            $(this).css("border-color", "red");
            $(this).siblings().html('');
            $("#check_constraint").val('true');
        }
        else
        {
//            alert('Alphabets only!');
            $(this).css("border-color", "red");
            $(this).siblings().html('<b>Alphabets only!</b>');
            $("#check_constraint").val('false');
        }
   });
   
   $("#initiator_name").after("<div id='error' style='color:red;'></div>");
   $("#initiator_name").keyup(function() {
//       alert("up");
        var val = $(this).val();
        var regex = new RegExp('^[a-zA-Z][a-zA-Z\\s]+$');
        
        if(regex.test(val))
        {
            $(this).css("border-color", "red");
            $(this).siblings().html('');
            $("#check_constraint").val('true');
        }
        else
        {
//            alert('Alphabets only!');
            $(this).css("border-color", "red");
            $(this).siblings().html('<b>Alphabets only!</b>');
            $("#check_constraint").val('false');
        }
   });
   
   $("#Projectnamecode").after("<div id='error' style='color:red;'></div>");
   $("#Projectnamecode").keyup(function() {
//       alert("up");
        var val = $(this).val();
        var regex = new RegExp('^[A-Za-z0-9\\s]+$');
        
        if(regex.test(val))
        {
            $(this).css("border-color", "red");
            $(this).siblings().html('');
            $("#check_constraint").val('true');
        }
        else
        {
//            alert('Alphabets only!');
            $(this).css("border-color", "red");
            $(this).siblings().html('<b>Alphnumeric only!</b>');
            $("#check_constraint").val('false');
        }
   });
   
    $("#billtoaddress").after("<div id='error' style='color:red;'></div>");
    $("#billtoaddress").keyup(function() {
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
            $(this).siblings().html('Bill to address may be alphanumeric with special symbol , and -');
            $("#check_constraint").val('false');
        }
    });
    
    $("#shiptoaddress").after("<div id='error' style='color:red;'></div>");
    $("#shiptoaddress").keyup(function() {
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
            $(this).siblings().html('Bill to address may be alphanumeric with special symbol are , and -');
            $("#check_constraint").val('false');
        }
    });
   
   $("#itemname").after("<div id='error' style='color:red;'></div>");
   $("#itemname").keyup(function() {
//       alert("up");
        var val = $(this).val();
        var regex = new RegExp('^[A-Za-z\\s]+$');
        
        if(regex.test(val))
        {
            $(this).css("border-color", "red");
            $(this).siblings().html('');
            $("#check_constraint").val('true');
        }
        else
        {
//            alert('Alphabets only!');
            $(this).css("border-color", "red");    
            $(this).siblings().html('<b>Alphabets only!</b>');
            $("#check_constraint").val('false');
        }
   });
   
   $("#name").after("<div id='error' style='color:red;'></div>");
   $("#name").keyup(function() {
//       alert("up");
        var val = $(this).val();
        var regex = new RegExp('^[A-Za-z\\s]+$');
        
        if(regex.test(val))
        {
            $(this).css("border-color", "red");
            $(this).siblings().html('');
            $("#check_constraint").val('true');
        }
        else
        {
//            alert('Alphabets only!');
            $(this).css("border-color", "red");
            $(this).siblings().html('<b>Alphabets only!</b>');
            $("#check_constraint").val('false');
        }
   });
   
   $("#approval1").blur(function() {
      $("#approval1").css('border-color', ''); 
   });
});