import edu.princeton.cs.algs4.StdRandom;

// 1. Shuffle the array
// 2. Partition the array, so around pivot point j, no larger entry is to the left
// of j and no smaller entry is to the right of j
// 3. Sort each partition recursively

// Invariant: Nothing greater than j is to the left of partitioning element
// Nothing less than j is to the right of the partitioning elelent.

public class Quicksort {

	public Quicksort() {
		// TODO Auto-generated constructor stub
	}
	
	public static void sort(Comparable[] a) {
		// Shuffle required to guarantee performance
		StdRandom.shuffle(a);
		sort(a, 0, a.length-1);
	}
	
	private static void sort(Comparable[] a, int lo, int hi) {
		if (hi <= lo) return;
		int j = partition(a, lo, hi); // j is the new pivot
		sort(a, lo, j - 1);
		sort(a, j + 1, hi);
	}
	
	private static int partition(Comparable[] a, int lo, int hi) {
		int i = lo;
		int j = hi + 1;
		
		while (true) {
			// move the left pointer to the right until it encounters a value
			// greater than the low value
			while(less(a[++i], a[lo])) {
				if (i == hi) {
					break;
				}
			}
			while(less(a[lo], a[--j])) {
				if (j == lo) {
					break;
				}
			}
			
			// Check if the pointers have crossed
			if (i >= j){
				break;
			}
			
			// Swap the elements at i and j
			exch(a, i, j);
		}
		// Swap the partition value on the left with the j value
		exch(a, lo, j);
		return j; // j is the index of the item now known to be in place
	}
	
	private static boolean less(Comparable a, Comparable b) {
		return a.compareTo(b) <= 0;
	}
	
	private static void exch(Comparable[] a, int i, int j) {
		Comparable temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	public static void qshr(int[] array) {
		// Kick off quicksort with the full array
		qshr(array, 0, array.length - 1);
	}

	private static void qshr(int[] array, int left, int right) {
		// bounds check
		if (left >= right) {
			return;
		}
		// pick a pivot (use the middle one here)
		int pivot = array[(left + right)/2];
		
		// Partition this slice of the array.
		// The partition method returns the partition point.
		int index = partition(array, left, right, pivot);
		
		// Quicksort the slices on the left and the right of the partition index.
		qshr(array, left, index - 1);
		qshr(array, index, right);
 	}
	
	private static int partition(int[] array, int left, int right, int pivot) {
		while (left <= right) {
			
			// Move the left pointer towards the right until it comes to a value > pivot
			while (array[left] < pivot) {
				left++;
			}
			// Move the right pointer towards the left until it comes to a value < pivot
			while (array[right] > pivot) {
				right--;
			}
			if (left <= right) {
				swap(array, left, right);
				left++;
				right--;
			}
		}
		// Now this array slice has been partitioned in two
		// Return the partition point
		return left;
	}
	
	private static void swap(int[] array, int i, int j) {
		int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}
}
