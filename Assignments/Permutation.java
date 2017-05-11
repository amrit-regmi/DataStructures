// Permutation.class SJ

import java.util.Random;
import java.util.Iterator;

/**
  * Helping class to generate permutations
  */
public class Permutation 
    implements Iterable<int[]>
{
    static private Integer seed = 1;
    private int N = 1;

    /**
      * creating permutations of size n 
      */
    Permutation(int n) {
        N = n;
        seed = n;
    }

    /**
      * with random seeding
      */
    Permutation(int n, int s) {
        N = n;
        seed = s;
    }

    /** 
     * Iterator to traverse all permutations.
     * next() will return a reference to en int[n] array
      */
    public Iterator<int[]> iterator() {
        return new PermIter();
    }

    /**
      * a random permutation of size given in constructor
      */
    int[] randomPermutation() {
        return randomPermutation(N, seed);
    }

    /**
      * a random permutation of size n
      */
    int[] randomPermutation(int n) {
        return randomPermutation(n, n + seed);
    }

    /**
      *  a random permutation of size n with seed s
      *  if s == 0, returns 0,1,2,...,n
      */
    static int[] randomPermutation(int n, int s) {
        int[] p = new int[n];

        if (s == 0) {
            for (int i = 0 ; i < n; i++)
                p[i] = i;
            return p;
        }


        Random rnd = new Random(s);

        // all positions as unused
        for (int i = 0 ; i < n; i++)
            p[i] = -1;

        // random value for each position
        for (int i = 0 ; i < n; i++) {
            // find next from a random position
            int r = rnd.nextInt(n);
            while (true) { 
                if (p[r] == -1) {
                    p[r] = i;
                    break;
                }
                r = (r+1) % n;
            }
        }

        seed = rnd.nextInt();

        return p;

    } // randomPermutation()

    /**
      * implementation of iterator
      */
    private class PermIter implements Iterator<int[]> {

        int[] result;
        int count;
        
        public PermIter() {
            result = null;
            // calculate N! 
            count = 1;
            for (int i = 2; i <= N; i++)
                count *= i;
        }

        public boolean hasNext() {
            return count > 0;
        }

        // permutaatio kokeilemalla kullekin paikalle lopusta alkaen uusia
        // arvoja
        // O(N^2)
        public int[] next() {
            count--;
            if (result == null) {
                // ensimmäinen permutaatio erikoistapauksena
                result = new int[N];
                for (int i = 0; i < N; i++)
                    result[i] = i;
                return result;
            }

            int c = N-2;
            startpos:
            while (c >= 0) {
                nextitem:
                // haetaan josko kohtaan c löytyisi uusi alkio
                for (int i = result[c] + 1; i < N; i++) {
                    for (int j = 0; j < c; j++) {
                        if (i == result[j])
                            continue nextitem;
                    }
                    // uusi alkio löytyi paikkaan c
                    result[c] = i;

                    // loput paikalleen minimiarvoisina
                    pos:
                    for (int k = c+1; k < N; k++) {
                        // tämän voi tehdä toisinkin, muttei juuri nopeuta
                        cand:
                        for (int l = 0; l < N; l++) {
                            for (int m = 0; m < k; m++)
                                if (result[m] == l)
                                    continue cand;
                            result[k] = l;
                            continue pos;
                        }
                    }
                    break startpos;
                }
                c--;
            }
            return result;

        }

        public void remove() {
        }


    }   // class PermIter




} // class Permutation

