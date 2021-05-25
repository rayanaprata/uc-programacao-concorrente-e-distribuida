package exercicio7;

import java.util.concurrent.RecursiveAction;

public class ParallellInc extends RecursiveAction {

	private static final long serialVersionUID = 1L;
	private int data[];
	int start, end;

	public ParallellInc(int[] data, int start, int end) {
		this.data = data;
		this.start = start;
		this.end = end;
	}

	@Override
	protected void compute() {
		if (start < end) {
			int middle = (start + end) / 2;
			ParallellInc taskA = new ParallellInc(data, start, middle);
			ParallellInc taskB = new ParallellInc(data, middle+1, end);
			intercala(data, start, middle, end);
			invokeAll(taskA, taskB);
		}
	}
	
	private static void intercala (int[] data, int start, int middle, int end) {
		int newArray[] = new int[end - start];
		int s = start;
		int m = middle;
		int pos = 0;
		
		while(s < middle && m < end) {
		      /* Se o vetor[i] for menor que o vetor[m], então guarda o valor do
		        vetor[s] pois este é menor. */
		      if(data[s] <= data[m]) {
		    	  newArray[pos] = data[s];
		        pos = pos + 1;
		        s = s + 1;
		      // Senão guarda o valor do vetor[m] pois este é o menor.
		      } else {
		    	  newArray[pos] = data[m];
		        pos = pos + 1;
		        m = m + 1;
		      }
		    }
		    
		    // Adicionar no vetor os elementos que estão entre o inicio e meio,
		    // que ainda não foram adicionados no vetor.
		    while(s < middle) {
		    	newArray[pos] = data[s];
		      pos = pos + 1;
		      s = s + 1;
		    }
		    
		    // Adicionar no vetor os elementos que estão entre o meio e o fim,
		    // que ainda não foram adicionados no vetor.
		    while(m < end) {
		    	newArray[pos] = data[m];
		      pos = pos + 1;
		      m = m + 1;
		    }
		    
		    // Coloca no vetor os valores já ordenados.
		    for(pos = 0, s = start; s < end; s++, pos++) {
		      data[s] = newArray[pos];
		    }
		
	}

}
