// method 1
/*
class Solution {
    public int numberOfPatterns(int m, int n) {



        
    }
}
*/

// method 2
// backtracking method
// recursive
// runnint time: O(n!)
// space: O(n)
// 逻辑固定
// 解锁的顺序次数
// idea: 在构造递归搜索树的时候，算法会剪枝掉所有不满足规则的方案
// 搜索路径如下（类dfs）
// 1 在构造递归搜索树的时候，算法会剪枝掉所有不满足规则的方案
// 2 我们需要记录上一个访问的数字 last。算法需要检查是否满足以下任一条件
// 3 从 last 到 i 之间是国际象棋中马的移动，或者 last 和 i 是同一行或列的相邻元素。这种情况下，两个数字之和应当为奇数
// 4 连接 last 和 i 的中间元素 mid 已经被访问过，比方说 last 和 i 选择的是对角线上的两点，那么中间点 mid = 5应当已经选过
// 5 last 和 i 是对角线上的相邻元素
// 6 假设上面有一个条件满足，数字 i 就变成了合法手势的一部分，算法继续枚举下一个数字，知道整个手势图案被生成。最终计数总方案数

// 递归搜索条件为 合法的移动
class Solution {
    public boolean[] visited;
    public int numberOfPatterns(int m, int n) {
        int count = 0;
        visited = new boolean[9];
        for(int len=m; len<=n; len++){
            count += calPatterns(-1, len);
            for(int i=0; i<9; i++){
                visited[i] = false;
            }
        }
        return count;
    }

    public int calPatterns(int last, int len){
        if(len == 0) return 1;
        int sum = 0;
        // totally 9 possible next position
        for(int i=0; i<9;i++){
            if(isValid(i, last)){
                visited[i] = true;
                // recursive function patterns
                sum += calPatterns(i, len-1);
                // backtracking logic
                visited[i] = false;
            }
        }
        return sum;
    }

    public boolean isValid(int index, int last){
        // validation checking
        // 1 if it has been visited
        // 解锁手势中不能设置重复的点
        if(visited[index]) return false;
        // 2 if index == -1, always return true; 
        // the initial state
        if(last == -1) return true;
        // 3 knight move: 相邻同行同列的移动, 和为奇数
        if((index + last) % 2 == 1) return true;
        // 4 在对角线上两端元素, 需要check 中间元素（或者过中间元素的一行和一列）
        int mid = (index +  last ) /2;
        if(mid == 4) return visited[mid];
        // 5 对角线上的临近元素
        // adjacent cells on diagnoals: 不同列不同行
        if((index%3 != last%3) && (index/3 != last/3)) return true;

        // 6 all other cells which are not adjacent
        return visited[mid];
    }

}

// forward idea
// optimization
// 如果利用对称的性质可以优化算法，我们发现从数字 1, 3, 7, 9 出发的手势是相同的，同理从 2, 4, 6, 8 出发的也是。因此对这些相同的组我们只需要对结果乘以 4 即可