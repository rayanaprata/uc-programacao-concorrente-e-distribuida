
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SharedBufferTest {
	public static void main(String[] args) {
		ExecutorService application = Executors.newFixedThreadPool(3);
		Random gerador = new Random();
		int qtdItens = gerador.nextInt((30 - 10) + 1) + 10;
		
		Buffer sharedLocation = new Esteira(30);
		Buffer bufferRampa = new Rampa(30);

		try {
			application.execute(new Comprador(sharedLocation, qtdItens));
			application.execute(new Caixa(sharedLocation, bufferRampa, qtdItens));
			application.execute(new Ensacolador(bufferRampa, qtdItens));
		} catch (Exception e) {}

		application.shutdown();
	}
}