// Class: A2DynamicMem
// Implements Degragment in A2. No other changes should be needed for other functions.

public class A2DynamicMem extends A1DynamicMem {
      
    public A2DynamicMem() {  super(); }

    public A2DynamicMem(int size) { super(size); }

    public A2DynamicMem(int size, int dict_type) { super(size, dict_type); }

    // In A2, you need to test your implementation using BSTrees and AVLTrees. 
    // No changes should be required in the A1DynamicMem functions. 
    // They should work seamlessly with the newly supplied implementation of BSTrees and AVLTrees
    // For A2, implement the Defragment function for the class A2DynamicMem and test using BSTrees and AVLTrees. 

    public void Defragment() {
        if(type == 2){
            BSTree helperTree = new BSTree();
            for(Dictionary freeBlock = freeBlk.getFirst(); freeBlock != null; freeBlock = freeBlock.getNext()){
                // In freeBlk, the key of the nodes is "SIZE". In the helperTree, we must insert the blocks in such a way that "key = address". freeBlock = (address, key, size)    allocBlock = (key, size, address)
                helperTree.Insert(freeBlock.size, freeBlock.key, freeBlock.address);
            }
            BSTree helperBlock = helperTree.getFirst();
            if(helperBlock == null) return ;
            while(helperBlock != null){
                Dictionary helperBlockNext = helperBlock.getNext();
                if(helperBlockNext == null) return;
                if(helperBlock.size + helperBlock.key == helperBlockNext.key){
                    // the blocks are contiguous, merge them
                    Dictionary mergedHelperBlock = new BSTree(helperBlock.address, helperBlock.size + helperBlockNext.size, helperBlock.key);
                    Dictionary mergedFreeBlock = new BSTree(helperBlock.key, helperBlock.address, helperBlock.size + helperBlockNext.size);
                    Dictionary oldBlock = new BSTree(helperBlock.key, helperBlock.address, helperBlock.size);
                    Dictionary oldBlockNext = new BSTree(helperBlockNext.key, helperBlockNext.address, helperBlockNext.size);

                    helperTree.Delete(helperBlock); helperTree.Delete(helperBlockNext);
                    freeBlk.Delete(oldBlock); freeBlk.Delete(oldBlockNext);

                    freeBlk.Insert(helperBlock.key, helperBlock.address, helperBlock.size + helperBlockNext.size); 
                    helperBlock = helperTree.Insert(helperBlock.address, helperBlock.size + helperBlockNext.size, helperBlock.key);
                }

                else{
                    helperBlock = helperBlock.getNext();
                }
            }
            return ;
        }
        if(type == 3){
            AVLTree helperTree = new AVLTree();
            for(Dictionary freeBlock = freeBlk.getFirst(); freeBlock != null; freeBlock = freeBlock.getNext()){
                // In freeBlk, the key of the nodes is "SIZE". In the helperTree, we must insert the blocks in such a way that "key = address". freeBlock = (address, key, size)    allocBlock = (key, size, address)
                helperTree.Insert(freeBlock.size, freeBlock.key, freeBlock.address);
            }
            AVLTree helperBlock = helperTree.getFirst();
            if(helperBlock == null) return ;
            while(helperBlock != null){
                Dictionary helperBlockNext = helperBlock.getNext();
                if(helperBlockNext == null) return;
                if(helperBlock.size + helperBlock.key == helperBlockNext.key){
                    // the blocks are contiguous, merge them
                    Dictionary mergedHelperBlock = new AVLTree(helperBlock.address, helperBlock.size + helperBlockNext.size, helperBlock.key);
                    Dictionary mergedFreeBlock = new AVLTree(helperBlock.key, helperBlock.address, helperBlock.size + helperBlockNext.size);
                    Dictionary oldBlock = new AVLTree(helperBlock.key, helperBlock.address, helperBlock.size);
                    Dictionary oldBlockNext = new AVLTree(helperBlockNext.key, helperBlockNext.address, helperBlockNext.size);

                    helperTree.Delete(helperBlock); helperTree.Delete(helperBlockNext);
                    freeBlk.Delete(oldBlock); freeBlk.Delete(oldBlockNext);

                    freeBlk.Insert(helperBlock.key, helperBlock.address, helperBlock.size + helperBlockNext.size); 
                    helperBlock = helperTree.Insert(helperBlock.address, helperBlock.size + helperBlockNext.size, helperBlock.key);
                }

                else{
                    helperBlock = helperBlock.getNext();
                }
            }
            return ;
        }
        if(type == 1){
            A1List helperTree = new A1List();
            for(Dictionary freeBlock = freeBlk.getFirst(); freeBlock != null; freeBlock = freeBlock.getNext()){
                // In freeBlk, the key of the nodes is "SIZE". In the helperTree, we must insert the blocks in such a way that "key = address". freeBlock = (address, key, size)    allocBlock = (key, size, address)
                helperTree.Insert(freeBlock.size, freeBlock.key, freeBlock.address);
            }
            A1List helperBlock = helperTree.getFirst();
            if(helperBlock == null) return ;
            while(helperBlock != null){
                Dictionary helperBlockNext = helperBlock.getNext();
                if(helperBlockNext == null) return;
                if(helperBlock.size + helperBlock.key == helperBlockNext.key){
                    // the blocks are contiguous, merge them
                    Dictionary mergedHelperBlock = new A1List(helperBlock.address, helperBlock.size + helperBlockNext.size, helperBlock.key);
                    Dictionary mergedFreeBlock = new A1List(helperBlock.key, helperBlock.address, helperBlock.size + helperBlockNext.size);
                    Dictionary oldBlock = new A1List(helperBlock.key, helperBlock.address, helperBlock.size);
                    Dictionary oldBlockNext = new A1List(helperBlockNext.key, helperBlockNext.address, helperBlockNext.size);

                    helperTree.Delete(helperBlock); helperTree.Delete(helperBlockNext);
                    freeBlk.Delete(oldBlock); freeBlk.Delete(oldBlockNext);

                    freeBlk.Insert(helperBlock.key, helperBlock.address, helperBlock.size + helperBlockNext.size); 
                    helperBlock = helperTree.Insert(helperBlock.address, helperBlock.size + helperBlockNext.size, helperBlock.key);
                }

                else{
                    helperBlock = helperBlock.getNext();
                }
            }
            return ;
        }
        
    }
}