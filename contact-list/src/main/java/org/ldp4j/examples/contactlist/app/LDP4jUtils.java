package org.ldp4j.examples.contactlist.app;

import java.net.URI;

public class LDP4jUtils {
	
	private LDP4jUtils(){
		// Utility class
	}
	
	/***
	 * Create a <code>java.net.URI</code> from a string
	 * @param uri String representation of the URI
	 * @return a URI object
	 */
	public static URI uri(String uri){
		return URI.create(uri);
	}

}
