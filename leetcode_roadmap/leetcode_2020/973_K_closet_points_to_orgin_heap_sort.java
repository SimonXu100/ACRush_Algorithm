// heap-- specified heap, sorted by distance
// overwrite the heap comparator
// running time: O(nlgk)
// space: O(lgn)
/*
import java.lang.Math;
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        int[][] ans = new int[K][2];
        // heap 
        PriorityQueue<int[]> heap = new PriorityQueue<int[]>(
            new Comparator<int[]>(){
                public int compare(int[] a, int[] b){
                    return (Math.sqrt(b[0]*b[0] + b[1]*b[1]) - Math.sqrt(a[0]*a[0] + a[1]*a[1])) > 0 ? 1:-1;
                }
            });

        // using max heap, first insert into heap, if the size exceed k, then pop out
        for(int[] temp : points){
            heap.offer(temp);
            if(heap.size() > K) heap.poll();
        }
        for(int i=0; i<K; i++){
            ans[i] = heap.poll();
        }
        return ans;
    }
}
*/

// sort
// collections sort
// running time: O(nlgn)
// space: O(n)
/*
import java.lang.Math;
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        int[][] ans = new int[K][2];
        
        ArrayList<int[]> list = new ArrayList<int[]>();
        for(int[] point : points){
            list.add(point);
        }
        // sort by collection.sort
        Collections.sort(list, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                return (Math.sqrt(a[0]*a[0] + a[1]*a[1]) - Math.sqrt(b[0]*b[0] + b[1]*b[1])) > 0 ? 1:-1;
            }
        });
        for(int i=0; i<K; i++){
            ans[i] = list.get(i);
        }
        return ans;
    }
}
*/
// optimization 
// sort only based on dist, consider the point as sinle points
// do not need to conver from list to array and let alone consuming Math function
/*
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        int[][] ans = new int[K][2];
        int[] dists = new int[points.length];
        for(int i =0; i<points.length; i++){
            dists[i] = dist(points[i]);
        }
        // sort
        Arrays.sort(dists);
        int K_dist = dists[K-1];
        int i =0;
        for(int[] temp : points){
            if(dist(temp) <= K_dist){
                ans[i] = temp;
                i++;
            }
        }
        return ans;
    }
    public int dist(int[] a){
        return a[0] * a[0] + a[1] * a[1];
    }
}
*/
























// accumulation pairs in java
/*
Pair<Integer, String> pair = new Pair<>(1, "One");
Integer key = pair.getKey();
String value = pair.getValue();
*/


// PriorityQueue overwrite


// compare function
0: if (x==y)
-1: if (x < y)
1: if (x > y)

// inverse to get max heap
// queue2=new PriorityQueue<E>(0,Collections.reverseOrder(e));


// import java.util.concurrent.ThreadLocalRandom;


// ThreadLocalRandom.current().nextInt(i, j)

// 随机数生成是一个非常常见的操作，而且 Java 也提供了 java.util.Random 类用于生成随机数，而且呢，这个类也是线程安全的，就是有一点不好，在多线程下，它的性能不佳


// Arrays.copyOfRange(this.points, 0, K);







































