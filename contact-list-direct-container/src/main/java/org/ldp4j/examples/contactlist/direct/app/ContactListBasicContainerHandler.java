package org.ldp4j.examples.contactlist.direct.app;

import static org.ldp4j.application.data.IndividualReferenceBuilder.newReference;
import static org.ldp4j.examples.contactlist.direct.app.ContactListApplication.getDataStore;

import java.net.URI;
import java.util.concurrent.atomic.AtomicLong;

import org.ldp4j.application.data.DataDSL;
import org.ldp4j.application.data.DataSet;
import org.ldp4j.application.data.DataSetHelper;
import org.ldp4j.application.data.DataSetModificationException;
import org.ldp4j.application.data.Individual;
import org.ldp4j.application.data.ManagedIndividual;
import org.ldp4j.application.data.ManagedIndividualId;
import org.ldp4j.application.data.Name;
import org.ldp4j.application.data.NamingScheme;
import org.ldp4j.application.data.NewIndividual;
import org.ldp4j.application.domain.LDP;
import org.ldp4j.application.domain.RDF;
import org.ldp4j.application.ext.ContainerHandler;
import org.ldp4j.application.ext.annotations.BasicContainer;
import org.ldp4j.application.session.ContainerSnapshot;
import org.ldp4j.application.session.ResourceSnapshot;
import org.ldp4j.application.session.WriteSession;
import org.ldp4j.application.session.WriteSessionException;
import org.ldp4j.examples.contactlist.model.Contact;
import org.ldp4j.examples.rdf.vocab.DCTerms;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

@BasicContainer(id = ContactListBasicContainerHandler.ID, memberHandler = ContactResourceHandler.class)
public class ContactListBasicContainerHandler implements ContainerHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ContactListBasicContainerHandler.class);

	private DataSet dataset;

	public static final String ID = "ContactListBasicContainerHandler";

	private ContactResourceHandler handler;

	private AtomicLong counter = new AtomicLong();

	public final void setHandler(ContactResourceHandler handler) {
		this.handler = handler;
	}

	protected final ContactResourceHandler handler() {
		return this.handler;
	}

	public DataSet get(ResourceSnapshot resource) {
		return dataset;
	}

	public ResourceSnapshot create(ContainerSnapshot container, DataSet dataSet, WriteSession session) {

		LOGGER.trace("Creating member of container {} using: \n{}", ID, dataSet);

		String id = Long.toHexString(counter.getAndIncrement());
		Name<String> name = NamingScheme.getDefault().name(id);
		
		NewIndividual newInd=dataSet.individual(DataSetHelper.SELF,NewIndividual.class);
		Individual<URI, ?> self = dataSet.individualOfId(DataSetHelper.SELF);
		
		
		
		
		DataSetHelper helper = DataSetHelper.newInstance(dataSet);

		try {
			ManagedIndividual newManInd = helper.manage(ManagedIndividualId.createId(name, ID));
		} catch (DataSetModificationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ManagedIndividual individual = helper.replace(DataSetHelper.SELF,
				ManagedIndividualId.createId(name, ID), ManagedIndividual.class);
		Preconditions.checkNotNull(individual, "The contact individual is null");
		
		//TODO how to get the individual without renaming

		Contact contact = ContactResourceHandler.transform(individual, id);

		try {
			getDataStore().addEntity(contact);
			ResourceSnapshot member = container.addMember(name);
			session.saveChanges();
			return member;
		} catch (WriteSessionException e) {
			getDataStore().deleteEntity(id);
			throw new IllegalStateException("Could not create member", e);
		}

	}

	public void initialize(String name) {
		dataset = DataDSL.dataSet().individual(newReference().toManagedIndividual(ID).named(name))
				.hasProperty(DCTerms.title).withValue(new String("Nandana's Contact List"))
				.hasLink(RDF.TYPE.qualifiedEntityName())
				.referringTo(newReference().toExternalIndividual().atLocation(LDP.BASIC_CONTAINER.as(URI.class)))
				.build();
	}

}
