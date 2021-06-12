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
 * (7) deverah possuir um buffer compartilhado (sugestao: LinkedBlockingQueue)
 * (8) deverah possuir uma thread para gravacao em arquivo (consumidor)
 * (9) para inserir uma mensagem no log, deve-se utilizar o metodo putMessage (produtor)
 * (10) cada mensagem, ao ser gravada em arquivo, deverah conter o numero (contador) e a hora do evento
 * (11) inclua o que for necessario (metodos e atributos)
 */

public class Logger {
	
	private static Logger instance = null;
	private final static String logFileName = "serverlog.txt";
	// incluir campos necessarios
	
	private BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);
	
	// singleton
	public static synchronized Logger getInstance(){
		
		if(instance == null){
			instance = new Logger();
		}
		return instance;
	}
	
	private Logger() {
		new Thread() {
			@Override
			public void run() {
				try {
					consumidor();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}			
		}.start();
	}
	
	public void putMessage(String message){
		new Thread() {
			@Override
			public void run() {
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
					Date hora = Calendar.getInstance().getTime();
					String dataFormatada = sdf.format(hora);
					String dataMessage = dataFormatada + " " + message;
					produtor(dataMessage);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
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
			String texto = queue.take();
			escreverArquivo("arquivoDeLog.txt", texto + "\n");
	    }
	}
	
	public void escreverArquivo(String caminho, String texto) {
		
		try {
			FileWriter criarArquivo = new FileWriter(caminho, true);
			BufferedWriter bufferArquivo = new BufferedWriter(criarArquivo);
			PrintWriter escreverTexto = new PrintWriter(bufferArquivo);
			escreverTexto.append(texto);
			bufferArquivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
