package exercicio6;

public class SemaphoreBuffer implements Buffer {
	private int elementInsert = 0;
	private int positRead = 0;
	private int positWrite = 0;

	private int buffer[];
	private Semaforo mutex = new Semaforo(1);
	private Semaforo cond = new Semaforo(0);

	public SemaphoreBuffer(int i) {
		buffer = new int[i]; // construtor recebe o número de threads simultâneas que poderão passar
	}

	public void set(int value) {
		mutex.acquire(); // equivale ao p() -> pegar o semaforo
		buffer[positWrite] = value;
		System.out.printf("Producer writes\t%2d", value);
		positWrite = (positWrite + 1) % buffer.length;
		mutex.release(); // equivale ao v() -> esperar o semaforo
		cond.release();
	}

	public int get() {
		int value = -1;
		value = buffer[positRead];
		positRead = (positRead + 1) % buffer.length;
		elementInsert--;
		mutex.acquire();
		System.out.printf("Consumer writes\t%2d", value);
		mutex.release();
		return value;
	}
}
