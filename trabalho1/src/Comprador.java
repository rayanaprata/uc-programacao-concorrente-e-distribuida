
// Fig. 23.7: Producer.java
// Producer's run method stores the values 1 to 10 in buffer.
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

	public class Comprador implements Runnable 
{
	private static Random generator = new Random();
	private Buffer sharedLocation; 
	private Produtos produto;
	ArrayList<Produtos> superMercado = new ArrayList<Produtos>();
	ArrayList<Produtos> carrinhoCompras = new ArrayList<Produtos>();
	private int qtdItensCompra;
  	
   public Comprador(Buffer shared, int qtdItensCompra) {
	   sharedLocation = shared;
	   this.qtdItensCompra = qtdItensCompra;
   }

   public void carregarCarrinho() {
	   superMercado.add(produto = new Produtos("Feijao", 6.00));
	   superMercado.add(produto = new Produtos("Macarrao", 3.00));
	   superMercado.add(produto = new Produtos("Arroz", 15.00));
	   superMercado.add(produto = new Produtos("Flocao", 3.00));
	   superMercado.add(produto = new Produtos("Sabao", 9.00));
//	   superMercado.add(produto = new Produtos("Detergente", 3.00));
//	   superMercado.add(produto = new Produtos("Amaciante", 6.00));
//	   superMercado.add(produto = new Produtos("Bucha", 3.00));
//	   superMercado.add(produto = new Produtos("Alvejante", 3.00));
//	   superMercado.add(produto = new Produtos("Iogurte", 10.00));
//	   superMercado.add(produto = new Produtos("Leite", 5.00));
//	   superMercado.add(produto = new Produtos("Bolacha", 5.00));
//	   superMercado.add(produto = new Produtos("Biscoito", 5.00));
//	   superMercado.add(produto = new Produtos("Torrada", 4.00));
//	   superMercado.add(produto = new Produtos("Rapadura", 3.00));
//	   superMercado.add(produto = new Produtos("Leite condencado", 4.00));
//	   superMercado.add(produto = new Produtos("Creme de leite", 3.00));
//	   superMercado.add(produto = new Produtos("Doce", 5.00));
//	   superMercado.add(produto = new Produtos("Gelatina", 2.00));
//	   superMercado.add(produto = new Produtos("Mamão", 3.00));
//	   superMercado.add(produto = new Produtos("Manga", 3.00));
//	   superMercado.add(produto = new Produtos("Banana", 4.00));
//	   superMercado.add(produto = new Produtos("Goiaba", 6.00));
//	   superMercado.add(produto = new Produtos("Laranja", 3.00));
//	   superMercado.add(produto = new Produtos("Batata", 3.00));
//	   superMercado.add(produto = new Produtos("Cenoura", 4.00));
//	   superMercado.add(produto = new Produtos("Cebola", 3.00));
//	   superMercado.add(produto = new Produtos("Tomate", 4.00));
//	   superMercado.add(produto = new Produtos("Alface", 2.00));
   }
   
   
   public void run()
   {	 	   
	   System.out.printf( "-Comprador: [" + getHora() + "] Iniciando compras.\n" );
	   carregarCarrinho();
	   int cont = 1;
	   for( Produtos prod : superMercado) {
		   try {	
			   Thread.sleep( generator.nextInt(2000));		
			   if(cont == qtdItensCompra) {
				   carrinhoCompras.add(produto = new Produtos("Ultimo produto", 0.00));
				   break;
			   }else {
				   carrinhoCompras.add(prod);
				   System.out.printf( "-Comprador: [" + getHora() + "] Colocando " + prod.getDescricao() + " no carrinho.\n" );				   
			   }			   
		   } catch (InterruptedException e1) {				
				e1.printStackTrace();
		   }
		   cont += 1;
	   }		   
		   
	   System.out.printf( "-Comprador: [" + getHora() + "] Indo para o caixa.\n" );	
	   
	   for(Produtos prod : carrinhoCompras) {		   
		   try {
			Thread.sleep(1000);
			sharedLocation.set(prod);			
		   } catch (Exception e) {	
			e.printStackTrace();
		   }
	   }

      System.out.printf( "Carrinho vazio.\n" );      
   }
   
   public String getHora() {
 	  SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
 	  Date hora = Calendar.getInstance().getTime(); // Ou qualquer outra forma que tem
 	  String dataFormatada = sdf.format(hora);
 	  return dataFormatada;
   }
   
} 


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