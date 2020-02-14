
// dfs + hashtable

// note: how to control
// keep the shape same
// by subtraction by first coorniates of a island 
// then confirm uniqueness by check the set
// Hashtable store every construction of each islands

// notes: set can decide duplicate for list, but cannot check List<Integer[]>
// 维护一个简单地值
// running time: O(r * c)
// spaace: O(r * c)
/*
class Solution {
    // adjacent array
    int[][] adjacent;
    int[][] grid;
    Set<List<Integer>> islands;
    boolean[][] visited;
    int row;
    int col;
    public int numDistinctIslands(int[][] grid) {
        if(grid == null || grid.length == 0) return 0;
        // build adjacent array
        adjacent = new int[4][2];
        adjacent[0][0] = -1;
        adjacent[0][1] = 0;
        adjacent[1][0] = 0;
        adjacent[1][1] = -1;
        adjacent[2][0] = 1;
        adjacent[2][1] = 0;
        adjacent[3][0] = 0;
        adjacent[3][1] = 1;
        int numIslands = 0;
        // hashtable: store all possible islands
        islands = new HashSet<>();
        this.grid = grid;
        visited = new boolean[grid.length][grid[0].length];
        row = grid.length;
        col = grid[0].length;
        // build dfs, find all possible islands
        // tagging the islands
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                if(!visited[i][j] && grid[i][j] == 1){
                    List<Integer> temp_list = new ArrayList<>();
                    dfs_helper(temp_list,i,j,i,j);
                    islands.add(temp_list);
                    numIslands++;
                }
            }
        }
        return islands.size();    
    }
    public void dfs_helper(List<Integer> temp_list,int x, int y, int original_x, int orginial_y){
        visited[x][y] = true;
        temp_list.add(((x- original_x)*col+ (y-orginial_y)));
        // check the adjacent value
        for(int i=0; i<4; i++){
            int temp_x = adjacent[i][0]+x;
            int temp_y = adjacent[i][1]+y;
            if(temp_x<0 || temp_x>=grid.length) continue;
            if(temp_y<0 || temp_y>=grid[0].length) continue;
            if(grid[temp_x][temp_y] == 1 && visited[temp_x][temp_y] == false) {
                dfs_helper(temp_list, temp_x, temp_y, original_x, orginial_y);  
            }
        }
    }
}
*/


// method 2:
// by checking searching path, confirm the uniqueness of the tree
// representation of path:
// 1 string pat, symbolic representation

class Solution {
    // adjacent array
    int[][] adjacent;
    int[][] grid;
    Set<String> islands;
    boolean[][] visited;
    public int numDistinctIslands(int[][] grid) {
        if(grid == null || grid.length == 0) return 0;
        // build adjacent array
        adjacent = new int[][]{{1,0}, {0,1}, {-1,0}, {0,-1} };
        int numIslands = 0;
        // hashtable: store all possible islands
        islands = new HashSet<>();
        this.grid = grid;
        visited = new boolean[grid.length][grid[0].length];

        // build dfs, find all possible islands
        // tagging the islands
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                if(!visited[i][j] && grid[i][j] == 1){
                    StringBuilder sb = new StringBuilder();
                    dfs_helper(sb,i,j);
                    islands.add(sb.toString());
                    numIslands++;
                }
            }
        }
        return islands.size();    
    }
    public void dfs_helper(StringBuilder sb,int x, int y){
        if(x<0 || x>=grid.length) return;
        if(y<0 || y>=grid[0].length) return;
        if(visited[x][y] == true || grid[x][y] == 0) return;
        visited[x][y] = true;
        
        for(int i=0; i<4; i++){
            int temp_x = adjacent[i][0]+x;
            int temp_y = adjacent[i][1]+y;
            if(i == 0) sb.append("d");
            if(i == 1) sb.append("r");
            if(i == 2) sb.append("u");
            if(i == 3) sb.append("l");
            dfs_helper(sb, temp_x, temp_y);
        }
    }
}



/*
class Solution {
    public int numDistinctIslands(int[][] grid) {
        HashSet<String> set = new HashSet<>();
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[0].length; j++)
            {
                if(grid[i][j] == 0)
                    continue;
                StringBuilder sb = new StringBuilder();
                dfs(grid, i, j, sb);
                System.out.println(sb.toString());
                set.add(sb.toString());
            }
        }
        return set.size();
    }

    public void dfs(int[][] grid, int i, int j, StringBuilder sb) {
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != 1)
            return;
        
        grid[i][j] = 0;
        dfs(grid, i + 1, j, sb.append("d"));
        dfs(grid, i, j + 1, sb.append("r"));
        dfs(grid, i - 1, j, sb.append("u"));
        dfs(grid, i, j - 1, sb.append("l"));
    }
}
*/








// summary: find island may adopt dfs, bfs, union_find
// the key is: remove duplicate
// the method: set or sort






// accumulation function
// StringBuilder()
// Constructs a string builder with no characters in it and an initial capacity of 16 characters.
// StringBuilder(CharSequence seq)
// Constructs a string builder that contains the same characters as the specified CharSequence.
// StringBuilder(int capacity)
// Constructs a string builder with no characters in it and an initial capacity specified by the capacity argument.
// StringBuilder(String str)
// Constructs a string builder initialized to the contents of the specified string.

// functions
// StringBuilder sb = new StringBuilder()
// sb.append()
// sb.deleteCharAt()
// sb.toString()




// 排重思路
// 1 set: island.push_back((r-sr)*col+(c-sc));

// 2 check path: String or recursive

