// method 0
// brute force 
// 从每个空的房间开始做宽度优先搜索，找到最近的门即可
// BFS
// running time: O(m^2n^2)
// space: O(mn)

// method 1 BFS
// 连通性问题
// 追踪distance
// 最近距离
// 从门开始搜索
// running time: O(mn)
// space: O(mn)
class Solution {
    public void wallsAndGates(int[][] rooms) {
        // direction 
        // up, down, left, right
        int[][] direction = new int[][] {{-1,0}, {1,0},  {0,-1}, {0,1}};

        
        int m = rooms.length;
        if(m == 0) return;
        int n = rooms[0].length;
        // BFS
        Queue<int[]> queue = new LinkedList<>();
        // initilize 
        // 同步进行
        // 坐标标记位置
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(rooms[i][j] == 0){
                    queue.offer(new int[] {i, j});
                }
            }
        }
        
        while(!queue.isEmpty()){
            int[] currentPoint = queue.poll();
            for(int[] dir : direction){
                int r = dir[0] + currentPoint[0];
                int c = dir[1] + currentPoint[1];
                
                // validation check
                // visited: direction matrix 
                if(r<0 || c<0 || r>=m || c>=n || rooms[r][c] != Integer.MAX_VALUE) continue;
                rooms[r][c] = rooms[currentPoint[0]][currentPoint[1]] + 1;
                queue.add(new int[]{r, c});
            }
        }
    }

    
}

















