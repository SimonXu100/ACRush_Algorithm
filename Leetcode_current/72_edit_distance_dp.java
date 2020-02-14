// DP, similar to LCS
// S[i,j] = 1 + Math.max(s[i-1,j-1], s[i-1,j], s[i,j-1]) if word1[i] != word2[j]: according to replace, input, remove
// Otherwise: S[i,j] = S[i-1][j-1]: if word1[i]==word2[j] or S[i,j] = Math.min(S[i-1,j-1], s[i-1,j-1]+1, s[i-1,j] +1)
// because replace and do not operation got the same result
/*
class Solution {
    public int minDistance(String word1, String word2) {
          int m = word1.length();
          int n = word2.length();
          int[][] s = new int[m+1][n+1];
          if( m==0 && n==0 ) return 0;
          //initialize
          for(int i=1;i<m+1;i++){
              s[i][0] = i;
          }
          for(int j=1;j<n+1;j++){
              s[0][j] = j;
          }   
          for(int i=1;i<m+1;i++){
              for(int j=1;j<n+1;j++){
                  if(word1.charAt(i-1) == word2.charAt(j-1)){
                      s[i][j] = s[i-1][j-1];
                  }
                  else{
                      s[i][j] = 1 + Math.min( Math.min(s[i-1][j-1], s[i-1][j]),s[i][j-1]); 
                  }
              }
          }
          return s[m][n];
    }
}
*/

// sample

class Solution {
    public int minDistance(String word1, String word2) {
          int m = word1.length();
          int n = word2.length();
          int[][] s = new int[m+1][n+1];
          //initialize
          for(int i=1;i<m+1;i++){
              s[i][0] = i;
          }
          for(int j=1;j<n+1;j++){
              s[0][j] = j;
          }   
          for(int i=1;i<m+1;i++){
              for(int j=1;j<n+1;j++){
                  
                  int left = s[i - 1][j] + 1;
                  int down = s[i][j - 1] + 1;
                  int left_down = s[i - 1][j - 1];
                  
                  if(word1.charAt(i-1) != word2.charAt(j-1)){
                      //s[i][j] = s[i-1][j-1];
                      left_down +=1; 
                  }
                  s[i][j] = Math.min(left_down,Math.min(left,down));
              }
          }
          return s[m][n];
    }
}

/*
class Solution {
  public int minDistance(String word1, String word2) {
    int n = word1.length();
    int m = word2.length();

    // if one of the strings is empty
    if (n * m == 0)
      return n + m;

    // array to store the convertion history
    int [][] d = new int[n + 1][m + 1];

    // init boundaries
    for (int i = 0; i < n + 1; i++) {
      d[i][0] = i;
    }
    for (int j = 0; j < m + 1; j++) {
      d[0][j] = j;
    }

    // DP compute 
    for (int i = 1; i < n + 1; i++) {
      for (int j = 1; j < m + 1; j++) {
        int left = d[i - 1][j] + 1;
        int down = d[i][j - 1] + 1;
        int left_down = d[i - 1][j - 1];
        if (word1.charAt(i - 1) != word2.charAt(j - 1))
          left_down += 1;
        d[i][j] = Math.min(left, Math.min(down, left_down));

      }
    }
    return d[n][m];
  }
}
*/

























