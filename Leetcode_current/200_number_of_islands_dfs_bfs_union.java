// Ackerman’s Function
// Amortized time per operation is a(V )
// think of it as lg* V , which is slightly bigger

// method 1 DFS
// transversal
// O(V+E): O(n^2)
// use flag properly
// 先判断或者后判断都可

class Solution {
    boolean[][] visited;
    int[][] adjacent;
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0) return 0;
        
        int num_component = 0;
        int row = grid.length;
        int column = grid[0].length;
        // adjacent access array
        adjacent = new int[4][2];
        // up, left, down, right
        adjacent[0][0] = -1;
        adjacent[0][1] = 0;
        adjacent[1][0] = 0;
        adjacent[1][1] = -1;
        adjacent[2][0] = 1;
        adjacent[2][1] = 0;
        adjacent[3][0] = 0;
        adjacent[3][1] = 1;
        
        visited = new boolean[row][column];
        
        for(int i=0; i<row; i++){
            for(int j=0; j<column; j++){
                if(grid[i][j] == '1' && visited[i][j] == false){
                    dfs_helper(grid, i, j);
                    num_component++;
                } 
            }
        }
        return num_component;
    }
    public void dfs_helper(char[][] grid, int x, int y){
        visited[x][y] = true;
        for(int i=0;i<4;i++){
            int temp_x = adjacent[i][0]+x;
            int temp_y = adjacent[i][1]+y;
            if(temp_x<0 || temp_x>=grid.length) continue;
            if(temp_y<0 || temp_y>=grid[0].length) continue;
            if(grid[temp_x][temp_y] == '1' && visited[temp_x][temp_y] == false) {
                dfs_helper(grid, temp_x, temp_y);   
            }
        } 
    }
}



// method 2 BFS
// same method
// change flag, no need to use recursion
// same thing


// method 3 union find
// 遍历二维网格，将竖直或水平相邻的陆地联结。最终，返回并查集数据结构中相连部分的数量
// 注意当使用路径压缩和排名结合并实现并查集时，并操作只需要消耗常数时间
// running time: O(m*n) 
// Union by Rank - make shallow tree a child of root of big tree
// Path Compression - every time you touch a node, make it a child of root
// Union by Rank gives log V time per operation
// Union by Rank and path compression give better performance
// disjoint set
/*
class Solution {
    // union usuage
    // flag: change to 0: visited
    int[][] adjacent;
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0) return 0;
        int num_component = 0;
        int row = grid.length;
        int column = grid[0].length;
        // adjacent access array
        adjacent = new int[4][2];
        // up, left, down, right
        adjacent[0][0] = -1;
        adjacent[0][1] = 0;
        adjacent[1][0] = 0;
        adjacent[1][1] = -1;
        adjacent[2][0] = 1;
        adjacent[2][1] = 0;
        adjacent[3][0] = 0;
        adjacent[3][1] = 1;
        int temp_x = 0;
        int temp_y = 0;
        // Initialization
        UnionFind uf = new UnionFind(grid);
        for(int i=0; i<row; i++){
            for(int j=0; j<column; j++){
                if(grid[i][j] == '1'){
                    // represent visited
                    grid[i][j] = '0';
                    // access adjacent nodes
                    for(int k=0; k<4; k++){
                        temp_x = adjacent[k][0] + i;
                        temp_y = adjacent[k][1] + j;
                        if(temp_x<0 || temp_x>=row) continue;
                        if(temp_y<0 || temp_y>=column) continue;
                        if(grid[temp_x][temp_y] == '1'){
                            uf.union(i*column + j, temp_x*column + temp_y);
                        }  
                    }
                }   
            }
        }
        return uf.getCount();   
    }
    
    // class UnionFind
    class UnionFind {
        int count; // # of connected components
        int[] parent;
        int[] rank;
        
        
        // find: path compressionm
        public int find(int x){
            if(parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];  
        }
        
        // union with rank
        public void union(int x, int y){
            link(find(x), find(y));   
        }
        // make shallow tree a child of root of a big tree
        public void link(int x, int y){
            if(x != y){
                if(rank[x] > rank[y]) parent[y] = x;
                else{
                    parent[x] = y;
                    if(rank[x] == rank[y]){
                        rank[y] = rank[y] + 1;
                    }
                }
                // the number of component decrease one
                count--;
            }
        }
        
        public int getCount(){
            return count;
        }
        
        // special case for the problem
        public UnionFind(char[][] grid){
            count = 0;
            int row = grid.length;
            int column = grid[0].length;
            parent = new int[row * column];
            rank = new int[row * column];
            
            //make set
            // parent[x] = x
            // rank[x] = 0
            for(int i=0; i<row; i++){
                for(int j=0; j<column; j++){
                    if(grid[i][j] == '1'){
                        parent[i*column + j] = i*column + j;
                        // every single set is counted as one component
                        count++;
                    }
                    rank[i*column + j] = 0; 
                }
            }
        }
    }  
}

*/



















