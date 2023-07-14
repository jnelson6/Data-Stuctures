/*
 * PianoString.java
 *
 */

package assign3;
import java.lang.Math;

public class PianoString extends InstString {
	private int N; // samplingRate/frequency

  /*creates a RingBuffer of capacity N, and initializes it 
  to represent a string at rest by enqueueing N zeros.
  N is sampR/frequency (rounded to the nearest int)*/
    public PianoString(double frequency) {
    	N = (int) Math.round(sampR/frequency);
        buffer = new RingBuffer(N);
        for (int i = 0; i < N; i++){
            buffer.enqueue(0);
        }
    }



	/* creates a RingBuffer of capacity = to size of the array, 
    and initializes the contents of the buffer to the values in 
    the array. Purpose here is solely for debugging.*/
    public PianoString(double[] init) {
    	int m = init.length;
        buffer = new RingBuffer(m);
        for(int i = 0; i< m; i++){
            buffer.enqueue(init[i]);
        }
    }
   

    /*Replace the N elements in the buffer using f(x) function.
    x is the xth element in the buffer.
    simulates the small localized disturbance of hammer hitting string*/
    public void pluck() {

    	for (int x = 0; x < N; x++){

    		if( x < ((7.0/16.0)*N) || x > ((9.0/16.0)*N) ){
    			buffer.dequeue();
    			buffer.enqueue(0);
    		} 

    		else if( x >= ((7.0/16.0)*N) || x <= ((9.0/16.0)*N) ){
    			buffer.dequeue();
    			buffer.enqueue( 0.25 * Math.sin( 8.0 * Math.PI * ( (x / ((double) N)) - (7.0/16.0))));
    		}
    	}
    }
   


    public void tic() {	
    	double firstS = buffer.dequeue();
        double secondS = sample();
        buffer.enqueue((firstS + secondS) * 0.5 * decR); 
        time ++; 
    }


}





    