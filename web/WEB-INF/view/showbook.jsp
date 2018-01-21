<%--
  Created by IntelliJ IDEA.
  User: dongmengyuan
  Date: 18-1-6
  Time: 下午1:54
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="clear" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>linux图书管理</title>
    <meta name="viewport" content="width=device-width,inital-scale=1,maxmum-scale=1,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="HandleFriendly" content="true">
    <!--font-awesome矢量图标-->
    <link href="/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/index.css" rel="stylesheet">
    <link href="/css/index1.css" rel="stylesheet">
    <link href="/css/showbook.css" rel="stylesheet">
</head>
<body>
<header>
    <div id="hea">
        <img id="index_head" src="/img/index_head.png" />
        <a id="head" href="main.jsp">XiyouLinux Group 图书借阅</a>
        <div id="index1_input">
            <input type="text" placeholder="搜索书名/作者/归属者">
            <button class="btn btn-link">提交</button>
            <a href="mybooks.html"><i class="fa fa-file-text fa-fw"></i>我的书籍</a>
            <a href="pushbook.html"><i class="fa fa-tags fa-fw"></i>上传数据</a>
        </div>
        <a id="index1_sign" href="index.jsp">退出登录</a>
    </div>
</header>
<div id="main">
    <div id="main_head"><a>首页</a><i class="fa fa-lg fa-angle-right"></i> 图书详情
    </div>
    <div id="con">
        <div class="rows">
            <c:forEach items="${bookMap}" var="books" >
                <div class="col-xs-12 col-md-6">
                    <img src="/img/book0.jpeg">
                </div>
                <div class="col-xs-12 col-md-6">
                    <p>书名：<span>《${books.key.ugkName}》</span></p>
                    <p>作者：<span>${books.key.author}</span></p>
                    <p>归属者：<span>${books.value}</span></p>
                    <p>被借<span>${borrowCount}</span>次</p>
                    <p>描述：<span>${books.key.describ}</span></p>
                    <p><button class="btn btn-primary" onclick="borrowBook(${books.key.pkId},${uid})">点我借阅</button></p>
                </div>
                <div style="clear:both"></div>
            </c:forEach>
        </div>
    </div>
    <div id="talk">
        <p>评论<span>(${contentCount})</span></p>
        <c:forEach items="${content}" var="content">
            <div>
                <img src="/img/index_head.png">
                <div>
                    <p>${content.value}</p>
                    <p>${content.key.content}</p>
                    <p>${content.key.commentDatetime}</p>
                </div>

            </div>
        </c:forEach>
        <div id="index_pingination">
            <ul class="pagination">
                <li><a href="#">&laquo;</a></li>
                <li class="pa_in"><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
                <li><a href="#">&raquo;</a></li>
            </ul>
        </div>

    </div>
    <div id="italk">
        <p>我要评论</p>
        <textarea id="content"></textarea>
        <button class="btn btn-success" onclick="submitContent(${book.pkId})">提交评论</button>
    </div>
</div>

<footer>
    <div class="rows">
        <div class="col-xs-6 col-md-3">
            <p class="footer_head">友情链接</p>
            <p><a>西邮Linux兴趣小组</a></p>
            <p><a>西安邮电大学</a></p>
            <p><a>西安邮电大学计算机学院</a></p>
            <p><a>linux内核之旅</a></p>
            <p><a>The Linux Kernel Archives</a></p>
            <p><a>The Linux Foundation</a></p>
        </div>
        <div class="col-xs-6 col-md-3">
            <p class="footer_head">社区</p>
            <p>邮件列表：<a>xiyoulinux</a></p>
            <p>新浪微博：<a>@西邮Linux兴趣小组</a></p>
            <p>GUN：<a>GUN's Not Unix</a></p>
            <p>LWN：<a>Linux Weekly News</a></p>
            <p>Linux Story：<a>Linux Story</a></p>
        </div>
        <div class="col-xs-6 col-md-3">
            <p class="footer_head">联系我们</p>
            <p><span><i class="fa fa-map-marker"></i>地址：陕西省 西安市 长安区 西安邮电大学长安校区 东区 教学实验楼 FZ118</span></p>
            <p><span><i class="fa fa-envelope"></i>邮编：710121</span></p>
        </div>
        <div class="col-xs-6 col-md-3">
            <p class="footer_head">关注我们</p>
            <img src="img/weixin.jpg">
        </div>
        <div style="clear: both;height:0;"></div>
    </div>
    <div id="foot">
        <p>Copyright @ 2006-2017 西邮Linux兴趣小组 </p>
        <p>All Rights Reserved</p>
    </div>
</footer>
<script type="text/javascript" src="/js/jquery.min.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/canvas1.js"></script>
<script type="text/javascript" src="/js/index.js"></script>
<script type="text/javascript">

    function borrowBook(pk_id,uid) {
        $.post("/showbook/borrowBook",{"bookInfoPkId":pk_id,"csUserId":uid},function(data){
            if(data.length==7){
                alert("您已借阅过该本书");
            }else if(data.length==4){
                alert("借阅成功");
                window.location.href="/mybook/getBook";
            }else{
                alert("请先登录后操作");
                window.location.href="/bookmanager";
            }
        });
    }

    function submitContent(pk_id) {
        var uid = "${uid}";
        var reg = /^(\s)*$/;


        var s = $("#content").val();


        if(uid==""){
            alert("您未登录");
        }else if(reg.test(s)){
            alert("评论格式非法！！！！");
        }else{
            $.post("/showbook/submitContent",{"csUserId":uid,"bookInfoPkId":pk_id,"content":$("#content").val()},function(data){
                alert("评论提交成功");
                window.location.reload();
            });
        }

    }
</script>
</body>
</html>
