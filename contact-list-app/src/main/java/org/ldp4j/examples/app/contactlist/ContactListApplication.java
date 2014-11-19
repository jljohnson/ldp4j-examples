package org.ldp4j.examples.app.contactlist;

import org.ldp4j.application.data.Name;
import org.ldp4j.application.data.NamingScheme;
import org.ldp4j.application.ext.Application;
import org.ldp4j.application.ext.Configuration;
import org.ldp4j.application.session.WriteSession;
import org.ldp4j.application.setup.Bootstrap;
import org.ldp4j.application.setup.Environment;
import org.ldp4j.examples.app.contactlist.handlers.BasicContainerHandler;
import org.ldp4j.examples.app.contactlist.handlers.DirectContainerHandler;
import org.ldp4j.examples.app.contactlist.handlers.IndirectContainerHandler;
import org.ldp4j.examples.app.contactlist.handlers.ResourceHandler;
import org.ldp4j.examples.app.contactlist.model.Contact;
import org.ldp4j.examples.util.persistence.InMemoryStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class provides the configuration for the Contact List LDP4j application and manages the lifecycle events
 * of the application.
 *
 */
public class ContactListApplication extends Application<Configuration>  {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(ContactListApplication.class);
	
	private static InMemoryStore<Contact> dataStore;
	
	private static final Name<String> BASIC_CONTAINER_NAME = NamingScheme.getDefault().name("basicContainer");
	private static final String BASIC_CONTAINER_PATH    = "contact_list_basic/";
	
	private static final Name<String> DIRECT_CONTAINER_NAME = NamingScheme.getDefault().name("directContainer");
	private static final String DIRECT_CONTAINER_PATH    = "contact_list_direct/";

	private static final Name<String> INDIRECT_CONTAINER_NAME = NamingScheme.getDefault().name("indirectContainer");
	private static final String INDIRECT_CONTAINER_PATH    = "contact_list_indirect/";


	/***
	 * The logic of setting up the application goes here. This includes <ul>
	 * <li> constructing the resource and container handlers and configuring them </li>
	 * <li> registering the handlers in the bootstrap</li>
	 * <li> publishing the containers</li>
	 */
	@Override
	public void setup(Environment environment,
			Bootstrap<Configuration> bootstrap) {
		
		// Create a resource handler to manage contact resources
		ResourceHandler resourceHandler = new ResourceHandler();
		bootstrap.addHandler(resourceHandler);
		
		BasicContainerHandler basicContainerHandler = new BasicContainerHandler();
		basicContainerHandler.initialize(BASIC_CONTAINER_NAME.id());
		bootstrap.addHandler(basicContainerHandler);
		environment.publishResource(BASIC_CONTAINER_NAME, BasicContainerHandler.class, BASIC_CONTAINER_PATH);
		
		DirectContainerHandler directContainerHandler = new DirectContainerHandler();
		directContainerHandler.initialize(DIRECT_CONTAINER_NAME.id());
		bootstrap.addHandler(directContainerHandler);
		environment.publishResource(DIRECT_CONTAINER_NAME, DirectContainerHandler.class, DIRECT_CONTAINER_PATH);
		
		IndirectContainerHandler indirectContainerHandler = new IndirectContainerHandler();
		indirectContainerHandler.initialize(INDIRECT_CONTAINER_NAME.id());
		bootstrap.addHandler(indirectContainerHandler);
		environment.publishResource(INDIRECT_CONTAINER_NAME, IndirectContainerHandler.class, INDIRECT_CONTAINER_PATH);
		
	}

	@Override
	public void initialize(WriteSession session) {
		LOGGER.info("Initializing application: {}",session);
		
		//Initialize a dummy in-memory data store to store the application data. The contact resource handler and the
		//containers will use this data store to keep the contact domain objects. 
		dataStore = new InMemoryStore<Contact>();
		
	}

	@Override
	public void shutdown() {
		LOGGER.info("Shutting down application");
		
	}
	
	public static InMemoryStore<Contact> getDataStore() {
		return dataStore;
	}

}
