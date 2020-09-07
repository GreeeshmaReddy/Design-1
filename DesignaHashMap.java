//Time - O(N) N: length of linkedlist.max 100
//Space - O(M + N) M: length of array nodes. max 10000
/*
Maintain an array of nodes of some size like 60%, then in case of collision attach nodes like linkedlist(40%)
*/
class MyHashMap {
    
    class Node{
        int key; int value;
        Node next;
        Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
    
    Node[] nodes;

    /** Initialize your data structure here. */
    public MyHashMap() {
        nodes = new Node[10000];     //array of 10000 nodes, so that 100 can be linkedlist
    }
    
    public int index(int key){
        return Integer.hashCode(key)%nodes.length;
    }
    
    /** value will always be non-negative. */
    public void put(int key, int value) {
        int index = index(key);
        if(nodes[index] == null){
            nodes[index] = new Node(-1,-1);
        }
        Node prev = null;
        Node curr = nodes[index];
        while(curr!=null && curr.key!=key){
            prev = curr;
            curr = curr.next;
        }
        if(prev.next == null) prev.next = new Node(key,value);
        else{
            prev.next.value = value;
        }
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int index = index(key);
        if(nodes[index] == null){
            return -1;
        }
        Node prev = null;
        Node curr = nodes[index];
        while(curr!=null && curr.key!=key){
            prev = curr;
            curr = curr.next;
        }
        if(prev.next == null) return -1;
        return prev.next.value;
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int index = index(key);
        if(nodes[index] == null){
            return;
        }
        Node prev = null;
        Node curr = nodes[index];
        while(curr!=null && curr.key!=key){
            prev = curr;
            curr = curr.next;
        }
        if(prev.next == null) return;
        prev.next = prev.next.next;
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */