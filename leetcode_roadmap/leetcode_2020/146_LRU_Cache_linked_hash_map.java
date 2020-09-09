// hashmap 
// queue 
/*
class LRUCache {
    HashMap<Integer,Integer> hashmap = new HashMap<Integer,Integer>();
    Queue<Integer> queue = new LinkedList<Integer>();
    int capacity = 0;
    int count = 0;
    public LRUCache(int capacity) {
        this.capacity = capacity;
    }
    
    public int get(int key) {
        int res = hashmap.getOrDefault(key,-1);
        if(res>0){
            queue.offer(key);
        }
        return res;
    }
    
    public void put(int key, int value) {
        // if the key is not already present, then insert the value
        // if the the cache reached its capacity, then should invalidate the leaset recently used
        if(hashmap.getOrDefault(key,0)==0){
            if(hashmap.size()>= this.capacity){
                int lru = queue.poll();
                hashmap.remove(lru);
                hashmap.put(key,value);
                queue.remove(lru);
            }
            else{
                hashmap.put(key,value);
            }
        }
    }
}
*/

// method 1: Linked hashmap
/*
class LRUCache extends LinkedHashMap<Integer,Integer>{
    private int capacity;
    public LRUCache(int capacity) {
        // capactiy, loadFactor, acessOrder
        // accessOrder = True: access order
        // default accessOrder = False: created order
        // 0.75F load factor
        super(capacity,0.75F,true);
        this.capacity = capacity;
    }
    
    public int get(int key) {
        return super.getOrDefault(key, -1);
    }
    
    public void put(int key, int value) {
        super.put(key,value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer,Integer> eldest){
        return size() > capacity;
    }
}
*/

// method 2 actual implementation
// hashtable + 双向链表
//使用双向链表的一个好处是不需要额外信息删除一个节点，同时可以在常数时间内从头部或尾部插入删除节点。
//一个需要注意的是，在双向链表实现中，这里使用一个伪头部和伪尾部标记界限，这样在更新的时候就不需要检查是否是 null 节点
// 双向链表：o(1): delete, o(n): searching， 用来维护访问循序
// hashtable: O(1): searching: 用来维护访问

import java.util.Hashtable;
class LRUCache {

    //内部类
    // 删除指定节点在O(1): 时间内
    class DlinkedNode{
    	int key;
    	int value;
    	DlinkedNode prev;
    	DlinkedNode next;
    }
 	
 	private void addNode(DlinkedNode node){
 		// always add the new node right after head
 		node.prev = head;
 		node.next = head.next;

 		head.next.prev = node;
 	    head.next = node;
 	}


 	private void removeNode(DlinkedNode node){
 		// remove an existing node from the linked list
        
 		DlinkedNode prev = node.prev;
 		DlinkedNode next = node.next;
 		prev.next = next;
 		next.prev = prev;
 	}

 	private void moveTohead(DlinkedNode node){
 		// move certain node in between to the head
 		removeNode(node);
 		addNode(node);
 	}

 	private DlinkedNode popTail(){
 		//pop the current tail
 		DlinkedNode res = tail.prev;
        // error remove node find error
 		removeNode(res);
 		return res;
 	}

    // hash Table 维护
 	private Hashtable<Integer, DlinkedNode> cache = new Hashtable<Integer, DlinkedNode>();
    private int size;
    private int capacity;
    private DlinkedNode head, tail;
    public LRUCache(int capacity) {
    	this.capacity = capacity;
    	this.size = 0;

    	head = new DlinkedNode();
    	tail = new DlinkedNode();
    	//head.prev = null;
    	//tail.next = null;
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {

        DlinkedNode node = cache.get(key);
        if(node == null) return -1;

        // move the access node to the head
        moveTohead(node);
        return node.value; 
    }
    
    public void put(int key, int value) {
        DlinkedNode node = cache.get(key);
        if(node == null){
        	DlinkedNode newNode = new DlinkedNode();
        	newNode.key = key;
        	newNode.value = value;

        	cache.put(key, newNode);
        	addNode(newNode);
        	size++;
        	if(size > capacity){
        		//pop the tail
        		DlinkedNode tail = popTail();
        		cache.remove(tail.key);
        		size--;
        	}
        }
        else{
        	//update the access order
        	node.value = value;
      		moveTohead(node);
        }
    }
}



// 





















