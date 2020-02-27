//根据类别控制
function category(categoryId) {
    $("#article-content").html("");
    leftCategoryList(categoryId);
    askArticleList(categoryId);
}

//左侧分类栏样式控制
function leftCategoryList(category) {
    if (category == null)
        category = 1;

    $("#left-float ul li").slice(category - 1, category).css('background-color', '#f44444');
    $("#left-float ul li a span").slice(category - 1, category).css("color", "#fff");
    leftCategoryListUnChecked(category);
    return;
}

//样式控制2
function leftHover() {
    $("#left-float ul li").hover(function () {
        $("#left-float ul li").css({"background-color": "#f44444", "color": "#fff"});
    })
}

//设置未选中

function leftCategoryListUnChecked(category) {
    $("#left-float ul li").slice(0, category - 1).css('background-color', '#fff');
    $("#left-float ul li a span").slice(0, category - 1).css("color", "#707070");

    $("#left-float ul li").slice(category, 20).css('background-color', '#fff');
    $("#left-float ul li a span").slice(category, 20).css("color", "#707070");
    return;
}


//请求文章信息
function askArticleList(category, currentPage, pageSize) {
    if(category==undefined)
        category = 1;
    if (currentPage == undefined || pageSize == undefined) {
        pageSize = 6;
        currentPage = 0;
    }

    $("#category").val(category);
    $.post(
        "/findArticle?category=" + category + "&currentPage=" + currentPage + "&pageSize=" + pageSize
        , {}
        , function (data) {
            $("#currentPage").val(parseInt(data.currentPage)+parseInt(data.pageSize));
            $("#pageSize").val(data.pageSize);
            $("#pageCount").val(data.pageCount);
            $("#totalItem").val(data.totalItem);
            addContent(eval(data.pageItems));
        }
    );
}

//根据滑条滑动的位置请求新的内容
function listenerAskArticle() {
    var margin = 0;
    var top;
    var currentPage;
    var pageCount;
    var pagesize;
    var pageItem;
    var pre = 0;
    var maxtop = -1;
    var preCurrent = -1;
    $(window).scroll(function () {
        top = $(document).scrollTop();
        //记录最大位置
        if(top>maxtop){
            maxtop = top;
        }
        //到达底端
        if($(window).height<=pre+10){
            pre = top-10;
        }
        currentPage = parseInt($("#currentPage").val())-1;
        pageCount = parseInt($("#pageCount").val());
        pagesize = parseInt($("#pagesize").val());
        pageItem = parseInt($("#pageItem").val());
        margin = pre-top;
        //所有文章展示完成
        if (currentPage > pageItem||preCurrent>=currentPage) {
            return;
        }else if (top>pre) {
            pre = top;
            preCurrent = currentPage;
            askArticleList($("#category").val(),currentPage+1, 1);
        }
    });
}


//添加内容
function addContent(data) {
    var li = "";
    for (var i in data) {
        li += "<li><a class=\"title\" href='/findArticleById?id=" + data[i].aid + "'>" + data[i].title + "</a>\n" +
            "                <div class=\"summary-content\">\n" + data[i].summary +
            "                </div>\n" +
            "\n" +
            "                <dl>\n" +
            "                    <dt><a>\n" +
            "                        <img src='/img/head_icon/" + data[i].img + "'/>\n" +
            "                    </a></dt>\n" +
            "                    <dt>\n" +
            "                        <a href='/getUserInfoById?uid=" + data[i].uid + "' class=\"username\">\n" + data[i].uname +
            "                        </a>\n" +
            "                    </dt>\n" +
            "                </dl>\n" +
            "                <div class=\"footnote\">\n" +
            "                    <span>阅读数 " + data[i].likenum + "&nbsp;|&nbsp;</span>\n" +
            "                    <span>\n" +
            "                    评论\n" + data[i].commentNum + "                </span>\n" +
            "                </div>\n" +
            "            </li>";
    }
    var html = $("#article-content").html()+li;
    $("#article-content").html(html);
    return;
}


/***
 * 请求右侧推荐内容
 */
function askRightList(){
    $.get(
        "/getRecommendArticle"
        ,{}
        ,function(data){
            addRightList(eval(data));
        }
    )
}

/***
 * 添加右侧推荐元素
 */
function addRightList(data) {
    var list="";
    for(var i in data){
        list += ' <li> <div class="right-recommend-img"><a href="findArticleById?id='+data[i].aid+'"><img src="/img/head_icon/'+data[i].img+'"></a></div>' +
            '  <div class="right-recommend-title"><a href="/findArticleById?id='+data[i].aid+'">'+data[i].title+'</a></div>' +
            '         </li>';
    }
    $("#list-recommend-content").html(list);
}

