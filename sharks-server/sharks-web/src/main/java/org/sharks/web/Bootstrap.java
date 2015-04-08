package org.sharks.web;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.sharks.service.ApplicationLifecycle;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@WebListener
public class Bootstrap implements ServletContextListener {

	@Inject
	ApplicationLifecycle app;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		app.start();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		app.stop();
	}
}
