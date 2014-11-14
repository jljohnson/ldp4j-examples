package org.ldp4j.examples.util;

import java.net.URI;

public class Utils {
	
	private Utils () {
		// A utility class
	}

	public static URI uri(String uri) {
		return URI.create(uri);
	}

}
