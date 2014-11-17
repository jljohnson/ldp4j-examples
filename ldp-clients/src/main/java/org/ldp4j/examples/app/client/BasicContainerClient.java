package org.ldp4j.examples.app.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ldp4j.examples.vocab.VCARD;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

public class BasicContainerClient {
	
	private static final MediaType TEXT_TURTLE = new MediaType("text", "turtle");
	
	private static final String containerURI = "http://localhost:8080/ldp4j/api/contact_list_basic/";
	
	public static void main(String[] args) {
			
		System.out.printf("GET '%s' representation of '%s' %n", TEXT_TURTLE, containerURI);
		System.out.println("#######################################################");
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(containerURI);
		
		Response response = target.request(TEXT_TURTLE).get();
		ClientUtils.printResposnse(response);
		
		String newResource = creatNewModel();
		
		System.out.printf("Creating a new resource ");
		System.out.println("#######################################################");
		System.out.println("Content: ");
		System.out.println(newResource);

		response = target.request().header("Link", "<http://www.w3.org/ns/ldp#Resource>;rel=\"type\"")
				.post(Entity.entity(newResource, TEXT_TURTLE));
		
		ClientUtils.printResposnse(response);
		
		String newResourceURI;
		if (response.getStatus() == 201) {
			newResourceURI = response.getHeaderString("Location");
			System.out.printf("URI of the new created resource : %s  %n", newResourceURI);
			System.out.println("#######################################################");
		} else {
			System.out.println("Ooops, something went wrong ...");
			return;
		}
		
		System.out.printf("Container '%s' representation after resource creation %n", containerURI);
		System.out.println("#######################################################");
		
		response = target.request(TEXT_TURTLE).get();
		ClientUtils.printResposnse(response);
		
		System.out.printf("Representation of the new resource '%s' %n", newResourceURI);
		System.out.println("#######################################################");
		
		WebTarget newTarget = client.target(newResourceURI);
		response = newTarget.request(TEXT_TURTLE).get();
		ClientUtils.printResposnse(response);
		
	}
	
	private static String creatNewModel() {
		Model m = ModelFactory.createDefaultModel();
		m.setNsPrefix("vcard", "http://www.w3.org/2006/vcard/ns#");
		
		Resource newResource = m.createResource("");
		newResource.addProperty(RDF.type, VCARD.Individual);
		newResource.addProperty(property(VCARD.hasURL, m), m.createResource("#me"));
		newResource.addLiteral(property(VCARD.hasEmail, m), "fernado@example.com");
		newResource.addLiteral(property(VCARD.hasGivenName, m), "Fernando");
		newResource.addLiteral(property(VCARD.hasFamilyName, m), "Serena");
		
		Resource telephone = m.createResource();
		telephone.addProperty(RDF.type, VCARD.Home);
		telephone.addProperty(RDF.type, VCARD.Voice);
		telephone.hasProperty(property(VCARD.hasValue, m), m.createResource("tel:+34655555555"));
		
		return ClientUtils.asString(m, "TURTLE");
		
	}
	
	private static Property property(String uri, Model m) {
		return m.createProperty(uri);
	}
	

}
