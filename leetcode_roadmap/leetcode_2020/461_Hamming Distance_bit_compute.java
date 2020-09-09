class Solution {
    public int hammingDistance(int x, int y) {
        return bits_sum(x^y);
    }

    public int bits_sum(int x){
        int sum = 0;
        while(x!=0){
            if(x % 2 == 1) sum++;
            x = x / 2;
        }
        return sum;
    }
}


// 位运算
