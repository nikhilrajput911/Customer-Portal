$(document).ready(function() {

    $("#validation-emp").bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            companyname: {
                validators: {
                    // stringLength:{
                    // min:2,
                    // max:10
                    // },
//                    regexp: {
//                        regexp: /^[a-z\s]+$/i,
//                        message: 'The first name can consist of alphabetical characters and spaces only'
//                    },
                    notEmpty: {
                        message: 'Please enter first name'
                    }
                }

            }


        }
    });
});