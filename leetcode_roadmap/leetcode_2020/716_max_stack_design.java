// hashmap: get List<> 为引用




// method 1
// assistant stack
// running time: O(N)
// space: O(N)
/*
class MaxStack {
    Stack<Integer> stack;
    Stack<Integer> maxStack;
    
    public MaxStack() {
        stack = new Stack<Integer>();
        maxStack = new Stack<Integer>();
    }
    
    public void push(int x) {
        stack.push(x);
        if(maxStack.isEmpty() || x >= maxStack.peek()) maxStack.push(x);
    }
    
    public int pop() {
        int temp = 0;
        if(!stack.isEmpty()){
            temp = stack.pop();
            if(temp == maxStack.peek()) maxStack.pop();
        }
        return temp;
    }
    
    public int top() {
        if(!stack.isEmpty()) return stack.peek();
        return 0;   
    }
    
    public int peekMax() {
        if(!maxStack.isEmpty()) return maxStack.peek();
        return 0;
    }
    // only remove one max
    public int popMax() {
        int temp = 0;
        if(!maxStack.isEmpty()) temp = maxStack.pop();
        List<Integer> temp_list = new ArrayList<>();
        while(stack.peek()!= temp){
            temp_list.add(0, stack.pop());
        } 
        stack.pop();
        // inversely insert the value
        for(int value : temp_list){
            push(value);
        }
        return temp;
    }
}
*/


// method 2:
// 双向链表 + 平衡树
// running time: O(lgn)
// 我们使用双向链表存储栈，使用带键值对的平衡树（Java 中的 TreeMap）存储栈中出现的值以及这个值在双向链表中出现的位置。
class MaxStack {
    // 为了收纳相同元素链表
    TreeMap<Integer, List<Node>> map;
    DoubleLinkedList dll;

    
    public MaxStack() {
        map = new TreeMap<>();
        dll = new DoubleLinkedList();
    }
    
    public void push(int x) {
        Node node = dll.add(x);
        if(!map.containsKey(x)){
            map.put(x, new ArrayList<Node>());
        }
        map.get(x).add(node);
    }
    
    public int pop() {
        // pop()操作：在双向链表的末尾删除一个节点，它的值为 val，随后在平衡树上找到 val，删除它的列表中的最后一个位置。
        int val = dll.pop();
        List<Node> temp_list = map.get(val);
        temp_list.remove(temp_list.size()-1);
        if(temp_list.isEmpty()) map.remove(val);
        return val;
    }
    
    public int top() {
        return dll.peek();
    }
    
    public int peekMax() {
        return map.lastKey();
    }
    
    public int popMax() {
        int max = peekMax();
        List<Node> temp_list = map.get(max);
        Node node = temp_list.remove(temp_list.size()-1);
        dll.unlink(node);
        if (temp_list.isEmpty()) map.remove(max);
        return max;
    }
}

// self-make doublelinked list
// otherwise we cannot not realize this method
class Node{
    Node prev;
    Node next;
    int val;
    Node(int val){
        this.val = val;
    }
}
class DoubleLinkedList{
    Node head;
    Node tail;
    
    public DoubleLinkedList(){
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.prev = head;
    }
    // add Last
    public Node add(int val){
        Node temp = new Node(val);
        temp.next = tail;
        temp.prev = tail.prev;
        tail.prev.next = temp;
        tail.prev = temp;
        return temp;
    }

    public int pop(){
        return unlink(tail.prev).val;
    }
    public int peek(){
        return tail.prev.val;
    }
    public Node unlink(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
        return node; 
    }
}










/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */




// method 2 
//sample code 

// 使用线性的数据结构（例如数组和栈）无法在较短的时间复杂度内完成 popMax() 操作，因此我们可以考虑使用双向链表 + 平衡树，其中双向链表用来表示栈，
// 平衡树中的每一个节点存储一个键值对，其中“键”表示某个在栈中出现的值，“值”为一个列表，表示这个值在双向链表中出现的位置，存储方式为指针。平衡树的插入，删除和查找操作都是 O(logn) 的，
// 而通过平衡树定位到双向链表中的某个节点后，对该节点进行删除也是 O(1) 的，因此所有操作的时间复杂度都不会超过 O(logn)

// 算法：
// 我们使用双向链表存储栈，使用带键值对的平衡树（Java 中的 TreeMap）存储栈中出现的值以及这个值在双向链表中出现的位置。

// push(x) 操作：在双向链表的末尾添加一个节点，并且在平衡树上找到 xx，给它的列表中添加一个位置，指向新的节点。

// pop()操作：在双向链表的末尾删除一个节点，它的值为 \mathrm{val}val，随后在平衡树上找到 \mathrm{val}val，删除它的列表中的最后一个位置。

// top() 操作：返回双向链表中最后一个节点的值。

// peekMax() 操作：返回平衡树上的最大值。

// popMax() 操作：在平衡树上找到最大值和它对应的列表，得到列表中的最后一个位置，并将它在双向链表中和平衡树上分别删除。


 
// --------------------------------------------------------------------------------------------------------


// accumulation function
// double linked list

// DoubleLinkedList
/*
void    addFirst(T me)
Adds a new node to the start of the link list.
void    addLast(T me)
Adds a new node to the end of the link list.
void    debugDumpEntries()
Dump the cache entries from first to list for debugging.
T   getFirst()
Removes the specified node from the link list.
T   getLast()
Returns the last node from the link list, if there are any nodes.
void    makeFirst(T ln)
Moves an existing node to the start of the link list.
void    makeLast(T ln)
Moves an existing node to the end of the link list.
boolean remove(T me)
Removes the specified node from the link list.
void    removeAll()
Remove all of the elements from the linked list implementation.
T   removeLast()
Removes the specified node from the link list.
int size()
Returns the size of the list.
Methods inherited from class java.lang.Object
*/























