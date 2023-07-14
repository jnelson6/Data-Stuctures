/*
 * Piano.java
 */

package assign3;


public class Piano extends Instrument{

    private static InstString[][] pStrings; //2D array of strings
    
    public Piano(int numNotes){
    	pStrings = new InstString[numNotes][3]; // should this be 2 or 3

    	for(int i=0; i< numNotes; i++){
    		pStrings[i][0] = new PianoString((CONCERT_A * Math.pow(2,(i-24)/12.0)) - 0.45);
    		pStrings[i][1] = new PianoString(CONCERT_A * Math.pow(2,(i-24)/12.0));
    		pStrings[i][2] = new PianoString((CONCERT_A * Math.pow(2,(i-24)/12.0)) + 0.45);
    	}
    }

  
  	//Overrides the one in Instrument
  	@Override
    public void playNote(int index){
    	pStrings[index][0].pluck();
    	pStrings[index][1].pluck();
    	pStrings[index][2].pluck();
	 
    }
    
    //Overrides the one in Instrument
    @Override
    public double ringNotes(){
    	double sum = 0;
    	for(int i=0; i< pStrings.length; i++){
    		pStrings[i][0].tic();		
    		pStrings[i][1].tic();
    		pStrings[i][2].tic();				
    		sum += (pStrings[i][0].sample() + pStrings[i][1].sample() + pStrings[i][2].sample());
    	}
    	return sum;
    }
			    

}
