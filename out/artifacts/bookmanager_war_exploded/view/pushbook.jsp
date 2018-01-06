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
    <link href="/css/pushbook.css" rel="stylesheet">
</head>

<body>
<header>
    <div id="hea">
        <img id="index_head" src="/img/index_head.png"/>
        <a id="head" href="/index1.html">XiyouLinux Group 图书借阅</a>
        <div id="index1_input">
            <input type="text" placeholder="搜索书名/作者/归属者">
            <button class="btn btn-link">提交</button>
            <a href="mybooks.html"><i class="fa fa-file-text fa-fw"></i>我的书籍</a>
            <a href="pushbook.jsp"><i class="fa fa-tags fa-fw"></i>上传数据</a>
        </div>
        <a id="index1_sign" href="index.html">退出登录</a>
    </div>
</header>

<div id="main">
    <p class="head">上传数据</p>
    <hr>
    <form method="post" enctype="multipart/form-data" >
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
                <input type="text" name = "amount" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" required>
            </div>
            <div style="clear:both"></div>
        </div>
        <div class="rows con">
            <div class="col-xs-12 col-md-3">
                所属分类：
            </div>
            <div class="col-xs-12 col-md-6">
                <input type="text" name="types" id="select_input" required readonly>
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
            <button class="btn btn-success">提交</button>
        </p>
    </form>
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
<script type="text/javascript" src="/js/jquery.min.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/canvas1.js"></script>
<script type="text/javascript" src="/js/index.js"></script>
<script type="text/javascript" src="/js/pushbooks.js"></script>
</body>

</html>