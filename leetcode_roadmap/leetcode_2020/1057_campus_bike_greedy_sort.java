// greedy, sort
// running time: O(nmlgnm)
class Solution {
    int[][] workers;
    int[][] bikes;
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        this.workers = workers;
        this.bikes = bikes;
        // all possible combination 
        ArrayList<int[]> combine_list = new ArrayList<int[]>();
        int num_workers = workers.length;
        int num_bikes = bikes.length;
        int[] res = new int[num_workers];
        Arrays.fill(res, -1);
        for(int i=0; i<num_workers; i++){
            for(int j=0; j<num_bikes; j++){
                combine_list.add(new int[]{i,j});
            }
        }
        // sort
        Collections.sort(combine_list, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                int dist_a = dist(a);
                int dist_b = dist(b);

                if(dist_a > dist_b) return 1;
                else if(dist_a < dist_b) return -1;
                else{
                    // when the distance is equal, then using worker index
                    if(a[0] > b[0]) return 1;
                    else if(a[0] < b[0]) return -1;
                    else{
                        //when the indexes of worker is equal, then using bike indexes
                        return (a[1] - b[1]) >= 0? 1:-1;
                    }
                }
            }
        });

        boolean[] bike_record = new boolean[num_bikes];
        for(int[] temp : combine_list){
            // did not assigned
            if(res[temp[0]] == -1 && bike_record[temp[1]] == false){
                res[temp[0]] = temp[1];
                bike_record[temp[1]] = true;
            } 
        }
        return res;
    }
    public int dist(int[] a){
        int[] worker = workers[a[0]];
        int[] bike = bikes[a[1]];

        return Math.abs(worker[0]-bike[0]) + Math.abs(worker[1]-bike[1]);
    }

}

// or greedy 
// using ordered mapping based on more concise mechanism
// then map:(distance, list<Pair(index_worker, index_bikes)>)
// greedy searching same as pruning