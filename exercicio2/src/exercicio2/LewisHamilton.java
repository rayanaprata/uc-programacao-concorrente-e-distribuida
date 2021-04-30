package exercicio2;
import java.util.Random;

public class LewisHamilton implements Runnable{
	Random random = new Random();
	
	public long time;
	
	@Override
	public void run() {
		for(int i=0; i<=65; i++) {			
			try {
				Thread.sleep(random.nextInt(1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Hamilton it's running");
			time = System.currentTimeMillis();
		}	
	}
	
	public long getTime() {
		return time;
	}
	
}