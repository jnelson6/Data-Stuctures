package graph;

/**
 * 
 * @author Julia Nelson
 * Data Structures
 * August 26, 2019
 * Final Project - Floyd_Warshall
 *
 */

public class Graph_FW {
	
	public int n;		// vertices (#rows)
	public int[][] D;	// distance matrix 
	
	public Graph_FW(int _n, int[][] _D) {
		n = _n;
		D = _D;
	}
	
	/**
	 * 
	 * Takes matrix A and updates it with the minimum value of either the sum of 
	 * distance from the start vertex to a midpoint + midpoint to the stop vertex 
	 * or the direct distance b/w the start and stop vertices.
	 * ( drops superscripts of pseudo-code and only required n^2 space )
	 * 
	 * @return A , an update matrix
	 */
	public int[][] Floyd_Warshall(){
						
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					  D[i][j] = Math.min(D[i][j], D[i][k] + D[k][j]);
				}
			}
		}
		return D;	
	}
			
	
	
	/**	
	 * toString to print out the updated matrix/array
	 */
	public String toString() {
		
		String s = "Floyd Warshall - Pairwise Shortest Distance Matrix: \n";
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if( D[i][j] < 0 || D[i][j] > 9) {
					s += D[i][j] + " ";
				}
				else {
					s += " " + D[i][j] + " ";
				}
			}
			s+= "\n";
		}
		return s;
	}
	
	
	
	public static void main(String[] args) {
		
		int inf = Integer.MAX_VALUE/2;	// division helps overflow issue
		
		int n = 5;
		int[][] D = 
			{{0, 3, 8, inf, -4},
			{inf, 0, inf, 1, 7},
			{inf, 4, 0, inf, inf},
			{2, inf, -5, 0, inf},
			{inf, inf, inf, 6, 0}};
		
		Graph_FW gfw = new Graph_FW(n,D);
		gfw.Floyd_Warshall();
		
		System.out.println(gfw.toString());
		}
	}
	


