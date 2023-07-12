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
		if ( p < r){
			int q = (p+r)/2;			//find the middle point to split the array
			merge_sort(array, p,q);		//sort the first half
			merge_sort(array, q+1,r);	//sort the second half
			merge(array, p, q, r);		//merge the 2 array halves
		}
		return array;
	}
	



	
	public static int[] merge (int[] array, int p, int q, int r) {
		int n1 = q - p +1;	//size of left sub-array
		int n2 = r - q;		//size of right sub-array

		int[] L = new int[ n1+2];	//new Left array
		int[] R = new int[ n2+2 ];	//new Right array

		for(int i = 1; i<=n1; i++){
			L[i] = array[p + i - 1];
		}


		for(int j = 1; j<=n2; j++){
			R[j] = array[q+j];
		}
		
		
		int inf = Integer.MAX_VALUE;
		L[n1+1] = inf;
		R[n2+1] = inf;

		
		int i = 1;
		int j = 1;

		
		for(int k=p; k<=r; k++){
			//while(i<=n1 && j<=n2) {
				if( L[i] <= R[j]){
					array[k] = L[i];
					i = i+1;
				}
				else {
					array[k] = R[j];
					j = j+1;
				}
			//}
			
		}
		return array;
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
