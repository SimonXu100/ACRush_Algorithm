// f(i)=f(i−1)+f(i−2)[i−1≥0,10≤x≤25]
// 动态规划
// 滚动数组的思想，压缩成三个变量
// 边界条件是 f(-1) = 0f(−1)=0，f(0) = 1f(0)=1
class Solution {
    public int translateNum(int num) {
        String src = String.valueOf(num);
        int p = 0, q = 0, r = 1;
        for(int i=0; i<src.length(); i++){
            p = q;
            q = r;
            r = 0;
            r += q;
            if(i == 0) continue;
            
            String pre = src.substring(i-1, i+1);
            if(pre.compareTo("25") <= 0 && pre.compareTo("10") >=0){
                r += p;
            }
        }
        return r;
    }
}