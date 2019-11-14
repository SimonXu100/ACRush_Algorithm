// method 1 brute force
// based on height and width 
// 从左至右扫描输入数组
// 将每跟柱子高度作为当前矩形的高度
// 矩形的宽度就从当前柱子出发一直延伸到左边和右边
// 一旦遇到低于当前高度的柱子就停止
// 计算面积，最后统计所有面积的最大值
// running time: O(n^2)
/*
class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length, max =0;
        for(int i=0;i<n;i++){
            int l = i;
            int r = i;
            while(l>=0 && heights[l]>=heights[i]){
                l--;
            }
            while(r<n && heights[r]>=heights[i]){
                r++;
            }
            max = Math.max(max, heights[i]*(r-l-1) );
        }
        return max;    
    }
}
*/

// method 2: record the first lower element in stack
// 时间复杂度为O(n), 从左到右扫描数组，每个元素被压入堆栈一次，弹出一次
// 空间复杂度为O(n), 因为使用一个堆栈来保存各元素的下标
// 最坏的情况为各个高度按照从矮到高的顺序排列，需要将它们都压入堆栈
class Solution {
    public int largestRectangleArea(int[] heights) {
        int n=heights.length, max=0;
        //定义一个stack，用来辅助计算
        Stack<Integer> stack = new Stack<>();
        //相当于在末尾扩展了一个高度为0的矩形
        //一旦发现当前高度比堆顶记录元素要低，既可以对堆顶元素记录的高度为最终矩形高度计算面积了
        //此处巧妙的处理了当i等于n的情况
        //同时判断当前面积是否为最大值
        for(int i=0; i<=n; i++){
            while(!stack.isEmpty() && ((i==n) || heights[i]<heights[stack.peek()]) ){
                int height = heights[stack.pop()];
                int width = stack.isEmpty()?i:i-1-stack.peek();
                max = Math.max(max, width*height);
            }
            //如果当前高度比堆顶元素的高度高，则压入堆栈，又继续扩展的可能
            stack.push(i);
        }
        return max;
    }
}





