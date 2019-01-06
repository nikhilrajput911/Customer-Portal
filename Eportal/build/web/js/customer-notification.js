$(document).ready(function() {
    
    var notification_count = $("marquee").children("input").val();
    console.log(notification_count);
    if(notification_count == 0)
    {
//        console.log("sadas");
//        console.log($("marquee").siblings());
        $("marquee").siblings().removeClass("hidden");
    }
    
    $("ul.sub-menu li").click(function() {
        //console.log(this);
        //alert(this);
        //alert($(this).html());
//        alert($(this).children("input").val());

        //alert($(this).html().find('input[type="hidden"]'));
        var notificationId = $(this).children("input").val();

        $.ajax(
                {
                    type: "GET",
                    url: "rfeCont.do",
                    async: true,
                    data:
                            {
                                "reqFrom": "CustomerNotificationChangeStatus",
                                "notificationId": notificationId
                            },
                    dataType: "json",
                    complete: function(responseJson)
                    {
                        var obj = $.parseJSON(responseJson.responseText);

                    }
                });
    });
    $("div.body div.text").click(function() {
        //console.log(this);
//        alert(this);
//        alert($(this).html());
//        alert($(this).children("input").val());

        //alert($(this).html().find('input[type="hidden"]'));
        var notificationId = $(this).children("input").val();
//        alert(notificationId);

        $.ajax(
                {
                    type: "GET",
                    url: "rfeCont.do",
                    async: true,
                    data:
                            {
                                "reqFrom": "CustomerNotificationChangeStatus",
                                "notificationId": notificationId
                            },
                    dataType: "json",
                    complete: function(responseJson)
                    {
                        var obj = $.parseJSON(responseJson.responseText);
                        
                    }
                });
    });
});