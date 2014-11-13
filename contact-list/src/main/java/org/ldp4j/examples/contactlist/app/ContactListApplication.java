package org.ldp4j.examples.contactlist.app;

import java.net.URI;

import org.ldp4j.application.data.Name;
import org.ldp4j.application.data.NamingScheme;
import org.ldp4j.application.ext.Application;
import org.ldp4j.application.ext.Configuration;
import org.ldp4j.application.session.WriteSession;
import org.ldp4j.application.setup.Bootstrap;
import org.ldp4j.application.setup.Environment;
import org.ldp4j.examples.contactlist.model.Contact;
import org.ldp4j.examples.util.persistence.InMemoryStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContactListApplication extends Application<Configuration>  {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(ContactListApplication.class);
	
	private static InMemoryStore<Contact> dataStore;
	
	private static final String BASIC_CONTAINER_NAME   = "ContactListBasic";
	
	public static final String BASIC_CONTAINER_PATH    = "contact_list_basic/";
	
	private final Name<String> basicContainerName = NamingScheme.getDefault().name(BASIC_CONTAINER_NAME);

	@Override
	public void setup(Environment environment,
			Bootstrap<Configuration> bootstrap) {
		
		ContactResourceHandler contactResourceHandler = new ContactResourceHandler();
		ContactListBasicContainerHandler basicContainerHandler = new ContactListBasicContainerHandler();

		basicContainerHandler.initialize(BASIC_CONTAINER_NAME);
		basicContainerHandler.setHandler(contactResourceHandler);
		
		bootstrap.addHandler(contactResourceHandler);
		bootstrap.addHandler(basicContainerHandler);
		
		//Initialize a dummy data store for this example.
		dataStore = new InMemoryStore<Contact>();

		environment.publishResource(this.basicContainerName, ContactListBasicContainerHandler.class, BASIC_CONTAINER_PATH);
		
	}

	@Override
	public void initialize(WriteSession session) {
		LOGGER.info("Initializing application: {}",session);
		
	}

	@Override
	public void shutdown() {
		LOGGER.info("Shutting down application");
		
	}
	
	public static InMemoryStore<Contact> getDataStore() {
		return dataStore;
	}

}
