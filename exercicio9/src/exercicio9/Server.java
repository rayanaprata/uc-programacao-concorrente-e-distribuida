package exercicio9;

import java.net.*;
import java.io.*;

public class Server {
	public static void main(String args[]) throws IOException {
		
		// Register service on port 1234
		ServerSocket server = new ServerSocket(1235);
		
		Socket s1 = server.accept(); // Wait and accept a connection
		 
		DataInputStream is = new DataInputStream(s1.getInputStream());
		String line = new String(is.readUTF());
		
		OutputStream s1out = s1.getOutputStream();
		DataOutputStream dos = new DataOutputStream (s1out);
		 
		// Send a string!
		dos.writeUTF(new StringBuilder(line).reverse().toString());
		 
		// Close the connection, but not the server socket
		dos.close();
		s1out.close();
		s1.close();
	}
}