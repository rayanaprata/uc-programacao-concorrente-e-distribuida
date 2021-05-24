
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Comprador implements Runnable {
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
		superMercado.add(produto = new Produtos("Papel Higiênco", 18.00));
		superMercado.add(produto = new Produtos("Sabonete", 3.20));
		superMercado.add(produto = new Produtos("Pasta de Dente", 7.80));
		superMercado.add(produto = new Produtos("Peito de Frango", 16.00));
		superMercado.add(produto = new Produtos("Carne Moída", 23.00));
		superMercado.add(produto = new Produtos("Batata Doce", 6.00));
		superMercado.add(produto = new Produtos("Tomate", 7.50));
		superMercado.add(produto = new Produtos("Morango", 8.50));
		superMercado.add(produto = new Produtos("Limão", 4.00));
		superMercado.add(produto = new Produtos("Arroz", 15.00));
	}

	public void run() {
		System.out.printf("-Comprador: [" + getHora() + "] Iniciando compras.\n");
		carregarCarrinho();
		int cont = 1;
		for (Produtos prod : superMercado) {
			try {
				Thread.sleep(generator.nextInt(2000));
				if (cont == qtdItensCompra) {
					carrinhoCompras.add(produto = new Produtos("Ultimo produto", 0.00));
					break;
				} else {
					carrinhoCompras.add(prod);
					System.out.printf(
							"-Comprador: [" + getHora() + "] Colocando " + prod.getDescricao() + " no carrinho.\n");
				}
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			cont = cont + 1;
		}

		System.out.printf("-Comprador: [" + getHora() + "] Indo para o caixa.\n");

		for (Produtos prod : carrinhoCompras) {
			try {
				Thread.sleep(1000);
				sharedLocation.set(prod);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		System.out.printf("Carrinho vazio.\n");
	}

	public String getHora() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date hora = Calendar.getInstance().getTime();
		String dataFormatada = sdf.format(hora);
		return dataFormatada;
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