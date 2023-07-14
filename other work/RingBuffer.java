/*****************************************************************************
 *Julia Nelson
I pledge my honor that I have abided by the stevens honor system
 *  This is a template file for RingBuffer.java. It lists the constructors and
 *  methods you need, along with descriptions of what they're supposed to do.
 *  
 *  Note: it won't compile until you fill in the constructors and methods
 *        (or at least commment out the ones whose return type is non-void).
 *
 *****************************************************************************/


public class RingBuffer {
    private int first;            // index of first item in buffer
    private int last;             // index of last item in buffer
    private int size;             // current number of items of buffer
    private double[] buffer;

    // create an empty buffer, with given max capacity
    public RingBuffer(int capacity) {
       buffer = new double[capacity];  //new array of a max capacity
       first = -1;   ///fix durign enqueue
       last = 0;
       size = 0;   //0s because its an empty queue
       
    }



    
    // return number of items currently in the buffer
    public int getSize() {

	if ((last - first)< 0){   // cant have it return a negative to check if its less than 0 and if so do this
	    return last + size - first ;}
	if(last == first){
	    if(last + first == 0){
		return 0;
	    }
	    else{
		return buffer.length;
	    }
	}
		
	else{
	    
	    return last - first;
	}

    }


    

    // is the buffer empty (size equals zero)?
    public boolean isEmpty() {
	if (getSize() == 0){
	    return true;
	}
	else {
	    return false;
	}
	
    }


    

    // is the buffer full (size equals array capacity)?
    public boolean isFull() {
        if (getSize() == buffer.length){   //if the size = the length of the buffer then its full
	    return true ;
	}
	else {
	    return false;
	}
	
    }

    

    // add item x to the end
    public void enqueue(double x) {
        if (isFull()) { throw new RuntimeException("Ring buffer overflow"); }
	
	buffer[last] = x;
	
	if (first == -1){
	    first = 0;}
	
	if (last == buffer.length - 1){     // maybe use buffer.length to get capacities 
	    last = 0;
	}

	else {
	    
	    last++ ;
	}
	
	

    }


    

    // delete and return item from the front
    public double dequeue() {
        if (isEmpty()) { throw new RuntimeException("Ring buffer underflow"); }

	if (first == -1){         /////////////////// i did this to fix the -1 start
	    first = 0;}
	double tempFirst = buffer[first];        /////(BAD) something is wrong with this because i keep getting out of bounds exception

	//	System.out.println(first);          //print to see

	
	if (first + 1 == buffer.length){            
	    first = 0;}                              //this loops it around

	else {
	    first++;
	}
	//	System.out.println(first);        // print to see .... im trying to call buffer of 10 but cant... why is it trying to call buffer of 10?
	return tempFirst ;               


	
    }
  

    
    // return (but do not delete) item from the front
    public double peek() {
        if (isEmpty()) { throw new RuntimeException("Ring buffer underflow"); }
        return buffer[first] ;                 // returns just the value of the first index without affecting it
    }



    
    // a simple test of the constructor and methods in RingBuffer
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        RingBuffer buffer = new RingBuffer(N);
        for (int i = 1; i <= N; i++) {
            buffer.enqueue(i);
        }
	
	//System.out.println(buffer.getSize()); //my added statement
	
		double t = buffer.dequeue();
		buffer.enqueue(t);
		System.out.println("Size after wrap-around is " 
				   + buffer.getSize());
        while (buffer.getSize() >= 2) {
            double x = buffer.dequeue();
            double y = buffer.dequeue();
            buffer.enqueue(x + y);
        }
        System.out.println(buffer.peek());
    }
}



