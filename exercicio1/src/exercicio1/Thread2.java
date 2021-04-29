package exercicio1;

public class Thread2 implements Runnable{

	@Override
	public void run() {
		
		int contador = 5;
		
		System.out.println("Tread 2 - Imprime de 6 a 10");
		
		for(int i=0; i<5; i++) {
			contador++;
			System.out.println("Tread 2 - " + contador);
		}
		
	}

}