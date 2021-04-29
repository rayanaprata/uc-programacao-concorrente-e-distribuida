package exercicio1;

public class Main {

	public static void main(String[] args) {
		
		Thread t1 = new Thread(new Thread1());
		Thread t2 = new Thread(new Thread2());
		Thread t3 = new Thread(new Thread3());
		
		t1.start();
		t2.start();
		t3.start();

	}

}
