/*
 * Bass.java
 */
package assign3;
import java.lang.Math;

public class Bass extends Instrument{


    public Bass(int numNotes){
		strings = new InstString[numNotes];
    	for(int i=0; i< numNotes; i++){
    		strings[i] = new GuitarString(Math.pow(2,(i-48)/12.0) * CONCERT_A);
    	}
    }		    
}
