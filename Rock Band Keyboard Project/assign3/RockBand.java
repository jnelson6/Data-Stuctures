/*
 * RockBand.java
 *
 */

package assign3;
import cos126.StdDraw;
import cos126.StdAudio;
import java.io.IOException;
import java.util.ArrayList;

public class RockBand {

	//keyboards
  //  String guitarBassKeyboard ="`1234567890-=qwertyuiop[]\\asdfghjkl;'";
   // String pianoKeyboard = "~!@#$%^&*()_+QWERTYUIOP{}|ASDFGHJKL:\"";
  //  String drumKeyboard = "ZXCVBNM<>?zxcvbnm,.";
  
    public static void main(String[] args) {
    	//create an array of instruments 
    	Instrument[] instruments = new Instrument[4];			//creates new array of 4 instrument type items 
    	instruments[0] = new Guitar(37);
    	instruments[1] = new Piano(37);
    	instruments[2] = new Bass(37);
    	instruments[3] = new Drum(19);

    	boolean lowMode = false;			// when true the Bass keyboard is activated

    	String guitarBassKeyboard ="`1234567890-=qwertyuiop[]\\asdfghjkl;'";
    	String pianoKeyboard = "~!@#$%^&*()_+QWERTYUIOP{}|ASDFGHJKL:\"";
    	String drumKeyboard = "ZXCVBNM<>?zxcvbnm,.";
    	// Map key presses to notes/instruments
    	


    	// check if the user has typed a key; if so, process it   
    	while (true){
    		double samples = 0;	
    		if (StdDraw.hasNextKeyTyped()) {
            	char key = StdDraw.nextKeyTyped();
            	
            	if(key == '\n'){ lowMode = !lowMode; }

            	int iGB = guitarBassKeyboard.indexOf(key);
            	if (iGB > -1){
            		if(!lowMode){ 
            			instruments[0].playNote(iGB); 
            			
            		}
            		else{
            			instruments[2].playNote(iGB); 
            			
            		}
            	}

            
            	int iP = pianoKeyboard.indexOf(key);

            	if(iP > -1){ 
            		instruments[1].playNote(iP);
            		
            	}

            	
            	int iD = drumKeyboard.indexOf(key);
            	if (iD > -1){ 
            		instruments[3].playNote(iD); 
            			
            			}

            		}

            samples += instruments[0].ringNotes();
            samples += instruments[1].ringNotes();
            samples += instruments[2].ringNotes();
            samples += instruments[3].ringNotes();

            						



            StdAudio.play(samples);

        }

            
    }
}

            
            	


            


    


            	


