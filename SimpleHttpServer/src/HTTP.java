
public enum HTTP {

	OK("HTTP/1.1 200 OK\n"),
	NOT_FOUND("HTTP/1.0 404 Not Found\n"),
	NOT_FOUND_PAGE( "<HTML>\n"
			+ "<HEAD><TITLE>404 Not Found</TITLE></HEAD>\n"
			+ "<BODY>404 Not Found" + "</BODY></HTML>\n\n"),
	TEXT_CONTENT("Content-type: text/html\n"),
	CONTENT_LENGTH("Content-Length: "),
	INDEX_PAGE_NAME("/index.html"),
	GET("GET"),
	CLOSE_CONNECTION("Connection: close\n\n"),
	SERVER("Server: Simple server\n");
	
	
	private final String stringValue;
	HTTP(final String s) { stringValue = s; }
	public String toString() { return stringValue; }

}