class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        Arrays.sort(candidates);
        backtracking(candidates,target,0,new ArrayList<Integer>(),results);
        return results;
    }
    //操作在过程中完成了处理 
    public void backtracking(int[] candidates, int target, int start, ArrayList<Integer> solution, List<List<Integer>> results){
        if(target<0){
            return;
        }
        if(target==0){
            //栈如果全部递归完成，solution会消失；
            results.add(new ArrayList(solution));
            //results.add(solution);
            return;
        }
        //所有可能出现的情况
        for(int i = start;i<candidates.length;i++){
            if (target < candidates[start]) break;
            solution.add(candidates[i]);
            //递归返回结果
            backtracking(candidates,target-candidates[i],i,solution,results);
            //返回上一步，把当前加入结果弹出
            solution.remove(solution.size()-1);
        }
        
    }
}