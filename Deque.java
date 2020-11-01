public class Deque<T> {
	Node header, trailer;		// Sentinel nodes
	class Node {
		T data;
		Node prev;
		Node next;
		public Node(T data){
			this.data = data;
		}
	}
	public void insertFirst(T data){
		Node temp = new Node(data);
		if(header.next == trailer){
			header.next = temp;
			temp.prev = header;
			trailer.prev = temp;
			temp.next = trailer;
		}
		else{
			Node current = header.next;
			header.next = temp;
			temp.prev = header;
			temp.next = current;
			current.prev = temp;
		}
	}
	public void insertLast(T data){
		Node temp = new Node(data);
		if(trailer.prev == header){
			trailer.prev = temp;
			temp.next = trailer;
			header.next = temp;
			temp.prev = header;
		}
		else{
			Node current = trailer.prev;
			trailer.prev = temp;
			temp.next = trailer;
			temp.prev = current;
			current.next = temp;
		}
	}
	public T removeFirst(){
		if(header.next == trailer){
			System.out.println("Error: Deque is empty.");
			return null;
		}
		else{
			T item = header.next.data;
			header.next = header.next.next;
			header.next.prev = header;
			return item;
		}
	}
	public T removeLast(){
		if(trailer.prev == header){
			System.out.println("Error: Deque is empty.");
			return null;
		}
		else{
			T item = trailer.prev.data;
			trailer.prev.prev = trailer.prev;
			trailer.prev.next = trailer;
			return item;
		}
	}
	public T first(){
		if(header.next == trailer){
			System.out.println("Error: Deque is empty");
			return null;
		}
		else{
			return header.next.data;
		}
	}
	public T last(){
		if(trailer.prev == header){
			System.out.println("Error: Deque is empty");
			return null;
		}
		else{
			return trailer.prev.data;
		}
	}
}