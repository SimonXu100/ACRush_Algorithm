// Hashtable used for count
// method 1:
// notes: may contain multiple island
// we could assume this only one island
// runing time: O(n^2)
// various link way
// Math way
// idea: 我的基本思路是去看每个岛能对总周长贡献几条边。例如一个岛四周都没有岛，自然是贡献4条边。如果一个岛周围有一个岛，那么它就只能贡献3条边，以此类推。
// 因此，其实就是遍历整个矩阵，每遍历到一个岛，则去看这个岛的上下左右有没有岛。可以发现如果一个岛附近每有一个岛，则这个岛贡献的周长会-1。最后返回贡献和即可。
/*
class Solution {
    public int islandPerimeter(int[][] grid) {
        if(grid== null) return 0;
        int m = grid.length;
        int n = grid[0].length;
        int count  = 0;
        int res = 0;
        int temp =0;
        int x =0;
        int y =0;
        
        int[][] direction = {{-1,0},{1,0},{0,-1},{0,1}};
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 1){
                    count++;
                    temp = 0;
                    // check the elements aroud the current element is 1 or not
                    for(int k=0; k<4; k++){
                        x = direction[k][0] + i;
                        y = direction[k][1] + j;
                        if(x<0 || x >=m) continue;
                        if(y<0 || y >=n) continue;
                        if(grid[x][y] == 1) temp++;
                    }
                    res += 4 - temp;
                }  
            }
        }  
        return res;
    }
}
*/



// method 2: optimization only compute 右下
// idea: count the overlapping edges and total number, then computer the Perimeter
// only count one side of information

class Solution {
    public int islandPerimeter(int[][] grid) {
        if(grid== null) return 0;
        int m = grid.length;
        int n = grid[0].length;
        int count  = 0;
        int res = 0;
        // overlapping edges
        int overlap =0;

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 1){
                    count++;
                    // check the overlapping for right size and down size
                    if(i+1<m && grid[i+1][j] == 1 ) overlap++;
                    if(j+1<n && grid[i][j+1] == 1) overlap++;   
                }  
            }
        }  
        res = count * 4 - overlap*2;
        return res;
        
        
    }
}























