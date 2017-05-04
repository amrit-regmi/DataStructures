// traI_17e_x1_skeleton.java SJ
/**
  * X1. Write an algorithm that gets as parameter two sorted lists (ascending
  * order) (A and B) and which returns a new sorted list (ascending order)
  * which has all the elements that occur in both lists at least twice (at
  * least twice in A and at least twice in B). Each element will, however, be
  * in the result list only once.  What is the time complexity of your
  * algorithm? The time will impact the grading the the task. For full points,
  * you need linear time complexity.  It may be easier to approach the task by
  * making first a version that makes an ordinary intersection, i.e., takes
  * those elements that occur in both lists at least once. If you make only
  * this ordinary intersection, maximum is 4 points (otherwise 6 p).  You can
  * use either java.util.LinkedList or TraLinkedList. Do not use set or map
  * types as helping structure. Do not modify the input lists.  Take a skeleton
  * from course www-page, do not modify the header of method.
  */

// if you do TraLinkedList, you'll need this import and corresponding library
// if you use java.util.LinkedList, you can remove this and compile with
// javac only
//import fi.joensuu.cs.tra.*;

import java.util.LinkedList;

// TODO: change the class name to your username

public class amritreg11 {


    // Main program use
    // java traI_17e_x1_skeleton [N] [N2] [S] [diff]
    // where N is # of elements, N2 is # of elements on second list
    // S is random number seed, and diff to ensure that there won't be
    // common elements in lists

    // please test with diverse inputs
    // you may also yourself generate different types of input

    public static void main(String[] args) {

        // array size
        int N1 = 15;
        if (args.length > 0)
            N1 = Integer.valueOf(args[0]);

        int N2 = N1+3;
        if (args.length > 0)
            N2 = Integer.valueOf(args[1]);

        // random seed
        int seed = 42;
        if (args.length > 2)
            seed = Integer.valueOf(args[2]);

        // same elements or not
        int diff = 0;
        if (args.length > 3)
            diff = 1;


        // create input arrays -> lists
        Integer[] T1 = new Integer[N1];
        Integer[] T2 = new Integer[N2];

        java.util.Random r = new java.util.Random(seed);

        for (int i = 0; i < N1; i++) {
            T1[i] = r.nextInt(N1/2);
        }
        java.util.Arrays.sort(T1);

        for (int i = 0; i < N2; i++) {
            T2[i] = r.nextInt(N2/2) + diff * N1;
        }
        java.util.Arrays.sort(T2);

        // print if there are not too many elements
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

        // create lists

        LinkedList<Integer> L1 = new LinkedList<Integer>();
        LinkedList<Integer> L2 = new LinkedList<Integer>();
        //TraLinkedList<Integer> TL1 = new TraLinkedList<Integer>();
        //TraLinkedList<Integer> TL2 = new TraLinkedList<Integer>();

        for (Integer x : T1) {
            L1.add(x);
            //TL1.insert(TL1.EOL, x);
        }

        for (Integer x : T2) {
            L2.add(x);
            //TL2.insert(TL2.EOL, x);
        }

        // call task X1
        // either of these suffices!!!
        LinkedList<Integer> TLtulos = twoCommonsX1(L1, L2);
        //TraLinkedList<Integer> TTLtulos = twoCommonsX1(TL1, TL2);

        System.out.print("Task X1    (LinkedList), twoCommons = ");
        if (N1 <= 20 && N2 <= 20) {
            for (Integer i : TLtulos)
                System.out.print(" " + i);
            System.out.println();
        } else {
            System.out.println(TLtulos.size() + " elements");
        }

        /*System.out.print("Task X1 (TraLinkedList), twoCommons = ");
        if (N1 <= 20 && N2 <= 20) {
            for (Integer i : TTLtulos)
                System.out.print(" " + i);
            System.out.println();
        } else {
            int n = 0;
            for (Integer x : TTLtulos)
                n++;
            System.out.println(n + " elements");
        }*/

    } // main()



