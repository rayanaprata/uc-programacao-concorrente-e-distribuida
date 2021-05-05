package exercicio3;

//Fig. 23.9: UnsynchronizedBuffer.java
//UnsynchronizedBuffer represents a single shared integer.

public class SynchronizedBuffer implements Buffer {
	private int buffer = -1; // shared by producer and consumer threads

	// place value into buffer
	public synchronized void set(int value) {
		try {
			while(buffer != -1) {
				wait();
			}
			System.out.printf("Producer writes\t%2d", value);
			buffer = value;
			notifyAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	} // end method set

	// return value from buffer
	public synchronized int get() {
		
		int val = -1;
		
		try {
			while(buffer == -1) {
				wait();
			}
			val = buffer;
			System.out.printf("Consumer reads\t%2d", buffer);
			buffer = -1;
			notifyAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return val;
	} // end method get
} // end class UnsynchronizedBuffer

/**************************************************************************
* (C) Copyright 1992-2005 by Deitel & Associates, Inc. and * Pearson Education,
* Inc. All Rights Reserved. * * DISCLAIMER: The authors and publisher of this
* book have used their * best efforts in preparing the book. These efforts
* include the * development, research, and testing of the theories and programs
* * to determine their effectiveness. The authors and publisher make * no
* warranty of any kind, expressed or implied, with regard to these * programs
* or to the documentation contained in these books. The authors * and publisher
* shall not be liable in any event for incidental or * consequential damages in
* connection with, or arising out of, the * furnishing, performance, or use of
* these programs. *
*************************************************************************/
