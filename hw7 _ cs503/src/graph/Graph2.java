package graph;
import java.util.PriorityQueue;

/**
 * 
 * @author Julia Nelson
 * Data Structures
 * August 23, 2019
 * Homework 8
 *
 */

public class Graph2 {
	int n;
	int[][] A;
	int[] d;	// shortest distance
	int[] pre;  
	
	/**
	 * @param args
	 */
	
	public Graph2 () {
		
	}
	
	
	public Graph2 (int _n, int[][] _A) {
		n = _n;
		A = _A;
		d = new int[n];
		pre = new int[n];
	}
	
	public void initialize_single_source(int s) {
		for (int i = 0; i < n; i++)
			d[i] = Integer.MAX_VALUE;
		d[s] = 0;
	}
	
	public void relax (int u, int v) {
		if (d[v] > (d[u] + A[u][v])) {
			d[v] = d[u] + A[u][v];
			pre[v] = u;				//added line to relax
		}
	}
	
	
	
	
	/**
	 * a shortest path contains at most n nodes that are connected by n-1 edges, 
	 * so for each destination, relax at most n-1 times.
	 * 
	 * @param s
	 * @return boolean
	 */
	public boolean bellman_ford (int s) {
		initialize_single_source(s);
		int num = A.length;	
		for (int i = 1; i <= num-1; i++ ) {			// start to iterate from 1 to |G.V|-1
			for(int u = 0; u <= num-1; u++) {		// each edge (u,v) exists in G.E
				for (int v = 0; v <= num -1; v++) {	
					if (A[u][v]!= 0) {				// is the edge present b/w u & v ?
						relax(u, v);
					}
				}	
			}
		}
		for (int u = 0; u <= num-1; u++) {		//  each edge (u,v)
			for (int v = 0; v <= num-1; v++) {
				if(A[u][v] != 0) {				// is edge is present?
					if (d[v]>d[u]+A[u][v]) {	// v.d > u.d + w(u,v)
						return false;
					}
				}
			}
		}
		return true;
	}
	
	
	
	
	
	
	/**
	 * Condition: non-negative edge weight
	 * Maintain a set S of vertices whose final shortest-path weights have 
	 * been determined. In each iteration, add one node to S
	 * 
	 * @param s
	 */
	public void dijkstra (int s) {			
		initialize_single_source(s);
		PriorityQueue<Integer> Q = new PriorityQueue<>((a,b) -> d[a] - d[b]);
		for (int i = 0; i < n; i++) {
			Q.add(i);
			pre[i] = -1;
		}
		
		while(!Q.isEmpty()) {
			int node = Q.poll();
			
			for(int i=0; i <n; i++) {
				if (A[node][i] != 0) {
					relax(node, i);
					if (Q.contains(i)) {
						Q.remove(i);
						Q.add(i);
					}
				}
			}
		}
	}
	
	
	
public void display_distance () {
	for (int i = 0; i < n; i++)
		System.out.println(i + ": " + get_path(0,i) + ", distance: " + d[i]); 	// modified code
}

/**
 * Organizes the shortest path for each node as a string
 * @param s
 * @param g
 * @return toString of predecessors and paths
 */
private String get_path(int s, int g) {
	int current = g;
	
	StringBuilder sb = new StringBuilder();
	while (current != s) {
		sb.insert(0, " -> " + current);
		current = pre[current];
	}
	
	sb.insert(0, "0");
	return sb.toString();
}
			
				
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 5;
		int[][] A = {
		{0, 6, 0, 7, 0},
		{0, 0, 5, 8, -4},
		{0, -2, 0, 0, 0},
		{0, 0, -3, 0, 9},
		{2, 0, 7, 0, 0}
		};
		Graph2 g1 = new Graph2(n, A);
		g1.bellman_ford(0);
		g1.display_distance();
		
		System.out.println("-----------------------");
		
		int[][] B = {
		{0, 10, 0, 5, 0},
		{0, 0, 1, 2, 0},
		{0, 0, 0, 0, 4},
		{0, 3, 9, 0, 2},
		{7, 0, 6, 0, 0}
		};
		Graph2 g2 = new Graph2(n, B);
		g2.dijkstra(0);
		g2.display_distance();
	}


	
	
	
}
