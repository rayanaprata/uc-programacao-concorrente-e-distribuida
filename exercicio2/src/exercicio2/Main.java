package exercicio2;

public class Main {
	public static void main(String[] args) {
		
		MaxVerstappen objVerstappen = new MaxVerstappen();
		LewisHamilton objHamilton = new LewisHamilton();
		
		Thread verstappen = new Thread(objVerstappen);
		Thread hamilton = new Thread(objHamilton);		
		
		verstappen.start();
		hamilton.start();
		
		try {
			verstappen.join();
			hamilton.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		if(objVerstappen.getTime() < objHamilton.getTime()) {
			System.out.println("Verstappen is winner");
		}else if(objVerstappen.getTime() > objHamilton.getTime()){
			System.out.println("Hamilton is winner");
		}
	}
}
