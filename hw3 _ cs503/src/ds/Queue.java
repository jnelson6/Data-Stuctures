package ds;


/**
 * 
 * @author Julia Nelson
 * Homework 3
 * 7/19/19
 *
 */
public class Queue {

	public int size;
	public int[] array;
	public int head;
	public int tail;
	
	public Queue () {
		size = 0;
		array = null;
		head = 0;
		tail = 0;
	}
	
	public Queue (int _size) {
		size = _size;
		array = new int[size];
		head = 0;
		tail = 0;
	}
	
	
	
	/*
	 * Implement the ENQUEUE(Q, x) function
	 */
	public void enqueue (int x) {
		array[tail] = x;
		if(tail == array.length) {
			if(head == 0) {								//Task 5 capacity check
				System.err.println("Queue Overflow");
			}
			tail = 1;
		}
		else {
			tail = tail + 1;
		}
	}
	
	
	
	/*
	 * Implement the DEQUEUE(Q) function
	 */
	public int dequeue() {
		int x = array[tail];
		
		if (head < 0 || head > tail) {				////Task 6 capacity check
			System.err.println("Queue is empty");
		}
		
		if( head == array.length) {
			head = 1;
		}
		else {
			head = head +1;
		}
		return x;
		
	}
	
	
	
	
	
	/*
	 * Convert queue to string in the format of #size, head, tail, [#elements]
	 */
	public String toString () {
		String str;
		
		str = size + ", " + head + ", " + tail + ", [";
		for (int i = head; i%size < tail; i++) 
			str += array[i] + ",";
		
		str += "]";
		return str;
	}
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queue q;
		
		q = new Queue(10);
		for (int i = 0; i < 5; i++)
			q.enqueue(i);
		System.out.println(q.toString());
		for (int i = 0; i < 2; i++) 
			q.dequeue();
		System.out.println(q.toString());
	}


}
