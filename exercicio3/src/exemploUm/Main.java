package exemploUm;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Contador c = new Contador();
		ThreadContadora t1 = new ThreadContadora(c);
		ThreadContadora t2 = new ThreadContadora(c);
		ThreadContadora t3 = new ThreadContadora(c);
		
		t1.start();
		t2.start();
		t3.start();
		
		t1.join();
		t2.join();
		t3.join();
		
		System.out.println("Valor final do contador: " + c.getContador());
	}
}