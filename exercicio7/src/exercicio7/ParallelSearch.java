package exercicio7;

import java.util.concurrent.RecursiveTask;

public class ParallelSearch extends RecursiveTask<Boolean>{

	private int data[];
	int start, end; 
	int key;
	
	public ParallelSearch(int[] data, int start, int end, int key) {
		this.data = data;
		this.start = start;
		this.end = end;
		this.key = key;
	}

	@Override
	protected Boolean compute() {
		
		if((end - start + 1) <= 100) {
			for (int i = start; i <= end; i++) {
				if(data[i] == key) {
					return true;
				}
			}
		} else {
			int middle = (start+end)/2;
			ParallelSearch taskA = new ParallelSearch(data, start, middle, key);
			ParallelSearch taskB = new ParallelSearch(data, middle+1, end, key);
			taskA.fork();
			taskB.fork();
			return taskA.join() || taskB.join();
		}
		
		return false;
	}
	
}
