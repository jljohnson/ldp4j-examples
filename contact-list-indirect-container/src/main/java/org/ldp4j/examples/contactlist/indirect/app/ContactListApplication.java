package org.ldp4j.examples.contactlist.indirect.app;

import java.net.URI;

import org.ldp4j.application.data.Name;
import org.ldp4j.application.data.NamingScheme;
import org.ldp4j.application.ext.Application;
import org.ldp4j.application.ext.Configuration;
import org.ldp4j.application.session.ContainerSnapshot;
import org.ldp4j.application.session.ResourceSnapshot;
import org.ldp4j.application.session.WriteSession;
import org.ldp4j.application.session.WriteSessionException;
import org.ldp4j.application.setup.Bootstrap;
import org.ldp4j.application.setup.Environment;
import org.ldp4j.examples.contactlist.model.Contact;
import org.ldp4j.examples.util.persistence.InMemoryStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContactListApplication extends Application<Configuration>  {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(ContactListApplication.class);
	
	private static InMemoryStore<Contact> dataStore;
	
	private static final String CONTAINER_NAME   = "basicContainer";
	
	public static final String CONTAINER_PATH    = "contact_list_basic/";
	
	private final Name<String> containerName = NamingScheme.getDefault().name(CONTAINER_NAME);

	@Override
	public void setup(Environment environment,
			Bootstrap<Configuration> bootstrap) {
		
		ContactResourceHandler contactResourceHandler = new ContactResourceHandler();
		ContactListBasicContainerHandler basicContainerHandler = new ContactListBasicContainerHandler();

		basicContainerHandler.initialize(CONTAINER_NAME);
		basicContainerHandler.setHandler(contactResourceHandler);
		
		bootstrap.addHandler(contactResourceHandler);
		bootstrap.addHandler(basicContainerHandler);
		
		//Initialize a dummy data store for this example.
		dataStore = new InMemoryStore<Contact>();

		environment.publishResource(this.containerName, ContactListBasicContainerHandler.class, CONTAINER_PATH);
		
	}

	@Override
	public void initialize(WriteSession session) {
		LOGGER.info("Initializing application: {}",session);
		
/*		try {
			ContainerSnapshot container = session.find(ContainerSnapshot.class, this.containerName, ContactListBasicContainerHandler.class);
			container.addMember(NamingScheme.getDefault().name(1));
			container.addMember(NamingScheme.getDefault().name(2));
			container.addMember(NamingScheme.getDefault().name(3));
			ResourceSnapshot resource = session.find(ResourceSnapshot.class,NamingScheme.getDefault().name(4),ContactResourceHandler.class);
			if(resource!=null) {
				session.delete(resource);
			}
			session.saveChanges();
		} catch (WriteSessionException e) {
			throw new 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}

	@Override
	public void shutdown() {
		LOGGER.info("Shutting down application");
		
	}
	
	public static InMemoryStore<Contact> getDataStore() {
		return dataStore;
	}

}
