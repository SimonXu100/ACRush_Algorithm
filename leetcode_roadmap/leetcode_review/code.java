Class solution{

	public List<Ingteger> topk(int[] array, int M){
		int n = array.length;
		// heap 
		PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
		for(int value : array){
			heap.offer(value);
			if(heap.size() > M){
				heap.poll();
			}
		}

	}

}

O(nlgm)


