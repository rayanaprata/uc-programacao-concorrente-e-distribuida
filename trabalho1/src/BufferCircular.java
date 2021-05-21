import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BufferCircular implements Buffer {	
	
	private Produtos buffer[];	
	private int elementosInseridos = 0;
	private int posicaoLeitura = 0;
	private int posicaoEscrita = 0;	
	private double valorCompra = 0;
	
	public BufferCircular(int capacidade) {
		super();
		buffer = new Produtos[capacidade]; 
	}
	
	public synchronized void set(Produtos produto) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date hora = Calendar.getInstance().getTime(); // Ou qualquer outra forma que tem
		String dataFormatada = sdf.format(hora);
		try {
			while(elementosInseridos == buffer.length) {
				wait();
			}			
			buffer[posicaoEscrita] = produto;
			if(produto.getDescricao() != "Ultimo produto") {
				System.out.printf( "-Comprador: [" + dataFormatada + "] Colocando " + produto.getDescricao() + " na esteira.\n" );
			}
			posicaoEscrita = (posicaoEscrita + 1) % buffer.length;
			elementosInseridos++;
			notifyAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	} 

	public synchronized Produtos get() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date hora = Calendar.getInstance().getTime(); 
		String dataFormatada = sdf.format(hora);
		Produtos valor = null;		
		try {
			while(elementosInseridos == 0) {
				wait();
			}
			valor = buffer[posicaoLeitura];
			posicaoLeitura = (posicaoLeitura + 1) % buffer.length;
			elementosInseridos--;
			valorCompra += valor.getPreco();
			if(valor.getDescricao() != "Ultimo produto") {
				System.out.printf("-Caixa: [" + dataFormatada + "] Passando " + valor.getDescricao() + " - " + valor.getPreco() + "\n");
			}
			if(valor.getDescricao() == "Ultimo produto"){
				System.out.println("-Caixa: [" + dataFormatada + "] Informando valor da compra: " + valorCompra + "\n");
				System.out.println("-Comprador: [" + dataFormatada + "] Pagando a compra: " + valorCompra + "\n");
			}
			
			notifyAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
		return valor;
	}
}

