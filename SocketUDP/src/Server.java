import java.net.*;
import java.io.*;

public class Server {
	public static void main(String args[]) {
		DatagramSocket aSocket = null;
		try {
			// Create a socket on port 6789
			aSocket = new DatagramSocket(6789);
			
			// Prepare an empty message
			byte[] buffer = new byte[1000];
			DatagramPacket request = new DatagramPacket(buffer, buffer.length);
			
			// Receive a message
			aSocket.receive(request);
			
			// Prepare a response
			String resp = "Server response to " + new String(buffer);
			byte[] m = resp.getBytes();
			// Obtain response address/port from request
			DatagramPacket response = new DatagramPacket(m, resp.length(),
					request.getAddress(), request.getPort());
			
			// Send response
			aSocket.send(response);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			aSocket.close();
		}
	}
}