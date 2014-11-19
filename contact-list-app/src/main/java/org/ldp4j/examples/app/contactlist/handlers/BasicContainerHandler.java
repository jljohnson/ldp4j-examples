package org.ldp4j.examples.app.contactlist.handlers;

import static org.ldp4j.application.data.IndividualReferenceBuilder.newReference;

import org.ldp4j.application.data.DataDSL;
import org.ldp4j.application.data.DataSet;
import org.ldp4j.application.ext.ApplicationRuntimeException;
import org.ldp4j.application.ext.ContainerHandler;
import org.ldp4j.application.ext.UnknownResourceException;
import org.ldp4j.application.ext.UnsupportedContentException;
import org.ldp4j.application.ext.annotations.BasicContainer;
import org.ldp4j.application.session.ContainerSnapshot;
import org.ldp4j.application.session.ResourceSnapshot;
import org.ldp4j.application.session.WriteSession;
import org.ldp4j.examples.rdf.vocab.DCTerms;

@BasicContainer(
		id = BasicContainerHandler.ID, 
		memberHandler = ResourceHandler.class)
public class BasicContainerHandler implements ContainerHandler {

	public static final String ID = "basicContainer";
	
	private GenericContainerImpl delegate;
			
	public DataSet get(ResourceSnapshot resource) throws UnknownResourceException, ApplicationRuntimeException {
		return delegate.get(resource);
	}

	public ResourceSnapshot create(ContainerSnapshot container, DataSet representation, WriteSession session)
			throws UnknownResourceException, UnsupportedContentException, ApplicationRuntimeException {
		return delegate.create(container, representation, session);
	}
	
	public void initialize(String name) {
		DataSet dataset = DataDSL.dataSet().individual(newReference().toManagedIndividual(ID).named(name))
				.hasProperty(DCTerms.title).withValue(new String("Nandana's Contact List Basic Container"))
				.build();
		delegate = new GenericContainerImpl(ID, dataset);
	}

}
