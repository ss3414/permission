<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script type="application/javascript" src="/static/frames/jquery-2.2.3/jquery.min.js"></script>
</head>
<body>

<table border="1">
    <tr>
        <td><a href="/">首页</a></td>
    </tr>
    <tr>
        <td><a href="/json">JSON</a></td>
    </tr>
    <tr>
        <td><a href="/swagger-ui.html">swagger2</a></td>
    </tr>
</table>

<table border="1">
    <tr>
        <td>name：<input type="text" name="name" id="name"></td>
    </tr>
    <tr>
        <td>pwd：<input type="text" name="password" id="pwd"></td>
    </tr>
    <tr>
        <td><input type="button" value="登录" onclick="login()"></td>
    </tr>
</table>

<table border="1">
    <tr>
        <td>token：<input type="text" name="token" id="token1"></td>
    </tr>
    <tr>
        <td><input type="button" value="后台首页" onclick="back()"></td>
    </tr>
</table>

<table border="1">
    <tr>
        <td>token：<input type="text" name="token" id="token2"></td>
    </tr>
    <tr>
        <td><input type="button" value="/user/list" onclick="user()"></td>
    </tr>
</table>

<script>
    function login() {
        $.ajax({
            url: "/login/doLogin",
            data: {
                name: $("#name").val(),
                password: $("#pwd").val(),
            },
            dataType: "json",
            type: "post",
            success: function (data) {
                console.log(JSON.stringify(data))
                alert(JSON.stringify(data))
            }
        });
    }

    function back() {
        $.ajax({
            url: "/back/home",
            headers: {
                token: $("#token1").val(),
            },
            dataType: "json",
            type: "get",
            success: function (data) {
                console.log(JSON.stringify(data))
                alert(JSON.stringify(data))
            }
        });
    }

    function user() {
        $.ajax({
            url: "/user/list",
            headers: {
                token: $("#token2").val(),
            },
            dataType: "json",
            type: "get",
            success: function (data) {
                console.log(JSON.stringify(data))
                alert(JSON.stringify(data))
            }
        });
    }
</script>

</body>
</html>