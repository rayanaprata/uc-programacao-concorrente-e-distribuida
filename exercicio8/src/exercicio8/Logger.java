package exercicio8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/*
 * O que o logger deve fazer:
 * (1) deverah possuir um buffer compartilhado (sugestao: LinkedBlockingQueue)
 * (2) deverah possuir uma thread para gravacao em arquivo (consumidor)
 * (3) para inserir uma mensagem no log, deve-se utilizar o metodo putMessage (produtor)
 * (4) cada mensagem, ao ser gravada em arquivo, deverah conter o numero (contador) e a hora do evento
 * (5) inclua o que for necessario (metodos e atributos)
 */

public class Logger {
	
	private static Logger instance = null;
	private final static String logFileName = "logmessages.txt";

	private BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);
	
	// singleton
	public static synchronized Logger getInstance(){
		if(instance == null){
			instance = new Logger();
		}
		return instance;
	}
	
	private Logger(){
		new Thread() {
			@Override
			public void run() {
				try {
					consumidor();
				} catch (InterruptedException e) {}
			}			
		}.start();
	}
	
	public void putMessage(String message){
		new Thread() {
			@Override
			public void run() {
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
					Date hour = Calendar.getInstance().getTime();
					String dataFormatada = sdf.format(hour);
					String dataMessage = dataFormatada + " " + message;
					produtor(dataMessage);
				} catch (InterruptedException e) {}
			}			
		}.start();	
	}
	
	private void produtor(String message) throws InterruptedException {
		while(true) {
			queue.put(message);
		}				
	}
		 
	private void consumidor() throws InterruptedException {		
		while(true) {			
			String text = queue.take();
			writeFile("arquivoDeLog.txt", text + "\n");
	    }
	}
	
	public void writeFile(String path, String text) {
		
		try {
			FileWriter createFile = new FileWriter(path, true);
			BufferedWriter bufferFile = new BufferedWriter(createFile);
			PrintWriter writeText = new PrintWriter(bufferFile);
			writeText.append(text);
			bufferFile.close();
		} catch (IOException e) {}
		
	}

}