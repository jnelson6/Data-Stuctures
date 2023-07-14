/*
 * DrumString.java
 *
 */

package assign3;
import java.lang.Math;


public class DrumString extends InstString{
	private int N; // samplingRate/frequency
   
   /* creates a RingBuffer of desired capacity N, and initializes 
    it to represent a guitar string at rest by enqueing N zeros. 
    N is the (SampR/frequency) rounded to nearest int */
    public DrumString(double frequency) {
    	N = (int) Math.round(sampR/frequency);
        buffer = new RingBuffer(N);
        for (int i = 0; i < N; i++){
            buffer.enqueue(0);
        }
    }

/* creates a RingBuffer of capacity = to size of the array, 
    and initializes the contents of the buffer to the values in 
    the array. Purpose here is solely for debugging.*/
    public DrumString(double[] init) {
    	int m = init.length;
        buffer = new RingBuffer(m);
 
        for(int i = 0; i < m; i++){
            buffer.enqueue(init[i]);
        }
    }


    /*Replace the N elements in the buffer using a square function. 
    To generate our square function by using Math.sin(), with a frequency of 1/N.
	For each value of x in our sine function, if sin(x) > 0 we will change it to a 1. 
	Else, we will change it to a -1*/
    public void pluck() {
    	for (int x = 0; x < N; x++){
    		if( Math.sin( ( 1/(double) N ) * x ) > 0 ) {
    			buffer.dequeue();
    			buffer.enqueue(1);
    		}
    		else{
    			buffer.dequeue();
    			buffer.enqueue(-1);
    		}
    	}
	}
    


	/*
	 apply the given filter with b= 0.5 and s = 5/3
     in the filter we are randomly deciding to use a low pass filter (60%)
     or just re-enqueuing (40%) theres also a 0.5 prob that 
     the item will be negated or not when enqueued
	*/
    public void tic() {
		
		double s1 = buffer.dequeue();
        double s2 = sample();
        double r1 = Math.random();
        double r2 = Math.random();

        if(r1 >= 0.4){			// %60 chance of low-pass
        	if(r2 > 0.5){
        		buffer.enqueue((s1 + s2) * 0.5);
        	}
        	else{
        		buffer.enqueue((s1 + s2) * -0.5);
        	}
        }
        else if (r1 < 0.4){		// %40 chance of re-enqueing
        	if(r2 > 0.5){
        		buffer.enqueue(s1);
        	}
        	else{
        		buffer.enqueue(-1.0 * s1);
        	}

        }
		time ++; 
    }
  
}








