package sorting;

import java.util.*;


/**
 * @author Julia Nelson
 * CSIT_503_41_SU19 - DATA STRUCTURES 
 * HW2_Sorting_Sort2
 * July 12, 2019
 *
 */

public class Sort2 {
	
/**
 * Divide-and-Conquer -> Best in practice
 * Time Complexity of O(n^2)
 * @param array
 * @param p
 * @param r
 * @return sorted array
 */
	public static int[] quick_sort( int[] array, int p, int r) {
		
		if(p<r){								
			int q = partition(array,p,r);		// q = i+1 from partition 
			quick_sort(array,p,q-1);
			quick_sort(array,q+1,r);
		}
		return array;
	}
	
	
	
	
	/**
	 * 
	 * @param array
	 * @param p
	 * @param r
	 * @return i+1
	 */
	public static int partition(int[] array, int p, int r){
		
		int x = array[r];
		int i = p-1;
		for(int j = p; j < r; j++) {		//j=p and increments until j is no long < r
			if(array[j] <= x) {
				i = i+1;
				int temp = array[i];		//swapping array[i] with array[j]
				array[i] = array[j];		//~
				array[j] = temp;			//~
			}
		}
		
		int temp2 = array[i+1];				//swapping array[i+1] with array[r]
		array[i+1] = array[r];				//~
		array[r] = temp2;					//~
		
		return i+1;
		
	}
	
	
	
	
	/**
	 * sorts the values in the range of [0...k] 
	 * has complexity of O(n+k)
	 * @param array
	 * @param k
	 * @return sorted array B
	 */
	public static int[] counting_sort(int[] array, int k){
		
		int[] B = new int[array.length];				//new array to be used as storage for the array keys -> must be length of the array 
		int[] C = new int[k+1];							//new array including k so it must be size k+1 to accomodate 
		
		
		for(int i = 0; i <= k; i++) {					//change C[i] through C[k] to 0
			C[i] = 0;
		}
		
		for(int j = 1; j < array.length; j++) {		
			C[array[j]] = C[array[j]] + 1;				//C[i] now contains # of elems = i.
		}
		
		for(int i = 1; i <= k; i++) {
			C[i] = C[i] + C[i-1];							//C[i] now contains # of elems <= i.
		}
		
		for(int j = array.length -1 ; j >= 0; j--) {	//copies sorted into B
			B[C[array[j]]] = array[j];			
			C[array[j]] = C[array[j]] - 1;
		}
		
	
		return B;										//returns sorted
	}
	
	
	
	
	
	
	
	
	public static int[] generate_random_array (int n, int k){
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
	
	public static int[] generate_random_array (int n){
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
		int k = 10000;
		
		System.out.println("Quick sort starts ------------------");
		for (int n = 100000; n <= 1000000; n=n+100000) {
			int[] array = Sort2.generate_random_array(n);
			long t1 = System.currentTimeMillis();
			array = Sort2.quick_sort(array, 0, n-1);
			long t2 = System.currentTimeMillis();
			long t = t2 - t1;
			boolean flag = Sort2.check_sorted(array);
			System.out.println(n + "," + t + "," + flag);
		}
		System.out.println("Quick sort ends ------------------");
		
		System.out.println("Counting sort starts ------------------");
		for (int n = 100000; n <= 1000000; n=n+100000) {
			int[] array = Sort2.generate_random_array(n, k);
			long t1 = System.currentTimeMillis();
			array = Sort2.counting_sort(array, k);
			long t2 = System.currentTimeMillis();
			long t = t2 - t1;
			boolean flag = Sort2.check_sorted(array);
			System.out.println(n + "," + t + "," + flag);
		}
		System.out.println("Counting sort ends ------------------");
	}
}
