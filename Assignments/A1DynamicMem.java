// Class: A1DynamicMem
// Implements DynamicMem
// Does not implement defragment (which is for A2).

public class A1DynamicMem extends DynamicMem {
      
    public A1DynamicMem() {
        super();
    }

    public A1DynamicMem(int size) {
        super(size);
    }

    public A1DynamicMem(int size, int dict_type) {
        super(size, dict_type);
    }

    public void Defragment() {
        return ;
    }

    // In A1, you need to implement the Allocate and Free functions for the class A1DynamicMem
    // Test your memory allocator thoroughly using Doubly Linked lists only (A1List.java).

    public int Allocate(int blockSize) {
        if(blockSize <= 0){
            return -1;
        }
        
        Dictionary requiredBlock = freeBlk.Find(blockSize, false);
        if(requiredBlock == null){
            return -1;
        }
        else{
            int startingAddress = requiredBlock.address;
            if(requiredBlock.key == blockSize){
                allocBlk.Insert(requiredBlock.size, blockSize, startingAddress);
                freeBlk.Delete(requiredBlock);
            }
            else{
                freeBlk.Delete(requiredBlock);
                allocBlk.Insert(requiredBlock.size, blockSize, requiredBlock.address);
                freeBlk.Insert(requiredBlock.address + blockSize, requiredBlock.size, requiredBlock.key - blockSize);
            }
            return startingAddress;
        }
    } 
    
    public int Free(int startAddr) {
        Dictionary requiredBlock = allocBlk.Find(startAddr, true);
        if(requiredBlock == null){
            return -1;
        }
        else{
            freeBlk.Insert(startAddr, requiredBlock.key, requiredBlock.size);
            allocBlk.Delete(requiredBlock);
            return 0;
        }
    }
}