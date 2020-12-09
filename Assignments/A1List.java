// Implements Dictionary using Doubly Linked List (DLL)
// Implement the following functions using the specifications provided in the class List

public class A1List extends List {

    private A1List  next; // Next Node
    private A1List prev;  // Previous Node 

    public A1List(int address, int size, int key) { 
        super(address, size, key);
    }
    
    public A1List(){
        super(-1,-1,-1);
        // This acts as a head Sentinel

        A1List tailSentinel = new A1List(-1,-1,-1); // Intiate the tail sentinel
        
        this.next = tailSentinel;
        tailSentinel.prev = this;
    }

    public A1List Insert(int address, int size, int key)
    {   
        // if insertion is performed at tail sentinel, return null
        if(this.next == null) return null;

        A1List newElement = new A1List(address, size, key);
        A1List currentFirst = this.next;
        this.next = newElement;
        newElement.next = currentFirst;
        newElement.prev = this;
        currentFirst.prev = newElement;
    
        return newElement;
    }

    public boolean Delete(Dictionary d) 
    {   
        if(d == null) return false;
        A1List current = getFirst();
        while(current != null){
            if(d.key == current.key && d.address == current.address && d.size == current.size){
                current.prev.next = current.next;
                current.next.prev = current.prev;
                current.prev = null;
                current.next = null;
                return true;
            }
            
            else{
                current = current.next;
            }
        }
        return false;
    }

    public A1List Find(int k, boolean exact)
    {   
        A1List current = getFirst();
        if(current == null){
            return null;
        }
        if(exact){
            while(current.next != null){
                if(current.key == k){
                    return current;
                }
                current = current.next;
            }
            return null;
        }
        else{
            while(current.next != null){
                if(current.key >= k){
                    return current;
                }
                current = current.next;
            }
            return null;
        }
    }

    public A1List getFirst()
    {   A1List current = this;
        while(current.prev != null){
            current = current.prev;
        }
        if(current.next.next == null) return null;
        return current.next;
    }
    
    public A1List getNext() 
    {
        if(this.next == null || this.next.next == null) return null;
        return this.next;
    }

    public boolean sanity()
    {   
        A1List current = this;

        // check for circular linked list
        A1List slow = this, fast = this;
        while(slow != null && fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                return false;
            }
        }
        slow = this; fast = this;
        while(slow != null && fast != null && fast.prev != null){
            slow = slow.prev;
            fast = fast.prev.prev;
            if(slow == fast){
                return false;
            }
        }
        // Handle the case where list is non-empty
        if(current != null && current.prev != null && current.next != null){
            // node.next.prev should point to node. node.prev.next should point to node
            if(current.next.prev != current || current.prev.next != current){
                return false;
            }
        }
        // list is empty and tailer's next node must be null
        else{
            if(current.next.next != null){
                return false;
            }
        }
        // handle header and tailer node conditions
        if(current != null){
            if(current.prev == null || current.next == null){
                if(current.address != -1 || current.key != -1 || current.size != -1){
                    return false;
                }
            }
        }
        return true;
    }
}


