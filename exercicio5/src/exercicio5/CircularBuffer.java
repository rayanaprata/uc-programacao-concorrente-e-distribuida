package exercicio5;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CircularBuffer implements Buffer {

	private int capacidade;
	private int buffer[];
	
	private int nElementos = 0;
	private int posLeitura = 0;
	private int posEscrita = 0;
	
	private ReentrantLock mutex = new ReentrantLock();
	private Condition cSet = mutex.newCondition();
	private Condition cGet = mutex.newCondition();	
	
	public CircularBuffer(int capacidade) {
		super();
		this.capacidade = capacidade;
		buffer = new int[capacidade]; 
	}
	
	public synchronized void set(int value) {
		try {
			mutex.lock();
			while(nElementos == buffer.length) {
				cSet.await();
			}
			buffer[posEscrita] = value;
			System.out.printf("Producer writes\t%2d", buffer[posEscrita]);
			posEscrita = (posEscrita + 1) % buffer.length;
			nElementos++;
			cGet.signalAll();
		} catch (InterruptedException e) {}
		finally {
			mutex.unlock();
		}
	}

	public synchronized int get() {
		int valor = -1;
		try {
			mutex.lock();
			while(nElementos == 0) {
				cGet.await();
			}
			valor = buffer[posLeitura];
			posLeitura = (posLeitura + 1) % buffer.length;
			nElementos--;
			System.out.printf("Consumer reads\t%2d", valor);
			cSet.signalAll();
		} catch (InterruptedException e) {}		
		finally {
			mutex.unlock();
		}
		return valor;
	}
	
}
