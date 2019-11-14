//排序+比较
//区间变形1
//时间复杂度分析：O（nlogn+n）---O(nlogn)
//空间复杂度分析：O（n）:存储结果
class Solution {
    public int[][] merge(int[][] intervals) {
        //将所有区间按照开始的时间顺序排序
        //从小到大排序
        Arrays.sort(intervals,(i1,i2)->Integer.compare(i1[0],i2[0]));

        //比较前后两个变量，定义previous和current变量
        //虚头比较简洁

        int[] previous = null;

        //result变量保存最终结果，要求数组长度可变，定义List
        List<int[]> result = new ArrayList<>();

        //从头遍历给定所有区间
        //
        for(int[] current : intervals){
           //如果不重合，加入当前组合到结果中，并设置previous = current
           //注意压界算作交叉可以融合
           if(previous == null || previous[1]<current[0]){
           	   previous = current;
               result.add(current);
           }//发生重合，更新previous区间的结束时间
            //结束时间更新为previous和current区间最晚结束时间
           else{
               previous[1] = Math.max(previous[1],current[1]);
           }
        }

        return result.toArray(new int[result.size()][]);

    }
}