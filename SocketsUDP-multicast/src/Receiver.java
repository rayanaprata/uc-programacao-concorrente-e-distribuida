import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Receiver implements Runnable, ActionListener {
	
	JTextArea textView, textSend;
	JLabel labelNome;
	JTextField  nome;
	JButton buttonEnviar;
	
	// Socket usado para enviar
	private DatagramSocket receiveSocket;
	// Porta utilizada;
	private int port = 6000;

	public Receiver() {
		
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				openFormulario();
			}
		});
		
		try {
			receiveSocket = new DatagramSocket();
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
			// Receber mensagem
			try {
				receiveSocket.receive(clientDatagram);
				String resp = new String(clientDatagram.getData());
				String msg = new String(buffer);
				textView.setText("received> " + resp + "\n");
				System.out.printf("received> %s\n", msg);
				
			/////// eviar mensagem
				DatagramPacket clientDatagram1 = new DatagramPacket(msg.getBytes(),msg.getBytes().length, clientDatagram.getAddress(), clientDatagram.getPort());
				try {
					Thread.sleep(1000);
					textSend.setText(resp);
					receiveSocket.send(clientDatagram1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			} catch (IOException e) {
				e.printStackTrace();
			}				
		}
	}
	
	public void openFormulario() {
		JFrame frame;
		frame = new JFrame("Receiver");
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
		// sender criado como uma thread, facilita reutilizacao
		Thread receiver = new Thread(new Receiver());
		receiver.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
