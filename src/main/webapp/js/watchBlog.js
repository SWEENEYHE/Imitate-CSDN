
//评论区获得焦点
$("#comment-editor").click(function () {
    if($("#user_name").val()==''){
        alert("尚未登陆请先登陆");
        window.location.href="/html/login.html?reference="+window.location.pathname+window.location.search;
    }
    if (flag == 0) {
        var e = "<div id='hidden-element'><div class='font-pale  '>还能输入1000个字符</div><div id='submit-button' class='submit-button' >发表评论</div></div>";
        $(this).after(e);
        flag = 1;
    }
    return;
});
//点击提交
$(document).on('click','#submit-button',(function () {
    var comment = $("#comment-editor").val();
    var user_name = $("#user_name").val();
    var user_img = "/img/head_icon/"+$("#user_img").val();

    //判断评论是否为空
    if(comment.length==0){
        alert("评论为空");
        return;
    }
    $("#comment").val(comment);
    uploadComment();

    $("#hidden-element").remove();

    addComment(floor++,comment,user_name,user_img);
    //设置右侧评论数量
    var commentnum = $("#comment-num").html();
    $("#comment-num").html(parseInt(commentnum)+1);

    //左侧评论数量
    commentnum = $("#comment-all-num").html();
    $("#comment-all-num").html(parseInt(commentnum)+1);

    $("#comment-editor").val("");
    var height = $(".right-container").css('height');
    height= parseInt(height);
    height+=70;
    var strHeight = height.toString()+'px';
    $(".right-container").height(strHeight);
    flag = 0;
}));

/***
 * 提交评论
 */

function uploadComment(){
    $.post(
        "/addComment"
        ,$("#comment-form").serialize()
    )
}




//添加新的评论
function addComment(floor,content,username,img){

    var comment = "<li><div class=\"head-img-comment\">\n" +
        "            <img src="+img+">\n" +
        "            </div>\n" +
        "            <div class=\"comment-time\">\n" +
        "            <strong>"+username+"</strong> <span>刚刚&nbsp;&nbsp;#"+floor+"楼</span>\n" +
        "        </div>\n" +
        "        <div class=\"comment-content\">\n" + content +
        "            </div>\n" +
        "            </li>";
    var html = $(".comment-list-ul").html();
    $(".comment-list-ul").html(html+comment);
}

function upbutton(aid){
    //点赞后取消触发事件
    $("#upnum-onclick").attr("onclick","");
    $("#upnum-onclick").css({"width":"100%","text-align":"center"
        ,"border-bottom":"rgb(0,0,0,.1) solid 1px","background":"#FFFFFF","color":"#ca0c16"});

    var likenum = $("#likenum").html();
    $("#likenum").html(parseInt(likenum)+1);

    likenum = $("#up-all-num").html();
    $("#up-all-num").html(parseInt(likenum)+1);


    $.post(
        "/upArticle"
        ,{"articleId":aid}
    )
}
