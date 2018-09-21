demo：

服务注册中心：esureka

报文的生成：clientA

报文的解析：clientB

在报文生成页面中输入相关数据，点击按钮发送报文。

在报文解析页面中解析相关数据，点击按钮生成报文。

利用Zuul对路由网关进行管理

3.服务之间传递数据

3.1 三种方式

(1)最基本的利用IP端口进行请求访问接口实现数据的传输。

(2)使用Eureka取代IP（硬编码）的方式实现数据的传输。

(3)使用Feign更加快捷"分服务"的方式实现微服务之间的数据传输。

serverA：服务提供者

serverB：服务消费者

eureka-server：Eureka注册中心

A把数据传给B，B接收数据

7.20~8.20

1.完成梳理处理流程

2.学习Springboot和SpringCloud相关知识点。

3.完成知识点相关demo

4.学习局端发行模块相关业务


## 注解： ##
1.@ResponseBody：

定义：表示该方法的返回的结果直接写入 HTTP 响应正文（ResponseBody）中，一般在异步获取数据时使用，通常是在使用 @RequestMapping 后，返回值通常解析为跳转路径，**加上 @Responsebody 后返回结果不会被解析为跳转路径**，而是直接写入HTTP 响应正文中。 

作用： 
该注解用于将Controller的方法返回的对象，通过适当的HttpMessageConverter转换为指定格式后，写入到Response对象的body数据区。

使用时机： 返回的数据不是html标签的页面，而是其他某种格式的数据时（如json、xml等）使用。

2.@RequestBody：

作用：

2.1 该注解用于读取Request请求的body部分数据，使用系统默认配置的HttpMessageConverter进行解析，然后把相应的数据绑定到要返回的对象上。

2.2 再把HttpMessageConverter返回的对象数据绑定到controller中方法的参数上。



参考链接：[https://blog.csdn.net/ff906317011/article/details/78552426](https://blog.csdn.net/ff906317011/article/details/78552426)

问题：

1.serverClientA：index.html

在后端赋值给日期，前端显示日期，对象再返回给后端时，后端取不到值。

```
<p th:text="*{date}"></p>
```

但是使用input标签可以使用，还需查看资料。

```    
<input type="text" th:field="*{date}">
```

2.添加依赖Feign时，找不到相关maven包，添加<version>1.4.5.RELEASE</version>版本号后才自动下载和导入。

eureka，url地址：http://localhost:1112/

serverClientA：http://localhost:2223/index

serverCilentB：http://localhost:2224/index
