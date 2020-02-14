
/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */

 /**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
// method 2
// heap 
// every opeartion
// 小顶堆
// when size of the heap < k, input
// when size of the 
// O(lgn)

class KthLargest {
    PriorityQueue<Integer> heap;
    int k;
    public KthLargest(int k, int[] nums) {
        this.k = k;
        // defalut: minheap
        heap = new PriorityQueue<Integer>();
        for(int value : nums) add(value);

    }
    public int add(int val) {
        if(heap.size() < k) heap.offer(val);
        else{
            heap.offer(val);
            heap.poll();
        }
        return heap.peek();
    }
}



// method 3: TreeMap 自动排序
// every operation O(nlgn)
// bugs: TreeSet 不允许重复
// wrong
/*
class KthLargest {
    TreeSet<Integer> set;
    int k;
    public KthLargest(int k, int[] nums) {
        this.k = k;
        set = new TreeSet<Integer>();
        for(int value : nums) add(value);
    }
    
    public int add(int val) {
        if(set.size() <k ) set.add(val);
        else{
            set.add(val);
            set.pollFirst();
        }
        return set.first();
    }
}

*/























