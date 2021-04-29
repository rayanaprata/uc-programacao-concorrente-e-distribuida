package exercicio1;

public class Thread3 implements Runnable{

	@Override
	public void run() {
		
		int contador = 10;
		
		System.out.println("Tread 3 - Imprime de 11 a 15");
		
		for(int i=0; i<5; i++) {
			contador++;
			System.out.println("Tread 3 - " + contador);
		}
		
	}

}
