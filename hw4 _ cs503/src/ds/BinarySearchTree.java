package ds;

/**
 * Homework 4
 * Binary Search Tree
 * @author Julia Nelson
 * July 26, 2019
 *
 */

public class BinarySearchTree {
public TreeNode root;

public TreeNode x;
public TreeNode y;
public TreeNode z = new TreeNode();
	
	public BinarySearchTree () {
		root = null;
	}
	
	/**
	 * print values in the left subtree first
	 * then print the key of the root of a subtree
	 * last print values in the right subtree
	 * @param x
	 */
	public void inorder_tree_walk (TreeNode x) {
		if(x != null){
			inorder_tree_walk(x.left);
			System.out.println(x.key);
			inorder_tree_walk(x.right);
		}		
	}
	
	/**
	 * Preorder walk:  print the root key before the values in the subtrees.
	 * @param x
	 */
	public void preorder_tree_walk (TreeNode x) {
		if(x != null){
			System.out.println(x.key);
			preorder_tree_walk(x.left);
			preorder_tree_walk(x.right);
		}		
	}
	
	/**
	 * Postorder walk: print the root after the values in its subtrees.
	 * @param x
	 */
	public void postorder_tree_walk (TreeNode x) {
		if(x != null){
			postorder_tree_walk(x.left);
			postorder_tree_walk(x.right);
			System.out.println(x.key);
		}		
	}
	
	/**
	 * 
	 * @param x
	 * @param k
	 * @return the node with key k if one exists; otherwise, Null
	 */
	public TreeNode search(TreeNode x, int k) {
		if(x == null || k == x.key) {
			return x;
		}
		if(k<x.key) {
			return search(x.left, k );
		}
		else {
			return search(x.right, k);
		}
	}
	
	/**
	 * An iterative version of the tree search
	 * @param k
	 * @return node if one exists
	 */
	public TreeNode iterative_search(int k) {
		x = this.root;
		while(x != null && k!= x.key) {
			if(k < x.key) {
				x = x.left;
			}
			else { 
				x = x.right;	
			}
		}
		return x;
	}
	
	/**
	 * Input: a binary search tree node x
	 * Key idea: the leftmost node has the smallest key
	 * @return the minimum key in the subtree rooted at x
	 */
	public TreeNode minimum() {
		x = this.root;
		while(x.left != null) {
			x = x.left;
		}
		return x;
	}
	
	/**
	 * Input: a binary search tree node x
	 * Key idea: the rightmost node has the smallest key
	 * @return the maximum key in the subtree rooted at x
	 */
	public TreeNode maximum() {
		x = this.root;
		while(x.right != null) {
			x = x.right;
		}
		return x;
	}
	
	/**
	 * If x has a right subtree, find the leftmost node in the subtree
	 * Otherwise, find x’s first ancestor at x’s right side
	 * @param x (a tree node)
	 * @returna tree node that contains the smallest key greater than x.key
	 */
	public TreeNode successor(TreeNode x) {
		if(x.right != null) {					//this ones return may be wrong
			x = x.right;
			return minimum();
		}
		y = x.p;
		while(y != null && x == y.right) {
			x = y;
			y = y.p;
		}
		return y;
	}
	
	
	/**
	 * a new node z with z.key (initialized to input k)
	 * use z.key to find a node y to add z as y’s child
	 * @param k
	 * Output: add z to a tree T
	 */
	public void insert(int k) {
		TreeNode z = new TreeNode(k);
		y = null;
		x = this.root;
		while(x != null) {
			y = x;
			if(z.key <x.key) {
				x = x.left;
			}
			else {
				x = x.right;
			}
		}
		z.p = y;
		if(y== null) {
			this.root = z; 		//tree T was empty
		}
		else if ( z.key < y.key) {
			y.left = z;
		}
		else {
			y.right = z;
		}
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {15, 6, 18, 3, 7, 17, 20, 2, 4, 13, 9};
		BinarySearchTree bst;
		TreeNode n;
		
		bst = new BinarySearchTree();
		for (int i = 0; i < array.length; i++)
			bst.insert(array[i]);
		
		System.out.println("Inorder_tree_walk starts ------------------");
		bst.inorder_tree_walk(bst.root);
		System.out.println("Inorder_tree_walk ends ------------------");
		System.out.print("\n\n");
		
		System.out.println("Search starts ------------------");
		n = bst.search(bst.root, 13);
		System.out.println("found: " + n.key);
		System.out.println("Search ends ------------------");
		System.out.print("\n\n");

		System.out.println("Iterative search starts ------------------");
		n = bst.iterative_search(4);
		System.out.println("found: " + n.key);
		System.out.println("Iterative search ends ------------------");
		System.out.print("\n\n");
		
		System.out.println("Minimum starts ------------------");
		n = bst.minimum();
		System.out.println("Minimum key is " + n.key);
		System.out.println("Minimum ends ------------------");
		System.out.print("\n\n");
		
		System.out.println("Maximum starts ------------------");
		n = bst.maximum();
		System.out.println("Maximum key is " + n.key);
		System.out.println("Maximum ends ------------------");
		System.out.print("\n\n");

		System.out.println("Successor starts ------------------");
		n = bst.successor(bst.root.left.right.right);
		System.out.println("Successor key is " + n.key);
		System.out.println("Successor ends ------------------");
		System.out.print("\n\n");
	}


}
