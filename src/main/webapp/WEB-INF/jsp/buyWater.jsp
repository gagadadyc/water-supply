<%--
  Created by IntelliJ IDEA.
  User: gagada
  Date: 2017/4/30
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <script type="text/javascript" src="/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript">
        function show_alert() {
            alert("修改成功");
        }
        function xiadan() {
            window.location.href = "/buyWatercar"
        }
        function sum() {

            ${sessionScope.monysum = sessionScope.monysum + item.waterprice}
        }


    </script>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>矿泉水购买</title>
    <link rel="stylesheet" type="text/css" href=/styles/styles.css>
</head>
<body bgcolor="#EAEAEA">

<form method="get" action="topServlet">
    <table bgcolor="#CFEFDA">
        <tr>
            <td>订水系统LOGO&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td><a id="info" href="/userInfo">个人信息</td>

            <td></td>
        </tr>
    </table>
</form>

<hr/>
<h1>购买矿泉水</h1>
<!--使用jstl标签实现动态table表格，每行都是一个form表单-->
<table border="1px" cellpadding="0" cellspacing="0">
    <thead>
    <tr>
        <th>矿泉水编号</th>
        <th>矿泉水名称</th>
        <th>矿泉水售价</th>
        <th/>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${waterlist}" var="item">
        <tr>
            <form method="post" action="<c:url value="/buyCheck"/>">
                <td align="center"><input type="text" name="waterid" class=input_default value="${item.waterid}"/></td>
                <td align="center"><input type="text" name="watername" class=input_default value="${item.watername}"/>
                </td>
                <td align="center"><input type="text" name="waterprice" class=input_default value="${item.waterprice}"/>
                </td>
                <td align="center"><input type="submit" value="加入购物车" name="LoginButton" class="button-blue"

                                          onmouseover="this.className='button-blue2'"/></td>
            </form>
        </tr>
    </c:forEach>
    </tbody>
</table>
<input type="button" value="下单" onclick="xiadan()"/><c:if test="${!empty error}">
    <font color="red"><c:out value="${error}"/></font>
</c:if>
<h3>我的购物车</h3>
<div bgcolor="#CFEFDA">

    总金额：${sessionScope.prisum}
    <table border="1px" cellpadding="0" cellspacing="0">
        <thead>
        <tr>
            <th>矿泉水名称</th>
            <th>矿泉水售价</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${sessionScope.map}" var="ses">
            <tr>
                <td align="center">${ses.key}</td>
                <td align="center">${ses.value}</td>

            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
</body>
</html>

