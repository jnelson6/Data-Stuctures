import java.lang.Number;

public class RationalNumber{
	

    public RationalNumber(int p,int q){
	if (q == 0){
	    IllegalArgumentException exception
		= new IllegalArgumentException("Denominator = 0, Not a Number");
	    throw exception;
	}
    }
    

    private int gcd(int p,int q){
	if (p == 0) {
	    return q;
	}
	else if (q == 0) {
	    return p;
	}
	if (p == 1) {
	    return 1;
	}
	else if (q == 1) {
	    return 1;
	}
	int holder;
	while (q != 0) {
	    holder = p % q;
	    p = q;
	    q = holder;
	}
	return p;
    }
    
    public void simplify(int p, int q) {
	
	int gcd = gcd(p,q);
	int r = p / gcd;
	int s = q / gcd;
	
	return new RationalNumber(r,s);
        
    }
    
    public void add(RationalNumber b){
	RationalNumber addTo = new RationalNumber(simplify(RationalNumber(b)));
	
	return  RationalNumber((p.this.RationalNumber * q.addTo.RationalNumber) +
				  (p.addTo.RationalNumber * q.this.RationalNumber),
				  (q.this.RationalNumber * q.addTo.RationalNumber));
    }

    public void multiply(RationalNumber b){

	RationalNumber mult  = new RationalNumber(simplify(RationalNumber(b)));
	
	return new RationalNumber((p.this.RationalNumber * p.mult.RationalNumber),
				  (q.this.RationalNumber * q.mult.RationalNumber));
    }
	    

    public double doubleValue(){
	return doubleValue(p.RationalNumber / q.RationalNumber);
    }
	
	
	
}
    

    

    
