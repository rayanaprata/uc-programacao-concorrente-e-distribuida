import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Sender implements Runnable{
	
	// Socket usado para enviar
	private DatagramSocket sendSocket;
	// Porta utilizada;
	private int port = 5000;
	private Scanner in;
	// Para quem
	private InetAddress toHost;
	
	public Sender() {
		try {
			sendSocket = new DatagramSocket();
			// configurar socket para enviar em broadCast
			sendSocket.setBroadcast(true);
			// mandar para endereco de broadcast
			toHost = InetAddress.getByName("255.255.255.255");
		} catch (Exception e) {
			e.printStackTrace();
		}
		in = new Scanner(System.in);
	}
	
	@Override
	public void run() {
		System.out.println("Sender Ready");
		while(true) {
			System.out.print(">");
			String msg = in.nextLine();
			if(msg.equals("exit")) break;
			
			// Cria datagrama para carregar a mensagem
			DatagramPacket clientDatagram = new DatagramPacket(msg.getBytes(), 
					msg.getBytes().length,
					toHost, port);
			
			// Envia mensagem
			try {
				sendSocket.send(clientDatagram);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// fecha socket e libera porta
		sendSocket.close();
	}
	
	public static void main(String[] args) throws Exception {

		// sender criado como uma thread, facilita reutilizacao
		Thread sender = new Thread(new Sender());
		sender.start();
	}
}
