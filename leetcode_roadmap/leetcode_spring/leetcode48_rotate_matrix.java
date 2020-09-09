// method 1
// math, algebra, rotation
//(x,y)  ---> (y,-x)
//build coordinates
// 不借助新的矩阵
// 先转置，再反转
// running time: O(n^2)
class Solution {
    public void rotate(int[][] matrix) {
        // transpose the matrix
        // matrix[x][y] = matrix[y][x]
        // 关于主对称轴对称
        for(int i = 0; i< matrix.length; i++){
            for(int j= i; j < matrix.length; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // symmmetrical exchange
        for(int i=0; i< matrix.length; i++){
            for(int j = 0; j < matrix.length/2; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length-j-1];
                matrix[i][matrix.length-j-1] = temp;
            }
        }
    }
}