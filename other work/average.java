public class average{

    //return the sum of all arguments
    public static int getNumSum(String [] args){
	int a = 0;
	int b = 0;
	int c = 0;
	int d = 0;
	int e = 0;
	int f = 0;
	int g = 0;
	int h = 0;
	int i = 0;
	int j = 0;

	if (args.length >=1) {
	    a = Integer.parseInt(args[0]);
	}
	
	if (args.length >=2) {
	    b = Integer.parseInt(args[1]);
	}

	if (args.length >=3) {
	    c = Integer.parseInt(args[2]);
	}

	if (args.length >=4) {
	    d = Integer.parseInt(args[3]);
	}

	if (args.length >=5) {
	    e = Integer.parseInt(args[4]);
	}
	
	if (args.length >=6) {
	    f = Integer.parseInt(args[5]);
	}

	if (args.length >=7) {
	    g = Integer.parseInt(args[6]);
	}
	
	if (args.length >=8) {
	    h = Integer.parseInt(args[7]);
	}

	if (args.length >=9) {
	    i = Integer.parseInt(args[8]);
	}
	
	if (args.length >=10) {
	    j = Integer.parseInt(args[9]);
	}


	return  a+b+c+d+e+f+g+h+i+j;

    }

    //given numbers, return average, using arrays
    public static double getAvgArrays(String [] args){
	int sum = 0;
	for(int i = 0; i < args.length; i++){
	    sum += Integer.parseInt(args[i]);
	}
	double avg = (double)sum/args.length;
	return avg;

    }


   
    public static void main (String [] args) {
	int numNums = args.length;
	int sumNums = getNumSum(args);
	double average = (double)sumNums/numNums;
	System.out.println(average);
	System.out.println(getAvgArrays(args));

    }

}
