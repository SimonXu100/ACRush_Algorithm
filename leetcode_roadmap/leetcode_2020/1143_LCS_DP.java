// DP 
// C[i,j] = 0 if i=0,j=0
// C[i,j] = C[i-1,j-1]+1 if x[i]=y[j]
// C[i,j] = max(C[i,j-1],C[i-1,j]) if x[i]!=y[j]  
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        char[] x = text1.toCharArray();
        char[] y = text2.toCharArray();  
        int m = x.length;
        int n = y.length;
        int[][] c = new int[m+1][n+1];
        for(int i=0;i<m+1;i++){
            c[i][0] = 0;
        }
        for(int j=0;j<n+1;j++){
            c[0][j] = 0;
        }
        for(int i=1;i<m+1;i++){
            for(int j=1;j<n+1;j++){
                if(x[i-1]==y[j-1]) {
                    c[i][j] = c[i-1][j-1]+1;
                }
                else{
                    c[i][j] = Math.max(c[i-1][j],c[i][j-1]);
                }
            }
        }
        return c[m][n];
    }
}