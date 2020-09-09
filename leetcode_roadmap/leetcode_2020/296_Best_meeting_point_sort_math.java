// brute force：
// select one position as the temporary position, compute all sum of distances
// final get the optimal point
// running time: O(n^4)
// if using hashmap or list record the valid home points, the running time: O(n^3)

// method 1: brute force: BFS from any enumerating points
// running time: O(m^2n^2)
// space: O(mn): visited
/*
class Point {
        int row;
        int col;
        int distance;
        public Point(int row, int col, int distance) {
            this.row = row;
            this.col = col;
            this.distance = distance;
        }
}
class Solution {

    public int minTotalDistance(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int minDistance = Integer.MAX_VALUE;
        int temp_distance = Integer.MAX_VALUE;
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                temp_distance = bfs_search(grid, i, j);
                if(temp_distance < minDistance) minDistance = temp_distance;
            }
        }
        return minDistance;
    }

    // search from every one points by bfs
    public int bfs_search(int[][] grid, int row, int col){
        Queue<Point> queue = new LinkedList<Point>();
        int totalDistance = 0;
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        queue.offer(new Point(row, col, 0));
        int r = 0;
        int c = 0;
        int d = 0;
        while(!queue.isEmpty()){
            Point point = queue.poll();
            r = point.row;
            c = point.col;
            d = point.distance;
            // add adjacent points
            if( r<0 || c<0 || r>=m || c>=n || visited[r][c]) continue;
            if(grid[r][c] == 1) totalDistance = totalDistance + d;
            visited[r][c] = true;
            // add adjacent point
            queue.offer(new Point(r+1, c, d+1));
            queue.offer(new Point(r-1, c, d+1));
            queue.offer(new Point(r, c+1, d+1));
            queue.offer(new Point(r, c-1, d+1));
        }
        return totalDistance;
    }
}
*/


// method 2: Math perspective: row and col separable
// calculate Manhatten distance
// running time: O(m^2n^2)
// space:O(mn)
/*
class Point {
        int row;
        int col;
        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
}
class Solution {
    public int minTotalDistance(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int minDistance = Integer.MAX_VALUE;
        int temp_distance = Integer.MAX_VALUE;
        // using a list or hashmap or set storing the any point
        List<Point> lst = new ArrayList<>();
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                if(grid[i][j] == 1) lst.add(new Point(i, j));
            }
        }
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                temp_distance = calculateDistance(lst, i, j);
                if(temp_distance < minDistance) minDistance = temp_distance;
            }
        }
        return minDistance;
    }
    public int calculateDistance(List<Point> lst, int row, int col){
        int distance = 0;
        for(Point p : lst){
            distance += Math.abs(p.row - row) + Math.abs(p.col - col);
        }
        return distance;
    }
}
*/


// method 3: sort
// 注意到曼哈顿距离其实是两个独立变量的子问题的和, 只要解决一维的情况，我们就可以把二维的情况当做两个一维独立的子问题的和
// 数学统计统计中，中位数是最优的相遇点
// 只要相遇点左边和右边有相同数目的点，总距离都是最小的
// 实现的思想： 实现方法是十分直接的。首先，我们将行和列的坐标收集并排序，然后选择它们中间的元素
// running time: sort: O(mnlgmn)
// space: O(mn): store the related point coordinates
/*
class Solution {
    public int minTotalDistance(int[][] grid) {
        List<Integer> rows = new ArrayList<Integer>();
        List<Integer> cols = new ArrayList<Integer>();
        int m = grid.length;
        int n = grid[0].length;
        // store all entries with value of 1
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 1) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }
        // Collections.sort(rows);
        // here unnessary to sort the row coordinates
        // the order of add row data to list is defalut pre-order
         Collections.sort(cols);
        int row_median = rows.get(rows.size()/2);
        int col_median = cols.get(cols.size()/2);
        return minDistance1D(rows,row_median) + minDistance1D(cols, col_median);
    }
    // For manhatten distance, treating row and col equally
    public int minDistance1D(List<Integer> lst, int median){
        int distance = 0;
        // sort
        for(int point : lst){
            distance = distance + Math.abs(point - median);
        }
        return distance;
    }
}
*/




// method 4: collect coordinates accoording the order
// 可以通过快速选择算法，选择中位数
// 可以按照顺序手机行和列
// running time: O(mn)
/*
class Solution {
    public int minTotalDistance(int[][] grid) {
        List<Integer> rows = new ArrayList<Integer>();
        List<Integer> cols = new ArrayList<Integer>();
        int m = grid.length;
        int n = grid[0].length;
        // store all entries with value of 1
        // Collect coordinates sequentially
        // first, row
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] ==1 ) rows.add(i);
            }
        }
        for(int j=0; j<n; j++){
            for(int i=0; i<m; i++){
                if(grid[i][j] == 1) cols.add(j);
            }
        }

        int row_median = rows.get(rows.size()/2);
        int col_median = cols.get(cols.size()/2);
        return minDistance1D(rows,row_median) + minDistance1D(cols, col_median);
    }

    // For manhatten distance, treating row and col equally
    public int minDistance1D(List<Integer> lst, int median){
        int distance = 0;
        // sort
        for(int point : lst){
            distance = distance + Math.abs(point - median);
        }
        return distance;
    }
}
*/