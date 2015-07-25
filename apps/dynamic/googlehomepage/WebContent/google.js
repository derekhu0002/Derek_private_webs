$(function(){
    $(document).on("click",function(event){
        // console.log($(document));
        event.stopPropagation();
        if (!($(event.target).closest(".grid-li").hasClass("grid-li"))){

            if (!($(".frame").hasClass("no-display"))){
                $(".frame").toggleClass("no-display");
            };
            if (!($(".more-ul").hasClass("no-display"))){
                $(".more-ul").toggleClass("no-display");
            };
        } ;
        if (!($(event.target).closest(".bell-li").hasClass("bell-li"))){
            if(!($(".bell-frame").hasClass("no-display"))){
                $(".bell-frame").toggleClass("no-display");
            }
        } else{
            event.stopPropagation();
        };
        if (!($(event.target).closest(".user-li").hasClass("user-li"))){
            if(!($(".user-frame").hasClass("no-display"))){
                $(".user-frame").toggleClass("no-display");
            }
        } else{
            event.stopPropagation();
        };
        if (!($(event.target).closest(".plus-li").hasClass("plus-li"))){

            if(!($(".plus-frame").hasClass("no-display"))){
                $(".plus-frame").toggleClass("no-display");
            }
        } else{
            event.stopPropagation();
            return;
            // console.log($(".plus-li").hasClass("no-display"));
//             console.log("keep the same");
        };
    });
    $("#close-icon").on("click",function(event){

        event.stopPropagation();
        event.preventDefault();
        $("#public").css({"display":"none"});
        $("#add-input").attr("placeholder","To: Add names, circles, or email addresses");
    });
    $(".grid-li").on("click",function(event){

        event.preventDefault();
        $(this).find(".more-ul").addClass("no-display");
        $(this).find(".frame").toggleClass("no-display");
    });
    $(".frame").on("click",function(event){
        event.stopPropagation();

    });
    $(".bell-frame").on("click",function(event){
        event.stopPropagation();

    });
    $(".plus-frame").on("click",function(event){
        event.stopPropagation();

    });
    $(".user-li").on("click",function(event){

        event.preventDefault();

        $(".user-frame").toggleClass("no-display");
    });
    $(".plus-li").on("click",function(event){
        event.preventDefault();
        $(".plus-frame").toggleClass("no-display");
    });
    $("li.more").on("click", function(event){
        event.stopPropagation();
        event.preventDefault();

        $(".more-ul").toggleClass("no-display");
    });

    $(".bell-li").on("click", function(event){

        event.preventDefault();
        $(this).find("div.bell-frame").toggleClass("no-display");
    });
    $("#user-face").on("click", function(event){
        event.stopPropagation();
        event.preventDefault();
        $(".modal").removeClass("no-display");
        $("#overlay").css({background: "#fff", opacity: "0.3"});
        // $("header").css({"color":"#ccc","background":"#bbb"});
//         $("main").css({"color":"#ccc"});
//         $("footer").css({"color":"#ccc","background":"#bbb"});
//         $(".plus-frame").css({"color":"#ccc","background":"#bbb"});

    });
    $("#search-close").on("click",function(event){
        event.stopPropagation();
        event.preventDefault();
        $(".modal").addClass("no-display");
        $("#overlay").css({background: "white", opacity: 1});
    });
    var buttons= ["b0","b1","b2","b3","b4","b5",
    "b6","b7"];


    $(".roll").hover(function(){

        var n = Math.floor(Math.random() * buttons.length);
        for(var i=0;i <8;i++){
            $("."+ buttons[i]).css({"top":(i-n)*100+"%"});
        }
    },
    function(){
        for(var i=0; i <8; i++){
            $("." + buttons[i]).css({"top":i*100+"%"});
        }
    }
    );
});