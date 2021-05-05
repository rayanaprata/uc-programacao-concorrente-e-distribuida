package exemploUm;

public class ThreadContadora extends Thread{
	
	private Contador contador;
	
	public ThreadContadora(Contador contador) {
		super();
		this.contador = contador;
	}
	
	@Override
	public void run() {
		for (int i=0; i<20000; i++) {
			contador.contar();
		}
	}
	
}
