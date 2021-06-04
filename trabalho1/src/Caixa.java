import java.util.Random;

public class Caixa implements Runnable {

	private Buffer sharedLocation;
	private Buffer bufferRampa;
	private int qtdItensCompra;

	Random gerador = new Random();
	int time = gerador.nextInt((4000 - 2000) + 1) + 2000;

	public Caixa(Buffer esteiraBuffer, Buffer rampaBuffer, int qtdItensCompra) {
		sharedLocation = esteiraBuffer;
		bufferRampa = rampaBuffer;
		this.qtdItensCompra = qtdItensCompra;
	}

	public void run() {
		for (int i = 0; i < qtdItensCompra; i++) {
			try {
				Thread.sleep(time);
				bufferRampa.set(sharedLocation.get());
			} catch (InterruptedException e) {}
		}
	}
}

//Caixa: o caixa retira cada item da esteira e efetua a contabilização do mesmo, passando cada item
//para a rampa de ensacolamento. Cada item leva entre 2 a 4 segundos para ser processado. Ao final,
//ele indica ao comprador o valor a ser pago. Este elemento deverá ser modelado como uma Thread.
//Saídas exemplo:
//-Caixa: [hora:minuto:segundo] Passando item X
//-Caixa: [hora:minuto:segundo] Informando conta de Z R$