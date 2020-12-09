// Class: Implementation of BST in A2
// Implement the following functions according to the specifications provided in Tree.java
public class BSTree extends Tree {

    private BSTree left, right;     // Children.
    private BSTree parent;          // Parent pointer.
        
    public BSTree(){  
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node!.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
    }    

    public BSTree(int address, int size, int key){
        super(address, size, key); 
    }

    private BSTree getRoot(){
        BSTree current = this;
        if(current == null) return null;
        while(current.parent != null){
            current = current.parent;
        }
        return current;
    }

    private BSTree getActualRoot(){
        BSTree current = getRoot();
        if(current.right == null) return null;
        else return current.right;
    }

    private int compare(BSTree node1, BSTree node2){
        if(node1.key < node2.key){
            return -1;
        }
        else if(node1.key > node2.key){
            return 1;
        }
        else{
            if(node1.address < node2.address){
                return -1;
            }
            else if(node1.address > node2.address){
                return 1;
            }
            else{
                if(node1.size < node2.size){
                    return -1;
                }
                else if(node1.size > node2.size){
                    return 1;
                }
                else return 0;
            }
        }
    }

    private void insertHelper(BSTree current, BSTree newNode){
        if(current == null){
            current = newNode;
            newNode.parent = current;
        }
        else {
            if(compare(newNode, current) < 0){
                // Insert in left subtree
                if(current.left == null){
                    current.left = newNode;
                    newNode.parent = current;
                }
                else insertHelper(current.left, newNode);
            }
            else{
                // Insert in right subtree
                if(current.right == null){
                    current.right = newNode;
                    newNode.parent = current;
                }
                else insertHelper(current.right, newNode);
            }
        }
    }

    public BSTree Insert(int address, int size, int key) 
    {   
        BSTree newDict = new BSTree(address, size, key);
        BSTree current = getRoot();
        insertHelper(current, newDict);
        return newDict;
    }

    public boolean Delete(Dictionary e) { 
        if(e == null) return false;
        BSTree dict = new BSTree(e.address, e.size, e.key);
        BSTree root = getActualRoot();
        if(root == null) return false;

        BSTree current = root.Find(e.key, true);

        while(current != null){
            if(compare(current, dict) == 0)
                break;
            else
                current = current.getNext();
        }
        if(current == null) return false;

        BSTree d = current;
        if(d.left == null && d.right == null){
            // leaf node, just delete
            if(d.parent.left == d){
                d.parent.left = null;
            }
            else{
                d.parent.right = null;
            }
            d.parent = null;
            return true;
        }
        else if(d.left == null){
            // node with one right child, just update pointers
            BSTree currParent = d.parent;
            BSTree currChild = d.right;
            if(d.parent.left == d){
                d.parent.left = currChild;
            }
            else{
                d.parent.right = currChild;
            }
            currChild.parent = currParent;
            d.parent = null;
            d.right = null;
            return true;
        }
        else if(d.right == null){
            // node with one left child, just update pointers
            BSTree currParent = d.parent;
            BSTree currChild = d.left;
            if(d.parent.left == d){
                d.parent.left = currChild;
            }
            else{
                d.parent.right = currChild;
            }
            currChild.parent = currParent;
            d.parent = null;
            d.left = null;
            return true;
        }

        BSTree next = d.getNext();       
        BSTree currParent = d.parent;
        BSTree nextParent = next.parent;
        BSTree leftChild = d.left;
        BSTree rightChild = d.right; 

        if(nextParent == d){
            next.parent = currParent;
            if(currParent.left == d) currParent.left = next;
            else currParent.right = next;

            next.left = leftChild; leftChild.parent = next;
            return true;
        }
        if(next.left == null && next.right == null){
            // successor has no children
            if(nextParent.left == next) nextParent.left = null;
            else nextParent.right = null;
        }
        else{
            // successor has one child which has to be the right child
            BSTree nextRightChild = next.right;
            if(nextParent.left == next){
                nextParent.left = nextRightChild;
                nextRightChild.parent = nextParent;
            }
            else{
                nextParent.right = nextRightChild;
                nextRightChild.parent = nextParent;
            }
        }
        next.parent = currParent;
        next.left = leftChild; next.right = rightChild;

        if(leftChild != null) leftChild.parent = next;
        if(rightChild != null) rightChild.parent = next;

        if(currParent.left != null && currParent.left == d) currParent.left = next;
        else currParent.right = next;

        d.left = null; d.right = null; d.parent = null;
        return true;
    }

    private BSTree findMin(BSTree node){
        while(node.left != null){
            node = node.left;
        }
        return node;
    }
    
    public BSTree Find(int key, boolean exact)
    {   
        BSTree current = getActualRoot();
        BSTree answer = null;
        if(exact){
            while(current != null){ 
                if(current.key > key)
                    current = current.left;

                else if(current.key < key)
                    current = current.right;

                else{
                    // store the correct node as the potential answer and search in the left subtree
                    answer = current;
                    current = current.left;
                }
            }
        }
        else{
            while(current != null){ 
                if(current.key >= key){
                    answer = current;
                    current = current.left;
                }
                else
                    current = current.right;
            }
        }
        return answer;
    }

    public BSTree getFirst()
    { 
        BSTree current = getActualRoot();
        if(current == null || current.left == null) return current;

        return findMin(current);
    }

    public BSTree getNext()
    { 
        BSTree current = this;
        if(current.right != null){
            return findMin(current.right);
        }
        else{
            BSTree currParent = current.parent;
            while(currParent != null && current == currParent.right){
                current = currParent;
                currParent = currParent.parent;
            }
            return currParent;
        }
    }
    
    private boolean checkBSTAndCycle(BSTree root){
        if(root.left != null){
            if(root.left.parent != root || root.left.key > root.key || (root.left.key == root.key && root.left.address > root.address)) return false;
            if(!checkBSTAndCycle(root.left)) return false;
        }
        if(root.right != null){
            if(root.right.parent != root || root.right.key < root.key || (root.right.key == root.key && root.right.address < root.address)) return false;
            if(!checkBSTAndCycle(root.right)) return false;
        }
        return true;
    }
    public boolean sanity()
    {   
        BSTree current = this;
        while(current.parent != null){
            if(current.parent.left != null || current.parent.right != null)
                return false;
            current = current.parent;
        }
        // check sentinel node conditions
        if(current.parent == null){
            // all data must be -1
            if(current.address != -1 || current.key != -1 || current.size != -1){
                return false;
            }
            // left child must be null
            if(current.left != null){
                return false;
            }
        }
        // sentinel conditions checked, go to the root of the tree now
        current = current.right;
        if(current == null) return true;
        if(!checkBSTAndCycle(current)) return false;
        return true;
    }
}


 


