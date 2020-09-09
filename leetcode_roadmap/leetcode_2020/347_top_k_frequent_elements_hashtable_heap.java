
// heap, top k frequent elements
// hashtable store count number
// method 2
// running time: O(klgn)
/*
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> res =  new ArrayList<Integer>();
        Map<Integer, Integer> hashmap = new HashMap<Integer, Integer>();
        int count =0;
        for(int i : nums){
            count = hashmap.getOrDefault(i, 0);
            hashmap.put(i, count+1);
        }
        // heap building
        // optimization, maintain the maximum size of heap
        // store the value of elements
        // minhead
        
        PriorityQueue<Integer> heap = new PriorityQueue(
            new Comparator<Integer>(){
                public int compare(Integer a, Integer b){
                    return  hashmap.get(a) - hashmap.get(b);
                }
            }
        );
        
        // keep k size min heap
        for(int i : hashmap.keySet()){
            heap.offer(i);
            if(heap.size()>k){
                heap.poll();
            }
        }
        // build res list
        for(int i=0; i<k; i++){
            res.add(heap.poll());
        }
        Collections.reverse(res);
        return res;
    }
}
*/



























// accumulation function
// default minheap:  PriorityQueue<Integer> pq= new PriorityQueue<Integer>();


// simple maxheap: PriorityQueue<Integer> queue = new PriorityQueue<>(10, Collections.reverseOrder());
// The Collections.reverseOrder() provides a Comparator that would sort the elements in the PriorityQueue in a the oposite order to their natural order in this case.



// self definition heap
/*
PriorityQueue<Integer> pq = new PriorityQueue<Integer> (
  new Comparator<Integer> () {
    public int compare(Integer a, Integer b) {
       return b - a;
    }
  }
);

*/


// or
/*
PriorityQueue<Integer> heap =
            new PriorityQueue<Integer>((n1, n2) -> hashmap.get(n1) - hashmap.get(n2));
*/



// Arrays.sort(twoDim, Comparator.comparingInt(a -> a[0])


//Natural order
// Collections.sort(names);    //[Alex, Brian, Charles, David]
 
//Reverse order
// Collections.sort(names, Collections.reverseOrder()); 

// Comparator<Employee> compareById = (Employee o1, Employee o2) -> o1.getId().compareTo( o2.getId() );
 
// Collections.sort(employees, compareById);