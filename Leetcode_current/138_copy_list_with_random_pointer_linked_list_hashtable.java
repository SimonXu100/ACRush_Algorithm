/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/
// LinkedList + Hashtable
// running time: O(n)
/*
class Solution {
    public Node copyRandomList(Node head) {
        if(head == null) return null;
        // hashtable storing the random pointer
        Map<Node, Node> hashmap = new HashMap<>();
        Node cur = head;
        Node first = new Node(head.val);
        hashmap.put(head, first);
        
        while(cur != null){
            if(cur.next!=null && !hashmap.containsKey(cur.next)){
                hashmap.put(cur.next, new Node(cur.next.val));
            }
            hashmap.get(cur).next = hashmap.get(cur.next);
            
            if(cur.random!=null && !hashmap.containsKey(cur.random)){
                hashmap.put(cur.random, new Node(cur.random.val));
            }
            hashmap.get(cur).random = hashmap.get(cur.random);
            cur = cur.next;
        }
        return first;
    }
}
*/






// method 2: first copy all nodes, and then linked together
/*
class Solution {
    public Node copyRandomList(Node head) {
        if(head == null) return null;
        // hashtable storing the random pointer
        Map<Node, Node> hashmap = new HashMap<>();
        Node cur = head.next;
        Node first = new Node(head.val);
        hashmap.put(head, first);
        
        //create all clone and put in the hashmap
        while(cur != null){
            hashmap.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        
        cur = head;
        // link together
        while(cur != null){
            hashmap.get(cur).next = hashmap.get(cur.next);
            hashmap.get(cur).random = hashmap.get(cur.random);
            cur = cur.next;
        }
        return first;   
    }
}
*/






