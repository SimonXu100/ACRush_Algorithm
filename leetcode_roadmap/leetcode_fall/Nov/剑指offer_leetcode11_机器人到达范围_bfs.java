// BFS
// 到达的范围
// running time: O(MN)
class Solution {
    private boolean[][] visited;
    private int[][] directions = {{-1,0}, {1,0}, {0,-1}, {0,1}};
    public int movingCount(int m, int n, int k) {
        if(m == 0 && n ==0 ) return 0;
        if(k == 0) return 1;
        visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<int[]>();
        int count = 0;
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            count += 1;
            int x = 0;
            int y = 0;
            for(int[] direction : directions){
                x = cur[0] + direction[0];
                y = cur[1] + direction[1];
                if(x < 0 || x > m-1 || y <0 || y > n-1 || digit_num(x) + digit_num(y) > k || visited[x][y]) continue;
                else{
                    queue.offer(new int[]{x,y});
                    visited[x][y] = true;  
                }
            }
        }
        return count;
    }
    // 数位之和
    public int digit_num(int number){
        int sum = 0;
        while(number >0){
            sum +=  number % 10;
            number = number / 10;
        }
        return sum;
    }

}