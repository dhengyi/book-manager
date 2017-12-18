<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hg_yi
  Date: 17-7-22
  Time: 上午8:57
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <title>标签树</title>
    <link rel="shortcut icon" href="https://www.xiyoulinux.org/images/xiyoulinux.png"/>
</head>

<body data-mod="tag" class="tag-index ">
<c:forEach items="${labelsName.entrySet()}" var="labelGroup" >
    <li id="<c:out value="labelGroup" />" >
        <div class="parentLabelName">
            <c:out value="${labelGroup.getKey()}" />
        </div>
    </li>
    <c:forEach items="${labelGroup.getValue()}" var="childLabel" >
        <li id="<c:out value="childLabel" />" >
            <div class="label">
                <c:out value="${childLabel}" />
            </div>
        </li>
    </c:forEach>
</c:forEach>
</body>
</html>