    /**
     * Double intersection of lists.
     * Creates a new list of those elements that occur in both
     * input lists at least twice.
     * Version TraLinkedList, one of these suffices.
     * @param L1 input list, in ascending order
     * @param L2 input list, in ascending order
     * @return list of elements that are in both lists at least twice.
     */
   /* public static <E extends Comparable<? super E>>
    	TraLinkedList<E> twoCommonsX1(TraLinkedList<E> L1, TraLinkedList<E> L2) {

        // result list
        TraLinkedList<E> twoCommons = new TraLinkedList<E>();

        // TODO

        // please make a plan with pen and paper _before_ writing anything here...

        return twoCommons;
    }   // twoCommonsX1
*/


    
    /**
     * Double intersection of lists.
     * Creates a new list of those elements that occur in both
     * input lists at least twice.
     * Version LinkedList, one of these suffices.
     * @param L1 input list, in ascending order
     * @param L2 input list, in ascending order
     * @return list of elements that are in both lists at least twice.
     */
    public static <E extends Comparable<? super E>>
    	LinkedList<E> twoCommonsX1(LinkedList<E> L1, LinkedList<E> L2) {

        // result list
        LinkedList<E> twoCommons = new LinkedList<E>();

        // TODO
        LinkedList<E> L1d = new LinkedList<E>(); 
        LinkedList<E> L2d = new LinkedList<E>();
        
       /*
        * Removing the repeating elements from first list and assigning it to new list L1d
        * total time complexity will be O(n)
        */
        for (int i = 0 ; i < L1.size() ; i++){  
        	if(i+1<L1.size()){
        		if(L1.get(i).equals(L1.get(i+1))){
        			if (!L1d.isEmpty()){
        				if(!L1d.getLast().equals(L1.get(i))){
        					L1d.add(L1.get(i));	
        				}
        			}else {
    					L1d.add(L1.get(i));
    				}
        			
        		}
        	}
        	
        	
        	
        }
        
        /*
         * Removing the repeating elements from second  list and assigning it to new list L2d
         * total time complexity will be O(n)
         */
        
        
        for (int i = 0 ; i < L2.size() ; i++){
        	if(i+1<L2.size()){
        		if(L2.get(i).equals(L2.get(i+1))){
        			if (!L2d.isEmpty()){
        				if(!L2d.getLast().equals(L2.get(i))){
        					L2d.add(L2.get(i));	
        				}
        			}else {
    					L2d.add(L2.get(i));
    				}
        			
        		}
        	}
        	
        	
        	
        }
       
        /*
         * Compares the value of element on both list L1d and L2d starts from beginning and 
         * if no match compares the next element from the list with smaller value while moving along the both list
         * total time complexity is O(length of first list + length of second list) = O(n) 
         */
         
        int i1 = 0;
        int i2 = 0;
        
        while (true){    	
	        if(L1d.get(i1).compareTo(L2d.get(i2))<0 ){
	        	i1 = i1+1;
	        	
	        }
	        else if(L1d.get(i1).compareTo(L2d.get(i2))>0 ){
	        	i2=i2+1;
	        	
	        }
	        else if(L1d.get(i1).compareTo(L2d.get(i2))==0 ){
	        	twoCommons.addLast(L1d.get(i1));
	        	
	        	/*
	        	 * if any one list have the match with last element then the loop will stop
	        	 */
	        	if(L1d.getLast().equals(L1d.get(i1)) || L2d.getLast().equals(L2d.get(i2))){ 
	        		break;
	        	}
	        	
	        	i1 = i1+1;
	        	i2 = i2+1;
	        	}
        	/*
        	 * worst case if both list has been iterated until their last element then the loop will stop
        	 */
	        if(i1+i2 > L1d.size()+L2d.size()){
        			break;	
        	}
        	
	        }
        /*
         * Total time complexity would be sum of all which is equivalent to O(n).
         */

        return twoCommons;
    } // twoCommonsX1()

    

} // class