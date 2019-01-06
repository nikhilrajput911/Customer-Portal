$(document).ready(function() {
//For Login Form//
    $("#login-form").bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            username: {
                validators: {
                    // stringLength:{
                    // min:2,
                    // max:10
                    // },
                    regexp: {
                        regexp: /^[a-z\s]+$/i,
                        message: 'The full name can consist of alphabetical characters and spaces only'
                    },
                    notEmpty: {
                        message: 'Please enter username'
                    }
                }

          },
            password: {
                validators: {
//                    stringLength: {
//                        min: 2,
//                        max: 10
//                    },
                    notEmpty: {
                        message: 'Please enter password'
                    }
                }

            }

        }
    });

//For Sign up Form//

    $("#register").bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            firstname: {
                validators: {
                    // stringLength:{
                    // min:2,
                    // max:10
                    // },
                    regexp: {
                        regexp: /^[a-z\s]+$/i,
                        message: 'The first name can consist of alphabetical characters and spaces only'
                    },
                    notEmpty: {
                        message: 'Please enter first name'
                    }
                }

          }
            
            
        }
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










