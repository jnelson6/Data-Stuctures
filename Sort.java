package sorting;

import java.util.*;

public class Sort1 {
	
	
	public static int[] insertion_sort (int[] array) {
		
		while(check_sorted(array) == false) {
			for( int j = 2; j< array.length; j++){
			int key = array[j]; 	//insert A[j] into the sorted seq A[1...j-1]
			int i = j-1;
				while( i>= 0 && array[i]> key){
					array[i+1] = array[i];
					i = i-1;
				}
			array[i+1] = key;
			
			}
		}
		
		return array;
	
	}

	
	public static int[] merge_sort (int[] array, int p, int r) {
		if (p < r){
			int q = (p+r)/2;
			merge_sort(array, p,q);
			merge_sort(array, q+1,r);
			merge(array, p, q, r);
		}
		return array;
	}
	

	public static int[] merge (int[] array, int p, int q, int r) {
		int n1 = q - p +1;
		int n2 = r - q;

		int[] L = new int[ n1+1 ];
		int[] R = new int[ n2+1 ];

		for(int i = 1; i<n1; i++){
			L[i] = array[p + i - 1];
		}


		for(int j = 1; j<n2; j++){
			R[j] = array[q+j];
		}
		L[n1+1] = Integer.MAX_VALUE;
		R[n2+1] = Integer.MAX_VALUE;

		int i = 1;
		int j = 1;

		for(int k=p; k<=r; k++){
			if( L[i] <= R[j]){
				array[k] = L[i];
				i = i+1;
			}
			else{
				array[k] = R[j];
				j = j+1;
			}
		}
		return array;
	}
	



/////////////////////END STUDENT CODE///////////////////////////
	


	
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
