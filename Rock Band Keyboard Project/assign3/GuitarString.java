/*
 * GuitarString.java
 *
 */

package assign3;
import java.lang.Math;

public class GuitarString extends InstString{

    public GuitarString(){};
    private int N; // samplingRate/frequency rounded to nearest int

    /* creates a RingBuffer of desired capacity N, and initializes 
    it to represent a guitar string at rest by enqueing N zeros. 
    N is the (SampR/frequency) rounded to nearest int */
    public GuitarString(double frequency) {	
        N = (int) Math.round(sampR/frequency);
        buffer = new RingBuffer(N);
        for (int i = 0; i < N; i++){    //enqueues 0's for N # values as white noise
            buffer.enqueue(0);
        }
         
    }



    /* creates a RingBuffer of capacity = to size of the array, 
    and initializes the contents of the buffer to the values in 
    the array. Purpose here is solely for debugging.*/
    public GuitarString(double[] init) {
        int m = init.length;
        buffer = new RingBuffer(m);
        
        for(int i = 0; i < m; i++){     //enqueues all values of init into the RingBuffer
            buffer.enqueue(init[i]);
        }
    }
   


   /* replace N elements in the buffer with N random values b/w [-.5,+.5]
   use Math.random() to return a double value in the interval [0.0,1.0).
   create a function mapping values from former interval to latter 
   - simulates white noise*/
    public void pluck(){        //simulates plucking a string
        for(int i = 0; i < N; i++) {  
            buffer.dequeue();
            buffer.enqueue(Math.random() - 0.5);
        }
    }



   /* apply the Karplus String update: delete the sample at the front 
   and add the (average of the first 2 samples * the energy decay factor 
   0.996) to the end..... this is 1 iteration of the algorithm*/
    public void tic(){                  //allows note to continue ringing
        time ++; 
        double firstS = buffer.dequeue();
        double secondS = sample();
        buffer.enqueue((firstS + secondS) * 0.5 * decR); 
        
    }



   // public static void main(String[] args){
     //   int N = Integer.parseInt(args[0]);
       // double[] samples = {.2, .4, .5, .3, -.2, .4, .3, .0, -.1, -.3};
        //GuitarString testString = new GuitarString(samples);
        //for (int i = 0; i<N; i++){
          //  int t = testString.time();
            //double sample = testString.sample();
            //System.out.printf("%6d %8.4f\n", t, sample);
            //testString.tic();
        //}
   // }
 




}
