//1 Bubble sort
// T:O(n^2)
// S:O(1)
/*
void bubbleSort(int nums){
  boolean hasChange = true;
  for(int i =0;i<nums.length-1&&hasChange;i++){
  	  hasChange = false;
      for(int j = 0; j < length-1-i;j++){
            if(nums[j]>nums[j+1]){
                swap(nums,j,j+1);
                hasChange = true;
            }
      }
  }
  return nums;
}
*/

// 2 insert sort
// T:O(n^2)
// S:O(1)
/*
void insertSort(int nums){
  for(int i = 1, j,current;i<nums.length;i++){
     current = nums[i];
     for( j = i-1;j>=0&&nums[j]>current;j--){
     	nums[j+1] = nums[j];
     }
     nums[j+1] = current;
  }
  return nums;
}
*/
// 3 mergeSort
// T:O(nlogn)
// S:O(n)
/*
void mergeSort(int[] Array, int lo, int hi){
    if(lo>=hi){
    	// 需要的是操作，与返回值没有关系
        return;
    }
    int mid = lo + (hi - lo)/2;
    mergeSort(A,lo,mid);
    mergeSort(A,mid+1,hi);
    merge(A,lo,mid,hi);
}

void merge(int[] nums, int lo, int mid, int hi){
    int[] copy = nums.clone();
    int k = lo, i = lo, j = mid + 1;
    while(k<=hi){
      if(i>mid){
         nums[k++] = copy[j++];
      }
      else if(j>hi){
         nums[k++] = copy[i++];
      }
      else if(copy[i]<copy[j]){
         nums[k++] = copy[i++];
      }
      else{
         nums[k++] = copy[j++];
      }
    }
}
*/

// 快排
// 分析：
// T:O(nlgn)
// S:O(logn)
void quickSort(int[] nums, int lo, int hi){
    if(lo>=hi){
       return;
    }
    int p = partition(nums,lo,hi);
    quickSort(nums,lo,p-1);
    quickSort(nums,p+1,hi);
}
int partition(int[] nums, int lo, int hi){
    swap(nums,randRange(lo,hi),hi);
    int i, j;
    //双指针
    //把基准点放到了数组末尾
    for(i = lo,j = lo;j<hi;j++){
       if(nums[j]<nums[hi]){
           swap(nums,i++,j);
       }
    }
    //把基准点放在中间
    swap(nums,i,j);
    return i; 
}



215:
class Solution {
    int[] nums;
    public int findKthLargest(int[] nums, int k) {
        this.nums = nums;
        return quickSort(this.nums,0,this.nums.length-1,this.nums.length-k);
    }
    public int quickSort(int[] nums,int lo,int hi,int kth_min){
       if(lo>=hi){
           return nums[lo];
       }
       int current = partition(nums,lo,hi);
       if(current==kth_min){
           return nums[current];
       }
       else if(current<kth_min){
            return quickSort(nums,current+1,hi,kth_min);
       }
       else{
          return quickSort(nums,lo,current-1,kth_min);
       }
    }
    public int partition(int[] nums,int lo,int hi){
        //换到末尾
        //swap(nums,randRange(lo,hi),hi);
        Random random_num = new Random();
        int rand = lo + random_num.nextInt(hi-lo);
        swap(nums,rand,hi);
        int i,j;
        for(i=lo,j=lo;j<hi;j++){
           if(nums[i]<=nums[hi]){
               swap(nums,i++,j);
           }
        }
        swap(nums,i,j);
        return i;
    }
    public void swap(int[] nums, int i, int j ){
        int temp = 0;
        temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}


//拓扑排序
//BFS+入度实现
//具体实现省略
//临界矩阵
/*
void topological_sort(){
   Queue<Integer> q = new LinkedList<Integer>();
   for(int v = 0; v<V; v++){
      if(indegree[v]==0){
          q.add(v);
      }
   }

   while(!q.isEmpty()){
      int v = q.poll();
      print(v);
      for(int u = 0; u<adj[v].length;u++){
         if(--indegree[u] == 0){
             q.add(u);
         }
      }
   }

}
*/
// 邻接矩阵
/*
class Solution {
    boolean isPossible = true;
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //是否成立
        boolean isPossible = true;
        //邻接链表表示图
        //first para: original nodes, second para: pointing nodes
        Map<Integer,List<Integer>> adjList = new HashMap<Integer,List<Integer>>();
        //indegree array
        int[] indegree = new int[numCourses];
        //ans array
        int[] topologicalOrder = new int[numCourses];

        //create adjacency list of this graph
        // record indegree of each vertex
        for(int i = 0;i<prerequisites.length;i++){
           //left-nodes
           int dest = prerequisites[i][0];
           // right-nodes
           int src = prerequisites[i][1];
           List<Integer> lst = adjList.getOrDefault(src,new ArrayList<Integer>());
           lst.add(dest);
           adjList.put(src,lst);
           //
           indegree[dest]++;

        }
        
        //BFS+indegree for topological srt
        // add all vertices with 0 indegree to the queue
        Queue<Integer> q = new LinkedList<Integer>();
        
        for(int  i = 0; i< numCourses;i++){
           if(indegree[i]==0){q.add(i);}
        }
        int i = 0;
        while(!q.isEmpty()){

           //int v = q.poll();
           int v = q.remove();
           topologicalOrder[i++] = v;

           // reduce the indegree of each neighbour 
           //判断是否含有相关链表----存在点出度为0
           if(adjList.containsKey(v)){
           for(Integer neighbour:adjList.get(v)){
               if(--indegree[neighbour] == 0){
                  q.add(neighbour);
               }

           }
          } 
        }

        // check if the topological sort exits
        if(i==numCourses){
            return topologicalOrder;
        }
        return new int[0];

    }
}
*/

// DFS+stack
// 先把后点进栈，再进前节点，再反序出栈
















