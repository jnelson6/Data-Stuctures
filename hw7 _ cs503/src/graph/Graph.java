package graph;

/**
 * @author Julia Nelson
 * CSIT 503
 * Friday August 16, 2019
 * Homework 7 - BFS
 * 
 */

import java.util.LinkedList;
//import ds.Queue;

public class Graph{
	public int n;		//number of vertice
	public int[][] A;	//the adjacency matrix
	private final int WHITE = 2;
	private final int GRAY = 3;
	private final int BLACK = 4;

	public Graph (){
		n = 0;
		A = null;
	}

	public Graph (int _n, int[][] _A){
		this.n = _n;
		this.A = _A;
	}
	
	/*
	 * Input: s denotes the index of the source node
	 * Output: the array dist, where dist[i] is the distance 
	 *			 between the i-th node to s
	 * 
	 * _____HINTS_____
	 *  1--Colors have been defined in the class for you to use
	 *  2--IGNORE the parent (pi) in your code
	 *  3--In the matrix-adjacency represent, each node is just an integer... 
	 *        -CANNOT do..
	 *					u.color = WHITE;
	 *				for a node u. 
	 *
	 *		  -SUGGESTS!! to create 
	 *				-an integer array to represent the colors of the nodes,
	 *				-another array to represent the distance d for the nodes
	 *
	 *	4--To use Enqueue and Dequeue : you can use your prev. Queue from HW3
	 *			or you can use the add() and remove() function of Java LinkedList
	 *__________________________________________________________________
	 */
	
	public int[] bfs(int s){
		

		int[] colors = new int[n];		//an integer array to represent the colors of the nodes
		int[] distance = new int[n];	//array to represent the distance d for the nodes
		
		for(int i = 0; i < colors.length; i++) {
			colors[i] = this.WHITE;
			distance[i] = Integer.MAX_VALUE;
		}
		colors[s] = this.GRAY;
		distance[s] = 0;
		
		LinkedList<Integer> queue = new LinkedList<>();		//Use of Hint #4
		queue.add(s);
		
		while(!queue.isEmpty()){
			int u = queue.remove();
			for(int i = 0; i<n; i++) {
				if(A[u][i]==1) {
					if(colors[i] == this.WHITE) {
						colors[i] = this.GRAY;
						distance[i] = distance[u] + 1;
						queue.add(i);
					}
				}
			}
			colors[u] = this.BLACK;
		}
		return distance;	// SHOULD RETURN an integer Array that records the minimum
						//  distance between every node to the source s	
	}
	
	
	
	/**
	 * 
	 * EXTR CREDIT
	 * For each vertex u, 
	 * u.pre: the time that u turns from WHITE to GRAY 
	 * u.post: the time that u turns from GRAY to BLACK 
	 * u.color:
	 * WHITE: u has not been discovered yet
	 * GRAY: u has been discovered, but not its neighbors 
	 * Black: u and its neighbors have been discovered.
	 * 			It will not be visited any more.
	 * 
	 */
	public void dfs() {
		int[] colors = new int[n];
		int[] pre = new int[n];
		int[] post = new int[n];
		
		for(int i=0; i<n; i++) {
			colors[i] = this.WHITE;
		}
		
		
		int time  = 0;
		for(int i = 0; i <n; i++) {
			if(colors[i]== this.WHITE) {
				time = dfs_visit(i, colors, time, pre, post);
			}
		}
		
		System.out.println("Depth First Search");
		for (int i = 0; i < n; i++) {
				System.out.println(i + ": " + pre[i] +"/" + post[i]);	//to print the "array[i]" equiv. 
		}	
	}
	
	public int dfs_visit(int u, int[] colors, int time, int[] pre, int[] post) {
		time = time +1;
		pre[u] = time;
		colors[u] = this.GRAY;
		
		for (int i = 0; 1<n; i++) {
			if(A[u][i] ==1 && colors[i] == this.WHITE) {
				time = dfs_visit(i, colors, time, pre, post);
			}
		}
		colors[u] = this.BLACK;
		time = time +1;
		post[u] = time;
		return time;
	}
	
	
	
	
	public void print_array (int[] array) {
		for (int i = 0; i < array.length; i++)
			System.out.println(i + ": " + array[i]);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 8;
		int[][] A = 
			{{0, 1, 0, 0, 1, 0, 0, 0},
			{1, 0, 0, 0, 0, 1, 0, 0},
			{0, 0, 0, 1, 0, 1, 1, 0},
			{0, 0, 1, 0, 0, 0, 1, 1},
			{1, 0, 0, 0, 0, 0, 0, 0},
			{0, 1, 1, 0, 0, 0, 1, 0},
			{0, 0, 1, 1, 0, 1, 0, 1},
			{0, 0, 0, 1, 0, 0, 1, 0}};
		Graph g = new Graph(n, A);
		g.print_array(g.bfs(1));
		
	}


}
