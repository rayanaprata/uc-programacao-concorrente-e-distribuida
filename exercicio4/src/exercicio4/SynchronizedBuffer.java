package exercicio4;

//Fig. 23.9: UnsynchronizedBuffer.java
//UnsynchronizedBuffer represents a single shared integer.

public class SynchronizedBuffer implements Buffer {
	private int buffer = -1; // shared by producer and consumer threads

	// place value into buffer
	public synchronized void set(int value) {
		try {
			while(buffer != -1) { // se tiver um dado ali dentro
				wait(); // espera
			}
		} catch (InterruptedException e) {}
		notifyAll();
		System.out.printf("Producer writes\t%2d", value);
		buffer = value;
	} // end method set

	// return value from buffer
	public synchronized int get() {
		
		int val = -1;
		
		try {
			while(buffer == -1) {
				wait();
			}
			
		} catch (InterruptedException e) {}
		
		val = buffer;
		notifyAll(); // buffer vazio novamente
		System.out.printf("Consumer reads\t%2d", buffer);
		buffer = -1;
		return val;
	} // end method get
} // end class UnsynchronizedBuffer
