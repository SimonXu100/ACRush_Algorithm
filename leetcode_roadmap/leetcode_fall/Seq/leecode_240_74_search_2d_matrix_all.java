-----------------------------------------------------------------------------------------------------------------------------------
关联题目： 剑指offer 04 / leetcode 240 
// 最优算法
// method 4
// 剪枝
// 贪心区间搜索逻辑
// 经典方法记忆：
// 从左下角开始寻找
// 找到明确的divide and conquer的依据
// running time: O(m+n)
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int row = m - 1;
        int col = 0;
        
        while(row >=0 && col < matrix[0].length){
            if(matrix[row][col] > target){
                row--;
            }
            else if(matrix[row][col] < target){
                col++;
            }
            else return true;
        }
        return false;
    }   
}




// method 1
// brute force
// O(mn)
// 每一行使用bs
// O(mlgn)


// method 2
// 二分法搜索
// 加上一个判断分治，在行进行二叉搜索， 还是列进行二叉搜索
// 沿着对角线元素递归查找， 所有元素都经过遍历
// running time: O(lg(n*m))
// 多分类的思考方法
// class Solution {
//     public boolean searchMatrix(int[][] matrix, int target) {
//         if(matrix == null || matrix.length == 0 ){
//             return false;
//         }

//         // iterating over the diagnoal element
//         int shortDim = Math.min(matrix[0].length, matrix.length);
//         for(int i=0; i<shortDim; i++){
//             boolean rowFound = binarySearch(matrix, target, i, true);
//             boolean colFound = binarySearch(matrix, target, i, false);
//             if(rowFound || colFound ) return true;
//         }
//         return false;
//     }  

//     public boolean binarySearch(int[][] matrix, int target, int start, boolean vertical){
//         int l = start;
//         int r = vertical? matrix[0].length-1: matrix.length-1;

//         while(l <= r){
//             int mid = l + (r -l) / 2;
//             if(vertical){ // 按照行二叉搜索
//                 if(matrix[start][mid] < target){
//                     l = mid + 1;
//                 }
//                 else if(matrix[start][mid] > target){
//                     r = mid - 1;
//                 }
//                 else{
//                     return true;
//                 }
//             }else{
//                 if(matrix[mid][start] < target){
//                     l = mid + 1;
//                 }
//                 else if(matrix[mid][start] > target){
//                     r = mid -1;
//                 }
//                 else{
//                     return true;
//                 }
//             }
//         }
//         return false;
//     } 
// }



// method3
// 搜索空间缩减
// divide and conquer
// 已排序的二维矩阵划分为四个子矩阵，其中两个可能包含目标，其中两个肯定不包含
// 递归条件
// 删除 左上和右下从搜索空间中
// 左下 和 右上 作为递归分叉搜索空间
// 领会理解如何剪枝
// running time: (n*m)lg(n*m)
class Solution {
    private int[][] matrix;
    private int target;
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 ){
            return false;
        }
        this.matrix = matrix;
        this.target = target;
        return searchRec(0, matrix[0].length-1, 0, matrix.length-1);
    }  

    public boolean searchRec(int left, int right, int up, int down){
        // submatrix has no height or width
        // track 四个角的数据
        if(left > right  || up > down){
            return false;
        }
        else if(target < matrix[up][left] || target > matrix[down][right]){
            return false;
        }
        // 剪枝， 完全找不到值
        int mid = left + (right - left) / 2;
        // 定位剪枝位置
        int row = up;
        while(row <= down && matrix[row][mid] <= target){
            if(matrix[row][mid] == target){
                return true;
            }
            row++;
        }
        //剪枝
        return searchRec(mid+1, right, up, row-1) || searchRec(left, mid-1, row, down);
    }
}












































-----------------------------------------------------------------------------------------------------------------------------------
// leetcode 74
// 之字形顺序排序
// 二叉搜索
// running time: O(nmlgnm)
// 递归写法
// class Solution {
//     public boolean searchMatrix(int[][] matrix, int target) {
//         if(matrix.length == 0 || matrix[0].length == 0){
//             return false;
//         }
//         int row = matrix.length;
//         int col = matrix[0].length;
//         return binarySearch(matrix, 0, row * col - 1, target);
//     }

//     public boolean binarySearch(int[][] matrix, int l, int r, int target){
//         if(l > r) {
//             return false;
//         }
//         int mid = l + (r -l) / 2;
//         int row = mid / matrix[0].length;
//         int col = mid % matrix[0].length;
//         if(target == matrix[row][col]){
//             return true;
//         }
//         else if(target < matrix[row][col]){
//             return binarySearch(matrix, l, mid-1, target);
//         }
//         else{
//             return binarySearch(matrix, mid+1, r, target);
//         }
//     }
// }

// 递归写法

// 迭代写法
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length == 0 || matrix[0].length == 0){
            return false;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int l = 0;
        int r = row * col - 1;
        while(l <= r){
            int mid = l + (r -l) / 2;
            int tmp_row = mid / matrix[0].length;
            int tmp_col = mid % matrix[0].length;
            if(target == matrix[tmp_row][tmp_col]){
                return true;
            }
            else if(target < matrix[tmp_row][tmp_col]){
                r = mid - 1;
            }
            else{
                l = mid + 1;
            }
        }
        return false;
    }
}
