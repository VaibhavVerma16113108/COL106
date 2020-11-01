public class LinkedList<T>{
	Node head;
	private int size = 0;
	private class Node{
		T data;
		Node next;
		public Node(T data){
			this.data = data;
		}
	}
	public int getSize(){
		return size;
	}
	public void append(T data){		  // inserts data at the end of the list
		size++;
		if(head == null){
			head = new Node(data);
			return;
		}
		Node current = head;
		while(current.next != null){
			current = current.next;
		}
		current.next = new Node(data);
	}

	public void prepend(T data){		// inserts data at the beginning of the list
		Node newHead = new Node(data);
		newHead.next = head;
		head = newHead;
		size++;
	}
	public void insert(T data, int pos){		// inserts data at the given index, assuming pos is less than the 	  										  // size of the list
		if(pos >= size){
			System.out.println("Invalid position.");
			return;
		}
		size++;
		if(pos == 0){
			this.prepend(data);
			return;
		}
		Node current = head;
		for(int i = 1; i < pos; i++){
			current = current.next;
		}
		Node oldcurrent = current.next;		// 30
		Node newNode = new Node(data);
		current.next = newNode;
		newNode.next = oldcurrent;
	}

	public void deleteWithValue(T data){		// deletes data with given value
		if (head == null) return;
		size--;
		if(head.data == data){
			head = head.next;
			return;
		}
		Node current = head;
		while(current.next != null){
			if(current.next.data == data){
				current.next = current.next.next;
				return;
			} 
			current = current.next;
		}
	}		
	public void printList(){				// prints out all the elements of the list
		if(head == null){
			System.out.println("List is empty.");
			return;
		}
		Node current = head;
		while(current.next != null){
			System.out.print(current.data + "->");
			current = current.next;
		}
		System.out.println(current.data);
	}
	// static SinglyLinkedListNode reverse(SinglyLinkedListNode head) {	  REVERSES A LINKED LIST USING STACK
 //        Stack<SinglyLinkedListNode> helper = new Stack<>();
 //        while(head != null){
 //            helper.push(head);
 //            head = head.next;
 //        }
 //        head = helper.pop();
 //        SinglyLinkedListNode current = head;
 //        while(!helper.isEmpty()){
 //            current.next = helper.peek();
 //            current = helper.pop();
 //        }
 //        current.next = null;
 //        return head;
 //    }
}