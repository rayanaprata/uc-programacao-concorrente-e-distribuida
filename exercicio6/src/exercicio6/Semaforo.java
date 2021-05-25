package exercicio6;

public class Semaforo {

	private int cont;

	public Semaforo(int cont) {
		this.cont = cont;
	}

	public synchronized void acquire() {
		while (cont == 0) {
			try {
				wait();
			} catch (InterruptedException e) {}
			cont--;
		}
	}

	public synchronized void release() {
		cont++;
		notifyAll();
	}

}
