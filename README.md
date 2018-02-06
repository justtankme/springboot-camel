# camel一些好玩的特性
## 20180206
### 测试multicast和wiretap的区别：
访问路径：http://localhost:8080/camel/wiretaptest   http://localhost:8080/camel/multicasttest
1、multicast的作用是并行执行多个to（每个to都是一个新的exchange），然后通过aggregate参数指定exchange的合并规则，等所有的to都执行完成后主线程才会终止。
2、wiretap的作用是创建一个inOnly的exchange给新线程使用，主线程继续执行并且不再关心新线程。
以如下场景为例：
servlet-->routA（立即响应给请求方）-->routeB（执行一个长时间的操作，并且操作结果不需要通知请求方）
如果使用multicast（见MulticastTest）会导致请求响应超时，使用wiretap（见WireTapTest）则满足需求。
### 解决servlet型路由processor中使用exchange.getIn().setBody(time, TimeDTO.class)导致Stream closed的问题
在所有的路由最后增加.convertBodyTo(String.class, "utf-8")即可（见MulticastTest），个人猜测是camel没办法将自定义的pojo类转换成outputstream。