package org.ldp4j.examples.app.client;

import static org.ldp4j.examples.app.client.ClientUtils.asString;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

public class FOAFClient {

	private static final MediaType TEXT_TURTLE = new MediaType("text", "turtle");

	public static void main(String[] args) {

		String resourceURI = "http://localhost:8080/ldp4j/api/nandana/";
		String personURI = "http://localhost:8080/ldp4j/api/nandana/#me";

		String etag;
		String content;

		System.out.printf("GET '%s' representation of '%s' %n", TEXT_TURTLE, resourceURI);
		System.out.println("#######################################################");

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(resourceURI);

		Response response = target.request(TEXT_TURTLE).get();
		ClientUtils.printResposnse(response);

		etag = response.getEntityTag().getValue();
		content = response.readEntity(String.class);

		System.out.println("Modifying the last name, 'Mihindukulasooriya' -> 'Doe'");
		System.out.println("#######################################################");

		content = modifySurname(content, personURI, "Doe");
		System.out.println(content);

		System.out.printf("PUT a modified presentation of representation of '%s' %n", resourceURI);
		System.out.println("#######################################################");

		response = target.request().header("If-Match", etag).put(Entity.entity(content, TEXT_TURTLE));
		ClientUtils.printResposnse(response);

	}

	/**
	 * Modify the foaf:surname property in the representation
	 */
	private static String modifySurname(String content, String personURI, String newValue) {

		Model model = ClientUtils.createModel(content, "TURTLE");

		Property surname = model.createProperty("http://xmlns.com/foaf/0.1/surname");

		Resource person = model.getResource(personURI);
		person.removeAll(surname);
		person.addProperty(surname, newValue);

		return asString(model, "TURTLE");

	}





}
