/*
 * Guitar.java
 */
package assign3;
import java.lang.Math;

public class Guitar extends Instrument{

/*This constructor will initialize our abstract class Instruments strings variable 
to size numNotes, and create numNotes amount of GuitarStringsfor that instrument, 
with each string having the appropriate frequency (see formula below). All of these 
strings will be stored in that strings array. Each string i should be assigned a 
frequency using the formula: 
CONCERT_A * 2^(i−24)/12.0 with i going from 0 to numNotes,  CONCERT A = 440.0 (Hz)*/
    public Guitar(int numNotes){
    	strings = new InstString[numNotes];
    	for(int i=0; i< numNotes; i++){
    		strings[i] = new GuitarString(Math.pow(2,(i-24)/12.0) * CONCERT_A);
    	}
    }		    
}
