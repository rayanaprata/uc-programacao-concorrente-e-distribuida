package exercicio9;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Client {
	public static void main(String args[]) throws IOException {
		 
		Socket s1 = new Socket("localhost",1235);
		
		String msg;
		
		try (Scanner sc = new Scanner(System.in)) {
			System.out.printf("Mensagem:\n");
			msg = sc.nextLine();
		}		
		
		DataOutputStream os = new DataOutputStream(s1.getOutputStream());
		os.writeUTF(msg);
		
		InputStream s1In = s1.getInputStream();
		DataInputStream dis = new DataInputStream(s1In);
		String st = new String (dis.readUTF()); 
		
		System.out.println(st);
		 
		dis.close();
		s1In.close();
		s1.close();
	}
} 
