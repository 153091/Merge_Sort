/** %java MergeSortBU.java input.txt
 Note: MergeSortBU.java uses unchecked or unsafe operations.
 Note: Recompile with -Xlint:unchecked for details.
 A E E L M O P R S T X
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class MergeSortBuX {

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid+1, hi);

        for (int k = lo; k<=hi; k++)
            aux[k] = a[k];

        int i = lo, j = mid+1;
        for (int k = lo; k<=hi; k++) {
            if      (i>mid)                a[k] = aux[j++]; // i закончились
            else if (j>hi)                 a[k] = aux[i++]; //j закончились
            else if (less(aux[j], aux[i])) a[k] = aux[j++]; //j<i
            else                           a[k] = aux[i++]; //i<=j
        }



    }

    public static void  sort(Comparable[] a) {
        int n = a.length;
        if (n<=7) {
            Insertion.sort(a);
            return;
        }
        Comparable[] aux = new Comparable[n];

        for (int sz = 1; sz<n; sz=sz+sz)
            for (int lo=0; lo<n-sz; lo+=sz+sz)
                merge(a, aux, lo, lo+sz-1, Math.min(n-1, lo+sz+sz-1));
    }

    //sravnenie V and W
    private static boolean less(Comparable v, Comparable w) {
        return  v.compareTo(w)<0;
    }

    private static boolean isSorted(Comparable[]a, int hi, int lo) {
        for (int i = lo+1; i<=hi; i++)
            if(less(a[i], a[i-1])) return false;
            return true;
    }

    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i<a.length; i++)
            if (less(a[i], a[i-1])) return false;
            return true;
    }

    /*******************************************************************
     *  Version that takes Comparator as argument.
     *******************************************************************/
    private static void merge(Object[] a, Object[] aux, Comparator comparator, int lo, int mid, int hi) {
        assert isSorted(a, comparator, lo, mid);
        assert isSorted(a, comparator, mid+1, hi);

        //array copy
        for (int k = lo; k<= hi; k++)
            aux[k] = a[k];

        int i = lo, j = mid+1;
        for(int k= lo; k<=hi; k++){
            if      (i>mid)                            a[k] = aux[j++]; //закончились i
            else if (j>mid)                            a[k] = aux[i++]; // закончлись j
            else if (less(aux[j], aux[i], comparator)) a[k] = aux[j++]; //j<i
            else                                       a[k] = aux[i++]; //i<=j
        }
        assert isSorted(a, comparator, lo, hi);
    }

    public static void sort(Object[] a, Comparator comparator) {
        Object[] aux = new Object[a.length];
        int n = a.length;
        if (n<=7) {
            Insertion.sort(a, comparator);
            return;
        }

        for (int sz = 1; sz<n; sz= sz+sz)
            for(int lo = 0; lo < n-sz; lo+=sz+sz)
                merge(a, aux, comparator, lo, lo+sz-1, Math.min(lo+sz+sz-1, n-1));

    }

    private static boolean less(Object v, Object w, Comparator comparator) {
        return comparator.compare(v, w)< 0;
    }
    private static boolean isSorted(Object[] a, Comparator comparator, int lo, int hi){
        for (int i=lo; i<=hi; i++)
            if (less(a[i], a[i-1], comparator)) return false;
            return true;
    }
    private static boolean isSorted(Object[] a, Comparator comparator){
        return isSorted(a, comparator, 0, a.length-1);
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        String[] a = in.readAllStrings();
        MergeSortBuX.sort(a);
        for (String s : a )
            StdOut.print(" " +s);
    }
}
