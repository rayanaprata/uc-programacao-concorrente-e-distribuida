

// Fig. 23.8: Consumer.java
// Consumer's run method loops ten times reading a value from buffer.

public class Caixa implements Runnable 
{ 
   
   private Buffer sharedLocation; // reference to shared object
   private Buffer bufferRampa;
   private int qtdItensCompra;
  
   // constructor
   public Caixa( Buffer esteiraBuffer, Buffer rampaBuffer, int qtdItensCompra )
   {
      sharedLocation = esteiraBuffer;
      bufferRampa = rampaBuffer;
      this.qtdItensCompra = qtdItensCompra;
   } // end Consumer constructor

   // read sharedLocation's value four times and sum the values
   public void run(){          
	   for(int i=0; i<qtdItensCompra; i++){		   
		   try{               
            Thread.sleep(2000);  
            bufferRampa.set(sharedLocation.get());	           
         } 
         catch ( InterruptedException exception ) 
         {
            exception.printStackTrace();
         }
	   }
   } // end method run
} // end class Consumer


