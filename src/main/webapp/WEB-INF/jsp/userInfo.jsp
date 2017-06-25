<%--
  Created by IntelliJ IDEA.
  User: gagada
  Date: 2017/5/1
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script type="text/javascript">
        function show_alert(){
            alert("修改成功");
        }
    </script>

    <title>用户信息管理</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

    <link rel="stylesheet" type="text/css" href="/styles/styles.css">


</head>

<body bgcolor="#EAEAEA">

<form method="get" action="topServlet">
    <table bgcolor="#CFEFDA">
        <tr>
            <td>订水系统LOGO&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td><a id="info" href="/buyWatercon">购买矿泉水</a></td>
            <td></td>
            <td></td>
        </tr>
    </table>
</form>
<hr/>

<div>
    <h3>您的信息</h3>
    <div style="font-size:12px">如需修改您的信息，请在输入框输入要修改的值，点击下方的提交即可</div>
</div>
<table border="0">
    <tbody>
    <tr>
        <td>账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
        <td><input type="text" name="customerid" disabled="true" class=input_default value="${map.userid}"/></td>
        <td>
            <div class="ziti-hui">账号暂不支持修改</div>
        </td>
    </tr>

    <form method="post" action="<c:url value="/modifyInfo"/>">

        <tr>
            <td>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</td>
            <td><input type="text" name="name" class=input_default value="${map.username}"/></td>
            <td></td>
        </tr>

        <tr>
            <td>电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话：</td>
            <td><input type="text" name="phone" class=input_default value="${map.phone}"/></td>
            <td></td>
        </tr>
        <tr>
            <td>住&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址：</td>
            <td><input type="text" name="address" class=input_default value="${map.address}"/></td>
            <td></td>
        </tr>

        <tr>
            <td></td>
            <td></td>
            <td><input type="submit" value="提交修改" name="LoginButton" class="button-blue" onclick="show_alert()" <%--点击则弹框反馈--%>
                       onmouseover="this.className='button-blue2'" onmouseout="this.className='button-blue'"
                       style="height:45px; width: 80px; "></td>
        </tr>
    </form>
        <tr>
            <td></td>
            <%--     <td><a href="/WaterSupply/user/password.html"> 密码修改在此处 </a>--%>
            </td>
            <td></td>
        </tr>
    </tbody>
</table>

<hr/>

<table border="0">
    <tr>
        <td>总订水桶数：</td>
        <td><input type="text" name="watersum" readOnly="true" class=input_default value="${map.watersum}"/></td>
        <td></td>
    </tr>
</table>
<h4>我的历史订单 </h4>
<table border="1px" cellpadding="0" cellspacing="0">
    <thead>
    <tr>
        <th>订单号</th>
        <th>员工号</th>
        <th>&nbsp;下单时间&nbsp;</th>
        <th>&nbsp;送达时间&nbsp;</th>
        <th>&nbsp;矿泉水名称&nbsp;</th>
        <th>&nbsp;金额&nbsp;</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${map.orderlist}" var="item">
        <tr>
            <td align="center">${item.orderid}</td>
            <td align="center">${item.workerid}</td>
            <td align="center">${item.delivertime}</td>
            <td align="center">${item.arrivaltime}
                <c:if test="${empty item.arrivaltime}">
                    <c:out value="未送达"/>
                </c:if></td>
            <td align="center">${item.watername}</td>
            <td align="center">${item.waterprice}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
