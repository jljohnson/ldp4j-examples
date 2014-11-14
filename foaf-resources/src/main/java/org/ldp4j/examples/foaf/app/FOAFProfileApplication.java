package org.ldp4j.examples.foaf.app;

import org.ldp4j.application.data.NamingScheme;
import org.ldp4j.application.ext.Application;
import org.ldp4j.application.ext.Configuration;
import org.ldp4j.application.session.WriteSession;
import org.ldp4j.application.setup.Bootstrap;
import org.ldp4j.application.setup.Environment;
import org.ldp4j.examples.foaf.model.Person;
import org.ldp4j.examples.util.persistence.InMemoryStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FOAFProfileApplication extends Application<Configuration> {

	private static final Logger LOGGER = LoggerFactory.getLogger(FOAFProfileApplication.class);
	
	private static InMemoryStore<Person> dataStore;
	
	private Environment environment;

	@Override
	public void setup(Environment environment, Bootstrap<Configuration> bootstrap) {
		
		this.environment = environment;

		// Initialize the handler and add it to the bootstrap
		FOAFProfileHandler foafHandler = new FOAFProfileHandler();
		bootstrap.addHandler(foafHandler);
		
		//Initialize a dummy data store for this example.
		dataStore = new InMemoryStore<Person>();
		
		// Add the initial data
		dataStore.addEntity(new Person("nandana", "Nandana", "Mihindukulasooriya", "nmihindu@fi.upm.es"));
		dataStore.addEntity(new Person("miguel", "Miguel", "Esteban Gutiérrez", "mesteban@fi.upm.es"));
		dataStore.addEntity(new Person("raul", "Raúl", "García Castro", "rgarcia@fi.upm.es"));
		
		// Publish initial data as resources
		for (Person p : dataStore.getEntityList()) {
			environment.publishResource(NamingScheme.getDefault().name(p.getID()), FOAFProfileHandler.class,
					String.format("%s/", p.getID()));
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
	
	public static InMemoryStore<Person> getDataStore() {
		return dataStore;
	}

}
