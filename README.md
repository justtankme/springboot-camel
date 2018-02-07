# camel一些好玩的特性
## 参考文档
* https://github.com/apache/camel/blob/master/components/camel-spring-boot/src/main/docs/spring-boot.adoc

## springboot与camel
### 关于properties
在camel-spring-boot-xxx.jar、camel-core-starter-xxx.jar、camel-xxx-starter-xxx.jar中可以看到/META-INF/spring-configuration-metadata.json和/META-INF/additional-spring-configuration-metadata.json，这里面记录的就是所有的可配置参数。

### 场景、问题、方案
#### 使用springboot配置属性设置camel servlet路径
例如有如下路由：
	
	from("servlet://wiretaptest")

通过设置

	camel.component.servlet.mapping.contextPath = /camelspringboot/*
	
路由访问地址即为<http://localhost:8080/camelspringboot/wiretaptest>

#### 测试multicast和wiretap的区别：
##### multicast
访问路径：<http://localhost:8080/camel/wiretaptest>
* <http://camel.apache.org/multicast.html>
* multicast的作用是并行执行多个to（每个to都是一个新的exchange），然后通过aggregate参数指定exchange的合并规则，等所有的to都执行完成后主线程才会终止。
* multicast如果不指定聚合规则的话，应该是默认取最后一个返回的exchange。

##### wiretap
访问路径：<http://localhost:8080/camel/multicasttest>
* <http://camel.apache.org/wire-tap.html>
* wiretap的作用是创建一个inOnly的exchange给新线程使用，主线程继续执行并且不再关心新线程。

##### 使用场景
* servlet-->routA（立即响应给请求方）-->routeB（执行一个长时间的操作，并且操作结果不需要通知请求方）

  如果使用multicast（见MulticastTest）会导致请求响应超时，使用wiretap（见WireTapTest）则满足需求。

#### 解决servlet型路由processor中使用`exchange.getIn().setBody(time, TimeDTO.class)`导致Stream closed的问题
在所有的路由最后增加`.convertBodyTo(String.class, "utf-8")`即可（见MulticastTest），个人猜测是camel没办法将自定义的pojo类转换成outputstream。

#### 解决to路由指向http接口时`java.lang.IllegalArgumentException: Invalid uri: /services.If you are forwarding/bridging http endpoints, then enable the bridgeEndpoin`的错误
参考

<https://stackoverflow.com/questions/12058479/camel-route-from-jetty-to-absolute-url>

在http地址后面加上`?bridgeEndpoint=true`即可。

	from("servlet://timeout")
	        .to("log:beforehttp?showAll=true&multiline=true")
			.to("http4://localhost:8080/timeout2?bridgeEndpoint=true&throwExceptionOnFailure=false")
	        .convertBodyTo(String.class, "utf-8")
	        .to("log:afterhttp?showAll=true&multiline=true");

#### 处理路由中的异常
访问路径<http://localhost:8080/camel/timeout>

参考资料：

<http://camel.apache.org/error-handling-in-camel.html>

<http://camel.apache.org/error-handler.html>

<http://camel.apache.org/defaulterrorhandler.html>

<http://camel.apache.org/try-catch-finally.html>

`We can add an onException in case we want to catch certain exceptions and route them differently, for instance to catch a ValidationException and return a fixed response to the caller.`

通过指定要捕获的异常类名、捕获异常后的处理processor即可完成异常处理。见`TimeOutRoute`

	onException(SocketTimeoutException.class).handled(true).process(new SocketTimeoutProcessor());
	
	
	
	
	