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
			invokeAll(taskA, taskB);
			MergeSort.mergeSortRecursivo(start, end, data);
		}
	}

}
