package homework4;

import java.util.Random;

/**
 * A class to represent a treap, that is, a BST with node placement
 * randomized by probabilistic heap-like priorities
 * @author Julia Nelson I pledge my honor that I have abided by the Stevens Honor System
 */
public class Treap<E extends Comparable<E>> extends BinarySearchTree<E> {
    
	protected static class Node<E> {
		public E data; // key for the search
		public int priority; // random heap priority
		public Node<E> left;
		public Node<E> right;

		/** Creates a new node with the given data and priority. The
		 *  pointers to child nodes are null. Throw exceptions if data
		 *  is null. 
		 */
		public Node(E data, int priority) {
			if (data == null){
				throw new IllegalArgumentException("Node Data cannot be null"); // is this the right exception????
			}
	    		this.data = data;
	    		this.priority = priority;
	    		this.right = null;
	    		this.left = null;
	    	}
	
		public Node<E> rotateRight() { /// can this just be this? or should it be root or this.root
	    			Node<E> newroot = this.left;
	    			this.left = newroot.right;
	    			newroot.right = this;
	    			return newroot;	 // should this be the new root or a reference to the old one???
	    		}
		 


		public Node<E> rotateLeft() {	
				Node<E> newroot = this.right;
	    			this.right = newroot.left;
	    			newroot.left = this;
	    			return newroot; // should this be the new root or a reference to the old one???
					/// or should these just return null after doing the rotation?
	    	}   
		
		@Override
        public String toString(){
            return "(key=" +this.data+", priority=" +this.priority+ ")";
        }
	}
	


    private Random priorityGenerator;
    private Node<E> root;

    /** Create an empty treap. Initialize {@code priorityGenerator}
     * using {@code new Random()}. See {@url
     * http://docs.oracle.com/javase/8/docs/api/java/util/Random.html}
     * for more information regarding Java's pseudo-random number
     * generator. 
     */
    
    public Treap(){
    		this.priorityGenerator = new Random();
    		this.root = null;			
    	//root.left = root.right = null;
    	//priority = priorityGenerator;

    }


    /** Create an empty treap and initializes {@code
     * priorityGenerator} using {@code new Random(seed)}
     */
    public Treap(long seed) {
		priorityGenerator = new Random(seed);
		root = null;			
    	//root.left = root.right = null;
    	//priority = priorityGenerator;

    }


    protected boolean addReturn;
    
	
	
    public boolean add(E key){
    		if(key == null) {
    			throw new NullPointerException("key cant be null");
    		}
    		int priority = priorityGenerator.nextInt();
		root = add(root, key, priority);
		return addReturn;
    }


    private Node<E> add(Node<E> localroot, E key, int priority){
    		if (localroot == null){ // key is not in the tree; insert it.
           	addReturn = true;
           	return new Node<E>(key, priority);
       	}
    		else if (key.compareTo(localroot.data) == 0) { 	// key is equal to localroot.data
    			addReturn = false;
    			//return localroot;
        }
    		
    		else if (key.compareTo(localroot.data) < 0) { 	// key is less than localroot.data
           	
    			localroot.left = add(localroot.left, key, priority);		//should this call the first boolean add?/////////////
           	if (localroot.priority > localroot.left.priority){  		//sign might need to be reversed 
           		localroot = localroot.rotateRight();
           	}
           	addReturn = true;
        }
    		else if (key.compareTo(localroot.data) > 0) {	// key is greater than localroot.data
            
    			localroot.right = add(localroot.right, key, priority);	//should this call the first boolean add?//////////////
			if (localroot.priority > localroot.right.priority){ 		// sign might need to be flipped
           		localroot = localroot.rotateLeft();
           	}
			
			addReturn = true;
        }
        return localroot;

	}

