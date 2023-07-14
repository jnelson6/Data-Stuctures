/*
 * Instrument.java
 */
package assign3;

public abstract class Instrument {
    
    protected InstString[] strings;
    public final double CONCERT_A = 440.0;
    
    /*Given an index, pluck the appropriate string for that instrument.*/
    public void playNote(int i) {
    	strings[i].pluck();		
    }



    /*Lets the notes keep ringing on the instrument. 
    Calls tic () and adds up the sample for each string. 
    Returns the sum of all string samples */
    public double ringNotes(){
    	double sum = 0;
    	for(int i=0; i< strings.length; i++){
    		strings[i].tic();						//time (or tics) pass for the nate played
    		sum += strings[i].sample();}
    	return sum;
    }



}
