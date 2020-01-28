<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h2>欢迎页</h2>
<h3><a href="/">返回欢迎页</a></h3>

<!--************************************************************分割线************************************************************-->

<table border="1">
    <c:choose>
        <c:when test="${empty subject.principal}">
            <tr>
                <td>
                    <a href="/jsp/login.jsp">登录</a>
                </td>
            </tr>
        </c:when>
        <c:when test="${!empty subject.principal}">
            <tr>
                <td>
                        ${subject.principal}已登录
                    <a href="/doLogout">注销</a>
                </td>
            </tr>
            <tr>
                <td>
                    <a href="/jsp/success/loginSuccess.jsp">登录成功</a>
                </td>
            </tr>
            <tr>
                <td>
                    <a href="/jsp/success/needRole.jsp">需要角色</a>
                </td>
            </tr>
            <tr>
                <td>
                    <a href="/jsp/success/needPermission.jsp">需要权限</a>
                </td>
            </tr>
        </c:when>
    </c:choose>
</table>

</body>
</html>
