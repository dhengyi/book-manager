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
</head>

<body>
<header>
    <div id="hea">
        <img id="index_head" src="${pageContext.request.contextPath}/img/index_head.png"/>
        <a id="head" href="${pageContext.request.contextPath}/">XiyouLinux Group 图书借阅</a>
        <div id="index_input"></div>
        <a id="index_sign" href="javascript:showDialog();"><b>立即登录</b></a>
    </div>
</header>
<div id="main">
    <div id="tag">
        <c:forEach items="${labels}" var="label">
            <a href="${pageContext.request.contextPath}/page/1?tag=${label.pkId}">${label.name}</a>
        </c:forEach>
    </div>

    <div id="left">
        <c:forEach items="${books}" var="book">
            <div class="rows">
                <div class="col-xs-12 col-md-3 book_img">
                    <img src="${book.key.bookPicture}">
                </div>
                <div class="book_info col-xs-12 col-md-9">
                    <p>《${book.key.ugkName}》----- ${book.key.author}</p>
                    <p>${book.key.describ}</p>
                    <p><span><i class="fa fa-user"></i>${book.value}</span>
                        <span><i class="fa fa-book"></i>${book.key.amount}本</span>
                        <span><i class="fa  fa-clock-o"></i>${book.key.uploadDate}</span>
                    </p>
                </div>
                <div style="clear:both"></div>
            </div>
        </c:forEach>

        <%--分页的实现--%>
        <div id="index_pingination">
            <ul class="pagination">
                <%--当当前页面不是第一页的时候, 要显示"首页"和"<<"按钮--%>
                <c:if test="${pageInfo.currentPage != 1 && pageInfo.totalPage != 0}">
                    <c:if test="${labelId == -1}">
                        <li><a href="${pageContext.request.contextPath}/page/1">首页</a></li>
                        <li><a href="${pageContext.request.contextPath}/page/${pageInfo.currentPage-1}">&laquo;</a></li>
                    </c:if>
                    <c:if test="${labelId != -1}">
                        <li><a href="${pageContext.request.contextPath}/page/1?tag=${labelId}">首页</a></li>
                        <li><a href="${pageContext.request.contextPath}/page/${pageInfo.currentPage-1}?tag=${labelId}">&laquo;</a>
                        </li>
                    </c:if>
                </c:if>

                <%--当当前页面大于等于6页的时候, 要显示"[...]"按钮--返回到前一个5页--%>
                <c:if test="${pageInfo.currentPage >= 6}">
                    <c:if test="${labelId == -1}">
                        <li>
                            <a href="${pageContext.request.contextPath}/page/${returnPreFivePage}">[...]</a>
                        </li>
                    </c:if>
                    <c:if test="${labelId != -1}">
                        <li>
                            <a href="${pageContext.request.contextPath}/page/${returnPreFivePage}?tag=${labelId}">[...]</a>
                        </li>
                    </c:if>
                </c:if>

                <%--显示当前页面所有应显示的页码--%>
                <c:forEach varStatus="i" begin="${ELPageValue+1}"
                           end="${ELPageValue+5}" step="${1}">
                    <c:if test="${i.current <= pageInfo.totalPage}">
                        <%--当前页的超链接处理为不可点击--%>
                        <c:if test="${i.current == pageInfo.currentPage}">
                            <li class="pa_in"><a disabled="true">${pageInfo.currentPage}</a></li>
                        </c:if>
                        <c:if test="${i.current != pageInfo.currentPage}">
                            <c:if test="${labelId == -1}">
                                <li><a href="${pageContext.request.contextPath}/page/${i.current}">${i.current}</a></li>
                            </c:if>
                            <c:if test="${labelId != -1}">
                                <li>
                                    <a href="${pageContext.request.contextPath}/page/${i.current}?tag=${labelId}">${i.current}</a>
                                </li>
                            </c:if>
                        </c:if>
                    </c:if>
                </c:forEach>

                <%--如果不是最后一个五页中的页码, 要在后面显示[...]按钮--跳到下一个5页--%>
                <c:if test="${pageInfo.currentPage < isOneOfNextFivePage && pageInfo.totalPage >= 6}">
                    <c:if test="${labelId == -1}">
                        <li>
                            <a href="${pageContext.request.contextPath}/page/${reachNextFivePage}">[...]</a>
                        </li>
                    </c:if>
                    <c:if test="${labelId != -1}">
                        <li>
                            <a href="${pageContext.request.contextPath}/page/${reachNextFivePage}?tag=${labelId}">[...]</a>
                        </li>
                    </c:if>
                </c:if>

                <%--如果不是尾页, 要显示">>"和"尾页"按钮--%>
                <c:if test="${pageInfo.currentPage != pageInfo.totalPage && pageInfo.totalPage != 1 && pageInfo.totalPage != 0}">
                    <c:if test="${labelId == -1}">
                        <li><a href="${pageContext.request.contextPath}/page/${pageInfo.currentPage+1}">&raquo;</a></li>
                        <li><a href="${pageContext.request.contextPath}/page/${pageInfo.totalPage}">尾页</a></li>
                    </c:if>
                    <c:if test="${labelId != -1}">
                        <li><a href="${pageContext.request.contextPath}/page/${pageInfo.currentPage+1}?tag=${labelId}">&raquo;</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/page/${pageInfo.totalPage}?tag=${labelId}">尾页</a>
                        </li>
                    </c:if>
                </c:if>
            </ul>
        </div>
    </div>
    <div style="clear:both"></div>
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
</body>

