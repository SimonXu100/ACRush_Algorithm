// Binary Search
//running time: O(lgn)
/*
class Solution {
    public int mySqrt(int x) {
        if(x<2) return x;
        return (int) BinarySearch(1,x,x);     
    }
    public long BinarySearch(long l, long r, long x){
        long mid = l + (long) ((r-l)/2);
        if(l>r) return (l-1);
        long temp = mid * mid;
        if(x == temp){
            return mid;
        }
        else if(x < temp ){
            return BinarySearch(l,mid-1,x);
        }
        else{
            return BinarySearch(mid+1,r,x);
        }
    }
}
*/
// while iteration: binarySearch
class Solution {
    public int mySqrt(int x) {
        if(x<2) return x;
        long start=1, end=x;
        long mid=0;
        long temp =0;
        while(start<=end){
            mid = start + (end-start)/2;
            temp = mid * mid;
            if(x==temp) return (int) mid;
            else if(x<temp) end=mid-1;
            else start=mid+1;
        }
        return (int) start-1;
    }
    
}