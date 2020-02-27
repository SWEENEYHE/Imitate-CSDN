function scrollElem(top_init) {
    //窗口滑动
    $(document).scroll(function () {
        var m = $(this).scrollTop();
        if (m > 50) {
            $("#left-float").css("top", m - 50);
        }
        else {
            $("#left-float").css("top", top_init);
        }
        if (m > 1250) {
            $("#right-footer").css("margin-top", m - 1250);
        } else {
            $("#right-footer").css("margin-top", 6);
        }
    })
}