<style type="text/css">
    body {
        padding: 0px;
        margin: 0px;
        /*font-size: 12px;*/
        font-family: "微软雅黑";
    }

    .link {
        text-align: right;
        line-height: 20px;
        padding-right: 40px;
    }

    .ui-dialog {
        width: 380px;
        height: auto;
        display: none;
        position: absolute;
        z-index: 9000;
        top: 0px;
        left: 0px;
        border: 1px solid #D5D5D5;
        background: #fff;
    }

    .ui-dialog a {
        text-decoration: none;
    }

    .ui-dialog-title {
        height: 48px;
        line-height: 48px;
        padding: 0px 20px;
        color: #535353;
        font-size: 16px;
        border-bottom: 1px solid #efefef;
        background: #f5f5f5;
        cursor: move;
        user-select: none;
    }

    .ui-dialog-closebutton {
        width: 16px;
        height: 16px;
        display: block;
        position: absolute;
        top: 12px;
        right: 20px;
        background: url(/img/close_def.png) no-repeat;
        cursor: pointer;

    }

    .ui-dialog-closebutton:hover {
        background: url(/img/close_def.png);
    }

    .ui-dialog-content {
        padding: 15px 20px;
    }

    .ui-dialog-pt15 {
        padding-top: 15px;
    }

    .ui-dialog-l40 {
        height: 40px;
        line-height: 40px;
        text-align: right;
    }

    .ui-dialog-input {
        width: 100%;
        height: 40px;
        margin: 0px;
        padding: 0px;
        border: 1px solid #d5d5d5;
        font-size: 16px;
        color: #c1c1c1;
        text-indent: 25px;
        outline: none;
    }

    .ui-dialog-input-username {
        background: url(/img/input_username.png) no-repeat 2px;
    }

    .ui-dialog-input-password {
        background: url(/img/input_password.png) no-repeat 2px;
    }

    .ui-dialog-submit {
        width: 100%;
        height: 50px;
        background: #3b7ae3;
        border: none;
        font-size: 16px;
        color: #fff;
        outline: none;
        text-decoration: none;
        display: block;
        text-align: center;
        line-height: 50px;
    }

    .ui-dialog-submit:hover {
        background: #3f81b0;
    }

    .ui-mask {
        width: 100%;
        height: 100%;
        background: #000;
        position: absolute;
        top: 0px;
        height: 0px;
        z-index: 8000;
        opacity: 0.4;
        filter: Alpha(opacity=40);
    }
</style>

