package exercicio1;

public class Thread1 implements Runnable{

	@Override
	public void run() {
		
		int contador = 0;
		
		System.out.println("Tread 1 - Imprime de 1 a 5");
		
		for(int i=0; i<5; i++) {
			contador++;
			System.out.println("Tread 1 - " + contador);
		}
		
	}

}
