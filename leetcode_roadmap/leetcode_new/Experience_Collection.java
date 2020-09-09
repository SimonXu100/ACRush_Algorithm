1 bit manipulation
异或： ^= 
// 任何数和 0 做异或运算，结果仍然是原来的数
// 任何数和其自身做异或运算, 结果是 0
// 异或运算满足交换律和结合律


左移： 左移( << )
eg: 5<<2
右移：  右移（ >>）
eg: 5>>2


无符号右移( >>> )


位与( & )

位或( | )

位异或( ^ )

位非( ~ ), 位非是一元操作符




---------------------------------------
// accumulation function
1 list to array
public Object[] toArray()
           or
public <T> T[] toArray(T[] a)








---------------------------------
2 StringBuilder






------------------------------------------------------------------
3 List
removeLast() method is used to remove the last element from the LinkedList





----------------------------------------------------------------------
4 HashSet
boolean	add(E e)
Adds the specified element to this set if it is not already present.
void	clear()
Removes all of the elements from this set.
Object	clone()
Returns a shallow copy of this HashSet instance: the elements themselves are not cloned.
boolean	contains(Object o)
Returns true if this set contains the specified element.
boolean	isEmpty()
Returns true if this set contains no elements.
Iterator<E>	iterator()
Returns an iterator over the elements in this set.
boolean	remove(Object o)
Removes the specified element from this set if it is present.
int	size()
Returns the number of elements in this set (its cardinality).



// transversal
for(int key : set){

}


--------------------------------------
5 C++
// 去重
nums.erase(unique(nums.begin(),nums.end()),nums.end());











--------------------------------------------------------------------------------------------------------------------------
经典问题汇总：
1 子序列问题
reference materials:https://leetcode-cn.com/problems/longest-consecutive-sequence/solution/tao-lu-jie-jue-zui-chang-zi-xu-lie-deng-yi-lei-wen/
LCIS:Longest Continuous Increasing Subsequence 最长连续递增序列

LIS:Longest Increasing Subsequence 最长上升子序列

LCS:Longest Consecutive Sequence 最长连续序列

LCS:longest common subsequence 最长公共子序列



2 bit manipulation 
位掩码实现： 
if((n & mask) != 0){
      count++;
}
   mask <<= 1;

// method 2
// trick: bit manipulation
// optimization
// 不再检查数字的每一个位，而是不断把数字最后一个 1 反转
// idea: 关键的想法是对于任意数字 n ，将 n 和 n -1 做与运算，会把最后一个 1 的位变成 0
// 将 n 和 n - 1 与运算总是能把 nn 中最低位的 1 变成 0 ，并保持其他位不变
while(n!=0){
    n = n &(n-1);
    count++;
}


3 BFS
int[][] direction = new int[][] {{-1,0}, {1,0},  {0,-1}, {0,1}};

for(int[] dir : direction){
    int r = dir[0] + currentPoint[0];
    int c = dir[1] + currentPoint[1];
    
    // validation check
    // visited: direction matrix 
    if(r<0 || c<0 || r>=m || c>=n || rooms[r][c] != Integer.MAX_VALUE) continue;
    rooms[r][c] = rooms[currentPoint[0]][currentPoint[1]] + 1;
    queue.add(new int[]{r, c});
}










































































