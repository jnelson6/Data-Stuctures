import java.util.*;

 class stringIterator implements Iterator{
    private String s;

    public stringIterator(String s){
	//for formating, remove leading and trailing whitespace (trim())
	this.s = s.trim();
    }

    public boolean hasNext(){
	// s.length()==0 evaluates to either true or false
	// so lets return the result of that evaluation
	return s.length()!=0;
	  
    }

    public String next(){
	String retString = "";
	int spaceIndex = s.indexOf(' ');
	if(spaceIndex != -1){
	    retString = s.substring(0,spaceIndex);
	    s = s.substring(spaceIndex+1);
	    s= s.trim(); //in case there were several spaces, remove them all;
	}else{
	    retString = s;
	    s = "";
	}

	return retString;
    }

}

public class StackMath{

    //returns true if s is +, -, /, or *; false otherwise
    private static boolean isOp(String s){
	return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }

    //tries to convert a string to an int, throws error otherwise
    private static double stringToDouble(String s){
	try{
	    return Double.parseDouble(s);
	}catch(NumberFormatException nfe){
	    throw new IllegalArgumentException("Error! Invalid character in formula!");
	}
    }
    
    public static double processEquation(String eq){
	
	stringIterator si = new stringIterator(eq);
	Stack<Double> stack = new Stack<Double>();

	while(si.hasNext()){
	    
	    String s = si.next();
	    
	    if(isOp(s)){
		try{  
		    double a1 = stack.pop();
		    double a2 = stack.pop();
		
		switch(s){
		case "*":
		    stack.push(a1*a2);
		    break;
		case "-":
		    stack.push(a2-a1);
		    break;
		case "+":
		    stack.push(a1+a2);
		    break;
		case "/":
		    stack.push(a2/a1);
		    break;
		}
		} castch(EmptyStackException ese){
		    throw new IllegalArgumentException("Invalid form for equation");
		}
	    }else{
		stack.push(stringToDouble(s));
	    }
	   
	}

	return stack.pop();
    }
    
    
    public static void main(String [] args){
	System.out.println(processEquation(args[0]));
    }

}
