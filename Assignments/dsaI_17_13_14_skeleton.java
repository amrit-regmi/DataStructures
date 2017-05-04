// dsaI_17_13_14_skeleton.java SJ
// 

import fi.joensuu.cs.tra.*;


public class dsaI_17_13_14_skeleton {


    // Usage
    // java dsaI_17_13_14_skeleton [N] [N2] [S] [P]
    // where N1 is # of elements, N2 is # of elements in 2nd list
    // S is random number seed, P is the smallest number to appear
    public static void main(String[] args) {

        // input sizes
        int N1 = 12;
        if (args.length > 0)
            N1 = Integer.valueOf(args[0]);

        int N2 = N1;
        if (args.length > 1)
            N2 = Integer.valueOf(args[1]);

        // random seed
        int seed = 42;
        if (args.length > 2)
            seed = Integer.valueOf(args[2]);

        // smalles number
        // try occasionally, e.g., 260
        int start = 1;
        if (args.length > 3)
            start = Integer.valueOf(args[3]);

        // create input arrays
        Integer[] T1 = new Integer[N1];
        Integer[] T2 = new Integer[N2];

        // fill with elements
        java.util.Random r = new java.util.Random(seed);
        for (int i = 0; i < N1; i++) {
            T1[i] = start + r.nextInt(N1);
        }

        for (int i = 0; i < N2; i++) {
            T2[i] = start + r.nextInt(N2*2);
        }

        // print arrays (unless there are a lot of elements)
        if (N1 <= 20 && N2 <= 20) {
            System.out.print("T1: ");
            for (int i = 0; i < N1; i++)
                System.out.print(" " + T1[i]);
            System.out.println();

            System.out.print("T2: ");
            for (int i = 0; i < N2; i++)
                System.out.print(" " + T2[i]);
            System.out.println();
        }

        // make linked lists out of array contents
        TraLinkedList<Integer> L1 = new TraLinkedList<Integer>();
        TraLinkedList<Integer> L2 = new TraLinkedList<Integer>();
        for (Integer x : T1)
            L1.insert(L1.EOL,x);

        for (Integer x : T2)
            L2.insert(L2.EOL,x);

        // call task 13
        int removed = removeAll13(L1, L2);

        System.out.print("Task 13, L1 after removeAll= ");
        if (N1 <= 20) {
            for (Integer i : L1)
                System.out.print(" " + i);
            System.out.println();
        } 
        System.out.println(traSize(L1) + " elements left, " + removed + " removed");

        // Task 14 test

        // sort input arrays
        java.util.Arrays.sort(T1);
        java.util.Arrays.sort(T2);

        // print arrays (unless there are a lot of elements)
        if (N1 <= 20 && N2 <= 20) {
            System.out.print("T1: ");
            for (int i = 0; i < N1; i++)
                System.out.print(" " + T1[i]);
            System.out.println();

            System.out.print("T2: ");
            for (int i = 0; i < N2; i++)
                System.out.print(" " + T2[i]);
            System.out.println();
        }

        // make linked lists out of array contents
        L1 = new TraLinkedList<Integer>();
        L2 = new TraLinkedList<Integer>();
        for (Integer x : T1)
            L1.insert(L1.EOL,x);

        for (Integer x : T2)
            L2.insert(L2.EOL,x);

        // call task 12
        removed = removeAll14(L1, L2);

        System.out.print("Task 14, L1 after removeAll= ");
        if (N1 <= 20) {
            for (Integer i : L1)
                System.out.print(" " + i);
            System.out.println();
        } 
        System.out.println(traSize(L1) + " elements left, " + removed + " removed");


    } // main()


    /**
     * 13.
     * Removes all elements of L2 from L1
     * Lists are not sorted.
     * Do not alter the order of elements in the lists
     * @param L1 List from which elements are removed
     * @param L2 List of elements to be removed from L1
     * @return number of elements removed
     */

    public static <E extends Comparable<? super E>>
        int removeAll13(TraLinkedList<E> L1, TraLinkedList<E> L2) {
        int removed = 0;
        ListNode tmp = L1.first();
        
        
        
        while (tmp.next() != L1.EOL){
        	
        	//System.out.println(tmp.next().getElement());
        	ListNode tmp2 = L2.first();
        	
        	
        	if(!tmp.getElement().equals(tmp2.getElement())){
        		System.out.println("yes "+ tmp.getElement() +"!="+ tmp2.getElement());
        		while (tmp2.next() != L2.EOL){
        			tmp2=tmp2.next();
        			
        		}
        		
        	}else{
        		tmp2 =L2.first();
        		L1.remove(tmp);
        		removed = removed +1;
        	}

        	tmp = tmp.next();
        }

        // TODO

        return removed;
    } // removeAll13()



    /**
     * 14.
     * Removes all elements of L2 from L1
     * Both lists in ascending order.
     * Linear time.
     * @param L1 List from which elements are removed
     * @param L2 List of elements to be removed from L1
     * @return number of elements removed
     */

    public static <E extends Comparable<? super E>>
        int removeAll14(TraLinkedList<E> L1, TraLinkedList<E> L2) {

        int removed = 0;

        // TODO

        return removed;
    } // removeAll12()




    // count the number of elements in a TraLinkedList
    public static int traSize(TraLinkedList L) {
        int n = 0;
        ListNode p = L.first();
        while (p != L.EOL) {
            n++;
            p = p.next();
        }
        return n;
    }




} // class

