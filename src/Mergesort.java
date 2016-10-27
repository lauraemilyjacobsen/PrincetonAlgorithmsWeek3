import edu.princeton.cs.algs4.*;

public class Mergesort {

	public Mergesort() {
		CUTOFF = 7;
	}
	
	static int CUTOFF;
	
	public static void sort(Comparable[] a) {
		// Create the aux array only once at kickoff
		Comparable[] aux = new Comparable[a.length];
		divide(a, aux, 0, a.length - 1);
	}
	
	// Divides an array segment into two segments, sorts each segment, and merges them
	private static void divide(Comparable[] a, Comparable[] aux, int lo, int hi) {
		System.out.println("sorting from " + lo + " to " + hi);
		
		// bounds check
		if (hi <= lo) return;
		
		// Insertion sort is better than mergesort for arrays of up to about 7
		if (hi - lo < CUTOFF) {
			Insertion.sort(a, lo, hi);
			return; // finish up this segment without recursing with small arrays.
		}
		
		// calculate mid
		int mid = lo + (hi - lo) / 2;
		

		// If the two halves are already merged, don't do anything
		// This is the case if the highest number in the left side is less than or equal to the smallest number on the right side
		if (!less(a[mid + 1], a[mid])) return;
		
		// divide and sort the two halves
		divide(a, aux, lo, mid);
		divide(a, aux, mid + 1, hi);
		
		// merge the two halves
		merge(a, aux, lo, mid, hi);
	}

	private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {

		System.out.println("merging " + lo + "-" + mid + " with " + (mid + 1) + "-" + hi);
		
		// Preconditions: the array elements from lo to mid are sorted
		// the array elements from mid + 1 to hi are sorted
		assert isSorted(a, lo, mid);
		assert isSorted(a, mid + 1, hi);

		// 1. Copy the array into an auxiliary array
		for (int i = 0; i < a.length; i++) {
			aux[i] = a[i];
		}

		// 2. Set up pointers i and j to the first element of each half
		int i = lo, j = mid + 1;

		// 3. Compare aux[i] and aux[j] k times, making one copy from aux to a
		// for each comparison

		for (int k = lo; k <= hi; k++) {
			// i's side is exhausted so copy aux[j] and increment j
			if (i > mid) {
				a[k] = aux[j++];
			}
			// j's side is exahusted so copy aux[i] and increment i
			else if (j > hi) {
				a[k] = aux[i++];
			}
			
			// if aux[i] is less than or equal to aux[j], copy aux[i] to old
			// array[k] and increment i
			// if aux[j] is less than aux[i], copy aux[j] to old array[k] and
			// increment j
			else if (less(aux[i], aux[j])) {
				a[k] = aux[i++];
			} else {
				a[k] = aux[j++];
			}
		}
		for (Comparable m : a) {
			System.out.print(m + " ");
		}
		System.out.println();
		assert isSorted(a, lo, hi);
	}
	
	public static void bottomUpSort(Comparable[] a) {
		Comparable[] aux = new Comparable[a.length];
		
		int n = a.length;
		// Iterate in doubling step sizes
		// outer loop gets executed log n times
		for (int sz = 1; sz < n; sz = sz + sz) {
			for (int lo = 0; lo < n - sz; lo += sz + sz) {
				merge(a, aux, lo, lo + sz-1, Math.min(lo + sz+sz-1, n-1));
			}
		}
		
	}

	private static boolean less(Comparable a, Comparable b) {
		return a.compareTo(b) < 0;
	}

	private static boolean isSorted(Comparable[] a) {
		return isSorted(a, 0, a.length - 1);
	}

	// is the array sorted from a[lo] to a[hi]
	private static boolean isSorted(Comparable[] a, int lo, int hi) {
		for (int i = lo + 1; i <= hi; i++)
			if (less(a[i], a[i - 1]))
				return false;
		return true;
	}
	
	public static void main(String[] args) {
		Integer[] a = new Integer[10];
		a[0] = 9;
		a[1] = 8;
		a[2] = 3;
		a[3] = 5;
		a[4] = 2;
		a[5] = 6;
		a[6] = 7;
		a[7] = 4;
		a[8] = 1;
		a[9] = 5;
		
		sort(a);
		
		for (Integer i : a) {
			System.out.print(i + " ");
		}
	}

}
