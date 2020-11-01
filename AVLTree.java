public class AVLTree {
	class Node {
		int data;
		Node left;
		Node right;
		int height;

		Node(int data){
			this.data = data;
			this.height = 1;
		}
	}

	private Node root;

	// Search for an element in the AVL Tree; this function is accessible to the user.
	public boolean search(int item){
		return search(this.root, item);
	}
	// Implements the logic of the "search" function. Returns true if the element is found, else return false.
	private boolean search(Node node, int item){
		if(node == null) return false;
		if(node.data == item) return true;
		if(item < node.data){
			return search(node.left, item);
		}
		else{
			return search(node.right, item);
		}
	}

	// Deletes the given item from the tree (if it exists) and returns the root of the new tree.
	public Node delete(int item){
		if(!search(item)) return delete(this.root, item);
		return root;
	}
	private Node delete(Node node, int item){
		if(node == null) return null;
		
		if(node.left == null && node.right == null){
			// Node is a leaf and is the one to be deleted. Simply set the parent reference as null.
		}
		else if(node.left == null){

		}
		else if(node.right == null){

		}
		else{

		}
	}

	// Inserts the given element in the AVL Tree
	public void insert(int item){
		this.root = insert(this.root, item);
	}
	private Node insert(Node node, int item){
		if(node == null){
			Node newNode = new Node(item);
			return newNode;
		}

		if(item > node.data){
			node.right = insert(node.right, item);
		}
		else if(item < node.data){
			node.left = insert(node.left, item);
		}

		node.height = Math.max(height(node.left), height(node.right)) + 1;
		int bf = balancingFactor(node);

		// Left Left case
		if(bf > 1 && item < node.left.data){
			node = rightRotate(node);
		}

		// Right Right case
		if(bf < -1 && item > node.right.data){
			node = leftRotate(node);
		}

		// Left Right case
		if(bf > 1 && item > node.left.data){
			node.left = leftRotate(node.left);
			node = rightRotate(node);
		}

		// Right Left case
		if(bf < -1 && item < node.right.data){
			node.right = rightRotate(node.right);
			node = leftRotate(node);
		}

		return node;
	}
	private int balancingFactor(Node node){
		if(node == null) return 0;
		return height(node.left) - height(node.right);
	}
	private int height(Node node){
		if(node == null) return 0;
		return node.height;
	}
	private Node rightRotate(Node c){
		Node b = c.left;
		Node T3 = b.right;

		// Rotate
		b.right = c;
		c.left = T3;

		// update height
		c.height = Math.max(height(c.left), height(c.right)) + 1;
		b.height = Math.max(height(b.left), height(b.right)) + 1;

		return b;
	}
	private Node leftRotate(Node c){
		Node b = c.right;
		Node T2 = b.left;

		// Rotate
		b.left = c;
		c.right = T2;

		// update height
		c.height = Math.max(height(c.left), height(c.right)) + 1;
		b.height = Math.max(height(b.left), height(b.right)) + 1;

		return b;  
	}
	
	
}