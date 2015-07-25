package sample.hello.storage;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextLinstener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		ContactStore.finalizepaper();
	}
	@Override
	public void contextInitialized(ServletContextEvent event) {
		ContactStore.init();
	}
}
