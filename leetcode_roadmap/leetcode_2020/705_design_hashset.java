单独链接法：对于相同的散列值，我们将它们放到一个桶中，每个桶是相互独立的。
开放地址法：每当有碰撞， 则根据我们探查的策略找到一个空的槽为止。
双散列法：使用两个哈希函数计算散列值，选择碰撞更少的地址。
// key idea： 
/*
解决冲突单独链接法：对于相同的散列值，我们将它们放到一个桶中，每个桶是相互独立的。
开放地址法：每当有碰撞， 则根据我们探查的策略找到一个空的槽为止。
双散列法：使用两个哈希函数计算散列值，选择碰撞更少的地址。
*/

// method 1: 单独链表法
/*
哈希函数的共同特点是使用模运算符。\text{hash} = \text{value} \mod \text{base}hash=valuemodbase。其中，\text{base}base 将决定 HashSet 中的桶数。
从理论上讲，桶越多（因此空间会越大）越不太可能发生碰撞。\text{base}base 的选择是空间和碰撞之间的权衡。
此外，使用质数作为 \text{base}base 是一个明智的选择。例如 769769，可以减少潜在的碰撞

择链表来存储桶的所有值是更好的选择，插入和删除具有常数的时间复杂度
*/
/*
class MyHashSet {
    private Bucket[] bucketArray;
    private int keyRange;

    public MyHashSet() {
        // initialization
        this.keyRange = 769;
        this.bucketArray = new Bucket[this.keyRange];
        for(int i=0; i<this.keyRange; i++){
            this.bucketArray[i] = new Bucket();
        }
    }
    protected int hash(int key){
        return (key % this.keyRange);
    }
    public void add(int key) {
        int bucketIndex = this.hash(key);
        this.bucketArray[bucketIndex].insert(key);
    }
    public void remove(int key) {
        int bucketIndex = this.hash(key);
        this.bucketArray[bucketIndex].delete(key);
    }
    public boolean contains(int key) {
        int bucketIndex = this.hash(key);
        return this.bucketArray[bucketIndex].exists(key);
    }
}

class Bucket{
    private LinkedList<Integer> containter;

    public Bucket(){
        containter = new LinkedList<Integer>(); 
    }

    public void insert(Integer key){
        int index = this.containter.indexOf(key);
        if(index == -1) {
            this.containter.addFirst(key);
        }
    }
    
    public void delete(Integer key){
        this.containter.remove(key);
    }
    
    public boolean exists(Integer key){
        int index = this.containter.indexOf(key);
        return (index != -1);
    }
}
*/


// method 2:
// 二叉搜索树作为桶
class MyHashSet {
    private Bucket[] bucketArray;
    private int keyRange;

    public MyHashSet() {
        // initialization
        this.keyRange = 769;
        this.bucketArray = new Bucket[this.keyRange];
        for(int i=0; i<this.keyRange; i++){
            this.bucketArray[i] = new Bucket();
        }
    }
    protected int hash(int key){
        return (key % this.keyRange);
    }
    public void add(int key) {
        int bucketIndex = this.hash(key);
        this.bucketArray[bucketIndex].insert(key);
    }
    public void remove(int key) {
        int bucketIndex = this.hash(key);
        this.bucketArray[bucketIndex].delete(key);
    }
    public boolean contains(int key) {
        int bucketIndex = this.hash(key);
        return this.bucketArray[bucketIndex].exists(key);
    }
}








/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */