// method 0
// 模拟
// 初始位置是矩阵的左上角，初始方向是向右，当路径超出界限或者进入之前访问过的位置时，则顺时针旋转，进入下一个方向
// 结束条件
// running time: O(mn)
// 寻找理解迁移条件
// class Solution {
//     public List<Integer> spiralOrder(int[][] matrix) {
//         List<Integer> order = new ArrayList<Integer>();
//         if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return order;
//         int rows = matrix.length;
//         int cols = matrix[0].length;
//         int total = rows * cols;
//         int[][] directions= {{0,1}, {1,0}, {0,-1}, {-1,0}};
//         boolean [][] visited = new boolean[rows][cols];
//         int row = 0;
//         int col = 0;
//         // 遇到一次不成立现象变换一次角度
//         int directionIndex = 0;
//         // math computing
//         // 进入限制条件
//         for(int i=0; i<total; i++){
//             order.add(matrix[row][col]);
//             visited[row][col] = true;

//             // 按照原来的运动轨迹继续
//             int nextRow = row + directions[directionIndex][0];
//             int nextCol = col + directions[directionIndex][1];
//             if(nextRow < 0 || nextCol < 0 || nextRow >= rows || nextCol >= cols || visited[nextRow][nextCol]){
//                 directionIndex = (directionIndex + 1) % 4;
//             }

//             row += directions[directionIndex][0];
//             col += directions[directionIndex][1];
//         }

//         return order;
//     }
// }


// method 1
// 按层模拟
// 定义矩阵的第 k 层是到最近边界距离为 k 的所有顶点
// 从左上方开始以顺时针的顺序遍历所有元素。假设当前层的左上角位于 (\textit{top}, \textit{left})(top,left)，右下角位于 (\textit{bottom}, \textit{right})(bottom,right)，按照如下顺序遍历当前层的元素
// 在每个位置控制顺序

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> order = new ArrayList<Integer>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return order;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int left = 0, right = cols - 1, top = 0, bottom = rows - 1;
        while(left <= right && top <= bottom){
            // 上边界
            for(int col = left; col <= right; col++){
                order.add(matrix[top][col]);
            }
            // 右边界
            for(int row = top+1; row <= bottom; row++){
                order.add(matrix[row][right]);
            }
            // 判断是否存在下一个元素
            if(left < right && top < bottom){
                // 下边界
                for(int col = right-1; col >= left; col--){
                    order.add(matrix[bottom][col]);
                }
                // 左边界
                for(int row = bottom-1; row > top; row--){
                    order.add(matrix[row][left]);
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return order;
    }
}