<div class="ui-mask" id="mask" onselectstart="return false"></div>
<div class="ui-dialog" id="dialogMove" onselectstart='return false;'>
    <div class="ui-dialog-title" id="dialogDrag" onselectstart="return false;">
        登录通行证
        <a class="ui-dialog-closebutton" href="javascript:hideDialog();"></a>
    </div>
    <div class="ui-dialog-content">
        <form action="${pageContext.request.contextPath}/login.do" method="post">
            <div class="ui-dialog-l40 ui-dialog-pt15">
                <input class="ui-dialog-input ui-dialog-input-username" type="text" placeholder="用户名" name="name"/>
            </div>
            <div class="ui-dialog-l40 ui-dialog-pt15">
                <input class="ui-dialog-input ui-dialog-input-password" type="password" placeholder="密码"
                       name="password"/>
            </div>
            <br>
            <br>
            <br>
            <br>
            <div>
                <input class="ui-dialog-submit" type="submit" name="login" value="登录"/>
            </div>
        </form>
    </div>
</div>

<script type="text/javascript">
    var dialogInstace, onMoveStartId, mousePos = {x: 0, y: 0};	// 用于记录当前可拖拽的对象
    // 获取元素对象
    function g(id) {
        return document.getElementById(id);
    }

    // 自动居中元素（el = Element）
    function autoCenter(el) {
        var bodyW = document.documentElement.clientWidth;
        var bodyH = document.documentElement.clientHeight;

        var elW = el.offsetWidth;
        var elH = el.offsetHeight;

        el.style.left = (bodyW - elW) / 2 + 'px';
        el.style.top = (bodyH - elH) / 2 + 'px';

    }

    // 自动扩展元素到全部显示区域
    function fillToBody(el) {
        el.style.width = document.documentElement.clientWidth + 'px';
        el.style.height = document.documentElement.clientHeight + 'px';
    }

    // Dialog实例化的方法
    function Dialog(dragId, moveId) {
        var instace = {};

        instace.dragElement = g(dragId);	//	允许执行 拖拽操作 的元素
        instace.moveElement = g(moveId);	//	拖拽操作时，移动的元素

        stace = instace;

        instace.mouseOffsetLeft = 0;			//	拖拽操作时，移动元素的起始 X 点
        instace.mouseOffsetTop = 0;			    //	拖拽操作时，移动元素的起始 Y 点

        instace.dragElement.addEventListener('mousedown', function (e) {

            var e = e || window.event;

            dialogInstace = instace;
            instace.mouseOffsetLeft = e.pageX - instace.moveElement.offsetLeft;
            instace.mouseOffsetTop = e.pageY - instace.moveElement.offsetTop;

            onMoveStartId = setInterval(onMoveStart, 10);
            return false;
        })

        return instace;
    }

    // 在页面中侦听 鼠标弹起事件
    document.onmouseup = function (e) {
        dialogInstace = false;
        clearInterval(onMoveStartId);
    }

    document.onmousemove = function (e) {
        var e = window.event || e;
        mousePos.x = e.clientX;
        mousePos.y = e.clientY;

        e.stopPropagation && e.stopPropagation();
        e.cancelBubble = true;
        e = this.originalEvent;
        e && ( e.preventDefault ? e.preventDefault() : e.returnValue = false );

        document.body.style.MozUserSelect = 'none';
    }

    function onMoveStart() {
        var instace = dialogInstace;
        if (instace) {

            var maxX = document.documentElement.clientWidth - instace.moveElement.offsetWidth;
            var maxY = document.documentElement.clientHeight - instace.moveElement.offsetHeight;

            instace.moveElement.style.left = Math.min(Math.max(( mousePos.x - instace.mouseOffsetLeft), 0), maxX) + "px";
            instace.moveElement.style.top = Math.min(Math.max(( mousePos.y - instace.mouseOffsetTop ), 0), maxY) + "px";
        }
    }

    // 重新调整对话框的位置和遮罩，并且展现
    function showDialog() {
        g('dialogMove').style.display = 'block';
        g('mask').style.display = 'block';
        autoCenter(g('dialogMove'));
        fillToBody(g('mask'));
    }

    // 关闭对话框
    function hideDialog() {
        g('dialogMove').style.display = 'none';
        g('mask').style.display = 'none';
    }

    // 侦听浏览器窗口大小变化
    window.onresize = showDialog;

    Dialog('dialogDrag', 'dialogMove');
</script>
</html>