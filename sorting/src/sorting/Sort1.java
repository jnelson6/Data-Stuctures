package sorting;

import java.util.*;

/**
 * @author Julia Nelson
 * CSIT_503_41_SU19 - DATA STRUCTURES 
 * HW1_Sorting_Sort1
 * July 5, 2019
 *
 */

public class Sort1 {
	/**
	 * insertion_sort 
	 * @param array of integers
	 * @return  'insertion_sort'ed array 
	 */
	public static int[] insertion_sort (int[] array) {
		while(check_sorted(array) == false) {		//while the array(checked w/ helper funct.) is NOT sorted
			for( int j = 2; j< array.length; j++){	//for index j = 2, when its < length of array and increments 
				int key = array[j]; 				//insert A[j] into the sorted seq A[1...j-1]
				int i = j-1;						//index i is 1 less than index j
				while( i>= 0 && array[i]> key){		//while i >= 0 AND its value is > the "key" which is j's value
					array[i+1] = array[i];			//the array shifts index i's value to index i+1
					i = i-1;						//i is then decreased--shifts down an index
				}
			array[i+1] = key;						//then index i+1 is given the value of the key (or value of index j
			
			}
		}
		
		return array;
	}
	
/**
 * merge sort - recursively dived the array and sort, then combine the two sorted sub-arrays
 * @param array
 * @param p
 * @param r
 * @return sorted and merged array
 */
	public static int[] merge_sort (int[] array, int p, int r) {
		if ( p < r){
			int q = (p+r)/2;			//find the middle point to split the array
			merge_sort(array, p,q);		//sort the first half
			merge_sort(array, q+1,r);	//sort the second half
			merge(array, p, q, r);		//merge the 2 array halves
		}
		return array;
	}
	


/**
 * merge - helper to the merge_sort function, helps with the process of merging the two arrays
 * @param array
 * @param p
 * @param q
 * @param r
 * @return the merged array
 */
	public static int[] merge (int[] array, int p, int q, int r) {
		int n1 = q - p +1;	//size of left sub-array
		int n2 = r - q;		//size of right sub-array

		int[] L = new int[ n1+2];	//new Left array - it is +2 because array size is n1+1 so index n1+1 is not an existing 
									//index (array index starts at 0) so we must accommodate for that missing index
		int[] R = new int[ n2+2 ];	//new Right array - +2 as well

		
		for(int i = 1; i<=n1; i++){		// sets i to index the L array
			L[i] = array[p + i - 1];	// sets index L[i] to value from index array[start index of array + index i  - 1 ]
		}								// starts at the beginning of the original array and copies each next into the new array L

		for(int j = 1; j<=n2; j++){		//sets j to index the R array
			R[j] = array[q+j];			//sets index R[j] to value of index [middle + j] of the array
		}								//starts at the middle of the original array and copies each next item into the new array R
		
		
		int inf = Integer.MAX_VALUE;
		L[n1+1] = inf;			//infinity value assigned to the n1+1 indexes so the last value will be the biggest possible 
		R[n2+1] = inf;			//so that in the for-loop, the if-statement can continue to run without reaching out of 
								//bounds in the array index

		
		int i = 1;				// set the index i of Left sub-array back to the first index 1
		int j = 1;				// set the index j of Right sub-array back to the first index 1

		
		for(int k=p; k<=r; k++){			//k is the initial index of the merged sub-array ; k increments
			
			//while(i<=n1 && j<=n2){ //a different approach i attempted but failed
			
			if( L[i] <= R[j]){				//if index ofthe value of L[i] is <= to R[j]
				array[k] = L[i];			//set the value of index array[k] (thats set to the start index p and increments) to value of L[i]
				i = i+1;					// then increment the value of i by 1
			}
			else {							//else if index of the value of L[i] is >  R[j]
				array[k] = R[j];			//set the value of index array[k] (thats set to the start index p and increments) to value of R[j]
				j = j+1;					// then increment the value of j by 1
			}	
			//}
		}									//because its a for-loop it will increment k and run through the code until the array is sorted
		return array;						// the sorted array
	}
	
	



	
	


	/*
	 * n: the size of the output array
	 * k: the maximum value in the array
	 */
	public static int[] generate_random_array (int n, int k) {
		List<Integer> list;
		int[] array;
		Random rnd;
		
		rnd = new Random(System.currentTimeMillis());
		
		list = new ArrayList<Integer> ();
		for (int i = 1; i <= n; i++) 
			list.add(new Integer(rnd.nextInt(k+1)));
		
		Collections.shuffle(list, rnd);
		
		array = new int[n];
		for (int i = 0; i < n; i++) 
			array[i] = list.get(i).intValue();
		
		return array;
	}
	
	/*
	 * n: the size of the output array
	 */
	public static int[] generate_random_array (int n) {
		List<Integer> list;
		int[] array;
		
		list = new ArrayList<Integer> ();
		for (int i = 1; i <= n; i++) 
			list.add(new Integer(i));
		
		Collections.shuffle(list, new Random(System.currentTimeMillis()));
		
		array = new int[n];
		for (int i = 0; i < n; i++) 
			array[i] = list.get(i).intValue();
		
		return array;
	}
	
	/*
	 * Input: an integer array
	 * Output: true if the array is acsendingly sorted, otherwise return false
	 */
	public static boolean check_sorted (int[] array) {
		for (int i = 1; i < array.length; i++) {
			if (array[i-1] > array[i])
				return false;
		}
		return true;
	}
	
	public static void print_array (int[] array) {
		for (int i = 0; i < array.length; i++)
			System.out.print(array[i] + ", ");
		System.out.println();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Insertion sort starts ------------------");
		for (int n = 100000; n <= 1000000; n=n+100000) {
			int[] array = Sort1.generate_random_array(n);
			long t1 = System.currentTimeMillis();
			array = Sort1.insertion_sort(array);
			long t2 = System.currentTimeMillis();
			long t = t2 - t1;
			boolean flag = Sort1.check_sorted(array);
			System.out.println(n + "," + t + "," + flag);
		}
		System.out.println("Insertion sort ends ------------------");
		
		System.out.println("Merge sort starts ------------------");
		for (int n = 100000; n <= 1000000; n=n+100000) {
			int[] array = Sort1.generate_random_array(n);
			//Sort.print_array(array);
			long t1 = System.currentTimeMillis();
			array = Sort1.merge_sort(array, 0, n-1);
			long t2 = System.currentTimeMillis();
			long t = t2 - t1;
			//Sort.print_array(array);
			boolean flag = Sort1.check_sorted(array);
			System.out.println(n + "," + t + "," + flag);
		}
		System.out.println("Merge sort ends ------------------");
	}

}
