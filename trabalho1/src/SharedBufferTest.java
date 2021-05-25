
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SharedBufferTest {
	public static void main(String[] args) {
		ExecutorService application = Executors.newFixedThreadPool(3);

		Buffer sharedLocation = new BufferCircular(10);
		Buffer bufferRampa = new BufferRampa(10);

		try {
			application.execute(new Comprador(sharedLocation, 10));
			application.execute(new Caixa(sharedLocation, bufferRampa, 10));
			application.execute(new Ensacolador(bufferRampa, 10));
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		application.shutdown();
	}
}