// 迭代
//时间复杂度分析：

class Solution {
    public List<String> findStrobogrammatic(int n) {
    	 return helper(n,n);
    }

    public List<String> helper(int n, int m){
         //sanity check 
    	 //n: 问题规模
    	 //m: 判断是否到了扩充部分
    	 if(n<0||m<0||n>m){
    	 	throw new IllegalArgumentException("invalid input");
    	 }

    	 //terminal condition
         // step:2
    	 if(n==0) return new ArrayList<String>(Arrays.asList(""));
    	 if(n==1) return new ArrayList<String>(Arrays.asList("0","1","8"));

    	 //scale down
    	 List<String> list = helper(n-2,m);

    	 //combine
    	 List<String> res = new ArrayList<String>();
         // 为每一个字符串加上边界字符
    	 for(int i = 0; i<list.size();i++){
             String s = list.get(i);
             if(n!=m){
                res.add("0"+s+"0");
             }
             res.add("1"+s+"1");
             res.add("6"+s+"9");
             res.add("9"+s+"6");
             res.add("8"+s+"8");
    	 }
    	 return res;
    }
}
