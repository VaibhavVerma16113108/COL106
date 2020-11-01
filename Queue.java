public class Queue<T> {
	private static final int MAX_SIZE = 1024;
	private int N = MAX_SIZE;
	private int f = -1;
	private int r = 0;
	private T Q[];

	// Constructors
	public Queue(){
		Q = (T[]) new Object[MAX_SIZE];
	}
	public Queue(int capacity){
		Q = (T[]) new Object[capacity];
		N = capacity;
	}

	// modifier methods
	public void enqueue(T element){
		if(size() == N - 1){
			System.out.println("Error: The queue is full.");
		}
		else{
			Q[r] = element;
			r = (r + 1) % N;
		}
	}
	public T dequeue(){
		if(isEmpty()){
			System.out.println("Error: Queue is empty.");
			return null;
		}
		else{
			T item = Q[f];
			Q[f] = null;
			f = (f + 1) % N;
			return item;
		}
	}

	// accessor methods
	public T front(){
		if(isEmpty()){
			System.out.println("Error: Queue is empty.");
			return null;
		}
		else{
			return Q[f];
		}
	}
	public int size(){
		return (N - f + r) % N;
	}
	public boolean isEmpty(){
		return (f == r);
	}
}