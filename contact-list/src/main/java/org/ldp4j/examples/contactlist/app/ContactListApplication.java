package org.ldp4j.examples.contactlist.app;

import static org.ldp4j.application.data.IndividualReferenceBuilder.newReference;

import java.net.URI;
import java.util.Date;

import org.ldp4j.application.data.DataDSL;
import org.ldp4j.application.data.DataSet;
import org.ldp4j.application.data.Name;
import org.ldp4j.application.data.NamingScheme;
import org.ldp4j.application.domain.LDP;
import org.ldp4j.application.domain.RDF;
import org.ldp4j.application.ext.Application;
import org.ldp4j.application.ext.Configuration;
import org.ldp4j.application.session.WriteSession;
import org.ldp4j.application.setup.Bootstrap;
import org.ldp4j.application.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContactListApplication extends Application<Configuration>  {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(ContactListApplication.class);
	
	private static final String BASIC_CONTAINER_NAME   = "ContactListBasic";
	
	public static final String BASIC_CONTAINER_PATH    = "contact_list_basic/";
	
	static final URI READ_ONLY_PROPERTY = URI.create("http://www.example.org/vocab#creationDate");
	
	private final Name<String> basicContainerName;
	
	public ContactListApplication() {
		this.basicContainerName = NamingScheme.getDefault().name(BASIC_CONTAINER_NAME);
	}

	@Override
	public void setup(Environment environment,
			Bootstrap<Configuration> bootstrap) {
		
		ContactResourceHandler contactResourceHandler = new ContactResourceHandler();
		ContactListBasicContainerHandler basicContainerHandler = new ContactListBasicContainerHandler();

		basicContainerHandler.initialize(BASIC_CONTAINER_NAME);
		basicContainerHandler.setHandler(contactResourceHandler);
		
		bootstrap.addHandler(contactResourceHandler);
		bootstrap.addHandler(basicContainerHandler);

		environment.publishResource(this.basicContainerName, ContactListBasicContainerHandler.class, BASIC_CONTAINER_PATH);
		
	}
	
	private DataSet getInitialData(String templateId, String name, boolean markContainer) {
		DataSet initial=null;
		if(!markContainer) {
			initial=
				DataDSL.
					dataSet().
						individual(newReference().toManagedIndividual(templateId).named(name)).
							hasProperty(READ_ONLY_PROPERTY.toString()).
								withValue(new Date().toString()).
							build();
		} else {
			initial=
				DataDSL.
					dataSet().
						individual(newReference().toManagedIndividual(templateId).named(name)).
							hasProperty(READ_ONLY_PROPERTY.toString()).
								withValue(new Date().toString()).
							hasLink(RDF.TYPE.qualifiedEntityName()).
								referringTo(newReference().toExternalIndividual().atLocation(LDP.BASIC_CONTAINER.as(URI.class))).
							build();
		}
		return initial;
	}

	@Override
	public void initialize(WriteSession session) {
		LOGGER.info("Initializing application: {}",session);
		
	}

	@Override
	public void shutdown() {
		LOGGER.info("Shutting down application");
		
	}

}
