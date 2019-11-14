class Solution {
    public int trapRainWater(int[][] heightMap) {
        //sanity check
        if(heightMap==null || heightMap.length==0 || heightMap[0].length==0){
            return 0;   
        }
        int m = heightMap.length;
        int n = heightMap[0].length;
        
        //定义一个优先队列或者最小堆
        //按照每个方块接了雨水后的高度排序
        PriorityQueue<Cell> queue = new PriorityQueue(
            new Comparator<Cell>(){
                public int compare(Cell a, Cell b){
                    return a.height - b.height;
                }
            }
        );
        boolean[][] visited = new boolean[m][n];
        //Initially, add all the results which are on borders to the queue
        for(int i=0; i<m; i++){
            visited[i][0] = true;
            visited[i][n-1] = true;
            queue.offer(new Cell(i,0,heightMap[i][0]));
            queue.offer(new Cell(i,n-1,heightMap[i][n-1]));
        }
        
        for(int j=0; j<n; j++){
            visited[0][j] = true;
            visited[m-1][j] = true;
            queue.offer(new Cell(0,j,heightMap[0][j]));
            queue.offer(new Cell(m-1,j,heightMap[m-1][j]));
        }
        
        // from the bordes, pick the shortest cell visited and check the neighbours
        // if the neighbour is shorter, collect the water it can trap and update
        // its neighbour as its height plus the water trapped
        // add all its neighbours to the queue
        int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
        int total = 0;
        
        // BFS
        while(!queue.isEmpty()){
            // 每次从优先队列中取出最短的方块
            Cell cell = queue.poll();
            
            for(int[] dir: dirs){
                int row = cell.row + dir[0];
                int col = cell.col + dir[1];
                
                //该方向上的相邻方块能接多少雨水；取决于它是否它是否低于当前方块
                if(row>=0 && row<m && col>=0 && col<n && !visited[row][col]){
                    visited[row][col] = true;
                    total += Math.max(0,cell.height-heightMap[row][col]);
                    queue.offer(new Cell(row,col,Math.max(cell.height,heightMap[row][col])));
                }      
            }
        }
        return total;
    }
}

//为了配合优先队列的操作，我们定义了一个cell类
//用来保存每个方块的坐标，以及接了雨水后的高度
class Cell{
    int row;
    int col;
    int height;
    
    public Cell(int row, int col, int height){
        this.row = row;
        this.col = col;
        this.height = height;
    }
}
