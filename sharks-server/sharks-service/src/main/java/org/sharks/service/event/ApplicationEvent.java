package org.sharks.service.event;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface ApplicationEvent {

	public class Startup implements ApplicationEvent {

		public static final Startup INSTANCE = new Startup();

		Startup() {}	
	}

	public class Shutdown implements ApplicationEvent {

		public static final Shutdown INSTANCE = new Shutdown();

		Shutdown() {}	
	}

}
