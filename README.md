#最基本的spring boot结构：boot模块
启动服务后访问：
http://127.0.0.1:29000/boot/

#SPRING CLOUD
版本号：<br>
spring cloud: Brixton.SR7<br>
spring boot: 1.3.8.RELEASE<br>
参考：http://projects.spring.io/spring-cloud/

#应用服务
* boot(29000): 最简单的spring boot示例
* eureka-server(28000): 服务发现
* config-server(28010): 统一配置
* turbin(28020): 监控服务
* zuul(28030): 动态路由
* console(28060): web界面
* order(28070): 订单服务
* pay(28080): 支付服务

#基本业务功能
##界面
###欢迎界面
![image](https://github.com/zhchm101/micro/blob/master/screenshots/welcome.png)

###下单并支付
![image](https://github.com/zhchm101/micro/blob/master/screenshots/add-order-info.png)

###业务订单列表
![image](https://github.com/zhchm101/micro/blob/master/screenshots/order-info-list.png)

###支付订单列表
![image](https://github.com/zhchm101/micro/blob/master/screenshots/pay-info-list.png)

###联合查询
![image](https://github.com/zhchm101/micro/blob/master/screenshots/union.png)

##业务功能描述
###本示例模拟基本的下单支付功能，分为三大模块：

####订单模块
* 下单并支付: 生成业务订单，并调用支付模块进行支付
* 业务订单查询
* 业务订单列表
* 业务订单删除

####支付模块
* 支付: 模拟支付成功、支付失败、支付异常
* 支付订单查询
* 支付订单列表
* 支付订单删除
* 支付渠道列表(初始化三个支付渠道)

####控台界面：纯界面应用，调用各模块接口
* 订单模块管理
* 支付模块管理
* 监控-联合查询
