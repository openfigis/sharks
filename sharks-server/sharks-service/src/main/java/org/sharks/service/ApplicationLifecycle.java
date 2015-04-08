package org.sharks.service;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;

import org.sharks.service.event.ApplicationEvent;
import org.sharks.service.event.ApplicationEvent.Shutdown;
import org.sharks.service.event.ApplicationEvent.Startup;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@ApplicationScoped
@Slf4j
public class ApplicationLifecycle {

	@Inject
	private Event<ApplicationEvent> events;


	public void start() {

		log.info("application is starting...");

		events.fire(Startup.INSTANCE);
	}

	public void stop() {

		log.info("application is stopping...");

		events.fire(Shutdown.INSTANCE);
	}

}
