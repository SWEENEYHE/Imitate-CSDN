<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>浏览文章</title>
    <link rel="stylesheet" href="../css/decorate.css">
    <link rel="stylesheet" href="../css/font-awesome/css/font-awesome.min.css">
    <script src="/js/jquery-1.12.1.min.js"></script>
    <script src="/js/main.js"></script>

</head>

<body>

<!--导航栏-->
<iframe name="" frameborder="0" scrolling="no" marginwidth="0" marginheight="0" width="100%" height="38px"
        src="/html/navigation.html"></iframe>
<!--导航栏结束-->

<!--容器-->
<div class="container">
    <!--左侧信息栏-->
    <div class="left-container">

        <!--first-container-->
        <div class="first-container">
            <div class="first-first-container">
                <div class="first-head-logo">
                    <img src="/img/head_icon/${user.img}"/>
                </div>
                <div class="first-container-name">
                    <span class="first-container-name">${user.uname}</span>
                    <div class="down-button">
                        <ul>
                            <li>
                                <a href="/findUserDetailedById?id=${user.uid}"><span class="first-container-link">TA的个人主页></span></a>
                            </li>
                            <li>
                                <div class="button-small-yellow">
                                    <a>私信</a>
                                </div>
                            </li>
                            <li>
                                <div class="button-small-red">
                                    <a>关注</a>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>

            </div>

            <div class="first-content-data">
                <dl>
                    <dt><a>原创</a></dt>
                    <dd><span class="content-data">${userInfo.uarticlenum}</span></dd>
                </dl>
                <dl>
                    <dt><a>粉丝</a></dt>
                    <dd><span class="content-data">${userInfo.ufannum}</span></dd>
                </dl>
                <dl>
                    <dt><a>获赞</a></dt>
                    <dd><span id="up-all-num" class="content-data">${userInfo.uupnum}</span></dd>
                </dl>
                <dl>
                    <dt><a>评论</a></dt>
                    <dd><span id="comment-all-num" class="content-data">${userInfo.ucommentnum}</span></dd>
                </dl>
            </div>
            <div class="first-end-container">
                <dl>
                    <dt>访问:</dt>
                    <dd>
                        <span class="grade-box-rankA">${userInfo.uviewnum}</span>
                    </dd>
                </dl>
                <dl>
                    <dt>周排名:</dt>
                    <dd>
                        <a class="grade-box-rankA" href="#">
                            163 </a>
                    </dd>
                </dl>
                <dl>
                    <dt>积分:</dt>
                    <dd>
                        <span class="grade-box-rankA">${userInfo.ucoin}</span>
                    </dd>
                </dl>
                <dl>
                    <dt>总排名:</dt>
                    <dd>
                        <a class="grade-box-rankA" href="#">
                            6648 </a>
                    </dd>
                </dl>
            </div>

            <div class="first-footer-container">
                <div class="icon-div">
                    <div class="name-div">勋章:</div>
                    <div class="img-div">
                        <img src="../img/icon-hardworking.png">
                    </div>
                </div>
                <dl class="degree-div">
                    <div class="name-div">
                        等级:
                    </div>
                    <div class="img-div">
                        <img src="../img/blog6.png"/>
                    </div>
                </dl>
            </div>

        </div>
        <!--first-container-end-->

        <!--second-container-->
        <div class="second-container">
            <div class="decorate-row-line-red left-location"></div>
            <div class="second-title left-location-title font-title"><h3>微信扫码捐献：</h3></div>
            <div class="qr-code left-location-qr-code">
                <img src="../img/QR-code.png"/>
            </div>
        </div>
        <!--second-container-end-->

        <div class="third-container">
            <div class="decorate-row-line-red left-location"></div>
            <div class="second-title left-location-title font-title"><h3>最新文章：</h3></div>
            <div class="aside-content">
                <ul class="inf_list clearfix csdn-tracking-statistics tracking-click" data-mod="popu_382">
                    <li class="clearfix">
                        <a href="https://blog.csdn.net/fendouaini/article/details/102561521" target="_blank">
                            成为视频分析专家：自动生成视频集锦(Python实现) </a>
                    </li>
                    <li class="clearfix">
                        <a href="https://blog.csdn.net/fendouaini/article/details/102518748" target="_blank">
                            10行Python代码实现目标检测 </a>
                    </li>
                    <li class="clearfix">
                        <a href="https://blog.csdn.net/fendouaini/article/details/102499705" target="_blank">
                            MNIST数据集介绍 </a>
                    </li>
                    <li class="clearfix">
                        <a href="https://blog.csdn.net/fendouaini/article/details/102475550" target="_blank">
                            图像的特征工程：HOG特征描述子的介绍 </a>
                    </li>
                    <li class="clearfix">
                        <a href="https://blog.csdn.net/fendouaini/article/details/102454320" target="_blank">
                            3分钟内实现人脸检测 </a>
                    </li>
                </ul>
            </div>
        </div>


        <div class="fourth-container">
            <div class="decorate-row-line-red left-location"></div>
            <div class="second-title left-location-title font-title"><h3>热门文章：</h3></div>
            <div class="aside-content">
                <ul class="hotArticle-list csdn-tracking-statistics tracking-click">
                    <li>

                        <a href="https://blog.csdn.net/fendouaini/article/details/80198994">
                            使用Keras进行深度学习：（六）LSTM和双向LSTM讲解及实践 </a>
                        <p class="read">阅读数 <span>26601</span></p>
                    </li>
                    <li>

                        <a href="https://blog.csdn.net/fendouaini/article/details/82027310">
                            干货 | 史上最全中文分词工具整理 </a>
                        <p class="read">阅读数 <span>19028</span></p>
                    </li>
                    <li>

                        <a href="https://blog.csdn.net/fendouaini/article/details/79885721">
                            【干货】史上最全的Tensorflow学习资源汇总 </a>
                        <p class="read">阅读数 <span>16077</span></p>
                    </li>
                    <li>

                        <a href="https://blog.csdn.net/fendouaini/article/details/100910368">
                            2所非211院校挺进全球高校600强，甩开一众985 | 泰晤士世界大学排行榜出炉 </a>
                        <p class="read">阅读数 <span>15838</span></p>
                    </li>
                    <li>

                        <a href="https://blog.csdn.net/fendouaini/article/details/79789440">
                            一文彻底搞懂BP算法：原理推导+数据演示+项目实战（上篇） </a>
                        <p class="read">阅读数 <span>14712</span></p>
                    </li>
                </ul>
            </div>
        </div>
        <!--右下角脚注-->
        <div class="right-footer" id="right-footer">
            <div class="content-right-footer">
                <div class="content-dd deep-font">
                    <div class="content-dd-item">
                        <img src="../img/dd-1.png"/>
                        <span>CSDN学院</span>
                    </div>
                    <div class="content-dd-item">
                        <img src="../img/dd-2.png"/>
                        <span>CSDN招聘</span>
                    </div>
                    <div class="decorate-column"></div>
                </div>
                <div class="footer-notes">
                    <ul class="footer-notes-ul normal-font">
                        <li>
                            <div class="fa fa-qq" aria-hidden="true"></div>
                            <span> QQ客服</span>
                        </li>
                        <li>
                            <div class="fa fa-envelope"></div>
                            <span>kefu@csdn.net</span>
                        </li>
                        <li>
                            <div class="fa fa-comment"></div>
                            <span>客服论坛</span>

                        </li>
                        <li>
                            <div class="fa fa-phone"></div>
                            <span>400-660-0108</span>

                        </li>
                    </ul>
                    <div class="footer-work-time normal-font">
                        工作时间 8:30-22:00
                    </div>
                    <div class="decorate-column margin-left-8x"></div>
                </div>
                <div>
                    <ul class="about-us-ul hightline-font margin-left-4x margin-top-5x">
                        <li>关于我们</li>
                        <li>
                            <div class="decorate-row-line-1x grey-color"></div>
                        </li>
                        <li>招聘</li>
                        <li>
                            <div class="decorate-row-line-1x grey-color"></div>
                        </li>
                        <li>广告服务</li>
                        <li>
                            <div class="decorate-row-line-1x grey-color"></div>
                        </li>
                        <li>网站地图</li>
                    </ul>
                </div>
                <div class="baidu-div pale-font">
                    <i class="fa fa-paw"></i>百度提供站内搜索 京ICP备19004658号
                    ©1999-2019 北京创新乐知网络技术有限公司
                </div>
                <div class="decorate-column margin-left-8x"></div>
                <div class="friend-link-ul">
                    <ul class=" normal-font">
                        <li>网络110报警服务</li>
                        <li>经营性网站备案信息</li>
                        <li>北京互联网违法和不良信息举报中心</li>
                        <li>中国互联网举报中心</li>
                        <li>家长监护</li>
                        <li>版权申诉</li>
                    </ul>
                </div>

            </div>
        </div>
        <!--右边栏结束-->
    </div>
    <!--左侧信息栏结束-->

    <!--右侧内容栏-->
    <div class="right-container">
        <div class="blog-content-head">
            <div class="blog-title">
                <div class="button-small-red title-location">
                    原创
                </div>
                <h2 class="title-location">${article.title}</h2>
            </div>
            <div class="blog-authority-notes">
                <ul>
                    <li>
                        <span>${article.time}</span>
                    </li>
                    <li>
                        <a>${article.uname}</a>
                    </li>
                    <li>
                        <span>阅读数 11111</span>
                    </li>
                    <li>
                        <c:if test="${sessionScope.user.uid==article.uid}">
                            <a href="${pageContext.request.contextPath}/editor/editor.html?aid=${article.aid}">编辑</a>
                        </c:if>
                    </li>
                </ul>
            </div>
            <div class="decorate-line-blog-content"></div>

            <div class="copyright-notes">
                版权声明：本文为博主原创文章，遵循 <a>CC 4.0 BY-SA</a> 版权协议，转载请附上原文出处链接和本声明。
                <br/>
                <span>本文链接：<a>${pageContext.request.contextPath}/findArticleById?id=${article.aid}</a></span>
            </div>
        </div>
        <div class="blog-content">
            <div id="article_content" class="article_content clearfix">
                <div class="article-copyright">
                    <a rel="license" href="http://creativecommons.org/licenses/by-sa/4.0/">
                    </a>
                  ${article.acontent}
                </div>
            </div>
        </div>

        <div class="blog-end">
            <div class="decorate-line-blog-content"></div>
            <div class="blog-content-end-note">有0个人打赏</div>
            <div class="font-pale location-right-footer">文章最后发表于:2019-01-07 13:07:06</div>
            <div class="decorate-line-blog-content"></div>
        </div>
        <!--右侧内容栏结束-->


        <div class="blog-comments">
            <div class="head-img-comment">
                <img src="/img/head_icon/${sessionScope.user.img}"/>
            </div>
            <textarea class="blog-comment-content-textarea" id="comment-editor" placeholder="想对作者说点什么"></textarea>
            <div class="comment-list">
                <ul class="comment-list-ul">
                    <c:forEach var="comment" items="${comments}" varStatus="status">
                        <li>
                            <div class="head-img-comment">
                                <img src="/img/head_icon/${userMin[status.index].img}"/>
                            </div>
                            <div class="comment-time">
                                <strong>${userMin[status.index].uname}</strong> <span>${comment.commenttime}&nbsp;&nbsp;#${status.index+1}楼</span>
                            </div>
                            <div class="comment-content">
                                ${comment.commentcontent}
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
    <!--容器结束-->

    <div class="float-menu" id="left-float">
        <ul>
            <li id="upnum-onclick"onclick="upbutton(${article.aid})"><div ><i class="fa fa-thumbs-o-up" aria-hidden="true"></i><span id="likenum" class="font-samll-btn">${article.likenum}</span></div></li>
            <li><div><i class="fa fa-share-alt" aria-hidden="true"></i></div></li>
            <li><div><i class="fa fa-commenting-o" aria-hidden="true"></i><span id="comment-num" class="font-samll-btn">${comments.size()}</span></div></li>
            <li onclick="showSecondCategory('float-right-second-menu')"><div><i class="fa fa-list" aria-hidden="true" ></i></div></li>
            <li><div><i class="fa fa-bookmark-o" aria-hidden="true"></i></div></li>
            <li><div><i class="fa fa-mobile" aria-hidden="true"></i></div></li>
            <li onclick="upToTop()"><div><i class="fa fa-angle-up" aria-hidden="true"></i></div></li>
        </ul>
       <div class="second-menu font-size-category" id="float-right-second-menu">
           <ul>
               <li>1.三角矩阵的概念</li>
               <li>2.压缩原理</li>
               <li>3.矩阵坐标...</li>
           </ul>
           <div class="second-menu-bottom">
               <div><i class="fa fa-angle-up" aria-hidden="true"></i></div>
               <div><i class="fa fa-angle-down" aria-hidden="true"></i></div>
           </div>
       </div>
    </div>
