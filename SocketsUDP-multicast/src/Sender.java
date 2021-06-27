import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class Sender implements Runnable, ActionListener{
	
	JTextArea textView, textSend;
	JLabel labelNome;
	JTextField  nome;
	JButton buttonEnviar;
		
	// Socket usado para enviar
	private MulticastSocket sendSocket;
	// Porta utilizada;
	private int port = 6000;
	private Scanner in;
	// Para quem
	private InetAddress toHost;
	private String msgText;
	
	
	public Sender() {
		
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				openFormulario();
			}
		});
		
		try {
			sendSocket = new MulticastSocket(port);
			// configurar socket para enviar em broadCast
			// sendSocket.setBroadcast(true);
			// mandar para endereco de broadcast
			toHost = InetAddress.getByName("225.0.0.20");
			sendSocket.joinGroup(toHost);
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
			msgText = textSend.getText().toString();
			if(msgText.equals("exit")) break;
			
			// Cria datagrama para carregar a mensagem
			DatagramPacket clientDatagram = new DatagramPacket(msgText.getBytes(), 
					msgText.getBytes().length,
					toHost, port);
			
			// Envia mensagem
			if(buttonEnviar.getActionCommand().equalsIgnoreCase("Enviar")) {			
				try {
					sendSocket.send(clientDatagram);
				} catch (IOException ee) {
					ee.printStackTrace();
				}
			}
			
			// Receive response
			byte[] buffer = new byte[1000];
			DatagramPacket response = new DatagramPacket(buffer, buffer.length);
						
			try {
				sendSocket.receive(response);
			} catch (IOException e) {}			
			// Print response
			String resp = new String(response.getData());
			textView.setText("Servidor - " + resp);
			System.out.println("Servidor - " + resp);
			
		}
		// Fecha socket e libera porta
		sendSocket.close();
	}
	
	public void openFormulario() {
		JFrame frame;
		frame = new JFrame("Sender");
		frame.setSize(400, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		JPanel p1 = new JPanel ( new FlowLayout ( FlowLayout.LEFT ));
		labelNome = new JLabel("Apelido: ");
		p1.add(labelNome);
		nome = new JTextField(20);
		nome.setText("Anonimo");
		p1.add(nome);
		frame.add( p1, "North");
		
		JPanel p2 = new JPanel ();
		textView = new JTextArea (5, 30);
		textView.setEditable( false );
		JScrollPane scroll = new JScrollPane ( textView );
		textView.setLineWrap(true);
		p2.add(scroll);
		
		textSend = new JTextArea (5, 30);
		textSend.setEditable( true );
		JScrollPane scroll2 = new JScrollPane ( textSend );
		textSend.setLineWrap(true);
		p2.add(scroll2);
		frame.add( p2);
		
		JPanel p3 = new JPanel ( new FlowLayout ( FlowLayout.CENTER ));		
		buttonEnviar = new JButton("Enviar");
		buttonEnviar.addActionListener(this);
		p3.add(buttonEnviar);
		frame.add( p3, "South");
		
	}
	
	public static void main(String[] args) throws Exception {

		// Sender criado como uma thread, facilita reutilizacao
		Thread sender = new Thread(new Sender());
		sender.start();		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
				
	}	
	
}
