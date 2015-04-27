package org.sharks.web;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import lombok.extern.slf4j.Slf4j;

import org.sharks.service.ApplicationLifecycle;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@WebListener
@Slf4j
public class Bootstrap implements ServletContextListener {

	@Inject
	ApplicationLifecycle app;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		log.info("Application startup...");
		try {
			app.start();
			log.info("Application startup complete");
		}catch(Exception e) {
			log.error("application initialization failed", e);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		app.stop();
	}
}
