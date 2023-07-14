/*
 * InstString.java
 *
 */

package assign3;


public abstract class InstString{

	RingBuffer buffer;
    int time = 0;
    final double sampR = 44100; //sample rate
    final double decR = 0.996; // energy decay factor

    /* To be implemented by subclasses*/
    public abstract void pluck();
    public abstract void tic();
    
    
    /* returns the front value of our ring buffer*/
    public double sample(){
    	return buffer.peek();

    }


    public int time(){
    	return time;

    }

}
