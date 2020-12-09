// Class: Height balanced AVL Tree
// Binary Search Tree
import java.util.*;
public class AVLTree extends BSTree {
    
    private AVLTree left, right;     // Children. 
    private AVLTree parent;          // Parent pointer. 
    private int height;  // The height of the subtree
        
    public AVLTree() { 
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node !.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
        
    }

    public AVLTree(int address, int size, int key) { 
        super(address, size, key);
        this.height = 0;
    }

    // Implement the following functions for AVL Trees.
    // You need not implement all the functions. 
    // Some of the functions may be directly inherited from the BSTree class and nothing needs to be done for those.
    // Remove the functions, to not override the inherited functions.
    
    private AVLTree getRoot(){
        AVLTree current = this;
        if(current == null) return null;
        while(current.parent != null){
            current = current.parent;
        }
        return current;
    }

    private AVLTree getActualRoot(){
        AVLTree current = getRoot();
        if(current.right == null) return null;
        else return current.right;
    }

    private int compare(AVLTree node1, AVLTree node2){
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

    private AVLTree findMin(AVLTree node){
        while(node.left != null){
            node = node.left;
        }
        return node;
    }
    
    private void insertHelper(AVLTree current, AVLTree newNode){
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
        updateHeight(newNode);
        checkBalance(newNode);
    }

    private void updateHeight(AVLTree node){
        if(node.parent == null){
            return;
        }
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        updateHeight(node.parent); 
    }

    private void checkBalance(AVLTree node){
        if(node.parent == null) return;
        if(Math.abs(height(node.left) - height(node.right)) > 1){
            rebalance(node);
        }
        checkBalance(node.parent);
    }

    private void rebalance(AVLTree node){
        if(node.parent == null){
            return;
        }
        if(height(node.left) - height(node.right) > 1){
            if(height(node.left.left) >= height(node.left.right))
                node = rightRotate(node);
            else
                node = leftRightRotate(node);
        }
        else{
            if(height(node.right.right) >= height(node.right.left))
                node = leftRotate(node);
            else
                node = rightLeftRotate(node);
        }
    }

    private AVLTree rightRotate(AVLTree x){
        AVLTree currParent = x.parent;
        AVLTree z = x.left;
        AVLTree T23 = z.right;

        // Rotate
        z.right = x;
        x.parent = z;
        x.left = T23;
        if(T23 != null) T23.parent = x;
        z.parent = currParent;
        if(currParent.left == x) currParent.left = z;
        else currParent.right = z;

        // update height
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        z.height = Math.max(height(z.left), height(z.right)) + 1;
        currParent.height = Math.max(height(currParent.left), height(currParent.right)) + 1;
        return z;
    }

    private AVLTree leftRotate(AVLTree x){
        AVLTree currParent = x.parent;
        AVLTree z = x.right;
        AVLTree T23 = z.left;

        // Rotate
        z.left = x;
        x.parent = z;
        x.right = T23;
        if(T23 != null) T23.parent = x;
        z.parent = currParent;
        if(currParent.left == x) currParent.left = z;
        else currParent.right = z;

        // update height
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        z.height = Math.max(height(z.left), height(z.right)) + 1;
        currParent.height = Math.max(height(currParent.left), height(currParent.right)) + 1;
        return z;  
    }

    private AVLTree rightLeftRotate(AVLTree node){
        node.right = rightRotate(node.right);
        return leftRotate(node);
    }

    private AVLTree leftRightRotate(AVLTree node){
        node.left = leftRotate(node.left);
        return rightRotate(node);
    }

    private int height(AVLTree node){
        if(node == null) return -1;
        return node.height;
    }

    public AVLTree Insert(int address, int size, int key) {   
        AVLTree newDict = new AVLTree(address, size, key);
        AVLTree current = getRoot();
        insertHelper(current, newDict);
        return newDict;    
    }

    public boolean Delete(Dictionary e) { 
        if(e == null) return false;
        AVLTree dict = new AVLTree(e.address, e.size, e.key);
        AVLTree root = getActualRoot();
        if(root == null) return false;

        AVLTree current = root.Find(e.key, true);

        while(current != null){
            if(compare(current, dict) == 0)
                break;
            else
                current = current.getNext();
        }
        if(current == null) return false;

        AVLTree d = current;
        if(d.left == null && d.right == null){
            AVLTree currParent = d.parent;
            // leaf node, just delete
            if(d.parent.left == d){
                d.parent.left = null;
            }
            else{
                d.parent.right = null;
            }
            d.parent = null;
            updateHeight(currParent);
            checkBalance(currParent);
            return true;
        }
        else if(d.left == null){
            // node with one right child, just update pointers
            AVLTree currParent = d.parent;
            AVLTree currChild = d.right;
            if(d.parent.left == d){
                d.parent.left = currChild;
            }
            else{
                d.parent.right = currChild;
            }
            currChild.parent = currParent;
            d.parent = null;
            d.right = null;
            updateHeight(currParent);
            checkBalance(currParent);
            return true;
        }
        else if(d.right == null){
            // node with one left child, just update pointers
            AVLTree currParent = d.parent;
            AVLTree currChild = d.left;
            if(d.parent.left == d){
                d.parent.left = currChild;
            }
            else{
                d.parent.right = currChild;
            }
            currChild.parent = currParent;
            d.parent = null;
            d.left = null;
            updateHeight(currParent);
            checkBalance(currParent);
            return true;
        }

        AVLTree next = d.getNext();       
        AVLTree currParent = d.parent;
        AVLTree nextParent = next.parent;
        AVLTree leftChild = d.left;
        AVLTree rightChild = d.right; 

        if(nextParent == d){
            next.parent = currParent;
            if(currParent.left == d) currParent.left = next;
            else currParent.right = next;

            next.left = leftChild; leftChild.parent = next;
            updateHeight(next);
            checkBalance(next);
            return true;
        }
        if(next.left == null && next.right == null){
            // successor has no children
            if(nextParent.left == next) nextParent.left = null;
            else nextParent.right = null;
        }
        else{
            // successor has one child which has to be the right child
            AVLTree nextRightChild = next.right;
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
        updateHeight(nextParent);
        checkBalance(nextParent);
        return true;
    }

    public AVLTree Find(int key, boolean exact){   
        AVLTree current = getActualRoot();
        AVLTree answer = null;
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

    public AVLTree getFirst(){ 
        AVLTree current = getActualRoot();
        if(current == null || current.left == null) return current;

        return findMin(current);
    }

    public AVLTree getNext(){ 
        AVLTree current = this;
        if(current.right != null){
            return findMin(current.right);
        }
        else{
            AVLTree currParent = current.parent;
            while(currParent != null && current == currParent.right){
                current = currParent;
                currParent = currParent.parent;
            }
            return currParent;
        }
    }

    private boolean checkHeightBalance(AVLTree root){
        if(root == null) return true;
        if(Math.abs(height(root.left) - height(root.right)) > 1){
            System.out.println(height(root.left) + " " + height(root.right));
            return false;
        }
        if(!checkHeightBalance(root.left) || !checkHeightBalance(root.right)) return false;
        return true;
    }

    private boolean checkAVLAndCycle(AVLTree root){
        if(root.left != null){
            if(root.left.parent != root || root.left.key > root.key || (root.left.key == root.key && root.left.address > root.address)) return false;
            if(!checkAVLAndCycle(root.left)) return false;
        }
        if(root.right != null){
            if(root.right.parent != root || root.right.key < root.key || (root.right.key == root.key && root.right.address < root.address)) return false;
            if(!checkAVLAndCycle(root.right)) return false;
        }
        return true;
    }

    public boolean sanity()
    { 
        AVLTree current = this;
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

        // check BST property and pointers
        if(!checkAVLAndCycle(current)) return false;

        // check height property of AVL Tree
        if(!checkHeightBalance(current)) return false;

        return true;
    }
}


