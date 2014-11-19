package org.ldp4j.examples.app.contactlist.handlers;

import static org.ldp4j.application.data.IndividualReferenceBuilder.newReference;

import org.ldp4j.application.data.DataDSL;
import org.ldp4j.application.data.DataSet;
import org.ldp4j.application.ext.ApplicationRuntimeException;
import org.ldp4j.application.ext.ContainerHandler;
import org.ldp4j.application.ext.UnknownResourceException;
import org.ldp4j.application.ext.UnsupportedContentException;
import org.ldp4j.application.ext.annotations.IndirectContainer;
import org.ldp4j.application.ext.annotations.MembershipRelation;
import org.ldp4j.application.session.ContainerSnapshot;
import org.ldp4j.application.session.ResourceSnapshot;
import org.ldp4j.application.session.WriteSession;
import org.ldp4j.examples.rdf.vocab.DCTerms;

@IndirectContainer(
		id=IndirectContainerHandler.ID, 
		memberHandler=ResourceHandler.class,
		membershipRelation= MembershipRelation.HAS_MEMBER,
		membershipPredicate= "http://xmlns.com/foaf/0.1/knows",
		memberPath="http://example.org/nandana#me",
		insertedContentRelation="http://www.w3.org/ns/ldp#MemberSubject"
	)
public class IndirectContainerHandler implements ContainerHandler {
	
	public static final String ID = "indirectContainer";
	
	private GenericContainerImpl delegate;

	public DataSet get(ResourceSnapshot resource) throws UnknownResourceException, ApplicationRuntimeException {
		// The business logic handling the retrieval request goes here. 
		// In this example, this method delegates this to a generic container implementation which implements
		// this functionality for different kinds of containers included in this project.
		return delegate.get(resource);
	}

	public ResourceSnapshot create(ContainerSnapshot container, DataSet representation, WriteSession session)
			throws UnknownResourceException, UnsupportedContentException, ApplicationRuntimeException {
		// The business logic creating new resources goes here.
		// In this example, this method delegates this to a generic container implementation which implements
		// this functionality for different kinds of containers included in this project.
		return delegate.create(container, representation, session);
	}
	
	public void initialize(String name) {
		// create initial data for the container representation. We only add a title for container resource.
		DataSet dataset = DataDSL.dataSet().individual(newReference().toManagedIndividual(ID).named(name))
				.hasProperty(DCTerms.title).withValue(new String("Nandana's Contact List Indirect Container"))
				.build();
		delegate = new GenericContainerImpl(ID, dataset);
	}

}
