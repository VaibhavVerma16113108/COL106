public class BinarySearchTree<T extends Comparable<T>> {
	T data;
	BinarySearchTree parent, left, right;
	public BinarySearchTree(T data){
		this.data = data;
	}
	public void insert(T value){
		if(value.compareTo(data) < 0){
			if(left == null){
				left = new BinarySearchTree(value);
			}
			else{
				left.insert(value);
			}
		}
		else{
			if(right == null){
				right = new BinarySearchTree(value);
			}
			else{
				right.insert(value);
			}
		}
	}

	public boolean contains(T value){
		if(value == data){
			return true;
		}
		else if(value.compareTo(data) < 0){
			if(left == null) return false;
			else return left.contains(value);
		}
		else{
			if(right == null) return false;
			else return right.contains(value);
		}
	}

	public void printInOrder(){
		if(left != null){
			left.printInOrder();
		}
		System.out.print(data + " ");
		if(right != null){
			right.printInOrder();
		}
	}

	public void printPreOrder(){
		System.out.print(data + " ");
		if(left != null){
			left.printPreOrder();
		}
		if(right != null){
			right.printPreOrder();
		}
	}

	public void printPostOrder(){
		if(left != null){
			left.printPostOrder();
		}
		if(right != null){
			right.printPostOrder();
		}
		System.out.print(data + " ");
	}
}