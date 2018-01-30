<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>XiyouLinux Group图书借阅</title>
    <meta name="viewport" content="width=device-width,inital-scale=1,maxmum-scale=1,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="HandleFriendly" content="true">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/xiyoulinux.png">
    <!--font-awesome矢量图标-->
    <link href="${pageContext.request.contextPath}/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/index1.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/pushbook.css" rel="stylesheet">
</head>

<body>
<header>
    <div id="hea">
        <img id="index_head" src="${pageContext.request.contextPath}/img/index_head.png"/>
        <a id="head" href="${pageContext.request.contextPath}/auth/">XiyouLinux Group 图书借阅</a>
        <form id="index1_input" action="${pageContext.request.contextPath}/auth/search" method="post">
            <input type="text" placeholder="搜索书名/作者/归属者" name="keyword">
            <button class="btn btn-link">搜索</button>
            <a href="${pageContext.request.contextPath}/auth/mybook/"><i class="fa fa-file-text fa-fw"></i>我的书籍</a>
            <a href="${pageContext.request.contextPath}/auth/upload"><i class="fa fa-tags fa-fw"></i>上传书籍</a>
        </form>
        <a id="index1_sign" href="${pageContext.request.contextPath}/logout.do">退出登录</a>
    </div>
</header>

<div id="main">
    <p class="head">上传数据(分类一旦选定后期不可更改，请谨慎选择)</p>
    <hr>
    <form action="${pageContext.request.contextPath}/auth/upload.do" method="post" enctype="multipart/form-data">
        <div class="rows" id="choose">
            <div class="col-xs-12 col-md-4"></div>
            <div class="col-xs-12 col-md-2">
                <input type="file" name="bookPicture">
                <p style="font-size: 15px">您可以上传书本的图片</p>
            </div>
            <div style="clear:both"></div>
        </div>
        <div class="rows con">
            <div class="col-xs-12 col-md-3">
                书名：
            </div>
            <div class="col-xs-12 col-md-6">
                <input type="text" name="bookName" required>
            </div>
            <div style="clear:both"></div>
        </div>
        <div class="rows con">
            <div class="col-xs-12 col-md-3">
                作者：
            </div>
            <div class="col-xs-12 col-md-6">
                <input type="text" name="author">
            </div>
            <div style="clear:both"></div>
        </div>
        <div class="rows con">
            <div class="col-xs-12 col-md-3">
                数量：
            </div>
            <div class="col-xs-12 col-md-6">
                <input type="text" name="amount" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}"
                       required>
            </div>
            <div style="clear:both"></div>
        </div>
        <div class="rows con">
            <div class="col-xs-12 col-md-3">
                所属分类：
            </div>
            <div class="col-xs-12 col-md-6">
                <input type="text" name="types" id="select_input" required readonly>
                <button type="reset" class="btn">清空</button>
                <select id="select" name="one" onchange="changeoption(this.selectedIndex)">
                    <option>请选择</option>
                    <option value="编程语言">编程语言</option>
                    <option value="数据结构与算法">数据结构与算法</option>
                    <option value="软件工程">软件工程</option>
                    <option value="数据库">数据库</option>
                    <option value="操作系统">操作系统</option>
                    <option value="计算机网络">计算机网络</option>
                    <option value="Web后台">Web后台</option>
                    <option value="Web前端">Web前端</option>
                    <option value="人工智能">人工智能</option>
                    <option value="大数据与云计算">大数据与云计算</option>
                    <option value="计算机底层分析与开发工具">计算机底层分析与开发工具</option>
                    <option value="计算机安全">计算机安全</option>
                </select>
                <ul id="ul">
                </ul>
            </div>
            <div style="clear:both"></div>
        </div>
        <div class="rows con">
            <div class="col-xs-12 col-md-3">
                描述：
            </div>
            <div class="col-xs-12 col-md-6">
                <textarea name="describ" placeholder="请用简短的话来描述这本书，200字以内～" cols="50" rows="5"></textarea>
            </div>
            <div style="clear:both"></div>
        </div>
        <p>
            <button type="submit" class="btn btn-success">提交</button>
        </p>
    </form>
</div>

<footer>
    <div class="rows">
        <div class="col-xs-6 col-md-3">
            <p class="footer_head">友情链接</p>
            <p><a href="https://www.xiyoulinux.org">西邮Linux兴趣小组</a></p>
            <p><a href="http://blog.xiyoulinux.org">西邮Linux兴趣小组 群博</a></p>
            <p><a href="http://www.xiyou.edu.cn/jgsz/yxsz1/jsj31.htm">西安邮电大学计算机学院</a></p>
            <p><a href="http://kerneltravel.eefocus.com">Linux内核之旅</a></p>
            <p><a href="https://www.kernel.org">The Linux Kernel Archives</a></p>
            <p><a href="https://www.linuxfoundation.org">The Linux Foundation</a></p>
        </div>
        <div class="col-xs-6 col-md-3">
            <p class="footer_head">社区</p>
            <p>邮件列表：<a href="https://groups.google.com/forum/#!forum/xiyoulinux">xiyouLinux</a></p>
            <p>新浪微博：<a href="https://weibo.com/xylinux">@西邮Linux兴趣小组</a></p>
            <p>GNU：<a href="https://www.gnu.org">GNU's Not Unix</a></p>
            <p>LWN：<a href="https://lwn.net">Linux Weekly News</a></p>
            <p>Linux Story：<a href="https://linuxstory.org">Linux Story</a></p>
        </div>
        <div class="col-xs-6 col-md-3">
            <p class="footer_head">联系我们</p>
            <p><span><i class="fa fa-map-marker"></i>地址：陕西省 西安市 长安区 西安邮电大学长安校区 东区 教学实验楼 FZ118</span></p>
            <p><span><i class="fa fa-envelope"></i>邮编：710121</span></p>
        </div>
        <div class="col-xs-6 col-md-3">
            <p class="footer_head">关注我们</p>
            <img src="${pageContext.request.contextPath}/img/weixin.jpg">
        </div>
        <div style="clear: both;height:0;"></div>
    </div>
    <div id="foot">
        <p>Copyright &#169; 2006-2018 西邮Linux兴趣小组 </p>
        <p>All Rights Reserved</p>
    </div>
</footer>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/canvas1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/pushbooks.js"></script>
</body>

</html>