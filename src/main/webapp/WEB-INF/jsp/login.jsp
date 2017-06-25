<%--
  Created by IntelliJ IDEA.
  User: gagada
  Date: 2017/4/30
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

    <title>送水公司用户登录</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

    <link rel="stylesheet" type="text/css" href="/styles/styles.css">


</head>


<body bgcolor="#EAEAEA">
<div>
    <h3>会员登录</h3>
</div>


<hr/>

<form method="post" action="<c:url value="/loginCheck"/>">
    <table border="0">
        <tbody>
        <tr>
            <td>账号：</td>
            <td><input type="text" name="UserName" class=input_default value="" style="width: 160px; "/></td>
            <td></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><input type="password" name="PassWord" class=input_default value="" style="width: 160px; "/></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td>
                <!--使用JSTL标签引用了LoginController中ModelAndView声明的error参数，提示登陆失败-->
                <c:if test="${!empty error}">
                    <font color="red"><c:out value="${error}"/></font>
                </c:if>
            </td>
            <td><input type="submit" value="登录" name="LoginButton" class="button-blue"
                       onmouseover="this.className='button-blue2'" onmouseout="this.className='button-blue'"/></td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td><a href="user/registration.jsp">还没有账号？点此进行注册</a></td>
        </tr>
        </tbody>
    </table>
</form>
<hr/>


</body>
</html>
