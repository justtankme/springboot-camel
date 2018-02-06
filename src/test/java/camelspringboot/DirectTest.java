package camelspringboot;

import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.ModelCamelContext;

import com.tank.camelspringboot.multicastandwiretap.QuickThread;
import com.tank.camelspringboot.multicastandwiretap.WireTapTest;

public class DirectTest {
	    public static void main(String[] args) throws Exception {
	        // 这是camel上下文对象，整个路由的驱动全靠它了。
	        ModelCamelContext camelContext = new DefaultCamelContext();
	        // 启动route
	        camelContext.start();
	        // 首先将两个完整有效的路由注册到Camel服务中
	        camelContext.addRoutes(new WireTapTest());
	        camelContext.addRoutes(new QuickThread());

	        // 通用没有具体业务意义的代码，只是为了保证主线程不退出
	        synchronized (DirectTest.class) {
	        		DirectTest.class.wait();
	        }
	    }
}
