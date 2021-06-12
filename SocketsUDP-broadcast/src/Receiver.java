import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Receiver implements Runnable {
	// Socket usado para enviar
	private DatagramSocket receiveSocket;
	// Porta utilizada;
	private int port = 5000;

	public Receiver() {
		try {
			receiveSocket = new DatagramSocket(port);
			// configurar socket para receber em broadCast
			receiveSocket.setBroadcast(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		System.out.println("Receiver Ready");
		// Datagrama vazio para receber mensagem
		byte[] buffer = new byte[1000];
		DatagramPacket clientDatagram = new DatagramPacket(buffer, buffer.length);

		while (true) {

			// Envia mensagem
			try {
				receiveSocket.receive(clientDatagram);
				String msg = new String(buffer);
				System.out.printf("received> %s\n", msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws Exception {

		// sender criado como uma thread, facilita reutilizacao
		Thread receiver = new Thread(new Receiver());
		receiver.start();
	}
}
