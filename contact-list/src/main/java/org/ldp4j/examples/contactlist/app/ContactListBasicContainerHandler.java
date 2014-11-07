package org.ldp4j.examples.contactlist.app;

import static org.ldp4j.application.data.IndividualReferenceBuilder.newReference;
import static org.ldp4j.examples.contactlist.app.LDP4jUtils.uri;

import java.net.URI;

import org.ldp4j.application.data.DataDSL;
import org.ldp4j.application.data.DataSet;
import org.ldp4j.application.domain.LDP;
import org.ldp4j.application.domain.RDF;
import org.ldp4j.application.ext.ContainerHandler;
import org.ldp4j.application.ext.annotations.BasicContainer;
import org.ldp4j.application.session.ContainerSnapshot;
import org.ldp4j.application.session.ResourceSnapshot;
import org.ldp4j.application.session.WriteSession;
import org.ldp4j.server.tckf.TCKFBasicContainerHandler;
import org.ldp4j.server.tckf.TCKFResourceHandler;
import org.nandana.examples.rdf.vocab.DCTerms;

@BasicContainer(
		id = ContactListBasicContainerHandler.ID, 
		memberHandler = ContactResourceHandler.class
	)
public class ContactListBasicContainerHandler implements ContainerHandler {
	
	private DataSet dataset;
	
	public static final String ID="ContactListBasicContainerHandler";
	
	private ContactResourceHandler handler;
	
	public final void setHandler(ContactResourceHandler handler) {
		this.handler = handler;
	}
	
	protected final ContactResourceHandler handler() {
		return this.handler;
	}

	public DataSet get(ResourceSnapshot resource) {
		return dataset;
	}

	public ResourceSnapshot create(ContainerSnapshot container,
			DataSet representation, WriteSession session) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void initialize(String name) {
		dataset = DataDSL.dataSet().
				individual(newReference().toManagedIndividual(ID).named(name)).
					hasProperty(DCTerms.title).
						withValue(new String("Nandana's Contact List")).
						hasLink(RDF.TYPE.qualifiedEntityName()).
						referringTo(newReference().toExternalIndividual().atLocation(LDP.BASIC_CONTAINER.as(URI.class))).
					build();
	}

}