	public boolean add(E key, int priority){
		if (key == null){
			throw new NullPointerException("the key can't be null");
		}
		if (find(key) == null) {
			root = add(root, key, priority);
			return true;
		}
		else {
			return false;
		}
		}
	
	
	/////////////////////////////////////
	/*
	 * deletes a node from the treap
	 * @param key, the key of the node you want to delete
	 * @return, returns true if successfully deleted
	 */
	
	public E delete(E key) {
		
			Node<E> curr;
			curr = root;		//root at the top of the treap
			Node<E> supR;
			supR= null;		//there is no node above the main root
			
			while (curr != null){
					int result = key.compareTo(curr.data);
					if(result > 0) {
						supR = curr;
						curr = curr.right;
					}
					else if(result < 0) {
						supR = curr;
						curr = curr.left;	
					}
					else{
						if(key.compareTo(this.root.data) == 0) {
							if(this.root.left == null){
								this.root = this.root.right;
								return curr.data;
							}
							else if (this.root.right == null){
								this.root=this.root.left;
								return curr.data;
							}
							
							if(this.root.right.priority > this.root.left.priority){
									this.root = this.root.rotateLeft();
									supR = root;
							}
							else if (this.root.right.priority < this.root.left.priority){
									this.root = this.root.rotateRight();	
									supR = root;
								}
						}
						
						else if (curr.left != null && curr.right != null) {
							if(curr.right.priority < curr.left.priority){
								if(supR.left == curr) {
									supR.left = curr.rotateRight();
									supR = supR.left;
									}
								else{
									supR.right = curr.rotateRight();
									supR = supR.right;
								}
							}
							else if(curr.right.priority > curr.left.priority) {
								if(supR.right == curr){
									supR.right = curr.rotateLeft();
									supR = supR.right;
									
								}
								else if(supR.left == curr) {
									supR.left = curr.rotateLeft();
									supR = supR.left;
									}
								
								}
						}
						
						else if(curr.right == null && curr.left == null){
							result = supR.data.compareTo(curr.data);
							if(result < 0){
								supR.right = null;
								return curr.data;
							}
							else if (result > 0){
								supR.left = null;
								return curr.data;
							}
						}
						
						else if (curr.right != null && curr.left == null) {
							if(supR.right == curr){
								supR.right = curr.rotateLeft();
								supR = supR.right;
							}
							else if(supR.left == curr){
								supR.left = curr.rotateLeft();
								supR = supR.left;
							}
							
						}
						
						else if (curr.right == null && curr.left != null){
							if(supR.left == curr){
								supR.left = curr.rotateRight();
								supR = supR.left;
							}
							else if(supR.right == curr){
								supR.right = curr.rotateRight();
								supR = supR.right;
							}
						}
					}	
				}
				if (find(key) == null) {		
					return curr.data;
				}
				return curr.data;
			}
		





    private E find(Node<E> root, E key) { // SLIDES HAVE NOTES ON THE FIND METHOD
    		if (root == null) {
    			return null;
    		}
    		int result = key.compareTo(root.data);
        	if (result == 0 ){
        		return root.data;
        	}
        	else if (result <0) {
        		return find(root.left, key);
        	}
        	else{	// if (result > 0) 
        		return find(root.right, key);
        	}    			
    	}
    

    public E find(E key) {
    		if (key == null) {
    			throw new NullPointerException("the key can't be null");
    		}
    		return find(root, key);
    }


    public String toString(){
    		StringBuilder sb = new StringBuilder();
    		preOrderTraverse(root, 1, sb);
    		return sb.toString();
    }

    private void preOrderTraverse(Node<E> node, int depth,StringBuilder sb){
    		for (int i = 1; i < depth; i++){
    			sb.append("  ");
    		}
    		if (node == null){
    			sb.append("null\n");
    		} 
    		else{
    			sb.append(node.toString());
    			sb.append("\n");
    			preOrderTraverse(node.left, depth + 1, sb);
    			preOrderTraverse(node.right, depth + 1, sb);
    		}
    }

   


    }
