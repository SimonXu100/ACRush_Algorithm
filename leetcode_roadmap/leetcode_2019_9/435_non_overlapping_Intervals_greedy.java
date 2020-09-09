//暴力法
//解法一：递归尝试是否删除当前节点，比较分析结果
//只要当prev和current没有发生重叠的时候，才可以尝试当前的节点
//其他情况都选择选出当前节点： 原因： 选择删除prev节点不会对结果产生优化，甚至会删除更多节点
/*
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        //主体函数中，先将区间按照起始时间的先后顺序排序，然后调用递归函数
        Arrays.sort(intervals,(i1,i2)->Integer.compare(i1[0],i2[0]));

    }

    //递归函数：类似回溯函数，考虑多种情况
    //逻辑灵活应用各种编程思想和结构
    public int eraseOverlapIntervals(int prev, int curr, int[][] intervals){
        //结束条件，检查所有区间是否已处理完成
        //是的话，表明不需要删除操作，直接返回

        if(curr == intervals.length){
            return 0;
        }
        //taken: 如果保留当前区间，最少需要删除多少其他区间
        //nottaken: 如果删除当前区间，做少需要删除多少其他区间
        int taken = Integer.MAX_VALUE, nottaken;
        //当prev和curr没有发生重叠的时候，才可以选择保留当前区间的curr
        if(prev==-1||intervals[prev][1]<=intervals[curr][0]){
             taken = eraseOverlapIntervals(curr,curr+1,intervals);
        }

        //其他情况可以考虑删除curr区间，看看删除之后会不会产生更好的结果
        //其他情况：重叠删除当前区间
        //不重叠,考虑尝试删除时候会产生更好的结果

        nottaken = eraseOverlapIntervals(prev,curr+1,intervals)+1;

        return Math.min(taken,nottaken);
    }
}
*/
//解法二：贪婪方法
//局部优化：每次遇到两个重叠的区间删除结束较晚的，保留结束较早的（比较结束时间点）
//局部优化实现全局优化，最优化解法之一
/*
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
         if(intervals.length == 0 ){
             return 0;
         }
         //所有区间按照起始时间排序
         Arrays.sort(intervals,(i1,i2)->Integer.compare(i1[0],i2[0]));

         //定义end变量记录当前最小结束点
         //定义count变量记录到目前为止删除了多少区间
         //初始化
         int end = intervals[0][1];
         int count = 0;

         for(int i =1;i<intervals.length;i++){
         	//当前区间与前一个区间有重叠，end变量记录两个区间的结束的最小值（相当于删除结束点最晚的点）
            if(intervals[i][0]<end){
                 end = Math.min(intervals[i][1],end);
                 count++;

            }
            //无重叠，更新end为当前结束时间
            else{
            	end = intervals[i][1];
            }
         }
         return count;

    }
}
*/

//贪婪算法2：从结束位置开始排序
//注意理解对称性
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
       if(intervals.length==0){
           return 0;
       }
       //将所有区间按照结束时间排序
       Arrays.sort(intervals,(i1,i2)->Integer.compare(i1[1],i2[1]));

       //end 变量记录当前结束时间
       //count变量记录有多少个没有结束的区间
       int end  = intervals[0][1];
       int count = 1;

       //从第二晚结束的区间开始遍历
       for(int i =1; i< intervals.length;i++){
           //如果当前区间与前一个区间没有重叠，则计数加1，同时更新结束时间
           if(intervals[i][0]>=end){
              count++;
              end = intervals[i][1];
           }
       }
       //总区间个数减去没有重叠的区间个数，得到最少要删除的个数
       return intervals.length-count;
    }
}
































