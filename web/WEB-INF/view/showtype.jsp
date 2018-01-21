<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

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
    <link href="/css/showtype.css" rel="stylesheet">
</head>

<body>
<header>
    <div id="hea">
        <img id="index_head" src="/img/index_head.png"/>
        <a id="head" href="index1.html">XiyouLinux Group 图书借阅</a>
        <div id="index1_input">
            <input type="text" placeholder="搜索书名/作者/归属者">
            <button class="btn btn-link">提交</button>
            <a href="mybooks.html"><i class="fa fa-file-text fa-fw"></i>我的书籍</a>
            <a href="pushbook.html"><i class="fa fa-tags fa-fw"></i>上传数据</a>
        </div>
        <a id="index1_sign" href="index.html">退出登录</a>
    </div>
</header>
<div id="main">
    <div id="main_head"><a>首页</a><i class="fa fa-lg fa-angle-right"></i><a
            href="${pageContext.request.contextPath}/tags">全部标签</a>
        <i class="fa fa-lg fa-angle-right"></i>${labelname}
    </div>
    <c:forEach items="${books}" var="book">
        <div class="rows" id="con">
            <div class="col-xs-12 col-md-2 book_img">
                <img src="${book.key.bookPicture}">
            </div>
            <div class="book_info col-xs-12 col-md-8">
                <p>《${book.key.ugkName}》----- ${book.key.author}</p>
                <p>${book.key.describ}</p>
                <p><span><i class="fa fa-user"></i>${book.value}</span><span><i
                        class="fa fa-book"></i>${book.key.amount}本</span>
                    <span><i class="fa  fa-clock-o"></i>${book.key.uploadDate}</span>
                </p>
            </div>
            <div class="col-xs-12 col-md-2">
                <button class="btn"><a href="showbook.html">点我借阅</a></button>
            </div>
            <div style="clear:both"></div>
        </div>
    </c:forEach>

    <div id="index_pingination">
        <ul class="pagination">
            <%--// 当当前页面不是第一页的时候, 要显示"首页"和"<<"按钮--%>
            <c:if test="${pageInfo.currentPage != 1 && pageInfo.totalPage != 0}">
                <li><a href="/bookmanager/1">首页</a></li>
                <li><a href="/bookmanager/${pageInfo.currentPage-1}">&laquo;</a></li>
            </c:if>

            <%--// 当当前页面大于6页的时候, 要显示"[...]"按钮--%>
            <c:if test="${pageInfo.currentPage > 6}">
                <li><a href="/bookmanager/${(pageInfo.currentPage/5-1)*5-1}">[...]</a></li>
            </c:if>

            <%--// 从当前这个五页起始页开始遍历--%>
            <c:forEach varStatus="i" begin="${(pageInfo.currentPage-1)/5*5+1}" end="${(pageInfo.currentPage-1)/5*5+5}">

                <c:if test="${i.count <= pageInfo.totalPage}">
                    <c:if test="${i.count == pageInfo.currentPage}">
                        <li class="pa_in"><a href="#">${pageInfo.currentPage}</a></li>
                    </c:if>
                    <c:if test="${i.count != pageInfo.currentPage}">
                        <li><a href="/bookmanager/${i.count}">${i.count}</a></li>
                    </c:if>
                </c:if>

            </c:forEach>

            <%--// 如果不是最后一个五页的页码, 要在后面显示[...]按钮--%>
            <c:if test="${(pageInfo.currentPage-1)/5*5+1 != (pageInfo.totalPage-1)/5*5+1 && pageInfo.totalPage > 6}">
                <li><a href="/bookmanager/${(pageInfo.currentPage+4)/5*5+1}">[...]</a></li>
            </c:if>

            <%--// 如果不是尾页, 要显示">>"和"尾页"按钮--%>
            <c:if test="${pageInfo.currentPage != pageInfo.totalPage && pageInfo.totalPage != 1 && pageInfo.totalPage != 0}">
                <li><a href="${pageContext.request.contextPath}/label?type=${labelid}&page=${pageInfo.currentPage+1}">&raquo;</a>
                </li>
                <li><a href="${pageContext.request.contextPath}/label?type=${labelid}&page=${pageInfo.totalPage}">尾页</a>
                </li>
            </c:if>
        </ul>
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
            <img src="/img/weixin.jpg">
        </div>
        <div style="clear: both;height:0;"></div>
    </div>
    <div id="foot">
        <p>Copyright @ 2006-2017 西邮Linux兴趣小组 </p>
        <p>All Rights Reserved</p>
    </div>
</footer>
<script type="/text/javascript" src="js/jquery.min.js"></script>
<script type="/text/javascript" src="js/bootstrap.min.js"></script>
<script type="/text/javascript" src="js/canvas1.js"></script>
<script type="/text/javascript" src="js/index.js"></script>
</body>
</html>