/** % java MergeSort.java input.txt
 Note: MergeSort.java uses unchecked or unsafe operations.
 Note: Recompile with -Xlint:unchecked for details.
 A E E L M O P R S T X
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class MergeSort {


    //Merge
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid+1, hi);

        for (int i =lo; i<=hi; i++)
            aux[i] = a[i];

        int i = lo, j = mid+1;

        for (int k = lo; k<= hi; k++) {
            if      (i > mid)               a[k] = aux[j++]; //zakonchilis i
            else if (j > hi)                a[k] = aux[i++]; //zakonchilis j
            else if (less(aux[j], aux[i]))  a[k] = aux[j++]; // j<i
            else                            a[k] = aux[i++]; //i<=j
        }
        assert isSorted(a, lo, hi);
    }

    //recursive sort
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        int cutoff = 7; // if items <7 then Insertion sort
        if (hi <= lo + cutoff -1)
        {
            Insertion.sort(a, lo, hi+1);
            return;
        }
        //if (hi <= lo) return;  - или можно так

        int mid = lo + (hi - lo)/2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid+1, hi);
        if (!less(a[mid+1], a[mid])) return; //is biggest in 1st half < than smallest in 2nd half?
        merge(a, aux, lo, mid, hi);
    }

    //dop. massiv and zapusk sortirovki
    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length-1);
    }

    //is sorted?
    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i<a.length; i++)
            if (less(a[i], a[i-1])) return false;
            return true;
    }

    //is sorted?
    private static boolean isSorted(Comparable[]a, int lo, int hi) {
        for (int i = lo+1; i<=hi; i++)
            if (less(a[i], a[i-1])) return false;
            return true;
    }

    //compare v and w
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w)<0;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        String[] a = in.readAllStrings();
        MergeSort.sort(a);

        for (String s: a)
            StdOut.print(" " +s);
    }
}
