<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h2>登录页</h2>
<h3><a href="/">返回欢迎页</a></h3>

<!--************************************************************分割线************************************************************-->

<form action="/login" method="post">
    <table border="1">
        <tr>
            <td>name<input type="text" name="name"></td>
        </tr>
        <tr>
            <td>password<input type="text" name="password"></td>
        </tr>
        <tr>
            <td><input type="submit" value="登录"></td>
        </tr>
    </table>
</form>

</body>
</html>
