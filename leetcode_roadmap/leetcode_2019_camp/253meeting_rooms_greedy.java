//贪心算法
//堆
import java.util.Arrays;
import java.util.Comparator;
class Solution {
    public int minMeetingRooms(int[][] intervals) {
       if(intervals==null||intervals.length==0) return 0; 
        
       //将输入的一系列会议按照会议的起始时间排序
       //自小到大
       
       Arrays.sort(intervals, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                return a[0]-b[0];
            }
        });
        
        //int arrays
        //用一个最小堆维护目前开辟的所有会议室，最小堆里的会议室按照会议的结束时间排序
        //intervals.length:表示第一维度，行数
        PriorityQueue<int[]>heap = new PriorityQueue<int[]>(intervals.length,new Comparator<int[]>(){
           public int compare(int[] a, int[] b){
               return a[1]-b[1];
           }
        });
        
        //初始化，开辟全新会议室
        heap.offer(intervals[0]);
        //从第二个会议开始，每次从最小堆中（结束时间），去除最先结束的会议室，判断是否可以续用会议室
        for(int i = 1;i<intervals.length;i++){
            int[] interval = heap.poll();
            //若当前最先结束会议结束在最新会议开始之前，则可续用会议室，并放入该会议时间
            if(interval[1]<= intervals[i][0]){
                interval = intervals[i];
            }
            else{
                heap.offer(intervals[i]);
            }
            heap.offer(interval);
        }
      return heap.size();
    }
}



