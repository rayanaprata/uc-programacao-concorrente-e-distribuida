package exercicio8;


public class LoggerTest {

	public static void main(String[] args) throws InterruptedException {
		
		Logger logger = Logger.getInstance();
		int messages = 50;
				
		for (int i = 0; i < messages; i++) {
			String msg = "Message number " + i;
			logger.putMessage(msg);
			Thread.sleep((long) (1000 * Math.random()));
		}
		System.out.println("Done");
	}

}
