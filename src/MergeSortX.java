/** % java MergeSort.java input.txt
 Note: MergeSort.java uses unchecked or unsafe operations.
 Note: Recompile with -Xlint:unchecked for details.
 A E E L M O P R S T X
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class MergeSortX {


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



    /*******************************************************************
     *  Version that takes Comparator as argument.
     *******************************************************************/

    //less for Comparator
    private static boolean less(Object v, Object w, Comparator comparator) {
        return comparator.compare(v, w)<0;
    }

    //is sorted for Comparator Lo hi
    private static boolean isSorted(Object[] a, Comparator comparator, int lo, int hi) {
        for (int i=lo+1; i<=hi; i++)
            if(less(a[i], a[i-1], comparator)) return  false;
        return  true;
    }

    //isSorted for Comparator
    private  static boolean isSorted(Object[] a, Comparator comparator) {
        return isSorted(a, comparator, 0, a.length-1);
    }

    public static void sort(Object[] a, Comparator comparator) {
        Object[] aux = new Object[a.length];
        sort(a, aux, comparator, 0, a.length-1);
    }

    private static void sort(Object[] a, Object[] aux, Comparator comparator, int lo, int hi) {
        int cutoff =7;
        if (hi <= lo + cutoff-1) {
            Insertion.sort(a, lo ,hi+1, comparator);
            return;
        }

        int mid = lo + (hi-lo)/2;
        sort(a, aux, comparator, lo, mid);
        sort(a, aux, comparator, mid+1, hi);
        if(!less(a[mid+1], a[mid], comparator)) return; //is biggest in 1st half < than smallest in 2nd half?
        merge(a, aux, comparator, lo, mid, hi);

    }

    //Merge for Comparator
    private static void merge(Object[] a, Object[] aux, Comparator comparator, int lo, int mid, int hi) {
        assert isSorted(a, comparator, lo, mid);
        assert isSorted(a, comparator, mid+1, hi);

        //copy array
        for (int k =lo; k<=hi; k++)
            aux[k] = a[k];

        int i=lo, j =mid+1;
        for(int k=lo; k<=hi; k++) {
            if      (i>mid)                         a[k] = aux[j++]; //zakonchilis i
            else if (j>hi)                          a[k] = aux[i++]; //zakonchilis j
            else if (less(a[j], a[i], comparator))  a[k] = aux[j++]; //j<i
            else                                    a[k] = aux[i++]; //i<=j
        }
        assert isSorted(a, comparator, lo, hi);


    }



    public static void main(String[] args) {
        In in = new In(args[0]);
        String[] a = in.readAllStrings();
        MergeSortX.sort(a);

        for (String s: a)
            StdOut.print(" " +s);
    }
}
