/*
 * Drum.java
 */
package assign3;
import java.lang.Math;

public class Drum extends Instrument{

    public Drum(int numNotes){
    	strings = new InstString[numNotes];
    	for(int i=0; i< numNotes; i++){
    		strings[i] = new DrumString(Math.pow(2,(i-24)/12.0) * CONCERT_A);
    	}
    }		    
}
