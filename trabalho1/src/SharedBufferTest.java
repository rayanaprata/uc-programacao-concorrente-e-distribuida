

// Fig 23.10: SharedBufferTest.java
// Application shows two threads manipulating an unsynchronized buffer.

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SharedBufferTest
{
   public static void main( String[] args )
   {
      ExecutorService application = Executors.newFixedThreadPool( 3 );

      Buffer sharedLocation = new BufferCircular(5);
      Buffer bufferRampa = new BufferRampa(5);

      try 
      {
         application.execute( new Comprador( sharedLocation, 5 ) );
         application.execute( new Caixa( sharedLocation, bufferRampa, 5 ) );
         application.execute( new Ensacolador( bufferRampa, 5 ));
      } // end try
      catch ( Exception exception )
      {
         exception.printStackTrace();
      } // end catch

      application.shutdown(); // terminate application when threads end
   } // end main
} // end class SharedBufferTest



/**************************************************************************
 * (C) Copyright 1992-2005 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/