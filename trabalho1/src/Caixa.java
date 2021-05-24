
public class Caixa implements Runnable {

	private Buffer sharedLocation;
	private Buffer bufferRampa;
	private int qtdItensCompra;

	public Caixa(Buffer esteiraBuffer, Buffer rampaBuffer, int qtdItensCompra) {
		sharedLocation = esteiraBuffer;
		bufferRampa = rampaBuffer;
		this.qtdItensCompra = qtdItensCompra;
	}

	public void run() {
		for (int i = 0; i < qtdItensCompra; i++) {
			try {
				Thread.sleep(2000);
				bufferRampa.set(sharedLocation.get());
			} catch (InterruptedException exception) {
				exception.printStackTrace();
			}
		}
	}
}

//Caixa: o caixa retira cada item da esteira e efetua a contabilização do mesmo, passando cada item
//para a rampa de ensacolamento. Cada item leva entre 2 a 4 segundos para ser processado. Ao final,
//ele indica ao comprador o valor a ser pago. Este elemento deverá ser modelado como uma Thread.
//Saídas exemplo:
//-Caixa: [hora:minuto:segundo] Passando item X
//-Caixa: [hora:minuto:segundo] Informando conta de Z R$