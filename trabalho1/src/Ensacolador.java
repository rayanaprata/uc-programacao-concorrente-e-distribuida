import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Ensacolador implements Runnable{

	private Buffer bufferRampa;
	private int qtdItensCompra;
	Produtos produto;
	
	public Ensacolador( Buffer shared, int qtdItensCompra ){
		bufferRampa = shared;
		this.qtdItensCompra = qtdItensCompra;
	}
	
	@Override
	public void run() {
		 for(int i=0; i<qtdItensCompra; i++){
			   try{               
	            Thread.sleep(2000);
	            do {
	            	produto = bufferRampa.get();
	            }while(produto.getDescricao() != "Ultimo produto");
	            
	            System.out.printf( "-Ensacolador: [" + getHora() + "] Fim do ensacolamento\n" );
	            
	         } // end try
	         // if sleeping thread interrupted, print stack trace
	         catch ( InterruptedException exception ) 
	         {
	            exception.printStackTrace();
	         }		
		 }
	}
	 public String getHora() {
	 	  SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	 	  Date hora = Calendar.getInstance().getTime(); // Ou qualquer outra forma que tem
	 	  String dataFormatada = sdf.format(hora);
	 	  return dataFormatada;
	   }
}
