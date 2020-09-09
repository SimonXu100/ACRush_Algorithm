// method 1 brute force:
// every time sorting 
// running time: O(nlgn) every operation
/*
class MedianFinder {
    ArrayList<Integer> lst;
    public MedianFinder() {
        lst = new ArrayList<Integer>();
    }
    
    public void addNum(int num) {
        lst.add(num);
    }
    
    public double findMedian() {
        Collections.sort(lst);
        int n = lst.size();
        return n%2==0? (0.5 * (lst.get(n/2-1) + lst.get(n/2))): lst.get(n/2);
    }
}
*/

    

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */


// method 2: maintain insertion sort order
// running time: insert a value in a sorted order
// 允许将一个数字添加到已排序的数字列表中，但仍保持整个列表的排序状态？插入排序
// binary search the right position and insert 
//当插入查询的数量较少或者中间查找查询的数量大致相同
// runngint time: O(lgn) + O(n) 
// space: O(n)
/*
class MedianFinder {
    ArrayList<Integer> lst;
    public MedianFinder() {
        lst = new ArrayList<Integer>();
    }
    
    public void addNum(int num) {
        int size = lst.size();
        int i=0;
        if(size == 0) lst.add(num);
        else{
            for(i=0; i<size; i++){
                if( num < lst.get(i)) {
                    lst.add(i, num);
                    break;
                }
            }
            if(i==size) lst.add(i,num);
        }
    }
    
    public double findMedian() {
        int n = lst.size();
        return n%2==0? (0.5 * (lst.get(n/2-1) + lst.get(n/2))): lst.get(n/2);   
    }
}

*/



// method 3: heap
// maintain the exact order of number
// basic idea
// 如果我们可以一直直接访问中值元素，那么找到中值将需要一个恒定的时间。
// 如果我们能找到一种相当快速的方法来增加容器的数量，那么所产生的额外操作可能会减少
// 只需要一种一致的方式来访问中值元素，这是不容易观察到的。不需要对整个输入进行排序
// Basic idea: 如果我们可以用以下方式维护两个堆：
// 用于存储输入数字中较小一半的最大堆
// 用于存储输入数字的较大一半的最小堆, 这样就可以访问输入中的中值：它们组成堆的顶部
// 那么 xx 和 yy 几乎小于（或等于）元素的一半，大于（或等于）另一半。这就是中值元素的定义
// 难点平衡这两颗树
// running time: O(5*lgn) + O(1) = O(lgn)
// worst case: 3 insertion and 2 remove
// space: O(n)
/*
import java.util.PriorityQueue;
import java.util.Comparator;
class MedianFinder {
    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;
    public MedianFinder() {
        // initilization
        // default minHeap
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<Integer>(new Comparator<Integer>(){
            public int compare(Integer a, Integer b){
                return b-a;
            }            
        });  
    }
    
    public void addNum(int num) {
        // keep balance between minHeap and maxHeap
        // at most, we can only allow manHeap has one more element than minHeap
        // only after mutually comparison, we can know the relative size
        maxHeap.offer(num);
        minHeap.offer(maxHeap.peek());
        maxHeap.poll();
        
        if(maxHeap.size() < minHeap.size()){
            maxHeap.offer(minHeap.peek());
            minHeap.poll();
        }
    }
    // find median in O(1) times
    public double findMedian() {
        return maxHeap.size() > minHeap.size()? maxHeap.peek() : (maxHeap.peek() + minHeap.peek())*0.5;
    }
}
*/

// method 4: AVL tree
// 自平衡二进制搜索树

class MedianFinder {

    
    public MedianFinder() {

        
    }
    
    public void addNum(int num) {

        
    }
    
    public double findMedian() {
    	
        
    }
}


// summary: 自平衡二进制搜索树（如AVL树）具有一些非常有趣的特性。它们将树的高度保持在对数范围内。因此，插入新元素具有相当好的时间性能。中值总是缠绕在树根或它的一个子树上
// 添加数字 num 时，会出现三种情况：
/*
容器当前为空。因此，我们只需插入 num 并设置两个指针指向这个元素。
容器当前包含奇数个元素。这意味着两个指针当前都指向同一个元素。
如果 num 不等于当前的中位数元素，则 num 将位于元素的任一侧。无论哪一边，该部分的大小都会增加，因此相应的指针会更新。例如，如果 num 小于中位数元素，则在插入 num 时，输入的较小半部分的大小将增加 11。
如果 num 等于当前的中位数元素，那么所采取的操作取决于 num 是如何插入数据的。
容器当前包含偶数个元素。这意味着指针当前指向连续的元素。
如果 num 是两个中值元素之间的数字，则 num 将成为新的中值。两个指针都必须指向它。
否则，num 会增加较小或较高一半的大小。我们相应地更新指针。必须记住，两个指针现在必须指向同一个元素。
*/




/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */








































// accumulation function
// Priority Queue
// minimum heap
// for example
/*
PriorityQueue<int[]> heap = new PriorityQueue<int[]> (intervals.length, new Comparator<int[]>{
	public int compare(int[] a, int[] b){
		return a[1] - b[1];
	}
  }
);
*/



// Arrays.sort()
// default: starting from little one
/*
Arryas.sort(intervals, new Comparator<int[]>(){
	public int compare(int[] a, int[] b){
		return a[0] - b[0];
	}
});
*/


// sort
// Collections.sort();
// Arrays.sort()
// ArrayList.toArray()



// The java. util. ArrayList. add(int index, E elemen) method inserts the specified element E 
// at the specified position in this list.It shifts the element currently at that position 
// (if any) and any subsequent elements to the right (will add one to their indices)
// public void add(int index, E element)


// some kind of consistent method to get access to median
// 如果我们可以用以下方式维护两个堆：
// 用于存储输入数字中较小一半的最大堆
// 用于存储输入数字的较大一半的最小堆
// 这样就可以访问输入中的中值：它们组成堆的顶部 



// heap
// 默认实现了一个最小堆。
/*
Queue<Integer> priorityQueue = new PriorityQueue<>(); 

// 实现最大堆
Queue<ListNode> priorityQueue = new PriorityQueue<ListNode>(lists.size(),new Comparator<ListNode>(){
 
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val-o2.val;
            }
 
        });
*/





















