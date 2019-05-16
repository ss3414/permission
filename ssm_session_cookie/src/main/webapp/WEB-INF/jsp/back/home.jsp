<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h2>后台首页（需要登录认证）</h2>
<h3><a href="/login/login">返回登录页（登录状态下）</a></h3>

<!--************************************************************分割线************************************************************-->

<table border="1">
    <tr>
        <td><a href="/login/logout">注销</a></td>
    </tr>
    <tr>
        <td><a href="/back/permission1">权限1</a></td>
    </tr>
    <tr>
        <td><a href="/back/permission2">权限2</a></td>
    </tr>
</table>

</body>
</html>