</div>
</body>
<!--隐藏数据域-->
<form id = "comment-form" style="display: none" method="post">
    <input name="articleId" value="${article.aid}">
    <input id="comment" name="comment" value="">
</form>
<input id = "floor" style="display: none" value=${comments.size()}>
<input id = "user_img" style="display: none" value=${sessionScope.user.img}>
<input id = "user_name" style="display: none" value=${sessionScope.user.uname}>

<script>
    scrollElem(1);
    var floor = parseInt($("#floor").val())+1;
    var flag = 0;




    //二级菜单显示
    function showSecondCategory(item){
        if($("#"+item).css('display')=='block'){
            $("#"+item).css('display','none');
        }else{
            $("#"+item).css('display','block');
        }
    }

    //回到顶部
    var timeout;
   function upToTop(){
      timeout = setInterval(scrollUpHumanly,30);
    }

      function scrollUpHumanly(){
        var top = $(document).scrollTop();
        var speed = top / 12;
        if(top>0){
            top = $(document).scrollTop();
            if (top-speed>=2) {
                top -=speed;
            }else {
                top = 0;
                clearInterval(timeout);
            }
            $(document).scrollTop(top);
        }
    }






</script>

<script src="/js/watchBlog.js"></script>
<!--必须放在尾部-->
<link rel="stylesheet" href="../css/watchBlog.css">

</html>