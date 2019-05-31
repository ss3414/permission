<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h2>Shiro整合SSM</h2>
<h3><a href="/">返回欢迎页</a></h3>

<!--************************************************************分割线************************************************************-->

<table border="1">
    <tr>
        <td><a href="/login/login">登录页面</a></td>
    </tr>
    <tr>
        <td><a href="/back/home">后台页面（需要登录）</a></td>
    </tr>
</table>

<table border="1">
    <tr>
        <td><a href="/session/cookie">Cookie回写SessionId</a></td>
    </tr>
</table>

</body>
</html>
