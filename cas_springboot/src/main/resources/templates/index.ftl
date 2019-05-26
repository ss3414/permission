<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script type="application/javascript" src="/static/frames/jquery-2.2.3/jquery.min.js"></script>
</head>
<body>

<h2>欢迎页</h2>
<h3><a href="/">返回欢迎页</a></h3>

<!--************************************************************分割线************************************************************-->

<table border="1">
    <tr>
        <td><a href="http://cas.test.com/login">CAS单点认证地址（casuser+casuser）</a></td>
    </tr>
    <tr>
        <td><a href="/login1">直接访问login1</a></td>
    </tr>
    <tr>
        <td><a href="http://cas.test.com/login?service=http%3A%2F%2F127.0.0.1%3A8080%2Flogin1">访问CAS单点认证地址再重定向回login1（开发环境）</a></td>
    </tr>
    <tr>
        <td><a href="http://cas.test.com/login?service=http%3A%2F%2Fclient1.test.com%2Flogin1">访问CAS单点认证地址再重定向回login1（生产环境）</a></td>
    </tr>
    <tr>
        <td><a href="/login2">直接访问login2</a></td>
    </tr>
</table>

</body>
</html>