import java.net.*;
import java.io.*;

public class Client {
	public static void main(String args[]) {
		// args give message contents and server hostname
		DatagramSocket aSocket = null;
		try {
			aSocket = new DatagramSocket();
			String msg = "Hello";
			byte[] m = msg.getBytes();
			InetAddress aHost = InetAddress.getByName("localhost");
			int serverPort = 6789;
			DatagramPacket request = new DatagramPacket(m, msg.length(),
					aHost, serverPort);
			
			// Send a message
			aSocket.send(request);
			
			// Prepare a empty message for a response
			byte[] buffer = new byte[1000];
			DatagramPacket response = new DatagramPacket(buffer, buffer.length);
			
			//Receive response
			aSocket.receive(response);
			
			// Print response
			String resp = new String(response.getData());
			System.out.println("Client received: " + resp);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			aSocket.close();
		}
	}
}