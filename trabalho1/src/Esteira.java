import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Esteira implements Buffer {

	private ItensMercado buffer[];
	private int elementosInseridos = 0;
	private int posicaoLeitura = 0;
	private int posicaoEscrita = 0;
	private double valorCompra = 0;

	public Esteira(int capacidade) {
		super();
		buffer = new ItensMercado[capacidade];
	}

	public synchronized void set(ItensMercado produto) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date hora = Calendar.getInstance().getTime();
		String data = sdf.format(hora);
		try {
			while (elementosInseridos == buffer.length) {
				wait();
			}
			buffer[posicaoEscrita] = produto;
			if (produto.getNome() != "Ultimo produto") {
				System.out.printf("-Comprador: [" + data + "] Colocando " + produto.getNome() + " na esteira.\n");
			}
			posicaoEscrita = (posicaoEscrita + 1) % buffer.length;
			elementosInseridos++;
			notifyAll();
		} catch (InterruptedException e) {}
	}

	public synchronized ItensMercado get() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date hora = Calendar.getInstance().getTime();
		String data = sdf.format(hora);
		ItensMercado valor = null;
		try {
			while (elementosInseridos == 0) {
				wait();
			}
			valor = buffer[posicaoLeitura];
			posicaoLeitura = (posicaoLeitura + 1) % buffer.length;
			elementosInseridos--;
			valorCompra += valor.getPreco();
			if (valor.getNome() != "Ultimo produto") {
				System.out.printf("-Caixa: [" + data + "] Passando " + valor.getNome() + " - "
						+ valor.getPreco() + "\n");
			}
			if (valor.getNome() == "Ultimo produto") {
				System.out.println("-Caixa: [" + data + "] Informando conta de " + valorCompra + "R$\n");
				System.out.println("-Comprador: [" + data + "] Pagando conta de " + valorCompra + "R$\n");
			}

			notifyAll();
		} catch (InterruptedException e) {}
		return valor;
	}
}

// Esteira: a esteira é onde o comprador coloca os itens e de onde caixa retira os itens. Este elemento
// deverá ser modelado como buffer compartilhado.
