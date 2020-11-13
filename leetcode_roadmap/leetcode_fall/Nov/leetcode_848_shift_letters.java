

// count 移动次数
// 总和剪值
// 倒叙计算






 
// java Math.floorMod  = (X -shifts[i])%26
class Solution {
    public String shiftingLetters(String S, int[] shifts) {
        StringBuilder ans = new StringBuilder();
        int X = 0;
        for (int shift: shifts)
            X = (X + shift) % 26;

        for (int i = 0; i < S.length(); ++i) {
            int index = S.charAt(i) - 'a';
            ans.append((char) ((index + X) % 26 + 97));
            X = Math.floorMod(X - shifts[i], 26);
        }

        return ans.toString();
    }
}




python
class Solution(object):
    def shiftingLetters(self, S, shifts):
        l = len(shifts) - 1
        while l > 0:
            shifts[l-1] += shifts[l]
            l = l - 1

        res = []
        for i in range(len(S)):
            tmp = (ord(S[i]) + shifts[i] - ord('a')) % 26 + ord('a')
            res.append(chr(tmp))
        return "".join(res)










