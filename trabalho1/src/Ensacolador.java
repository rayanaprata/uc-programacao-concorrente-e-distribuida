import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Ensacolador implements Runnable {

	private Buffer bufferRampa;
	private int qtdItensCompra;
	Produtos produto;

	public Ensacolador(Buffer shared, int qtdItensCompra) {
		bufferRampa = shared;
		this.qtdItensCompra = qtdItensCompra;
	}

	@Override
	public void run() {
		for (int i = 0; i < qtdItensCompra; i++) {
			try {
				Thread.sleep(2000);
				do {
					produto = bufferRampa.get();
				} while (produto.getDescricao() != "Ultimo produto");

				System.out.printf("-Ensacolador: [" + getHora() + "] Fim do ensacolamento\n");

			} catch (InterruptedException exception) {
				exception.printStackTrace();
			}
		}
	}

	public String getHora() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date hora = Calendar.getInstance().getTime();
		String dataFormatada = sdf.format(hora);
		return dataFormatada;
	}
}

//Ensacolador: o ensacolador retira os itens da rampa de ensacolamento e os acomoda em sacolas (2 segundos para ensacar). 
//Saídas exemplo:
//-Ensacolador: [hora:minuto:segundo] Acomodando item X na sacola
//-Ensacolador: [hora:minuto:segundo] Fim do ensacolamento
