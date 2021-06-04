import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Rampa implements Buffer {

	private ItensMercado buffer[];
	private int elementosInseridos = 0;
	private int posicaoLeitura = 0;
	private int posicaoEscrita = 0;

	public Rampa(int capacidade) {
		super();
		buffer = new ItensMercado[capacidade];
	}

	public synchronized void set(ItensMercado produto) {
		try {
			while (elementosInseridos == buffer.length) {
				wait();
			}
			buffer[posicaoEscrita] = produto;
			posicaoEscrita = (posicaoEscrita + 1) % buffer.length;
			elementosInseridos++;
			notifyAll();
		} catch (InterruptedException e) {}
	}

	public synchronized ItensMercado get() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date hora = Calendar.getInstance().getTime();
		String dataFormatada = sdf.format(hora);
		ItensMercado valor = null;
		try {
			while (elementosInseridos == 0) {
				wait();
			}
			valor = buffer[posicaoLeitura];
			posicaoLeitura = (posicaoLeitura + 1) % buffer.length;
			elementosInseridos--;
			if (valor.getNome() != "Ultimo produto") {
				System.out.printf("-Ensacolador: [" + dataFormatada + "] Acomodando " + valor.getNome() + " na sacola\n");
			}
			notifyAll();
		} catch (InterruptedException e) {}
		return valor;
	}

}

//Rampa de ensacolamento: é onde o caixa coloca os itens e o ensacolador retira os itens. Este
//elemento deverá ser modelado como buffer compartilhado.
