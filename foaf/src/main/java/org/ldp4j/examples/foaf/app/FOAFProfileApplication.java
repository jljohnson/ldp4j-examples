package org.ldp4j.examples.foaf.app;

import java.util.List;

import org.ldp4j.application.data.NamingScheme;
import org.ldp4j.application.ext.Application;
import org.ldp4j.application.ext.Configuration;
import org.ldp4j.application.session.WriteSession;
import org.ldp4j.application.setup.Bootstrap;
import org.ldp4j.application.setup.Environment;
import org.ldp4j.examples.foaf.model.Person;
import org.ldp4j.examples.foaf.persistence.DummyDataStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FOAFProfileApplication extends Application<Configuration> {

	private static final Logger LOGGER = LoggerFactory.getLogger(FOAFProfileApplication.class);

	@Override
	public void setup(Environment environment, Bootstrap<Configuration> bootstrap) {

		FOAFProfileHandler foafHandler = new FOAFProfileHandler();

		bootstrap.addHandler(foafHandler);

		List<Person> personList = DummyDataStore.getPersonlist();

		for (Person p : personList) {
			environment.publishResource(NamingScheme.getDefault().name(p.getId()), FOAFProfileHandler.class,
					String.format("%s/", p.getId()));
		}

	}

	@Override
	public void initialize(WriteSession session) {
		LOGGER.info("Initializing FOAF Profiles LDP4j application: {}", session);

	}

	@Override
	public void shutdown() {
		LOGGER.info("Shutting down FOAF Profiles LDP4j application ...");
	}

}
