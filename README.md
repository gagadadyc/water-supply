# Water Supply
一个集合了矿泉水付费配送，员工管理功能的送水系统
## 运行环境及使用技术

 - 编译器：IntelliJ IDEA
 - 环境支持：JDK1.8、Java EE 7、Apache Tomcat 8.0.x
 - 开发语言：Java、HTML、JavaScript、EL Expression
 - 程序类型：Web类B/S应用程序
 - 运用框架：Spring framework，Spring MVC，Spring JDBC
 - 开发工具：Maven

## 功能及实现

1. 登陆注册

   用户在进入网站时进入欢迎页，点击进入登陆页面，登陆页面提供了登陆和注册功能。

2. 购买矿泉水

   购买矿泉水使用了购物车模式，用户添加矿泉水时，可实时在本页面看到购物车中的矿泉水价格、
   名称和总金额的信息。(购物车功能使用Session+EL表达式实现)

3. 查看和修改个人信息

   用户可以在个人信息页面可以查看自己的姓名、账号、地址、电话、订水量的信息，
   可以修改自己的地址、电话等信息。（此处使用了JSTL+EL表达式实现了动态循环表格）

4. 查看历史订单

   用户可在个人信息页面查看历史订单，历史订单中的信息应有送货员、订购矿泉水种类和数量、
   创建订单时间。（根据下单时间标记矿泉水的出库时间，使用mySQL的内置函数now()来获取当前系统时间，
   所以不用担心格式是否与字段类型兼容）
  
5. 送水员接单 (未实现)

   送水员可以接取未接取状态的订单

6. 管理员管理平台 (未实现) 

   管理员可以添加，解雇送水员工，在主页发布公告

## 数据库配置文件

该文件在/src/main/resources目录下，由于涉及数据库密码，上传时过滤掉了该文件，命名为：jdbc.properties ，源代码如下
```
jdbc.username=
jdbc.password=
jdbc.driverClassName=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://127.0.0.1:3306/waterspply?useUnicode=true&characterEncoding=UTF-8


#<!-- 初始化连接 -->
initialSize=10

#最大连接数量
maxActive=50

#<!-- 最大空闲连接 -->
maxIdle=20

#<!-- 最小空闲连接 -->
minIdle=5
```
