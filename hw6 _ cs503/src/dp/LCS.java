package dp;

/**
 * 
 * @author Julia Nelson
 * Friday August 9, 2019
 * Homework 6
 *
 */

public class LCS {
	
	// use 'enum' to specify the directions in the table
	enum Direction{
		UPLEFT, LEFT, UP;
	}
	
	public int lcs_length(String X, String Y){
		
		// types of parameters need to be defined as String
		// int needs to be returned for the length
		
		// length is a String method
		int m = X.length();
		int n = Y.length();
		
		// Tables are specified as 2D arrays
		Direction[][] b = new Direction[m+1][n+1];
		int[][] c = new int[m+1][n+1];
		
		
		for (int i = 1; i<=m; i++) {
			c[i][0] = 0;
		}
		
		for (int j = 0; j<=n; j++) {
			c[0][j] = 0;
		}
		for (int i = 1; i<=m; i++) {
			for (int  j = 1; j<=n; j++) {
				if(X.charAt(i) == Y.charAt(j)) {
					c[i][j] = c[i-1][j-1]+1;
					b[i][j] = Direction.UPLEFT;
				}
				else if(c[i-1][j]>= c[i][j-1]) {
					c[i][j] = c[i-1][j];
					b[i][j] = Direction.UP;
				}
				else {
					c[i][j] = c[i][j-1];
					b[i][j] = Direction.LEFT;
				}
			}
		}
		return c[m][n];	//return the length of the longest common subsequence
	
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(LCS.lcs_length("ABCBDAB", "BDCABA"));
		System.out.println(LCS.lcs_length("ACCGGTCGAGTGCGCGGAAGCCGGCCGAA", "GTCGTTCGGAATGCCGTTGCTCTGTAAA"));
		}
	}
	



