
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SharedBufferTest {
	public static void main(String[] args) {
		ExecutorService application = Executors.newFixedThreadPool(3);

		Buffer sharedLocation = new BufferCircular(5);
		Buffer bufferRampa = new BufferRampa(5);

		try {
			application.execute(new Comprador(sharedLocation, 5));
			application.execute(new Caixa(sharedLocation, bufferRampa, 5));
			application.execute(new Ensacolador(bufferRampa, 5));
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		application.shutdown();
	}
}