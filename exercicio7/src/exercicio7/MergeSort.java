package exercicio7;

public class MergeSort {

	public static void mergeSortRecursivo(int inicio, int fim, int[] vetor) {
		if (inicio < fim - 1) {

			int meio = (inicio + fim) / 2;

			mergeSortRecursivo(inicio, meio, vetor);
			mergeSortRecursivo(meio, fim, vetor);
			
			intercala(vetor, inicio, meio, fim);
		}
	}

	public static void intercala(int[] data, int start, int middle, int end) {
		int newArray[] = new int[end - start];
		int s = start;
		int m = middle;
		int pos = 0;

		while (s < middle && m < end) {

			if (data[s] <= data[m]) {
				newArray[pos] = data[s];
				pos = pos + 1;
				s = s + 1;

			} else {
				newArray[pos] = data[m];
				pos = pos + 1;
				m = m + 1;
			}
		}

		while (s < middle) {
			newArray[pos] = data[s];
			pos = pos + 1;
			s = s + 1;
		}

		while (m < end) {
			newArray[pos] = data[m];
			pos = pos + 1;
			m = m + 1;
		}

		for (pos = 0, s = start; s < end; s++, pos++) {
			data[s] = newArray[pos];
		}
	}
}
