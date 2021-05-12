package exercicio5;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockBuffer implements Buffer {
	private int buffer = -1; // shared by producer and consumer threads
	private ReentrantLock mutex = new ReentrantLock();
	private Condition cSet = mutex.newCondition();
	private Condition cGet = mutex.newCondition(); //condition permite implementar espera e notificação da mesa forma que o wait() e notifyAll()
	
	/*
	private Condition c;
	
	void example() {
		c.await();       // wait();
		c.signal();      // notify();
		c.signalAll();   // notifyAll()
	}
	*/

	// place value into buffer
	public void set(int value) {
		try {
			mutex.lock();
			while (buffer != -1) {
				cSet.await();
			}
			System.out.printf("Producer writes\t%2d", value);
			buffer = value;
			cGet.signalAll();
		} catch (Exception e) {} 
		finally {
			mutex.unlock();
		}

	} // end method set

	// return value from buffer
	public int get() {

		int val = -1;
		
		try {
			mutex.lock();
			while (buffer == -1) {
				cGet.await();
			}
			val = buffer;
			System.out.printf("Consumer reads\t%2d", buffer);
			buffer = -1;
			cSet.signalAll();
		} catch (Exception e) {}
		finally {
			mutex.unlock();
		}

		return val;
	} // end method get
}
