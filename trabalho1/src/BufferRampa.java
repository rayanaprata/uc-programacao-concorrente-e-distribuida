import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BufferRampa implements Buffer {

	private Produtos buffer[];
	private int elementosInseridos = 0;
	private int posicaoLeitura = 0;
	private int posicaoEscrita = 0;

	public BufferRampa(int capacidade) {
		super();
		buffer = new Produtos[capacidade];
	}

	public synchronized void set(Produtos produto) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date hora = Calendar.getInstance().getTime();
		String dataFormatada = sdf.format(hora);
		try {
			while (elementosInseridos == buffer.length) {
				wait();
			}
			buffer[posicaoEscrita] = produto;
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
			while (elementosInseridos == 0) {
				wait();
			}
			valor = buffer[posicaoLeitura];
			posicaoLeitura = (posicaoLeitura + 1) % buffer.length;
			elementosInseridos--;
			if (valor.getDescricao() != "Ultimo produto") {
				System.out.printf(
						"-Ensacolador: [" + dataFormatada + "] Acomodando " + valor.getDescricao() + " na sacola.\n");
			}
			notifyAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return valor;
	}

}

//Rampa de ensacolamento: é onde o caixa coloca os itens e o ensacolador retira os itens. Este
//elemento deverá ser modelado como buffer compartilhado.
