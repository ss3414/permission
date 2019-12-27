<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>

<table border="1">
    <tr>
        <td><a href="/login/login">登录页面</a></td>
    </tr>
    <tr>
        <td><a href="/back/home">后台页面（需要登录）</a></td>
    </tr>
</table>

<c:import url="/WEB-INF/jsp/import.jsp"/>

<table border="1">
    <tr>
        <td><a href="/cookie">Cookie回写SessionId</a></td>
    </tr>
    <%--    <tr>--%>
    <%--        <td><img src="/kaptcha1"></td>--%>
    <%--    </tr>--%>
    <tr>
        <td><img src="/kaptcha2"></td>
        <script>
            function test() {
                let para = Math.random()
                $("img").attr("src", "/kaptcha2" + "?para=" + para) /* 加个随机参数防止不刷新图片 */
            }
        </script>
        <td><a href="javascript:void(0)" onclick="test()">刷新</a></td>
    </tr>
</table>

</body>
<%
    /* JSP中引入JSP也会写入JSESSIONID，在引入的JSP中手动删掉JSESSIONID */
    Cookie[] cookies = request.getCookies();
    if (cookies != null && cookies.length > 0) {
        for (Cookie cookie : cookies) {
            if ("JSESSIONID".equals(cookie.getName())) {
                cookie.setMaxAge(0);
            }
        }
    }
%>
</html>
