package org.ldp4j.examples.app.client;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class ClientUtils {
	
	private static final MediaType TEXT_TURTLE = new MediaType("text", "turtle");
	
	public static void printResposnse(Response response) {

		System.out.println("-----------------------Response---------------------------");
		System.out.printf("%d %s %n", response.getStatus(), response.getStatusInfo().getReasonPhrase());
		System.out.println("Headers: \n");
		MultivaluedMap<String, Object> headerMap = response.getHeaders();
		for (String header : headerMap.keySet()) {
			for (Object ob : headerMap.get(header)) {
				System.out.printf("%s : %s %n", header, ob);
			}
		}

		if (response.hasEntity()) {
			String content = response.readEntity(String.class);
			System.out.println("Content: \n");
			if (TEXT_TURTLE.equals(response.getMediaType())) {
				createModel(content, "TURTLE").write(System.out, "TURTLE");
			} else {
				System.out.println(content);
			}
		}
		System.out.println("--------------------------------------------------------------");

	}
	
	public static Model createModel(String content, String format) {

		Model m = ModelFactory.createDefaultModel();
		m.setNsPrefix("foaf", "http://xmlns.com/foaf/0.1/");
		m.setNsPrefix("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
		m.setNsPrefix("ldp", "http://www.w3.org/ns/ldp#");
		m.setNsPrefix("dcterms", "http://purl.org/dc/terms/");

		m.read(new ByteArrayInputStream(content.getBytes()), null, format);

		return m;

	}
	
	public static String asString(Model m, String format) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		m.write(baos, format);

		return baos.toString();

	}
	

}
