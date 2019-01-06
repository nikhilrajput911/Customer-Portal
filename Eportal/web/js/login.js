$(document).ready(function(){
    
  $.validator.addMethod(
        "regex",
        function(value, element, regexp) {
            var re = new RegExp(regexp);
            return this.optional(element) || re.test(value);
        },
        "Please check your input."
);
$("#contactnumber").rules("add", { regex: "^([0-9]+){10}$" });
    $("#register").validate(function(){
        rules: {
            contactnumber: {
                required: true
                //regex:'^([0-9]+){10}$'
            }
        }
        message: {
            contactnumber: {
                regex: 'Please enter a phone no. - nn nnnn nnnn.'
            }
        }
    });
});

function showPassword() {
    
    var key_attr = $('#key').attr('type');
    
    if(key_attr != 'text') {
        
        $('.checkbox').addClass('show');
        $('#key').attr('type', 'text');
        
    } else {
        
        $('.checkbox').removeClass('show');
        $('#key').attr('type', 'password');
        
    }
    
}