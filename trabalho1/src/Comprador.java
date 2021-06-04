
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Comprador implements Runnable {
	private Buffer sharedLocation;
	ArrayList<ItensMercado> superMercado = new ArrayList<ItensMercado>();
	ArrayList<ItensMercado> carrinhoCompras = new ArrayList<ItensMercado>();
	private int qtdItensCompra;

	Random gerador = new Random();
	int time = gerador.nextInt((2000 - 1000) + 1) + 1000;
	
	public Comprador(Buffer shared, int qtdItensCompra) {
		sharedLocation = shared;
		this.qtdItensCompra = qtdItensCompra;
	}

	public void carregarCarrinho() {
		superMercado.add(new ItensMercado("Papel Higiênco", 18.00));
		superMercado.add(new ItensMercado("Sabonete", 3.20));
		superMercado.add(new ItensMercado("Pasta de Dente", 7.80));
		superMercado.add(new ItensMercado("Peito de Frango", 16.00));
		superMercado.add(new ItensMercado("Carne Moída", 23.00));
		superMercado.add(new ItensMercado("Batata Doce", 6.00));
		superMercado.add(new ItensMercado("Tomate", 7.50));
		superMercado.add(new ItensMercado("Morango", 8.50));
		superMercado.add(new ItensMercado("Limão", 4.00));
		superMercado.add(new ItensMercado("Arroz", 15.00));
		superMercado.add(new ItensMercado("Macarrão", 2.00));
		superMercado.add(new ItensMercado("Cerveja", 6.20));
		superMercado.add(new ItensMercado("Ovo", 11.80));
		superMercado.add(new ItensMercado("Miojo", 16.00));
		superMercado.add(new ItensMercado("Brócolis", 23.00));
		superMercado.add(new ItensMercado("Bala", 6.00));
		superMercado.add(new ItensMercado("Chiclete", 7.50));
		superMercado.add(new ItensMercado("Camisinha", 8.50));
		superMercado.add(new ItensMercado("Desodorante", 4.00));
		superMercado.add(new ItensMercado("Shampoo", 15.00));
		superMercado.add(new ItensMercado("Chocolate", 18.00));
		superMercado.add(new ItensMercado("Vodka", 3.20));
		superMercado.add(new ItensMercado("Amendoim", 7.80));
		superMercado.add(new ItensMercado("Ração de Cachorro", 16.00));
		superMercado.add(new ItensMercado("Fralda", 23.00));
		superMercado.add(new ItensMercado("Palito de Dente", 6.00));
		superMercado.add(new ItensMercado("Vassoura", 7.50));
		superMercado.add(new ItensMercado("Farinha", 8.50));
		superMercado.add(new ItensMercado("Azeite", 4.00));
		superMercado.add(new ItensMercado("Mostarda", 15.00));		
	}

	public void run() {
		System.out.printf("-Comprador: [" + getHora() + "] Iniciando compras\n");
		carregarCarrinho();
		int cont = 1;
		for (ItensMercado item : superMercado) {
			try {
				Thread.sleep(time);
				if (cont == qtdItensCompra) {
					carrinhoCompras.add(new ItensMercado("Ultimo produto", 0.00));
					break;
				} else {
					carrinhoCompras.add(item);
					System.out.printf(
							"-Comprador: [" + getHora() + "] Colocando " + item.getNome() + " no carrinho\n");
				}
			} catch (InterruptedException e) {}
			cont = cont + 1;
		}

		System.out.printf("-Comprador: [" + getHora() + "] Indo para o caixa\n");

		for (ItensMercado item : carrinhoCompras) {
			try {
				Thread.sleep(1000);
				sharedLocation.set(item);
			} catch (Exception e) {}
		}

		System.out.printf("Carrinho vazio.\n");
	}

	public String getHora() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date hora = Calendar.getInstance().getTime();
		String data = sdf.format(hora);
		return data;
	}

}

//Comprador e seu carrinho de compra: o comprador retira um carrinho na entrada do supermercado
//e começa as suas compras. Ele deverá comprar um número aleatório de itens (entre 10 e 30), sendo
//que cada item demora entre 1 e 2 segundos para ser selecionado e colocado no carrinho
//(simplificação). Após a seleção dos itens, o comprador se dirige ao caixa e começa a depositar os itens
//na esteira (1 segundo para depositar cada item). Ao final, ele recebe o valor total do caixa e paga a conta.
//Este elemento deverá ser modelado como uma Thread. Saídas exemplo:
//-Comprador: [hora:minuto:segundo] Iniciando compras.
//-Comprador: [hora:minuto:segundo] Colocando item X no carrinho
//-Comprador: [hora:minuto:segundo] Indo para o caixa
//-Comprador: [hora:minuto:segundo] Depositando item X na esteira
//-Comprador: [hora:minuto:segundo] Pagando conta de Z R$